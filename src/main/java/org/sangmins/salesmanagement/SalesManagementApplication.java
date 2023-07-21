package org.sangmins.salesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SalesManagementApplication {
    public static void main(String[] args) {
        // TODO: USER domain 구현
        SpringApplication.run(SalesManagementApplication.class, args);
    }
}
