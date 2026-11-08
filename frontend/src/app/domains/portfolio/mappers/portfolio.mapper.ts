import { AssetMapper } from '../../asset/mappers/asset.mapper';
import { PortfolioPositionSummaryDTO } from '../dtos/portfolio.dto';
import { Portfolio, PortfolioPositionSummary } from '../models/portfolio.model';


export class PortfolioMapper {
  
  static toPortfolioDomain(dto: PortfolioPositionSummaryDTO): Portfolio {
    return {
      portfolioPositionSummary: [this.toPortfolioPositionSummaryDomain(dto)]
    }
  }

  static toPortfolioPositionSummaryDomain(dto: PortfolioPositionSummaryDTO): PortfolioPositionSummary {
    return {
      portfolioPosition: {
        id: dto.portfolioPosition.id,
        asset: AssetMapper.toDomain(dto.portfolioPosition.asset),
        shares: dto.portfolioPosition.shares,
        marketPrice: dto.portfolioPosition.marketPrice,
        averageBuyPrice: dto.portfolioPosition.averageBuyPrice
      },
      absoluteProfit: dto.absoluteProfit,
      percentageProfit: dto.percentageProfit
    };
  }
}