package com.onlinestore.orderservice.contextinfoutils;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Custom HTTP ServletFilter. Maps HTTP header values to {@link UserContext} class.
 */
@Component
public class UserContextFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * Retrieves values from HTTP request header into {@codeUserContext}.
	 *
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
		UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
		UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
		UserContextHolder.getContext().setCustomerId(httpServletRequest.getHeader(UserContext.CUSTOMER_ID));

		filterChain.doFilter(httpServletRequest, response);
	}

	@Override
	public void destroy() {

	}
}
