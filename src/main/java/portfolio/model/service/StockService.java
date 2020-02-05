package portfolio.model.service;

import portfolio.model.domain.Stock;
import portfolio.model.entity.StockEntity;

import java.util.List;

public interface StockService {

    void addStock(Stock stock);

    Stock findByIsinAndYear(String isin, String year);

    List<Stock> findByIsin(String isin);
}
