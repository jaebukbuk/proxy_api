/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : proxyServiceImpl.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package com.procs.application.service.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.procs.application.service.ProxyService;
import com.procs.application.service.domain.ProxyCloseableHttpClient;
import com.procs.application.service.domain.ProxyGetHttp;
import com.procs.application.service.domain.ProxyHttpClient;
import com.procs.application.service.domain.ProxyPostHttp;

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
@Service
public class ProxyServiceImpl implements ProxyService {
    
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Override
    public String doPost(HttpServletRequest request) {

        String rtn = "";
        
        try {
            
            ProxyHttpClient proxyHttpClient  = new ProxyHttpClient();
            proxyHttpClient.setClientMethod     ( new ProxyPostHttp() );
            proxyHttpClient.setProxyHttpConfig  ( request, 5          );
           
            CloseableHttpClient client = ( new ProxyCloseableHttpClient() ).getCloseableHttpClient();
            
            if( client != null ) {
                
                CloseableHttpResponse response = client.execute( proxyHttpClient.getProxyHttpRq() );
                rtn = new String(EntityUtils.toString(response.getEntity(), "UTF-8"));
                
                LOGGER.info("* RS Info - StatusCode : " + String.valueOf(response.getStatusLine().getStatusCode()) );
                LOGGER.info("* RS Info - httpBody   : " +  rtn );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            rtn = "요청처리중 오류가 발생했습니다.";
        }
            
        return rtn;
    }

    @Override
    public String doGet(HttpServletRequest request) {
        
        String rtn = "";
        
        try {
            
            ProxyHttpClient proxyHttpClient  = new ProxyHttpClient();
            proxyHttpClient.setClientMethod     ( new ProxyGetHttp()  );
            proxyHttpClient.setProxyHttpConfig  ( request, 5          );
           
            CloseableHttpClient client = ( new ProxyCloseableHttpClient() ).getCloseableHttpClient();
            
            if( client != null ) {
                
                CloseableHttpResponse response = client.execute( proxyHttpClient.getProxyHttpRq() );
                rtn = new String(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            rtn = "요청처리중 오류가 발생했습니다.";
        }
        
        return rtn;
    }

    @Override
    public String doPut(HttpServletRequest request) {
        return "미사용 Method 입니다.";
    }
}
