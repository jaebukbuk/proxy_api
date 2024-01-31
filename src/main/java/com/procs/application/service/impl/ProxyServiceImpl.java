/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : proxyServiceImpl.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package com.procs.application.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StreamUtils;
import com.procs.application.ProxyHttp;
import com.procs.application.service.ProxyService;

/**
 * @ClassName    : proxyServiceImpl
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.01.24
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.01.24       Hhome            최초생성
 * </pre>
 */
public class ProxyServiceImpl implements ProxyService {
    
    @Override
    public void doPost(HttpServletRequest request) {

        String rtn = "";
        
        try {
            
            ProxyHttp         proxyHttp  = new ProxyHttp();
            proxyHttp = proxyHttp.getHttpPost(request, 5);
            
            // 헤더 작업
            proxyHttp.setProxyHeaders(request);
           
            CloseableHttpClient client = this.getCloseableHttpClient();
            
            if( client != null ) {
                
                CloseableHttpResponse response = client.execute(http);
                rtn = new String(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
            

        } catch (Exception e) {
            
        }
    }

    @Override
    public void doGet() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doPut() {
        // TODO Auto-generated method stub
        
    }
    
    public void setProxyHeaders(HttpServletRequest request, HttpRequestBase http) {
        
        // 헤더 작업
        Enumeration<String> headerNames = request.getHeaderNames();
        
        while( headerNames.hasMoreElements() ) {
            
            String headerName = headerNames.nextElement().trim();
            String headerValue = request.getHeader(headerName).trim();
            
           if("content-type".equals(headerNames) || "authorization".equals(headerNames) || "Authorization".equals(headerNames)) {
                if(headerValue != null) {
                    http.setHeader(headerName, headerValue);
                    System.out.println(" headerName : "+ headerName + " headerValue : " + headerValue);
                }
           }
        }   
        
    }
    
    public CloseableHttpClient getCloseableHttpClient() {
       
        CloseableHttpClient client;
        
        try {
            
            HttpClientBuilder   httpClientBuilder = HttpClients.custom();
            SSLContext          sslcontext        = SSLContexts.custom()
                                                   .useProtocol("TLSv1.2")
                                                   .loadTrustMaterial(null, new TrustStrategy() {
                                                       @Override
                                                       public boolean isTrusted(
                                                               X509Certificate[] paramArrayOfX509Certificate,
                                                               String paramString
                                                       ) throws CertificateException {
                                                           return true;
                                                       }
                                                   })
                                                   .build();

            httpClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier())
                             .setSSLContext(sslcontext);
            
            client = httpClientBuilder.build();
            
        } catch (Exception e) {
            
            client = null;
        }
        
        return client;
    }
}
