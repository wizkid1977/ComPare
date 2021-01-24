package com.compare.services;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compare.bl.HtmlParser;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GetItemsDataService {

	@GetMapping("asos")
	public String getDataFromAsos(String keywords) {
		return HtmlParser.parseAsos(keywords);
	}
	
	@GetMapping("next")
	public String getDataFromNext(String keywords) {
		return HtmlParser.parseNext(keywords);
	}
	
}
