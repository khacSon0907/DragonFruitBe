package com.example.demo.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

public class CookieUtil {

    public static void addCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge,
            String path
    ) {

        ResponseCookie cookie = ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(false) // DEV → true khi production
                .path(path)
                .maxAge(maxAge)
                .sameSite("Lax")// 🔥 thêm dòng này
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }
    public static String getCookie(
            HttpServletRequest request,
            String name
    ) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public static void clearCookie(
            HttpServletResponse response,
            String name,
            String path
    ) {
        ResponseCookie cookie = ResponseCookie.from(name, "")
                .httpOnly(true)
                .secure(false)
                .path(path)
                .maxAge(0)
                .sameSite("Lax")// 🔥 thêm dòng này
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }
}