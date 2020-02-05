package portfolio.model.service;

import portfolio.model.domain.Stock;

import java.util.Optional;

public interface StockService {

    void addStock(Stock stock);

    Stock findByIsinAndYear(String isin, String year);
}
