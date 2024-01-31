/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : proxyService.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package com.procs.application.service.domain;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.springframework.util.StreamUtils;

/**
 * @ClassName    : proxyService
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.01.24
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.01.24       Hhome            최초생성
 * </pre>
 */
public class ProxyPostHttp extends ProxyHttp implements GetProxyHttp {
    

    @Override
    public HttpRequestBase getProxyHttpRq() {
        
        return super.http;
    }
    
    @Override
    public void setProxyHttp(HttpServletRequest request) {
        
        super.scheme  =  request.getHeader("proxyScheme") != null ? request.getHeader("proxyScheme")                 : super.DEFAULT_PROXY_SCHEME ;
        super.host    =  request.getHeader("proxyHhost")  != null ? request.getHeader("proxyHhost")                  : super.DEFAULT_PROXY_HOST ;
        super.port    =  request.getHeader("proxyPort")   != null ? Integer.parseInt(request.getHeader("proxyPort")) : super.DEFAULT_PROXY_PORT ;
        super.path    =  request.getRequestURI();
        
        try {
            super.strEntity    =  new StringEntity(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @Override
    public void buildProxyHttp() throws Exception {
       
        super.http = new HttpPost( ( new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath(path) ).build().toString());
        
        ((HttpPost)this.http).setEntity( super.strEntity );
    }
}
