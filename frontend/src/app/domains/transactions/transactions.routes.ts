import { Routes } from '@angular/router';
import { AddTransactionComponent } from './components/add-transaction/add-transaction.component';
import { TransactionsTableComponent } from './components/transactions-table/transactions-table.component';

export const TRANSACTIONS_ROUTES: Routes = [
  {
    path: '',
    component: TransactionsTableComponent
  },
  {
    path: 'add-transaction',
    component: AddTransactionComponent
  }
];