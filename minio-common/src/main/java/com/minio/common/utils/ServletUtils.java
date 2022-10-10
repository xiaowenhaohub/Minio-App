package com.minio.common.utils;

import com.alibaba.fastjson2.JSON;
import com.minio.common.constant.Constants;
import com.minio.common.core.AjaxResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:16 2022/8/19
 * @ Modified By：
 */
public class ServletUtils {


    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return getRequestAttributes().getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取请求头一个值
     * @param request
     * @param name
     * @return
     */
    public static String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }
        return urlDecode(value);
    }

    /**
     * 获取请求头所有键值对
     * @param request
     * @return
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedCaseInsensitiveMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

//    /**
//     * 根据request获取请求token
//     */
//    public static String getToken(HttpServletRequest request) {
//        // 从header获取token标识
//        String token = request.getHeader(TokenConstants.AUTHENTICATION);
//        return replaceTokenPrefix(token);
//    }
//
//    /**
//     * 裁剪token前缀
//     */
//    public static String replaceTokenPrefix(String token) {
//        // 如果前端设置了令牌前缀，则裁剪掉前缀
//        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
//            token = token.replaceFirst(TokenConstants.PREFIX, "");
//        }
//        return token;
//    }
    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }


//    /**
//     * 设置webflux模型响应
//     *
//     * @param response ServerHttpResponse
//     * @param value 响应内容
//     * @return Mono<Void>
//     */
//    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value) {
//        return webFluxResponseWriter(response, HttpStatus.OK, value, ResponseEnum.SHOW_FAIL.getCode());
//    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status http状态码
     * @param code 响应状态码
     * @param value 响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value, int code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
    }


    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param contentType content-type
     * @param status http状态码
     * @param value 响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status, Object value, int code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        AjaxResult result = new  AjaxResult(code, value.toString(), null);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
