package org.iframe.plugin.security;

/**
 * 常量接口
 * Created by lizhaoz on 2016/1/7.
 */

public interface SecurityConstant {
    String REALMS="iframe.plugin.security.realms";
    String REALMS_JDBC ="jdbc";
    String REALMS_CUSTOM="custom";
    String IFRAME_SECURITY="iframe.plugin.security.custom.class";
    String CACHE = "iframe.plugin.security.cache";
    String JDBC_AUTHC_QUERY = "iframe.plugin.security.jdbc.authc_query";
    String JDBC_ROLES_QUERY = "iframe.plugin.security.jdbc.roles_query";
    String JDBC_PERMISSIONS_QUERY = "iframe.plugin.security.jdbc.permissions_query";
}
