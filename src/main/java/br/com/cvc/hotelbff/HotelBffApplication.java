package br.com.cvc.hotelbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HotelBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBffApplication.class, args);
	}

}
