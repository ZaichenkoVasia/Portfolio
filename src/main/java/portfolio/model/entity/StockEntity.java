package portfolio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Builder
@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "isin")
    private String isin;

    @Basic(optional = false)
    @Column(name = "price_year")
    private BigDecimal price;

    @Basic(optional = false)
    @Column(name = "year")
    private String year;
}
