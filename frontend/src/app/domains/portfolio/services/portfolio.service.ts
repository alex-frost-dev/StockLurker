import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { ApiResponse } from '../../../shared/models/api-response.model';
import { PortfolioPositionSummaryDTO } from '../dtos/portfolio.dto';
import { PortfolioMapper } from '../mappers/portfolio.mapper';
import { Portfolio } from '../models/portfolio.model';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/v1/portfolio';

  getPortfolio(marketCode: string): Observable<Portfolio> {
    const params = new HttpParams().set('marketCode', marketCode);
    return this.http.get<ApiResponse<PortfolioPositionSummaryDTO[]>>(this.apiUrl, { params }).pipe(
      map(response => {
        const dto = response.data;
        console.log('Fetched Portfolio DTO from backend:', dto);
        var portfolio: Portfolio = { portfolioPositionSummary: [] };
        for (let i = 0; i < dto.length; i++) {
          portfolio.portfolioPositionSummary.push(PortfolioMapper.toPortfolioPositionSummaryDomain(dto[i]));
        }
        return portfolio;
      })
    );
  }
}