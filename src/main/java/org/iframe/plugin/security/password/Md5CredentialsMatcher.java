package org.iframe.plugin.security.password;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.framework.util.CodeUtil;

/**
 * Created by lizhaoz on 2016/1/7.
 */

public class Md5CredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //得到从客户提交过来的密码，明文，尚未通过MD5
        String submitted=String.valueOf(((UsernamePasswordToken)token).getPassword());
        //得到数据库中的MD5加密的密码
        String encrypted=String.valueOf(info.getCredentials());
        return CodeUtil.md5(submitted).equals(encrypted);
    }
}
