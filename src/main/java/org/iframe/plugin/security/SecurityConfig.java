package org.iframe.plugin.security;

import org.framework.helper.ConfigHelper;
import org.framework.util.ReflectionUtil;

/**
 * 从配置文件中获取相关属性
 * Created by lizhaoz on 2016/1/7.
 */

public class SecurityConfig {
    public static String getRealms() {
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static IframeSecurity getSmartSecurity() {
        String className=ConfigHelper.getString(SecurityConstant.IFRAME_SECURITY);
        return (IframeSecurity) ReflectionUtil.newInstance(className);
    }

    public static boolean isCache() {
        return ConfigHelper.getBoolean(SecurityConstant.CACHE);
    }

    public static String getJdbcAuthcQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery() {

        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }
}
