package portfolio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.model.entity.StockEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, String> {

    Optional<StockEntity> findByIsinAndYear(String isin, String year);

    List<StockEntity> findByIsin(String isin);
}
