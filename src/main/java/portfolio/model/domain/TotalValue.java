package portfolio.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.model.entity.PortfolioEntity;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalValue {
    private Long id;
    private BigDecimal value;
    private String year;
    private PortfolioEntity portfolio;
}
