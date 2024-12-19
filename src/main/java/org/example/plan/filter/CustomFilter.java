package org.example.plan.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        /*
        눈에 보기 편하도록 로그인 필터가 실행될 때 응답 URI가 무엇인지 터미널 콘솔에 로그가 찍힐 수 있게 하였다.
         */
        log.info("request URI = {}", requestURI);
        chain.doFilter(request, response);
    }
}
