import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { ApiResponse } from '../../../shared/models/api-response.model';
import { AssetDTO } from '../dtos/asset.dto';
import { AssetMapper } from '../mappers/asset.mapper';
import { Asset } from '../model/asset.model';

@Injectable({
  providedIn: 'root'
})
export class AssetsService {
  private http = inject(HttpClient);
  
  private readonly apiUrl = 'http://localhost:8080/api/v1/asset';

  getAssetOnineInfo(isin: string): Observable<Asset> {
    return this.http.get<ApiResponse<AssetDTO>>(this.apiUrl, { params: { isin } })
    .pipe(
      map(response => {
        const dto = response.data;
        console.log('Fetched DTO from backend:', dto);
        return AssetMapper.toDomain(dto);
      })
    );
  }
}