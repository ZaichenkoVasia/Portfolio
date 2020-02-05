package portfolio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.TotalValueEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TotalValueRepository extends JpaRepository<TotalValueEntity, Long> {

    Optional<TotalValueEntity> findByPortfolioAndYear(PortfolioEntity portfolio, String year);

    List<TotalValueEntity> findAllByPortfolio(PortfolioEntity portfolio);
}
