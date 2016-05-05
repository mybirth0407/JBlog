package jblog.interceptor;

import jblog.annotation.Auth;
import jblog.vo.UserVo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
        HttpServletRequest req,
        HttpServletResponse res,
        Object handler)
        throws Exception {

        if (handler instanceof HandlerMethod == false) {
            return true;
        }

        Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        HttpSession httpSession = req.getSession();
        if (httpSession == null) {
            res.sendRedirect(req.getContextPath() + "/user/loginform");
            return false;
        }

        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            res.sendRedirect(req.getContextPath() + "/user/loginform");
            return false;
        }

        return true;
    }
}
