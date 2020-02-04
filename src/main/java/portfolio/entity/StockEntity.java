package portfolio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @Basic(optional = false)
    @Column(name = "isin")
    private String isin;

    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;

    @Basic(optional = false)
    @Column(name = "quantity")
    private BigDecimal quantity;

    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PortfolioEntity portfolio;

    @Basic(optional = false)
    @Column(name = "year")
    private int year;
}
