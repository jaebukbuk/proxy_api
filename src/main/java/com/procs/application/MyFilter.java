/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : MyFilter.java
 * @Date        : 2023. 12. 18.
 * @Author      : Hhome
 */
package com.procs.application;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @ClassName    : MyFilter
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2023.12.18
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2023.12.18       Hhome            최초생성
 * </pre>
 */
@Component
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);

        chain.doFilter(multiReadRequest, response);
    }
}
