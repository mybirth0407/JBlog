package jblog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
        HttpServletRequest req,
        HttpServletResponse res,
        Object handler) throws Exception {

        String referrer = req.getHeader("referer");
//        System.out.println(referrer);

        HttpSession httpSession = req.getSession();
        if (httpSession != null) {
            httpSession.removeAttribute("authUser");
            httpSession.invalidate();
        }

        if (referrer.contains("blog-admin")) {
            int index = referrer.indexOf("blog-admin");
            referrer = referrer.substring(0, index);
            referrer += "blog-main";
        }
        res.sendRedirect(referrer);
        return false;
    }
}
