    package my.personal.stocklurker.application.asset.usecase.assetposition;

    import lombok.RequiredArgsConstructor;
    import my.personal.stocklurker.application.asset.dto.command.AddAssetPositionCommand;
    import my.personal.stocklurker.application.asset.dto.command.SaveAssetCommand;
    import my.personal.stocklurker.application.asset.usecase.FindAssetByIsinUseCase;
    import my.personal.stocklurker.application.asset.usecase.SaveAssetUseCase;
    import my.personal.stocklurker.domain.asset.model.Asset;
    import my.personal.stocklurker.domain.asset.model.AssetPosition;
    import my.personal.stocklurker.infrastructure.persistence.adapter.AssetPositionJpaAdapter;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.stereotype.Component;

    import java.util.Optional;

    @Component
    @RequiredArgsConstructor
    public class SaveAssetPositionUseCase {

        private static final Logger logger = LoggerFactory.getLogger(SaveAssetPositionUseCase.class);

        private final AssetPositionJpaAdapter assetPositionJpaAdapter;
        private final FindAssetByIsinUseCase findAssetByIsinUseCase;
        private final SaveAssetUseCase saveAssetUseCase;

        public AssetPosition execute(AddAssetPositionCommand command) {
            Asset asset;
            Optional<Asset> assetOpt = findAssetByIsinUseCase.execute(command.asset().isin);
            if (assetOpt.isEmpty()) {
                logger.info("The asset with ISIN {} does not exist in the database. Saving into DB...", command.asset().isin.value());
                asset = saveAssetUseCase.execute(new SaveAssetCommand(command.asset().isin, command.asset().name, command.asset().description));
            } else {
                asset = assetOpt.get();
            }
            AssetPosition assetPosition = new AssetPosition(
                    asset,
                    command.shares(),
                    command.price(),
                    command.timestamp(),
                    command.market()
            );
            return assetPositionJpaAdapter.save(assetPosition);
        }
    }
