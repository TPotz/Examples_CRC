package com.example.web.demo;

import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.modeli.Intercept;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Interceptor implements HandlerInterceptor {
    public String lastPage = "";
//    @Override
//    public boolean preHandle
//            (HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//
//        System.out.println("Pre Handle method is Calling");
//        System.out.println(request.getMethod());
//        Enumeration enumeration = request.getParameterNames();
//        Map<String, Object> modelMap = new HashMap<>();
//        while (enumeration.hasMoreElements()) {
//            String parameterName = enumeration.nextElement().toString();
//            modelMap.put(parameterName, request.getParameter(parameterName));
//        }
//        System.out.println(modelMap);
//        return true;
//    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response,
//                           Object handler, ModelAndView modelAndView) throws Exception {
//
//        System.out.println("Post Handle method is Calling");
//        System.out.println(request.getMethod());
//        Enumeration enumeration = request.getParameterNames();
//        Map<String, Object> modelMap = new HashMap<>();
//        while (enumeration.hasMoreElements()) {
//            String parameterName = enumeration.nextElement().toString();
//            modelMap.put(parameterName, request.getParameter(parameterName));
//        }
//        System.out.println(modelMap);
//    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        String name="";
        String page="";
        String parametri="";
        String method="";
        if (reqImportant(request.getRequestURI())) {///provjeri je li link requesta bitan, nebitne ne rješava (/img,/css,/js itd.)
            try {
                name=request.getUserPrincipal().getName();
            } catch (Exception e) {}

            page=request.getRequestURI();
            method=request.getMethod();
            Enumeration enumeration = request.getParameterNames();
            Map<String, Object> modelMap = new HashMap<>();
            while (enumeration.hasMoreElements()) {
                String parameterName = enumeration.nextElement().toString();
                modelMap.put(parameterName, request.getParameter(parameterName));
            }
            ///ovdje dobiva puno request info
            String host = request.getHeader("Host");
            String userAgent = request.getHeader("User-Agent");
            String connection = request.getHeader("Connection");
            String cacheControl = request.getHeader("Cache-Control");
            String encoding = request.getHeader("Accept-Encoding");
            String language = request.getHeader("Accept-Language");
            if (!modelMap.containsKey("order[0][column]")) {///uzve sve parametre samo ako nije tablica, ako je onda samo nekoliko bitnih)
                parametri = modelMap.toString();
            } else {
                parametri = String.format("{orderColumn=%s, orderDirection=%s, search=%s, start=%s, length=%s}",
                        modelMap.get("order[0][column]"),modelMap.get("order[0][dir]"),modelMap.get("search[value]"),
                        modelMap.get("start"),modelMap.get("length"));
                }
            TecajDao tec = new TecajDao();
            Map<String, String> cook = new HashMap<>();
            if (!page.equals("/login") ) {///ove provjere su dodane zbog ponavljanja login requesta, nekad čak 6 puta, a samo jedan klik na /login stranicu

                try {///mora try jer nekad nema cookies
                    for (Cookie c : request.getCookies()){
                        if (!c.getName().equals("sid")){//sid je ogromna verzija session id-a i nepotreban je jer postosjio JSESSION coockie
                        cook.put(c.getName(),c.getValue());}
                    }} catch (Exception e) {
                    System.out.println("No cookies!");
                }
                Intercept cept = new Intercept(page,parametri,method,name,cook.toString(),request.getRemoteAddr(),host,userAgent,connection,cacheControl,encoding,language);
                tec.logIntercept(cept);
                System.out.println(cept);
                lastPage = page;
            } else {if (!lastPage.equals(page)) {
                try {
                    for (Cookie c : request.getCookies()){
                        if (!c.getName().equals("sid")){
                        cook.put(c.getName(),c.getValue());}
                    }} catch (Exception e) {
                    System.out.println("No cookies!");
                }
                Intercept cept = new Intercept(page,parametri,method,name,cook.toString(),request.getRemoteAddr(),host,userAgent,connection,cacheControl,encoding,language);
                tec.logIntercept(cept);
                System.out.println(cept);
                lastPage = page;
            }}
        }

    }

    public boolean reqImportant(String page){//ovdje su iznimke za koje ne pravi intercept objekt
        boolean result=true;
        if (page.contains("/js/") ||page.contains("/favicon") ||page.contains("/css") ||page.contains("/images/") ||page.contains("/img/") ||page.contains("/error")){
            result=false;
        }
        return result;
    }
}
