package portfolio.model.service;

import portfolio.model.domain.Portfolio;

import java.math.BigDecimal;
import java.util.List;

public interface PortfolioService {

    void addPortfolio(Portfolio portfolio);

    Portfolio findById(Long id);

    BigDecimal differenceTotalValuesByTwoYear(Portfolio portfolio, String firstYear, String secondYear);

    List<String> getAvailableYears(Portfolio portfolio);

    BigDecimal getTotalValueByYear(Portfolio portfolio, String year);
}
