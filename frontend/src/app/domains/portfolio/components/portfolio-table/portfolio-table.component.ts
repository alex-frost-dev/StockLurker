import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { PortfolioService } from '../../services/portfolio.service';
import { Portfolio } from '../../models/portfolio.model';

@Component({
  selector: 'app-portfolio-table',
  standalone: true,
  imports: [CommonModule, CurrencyPipe],
  templateUrl: './portfolio-table.component.html'
})
export class PortfolioTableComponent implements OnInit {
  private portfolioService = inject(PortfolioService);

  portfolio = signal<Portfolio>({ portfolioPositions: [] });
  isLoading = signal<boolean>(false);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    this.loadPortfolio();
  }

  loadPortfolio(): void {
    this.isLoading.set(true);
    this.errorMessage.set(null);

    this.portfolioService.getPortfolio().subscribe({
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
}