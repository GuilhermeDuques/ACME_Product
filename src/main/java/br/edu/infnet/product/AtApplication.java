package br.edu.infnet.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class AtApplication {

    public static void main(String[] args) {
            SpringApplication.run(AtApplication.class, args);
    }
}
