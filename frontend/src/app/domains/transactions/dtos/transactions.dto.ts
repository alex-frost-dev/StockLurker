import { AssetDTO } from "../../asset/dtos/asset.dto";
import { TransactionType } from "../models/transactions.model";

export interface AssetTransactionDTO {
  asset: AssetDTO;
  shares: number;
  price: number;
  timestamp: string;
  market: string;
  transactionType: TransactionType;
}

export interface AddAssetTransactionRequest {
  position: AssetTransactionDTO;
}

