package portfolio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.TotalValueEntity;

import java.util.Optional;

public interface TotalValuesRepository extends JpaRepository<TotalValueEntity, Long> {

    Optional<TotalValueEntity> findByPortfolioAndYear(PortfolioEntity portfolio, String year);
}
