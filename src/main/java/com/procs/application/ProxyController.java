/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : ProcController.java
 * @Date        : 2023. 12. 15.
 * @Author      : Hhome
 */
package com.procs.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.SSLContext;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
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
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName    : ProcController
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2023.12.15
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2023.12.15       Hhome            최초생성
 * </pre>
 */
@WebServlet
@RestController
public class ProxyController {
    
    @RequestMapping(value="/**")
    public String proxy(HttpServletRequest request, @RequestBody String requestBody ) throws IOException, URISyntaxException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
     
    System.out.println("RQ URL : " + request.getRequestURI() + (request.getQueryString() != null ? request.getQueryString() : "" ));
    
    URIBuilder uriBuilder = new URIBuilder().setScheme("https").setHost("test-openapi.lotteon.com").setPort(443).setPath(request.getRequestURI());
    
    System.out.println("변경 URL : " + uriBuilder.build().toString());
    
    HttpRequestBase http = null;
    
    if ( HttpMethod.GET.matches(request.getMethod()) ) {
        
        uriBuilder.setQuery(request.getQueryString());
        http = new HttpGet(URLDecoder.decode(uriBuilder.build().toString(), "UTF-8") );
        
        System.out.println("rq 메소드 : " + request.getMethod() + "- 변경 get : " + URLDecoder.decode(uriBuilder.build().toString(), "UTF-8"));
    
    } else if ( HttpMethod.POST.matches(request.getMethod()) ) {
        
        if( request.getQueryString() != null) uriBuilder.setQuery(request.getQueryString());
        
        http = new HttpPost(uriBuilder.build().toString());
        ((HttpPost)http).setEntity( new StringEntity( StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8) ) );
        
        System.out.println("rq 메소드  : " + request.getMethod() + "변경 post : " + uriBuilder.build().toString());
        System.out.println("rq Body : " + StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8) );
        
    } else if ( HttpMethod.PUT.matches(request.getMethod()) ) {
        
        http = new HttpPut(uriBuilder.build().toString());
        
    } else if ( HttpMethod.PATCH.matches(request.getMethod()) ) {
        
        http = new HttpPatch(uriBuilder.build().toString());
    }
    
    
     http.setConfig(RequestConfig.custom()
         .setSocketTimeout   (5 * 10000)
         .setConnectTimeout  (5 * 10000)
         .setConnectionRequestTimeout(5 * 10000)
         .build());
     
     Enumeration<String> headerNames = request.getHeaderNames();
     
     while(headerNames.hasMoreElements()) {
         
         String headerName = headerNames.nextElement().trim();
         String headerValue = request.getHeader(headerName).trim();
      
         
         
        if("content-type".equals(headerNames) || "authorization".equals(headerNames) || "Authorization".equals(headerNames)) {
             if(headerValue != null) {
                 http.setHeader(headerName, headerValue);
                 System.out.println(" headerName : "+ headerName + " headerValue : " + headerValue);
             }
        }
     }
     http.setHeader("content-type" , "application/json;charset=UTF-8");
     http.setHeader("authorization", "Bearer 5d5b2cb498f3d20001665f4e86eac657a1774c9c803180eac4de0f54");
     
     System.out.println("****************************");
    
     
     HttpClientBuilder   httpClientBuilder   = HttpClients.custom();
     SSLContext          sslcontext          = SSLContexts.custom()
                                                          .useProtocol("TLSv1.2")
                                                          .loadTrustMaterial(null, new TrustStrategy() {
                                                              @Override
                                                              public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
                                                                 return true;
                                                              }
                                                          }).build();
                                                          
    httpClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier()).setSSLContext(sslcontext);
    
     CloseableHttpClient      client      = httpClientBuilder.build();
     CloseableHttpResponse    response    = client.execute(http);
     
     
     System.out.println(response.getStatusLine().getStatusCode());
     System.out.println(response.getStatusLine().getReasonPhrase());
     System.out.println(response.getEntity().getContentEncoding());
     String str = new String(EntityUtils.toString(response.getEntity(),"UTF-8"));
     System.out.println(str);
     
     System.out.println("****************************");
     
     return str;   
    }
}
