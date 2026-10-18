import { AssetMapper } from '../../asset/mappers/asset.mapper';
import { PortfolioDTO } from '../dtos/portfolio.dto';
import { Portfolio } from '../models/portfolio.model';

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