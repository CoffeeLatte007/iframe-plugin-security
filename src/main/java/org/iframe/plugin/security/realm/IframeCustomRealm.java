package org.iframe.plugin.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.iframe.plugin.security.IframeSecurity;
import org.iframe.plugin.security.SecurityConstant;
import org.iframe.plugin.security.password.Md5CredentialsMatcher;

import java.util.HashSet;
import java.util.Set;

/**
 * 基于Smart的自定义Realm(需要实现SmartSecurity)
 * Created by lizhaoz on 2016/1/7.
 */

public class IframeCustomRealm extends AuthorizingRealm{
    private final IframeSecurity iframeSecurity;
    public IframeCustomRealm(IframeSecurity iframeSecurity) {
        this.iframeSecurity=iframeSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());//使用MD5加密算法
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals==null){
            throw new AuthorizationException("parameter principals is null");
        }
        //获取已认证的用户
        String username= (String) super.getAvailablePrincipal(principals);
        //通过iframeSecurity接口根据角色名获取与其对应的权限名集合
        Set<String> roleNameSet=iframeSecurity.getRoleNameSet(username);
        //通过接口iframeSecurity 根据角色名获取与其对应的权限名集合
        Set<String> permissionNameSet=new HashSet<String>();
        if (roleNameSet!=null&&roleNameSet.size()>0){
            for (String roleName:roleNameSet){
                //把该角色名的所有权限全部加入
                Set<String> currentPermissionNameSet=iframeSecurity.getPermissionNameSet(roleName);
                permissionNameSet.addAll(currentPermissionNameSet);
            }
        }
        //将角色名集合与权限名放入AuthorizationInfo对象中，便于后续的授权操作
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //放入角色集合
        authorizationInfo.setRoles(roleNameSet);
        //放入权限
        authorizationInfo.setStringPermissions(permissionNameSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token==null){
            throw new AuthenticationException("parameter token is null");
        }
        //通过AuthenticationToken对象获取从表单中提交过来的用户名
        String username=((UsernamePasswordToken)token).getUsername();
        //通过iframeSecurity接口 根据用户名获取数据库中存放密码
        String password=iframeSecurity.getPassword(username);
        //将用户名与密码放入AuthenticationInfo对象中，便于后续的认证操作
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo();
        authenticationInfo.setPrincipals(new SimplePrincipalCollection(username,super.getName()));
        authenticationInfo.setCredentials(password);
        return authenticationInfo;
}
}
