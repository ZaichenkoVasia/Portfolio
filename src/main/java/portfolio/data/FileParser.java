package portfolio.data;

import com.opencsv.CSVReader;
import portfolio.data.dto.PriceStockDTO;
import portfolio.data.dto.QuantityStockDTO;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class FileParser {
    private String priceFileName;
    private String quantityFileName;

    public FileParser(String priceFileName, String quantityFileName) {
        this.priceFileName = priceFileName;
        this.quantityFileName = quantityFileName;
    }

    public List<PriceStockDTO> parseCSVPriceFile() {
        List<PriceStockDTO> priceStockDTOs = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(priceFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
               priceStockDTOs.add(new PriceStockDTO(line[0], line[1], new BigDecimal(line[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return priceStockDTOs;
    }

    public List<QuantityStockDTO> parseCSVQuantityFile(String year) {
        List<QuantityStockDTO> quantityStockDTOs = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(quantityFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
                quantityStockDTOs.add(new QuantityStockDTO(line[0], line[1], new Double(line[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quantityStockDTOs;
    }

    public static Set<String> findAvailableYears() {
        Set<String> priceYears = new HashSet<>();
        Set<String> quantityYears = new HashSet<>();
        try {
            reader = new CSVReader(new FileReader(FILE_NAME_PRICE));
            String[] line;
            while ((line = reader.readNext()) != null) {
                priceYears.add(line[0]);
            }
            reader = new CSVReader(new FileReader(FILE_NAME_QUANTITY));
            while ((line = reader.readNext()) != null) {
                quantityYears.add(line[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> availableYears = new HashSet<>();
        for (String priceYear : priceYears) {
            if (quantityYears.contains(priceYear)) {
                availableYears.add(priceYear);
            }
        }
        return availableYears;
    }
}

