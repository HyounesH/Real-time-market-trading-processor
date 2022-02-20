package ma.yh.quickstart.markettradingprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade_market")
public class TradeMarketMessage {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "currency_from")
    private String currencyFrom;
    @Column(name = "currency_to")
    private String currencyTo;
    @Column(name = "amount_sell")
    private BigDecimal amountSell;
    @Column(name = "amount_buy")
    private BigDecimal amountBuy;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "time_placed")
    private String timePlaced;
    @Column(name = "originating_country")
    private String originatingCountry;

}
