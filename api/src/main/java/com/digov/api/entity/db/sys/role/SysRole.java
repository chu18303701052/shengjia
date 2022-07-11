package com.digov.api.entity.db.sys.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@TableName(value = "sys_role")
public class SysRole {

    /**
     * 角色id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间毫秒值
     */
    private Long createTime;

    /**
     * 修改时间的毫秒值
     */
    private Long modifyTime;

    /**
     * 是否删除 0：正常 -1：删除
     */
    private Integer flag;
}
