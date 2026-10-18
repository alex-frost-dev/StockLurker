// src/app/domains/portfolio/components/portfolio-table/portfolio-table.component.ts
import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { TransactionPosition } from '../../models/transactions.model';
import { TransactionsService } from '../../services/transactions.service';

@Component({
  selector: 'app-transactions-table',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, DatePipe],
  templateUrl: './transactions-table.component.html'
})
export class TransactionsTableComponent implements OnInit {
  private transactionsService = inject(TransactionsService);

  transactions = signal<TransactionPosition[]>([]);
  isLoading = signal<boolean>(false);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.isLoading.set(true);
    this.errorMessage.set(null);

    this.transactionsService.getAllTransactions().subscribe({
      next: (data) => {
        data.sort((a, b) => b.timestamp.getTime() - a.timestamp.getTime());
        this.transactions.set(data);
        this.isLoading.set(false);
      },
      error: (err) => {
        this.errorMessage.set('Failed to load portfolio transactions...');
        this.isLoading.set(false);
        console.error(err);
      }
    });
  }

  trackByIsin(index: number, position: TransactionPosition): string {
    return position.asset.isin;
  }
}