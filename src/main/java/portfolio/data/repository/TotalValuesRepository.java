package portfolio.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.data.entity.PortfolioEntity;
import portfolio.data.entity.TotalValueEntity;

import java.util.Optional;

@Repository
public interface TotalValuesRepository extends JpaRepository<TotalValueEntity, Long> {

    Optional<TotalValueEntity> findByPortfolioAndYear(PortfolioEntity portfolio, String year);
}
