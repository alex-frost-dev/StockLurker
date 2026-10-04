package my.personal.stocklurker.asset.infrastructure.adapter.out;

import my.personal.stocklurker.asset.application.dto.AssetInfoDTO;
import my.personal.stocklurker.asset.application.mapper.AssetInfoMapper;
import my.personal.stocklurker.asset.domain.exception.AssetException;
import my.personal.stocklurker.asset.domain.model.AssetPrice;
import my.personal.stocklurker.asset.domain.model.Currency;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.domain.port.out.AssetPricePort;
import my.personal.stocklurker.market.application.usecase.FindMarketByCodeUseCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component
public class LSAssetAdapter implements AssetPricePort {

    private final RestClient restClient;
    private final AssetInfoMapper mapper;

    private static final ZoneId LS_ZONE_ID = ZoneId.of("Europe/Berlin");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .optionalStart()
            .appendPattern("dd.MM.")
            .optionalStart()
            .appendPattern("yyyy")
            .optionalEnd()
            .appendLiteral(" ")
            .optionalEnd()
            .appendPattern("HH:mm")
            .optionalStart()
            .appendPattern(":ss")
            .optionalEnd()
            .toFormatter();

    private static final String ROOT_URL = "https://www.ls-tc.de";
    private static final String INSTRUMENT_PATH = "/_rpc/json/.lstc/instrument";
    private static final String SEARCH_PATH = "/search/main";
    private static final String ASSET_PATH = "/en/stock/";
    private static final String MARKET_CODE = "LS";

    private final FindMarketByCodeUseCase findMarketByCodeUseCase;

    public LSAssetAdapter(@Qualifier("scraperRestClient") RestClient restClient, AssetInfoMapper mapper, FindMarketByCodeUseCase findMarketByCodeUseCase) {
        this.restClient = restClient;
        this.mapper = mapper;
        this.findMarketByCodeUseCase = findMarketByCodeUseCase;
    }

    @Override
    public AssetPrice scrapPricedAsset(ISIN isin) {
        try {
            Optional<AssetInfoDTO> optAssetInfoDto = lookupAssetInfo(isin);

            if (optAssetInfoDto.isEmpty()) {
                throw new AssetException("The request to Lang & Schwarz of ISIN {} returned an invalid result", isin.value());
            }
            AssetInfoDTO assetInfoDto = optAssetInfoDto.get();

            String htmlResponse = restClient.get()
                    .uri(ROOT_URL + ASSET_PATH + assetInfoDto.getId())
                    .retrieve()
                    .body(String.class);

            Document doc = Jsoup.parse(htmlResponse);
            String rawBid = doc.select("span[field=bid]").text().trim().replace(",", ".");
            String rawAsk = doc.select("span[field=ask]").text().trim().replace(",", ".");
            String rawInstant = doc.select("span[field=midTime]").first().text().trim();
            Element bidSpan = doc.select("span[field=bid]").first();
            String rawCurrencySymbol = (bidSpan != null && bidSpan.parent() != null)
                    ? bidSpan.parent().ownText().trim()
                    : "€";

            Float bid = Float.parseFloat(rawBid);
            Float ask = Float.parseFloat(rawAsk);
            Instant instant = parseLSTimeToInstant(rawInstant);
            Currency currency = Currency.valueOfSymbol(rawCurrencySymbol);

            return new AssetPrice(
                    mapper.toAsset(assetInfoDto),
                    findMarketByCodeUseCase.execute(MARKET_CODE),
                    instant,
                    bid,
                    ask,
                    currency
            );

        } catch (NullPointerException ex) {
            throw new AssetException("The extraction of the asset with ISIN '{}' threw an unexpected error", isin.value());
        }
    }

    private Optional<AssetInfoDTO> lookupAssetInfo(ISIN isin) {
        AssetInfoDTO[] jsonDto = restClient.get()
                .uri(ROOT_URL + INSTRUMENT_PATH + SEARCH_PATH, uriBuilder -> {
                    uriBuilder.queryParam("q", isin.value());
                    uriBuilder.queryParam("localeId", 1);
                    return uriBuilder.build();
                })
                .retrieve()
                .body(AssetInfoDTO[].class);

        if (jsonDto == null || jsonDto.length == 0) {
            return Optional.empty();
        }

        return Optional.of(jsonDto[0]);
    }

    private Instant parseLSTimeToInstant(String timeStr) {
        if (timeStr == null || timeStr.isBlank()) {
            return null;
        }

        String cleanedTimeStr = timeStr.trim();
        TemporalAccessor parsed = DATE_TIME_FORMATTER.parseBest(
                cleanedTimeStr,
                LocalDateTime::from,
                LocalTime::from
        );

        if (parsed instanceof LocalDateTime localDateTime) { //  "19.09. 12:58"
            if (!parsed.isSupported(java.time.temporal.ChronoField.YEAR)) {
                localDateTime = localDateTime.withYear(LocalDate.now(LS_ZONE_ID).getYear());
            }
            return localDateTime.atZone(LS_ZONE_ID).toInstant();
        } else if (parsed instanceof LocalTime localTime) { // "12:58" o "12:58:30"
            return localTime.atDate(LocalDate.now(LS_ZONE_ID))
                    .atZone(LS_ZONE_ID)
                    .toInstant();
        }

        throw new AssetException("Failed to parse date/time string: {}", timeStr);
    }
}