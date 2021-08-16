package com.atguigu.spring.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.bouncycastle.asn1.ocsp.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guyao
 */

@Component
public class MyZuulFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);


    @Override
    public String filterType() {
        // 返回当前过滤器的类型。决定当前过滤在什么时候执行
        // pre表示再目标微服务前执行
        String filterType = "pre";
        return filterType;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否要过滤
     * @return true 要过滤，false 不过滤
     */
    @Override
    public boolean shouldFilter() {
        // 先获取RequestContext对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 再获取request对象
        HttpServletRequest request = currentContext.getRequest();
        String parameter = request.getParameter("signal");
        String hello = "hello";

        return hello.equals(parameter);
    }

    /**
     * 要过滤
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        logger.info("当前请求进行了过滤，run()方法执行了");
        // Current implementation ignores it.
        // 实现后官方会忽略方法返回值，故返回null
        return null;
    }
}
