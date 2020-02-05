package portfolio.model.service;

import portfolio.model.domain.Portfolio;

import java.math.BigDecimal;
import java.util.List;

public interface PortfolioService {

    Portfolio findById(Long id);

    BigDecimal differenceTotalValuesByTwoYear(Portfolio portfolio, String firstYear, String secondYear);

    List<String> getAvailableYears(Portfolio portfolio);
}
