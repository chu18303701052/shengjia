package com.digov.api.entity.db.sys.user.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户与角色对应关系
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@TableName(value = "sys_user_role")
public class SysUserRole {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 是否删除 0：正常 -1：删除
     */
    private Integer flag;
}
