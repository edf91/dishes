package org.dishes.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dishes.commons.ConstantsValue;
import org.dishes.facade.dto.UserDTO;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 登陆过滤器
 */
public class SessionFilter extends OncePerRequestFilter{
	public SessionFilter() {
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		dto.setId("yyyy-mmm-ddd12355");
		dto.setName("nn");
		dto.setUserAccount("110802031000");
		dto.setPassword("123");
		
		request.getSession().setAttribute(ConstantsValue.USER_SESSION_NAME, dto);
		String requestPath = request.getRequestURI();
		System.out.println(requestPath);
		filter.doFilter(request, response);
	}

}
