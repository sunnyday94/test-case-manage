/**
 * FileName: WebConfig
 * Author:   sunny
 * Date:     2018/11/23 22:20
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.configuration;

import com.chunmi.testcase.interceptor.CustomInterceptor;
import com.chunmi.testcase.interceptor.ManagerCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/23
 * @since 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new ManagerCheckInterceptor()).addPathPatterns("/**");
    }
}