package com.example.web.demo.modeli;

public class Intercept {
    public String url;
    public String params;
    public String method;
    public String username;
    public String cookies;
    public String ip;
    public String host;
    public String userAgent;
    public String connection;
    public String cacheControl;
    public String encoding;
    public String language;


    public Intercept(String url, String params, String method, String username, String cookies, String ip, String host, String userAgent, String connection, String cacheControl, String encoding, String language) {
        this.url = url;
        this.params = params;
        this.method = method;
        this.username = username;
        this.cookies = cookies;
        this.ip = ip;
        this.host = host;
        this.userAgent = userAgent;
        this.connection = connection;
        this.cacheControl = cacheControl;
        this.encoding = encoding;
        this.language = language;
    }

    public Intercept(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCookies() {return cookies;}

    public void setCookies(String cookies) {this.cookies = cookies;}

    public String getIp() {return ip;}

    public void setIp(String ip) {this.ip = ip;}

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Intercept{" +
                "url='" + url + '\'' +
                ", params='" + params + '\'' +
                ", method='" + method + '\'' +
                ", username='" + username + '\'' +
                ", cookies='" + cookies + '\'' +
                ", ip='" + ip + '\'' +
                ", host='" + host + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", connection='" + connection + '\'' +
                ", cacheControl='" + cacheControl + '\'' +
                ", encoding='" + encoding + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
