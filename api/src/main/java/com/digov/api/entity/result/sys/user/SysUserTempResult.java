package com.digov.api.entity.result.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;

/**
 * 系统用户 新增参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysUserTempResult", description = "参数")
public class SysUserTempResult {
    @ApiModelProperty(value = "系统用户id", example = "系统用户id" )
    private Long id;
    @ApiModelProperty(value = "用户姓名", example = "用户姓名" )
    private String realName;
    @ApiModelProperty(value = "用户名", example = "用户名" )
    private String username;
    @ApiModelProperty(value = "密码", example = "密码" )
    private String password;
    @ApiModelProperty(value = "邮箱", example = "邮箱" )
    private String email;
    @ApiModelProperty(value = "手机号", example = "手机号" )
    private String mobile;
    @ApiModelProperty(value = "状态 0：禁用 1：正常", example = "状态 0：禁用 1：正常" )
    private Integer status;
    @ApiModelProperty(value = "创建者用户id", example = "创建者用户id" )
    private Long createUserId;
    @ApiModelProperty(value = "创建时间毫秒值", example = "创建时间毫秒值" )
    private Long createTime;
    @ApiModelProperty(value = "记录修改时间的毫秒数", example = "记录修改时间的毫秒数" )
    private Long modifyTime;
    @ApiModelProperty(value = "是否删除 0：正常 -1：删除", example = "是否删除 0：正常 -1：删除" )
    private Integer flag;

}
