import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { TransactionMapper } from '../mappers/transactions.mapper';
import { ApiResponse } from '../../../shared/models/api-response.model';
import { TransactionPosition } from '../models/transactions.model';
import { AddAssetTransactionRequest, AssetTransactionDTO } from '../dtos/transactions.dto';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  private http = inject(HttpClient);
  
  private readonly apiUrl = 'http://localhost:8080/api/v1/transaction';

  getAllTransactions(): Observable<TransactionPosition[]> {
    return this.http.get<ApiResponse<AssetTransactionDTO[]>>(this.apiUrl).pipe(
      map(response => {
        const dtos = response.data ?? [];
        console.log('Fetched DTOs from backend:', dtos);
        return dtos.map(dto => TransactionMapper.toDomain(dto));
      })
    );
  }

  addNewTransaction(transactionDTO: AddAssetTransactionRequest): Observable<ApiResponse<AssetTransactionDTO[]>> {
    return this.http.post<ApiResponse<AssetTransactionDTO[]>>(this.apiUrl, transactionDTO);
  }
}