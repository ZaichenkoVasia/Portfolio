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
@Table(name = "shares")
public class ShareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StockEntity stock;

    @Basic(optional = false)
    @Column(name = "quantity_year")
    private BigDecimal quantity;

    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PortfolioEntity portfolio;

    @Basic(optional = false)
    @Column(name = "year")
    private String year;
}
