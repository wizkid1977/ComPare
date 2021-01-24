package com.compare;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComPareApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		SpringApplication.run(ComPareApplication.class, args);
		
		// stress test on servers
//		for(int i=1; i<=60*5; i++) {
//			RequestChecker.GoGoGo();
//			Thread.sleep(1000);
//		}
		
	}

}
