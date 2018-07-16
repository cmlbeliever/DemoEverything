package com.cml.request.distribute.autoconfig.anon;

import com.cml.request.distribute.autoconfig.RequestDistributeAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Auther: cml
 * @Date: 2018-07-16 17:58
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RequestDistributeAutoConfiguration.class)
@Documented
public @interface EnableAutoDistributeRequest {
}
