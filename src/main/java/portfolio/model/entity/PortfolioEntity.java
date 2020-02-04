package portfolio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portfolios")
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "stock_isin", referencedColumnName = "isin")
    @ManyToOne(optional = false)
    private StockEntity stock;

    @Basic(optional = false)
    @Column(name = "quantity")
    private BigDecimal quantity;
}
