package com.digov.api.entity.result.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.util.common.CommonUtil;
/**
 * 系统用户 单条记录响应结果
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysUserIdResult", description = "响应结果")
public class SysUserIdResult {
    @ApiModelProperty(value = "系统用户id", example = "系统用户id")
    private Long id;
    @ApiModelProperty(value = "用户姓名", example = "用户姓名")
    private String realName;
    @ApiModelProperty(value = "用户名", example = "用户名")
    private String username;
    @ApiModelProperty(value = "密码", example = "密码")
    private String password;
    @ApiModelProperty(value = "邮箱", example = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号", example = "手机号")
    private String mobile;
    @ApiModelProperty(value = "状态 0：禁用 1：正常", example = "状态 0：禁用 1：正常")
    private Integer status;

}
