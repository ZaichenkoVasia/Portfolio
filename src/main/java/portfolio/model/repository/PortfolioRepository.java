package portfolio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.model.entity.PortfolioEntity;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {
}
