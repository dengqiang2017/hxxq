package com.dengqiang.integerceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 参数过滤 
 * <p>过滤参数中的无效字符
 * @author 邓强
 */
public class RequestFilter extends OncePerRequestFilter {
	public String filter(HttpServletRequest request, String input) {
		String ret = input;
		// ios客户端请求参数值可能为(null)服务端过滤掉当null处理即可
		if (input == null || "(null)".equals(input.trim())||"undefined".equals(input)) {
			ret = null;
			return ret;
		}
		final String userAgent = request.getHeader("User-Agent");
		final String method = request.getMethod();
		// 该处可以实现各种业务的自定义的过滤机制
		if (method.equalsIgnoreCase("get")
		|| userAgent.toLowerCase().indexOf("android") != -1) {
		}
		if (input.contains("undefined")) {
			ret=ret.replaceAll("undefined","");
		}
		if(input.contains("null")){
			ret=ret.replaceAll("null","");
		}
		return ret;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request,
	HttpServletResponse response, FilterChain chain)
	throws ServletException, IOException {
		chain.doFilter(new HttpServletRequestWrapper(request) {
			@Override
			public String getParameter(String name) {
				String value = super.getParameter(name);
				return filter(this, value);
			}
			@Override
			public String[] getParameterValues(String name) {
				String[] values = super.getParameterValues(name);
				if (values == null) {
					return null;
				}
				for (int i = 0; i < values.length; i++) {
					values[i] = filter(this, values[i]);
				}
				return values;
			}
		}, response);
	}
}