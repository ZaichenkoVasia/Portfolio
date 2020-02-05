package portfolio.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.model.domain.Portfolio;
import portfolio.model.repository.PortfolioRepository;
import portfolio.model.service.PortfolioService;
import portfolio.model.service.TotalValueService;
import portfolio.model.service.exception.InvalidDataRuntimeException;
import portfolio.model.service.mapper.PortfolioMapper;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PortfolioServiceImpl implements PortfolioService {
    private TotalValueService totalValueService;
    private PortfolioMapper mapper;
    private PortfolioRepository repository;

    @Override
    public Portfolio findById(Long id) {
        return mapper.portfolioEntityToPortfolio(repository.findById(id).
                orElseThrow(() -> new InvalidDataRuntimeException("Don't find portfolio by this data")));
    }

    @Override
    public BigDecimal differenceTotalValuesByTwoYear(Portfolio portfolio,
                                                     String firstYear, String secondYear) {
        BigDecimal firstTotalValue = totalValueService.findByPortfolioAndYear(portfolio, firstYear).getValue();
        BigDecimal secondTotalValue = totalValueService.findByPortfolioAndYear(portfolio, secondYear).getValue();
        return secondTotalValue.subtract(firstTotalValue);
    }

    @Override
    public List<String> getAvailableYears(Portfolio portfolio) {
        return totalValueService.getAvailableYears(portfolio);
    }
}
