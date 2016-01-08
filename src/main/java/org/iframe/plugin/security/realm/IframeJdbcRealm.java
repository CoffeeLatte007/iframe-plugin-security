package org.iframe.plugin.security.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.framework.helper.DatabaseHelper;
import org.iframe.plugin.security.SecurityConfig;
import org.iframe.plugin.security.password.Md5CredentialsMatcher;

/**
 *基于Smart的JDBC Realm
 * Created by lizhaoz on 2016/1/7.
 */

public class IframeJdbcRealm extends JdbcRealm {
    public IframeJdbcRealm() {
        //设置
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        //开启查询开关
        super.setPermissionsLookupEnabled(true);
        //使用MD5加密算法
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }
}
