package com.aaa.ssmshiro.realm;

import com.aaa.ssmshiro.dao.UserDao;
import com.aaa.ssmshiro.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * className:MyRealm
 * discriptoin:
 * author:邢博
 * createTime:2018-11-15 21:55
 */
public class MyRealm extends AuthorizingRealm {

@Autowired
private UserDao userDao;
    /**
     * 1.登录认证
     * @param authenticationToken
     * @return thenticationInfo：获取认证消息，如果数据库中没有，返回null，如果得到正确的用户名和密码
     * 2.AuthenticationInfo  可用simpleAuthenticationInfo实现类，封装获取到的正确的账号和密码
     * 返回正定类型的对象
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.将token转换为UserNamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //2.获取用户名
        User user = new User();
        user.setUsername(token.getUsername());
        user.setPassword(token.getPassword().toString());

        User us = userDao.login(user);
        if(us!=null){
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(us.getUsername(),us.getPassword(),"a");
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(us.getPasswordSalt()));
            return authenticationInfo;
        }else{
            throw new ExcessiveAttemptsException("账号密码错误");
        }
    }

    /**
     * 权限角色认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = principalCollection.getPrimaryPrincipal().toString();
        List<String> roles = userDao.selectRole(username);
        List<String> permissions = userDao.selectPermission(username);
        Set<String> ro = new HashSet<String>();
        Set<String> per = new HashSet<String>();
        for (String role : roles) {
            ro.add(role);
        }
        for (String permission : permissions) {
            per.add(permission);
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(ro);
        simpleAuthorizationInfo.setStringPermissions(per);
        return simpleAuthorizationInfo;
    }

}
