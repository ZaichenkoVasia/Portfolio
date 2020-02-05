package portfolio.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.TotalValue;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.TotalValueEntity;
import portfolio.model.repository.TotalValueRepository;
import portfolio.model.service.TotalValueService;
import portfolio.model.service.exception.InvalidDataRuntimeException;
import portfolio.model.service.mapper.PortfolioMapper;
import portfolio.model.service.mapper.TotalValueMapper;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TotalValueServiceImpl implements TotalValueService {
    private TotalValueRepository totalValueRepository;
    private TotalValueMapper mapper;
    private PortfolioMapper portfolioMapper;

    @Override
    public void addTotalValue(TotalValue totalValue) {
        if (Objects.isNull(totalValue)) {
            log.warn("Invalid input bus data");
            throw new InvalidDataRuntimeException("Invalid input bus data");
        }
        TotalValueEntity entity = mapper.totalValueToTotalValueEntity(totalValue);
        totalValueRepository.save(entity);
    }

    @Override
    public TotalValue findByPortfolioAndYear(Portfolio portfolio, String year) {
        PortfolioEntity portfolioEntity = portfolioMapper.portfolioToPortfolioEntity(portfolio);
        return mapper.totalValueEntityToTotalValue(totalValueRepository.findByPortfolioAndYear(portfolioEntity, year).
                orElseThrow(() -> new InvalidDataRuntimeException("Don't find user by this id")));
    }
}
