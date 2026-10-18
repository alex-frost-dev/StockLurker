export enum TransactionType {
  BUY = 'BUY',
  SELL = 'SELL'
}

export interface TransactionAsset {
  name: string;
  isin: string;
  description: string;
}

export interface TransactionPosition {
  asset: TransactionAsset;
  shares: number;
  price: number;
  timestamp: Date;
  market: string;
  transactionType: TransactionType;
}