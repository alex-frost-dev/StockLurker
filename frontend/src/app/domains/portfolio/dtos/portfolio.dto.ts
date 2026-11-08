import { AssetDTO } from "../../asset/dtos/asset.dto";

export interface PortfolioDTO {
  portfolioPositions: PortfolioPositionDTO[];
}

export interface PortfolioPositionDTO {
  id: string;
  asset: AssetDTO;
  shares: number;
  marketPrice: number;
  averageBuyPrice: number;
}

export interface PortfolioPositionSummaryDTO {
  portfolioPosition: PortfolioPositionDTO;
  absoluteProfit: number;
  percentageProfit: number;
}