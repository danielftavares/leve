package org.leve.web.ui.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/levetag-resources/*")
public class LeveTagResourcesServlet extends HttpServlet {

	public static final String RESOURCE_SERVLET_URI = "levetag-resources";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(request.getPathInfo().substring(1));
		
		if (is != null) {
			if (request.getPathInfo().endsWith(".js") || request.getPathInfo().endsWith(".css")) {
				if (request.getPathInfo().endsWith(".js")) {
					response.setContentType("text/javascript");
				}
				if (request.getPathInfo().endsWith(".css")) {
					response.setContentType("text/css");
				}
				
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(isr);
				PrintWriter writer = response.getWriter();
				String text = "";

				while ((text = reader.readLine()) != null) {
					writer.println(text);
				}
				writer.flush();
				writer.close();
				isr.close();

			} else {
				if (request.getPathInfo().endsWith(".png")) {
					response.setContentType("image/png");
				}

				BufferedInputStream bis = new BufferedInputStream(is);
				byte[] tmp = new byte[2048];
				ServletOutputStream out = response.getOutputStream();
				while (bis.read(tmp) != -1) {
					out.write(tmp);
				}
				out.flush();
				out.flush();
				bis.close();
			}
			is.close();
			setCache(response);
		}
	}

	// FIXME
	private void setCache(HttpServletResponse response) {
		final int CACHE_DURATION_IN_SECOND = 60 * 60 * 24 * 300; // 2 days
		final long   CACHE_DURATION_IN_MS = CACHE_DURATION_IN_SECOND  * 1000;
		long now = System.currentTimeMillis();
		//res being the HttpServletResponse of the request
		response.addHeader("Cache-Control", "max-age=" + CACHE_DURATION_IN_SECOND);
//		response.addHeader("Cache-Control", "must-revalidate");//optional
//		response.setDateHeader("Expires", now + CACHE_DURATION_IN_MS);
//		response.setHeader("Pragma", "private");
//		response.setHeader("Cache-Control", "max-age=" + CACHE_DURATION_IN_SECOND);
//		response.setHeader("Cache-Control", "must-revalidate");//optional
	}
}
