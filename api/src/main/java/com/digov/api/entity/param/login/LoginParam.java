package com.digov.api.entity.param.login;

import com.digov.api.util.common.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户登陆接口
 *
 * @author template
 * @since 2019-10-18
 */
@Setter
@Getter
@ApiModel(value = "LoginParam", description = "参数")
public class LoginParam {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true, example = "digov")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true, example = "666666")
    private String password;

    /**
     * 参数校验
     *
     * @return
     */
    public String checkParam() {
        if (CommonUtil.isEmpty(username)) {
            return "用户名不能为空";
        }
        if (CommonUtil.isEmpty(password)) {
            return "密码不能为空";
        }
        return null;
    }

}
