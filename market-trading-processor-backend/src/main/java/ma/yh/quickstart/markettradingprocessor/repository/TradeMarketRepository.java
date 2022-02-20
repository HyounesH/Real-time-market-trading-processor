package ma.yh.quickstart.markettradingprocessor.repository;

import ma.yh.quickstart.markettradingprocessor.model.TradeMarketMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeMarketRepository extends JpaRepository<TradeMarketMessage, String> {
}
