import { Asset } from "../../asset/model/asset.model";

export interface Portfolio {
    portfolioPositions: PortfolioPosition[];
}

export interface PortfolioPosition {
  asset: Asset;
  shares: number;
  price: number;
}