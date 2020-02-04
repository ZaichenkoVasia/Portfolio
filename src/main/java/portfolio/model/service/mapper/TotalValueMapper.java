package portfolio.model.service.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.TotalValue;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.TotalValueEntity;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TotalValueMapper {
    private PortfolioMapper portfolioMapper;

    public TotalValue totalValueEntityToTotalValue(TotalValueEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        Portfolio portfolio = portfolioMapper.portfolioEntityToPortfolio(entity.getPortfolio());

        return TotalValue.builder()
                .id(entity.getId())
                .value(entity.getValue())
                .year(entity.getYear())
                .portfolio(portfolio)
                .build();
    }

    public TotalValueEntity totalValueToTotalValueEntity(TotalValue domain) {
        if (Objects.isNull(domain)) {
            return null;
        }
        PortfolioEntity portfolio = portfolioMapper.portfolioToPortfolioEntity(domain.getPortfolio());

        return TotalValueEntity.builder()
                .id(domain.getId())
                .value(domain.getValue())
                .year(domain.getYear())
                .portfolio(portfolio)
                .build();
    }
}
