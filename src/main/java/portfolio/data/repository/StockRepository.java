package portfolio.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.data.entity.StockEntity;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, String> {

    Optional<StockEntity> findByIsinAndYear(String isin, String year);
}
