package portfolio.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Builder
public class Stock {
    private Long id;
    private String year;
    private String isin;
    private BigDecimal price;
}
