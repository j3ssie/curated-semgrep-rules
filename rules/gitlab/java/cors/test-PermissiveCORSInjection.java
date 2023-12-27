// License: MIT (c) GitLab Inc.

package com.test.servlet.cors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

// ref: java_cors_rule-PermissiveCORSInjection
// sample get req: http://localhost:8080/ServletSample/PermissiveCORSInjection/*?tainted=*f&URL=*&URL=*&url=*
public class PermissiveCORSInjection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("attributeName",request.getParameter("tainted"));
        request.getSession().setAttribute("attributeName",request.getParameter("tainted"));
        request.getServletContext().setAttribute("attributeName",request.getParameter("tainted"));

        String paramValue = request.getParameter("tainted");
        String header = request.getHeader("tainted");
        String requestAttribute = (String) request.getAttribute("attributeName");
        String sessionAttribute = (String) request.getSession().getAttribute("attributeName");
        String contextAttribute = (String) request.getServletContext().getAttribute("attributeName");
        String pathInfo = request.getPathInfo();
        String modifiedPath = pathInfo.replaceFirst("/","");
        String queryString = request.getQueryString();

        String[] parameterValues = request.getParameterValues("URL");
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, String[]> parameterMap = request.getParameterMap();

        String valueFromPV = parameterValues[0];
        String valueFromPN = parameterNames.nextElement();
        String valueFromPM = parameterMap.get("URL")[0];

        String[] keyValuePairs = queryString.split("=");
        String lastPair = keyValuePairs[keyValuePairs.length - 1];

        if (paramValue != null) {
          // ok:java_cors_rule-PermissiveCORSInjection
          response.setHeader("X-Example-Header", paramValue);

          // ok:java_cors_rule-PermissiveCORSInjection
          response.addHeader("X-Example-Header", paramValue);

          // ok:java_cors_rule-PermissiveCORSInjection
          response.addHeader("X-Example-Header", valueFromPV);

          // ok:java_cors_rule-PermissiveCORSInjection
          response.addHeader("Some-Example-Header", valueFromPN);

          // ok:java_cors_rule-PermissiveCORSInjection
          response.addHeader("X-Example-Header", valueFromPM);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.setHeader("Access-Control-Allow-Origin", paramValue);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("Access-Control-Allow-Origin", paramValue);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("Access-Control-Allow-Origin", modifiedPath);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", paramValue);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", header);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", requestAttribute);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", sessionAttribute);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", contextAttribute);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", valueFromPV);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", valueFromPN);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", valueFromPM);

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader("access-control-allow-origin", lastPair);

          String headerName = "ACCESS-CONTROL-ALLOW-ORIGIN";

          // ruleid:java_cors_rule-PermissiveCORSInjection
          response.addHeader(headerName, paramValue);

          return;
        }

        // ok:java_cors_rule-PermissiveCORSInjection
        response.setHeader("Access-Control-Allow-Origin", "https://example.com");

        // ok:java_cors_rule-PermissiveCORSInjection
        response.addHeader("Access-Control-Allow-Origin", "https://example.com");

        // ok:java_cors_rule-PermissiveCORSInjection
        response.addHeader("Access-Control-Allow-Origin", getFromList("key"));

        // ok:java_cors_rule-PermissiveCORSInjection
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    public String getFromList(String key){
        HashMap<String, String> corsList = new HashMap<>();
        corsList.put("key", "https://example.com");

        return corsList.get(key);
    }
}