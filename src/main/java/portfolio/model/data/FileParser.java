package portfolio.model.data;

import com.opencsv.CSVReader;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.model.data.dto.StockDTO;
import portfolio.model.data.dto.ShareDTO;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
public class FileParser {

    public List<ShareDTO> parseCSVShareFile(String shareFileName) {
        List<ShareDTO> shareDTOS = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(shareFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
                shareDTOS.add(ShareDTO.builder()
                        .year(line[0])
                        .isin(line[1])
                        .quantity(new BigDecimal(line[2]))
                        .IdPortfolio(Long.valueOf(line[3]))
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shareDTOS;
    }

    public List<StockDTO> parseCSVPriceFile(String priceFileName) {
        List<StockDTO> stockDTOS = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(priceFileName));
            String[] line;
            while ((line = reader.readNext()) != null) {
                stockDTOS.add(StockDTO.builder()
                        .year(line[0])
                        .isin(line[1])
                        .price(new BigDecimal(line[2]))
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockDTOS;
    }
}
