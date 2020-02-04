package portfolio.model.service;

import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;

public interface ShareService {

    void addShare(Share share);

    Share findByPortfolioAndYear(Portfolio portfolio, String year);
}
