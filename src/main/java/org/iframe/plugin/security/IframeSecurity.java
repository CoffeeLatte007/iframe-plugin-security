package org.iframe.plugin.security;

//~--- JDK imports ------------------------------------------------------------

import java.util.Set;

/**
 * Smart Security接口
 * 可在应用中实现该接口，或者在 smart.properties 文件中提供以下基于 SQL 的配置项：
 * smart.security.jdbc.authc_query：根据用户名获取密码
 * smart.security.jdbc.roles_query：根据用户名获取角色名集合
 * smart.security.jdbc.permissions_query：根据角色名获取权限名集合
 * Created by lizhaoz on 2016/1/6.
 */
public interface IframeSecurity {

    /**
     * 根据用户名获取密码
     * author：Lizhao
     * Date:16/01/06
     * version:1.0
     *
     * @param username
     *
     * @return
     */
    String getPassword(String username);

    /**
     * 根据用户名获取角色名的集合
     * author：Lizhao
     * Date:16/01/06
     * version:1.0
     *
     * @param username
     *
     * @return
     */
    Set<String> getRoleNameSet(String username);

    /**
     * 根据角色名获取权限名集合
     * author：Lizhao
     * Date:16/01/06
     * version:1.0
     *
     * @param roleName
     *
     * @return
     */
    Set<String> getPermissionNameSet(String roleName);
}


//~ Formatted by Jindent --- http://www.jindent.com
