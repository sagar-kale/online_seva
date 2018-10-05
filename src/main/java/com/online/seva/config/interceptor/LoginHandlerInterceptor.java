package com.online.seva.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
       /* if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if ((((HandlerMethod) handler).getBean() instanceof AppController))
            return true;
        if ((((HandlerMethod) handler).getBean() instanceof PasswordResetController))
            return true;
        if ((((HandlerMethod) handler).getBean() instanceof PasswordForgotController))
            return true;

        HttpSession session = request.getSession(false);

        if (!(((HandlerMethod) handler).getBean() instanceof UserController)) {
            if (session == null || session.getAttribute("user") == null) {
                response.setContentType("application/json");
                Response response1 = new Response();
                response1.setMsgType("error");
                response1.setMessage("User not logged in or session expired,Please login");
                response.getWriter().println(new Gson().toJson(response1));

                return false;
            }
        }*/
        return true;
    }

}
