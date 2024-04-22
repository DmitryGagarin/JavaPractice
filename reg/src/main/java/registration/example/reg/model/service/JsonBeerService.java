package registration.example.reg.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonBeerService {
    @Autowired
    RestTemplate restTemplate;

    public String getBeerApi() {
        String url = "https://api.sampleapis.com/beers/stouts";
        return restTemplate.getForObject(url, String.class);
    }
}

