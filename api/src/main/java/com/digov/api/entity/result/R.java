package com.digov.api.entity.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by yyx on 2018/8/27.
 */
@Setter
@Getter
@ApiModel(description = "返回结果")
public class R<T> {

    @ApiModelProperty(value = "操作是否成功 true:是 false:否")
    private boolean success;
    @ApiModelProperty(value = "错误信息")
    private String errMsg;
    @ApiModelProperty(value = "请求状态码 200：成功 10001：用户未登录 10002：用户未授权", example = "200")
    private int status;

    @ApiModelProperty(value = "数据")
    private T data;

    public R(){
        success = true;
        status = 200;
    }

    public static R<String> error(String errMsg){
        return error(errMsg, 417);
    }
    public static R<String> error(String errMsg, int errCode){
        R<String> r = new R<>();
        r.setSuccess(false);
        r.setErrMsg(errMsg);
        r.setStatus(errCode);
        return r;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
