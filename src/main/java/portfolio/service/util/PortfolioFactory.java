package portfolio.service.util;

import portfolio.data.FileParser;
import portfolio.data.dto.PriceStockDTO;
import portfolio.data.dto.QuantityStockDTO;
import portfolio.service.domain.Portfolio;
import portfolio.service.domain.Stock;
import portfolio.service.exception.IncorrectInitValueRuntimeException;

import java.math.BigDecimal;
import java.util.*;

public final class PortfolioFactory {
    private static final String INCORRECT_INIT_VALUES = "Init values of price or quantity are incorrect";
    private Map<Stock, Double> stockToQuantity = new HashMap<>();
    private FileParser fileParser;

    public PortfolioFactory(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public Portfolio getPortfolio(String year) {
        List<PriceStockDTO> priceStockDTOs = fileParser.parseCSVPriceFile();
        List<QuantityStockDTO> quantityStockDTOs = fileParser.parseCSVQuantityFile(year);
        if (priceStockDTOs == null || quantityStockDTOs == null) {
            throw new IncorrectInitValueRuntimeException(INCORRECT_INIT_VALUES);
        }

        for (int i = 0; i < quantityStockDTOs.size(); i++) {
            for (int j = 0; j < priceStockDTOs.size(); j++) {
                String isinQuantity = quantityStockDTOs.get(i).getIsin();
                String isinPrice = priceStockDTOs.get(j).getIsin();
                String yearQuantity = quantityStockDTOs.get(i).getYear();
                String yearPrice = priceStockDTOs.get(j).getYear();
                Double quantity = quantityStockDTOs.get(i).getQuantity();
                BigDecimal price = priceStockDTOs.get(j).getPrice();
                if(isinQuantity.equals(isinPrice) && yearQuantity.equals(yearPrice)){
                    Stock newStock = new Stock(yearQuantity, isinQuantity, price);
                    if(stockToQuantity.containsKey(newStock)){
                        for (Stock stock : stockToQuantity.keySet()) {
                            if(stock.getIsin().equals(isinQuantity) && stock.getYear().equals(yearQuantity)){
                                double oldQuantity = stockToQuantity.get(stock);
                                stockToQuantity.put(stock, oldQuantity+quantity);
                            }
                        }
                    }
                    stockToQuantity.put(newStock, quantity);
                }
                else{
                    List<PriceStockDTO> simplePriceStockDiffYear = new ArrayList<>();
                    for (PriceStockDTO priceStockDTO : priceStockDTOs) {
                        if(priceStockDTO.getIsin().equals(isinQuantity)){
                            simplePriceStockDiffYear.add(priceStockDTO);
                        }
                    }
                    NavigableSet<Integer> years = new TreeSet<>();
                    for (PriceStockDTO simplePriceStockDTO : simplePriceStockDiffYear) {
                         years.add(new Integer(simplePriceStockDTO.getYear()));
                    }
                    Integer findYear = years.lower(new Integer(yearQuantity));
                    if (findYear == null) {
                        //throw exception
                    }
                    for (PriceStockDTO stockDTO : simplePriceStockDiffYear) {
                            if(stockDTO.getYear().equals(findYear.toString())){
                                stockToQuantity.put(new Stock(findYear.toString(), isinQuantity, stockDTO.getPrice()), quantity);
                        }
                    }
                }
            }
        }
        return new Portfolio(stockToQuantity, year);
    }

    private void checkSimilarQuantities() {

    }
}
