package portfolio.model.service;

import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;

import java.util.List;

public interface ShareService {

    void addShare(Share share);

    List<Share> findByPortfolioAndYear(Portfolio portfolio, String year);

    List<Share> findByPortfolio(Portfolio portfolio);
}
