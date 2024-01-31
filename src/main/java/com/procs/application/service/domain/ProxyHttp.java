/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : proxyHttp.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package com.procs.application.service.domain;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
        
    protected final String DEFAULT_PROXY_SCHEME = "https";
    protected final String DEFAULT_PROXY_HOST   = "test-openapi.lotteon.com";
    protected final int    DEFAULT_PROXY_PORT   = 443;

    protected HttpRequestBase   http;
    
    protected String scheme;
    protected String host;   
    protected int    port;   
    protected String path;   
    
    protected StringEntity strEntity;
    
    public void setProxyHttpConfig(int seq) {
        
        this.http.setConfig(RequestConfig.custom().setSocketTimeout (seq * 10000)
                 .setConnectTimeout                                 (seq * 10000)
                 .setConnectionRequestTimeout                       (seq * 10000)
                 .build());
    }
    
    public void setProxyHttpHeader(HttpServletRequest request) {
        
        LOGGER.info("* Proxy Http Header Start");
        
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {

            String headerName  =  headerNames.nextElement().trim();
            String headerValue =  request.getHeader(headerName).trim();
            
            

            if ( "content-type".equals(headerName) || "authorization".equals(headerName) || "Authorization".equals(headerName) ) {

                if ( headerValue != null ) {
                    
                    this.http.setHeader(headerName, headerValue);
                    LOGGER.info("* Proxy Http Header : headerName : " + headerName + " headerValue : " + headerValue);
                }
            }
        }
        
        LOGGER.info("* Proxy Http Header End");
    }
    
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
}
