import { AssetDTO } from '../dtos/asset.dto';
import { Asset } from '../model/asset.model';

export class AssetMapper {
  
  static toDomain(dto: AssetDTO): Asset {
    return {
      name: dto.name,
      description: dto.description ?? '',
      isin: dto.isin
    };
  }

  static toDto(position: Asset): AssetDTO {
    return {
      name: position.name,
      description: position.description,
      isin: position.isin
    };
  }
}