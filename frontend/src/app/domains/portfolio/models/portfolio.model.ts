import { Asset } from "../../asset/model/asset.model";

export interface Portfolio {
    portfolioPositionSummary: PortfolioPositionSummary[];
}

export interface PortfolioPosition {
  id: string;
  asset: Asset;
  shares: number;
  marketPrice: number;
  averageBuyPrice: number;
}

export interface PortfolioPositionSummary {
  portfolioPosition: PortfolioPosition;
  absoluteProfit: number;
  percentageProfit: number;
}