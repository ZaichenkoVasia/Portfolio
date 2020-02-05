package portfolio.model.service;

import portfolio.model.domain.Portfolio;
import portfolio.model.domain.TotalValue;

import java.util.List;

public interface TotalValueService {

    void addTotalValue(TotalValue totalValue);

    TotalValue findByPortfolioAndYear(Portfolio portfolio, String year);

    List<String> getAvailableYears(Portfolio portfolio);

    void calculateTotalValue(Portfolio portfolio, String year);
}
