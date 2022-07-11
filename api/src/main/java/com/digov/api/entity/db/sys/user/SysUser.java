package com.digov.api.entity.db.sys.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 系统用户
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@TableName(value = "sys_user")
public class SysUser implements Serializable {

    /**
     * 系统用户id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;

    /**
     * 创建者用户id
     */
    private Long createUserId;

    /**
     * 创建时间毫秒值
     */
    private Long createTime;

    /**
     * 记录修改时间的毫秒数
     */
    private Long modifyTime;

    /**
     * 是否删除 0：正常 -1：删除
     */
    private Integer flag;
}
