/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.mea.contentmanagement.XssHttpServletRequestWrapper;
import com.mea.contentmanagement.constants.ContentMgtThreadContext;



/**
 * The Class RequestFilter.
 *
 * @author TCS.
 */
@Component
public class RequestFilter implements Filter, Ordered {

    /** The Constant LOGGER. */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager
            .getLogger(RequestFilter.class);

    // LOWEST_PRECEDENCE than WebRequestTraceFilter
    // (Ordered.LOWEST_PRECEDENCE-20)
    // so that the SPThreadData parameters
    /** The Constant ORDER. */
    // are available while logging the actuator trace
    private static final int ORDER = Ordered.LOWEST_PRECEDENCE - 20;

    /** The is inculde rich text. */
    private boolean isInculdeRichText = true;

    /** The excludes. */
    private List<String> excludes = new ArrayList<>();

    /**
     * (non-Javadoc).
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @param chain
     *            the chain
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servlet exception
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain) This
     *      method is overriden to set the MDC params required to be logged.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        try {

            XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                    (HttpServletRequest) request, isInculdeRichText);

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            ContentMgtThreadContext.setRequestId(
                    xssRequest.getHeader(ContentMgtThreadContext.REQUEST_ID_KEY));
            ContentMgtThreadContext.setSessionId(
                    xssRequest.getHeader(ContentMgtThreadContext.SESSION_ID_KEY));            
            ContentMgtThreadContext.setUserName("Test");
           // if (handleExcludeURL(httpRequest)) {
           //     chain.doFilter(request, response);
          //      return;
          //  }

            chain.doFilter(xssRequest, response);
        } finally {
            // Tear down SPThreadData data
            ThreadContext.clearAll();

        }

    }

    /**
     * Handle exclude URL.
     *
     * @param request
     *            the request
     * @return true, if successful
     */
    private boolean handleExcludeURL(HttpServletRequest request) {
        excludes.add("/favicon.ico");
        excludes.add("/img/*");
        excludes.add("/js/*");
        excludes.add("/css/*");

        if (excludes.isEmpty()) {
            return false;
        }
        String url = request.getServletPath();
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * (non-Javadoc).
     *
     * @param filterConfig
     *            the filter config
     * @throws ServletException
     *             the servlet exception
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig).
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String isIncludeRichText = filterConfig
                .getInitParameter("isIncludeRichText");
        if (StringUtils.isNotBlank(isIncludeRichText)) {
            isInculdeRichText = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = filterConfig.getInitParameter("excludes");
        if (temp != null) {
            String[] url = temp.split(",");
            for (int i = 0; url != null && i < url.length; i++) {
                excludes.add(url[i]);
            }
        }
    }

    /**
     * (non-Javadoc).
     *
     * @see javax.servlet.Filter#destroy().
     */
    @Override
    public void destroy() {

        //
    }

    /**
     * (non-Javadoc).
     *
     * @return the order
     * @see org.springframework.core.Ordered#getOrder().
     */
    @Override
    public int getOrder() {

        return ORDER;
    }

}
