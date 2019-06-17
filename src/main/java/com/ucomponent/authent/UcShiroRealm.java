package com.ucomponent.authent;

import com.ucomponent.base.ICommons;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:Shiro授权
 * Descp:
**/
public class UcShiroRealm extends AuthorizingRealm implements ICommons {

  /**
   * 用于获取登录成功后的角色、权限等信息
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    System.out.println("UcShiroRealm doGetAuthorizationInfo: userID = "+principals);
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    Subject subject = SecurityUtils.getSubject();
    Session ssession = subject.getSession();
    Set<String> set = (Set)ssession.getAttribute(USER_PATH_SET);
    for (String path : set) {
      authorizationInfo.addStringPermission(path);
    }
//    TokenMap tokenMap = TokenMap.getInstance();
//    if(tokenMap.getTokenById(principals.toString()) !=null){
//      Set<String> set = (Set)ssession.getAttribute(USER_PATH_SET);
//      for (String path : set) {
//        authorizationInfo.addStringPermission(path);
//      }
//    }

    return authorizationInfo;
  }

  /**
   *  主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
   *
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    // System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
    // 获取用户的输入的账号.
    // 通过username从数据库中查找 User对象，如果找到，没找到.
    // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

    //TokenMap tokenMap = TokenMap.getInstance();
    if ( token.getPrincipal() ==null) {
      return null;
    }

    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token.getPrincipal(), // 用户名
        token.getCredentials(), // 密码
        ByteSource.Util.bytes(token.getCredentials()),
        getName() // realm name
    );
    return authenticationInfo;
  }

}