package org.dishes.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dishes.commons.ConstantsValue;
import org.dishes.facade.dto.SessionUser;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * 登陆过滤器
 */
public class SessionFilter extends OncePerRequestFilter{
	private List<String> publicResources = new ArrayList<String>();
	public SessionFilter() {
		publicResources.add("/ntAdmin/index.html");
		publicResources.add("/index.html");
		publicResources.add("/user/login");
		publicResources.add("/user/logout");
		publicResources.add("/index/getCheckImg");
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		String requestPath = request.getRequestURI();
		// 公共资源
		if(publicResources.contains(requestPath) || (requestPath.contains(".") && !requestPath.endsWith(".html"))){
			filter.doFilter(request, response);
			return ;
		}
		HttpSession session = request.getSession();
		SessionUser user = (SessionUser) session.getAttribute(ConstantsValue.USER_SESSION_NAME);
		if(user == null){
			response.sendRedirect("index.html");
			return ;
		}
		filter.doFilter(request, response);
	}
	public static void main(String[] args) {
		List<String> aaa = new ArrayList<String>();
		aaa.add("/asdf/ss/dd");
		System.out.println(aaa.contains("/asdf/ss/dd"));
	}
}
