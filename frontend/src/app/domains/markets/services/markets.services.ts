import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface MarketDTO {
  id: number;
  code: string;
  name: string;
}

export interface ApiResponse<T> {
  data: T;
  // include status, message, or other fields if your ApiResponse wrapper has them
}

@Injectable({
  providedIn: 'root'
})
export class MarketsService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/v1/market'; // Adjust route if needed

  getAllMarkets(): Observable<ApiResponse<MarketDTO[]>> {
    return this.http.get<ApiResponse<MarketDTO[]>>(this.apiUrl);
  }
}