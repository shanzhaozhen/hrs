package com.hbjs.hrscommon.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtils {

    public static String getCookie(String cookieName) {
        HttpServletRequest httpServletRequest = HttpServletUtils.getHttpServletRequest();
        Cookie[] cookies =  httpServletRequest.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void setCookie(String cookieName, String value, Integer maxAge) {
        HttpServletResponse httpServletResponse = HttpServletUtils.getHttpServletResponse();
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        if (maxAge == null) {
            // 默认cookies有效期为一天
            cookie.setMaxAge(24*60*60);
        } else {
            cookie.setMaxAge(maxAge);
        }
        httpServletResponse.addCookie(cookie);
    }

    public static void removeCookie(String cookieName) {
        CookiesUtils.setCookie(cookieName, null, 0);
    }

}
