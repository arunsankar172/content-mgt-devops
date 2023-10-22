/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */

package com.mea.contentmanagement;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CloseShieldInputStream;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.mea.contentmanagement.constants.ContentMgtConstant;

/**
 * The Class PayloadFilter.
 *
 * @author TCS
 */
public class PayloadFilter extends OncePerRequestFilter implements Ordered {

	/** The order. */
	private int order = Ordered.LOWEST_PRECEDENCE - 15;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.filter.
	 * OncePerRequestFilter#doFilterInternal(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		MultiReadHttpServletRequest requestWrapper = new MultiReadHttpServletRequest(request);

		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

		Map<String, Object> traceRequest = new LinkedHashMap<>();

		setRequestTrace(traceRequest, requestWrapper);
		setRequestBodyTrace(requestWrapper, traceRequest);
		logTrace("REQUEST", traceRequest);

		try {
			filterChain.doFilter(requestWrapper, responseWrapper);
		} finally {

			Map<String, Object> traceResponse = new LinkedHashMap<>();
			traceResponse.put(ContentMgtConstant.HEADERS, traceRequest.get(ContentMgtConstant.HEADERS));

			setResponseTrace(traceResponse, responseWrapper);

			setResponseBodyTrace(responseWrapper, traceResponse);
			logTrace("RESPONSE", traceResponse);

		}
	}

	/**
	 * This method sets the request related params to the map instance trace.
	 *
	 * @param trace
	 *            the trace
	 * @param requestWrapper
	 *            the request wrapper
	 */
	protected void setRequestTrace(Map<String, Object> trace, HttpServletRequest requestWrapper) {

		HttpSession session = requestWrapper.getSession(false);

		trace.put("method", requestWrapper.getMethod());
		trace.put("path", requestWrapper.getRequestURI());
		trace.put("headers", getRequestHeaders(requestWrapper));

		trace.put("pathInfo", requestWrapper.getPathInfo());
		trace.put("pathTranslated", requestWrapper.getPathTranslated());
		trace.put("contextPath", requestWrapper.getContextPath());

		trace.put("parameters", getParameterMapString(requestWrapper.getParameterMap()));

		trace.put("query", requestWrapper.getQueryString());
		trace.put("authType", requestWrapper.getAuthType());
		trace.put("remoteAddress", requestWrapper.getRemoteAddr());
		trace.put("sessionId", (session == null ? null : session.getId()));
		trace.put("remoteUser", requestWrapper.getRemoteUser());
	}

	/**
	 * Gets the parameter map string.
	 *
	 * @param parameterMap
	 *            the parameter map
	 * @return the parameter map string
	 */
	private Map<String, String> getParameterMapString(Map<String, String[]> parameterMap) {

		Map<String, String> parameterMapStr = new LinkedHashMap<>();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String key = entry.getKey();
			String[] value = entry.getValue();
			parameterMapStr.put(key, Arrays.toString(value));
		}
		return parameterMapStr;
	}

	/**
	 * The method to Sets the request body trace.
	 *
	 * @param request
	 *            the request
	 * @param trace
	 *            the trace
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	final void setRequestBodyTrace(MultiReadHttpServletRequest request, Map<String, Object> trace) throws IOException {

		InputStream is = null;
		is = request.getInputStream();
		CloseShieldInputStream cis = new CloseShieldInputStream(is);
		trace.put("  RequestBody", getRequestData(cis));
	}

	/**
	 * Gets the request data.
	 *
	 * @param in
	 *            the in
	 * @return the request data
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	// new code------
	protected String getRequestData(CloseShieldInputStream in) throws IOException {

		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = null;
		byte[] buf = new byte[1024];
		int read;
		while ((read = in.read(buf)) > 0) {
			baos.write(buf, 0, read);
		}
		buffer = baos.toByteArray();
		bais = new ByteArrayInputStream(buffer);
		StringBuilder logRequestMessage = new StringBuilder("").append("").append(getRequestBody(bais));
		return logRequestMessage.toString();
	}

	/**
	 * Gets the request body.
	 *
	 * @param bais
	 *            the bais
	 * @return the request body
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	// commented out the old getrequestbody this is the new code
	protected String getRequestBody(ByteArrayInputStream bais) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(bais));
		String line = null;
		StringBuilder inputBuffer = new StringBuilder();
		do {
			line = reader.readLine();
			if (null != line) {
				inputBuffer.append(line.trim());
			}
		} while (line != null);
		reader.close();
		return inputBuffer.toString().trim();

	}

	/**
	 * This method returns the request headers params as a map.
	 *
	 * @param request
	 *            the request
	 * @return the request headers
	 */
	protected Map<String, Object> getRequestHeaders(HttpServletRequest request) {

		Map<String, Object> headers = new LinkedHashMap<>();

		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();

			headers.put(name, getHeaderValue(request, name));

		}
		return headers;
	}

	/**
	 * This method returns the value of the input request header param.
	 *
	 * @param request
	 *            the request
	 * @param name
	 *            the name
	 * @return the header value
	 */
	protected Object getHeaderValue(HttpServletRequest request, String name) {

		List<String> value = Collections.list(request.getHeaders(name));
		if (value.isEmpty()) {
			return "";
		} else if (value.size() == 1) {
			return value.get(0);
		}

		return value;
	}

	/**
	 * The method to Sets the response trace.
	 *
	 * @param trace
	 *            the trace
	 * @param responseWrapper
	 *            the response wrapper
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	/**
	 * protected Object getRequestBody( ContentCachingRequestWrapper
	 * requestWrapper) { requestWrapper.getParameterMap(); return new
	 * String(requestWrapper.getContentAsByteArray()); }
	 */

	/**
	 * This method sets the response related params to the map instance trace
	 *
	 * @param trace
	 * @param responseWrapper
	 */
	protected void setResponseTrace(Map<String, Object> trace, ContentCachingResponseWrapper responseWrapper)
			 {

		@SuppressWarnings("unchecked")
		Map<String, Object> headers = (Map<String, Object>) trace.get(ContentMgtConstant.HEADERS);
		headers.put("response", getResponseHeaders(responseWrapper));
	}

	/**
	 * This method sets the responseBody to the map instance trace.
	 *
	 * @param responseWrapper
	 *            the response wrapper
	 * @param trace
	 *            the trace
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void setResponseBodyTrace(ContentCachingResponseWrapper responseWrapper, Map<String, Object> trace)
			throws IOException {

		trace.put("responseBody", getResponseBody(responseWrapper));
	}

	/**
	 * This method returns the response headers params as a map.
	 *
	 * @param response
	 *            the response
	 * @return the response headers
	 */
	protected Map<String, String> getResponseHeaders(HttpServletResponse response) {

		Map<String, String> headers = new LinkedHashMap<>();
		for (String name : response.getHeaderNames()) {
			String value = getHeaderValue(response, name);
			headers.put(name, value);
		}

		headers.put("status", "" + response.getStatus());
		return headers;
	}

	/**
	 * This method returns the value of the input response header param.
	 *
	 * @param response
	 *            the response
	 * @param name
	 *            the name
	 * @return the header value
	 */
	protected String getHeaderValue(HttpServletResponse response, String name) {

		return response.getHeader(name);
	}

	/**
	 * This method is used to get the responsebody from the
	 * ContentCachingResponseWrapper instance *.
	 *
	 * @param responseWrapper
	 *            the response wrapper
	 * @return the response body
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected Object getResponseBody(ContentCachingResponseWrapper responseWrapper) throws IOException {

		String strResponseBody = new String(responseWrapper.getContentAsByteArray());
		responseWrapper.copyBodyToResponse();
		return strResponseBody;
	}

	/**
	 * The method to Log trace.
	 *
	 * @param trace
	 *            the trace
	 */
	protected void logTrace(Map<String, Object> trace) {
		// Do Nothing
	}

	/**
	 * The method to REQUEST/RESPONSE and Log trace.
	 *
	 * @param messageType the message type
	 * @param trace            the trace
	 */
	protected void logTrace(String messageType, Map<String, Object> trace) {
		// Do Nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.Ordered#getOrder()
	 */
	@Override
	public int getOrder() {

		return this.order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order
	 *            the new order
	 */
	public void setOrder(int order) {

		this.order = order;
	}

	/**
	 * The Class MultiReadHttpServletRequest.
	 *
	 * @author TCS
	 */
	// new wrapper class (custom wrapper)
	public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

		/** The cached bytes. */
		private ByteArrayOutputStream cachedBytes;

		/**
		 * Instantiates a new multi read http servlet request.
		 *
		 * @param request
		 *            the request
		 */
		public MultiReadHttpServletRequest(HttpServletRequest request) {

			super(request);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.servlet.ServletRequestWrapper#getInputStream()
		 */
		@Override
		public ServletInputStream getInputStream() throws IOException {

			if (cachedBytes == null) {
				cacheInputStream();
			}

			return new CachedServletInputStream();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.servlet.ServletRequestWrapper#getReader()
		 */
		@Override
		public BufferedReader getReader() throws IOException {

			return new BufferedReader(new InputStreamReader(getInputStream()));
		}

		/**
		 * The method to Cache input stream.
		 *
		 * @throws IOException
		 *             Signals that an I/O exception has occurred.
		 */
		public void cacheInputStream() throws IOException {

			cachedBytes = new ByteArrayOutputStream();
			IOUtils.copy(super.getInputStream(), cachedBytes);

		}

		/**
		 * The Class CachedServletInputStream.
		 *
		 * @author TCS
		 */
		public class CachedServletInputStream extends ServletInputStream {

			/** The input. */
			private ByteArrayInputStream input;

			/**
			 * Instantiates a new cached servlet input stream.
			 */
			CachedServletInputStream() {

				input = new ByteArrayInputStream(cachedBytes.toByteArray());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.io.InputStream#read()
			 */
			@Override
			public int read() throws IOException {

				return input.read();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.servlet.ServletInputStream#isFinished()
			 */
			@Override
			public boolean isFinished() {

				return false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.servlet.ServletInputStream#isReady()
			 */
			@Override
			public boolean isReady() {

				return false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.servlet.
			 * ServletInputStream#setReadListener(javax.servlet.ReadListener)
			 */
			@Override
			public void setReadListener(ReadListener listener) {
				// Do Nothing
			}
		}

	}

}
