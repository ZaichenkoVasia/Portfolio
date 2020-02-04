package portfolio.model.service;

import portfolio.model.domain.Portfolio;
import portfolio.model.domain.TotalValue;

public interface TotalValueService {

    void addTotalValue(TotalValue totalValue);

    TotalValue findByPortfolioAndYear(Portfolio portfolio, String year);
}
