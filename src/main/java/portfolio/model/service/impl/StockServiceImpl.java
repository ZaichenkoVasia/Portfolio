package portfolio.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.model.domain.Stock;
import portfolio.model.entity.StockEntity;
import portfolio.model.repository.StockRepository;
import portfolio.model.service.StockService;
import portfolio.model.service.exception.InvalidDataRuntimeException;
import portfolio.model.service.mapper.StockMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockServiceImpl implements StockService {
    private StockMapper mapper;
    private StockRepository repository;

    @Override
    public void addStock(Stock stock) {
        if (Objects.isNull(stock)) {
            log.warn("Invalid input stock data");
            throw new InvalidDataRuntimeException("Invalid input stock data");
        }
        StockEntity entity = mapper.stockToStockEntity(stock);
        repository.save(entity);
    }

    @Override
    public Stock findByIsinAndYear(String isin, String year) {
        return mapper.stockEntityToStock(repository.findByIsinAndYear(isin, year).
                orElseThrow(() -> new InvalidDataRuntimeException("Don't find stock by this data")));
    }

    @Override
    public List<Stock> findByIsin(String isin) {
        List<StockEntity> stocks = repository.findByIsin(isin);
        return stocks.isEmpty() ? Collections.emptyList()
                : stocks.stream()
                .map(mapper::stockEntityToStock)
                .collect(Collectors.toList());
    }
}
