package jblog.interceptor;

import jblog.service.UserService;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest req,
        HttpServletResponse res, Object handler)
        throws Exception {
        String id = req.getParameter("id");
        String passwd = req.getParameter("passwd");
        UserVo userVo = new UserVo(id, passwd);

        UserVo authUser = userService.login(userVo);
        if (authUser == null) {
            res.sendRedirect(req.getContextPath() + "/loginform");
        }
        else {
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authUser);
            res.sendRedirect(req.getContextPath() + "/main");
        }
        return false;
    }
}
