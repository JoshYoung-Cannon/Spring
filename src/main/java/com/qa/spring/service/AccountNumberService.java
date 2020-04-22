package com.qa.spring.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class AccountNumberService {
	
	public AccountNumberService() {
		super();
	}
	
	public String generateNumber(int numLength) {
		Random rnd = new Random();
		String number = "" + (char) (rnd.nextInt(3) + 'a');
		for (int i = 0; i < numLength - 1; i++) {
			rnd = new Random();
			number += rnd.nextInt(9);
		}
		return number;
	}
}
