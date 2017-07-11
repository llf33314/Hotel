package com.gt.hotel.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Druid的DataResource配置类
 * Created by zhangmz on 2017/6/15.
 * <p>
 * 凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment 可以在工程启动时，
 * 获取到系统环境变量和application配置文件中的变量。
 * 还有一种方式是采用注解的方式获取 @value("${变量的key值}")
 * ：获取application配置文件中的变量。 这里采用第一种要方便些
 * </p>
 */
@Configuration
public class DruidDataSourceConfig {

    @ConfigurationProperties(prefix = "datasource.druid")
    @Bean(name = "datasource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource getDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    /**
     * 注册一个StatViewServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServle2() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,192.168.2.150");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.2.150");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * 监听Spring
     * 1.定义拦截器
     * 2.定义切入点
     * 3.定义通知类
     *
     * @return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * 设置方法切入点
     *
     * @return
     */
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String servicePatterns = "com.gt.hotel.*.service.*";
        String daoPatterns = "com.gt.*.dao.*";
        druidStatPointcut.setPatterns(servicePatterns, daoPatterns);
        return druidStatPointcut;
    }

    /**
     * 配置AOP
     *
     * @return
     */
    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}
