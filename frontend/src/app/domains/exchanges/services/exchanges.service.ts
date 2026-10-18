// src/app/domains/portfolio/services/portfolio.service.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { ExchangeMapper } from '../mappers/exchange.mapper';
import { ApiResponse } from '../../../shared/models/api-response.model';
import { AddAssetExchangeRequest, AssetExchangeDTO } from '../dtos/exchange.dto';
import { ExchangePosition } from '../models/exchange.model';

@Injectable({
  providedIn: 'root'
})
export class ExchangesService {
  private http = inject(HttpClient);
  
  // Spring Boot backend endpoint
  private readonly apiUrl = 'http://localhost:8080/api/v1/exchange';

  getAllExchanges(): Observable<ExchangePosition[]> {
    return this.http.get<ApiResponse<AssetExchangeDTO[]>>(this.apiUrl).pipe(
      map(response => {
        const dtos = response.data ?? [];
        console.log('Fetched DTOs from backend:', dtos);
        return dtos.map(dto => ExchangeMapper.toDomain(dto));
      })
    );
  }

  addNewExchange(exchangeDTO: AddAssetExchangeRequest): Observable<ApiResponse<AssetExchangeDTO[]>> {
    return this.http.post<ApiResponse<AssetExchangeDTO[]>>(this.apiUrl, exchangeDTO);
  }
}