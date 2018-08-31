//package com.demo.sleuth.sleuth;
//
//
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.UUID;
//import java.util.regex.Pattern;
//
//@Component
//@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
//class CustomTraceFilter extends GenericFilterBean {
//
//    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}$");
//    private static final String TAG_NAME = "X-B3-TraceId";
//
//    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//
//        String existingTrace = (String) httpRequest.getHeader(TAG_NAME);
//
//        if (StringUtils.isEmpty(existingTrace) || (!StringUtils.isEmpty(existingTrace) && !isCorrectTraceIdFormat(existingTrace))) {
//
//            String customTraceId = UUID.randomUUID().toString();
//            LOGGER.info("Generating custom trace Id: {}", customTraceId);
//
//            httpRequest.setAttribute(TAG_NAME, customTraceId);
//            MDC.put(TAG_NAME, customTraceId);
//        }
//
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private boolean isCorrectTraceIdFormat(String traceId) {
//        return pattern.matcher(traceId).matches();
//    }
//
//}
