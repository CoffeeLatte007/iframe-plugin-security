package org.iframe.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.*;
import java.util.Set;

/**
 * Created by lizhaoz on 2016/1/7.
 */

public class IframeSecurityPlugin implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        //设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations","classpath:smart-security.ini");
        //注册Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        //注册Listener
        FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("IframeSecurityFilter",  IrameSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
