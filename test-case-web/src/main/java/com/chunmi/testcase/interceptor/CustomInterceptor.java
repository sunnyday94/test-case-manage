/**
 * FileName: CustomInterceptor
 * Author:   sunny
 * Date:     2018/11/23 22:12
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/23
 * @since 1.0.0
 */
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    private StopWatch stopwatch;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        stopwatch = new StopWatch();
        stopwatch.start();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不是映射到方法则直接return
        if(!(handler instanceof  HandlerMethod))
            return;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getName().equals("error"))
            return ;
        stopwatch.stop();
        log.info(method.getName()+"方法总耗时:{}", stopwatch.getTotalTimeSeconds()+"秒");
    }
}