package com.minio.framework.web.model;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:02 2022/8/25
 * @ Modified By：
 */
public class OperationLog {

    /** 操作模块 */
    private String title;

    /** 请求方法 */
    private String method;

    /** 操作人员学号 */
    private String account;

    /** 请求url */
    private String operationUrl;

    /** 错误消息 */
    private String errorMsg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOperationUrl() {
        return operationUrl;
    }

    public void setOperationUrl(String operationUrl) {
        this.operationUrl = operationUrl;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return
                "模块='" + title + '\'' +
                ", 请求方法='" + method + '\'' +
                ", 操作人员学号='" + account + '\'' +
                ", 请求url='" + operationUrl + '\'' +
                ", 错误消息='" + errorMsg ;

    }
}
