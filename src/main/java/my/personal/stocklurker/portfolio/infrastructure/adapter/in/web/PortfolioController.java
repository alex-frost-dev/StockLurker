package my.personal.stocklurker.portfolio.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import my.personal.stocklurker.portfolio.application.dto.PortfolioDTO;
import my.personal.stocklurker.portfolio.application.dto.usecase.GetAllPortfolioUseCase;
import my.personal.stocklurker.portfolio.application.mapper.PortfolioMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping("/api/v1/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final GetAllPortfolioUseCase getAllPortfolioUseCase;
    private final PortfolioMapper portfolioMapper;

    @GetMapping
    public ApiResponse<PortfolioDTO> getPortfolio() {
        return handleResponse(portfolioMapper.toDto(getAllPortfolioUseCase.execute()));
    }

}
