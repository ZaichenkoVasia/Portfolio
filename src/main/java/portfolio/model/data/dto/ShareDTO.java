package portfolio.model.data.dto;

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
public class ShareDTO {
    private String year;
    private String isin;
    private BigDecimal quantity;
    private Long IdPortfolio;
}
