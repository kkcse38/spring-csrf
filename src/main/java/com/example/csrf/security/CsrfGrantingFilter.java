package com.example.csrf.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;

public class CsrfGrantingFilter implements Filter {

	public static final String X_XSRF_TOKEN = "X-XSRF-TOKEN";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		CsrfToken csrf = (CsrfToken) servletRequest.getAttribute(CsrfToken.class.getName());
		String token = csrf.getToken();
		if (token != null) {
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.addHeader("XSRF-TOKEN", token);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}

}
