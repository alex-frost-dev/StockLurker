import { AssetMapper } from '../../asset/mappers/asset.mapper';
import { PortfolioDTO } from '../dtos/portfolio.dto';
import { Portfolio } from '../models/exchange.model';

export class PortfolioMapper {
  
  static toDomain(dto: PortfolioDTO): Portfolio {
    return {
        portfolioPositions: dto.portfolioPositions.map(position => ({
            asset: AssetMapper.toDomain(position.asset),
            shares: position.shares,
            price: position.price
        })),
    }
  }
}