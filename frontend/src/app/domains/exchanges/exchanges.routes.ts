import { Routes } from '@angular/router';
import { ExchangesTableComponent } from './components/portfolio-table/exchanges-table.component';
import { AddExchangeComponent } from './components/add-exchange/add-exchange.component';

export const EXCHANGES_ROUTES: Routes = [
  {
    path: '',
    component: ExchangesTableComponent
  },
  {
    path: 'add-exchange',
    component: AddExchangeComponent
  }
];