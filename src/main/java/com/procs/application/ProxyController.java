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
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.procs.application.service.ProxyService;

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

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProxyService    proxyService;
    
    public ProxyController ( ProxyService proxyService ) {
        this.proxyService  = proxyService;
    }
    
    @RequestMapping(value = "/**")
    public String proxy(HttpServletRequest request, @RequestBody String requestBody) {

        LOGGER.info("******************************Proxy Start******************************");
        
        String rtn = "";
        
        try {
            
            LOGGER.info("* RQ Info - RequestURI  : " + request.getRequestURI());
            LOGGER.info("* RQ Info - RequestURI  : " + request.getRequestURI() + (request.getQueryString() != null ? request.getQueryString() : ""));
            LOGGER.info("* RQ Body - InputStream : " + StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8));

            if ( HttpMethod.GET.matches(request.getMethod()) ) {
    
                rtn = new String( proxyService.doGet(request)  );
    
            } else if ( HttpMethod.POST.matches(request.getMethod()) ) {
    
                rtn = new String( proxyService.doPost(request) );
    
            } else if ( HttpMethod.PUT.matches(request.getMethod()) ) {
    
                rtn = new String( proxyService.doPut(request)  );
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        LOGGER.info("******************************Proxy End******************************");
        
        return rtn;
    }
}
