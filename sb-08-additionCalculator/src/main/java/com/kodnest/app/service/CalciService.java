package com.kodnest.app.service;

import org.springframework.stereotype.Service;

@Service
public class CalciService {
	
	public int calculateResult(int a, int b) {
		return a + b;
	}
	
}
