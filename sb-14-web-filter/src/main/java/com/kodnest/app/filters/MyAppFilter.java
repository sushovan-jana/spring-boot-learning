package com.kodnest.app.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class MyAppFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		1. Downcast ServletRequest reuest object to HttpServletRequest to access end-point URI
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		if (uri.equals("/api/")) {
//			Forwarding the request to destination end point
			System.out.println("Forwarding request without validation.");
			chain.doFilter(request, response);
		} else {
			req.setAttribute("message", "VALIDATION SUCCESS.");
			System.out.println("FORWARDING REQUEST AFTER VALIDATION.");
			chain.doFilter(request, response);
		}
		
	}

}
