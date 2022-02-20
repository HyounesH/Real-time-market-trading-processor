package ma.yh.quickstart.markettradingprocessor.controller;

import lombok.extern.slf4j.Slf4j;
import ma.yh.quickstart.markettradingprocessor.dto.ChartDataDTO;
import ma.yh.quickstart.markettradingprocessor.model.TradeMarketMessage;
import ma.yh.quickstart.markettradingprocessor.service.TradeMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/market-trade-processor")
@Slf4j
@CrossOrigin("http://localhost:4200")
public class TradeMarketController {

    @Autowired
    private TradeMarketService tradeMarketService;

    @PostMapping("/register-trade-market-message")
    public ResponseEntity<String> registerMarketTradeMessage(@RequestBody TradeMarketMessage tradeMarketMessage) {
        log.info("TradeController:: new market trade message is received from user : {}", tradeMarketMessage.getUserId());
        this.tradeMarketService.save(tradeMarketMessage);
        return ResponseEntity.ok(tradeMarketMessage.getUserId());
    }
    @GetMapping("/all")
    public ResponseEntity<List<TradeMarketMessage>> all() {
        return ResponseEntity.ok(this.tradeMarketService.findAll());
    }

    @GetMapping("/trade-by-currency-from")
    public ResponseEntity<List<ChartDataDTO>> getTradesByCurrencyFrom() {
        return ResponseEntity.ok(this.tradeMarketService.tradeCountByCurrencyFrom());
    }

    @GetMapping("/trade-by-currency-to")
    public ResponseEntity<List<ChartDataDTO>> getTradesByCurrencyTo() {
        return ResponseEntity.ok(this.tradeMarketService.tradeCountByCurrencyTo());
    }
}
