// src/app/domains/portfolio/components/portfolio-table/portfolio-table.component.ts
import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { TransactionPosition } from '../../models/transactions.model';
import { TransactionsService } from '../../services/transactions.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-transactions-table',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, DatePipe, RouterLink],
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
        console.log('Loaded transactions:', data)
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

  onEdit(item: any, event: MouseEvent): void {
    event.stopPropagation(); // Evita navegar a la ruta del asset al hacer clic en editar
    console.log('Edit position:', item);
    // TODO: Implementar lógica de edición, llevar a pantalla de assetPosition-edit con el id del assetPosition
  }

  onDelete(item: any, event: MouseEvent): void {
    event.stopPropagation(); // Evita navegar a la ruta del asset al hacer clic en eliminar
    console.log('Delete position:', item);
    this.transactionsService.deleteTransaction(item.id).subscribe({
      next: (response) => {
        console.log('Transaction deleted successfully:', response);
        this.loadTransactions();
      },
      error: (err) => {
        console.error('Failed to delete transaction:', err);
        this.errorMessage.set('Failed to delete transaction...');
      }
    });
  }
}