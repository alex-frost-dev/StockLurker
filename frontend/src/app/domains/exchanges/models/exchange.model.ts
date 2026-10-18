export enum ExchangeType {
  BUY = 'BUY',
  SELL = 'SELL'
}

export interface ExchangeAsset {
  name: string;
  isin: string;
  description: string;
}

export interface ExchangePosition {
  asset: ExchangeAsset;
  shares: number;
  price: number;
  timestamp: Date;
  market: string;
  exchangeType: ExchangeType;
}