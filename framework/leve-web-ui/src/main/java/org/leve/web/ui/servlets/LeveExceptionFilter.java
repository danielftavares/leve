package org.leve.web.ui.servlets;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.codehaus.jackson.map.ObjectMapper;
import org.leve.bundles.ResourceBundleFactory;
import org.leve.exception.LeveException;

@WebFilter("/leve/*")
public class LeveExceptionFilter implements Filter {

	@Inject
	private ResourceBundleFactory bundleFactory;

	ObjectMapper objectMapper = null;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			filterChain.doFilter(request, response);
		} catch (Exception exc) {
			Throwable workException = exc;
			do {
				if (workException instanceof LeveException) {
					exc = (Exception) workException;
					((LeveException) exc).setResourceBundle(getResourceBundle(request));
					break;
				}if (workException instanceof PersistenceException){
					Pattern pattern = Pattern.compile("constraint \"(.*?)\"");
			    	Matcher matcher = pattern.matcher(workException.getMessage());
			    	if (matcher.find()) {
			    		exc = new LeveException(matcher.group(1));
			    		((LeveException)exc).setResourceBundle(getResourceBundle(request));
			    		break;
			    	}
				}
				workException = workException.getCause();
			} while (workException != null);

			objectMapper.writeValue(response.getOutputStream(), exc);
		}
	}

	private ResourceBundle getResourceBundle(ServletRequest request) {
		return bundleFactory
				.getResourceBundle(request.getLocale());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		objectMapper = new ObjectMapper();
	}

}
