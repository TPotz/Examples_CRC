package com.example.web.demo.modeli;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.json.Json;

import java.lang.reflect.Array;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElasticData {
    public String url;
    public String[] params;
    public String method_name;
    public String user_name;
    public String cookies;
    public String ip;
    public String timestamp;
    public int id;
    public String hostt;
    public String user_agent;
    public String connection;
    public String cache_control;
    public String accept_encoding;
    public String accept_language;

    public ElasticData(String url, String[] params, String method_name, String user_name, String cookies, String ip, String timestamp, int id, String hostt, String userAgent, String connection, String cacheControl, String encoding, String language) {
        this.url = url;
        this.params = params;
        this.method_name = method_name;
        this.user_name = user_name;
        this.cookies = cookies;
        this.ip = ip;
        this.timestamp = timestamp;
        this.id = id;
        this.hostt = hostt;
        this.user_agent = userAgent;
        this.connection = connection;
        this.cache_control = cacheControl;
        this.accept_encoding = encoding;
        this.accept_language = language;
    }

    public ElasticData(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method_name;
    }

    public void setMethod(String method) {
        this.method_name = method;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public String getCookies() {return cookies;}

    public void setCookies(String cookies) {this.cookies = cookies;}

    public String getIp() {return ip;}

    public void setIp(String ip) {this.ip = ip;}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostt() {
        return hostt;
    }

    public void setHostt(String hostt) {
        this.hostt = hostt;
    }

    public String getUserAgent() {
        return user_agent;
    }

    public void setUserAgent(String userAgent) {
        this.user_agent = userAgent;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCacheControl() {
        return cache_control;
    }

    public void setCacheControl(String cacheControl) {
        this.cache_control = cacheControl;
    }

    public String getEncoding() {
        return accept_encoding;
    }

    public void setEncoding(String encoding) {
        this.accept_encoding = encoding;
    }

    public String getLanguage() {
        return accept_language;
    }

    public void setLanguage(String language) {
        this.accept_language = language;
    }

    @Override
    public String toString() {
        return "ElasticData{" +
                "url='" + url + '\'' +
                ", params=" + Arrays.toString(params) +
                ", method_name='" + method_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", cookies='" + cookies + '\'' +
                ", ip='" + ip + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", id=" + id +
                ", hostt='" + hostt + '\'' +
                ", userAgent='" + user_agent + '\'' +
                ", connection='" + connection + '\'' +
                ", cacheControl='" + cache_control + '\'' +
                ", encoding='" + accept_encoding + '\'' +
                ", language='" + accept_language + '\'' +
                '}';
    }
}
