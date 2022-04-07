package cn.wolfcode.car.shiro;

import cn.wolfcode.car.base.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * shiro 工具类
 * 
 * @author ruoyi
 */
public class ShiroUtils {
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout() {
        getSubject().logout();
    }

    public static User getUser() {
       return (User) getSubject().getPrincipal();
    }

    public static void setUser(User user) {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    public static Long getUserId(){
        return getUser().getId().longValue();
    }

    public static String getLoginName(){
        return getUser().getLoginName();
    }

    public static String getIp(){
        return getSubject().getSession().getHost();
    }

    public static String getSessionId(){
        return String.valueOf(getSubject().getSession().getId());
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt(){
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }
}
