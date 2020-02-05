package portfolio.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.model.data.FileParser;
import portfolio.model.data.dto.ShareDTO;
import portfolio.model.data.dto.StockDTO;
import portfolio.model.service.ParserService;
import portfolio.model.service.ShareService;
import portfolio.model.service.StockService;
import portfolio.model.service.mapper.ShareDTOMapper;
import portfolio.model.service.mapper.StockDTOMapper;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ParserServiceImpl implements ParserService {
    private ShareService shareService;
    private StockService stockService;
    private ShareDTOMapper shareDTOMapper;
    private StockDTOMapper stockDTOMapper;
    private FileParser fileParser;

    public void parseStock(String stockFileName) {
        List<StockDTO> stockDTOS = fileParser.parseCSVPriceFile(stockFileName);
        for (StockDTO stockDTO : stockDTOS) {
            stockService.addStock(stockDTOMapper.stockDTOToStock(stockDTO));
        }
    }

    public void parseShare(String shareFileName) {
        List<ShareDTO> shareDTOS = fileParser.parseCSVShareFile(shareFileName);
        for (ShareDTO shareDTO : shareDTOS) {
            shareService.addShare(shareDTOMapper.shareDTOToShare(shareDTO));
        }
    }
}
