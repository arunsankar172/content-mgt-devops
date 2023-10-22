/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */

package com.mea.contentmanagement;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class XssHttpServletRequestWrapper.
 *
 * @author TCS
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager
            .getLogger(XssHttpServletRequestWrapper.class);

    /** The org request. */
    private HttpServletRequest orgRequest = null;

    /** The is include rich text. */
    private boolean isIncludeRichText = false;

    /**
     * Instantiates a new xss http servlet request wrapper.
     *
     * @param request
     *            the request
     * @param isIncludeRichText
     *            the is include rich text
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request,
            boolean isIncludeRichText) {
        super(request);
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    /**
     * Covering the getParameter method, the parameter names and values are
     * filtered by xss. If you need to get the original value, you get it
     * through super.getParameterValues(name)
     * getParameterNames,getParameterValues And getParameterMap may also need to
     * be overwritten
     *
     * @param name
     *            the name
     * @return the parameter
     */
    @Override
    public String getParameter(String name) {
        if (("content".equals(name) || name.endsWith("WithHtml"))
                && !isIncludeRichText) {
            return super.getParameter(name);
        }
        name = XssFilterUtil.clean(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = XssFilterUtil.clean(arr[i]);
            }
        }
        return arr;
    }

    /**
     * Covering the getHeader method, the parameter names and values are
     * filtered by xss. <br/>
     * If you need to get the original value, get < br/> through
     * super.getHeaders(name) getHeaderNames Coverage may also be required
     *
     * @param name
     *            the name
     * @return the header
     */
    @Override
    public String getHeader(String name) {
        name = XssFilterUtil.clean(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    /**
     * Gets the org request.
     *
     * @return the org request
     */
    HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * Gets the org request.
     *
     * @param req
     *            the req
     * @return the org request
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletRequestWrapper#getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(
                inputHandlers(super.getInputStream()).getBytes());

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // Do nothing because of X and Y.

            }
        };
    }

    /**
     * Input handlers.
     *
     * @param servletInputStream
     *            the servlet input stream
     * @return the string
     */
    public String inputHandlers(ServletInputStream servletInputStream) {

        JSONObject json = null;

        String jsonString = null;
        try {
            jsonString = OAuthUtils.saveStreamAsString(servletInputStream);
            json = new JSONObject(jsonString);

            convertAndClean(json);

        } catch (IOException | JSONException e1) {

            LOGGER.log(null, "context");
            return jsonString;
        }

        return json.toString();
    }

    /**
     * Convert and clean.
     *
     * @param json
     *            the json
     */
    private void convertAndClean(JSONObject json) {

        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (json.get(key) instanceof JSONObject) {
                convertAndClean((JSONObject) json.get(key));
            } else if (json.get(key) instanceof JSONArray) {
                JSONArray arr = (JSONArray) json.get(key);
                arr.forEach(it -> {
                    if (it instanceof JSONObject) {
                        convertAndClean((JSONObject) it);
                    }
                });
            } else {
                String value = null;
                value = XssFilterUtil.clean(json.get(key).toString());
                json.put(key, value);
            }
        }

    }

}
