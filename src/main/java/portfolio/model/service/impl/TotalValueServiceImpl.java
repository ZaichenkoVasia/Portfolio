package portfolio.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;
import portfolio.model.domain.TotalValue;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.TotalValueEntity;
import portfolio.model.repository.TotalValueRepository;
import portfolio.model.service.ShareService;
import portfolio.model.service.TotalValueService;
import portfolio.model.service.exception.InvalidDataRuntimeException;
import portfolio.model.service.mapper.PortfolioMapper;
import portfolio.model.service.mapper.TotalValueMapper;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TotalValueServiceImpl implements TotalValueService {
    private ShareService shareService;
    private TotalValueMapper mapper;
    private PortfolioMapper portfolioMapper;
    private TotalValueRepository repository;

    @Override
    public void addTotalValue(TotalValue totalValue) {
        if (Objects.isNull(totalValue)) {
            log.warn("Invalid input total value data");
            throw new InvalidDataRuntimeException("Invalid input total value data");
        }
        TotalValueEntity entity = mapper.totalValueToTotalValueEntity(totalValue);
        repository.save(entity);
    }

    @Override
    public TotalValue findByPortfolioAndYear(Portfolio portfolio, String year) {
        PortfolioEntity portfolioEntity = portfolioMapper.portfolioToPortfolioEntity(portfolio);
        return mapper.totalValueEntityToTotalValue(repository.findByPortfolioAndYear(portfolioEntity, year).
                orElseThrow(() -> new InvalidDataRuntimeException("Don't find total value by this data")));
    }

    @Override
    public List<String> getAvailableYears(Portfolio portfolio) {
        List<Share> shares = shareService.findByPortfolio(portfolio);
        Set<Integer> availableYearsSet = new TreeSet<>(Comparator.comparingInt(y -> y));
        for (Share share : shares) {
            availableYearsSet.add(Integer.valueOf(share.getYear()));
        }
        List<String> availableYearsList = new ArrayList<>();
        for (Integer availableYear : availableYearsSet) {
            availableYearsList.add(availableYear.toString());
        }
        return availableYearsList;
    }

    @Override
    public BigDecimal getTotalValueByYear(Portfolio portfolio, String year) {
        PortfolioEntity portfolioEntity = portfolioMapper.portfolioToPortfolioEntity(portfolio);
        BigDecimal totalValue = BigDecimal.ZERO;
        List<Share> shares = shareService.findByPortfolioAndYear(portfolio, year);
        for (Share share : shares) {
            BigDecimal stockPrice = share.getStock().getPrice();
            BigDecimal portfolioStockPrice = stockPrice.multiply(share.getQuantity());
            totalValue = totalValue.add(portfolioStockPrice);
        }
        if (repository.findByPortfolioAndYear(portfolioEntity, year).isPresent()) {
            TotalValueEntity totalValueEntity = repository.findByPortfolioAndYear(portfolioEntity, year).get();
            totalValueEntity.setValue(totalValue);
            repository.save(totalValueEntity);
        } else {
            repository.save(TotalValueEntity.builder()
                    .portfolio(portfolioEntity)
                    .value(totalValue)
                    .year(year)
                    .build());
        }
        return totalValue;
    }
}
