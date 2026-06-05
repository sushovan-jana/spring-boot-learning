package com.kodnest.app.service;

import org.springframework.stereotype.Service;

@Service
public class RequestService {
	public String serviceMessage(int count) {
		return "Hello user " + count + " Welcome to KodNest Academy.";
	}
}
