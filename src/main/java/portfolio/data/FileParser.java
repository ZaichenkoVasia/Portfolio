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

    private Map<Stock, Double> stockToQuantity;

    public FileParser(String priceFileName, String quantityFileName) {
        this.priceFileName = priceFileName;
        this.quantityFileName = quantityFileName;
    }

    public Map<Stock, Double> parseCSVQuantityFile(String year) {
        List<QuantityStockDTO> quantityStockDTOs = new ArrayList<>();
        stockToQuantity = new HashMap<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(quantityFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(year)) {
                    quantityStockDTOs.add(new QuantityStockDTO(line[0], line[1], new Double(line[2])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (QuantityStockDTO quantity : quantityStockDTOs) {
            if (!checkPriceExist(quantity)) {
                findOldPrice(quantity);
            }
        }
        return stockToQuantity;
    }

    private void findOldPrice(QuantityStockDTO quantity) {
        try {
            CSVReader reader = new CSVReader(new FileReader(priceFileName));
            String[] line;
            List<PriceStockDTO> stockDTOS = new ArrayList<>();
            while ((line = reader.readNext()) != null) {
                if (quantity.getIsin().equals(line[1])) {
                    stockDTOS.add(new PriceStockDTO(line[0], line[1], new BigDecimal(line[2])));
                }
            }
                choseNearestPrice(stockDTOS, quantity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void choseNearestPrice(List<PriceStockDTO> stockDTOS, QuantityStockDTO quantity) {
        NavigableSet<Integer> years = new TreeSet<>();
        for (PriceStockDTO stockDTO : stockDTOS) {
            years.add(new Integer(stockDTO.getYear()));
        }
        Integer findYear = years.lower(new Integer(quantity.getYear()));
        if (findYear == null) {
            throw new IncorrectInitValueRuntimeException(INCORRECT_INIT_VALUES);
        }
        for (PriceStockDTO stockDTO : stockDTOS) {
            if (stockDTO.getYear().equals(findYear.toString())) {
                stockToQuantity.put(new Stock(findYear.toString(), stockDTO.getIsin(), stockDTO.getPrice()), quantity.getQuantity());
            }
        }
    }

    private boolean checkPriceExist(QuantityStockDTO quantity) {
        try {
            CSVReader reader = new CSVReader(new FileReader(priceFileName));
            String[] line;

            while ((line = reader.readNext()) != null) {
                if (quantity.getIsin().equals(line[1]) && quantity.getYear().equals(line[0])) {
                    stockToQuantity.put(new Stock(line[0], line[1], new BigDecimal(line[2])), quantity.getQuantity());
                    return true;
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return false;
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

