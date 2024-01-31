/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : proxyHttp.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package com.procs.application.service.domain;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.springframework.util.StreamUtils;

/**
 * @ClassName    : proxyHttp
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.01.24
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.01.24       Hhome            최초생성
 * </pre>
 */
public class ProxyHttp {
    
    protected final String DEFAULT_PROXY_SCHEME = "https";
    protected final String DEFAULT_PROXY_HOST   = "test-openapi.lotteon.com";
    protected final int    DEFAULT_PROXY_PORT   = 443;

    HttpRequestBase   http;
    
    String scheme;
    String host;   
    int    port;   
    String path;   
    
    StringEntity strEntity;
    
    /**
     * @return the strEntity
     */
    public StringEntity getStrEntity() {
        return strEntity;
    }
    /**
     * @param strEntity the strEntity to set
     */
    public void setStrEntity(StringEntity strEntity) {
        this.strEntity = strEntity;
    }
    /**
     * @return the http
     */
    public HttpRequestBase getHttp() {
        return http;
    }
    /**
     * @param http the http to set
     */
    public void setHttp(HttpRequestBase http) {
        this.http = http;
    }
    /**
     * @return the scheme
     */
    public String getScheme() {
        return scheme;
    }
    /**
     * @param scheme the scheme to set
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }
    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }
    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }
    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @return the dEFAULT_PROXY_SCHEME
     */
    public String getDEFAULT_PROXY_SCHEME() {
        return DEFAULT_PROXY_SCHEME;
    }
    /**
     * @return the dEFAULT_PROXY_HOST
     */
    public String getDEFAULT_PROXY_HOST() {
        return DEFAULT_PROXY_HOST;
    }
    /**
     * @return the dEFAULT_PROXY_PORT
     */
    public int getDEFAULT_PROXY_PORT() {
        return DEFAULT_PROXY_PORT;
    }
    
    /*
    public HttpRequestBase getHttpRequestBase() {
        return this.http;
    }
    
    public void setProxyHeaders(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {

            String headerName  =  headerNames.nextElement().trim();
            String headerValue =  request.getHeader(headerName).trim();

            if ( "content-type".equals(headerNames) || "authorization".equals(headerNames) || "Authorization".equals(headerNames) ) {

                if (headerValue != null) {
                    this.http.setHeader(headerName, headerValue);
                    System.out.println(" headerName : " + headerName + " headerValue : " + headerValue);
                }
            }
        }
    }
    
    public ProxyHttp getHttpPost(HttpServletRequest request, int seq) throws URISyntaxException, UnsupportedEncodingException, IOException {
        
        String scheme  =  request.getHeader("proxyScheme") != null ? request.getHeader("proxyScheme")                 : DEFAULT_PROXY_SCHEME ;
        String host    =  request.getHeader("proxyHhost")  != null ? request.getHeader("proxyHhost")                  : DEFAULT_PROXY_HOST ;
        int    port    =  request.getHeader("proxyPort")   != null ? Integer.parseInt(request.getHeader("proxyPort")) : DEFAULT_PROXY_PORT ;
        String path    =  request.getRequestURI();
        
        this.http = new HttpPost( ( new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath(path) ).build().toString());
        
        ((HttpPost)this.http).setEntity( new StringEntity(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8)));
        
        this.http.setConfig(RequestConfig.custom().setSocketTimeout            (seq * 10000)
                                                  .setConnectTimeout           (seq * 10000)
                                                  .setConnectionRequestTimeout (seq * 10000)
                                                  .build());
        
        return this;
    }
    
    public ProxyHttp getHttpGet(HttpServletRequest request, int seq) throws URISyntaxException, UnsupportedEncodingException, IOException {
        
        String scheme  =  request.getHeader("proxyScheme") != null ? request.getHeader("proxyScheme")                 : DEFAULT_PROXY_SCHEME ;
        String host    =  request.getHeader("proxyHhost")  != null ? request.getHeader("proxyHhost")                  : DEFAULT_PROXY_HOST ;
        int    port    =  request.getHeader("proxyPort")   != null ? Integer.parseInt(request.getHeader("proxyPort")) : DEFAULT_PROXY_PORT ;
        String path    =  request.getRequestURI();
        
        this.http = new HttpGet( ( new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath(path) ).build().toString());
        
        this.http.setConfig(RequestConfig.custom().setSocketTimeout            (seq * 10000)
                                                  .setConnectTimeout           (seq * 10000)
                                                  .setConnectionRequestTimeout (seq * 10000)
                                                  .build());
        
        return this;
    }
    */
}
