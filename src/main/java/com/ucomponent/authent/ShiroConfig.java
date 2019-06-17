package com.ucomponent.authent;

import com.ucomponent.authent.handler.GlobalExceptionResolver;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2018年9月30日
 * 代码老哥
 * NAME:权限控制器
 * Descp:
 **/
@Configuration
public class ShiroConfig {
	@Value("${spring.redis.shiro.host}")
	private String host;
	@Value("${spring.redis.shiro.timeout}")
	private int timeout;
	@Value("${spring.redis.shiro.password}")
	private String password;

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 *
	 * @return
	 */
//  @Bean
//  @DependsOn({ "lifecycleBeanPostProcessor" })
//  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//    advisorAutoProxyCreator.setProxyTargetClass(true);
//    return advisorAutoProxyCreator;
//  }
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public UcShiroRealm getUcshiroRealm() {
		UcShiroRealm realm = new UcShiroRealm();
		//realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	/**
	 * 安全管理器
	 * 将realm加入securityManager
	 *
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(EhCacheManager ehCacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(getUcshiroRealm());
		securityManager.setSessionManager(sessionManager());
		securityManager.setCacheManager(ehCacheManager);
		return securityManager;
	}

	/**
	 * shiro filter 工厂类
	 * 1.定义ShiroFilterFactoryBean
	 * 2.设置SecurityManager
	 * 3.配置拦截器
	 * 4.返回定义ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println("+++++++ SHIRO INIT +++++++");
		System.out.println("STEP 1. Shiro Filter Start");
		//1
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		//2
		//注册securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		//3
		// 拦截器+配置登录和登录成功之后的url
		//LinkHashMap是有序的，shiro会根据添加的顺序进行拦截
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//配置不会被拦截的连接  这里顺序判断
		//    //user，配置记住我或者认证通过才能访问
		//    //anon:所有的url都可以匿名访问
		filterChainDefinitionMap.put("/forget", "anon");
		filterChainDefinitionMap.put("/register", "anon");
		//filterChainDefinitionMap.put("/login","anon");
		filterChainDefinitionMap.put("/index", "anon");
		//logout:配置退出过滤器
		filterChainDefinitionMap.put("/logout", "logout");
		//过滤连接自定义，从上往下顺序执行，所以用LinkHashMap /**放在最下边
		//authc：所有url都必须认证通过才可以访问
		filterChainDefinitionMap.put("/manager/**", "authc");
		//filterChainDefinitionMap.put("/**","authc");
		//设置登录界面，如果不设置为寻找web根目录下的文件
		shiroFilterFactoryBean.setLoginUrl("/login");
		//设置登录成功后要跳转的连接
		shiroFilterFactoryBean.setSuccessUrl("/manager/index");
		//设置登录未成功，也可以说无权限界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthor");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("Shiro Filter Authc Start Success");

		//4
		//返回
		return shiroFilterFactoryBean;
	}

	public LogoutFilter getLogoutFilter() { // 在ShiroFilterFactoryBean中使用
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/login"); // 首页路径，登录注销后回到的页面
		return logoutFilter;
	}

	@Bean(name = "exceptionHandler")
	public HandlerExceptionResolver handlerExceptionResolver() {
		return new GlobalExceptionResolver();
	}

	@Bean
	public UcSessionManager sessionManager() {
		UcSessionManager shiroSessionManager = new UcSessionManager();
		//这里可以不设置。Shiro有默认的session管理。如果缓存为Redis则需改用Redis的管理
		shiroSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
		return shiroSessionManager;
	}

	@Bean(name = "springCacheManager")
	public EhCacheManager ehCacheManager() {
		EhCacheManager bean=new EhCacheManager();
		bean.setCacheManager(ehCacheManagerFactoryBean());
		return bean;
	}

	@Bean(name = "ehCacheManager")
	public CacheManager ehCacheManagerFactoryBean() {
		CacheManager cacheManager = CacheManager.getCacheManager("ucxEhcache");
		if(cacheManager == null){
			try {
				cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache.xml"));
			} catch (IOException e) {
				throw new RuntimeException("initialize cacheManager failed");
			}
		}
		return cacheManager;
	}
}
