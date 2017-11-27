package com.freetax.common;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * controller返回的实体：封装了返回前台的json数据
 *
 * @Author shuxf
 * @Date 2017/10/27 14:26
 */
@ApiModel(value = "后台返回前端的统一JSON格式", description = "JSON格式定义")
public class ResponseVo implements Serializable {

    private static final long serialVersionUID = 4273005680206220420L;
    /**
     * 返回结果码 200成功，400失败，401没有权限
     */
    @ApiModelProperty(value = "返回结果码,200成功，400失败，401没有权限", required = true)
    private int code = 200;
    /**
     * 操作结果信息
     */
    @ApiModelProperty(value = "返回信息", notes = "异常返回信息")
    private String msg = "";
    /**
     * 返回的数据
     */
    @ApiModelProperty(value = "返回数据", required = true)
    private Object data;

    public ResponseVo() {

    }

    public ResponseVo(String message) {
        this.msg = message;
    }

    public ResponseVo(Object data){
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseVo(int code, String message, String msg, Object data, int msgCode) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        if (data == null) {
            data = new Object[] {};
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
