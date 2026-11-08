package my.personal.stocklurker.transaction.application.port.in;

import java.util.UUID;

public interface DeleteAssetTransactionUseCase {

    UUID execute(UUID id);

}
