package ua.nure.jurkov.SummaryTask4.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/controller/*")
public class EncodingFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);
	private static final String ENCODING = "UTF-8"; 
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.debug("Filter destruction start");
		
		LOG.debug("Filter destruction finished");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(ENCODING);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter init start");
		LOG.trace("Request encoding " + ENCODING);
		LOG.debug("Filter init finished");
	}

}
