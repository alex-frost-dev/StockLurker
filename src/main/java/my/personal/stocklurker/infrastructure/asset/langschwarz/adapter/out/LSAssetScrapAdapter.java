package my.personal.stocklurker.infrastructure.asset.langschwarz.adapter.out;

import my.personal.stocklurker.domain.asset.exception.AssetException;
import my.personal.stocklurker.domain.asset.model.*;
import my.personal.stocklurker.domain.asset.port.out.ScraperPort;
import my.personal.stocklurker.infrastructure.asset.langschwarz.adapter.out.dto.LSAssetInfoDto;
import my.personal.stocklurker.infrastructure.asset.langschwarz.adapter.out.mapper.LSAssetInfoMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.Optional;

@Component
public class LSAssetScrapAdapter implements ScraperPort {

    private final RestClient restClient;
    private final LSAssetInfoMapper mapper;

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

    private static final String BID_XPATH = "/html/body/div[2]/div/div[4]/div/div[1]/div/div[2]/div[2]/div/span";
    private static final String ASK_XPATH = "/html/body/div[2]/div/div[4]/div/div[1]/div/div[2]/div[3]/div/span";
    private static final String CURRENCY_XPATH = "/html/body/div[2]/div/div[4]/div/div[1]/div/div[2]/div[2]/div";
    private static final String INSTANT_XPATH = "/html/body/div[2]/div/div[4]/div/div[1]/div/div[1]/div/div[2]/div/span[4]/span";

    public LSAssetScrapAdapter(RestClient.Builder restClientBuilder, LSAssetInfoMapper mapper) {
        this.restClient = restClientBuilder.build();
        this.mapper = mapper;
    }

    @Override
    public AssetPrice scrapPricedAsset(ISIN isin) {
        try {
            Optional<LSAssetInfoDto> optAssetInfoDto = lookupAssetInfo(isin);

            if (optAssetInfoDto.isEmpty()) {
                throw new AssetException("The request to Lang & Schwarz of ISIN {} returned an invalid result", isin.value());
            }
            LSAssetInfoDto assetInfoDto = optAssetInfoDto.get();

            String htmlResponse = restClient.get()
                    .uri(ROOT_URL + ASSET_PATH + assetInfoDto.getId())
                    .retrieve()
                    .body(String.class);

            Document doc = Jsoup.parse(htmlResponse);
            Float bid = Float.valueOf(doc.selectXpath(BID_XPATH).text().trim());
            Float ask = Float.valueOf(doc.selectXpath(ASK_XPATH).text().trim());
            Currency currency = Currency.valueOfSymbol(
                    Objects.requireNonNull(doc.selectXpath(CURRENCY_XPATH).first()).ownText().trim());
            Instant instant = parseLSTimeToInstant(doc.selectXpath(INSTANT_XPATH).text().trim());

            return new AssetPrice(
                    mapper.toAsset(assetInfoDto),
                    Market.LS,
                    instant,
                    bid,
                    ask,
                    currency
            );

        } catch (NullPointerException ex) {
            throw new AssetException("The extraction of the asset with ISIN '{}' threw an unexpected error", isin.value());
        }
    }

    private Optional<LSAssetInfoDto> lookupAssetInfo(ISIN isin) {
        LSAssetInfoDto[] jsonDto = restClient.get()
                .uri(ROOT_URL + INSTRUMENT_PATH + SEARCH_PATH, uriBuilder -> {
                    uriBuilder.queryParam("q", isin.value());
                    uriBuilder.queryParam("localeId", 1);
                    return uriBuilder.build();
                })
                .retrieve()
                .body(LSAssetInfoDto[].class);

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