package com.digov.api.entity.param.sys.user;

import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.util.common.CommonUtil;
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
@ApiModel(value = "SysUserTempParam", description = "参数")
public class SysUserTempParam {
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
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
//        if (CommonUtil.isEmpty(id)) {
//            return "系统用户id(不能为空)";
//        }
//        if (CommonUtil.isEmpty(realName)) {
//            return "用户姓名(不能为空)";
//        }
//        if (CommonUtil.isEmpty(username)) {
//            return "用户名(不能为空)";
//        }
//        if (CommonUtil.isEmpty(password)) {
//            return "密码(不能为空)";
//        }
//        if (CommonUtil.isEmpty(email)) {
//            return "邮箱(不能为空)";
//        }
//        if (CommonUtil.isEmpty(mobile)) {
//            return "手机号(不能为空)";
//        }
//        if (CommonUtil.isEmpty(status)) {
//            return "状态 0：禁用 1：正常(不能为空)";
//        }
//        if (CommonUtil.isEmpty(createUserId)) {
//            return "创建者用户id(不能为空)";
//        }
//        if (CommonUtil.isEmpty(createTime)) {
//            return "创建时间毫秒值(不能为空)";
//        }
//        if (CommonUtil.isEmpty(modifyTime)) {
//            return "记录修改时间的毫秒数(不能为空)";
//        }
//        if (CommonUtil.isEmpty(flag)) {
//            return "是否删除 0：正常 -1：删除(不能为空)";
//        }
        return null;
    }

    /**
    * 转为DB数据
    * @return
    */
    public SysUser toSysUser(){
        SysUser sysUser = new SysUser();
        long currentTimeMS = System.currentTimeMillis();
        sysUser.setId(id);
        sysUser.setRealName(realName);
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setEmail(email);
        sysUser.setMobile(mobile);
        sysUser.setStatus(status);
        sysUser.setCreateUserId(createUserId);
        sysUser.setCreateTime(createTime);
        sysUser.setModifyTime(currentTimeMS);
        sysUser.setFlag(flag);
        return sysUser;
    }
}
