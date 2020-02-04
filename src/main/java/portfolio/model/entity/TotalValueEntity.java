package portfolio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "total_values")
public class TotalValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "value")
    private BigDecimal value;

    @Basic(optional = false)
    @Column(name = "year")
    private String year;

    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PortfolioEntity portfolio;
}
