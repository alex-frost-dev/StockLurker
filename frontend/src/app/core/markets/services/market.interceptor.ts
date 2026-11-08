import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { MarketService } from './market.service';

export const marketInterceptor: HttpInterceptorFn = (req, next) => {
  const marketService = inject(MarketService);
  const market = marketService.currentMarket();

  const authReq = req.clone({
    setHeaders: { marketCode: market }
  });

  return next(authReq);
};