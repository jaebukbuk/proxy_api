/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : proxyService.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package com.procs.application.service.domain;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
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
public class ProxyHttpClient {
    
    GetProxyHttp getProxyHttp;
    
    public void setProxyHttp(HttpServletRequest request) {
        
        getProxyHttp.setProxyHttp(request);
    }
    
    public void getProxyHttp() throws Exception {
       
        getProxyHttp.getProxyHttp();
    }

    public void setProxyHttpConfig(int seq) {
        getProxyHttp.setProxyHttpConfig(seq);
    }
}
