package microservice.currency_conversion_service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyCalculation {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalAmount;
    private int port;

    public CurrencyCalculation(Long id,
                               String from,
                               String to,
                               BigDecimal conversionMultiple,
                               BigDecimal quantity,
                               BigDecimal totalAmount,
                               int port) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.port = port;
    }
}
