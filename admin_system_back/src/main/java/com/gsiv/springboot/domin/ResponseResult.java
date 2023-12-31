package com.gsiv.springboot.domin;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.domin
 * @className: ResponseResult
 * @author: 65151
 * @description: 前后端交互实体类
 * @date: 2023-07-01 10:24
 * @version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    private T extra_data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(T extra_data) {
        this.extra_data = extra_data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data, T extra_data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.extra_data = extra_data;
    }

}
