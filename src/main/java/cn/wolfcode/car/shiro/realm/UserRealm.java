package cn.wolfcode.car.shiro.realm;

import cn.wolfcode.car.base.domain.Menu;
import cn.wolfcode.car.base.domain.Role;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.service.IMenuService;
import cn.wolfcode.car.base.service.IRoleService;
import cn.wolfcode.car.base.service.IUserService;

import cn.wolfcode.car.shiro.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 自定义Realm 处理登录 权限
 */
public class UserRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        User user = ShiroUtils.getUser();
        // 角色列表
        List<Role> roles = new ArrayList<>();
        // 功能列表
        List<Menu> menus = new ArrayList<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if ("admin".equals(user.getLoginName())){
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        else{
            roles = roleService.queryByUserId(user.getId());
            menus = menuService.queryByUserId(user.getId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles.stream().map(role -> role.getRkey()).collect(Collectors.toSet()));
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus.stream().filter(menu-> StringUtils.hasText(menu.getPerms()))
                    .map(menu -> menu.getPerms()).collect(Collectors.toSet()));
        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        User user = userService.queryByUserName(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
    }

    /**
     * 清理指定用户授权信息缓存
     */
    public void clearCachedAuthorizationInfo(Object principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        this.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清理所有用户授权信息缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
