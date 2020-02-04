package portfolio.model.service.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Stock;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.StockEntity;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockMapper {

    public Stock stockEntityToStock(StockEntity entity) {

        return Objects.isNull(entity) ?
                null :
                Stock.builder()
                        .id(entity.getId())
                        .year(entity.getYear())
                        .value(entity.getValue())
                        .portfolio(portfolio)
                        .build();
    }

    public StockEntity stockToStockEntity(Stock domain) {

        return Objects.isNull(domain) ?
                null :
                StockEntity.builder()
                        .id(domain.getId())
                        .name(domain.getName())
                        .build();
    }
}