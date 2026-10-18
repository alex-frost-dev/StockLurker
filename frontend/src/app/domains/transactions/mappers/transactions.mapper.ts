import { AddAssetTransactionRequest, AssetTransactionDTO } from "../dtos/transactions.dto";
import { TransactionPosition } from "../models/transactions.model";

export class TransactionMapper {
  
  static toDomain(dto: AssetTransactionDTO): TransactionPosition {
    return {
      asset: {
        name: dto.asset.name,
        isin: dto.asset.isin,
        description: dto.asset.description ?? ''
      },
      shares: dto.shares,
      price: dto.price,
      timestamp: new Date(dto.timestamp),
      market: dto.market,
      transactionType: dto.transactionType
    };
  }

  static toDto(position: TransactionPosition): AssetTransactionDTO {
    return {
      asset: {
        name: position.asset.name,
        isin: position.asset.isin,
        description: position.asset.description
      },
      shares: position.shares,
      price: position.price,
      timestamp: position.timestamp.toISOString(),
      market: position.market,
      transactionType: position.transactionType
    };
  }

  static toAddRequest(position: TransactionPosition): AddAssetTransactionRequest {
    return {
      position: this.toDto(position)
    };
  }
}