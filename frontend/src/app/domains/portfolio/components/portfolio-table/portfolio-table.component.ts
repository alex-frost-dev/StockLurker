import { Component, inject, OnInit, signal, effect } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { PortfolioService } from '../../services/portfolio.service';
import { Portfolio } from '../../models/portfolio.model';
import { RouterLink } from '@angular/router';
import { MarketService } from '../../../../core/markets/services/market.service';
import { formatCurrency } from '../../../../shared/utils/format.util';

@Component({
  selector: 'app-portfolio-table',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, RouterLink],
  templateUrl: './portfolio-table.component.html',
  styleUrl: './portfolio-table.component.css'
})
export class PortfolioTableComponent implements OnInit {
  private portfolioService = inject(PortfolioService);
  private marketService = inject(MarketService);

  portfolio = signal<Portfolio>({ portfolioPositionSummary: []});
  isLoading = signal<boolean>(false);
  errorMessage = signal<string | null>(null);

  // Control del toast (booleano en lugar de string)
  showCopiedToast = signal<boolean>(false);
  isLeaving = signal<boolean>(false);
  toastKey = signal<number>(0);
  private copyTimeout: any;
  private fadeTimeout: any;

  ngOnInit(): void {
    this.loadPortfolio();
  }

  copyToClipboard(text: string) {
    navigator.clipboard.writeText(text).then(() => {
      // Limpiamos timers previos si hace clic rápido
      if (this.copyTimeout) clearTimeout(this.copyTimeout);
      if (this.fadeTimeout) clearTimeout(this.fadeTimeout);

      // Reseteamos estados para relanzar entrada
      this.isLeaving.set(false);
      this.showCopiedToast.set(false);
      
      setTimeout(() => {
        this.toastKey.update(k => k + 1);
        this.showCopiedToast.set(true);
      }, 10);

      // A los 1.75s activamos la animación de salida (antes de que se cumplan los 2s)
      this.copyTimeout = setTimeout(() => {
        this.isLeaving.set(true);
      }, 1750);

      // A los 2.0s removemos el componente por completo del DOM
      this.fadeTimeout = setTimeout(() => {
        this.showCopiedToast.set(false);
        this.isLeaving.set(false);
      }, 2000);

    }).catch(err => {
      console.error('Failed to copy: ', err);
    });
  }

  loadPortfolio(): void {
    this.isLoading.set(true);
    this.errorMessage.set(null);
    console.log('Loading portfolio for market:', this.marketService.currentMarket());
    this.portfolioService.getPortfolio(this.marketService.currentMarket()).subscribe({
      next: (data) => {
        this.portfolio.set(data);
        this.isLoading.set(false);
      },
      error: (err) => {
        this.errorMessage.set('Failed to load portfolio stocks...');
        this.isLoading.set(false);
        console.error(err);
      }
    });
  }

  formatMoney(value: number, digits: string = '1.2-2'): string {
    return formatCurrency(value, digits);
  }

  formatShares(value: number): string {
    return new Intl.NumberFormat('es-ES', { minimumFractionDigits: 0, maximumFractionDigits: 4 }).format(value);
  }
}