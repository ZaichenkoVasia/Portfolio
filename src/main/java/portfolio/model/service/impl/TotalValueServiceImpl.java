package portfolio.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.TotalValue;
import portfolio.model.entity.TotalValueEntity;
import portfolio.model.repository.TotalValueRepository;
import portfolio.model.service.TotalValueService;
import portfolio.model.service.exception.InvalidDataRuntimeException;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TotalValueServiceImpl implements TotalValueService {
    private TotalValueRepository totalValueRepository;


    @Override
    public void addTotalValue(TotalValue totalValue) {
        if (Objects.isNull(totalValue)) {
            log.warn("Invalid input bus data");
            throw new InvalidDataRuntimeException("Invalid input bus data");
        }
        BusEntity entityBus = busMapper.busToBusEntity(bus);
        busRepository.save(entityBus);
    }

    @Override
    public TotalValue findByPortfolioAndYear(Portfolio portfolio, String year) {
        return null;
    }
}
