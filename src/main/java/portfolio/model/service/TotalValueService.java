package portfolio.model.service;

import portfolio.model.domain.Portfolio;
import portfolio.model.domain.TotalValue;

import java.math.BigDecimal;
import java.util.List;

public interface TotalValueService {

    void addTotalValue(TotalValue totalValue);

    TotalValue findByPortfolioAndYear(Portfolio portfolio, String year);

    List<String> getAvailableYears(Portfolio portfolio);

    BigDecimal getTotalValueByYear(Portfolio portfolio, String year);
}
