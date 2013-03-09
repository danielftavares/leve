package org.leve.web.ui.servlets;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/levetag-resources/*")
public class LeveTagResourcesServlet extends HttpServlet {

	private static final Calendar startDate = Calendar.getInstance();
	private int maxAge = 31556926;
	private long milliseconds = 1000L;
	
	private static final Map<String, String> mimeTypes = new HashMap<String, String>();
	{
		mimeTypes.put("css", "text/css");
		mimeTypes.put("less", "text/css");
		mimeTypes.put("html", "text/html");
		mimeTypes.put("htm", "text/html");
		mimeTypes.put("js", "text/javascript");
		mimeTypes.put("txt", "text/plain");
		mimeTypes.put("xml", "text/xml");
		mimeTypes.put("gif", "image/gif");
		mimeTypes.put("ico", "image/x-icon");
		mimeTypes.put("jpeg", "image/jpeg");
		mimeTypes.put("jpg", "image/jpeg");
		mimeTypes.put("png", "image/png");
		mimeTypes.put("svg", "image/svg+xml");

		mimeTypes.put("oga", "audio/ogg");
		mimeTypes.put("ogg", "audio/ogg");
		mimeTypes.put("ogv", "video/ogg");
		mimeTypes.put("mp4", "video/mp4");
		mimeTypes.put("webm", "video/webm");

		mimeTypes.put("ttf", "font/truetype");
		mimeTypes.put("otf", "font/opentype");
		mimeTypes.put("eot", "application/vnd.ms-fontobject");
		mimeTypes.put("woff", "application/x-font-woff");
	}

	
	public static final String RESOURCE_SERVLET_URI = "levetag-resources";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String uri = request.getRequestURI().replaceAll("/+", "/");
		String mimeType = getResourceMimeType(uri);
		
		
		byte[] content = readBinaryFile(request.getPathInfo().substring(1));
		
		response.setContentType(mimeType + (mimeType.startsWith("text/") ? ";charset=UTF-8" : ""));
		response.setDateHeader("Last-Modified", startDate.getTimeInMillis());
		response.setDateHeader("Expires", System.currentTimeMillis() + maxAge*milliseconds);
		response.setHeader("Cache-control", "max-age=" + maxAge);
		response.setContentLength(content.length);
		response.getOutputStream().write(content);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
	
	public byte[] readBinaryFile(String file) throws IOException {
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
		
		
		byte[] result;
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			FileInputStream input = new VirtualFileInputStream(is);
			try {
				byte[] buffer = new byte[1024];
				int bytesRead = -1;
				while ((bytesRead = input.read(buffer)) != -1) {
					byteStream.write(buffer, 0, bytesRead);
				}
				result = byteStream.toByteArray();
			} finally {
				byteStream.close();
				input.close();
			}
		} catch (IOException e) {
			throw e;
		}
		return result;
	}

	private String getResourceMimeType(String uri) {
		String extension = uri.substring(uri.lastIndexOf(".") + 1);
		String mimeType = mimeTypes.containsKey(extension) ? mimeTypes.get(extension) : getServletContext().getMimeType(uri);
		return mimeType != null ? mimeType : "application/octet-stream";
	}

}
