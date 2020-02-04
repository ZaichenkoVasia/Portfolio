package portfolio.model.service.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.model.domain.Portfolio;
import portfolio.model.entity.PortfolioEntity;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PortfolioMapper {

    public Portfolio portfolioEntityToPortfolio(PortfolioEntity entity) {

        return Objects.isNull(entity) ?
                null :
                Portfolio.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .build();
    }

    public PortfolioEntity portfolioToPortfolioEntity(Portfolio domain) {

        return Objects.isNull(domain) ?
                null :
                PortfolioEntity.builder()
                        .id(domain.getId())
                        .name(domain.getName())
                        .build();
    }
}
