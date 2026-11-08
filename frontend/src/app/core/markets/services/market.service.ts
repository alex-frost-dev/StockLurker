import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MarketService {
  readonly currentMarket = signal<string>('LS');

  setMarket(market: string): void {
    this.currentMarket.set(market);
  }
}