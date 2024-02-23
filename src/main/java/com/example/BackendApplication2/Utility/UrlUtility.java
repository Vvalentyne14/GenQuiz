package com.example.BackendApplication2.Utility;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtility {
    public static String getApplicationUrl(HttpServletRequest request) {
        String appUrl = request.getRequestURI().toString();
        return appUrl.replace(request.getServletPath(), "");
    }
}
