import { AssetDTO } from "../../asset/dtos/asset.dto";
import { ExchangeType } from "../models/exchange.model";

export interface AssetExchangeDTO {
  asset: AssetDTO;
  shares: number;
  price: number;
  timestamp: string;
  market: string;
  exchangeType: ExchangeType;
}

export interface AddAssetExchangeRequest {
  position: AssetExchangeDTO;
}

