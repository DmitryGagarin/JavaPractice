package microservice.example.microservice.controllers;

import microservice.example.microservice.MicroserviceConfiguration;
import microservice.example.microservice.configutations.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroserviceConfigurationController {

    @Autowired
    private Config config;

    @GetMapping("/limits")
    public MicroserviceConfiguration retriveLimitsFromConfigurations(){
        return new MicroserviceConfiguration(config.getMaximum(), config.getMinimum());
    }

}
