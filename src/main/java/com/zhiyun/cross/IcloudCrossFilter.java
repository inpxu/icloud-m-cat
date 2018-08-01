package com.zhiyun.cross;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class IcloudCrossFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if ("OPTIONS".equals(request.getMethod())) {
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Origin",
					request.getHeader("Origin") == null ? "*" : request.getHeader("Origin"));
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Max-Age", "1800");// 30 min
			response.addHeader("Access-Control-Allow-Credentials", "true");
		}
		filterChain.doFilter(request, response);
	}
}
