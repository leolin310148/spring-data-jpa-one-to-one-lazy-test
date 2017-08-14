package me.leolin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringDataJpaOneToOneLazyTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaOneToOneLazyTestApplication.class, args);
	}
}
