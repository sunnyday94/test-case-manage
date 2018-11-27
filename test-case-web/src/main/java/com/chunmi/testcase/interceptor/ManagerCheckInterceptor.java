/**
 * FileName: ManagerCheckInterceptor
 * Author:   sunny
 * Date:     2018/11/27 16:50
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.interceptor;

import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.utils.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description
 * @author sunny
 * @create 2018/11/27
 * @since 1.0.0
 */
public class ManagerCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if(!servletPath.endsWith(".css") && !servletPath.endsWith(".js") && !servletPath.endsWith(".jpg") &&
                !servletPath.endsWith("checkLogin") && !servletPath.endsWith("goToLogin") && !servletPath.endsWith("goToRegister")
                && !servletPath.endsWith("checkRegister")) {
            Users manager = (Users) request.getSession().getAttribute(Constant.LOGIN_MANAGER);
            if(manager == null) {
                response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/goToLogin");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}