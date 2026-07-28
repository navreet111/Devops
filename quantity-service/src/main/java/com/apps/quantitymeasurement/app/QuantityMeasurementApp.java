package com.apps.quantitymeasurement.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = "com.apps.quantitymeasurement")
@EnableJpaRepositories(basePackages = "com.apps.quantitymeasurement.repository")
@EntityScan(basePackages = "com.apps.quantitymeasurement.entity")
public class QuantityMeasurementApp {

    public static void main(String[] args) {
        SpringApplication.run(QuantityMeasurementApp.class, args);
    }
}
