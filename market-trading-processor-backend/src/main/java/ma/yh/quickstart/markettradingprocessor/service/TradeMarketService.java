package ma.yh.quickstart.markettradingprocessor.service;

import lombok.extern.slf4j.Slf4j;
import ma.yh.quickstart.markettradingprocessor.dto.ChartDataDTO;
import ma.yh.quickstart.markettradingprocessor.model.TradeMarketMessage;
import ma.yh.quickstart.markettradingprocessor.repository.TradeMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TradeMarketService {

    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private TradeMarketRepository tradeMarketRepository;


    public TradeMarketMessage save(TradeMarketMessage tradeMarketMessage) {
        TradeMarketMessage message = tradeMarketRepository.save(tradeMarketMessage);
        log.info("the message saved successfully with id : {}", message.getId());
        notifyFrontend();
        return message;
    }

    public List<TradeMarketMessage> findAll() {
        return tradeMarketRepository.findAll();
    }

    public List<ChartDataDTO> tradeCountByCurrencyFrom() {
        Map<String, Integer> map = new HashMap<>();
        for (TradeMarketMessage tradeMarketMessage : findAll()) {
            if (map.containsKey(tradeMarketMessage.getCurrencyFrom())) {
                map.put(tradeMarketMessage.getCurrencyFrom(), map.get(tradeMarketMessage.getCurrencyFrom()) + 1);
            } else {
                map.put(tradeMarketMessage.getCurrencyFrom(), 1);
            }
        }
        return mapDataToChartDataDTO(map);
    }

    public List<ChartDataDTO> tradeCountByCurrencyTo() {
        Map<String, Integer> map = new HashMap<>();
        for (TradeMarketMessage tradeMarketMessage : findAll()) {
            if (map.containsKey(tradeMarketMessage.getCurrencyTo())) {
                map.put(tradeMarketMessage.getCurrencyTo(), map.get(tradeMarketMessage.getCurrencyTo()) + 1);
            } else {
                map.put(tradeMarketMessage.getCurrencyTo(), 1);
            }
        }
        return mapDataToChartDataDTO(map);
    }

    private void notifyFrontend() {
        webSocketService.sendMessage("trade-market");
    }

    private List<ChartDataDTO> mapDataToChartDataDTO(Map<String, Integer> data) {
        List<ChartDataDTO> result = new ArrayList<>();
        for (String key : data.keySet()) {
            result.add(ChartDataDTO.of(key, data.get(key)));
        }
        return result;
    }
}
