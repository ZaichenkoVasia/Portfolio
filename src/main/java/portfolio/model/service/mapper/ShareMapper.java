package portfolio.model.service.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;
import portfolio.model.domain.Stock;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.ShareEntity;
import portfolio.model.entity.StockEntity;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShareMapper {
    private PortfolioMapper portfolioMapper;
    private StockMapper stockMapper;

    public Share shareEntityToShare(ShareEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        Portfolio portfolio = portfolioMapper.portfolioEntityToPortfolio(entity.getPortfolio());
        Stock stock = stockMapper.stockEntityToStock(entity.getStock());

        return Share.builder()
                .id(entity.getId())
                .stock(stock)
                .quantity(entity.getQuantity())
                .year(entity.getYear())
                .portfolio(portfolio)
                .build();
    }

    public ShareEntity shareToShareEntity(Share domain) {
        if (Objects.isNull(domain)) {
            return null;
        }
        PortfolioEntity portfolio = portfolioMapper.portfolioToPortfolioEntity(domain.getPortfolio());
        StockEntity stock = stockMapper.stockToStockEntity(domain.getStock());

        return ShareEntity.builder()
                .id(domain.getId())
                .stock(stock)
                .quantity(domain.getQuantity())
                .year(domain.getYear())
                .portfolio(portfolio)
                .build();
    }
}
