package com.compare.bl;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestChecker {
	
	public static void GoGoGo() {
		RestTemplate rTemplate = new RestTemplate();
		ResponseEntity<?> response = rTemplate.getForEntity("https://www.newegg.com/p/pl?d=2070+super", String.class);
		if(response.getStatusCode() == HttpStatus.OK)
			System.out.println(new Date().toString() +  ": All good from Newegg!");
		else
			System.out.println(new Date().toString() +  ": got " + response.getStatusCode() +" from Newegg!");
		
		ResponseEntity<?> response2 = rTemplate.getForEntity("https://he.aliexpress.com/wholesale?catId=0&initiative_id=SB_20210121001051&SearchText=super+2080", String.class);
		if(response2.getStatusCode() == HttpStatus.OK)
			System.out.println(new Date().toString() +  ": All good from AliExpress!");
		else
			System.out.println(new Date().toString() +  ": got " + response2.getStatusCode() +" from AliExpress!");
	}

}
