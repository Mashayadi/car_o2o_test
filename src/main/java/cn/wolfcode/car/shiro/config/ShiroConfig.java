package cn.wolfcode.car.shiro.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.wolfcode.car.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 权限配置加载
 */
@Configuration
public class ShiroConfig {
    /**
     * 登录地址
     */
    @Value("${shiro.loginUrl}")
    private String loginUrl;

    /**
     * 登录成功
     */
    @Value("${shiro.successUrl}")
    private String successUrl;

    /**
     * 权限认证失败地址
     */
    @Value("${shiro.unauthorizedUrl}")
    private String unauthorizedUrl;


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/", "authc");
        //登录
        chainDefinition.addPathDefinition("/userLogin", "anon");
        chainDefinition.addPathDefinition("/login", "anon");

        // 登出功能
        chainDefinition.addPathDefinition("/logout", "logout");
        // 错误页面无需认证
        chainDefinition.addPathDefinition("/error", "anon");
        // 静态资源无需认证
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/fonts/**", "anon");
        chainDefinition.addPathDefinition("/images/**", "anon");
        chainDefinition.addPathDefinition("/plugins/**", "anon");
        chainDefinition.addPathDefinition("/ruoyi/**", "anon");
        chainDefinition.addPathDefinition("/frontend/**", "anon");
        chainDefinition.addPathDefinition("/favicon.ico", "anon");
        chainDefinition.addPathDefinition("/logo.png", "anon");
        // 其余资源都需要认证
        chainDefinition.addPathDefinition("/**", "authc");


        return chainDefinition;
    }

    //thymeleaf模板引擎和shiro框架的整合
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * setUsePrefix(true)用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
     * 加入这项配置能解决这个bug
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }



    @Bean
    public Realm userRealm() {
        return new UserRealm();
    }

   @Bean(name = "globalFilters")
    public  List<String> globalFilters() {
        return Collections.singletonList(DefaultFilter.invalidRequest.name());
    }
}