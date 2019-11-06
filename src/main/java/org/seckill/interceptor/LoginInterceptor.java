package org.seckill.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/20 14:05
 * 功能 SSM中的拦截器，在spring-web.xml文件中配置该包路径
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 重写preHandle方法，拦截未登录用户访问 /admin 路径
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession(); //获取session
        //User user = (User) session.getAttribute("user");
        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
        /*if (user != null) {
            return true;
        }*/
        //不符合条件的给出提示信息，
        response.sendRedirect(request.getContextPath()+"/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

