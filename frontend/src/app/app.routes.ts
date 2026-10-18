// app.routes.ts
import { Routes } from '@angular/router';
import { HomeComponent } from './layout/home/home.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'exchanges',
    loadChildren: () => import('./domains/exchanges/exchanges.routes').then(m => m.EXCHANGES_ROUTES)
  },
  {
    path: 'portfolio',
    loadChildren: () => import('./domains/portfolio/portfolio.routes').then(m => m.PORTFOLIO_ROUTES)
  },
  {
    path: '**',
    redirectTo: 'home'
  }
];