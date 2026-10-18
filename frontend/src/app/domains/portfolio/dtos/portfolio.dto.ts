import { AssetDTO } from "../../asset/dtos/asset.dto";

export interface PortfolioDTO {
  portfolioPositions: PortfolioPositionDTO[];
}

export interface PortfolioPositionDTO {
  asset: AssetDTO;
  shares: number;
  price: number;
}