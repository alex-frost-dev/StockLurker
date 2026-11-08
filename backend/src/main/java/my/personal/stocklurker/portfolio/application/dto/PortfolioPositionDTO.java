package my.personal.stocklurker.portfolio.application.dto;

import my.personal.stocklurker.asset.application.dto.AssetDTO;

import java.util.UUID;

public class PortfolioPositionDTO {

    public UUID id;

    public AssetDTO asset;

    public float shares;

    public double marketPrice;

    public double averageBuyPrice;
}
