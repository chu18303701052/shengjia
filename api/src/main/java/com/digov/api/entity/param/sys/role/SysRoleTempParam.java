package com.digov.api.entity.param.sys.role;

import com.digov.api.entity.db.sys.role.SysRole;
import com.digov.api.util.common.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;

/**
 * 角色 新增参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysRoleTempParam", description = "参数")
public class SysRoleTempParam {
    @ApiModelProperty(value = "角色id", example = "角色id" )
    private Long id;
    @ApiModelProperty(value = "角色名称", example = "角色名称" )
    private String roleName;
    @ApiModelProperty(value = "备注", example = "备注" )
    private String remark;
    @ApiModelProperty(value = "创建者ID", example = "创建者ID" )
    private Long createUserId;
    @ApiModelProperty(value = "创建时间毫秒值", example = "创建时间毫秒值" )
    private Long createTime;
    @ApiModelProperty(value = "修改时间的毫秒值", example = "修改时间的毫秒值" )
    private Long modifyTime;
    @ApiModelProperty(value = "是否删除 0：正常 -1：删除", example = "是否删除 0：正常 -1：删除" )
    private Integer flag;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
//        if (CommonUtil.isEmpty(id)) {
//            return "角色id(不能为空)";
//        }
//        if (CommonUtil.isEmpty(roleName)) {
//            return "角色名称(不能为空)";
//        }
//        if (CommonUtil.isEmpty(remark)) {
//            return "备注(不能为空)";
//        }
//        if (CommonUtil.isEmpty(createUserId)) {
//            return "创建者ID(不能为空)";
//        }
//        if (CommonUtil.isEmpty(createTime)) {
//            return "创建时间毫秒值(不能为空)";
//        }
//        if (CommonUtil.isEmpty(modifyTime)) {
//            return "修改时间的毫秒值(不能为空)";
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
    public SysRole toSysRole(){
        SysRole sysRole = new SysRole();
        long currentTimeMS = System.currentTimeMillis();
        sysRole.setId(id);
        sysRole.setRoleName(roleName);
        sysRole.setRemark(remark);
        sysRole.setCreateUserId(createUserId);
        sysRole.setCreateTime(createTime);
        sysRole.setModifyTime(currentTimeMS);
        sysRole.setFlag(flag);
        return sysRole;
    }
}
