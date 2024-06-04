package microservice.currency_exchange_service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exchange_value")
public class ExchangeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    @Column(name = "conversion_multiple")
    private BigDecimal conversionMultiple;
    @Column(name = "port")
    private int port;

    public ExchangeValue(Long id, String from, String to, BigDecimal conversion) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversion;
    }

}
