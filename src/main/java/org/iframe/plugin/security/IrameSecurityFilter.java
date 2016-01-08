package org.iframe.plugin.security;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.iframe.plugin.security.realm.IframeCustomRealm;
import org.iframe.plugin.security.realm.IframeJdbcRealm;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by lizhaoz on 2016/1/7.
 */

public class IrameSecurityFilter extends ShiroFilter {
    @Override
    public void init() throws Exception {
        super.init();
        WebSecurityManager webSecurityManager=super.getSecurityManager();
        //设置Realm,可同时支持多个Realm,并按照先后顺序用逗号分割。
        setRealms(webSecurityManager);
        //设置Cache,减少数据库查询次数，降低I/O访问
        setCache(webSecurityManager);
    }

    private void setCache(WebSecurityManager webSecurityManager) {
        //读取iframe.plugin.security.cache配置项
        if (SecurityConfig.isCache()){
            CachingSecurityManager cachingSecurityManager=(CachingSecurityManager) webSecurityManager;
            //使用基于内存的CacheManager
            CacheManager cacheManager=new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }

    private void setRealms(WebSecurityManager webSecurityManager) {
        //读取iframe.plugin.security.realms 配置项
        String securityRealms = SecurityConfig.getRealms();
        if (securityRealms!=null){
            //如果有多个realm，根据都好久进行拆分
            String[] securityRealmArray=securityRealms.split(",");
            if (securityRealmArray.length>0){
                //使Realm具备唯一性与顺序性
                Set<Realm> realms=new LinkedHashSet<Realm>();
                for (String securityRealm:securityRealmArray){
                    if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
                        //添加基于Jdbc的Realm,需配置一些SQL语句
                        addJdbcRealm(realms);
                    }
                    else if(securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
                        //添加基于定制的Realm,需实现SmartSecurity接口
                        addCustomRealm(realms);
                    }
                }
                RealmSecurityManager realmSecurityManager=(RealmSecurityManager)webSecurityManager;
                realmSecurityManager.setRealms(realms);
            }
        }
    }

    private void addCustomRealm(Set<Realm> realms) {
        //读取iframe.plugin.security.custom.class配置项
        IframeSecurity iframeSecurity=SecurityConfig.getSmartSecurity();
        IframeCustomRealm iframeCustomRealm=new IframeCustomRealm(iframeSecurity);
        realms.add(iframeCustomRealm);
    }

    private void addJdbcRealm(Set<Realm> realms) {
        //添加自己实现基于JDBC的Realm
        IframeJdbcRealm iframeJdbcRealm=new IframeJdbcRealm();
        realms.add(iframeJdbcRealm);
    }
}
