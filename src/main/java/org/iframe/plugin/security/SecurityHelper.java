package org.iframe.plugin.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.iframe.plugin.security.exception.AuthcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Security;

/**
 * Created by lizhaoz on 2016/1/7.
 */

public final class SecurityHelper {
    private static final Logger LOGGER= LoggerFactory.getLogger(SecurityHelper.class);
    public static void login(String username,String password) throws AuthcException{
        //得到当前用户
        Subject currentUser= SecurityUtils.getSubject();
        //判断当前用户是否为空
        if (currentUser!=null){
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            try{
                currentUser.login(token);
            }catch (AuthenticationException e){
                LOGGER.error("login failure",e);
                throw new AuthcException(e);
            }
        }
    }
    public static void logout(){

    }
}
