package com.neusoft.ehr.sys;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/27.
 * 乱码过滤器
 */
public class EncodingFilter implements Filter {
    private String enc;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 解决请求与响应的乱码问题
        req.setCharacterEncoding(enc);//处理post中文乱码
        //response.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset="+enc);
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
        enc=config.getInitParameter("encoding");
    }

}
