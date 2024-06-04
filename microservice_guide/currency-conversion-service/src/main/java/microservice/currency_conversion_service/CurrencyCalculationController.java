package microservice.currency_conversion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyCalculationController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency_calculation_feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyCalculation convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyCalculation response = proxy.retrieveExchangeValue(from, to);
        return new CurrencyCalculation(
                response.getId(),
                from,
                to,
                response.getConversionMultiple(),
                quantity,
                quantity.multiply(response.getConversionMultiple()),
                response.getPort());
    }
//    @GetMapping("/currency_calculation_feign/from/{from}/to/{to}/quantity/{quantity}")
//    public String convertCurrency(
//            @PathVariable String from,
//            @PathVariable String to,
//            @PathVariable BigDecimal quantity,
//            Model model
//    ) {
//        CurrencyCalculation response = proxy.retrieveExchangeValue(from, to);
//        CurrencyCalculation calculations = new CurrencyCalculation(
//                response.getId(), // Long
//                from, // String
//                to, // String
//                response.getConversionMultiple(), // Big Decimal
//                quantity,  // Big Decimal
//                quantity.multiply(response.getConversionMultiple()),  // Big Decimal
//                response.getPort()); // int
//        model.addAttribute("calculations", calculations);
//        return "currency";
//    }
}
