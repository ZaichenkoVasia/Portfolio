package portfolio.model.service;

import portfolio.model.domain.Stock;

public interface StockService {

    void addStock(Stock stock);

    Stock findByIsinAndYear(String isin, String year);
}
