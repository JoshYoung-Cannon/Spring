package com.qa.spring.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class PrizeCheckerService {

	private HashMap<String, String> prizes;

	public PrizeCheckerService() {
		super();
		this.prizes = new HashMap<String, String>() {
			{
				put("A6", "Nothing");
				put("A8", "Nothing");
				put("A10", "Nothing");
				put("B6", "£50");
				put("B8", "£500");
				put("B10", "£5000");
				put("C6", "£100");
				put("C8", "£5000");
				put("C10", "£10000");
			}
		};
	}

	public String getPrizeResult(String accountNumber) {
		String checkValue = "" + accountNumber.toUpperCase().charAt(0) + accountNumber.length();
		String result = prizes.get(checkValue);
		if (result == null) {
			result = "Nothing";
		}
		return result;
	}
}
