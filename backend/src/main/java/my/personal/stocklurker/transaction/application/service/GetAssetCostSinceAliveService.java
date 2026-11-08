package my.personal.stocklurker.transaction.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.common.domain.utils.BigDecimalUtils;
import my.personal.stocklurker.transaction.application.port.in.GetAssetCostSinceAliveUseCase;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAssetCostSinceAliveService implements GetAssetCostSinceAliveUseCase {

    private final AssetTransactionPort assetTransactionPort;

    public BigDecimal execute(ISIN isin) {
        List<AssetTransaction> assetTransactions = assetTransactionPort.findAllByISIN(isin);
        BigDecimal averageCost = BigDecimal.ZERO;
        BigDecimal totalShares = BigDecimal.ZERO;
        for (AssetTransaction transaction : assetTransactions) {
            BigDecimal positionTotalCost;
            positionTotalCost = BigDecimalUtils.multiply(transaction.shares,transaction.price);
            positionTotalCost = BigDecimalUtils.add(positionTotalCost, transaction.tax);
            positionTotalCost = BigDecimalUtils.add(positionTotalCost, transaction.commission);

            // AVERAGE COST
            if (transaction.transactionType.isBuy()) {
                if (averageCost.equals(BigDecimal.ZERO)) { // first calculus
                    averageCost = BigDecimalUtils.divide(positionTotalCost, transaction.shares);
                } else {
                    BigDecimal generalTotalCost = BigDecimalUtils.multiply(totalShares, averageCost);
                    BigDecimal combinedTotalCost = BigDecimalUtils.add(generalTotalCost, positionTotalCost);
                    BigDecimal combinedShares = BigDecimalUtils.add(totalShares, transaction.shares);
                    averageCost = BigDecimalUtils.divide(combinedTotalCost, combinedShares);
                }
            }

            // SHARES
            if (transaction.transactionType.isBuy()) {
                totalShares = BigDecimalUtils.add(totalShares, transaction.shares);
            } else { //transaction.transactionType.isSell()
                totalShares = BigDecimalUtils.subtract(totalShares, transaction.shares);
            }

            // STOP IF ASSET RESET
            if (totalShares.intValue() == 0) {
                break;
            }
        }
        return averageCost;
    }
}
