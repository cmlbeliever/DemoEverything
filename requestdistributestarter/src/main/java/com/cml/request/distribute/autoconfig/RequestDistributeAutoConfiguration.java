package com.cml.request.distribute.autoconfig;

import com.cml.request.distribute.DefaultDistributeRateLimiter;
import com.cml.request.distribute.DistributeGroupManager;
import com.cml.request.distribute.DistributeRateLimiter;
import com.cml.request.distribute.config.RequestDistributeConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class RequestDistributeAutoConfiguration {

    @Bean
    public FilterRegistrationBean requestDistibuteFilterRegistration(FilterRequestDistribute filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE / 2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @ConditionalOnMissingBean
    @Bean
    public FilterRequestDistribute requestDistribute(RequestDistributeCallback<RequestArgs, Void> callback, DistributeRateLimiter rateLimiter, DistributeGroupManager<RequestArgs> distributeGroupManager) {
        FilterRequestDistribute distributeSupport = new FilterRequestDistribute();
        distributeSupport.setGroupManager(distributeGroupManager);
        distributeSupport.setCallback(callback);
        distributeSupport.setRateLimiter(rateLimiter);
        return distributeSupport;
    }

    @ConditionalOnMissingBean
    @Bean
    public DistributeGroupManager<RequestArgs> distributeGroupManager() {
        return (arg) -> arg.getPath().replace("/", "-");
    }

    @ConditionalOnMissingBean
    @Bean
    public DistributeRateLimiter distributeRateLimiter(RequestDistributeConfig config) {
        DefaultDistributeRateLimiter rateLimiter = new DefaultDistributeRateLimiter();
        rateLimiter.setRequestDistributeConfig(config);
        rateLimiter.init();
        return rateLimiter;
    }

    @ConditionalOnMissingBean
    @Bean
    @ConfigurationProperties(prefix = "cml.request-distribute")
    public RequestDistributeConfig requestDistributeConfig() {
        return new RequestDistributeConfig();
    }
}
