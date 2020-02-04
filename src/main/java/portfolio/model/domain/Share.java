package portfolio.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Share {
    private Long id;
    private Stock stock;
    private BigDecimal quantity;
    private String year;
    private Portfolio portfolio;
}
