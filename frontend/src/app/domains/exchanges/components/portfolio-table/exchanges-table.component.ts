// src/app/domains/portfolio/components/portfolio-table/portfolio-table.component.ts
import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
  import { ExchangesService } from '../../services/exchanges.service';
import { ExchangePosition } from '../../models/exchange.model';

@Component({
  selector: 'app-exchanges-table',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, DatePipe],
  templateUrl: './exchanges-table.component.html'
})
export class ExchangesTableComponent implements OnInit {
  private exchangesService = inject(ExchangesService);

  stocks = signal<ExchangePosition[]>([]);
  isLoading = signal<boolean>(false);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    this.loadStocks();
  }

  loadStocks(): void {
    this.isLoading.set(true);
    this.errorMessage.set(null);

    this.exchangesService.getAllExchanges().subscribe({
      next: (data) => {
        this.stocks.set(data);
        this.isLoading.set(false);
      },
      error: (err) => {
        this.errorMessage.set('Failed to load portfolio stocks...');
        this.isLoading.set(false);
        console.error(err);
      }
    });
  }

  trackByIsin(index: number, position: ExchangePosition): string {
    return position.asset.isin;
  }
}