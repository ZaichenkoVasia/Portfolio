package portfolio.data;

import com.opencsv.CSVReader;
import portfolio.data.dto.PriceStockDTO;
import portfolio.data.dto.QuantityStockDTO;
import portfolio.service.domain.Stock;
import portfolio.service.exception.IncorrectInitValueRuntimeException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class FileParser {
    private static final String INCORRECT_INIT_VALUES = "Init values of price or quantity are incorrect";
    private String priceFileName;
    private String quantityFileName;

    public FileParser(String priceFileName, String quantityFileName) {
        this.priceFileName = priceFileName;
        this.quantityFileName = quantityFileName;
    }

    private Map<String, PriceStockDTO> parseCSVPriceFileByIsin(String isin) {
        Map<String, PriceStockDTO> yearToStock = new HashMap<>();;
        try {
            CSVReader reader = new CSVReader(new FileReader(priceFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[1].equals(isin)) {
                    yearToStock.put(line[0], new PriceStockDTO(line[0], line[1], new BigDecimal(line[2])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return yearToStock;
    }

    public Map<Stock, BigDecimal> parseCSVQuantityFile(String year, long id) {
        Map<Stock, BigDecimal> stockToQuantity = new HashMap<>();;
        List<QuantityStockDTO> quantityStockDTOs = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(quantityFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(year) && new Long(line[3]).equals(id)) {
                    quantityStockDTOs.add(new QuantityStockDTO(line[0], line[1], new BigDecimal(line[2])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (QuantityStockDTO quantity : quantityStockDTOs) {
            Map<String, PriceStockDTO> yearToPriceStock = parseCSVPriceFileByIsin(quantity.getIsin());
            PriceStockDTO stock = yearToPriceStock.containsKey(quantity.getYear())
                    ? yearToPriceStock.get(quantity.getYear())
                    : getNearestPriceStock(yearToPriceStock, quantity);
            addQuantityToStock(stockToQuantity, quantity, stock);
        }
        return stockToQuantity;
    }

    private PriceStockDTO getNearestPriceStock(Map<String, PriceStockDTO> stockDTOS, QuantityStockDTO quantity) {
        NavigableSet<Integer> years = new TreeSet<>();
        for (String year : stockDTOS.keySet()) {
            years.add(new Integer(year));
        }
        Integer findYear = years.lower(new Integer(quantity.getYear()));
        if (findYear == null) {
            throw new IncorrectInitValueRuntimeException(INCORRECT_INIT_VALUES);
        }
        return stockDTOS.get(findYear.toString());
    }

    private void addQuantityToStock(Map<Stock, BigDecimal> stockToQuantity, QuantityStockDTO quantity, PriceStockDTO priceStockDTO) {
        Stock stock = new Stock(priceStockDTO.getYear(), priceStockDTO.getIsin(), priceStockDTO.getPrice());
        if (!stockToQuantity.containsKey(stock)) {
            stockToQuantity.put(stock, quantity.getQuantity());
        } else {
            BigDecimal oldQuantity = stockToQuantity.get(stock);
            stockToQuantity.put(stock, oldQuantity.add(quantity.getQuantity()));
        }
    }

    public Set<String> findAvailableYears() {
        Set<String> availableYears = new HashSet<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(quantityFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
                availableYears.add(line[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return availableYears;
    }
}

