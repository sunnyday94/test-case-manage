/**
 * FileName: ErrorController
 * Author:   sunny
 * Date:     2018/11/27 17:10
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description
 * @author sunny
 * @create 2018/11/27
 * @since 1.0.0
 */
@Controller
public class ErrorController {


    /**
     * @Description: 404页面
     * @Author: sunny
     * @Date: 2018/11/27 17:11
     */
    @GetMapping(value = "/404")
    public String error_404() {
        return "error/404";
    }


    /**
     * @Description: 500页面
     * @Author: sunny
     * @Date: 2018/11/27 17:11
     */
    @GetMapping(value = "/500")
    public String error_500() {
        return "error/500";
    }
}