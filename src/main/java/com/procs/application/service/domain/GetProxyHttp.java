/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : proxyService.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package com.procs.application.service.domain;

import javax.servlet.http.HttpServletRequest;

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
public interface GetProxyHttp {
    
    public void setProxyHttp(HttpServletRequest request);
    public void getProxyHttp() throws Exception;
    public void setProxyHttpConfig(int seq);
}
