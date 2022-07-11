package com.digov.api.entity.result.sys.role;

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
@ApiModel(value = "SysRoleTempResult", description = "参数")
public class SysRoleTempResult {
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

}
