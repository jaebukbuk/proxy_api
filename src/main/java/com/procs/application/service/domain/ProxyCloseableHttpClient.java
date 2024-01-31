/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : Snippet.java
 * @Date        : 2024. 2. 1.
 * @Author      : Hhome
 */
package com.procs.application.service.domain;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

/**
 * @ClassName    : Snippet
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.02.01
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.02.01       Hhome            최초생성
 * </pre>
 */
public class ProxyCloseableHttpClient {

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

