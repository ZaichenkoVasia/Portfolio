package portfolio.model.service.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.model.data.dto.StockDTO;
import portfolio.model.domain.Stock;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockDTOMapper {

    public Stock stockDTOToStock(StockDTO stockDTO) {

        return Objects.isNull(stockDTO) ?
                null :
                Stock.builder()
                        .year(stockDTO.getYear())
                        .isin(stockDTO.getIsin())
                        .price(stockDTO.getPrice())
                        .build();
    }
}
