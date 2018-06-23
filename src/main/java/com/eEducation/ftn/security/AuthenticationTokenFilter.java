package com.eEducation.ftn.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String authToken = httpRequest.getHeader("x-access-token");
		boolean loginApiCall = false;
		
		if(httpRequest.getRequestURI().contains("login") || httpRequest.getRequestURI().contains("logout")) {
			loginApiCall = true;
		}
		
		if(loginApiCall == false) {
			if(authToken == null || authToken == "") {
				// send error 401
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "missing token");
			}
		}
		
		String username = tokenUtils.getUsernameFromToken(authToken);
		
		if(username == null && loginApiCall == false) {
			// send error 401
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "missing token");
		}

		if (username != null
				&& SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService
					.loadUserByUsername(username);
			
			/*
				0 - token invalid username or password
				1 - token expired
				2 - token valid
			*/
			int tokenValidationResult = tokenUtils.validateToken(authToken, userDetails);
			
			// not login api
			if(loginApiCall == false) {
				
				if(tokenValidationResult == 0) {
					// send error 409
					httpResponse.sendError(HttpServletResponse.SC_CONFLICT, "invalid username/password");
				}
				
				else if(tokenValidationResult == 1) {
					// send error 403
					httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "token expired");
				}
				
				else if(tokenValidationResult == 2) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource()
							.buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
				}
			} else {
				
				// login api, token not required - gonna set the token
				if(tokenValidationResult == 2) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource()
							.buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
				}
				
			}
			
		}

		chain.doFilter(request, response);
	}

}