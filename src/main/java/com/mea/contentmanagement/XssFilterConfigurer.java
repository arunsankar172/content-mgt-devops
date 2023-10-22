/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mea.contentmanagement.filter.RequestFilter;

/**
 * The Class XssFilterConfigurer.
 *
 * @author TCS
 */
@Configuration
public class XssFilterConfigurer {

    /**
     * xss Filter interceptor.
     * Xss filter registration bean.
     *
     *
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestFilter());

        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap();
        // excludes is used to configure request URLs that do not require
        // parameter filtering
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        // isIncludeRichText is mainly used to set whether rich text content
        // needs to be filtered or not.
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}
