package ma.yh.quickstart.markettradingprocessor;

import lombok.SneakyThrows;
import ma.yh.quickstart.markettradingprocessor.controller.TradeMarketController;
import ma.yh.quickstart.markettradingprocessor.dto.ChartDataDTO;
import ma.yh.quickstart.markettradingprocessor.model.TradeMarketMessage;
import ma.yh.quickstart.markettradingprocessor.service.TradeMarketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = TradeMarketController.class)
public class TradeMarketControllerTests {
    @MockBean
    TradeMarketService tradeMarketService;
    @Autowired
    TradeMarketController tradeMarketController;

    String message = """
            {
                  "userId": "FAKE_USER",
                  "rate": 7,
                  "amountBuy": 15200.00,
                  "amountSell": 15000.00,
                  "currencyFrom": "EUR",
                  "currencyTo": "MAD",
                  "originatingCountry": "CH",
                  "timePlaced": "2022-02-22"
                }""";

    String response = """
            [{"id":null,"userId":"FAKE_USER","currencyFrom":"EUR","currencyTo":"MAD","amountSell":15000,"amountBuy":15000,"rate":1,"timePlaced":"24-JAN-22 10:27:44","originatingCountry":"FR"}]""";

    String freqResponse = """
            [{"label":"MAD","count":10},{"label":"EUR","count":100},{"label":"USD","count":70}]""";

    @BeforeEach
    public void setup() {
        Mockito.when(tradeMarketService.save(returnSavedTradeMessage())).thenReturn(returnSavedTradeMessage());
        Mockito.when(tradeMarketService.findAll()).thenReturn(List.of(returnSavedTradeMessage()));
        Mockito.when(tradeMarketService.findAll()).thenReturn(List.of(returnSavedTradeMessage()));
        Mockito.when(tradeMarketService.tradeCountByCurrencyFrom()).thenReturn(returnChartDtoList());
        Mockito.when(tradeMarketService.tradeCountByCurrencyTo()).thenReturn(returnChartDtoList());
    }

    @Test
    @SneakyThrows
    public void testRegisterTradeMarketMessage() {
        MockMvc build = MockMvcBuilders.standaloneSetup(tradeMarketController).build();
        MvcResult result = build.perform(MockMvcRequestBuilders.post("/v1/market-trade-processor/register-trade-market-message")
                .content(message).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(returnSavedTradeMessage().getUserId(), result.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void testFindAllTradeMarketMessage() {
        MockMvc build = MockMvcBuilders.standaloneSetup(tradeMarketController).build();
        MvcResult result = build.perform(MockMvcRequestBuilders.get("/v1/market-trade-processor/all").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(response, result.getResponse().getContentAsString());

    }

    @Test
    @SneakyThrows
    public void test_GetTradesByCurrencyFrom() {
        MockMvc build = MockMvcBuilders.standaloneSetup(tradeMarketController).build();
        MvcResult result = build.perform(MockMvcRequestBuilders.get("/v1/market-trade-processor/trade-by-currency-from").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(freqResponse, result.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void test_GetTradesByCurrencyTo() {
        MockMvc build = MockMvcBuilders.standaloneSetup(tradeMarketController).build();
        MvcResult result = build.perform(MockMvcRequestBuilders.get("/v1/market-trade-processor/trade-by-currency-to").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(freqResponse, result.getResponse().getContentAsString());
    }

    private TradeMarketMessage returnSavedTradeMessage() {
        TradeMarketMessage tradeMarketMessage = new TradeMarketMessage();
        tradeMarketMessage.setUserId("FAKE_USER");
        tradeMarketMessage.setAmountBuy(new BigDecimal(15000.00));
        tradeMarketMessage.setAmountSell(new BigDecimal(15000.00));
        tradeMarketMessage.setCurrencyTo("MAD");
        tradeMarketMessage.setCurrencyFrom("EUR");
        tradeMarketMessage.setOriginatingCountry("FR");
        tradeMarketMessage.setRate(new BigDecimal(1.00));
        tradeMarketMessage.setTimePlaced("24-JAN-22 10:27:44");
        return tradeMarketMessage;
    }

    private List<ChartDataDTO> returnChartDtoList() {
        return List.of(ChartDataDTO.of("MAD", 10), ChartDataDTO.of("EUR", 100), ChartDataDTO.of("USD", 70));
    }

}
