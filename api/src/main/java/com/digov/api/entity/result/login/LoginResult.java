package com.digov.api.entity.result.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户登陆返回结果
 *
 * @author template
 * @since 2019-10-18
 */
@Setter
@Getter
@ApiModel(value = "LoginResult", description = "结果")
public class LoginResult {

    /**
     * token
     */
    @ApiModelProperty(value = "token", example = "fdalskfjdsflkj")
    private String sessionId;

}
