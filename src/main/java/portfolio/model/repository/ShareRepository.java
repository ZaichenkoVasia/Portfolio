package portfolio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.ShareEntity;

import java.util.Optional;

@Repository
public interface ShareRepository extends JpaRepository<ShareEntity, Long> {

    Optional<ShareEntity> findByPortfolioAndYear(PortfolioEntity portfolio, String year);
}
