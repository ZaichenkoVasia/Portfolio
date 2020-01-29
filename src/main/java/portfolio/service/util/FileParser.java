package portfolio.service.util;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileParser {
    private static CSVReader reader = null;
    private static final String FILE_NAME_PRICE = "src/main/resources/price/price.csv";
    private static final String FILE_NAME_QUANTITY = "src/main/resources/quantity/quantity.csv";

    private FileParser() {
    }

    public static Map<String, BigDecimal> parseCSVPriceFile(String year) {
        Map<String, BigDecimal> values = new HashMap<>();
        try {
            reader = new CSVReader(new FileReader(FILE_NAME_PRICE));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(year))
                    values.put(line[1], new BigDecimal(line[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static Map<String, Integer> parseCSVQuantityFile(String year) {
        Map<String, Integer> values = new HashMap<>();
        try {
            reader = new CSVReader(new FileReader(FILE_NAME_QUANTITY));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(year))
                    values.put(line[1], Integer.valueOf(line[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
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

