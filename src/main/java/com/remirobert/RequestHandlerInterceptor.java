package com.remirobert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by remirobert on 31/12/2016.
 */

@Component
public class RequestHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtils tokenUtils;
    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerInterceptor.class);
    private static final String HEADER_TOKEN = "RSS-TOKEN";
    private static final String USER_ATTRIBUTE_REQUEST = "user";
    private static final String TIME_ATTRIBUTE_REQUEST = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute(TIME_ATTRIBUTE_REQUEST, startTime);

        String token = request.getHeader(HEADER_TOKEN);
        User user = null;
        if (token != null) {
            user = tokenUtils.isTokenValid(token);
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AuthorizationRequired filter = handlerMethod.getMethod().getAnnotation(AuthorizationRequired.class);
            if (filter == null) {
                if (user != null) {
                    request.setAttribute(USER_ATTRIBUTE_REQUEST, user);
                }
                return true;
            }
        }
        if (token == null) {
            throw new AuthorizationException();
        }
        if (user == null) {
            return false;
        }
        request.setAttribute(USER_ATTRIBUTE_REQUEST, user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute(TIME_ATTRIBUTE_REQUEST);
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: End Time=" + System.currentTimeMillis());
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }
}
