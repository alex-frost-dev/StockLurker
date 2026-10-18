import { AssetExchangeDTO, AddAssetExchangeRequest } from '../dtos/exchange.dto';
import { ExchangePosition } from '../models/exchange.model';

export class ExchangeMapper {
  
  static toDomain(dto: AssetExchangeDTO): ExchangePosition {
    return {
      asset: {
        name: dto.asset.name,
        isin: dto.asset.isin,
        description: dto.asset.description ?? ''
      },
      shares: dto.shares,
      price: dto.price,
      timestamp: new Date(dto.timestamp), // String -> Date
      market: dto.market,
      exchangeType: dto.exchangeType
    };
  }

  static toDto(position: ExchangePosition): AssetExchangeDTO {
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
      exchangeType: position.exchangeType
    };
  }

  static toAddRequest(position: ExchangePosition): AddAssetExchangeRequest {
    return {
      position: this.toDto(position)
    };
  }
}