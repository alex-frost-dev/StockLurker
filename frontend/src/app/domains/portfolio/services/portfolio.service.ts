import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { ApiResponse } from '../../../shared/models/api-response.model';
import { PortfolioDTO } from '../dtos/portfolio.dto';
import { PortfolioMapper } from '../mappers/portfolio.mapper';
import { Portfolio } from '../models/exchange.model';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/v1/portfolio';

  getPortfolio(): Observable<Portfolio> {
    return this.http.get<ApiResponse<PortfolioDTO>>(this.apiUrl).pipe(
      map(response => {
        const dto = response.data;
        console.log('Fetched Portfolio DTO from backend:', dto);
        return PortfolioMapper.toDomain(dto);
      })
    );
  }
}