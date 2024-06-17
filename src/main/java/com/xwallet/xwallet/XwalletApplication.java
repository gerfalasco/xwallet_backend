package com.xwallet.xwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XwalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(XwalletApplication.class, args);
	}

}
