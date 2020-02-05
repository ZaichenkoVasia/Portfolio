package portfolio.model.service.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.model.data.dto.ShareDTO;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;
import portfolio.model.domain.Stock;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.StockEntity;
import portfolio.model.repository.PortfolioRepository;
import portfolio.model.repository.StockRepository;
import portfolio.model.service.StockService;
import portfolio.model.service.exception.IncorrectInitValueRuntimeException;

import java.util.*;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShareDTOMapper {
    private StockService stockService;
    private StockMapper stockMapper;
    private PortfolioMapper portfolioMapper;
    private PortfolioRepository portfolioRepository;
    private StockRepository stockRepository;

    public Share shareDTOToShare(ShareDTO shareDTO) {
        if (Objects.isNull(shareDTO)) {
            return null;
        }
        PortfolioEntity portfolioEntity = portfolioRepository.findById(shareDTO.getIdPortfolio()).
                orElseThrow(() -> new IncorrectInitValueRuntimeException("Id portfolio of share is incorrect"));
        Portfolio portfolio = portfolioMapper.portfolioEntityToPortfolio(portfolioEntity);
        StockEntity stockEntity;
        if (stockRepository.findByIsinAndYear(shareDTO.getIsin(), shareDTO.getYear()).isPresent()) {
            stockEntity = stockRepository.findByIsinAndYear(shareDTO.getIsin(), shareDTO.getYear()).get();
        } else {
            stockEntity = findNearestStock(shareDTO);
        }
        Stock stock = stockMapper.stockEntityToStock(stockEntity);
        return Share.builder()
                .stock(stock)
                .quantity(shareDTO.getQuantity())
                .portfolio(portfolio)
                .year(shareDTO.getYear())
                .build();
    }

    private StockEntity findNearestStock(ShareDTO shareDTO) {
        List<Stock> stocks = stockService.findByIsin(shareDTO.getIsin());
        NavigableSet<Integer> years = new TreeSet<>(Comparator.comparingInt((y -> y)));
        for (Stock stock : stocks) {
            years.add(Integer.valueOf(stock.getYear()));
        }
        Integer findYear = years.lower(Integer.valueOf(shareDTO.getYear()));
        if (Objects.isNull(findYear)) {
            throw new IncorrectInitValueRuntimeException("Data of shares is incorrect");
        }
        return stockRepository.findByIsinAndYear(shareDTO.getIsin(), findYear.toString()).get();
    }
}
