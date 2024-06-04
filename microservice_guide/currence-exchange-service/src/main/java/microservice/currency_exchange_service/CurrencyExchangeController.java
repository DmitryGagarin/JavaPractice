package microservice.currency_exchange_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CurrencyExchangeController {

    @Autowired
    public Environment environment;

    @Autowired
    public ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {
        ExchangeValue value = repository.findByFromAndTo(from, to);
        value.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port"))));
        return value;
    }
}
