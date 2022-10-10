package com.minio.framework.handler;


import com.minio.common.core.AjaxResult;
import com.minio.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 小问号
 * @date 2022/8/19 14
 * description 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理参数校验异常 --Json 转换异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public AjaxResult exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.error("参数校验异常-json转换异常:", e);
        return AjaxResult.error("参数校验异常-json转换异常:" + req.getRequestURI());
    }


//    /**
//     * 权限码异常
//     */
//    @ExceptionHandler(NotPermissionException.class)
//    public <T> ServerResponseEntity<T>  handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
//        return ServerResponseEntity.showFailMsg("没有访问权限，请联系管理员授权");
//    }

    /**
     * 角色权限异常
     */
//    @ExceptionHandler(NotRoleException.class)
//    public <T> ServerResponseEntity<T>  handleNotRoleException(NotRoleException e, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        log.error("请求地址'{}',角色权限校验失败'{}'", requestURI, e.getMessage());
//        return ServerResponseEntity.showFailMsg("没有访问权限，请联系管理员授权");
//    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult  handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, message);
        return AjaxResult.error(message);
    }


    /**
     * 业务异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handlerServiceException(ServiceException e, HttpServletRequest request) {
        String message = e.getMessage();
        log.error(message);
        return AjaxResult.error(message);
    }

    /**
     * 拦截未知的运行异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult  handlerRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        log.error("请求地址'{}',发生未知异常",requestURI);
        return AjaxResult.error(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult  handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        // 所有参数异常信息
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        String message = allErrors.get(0).getDefaultMessage();
        log.info(message);
        return AjaxResult.error(message);
    }

    @ExceptionHandler(BindException.class)
    public AjaxResult handlerBindException(BindException e) {
        log.info(e.getMessage());
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }

}
