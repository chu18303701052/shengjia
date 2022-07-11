package com.digov.api.entity.result.sys.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
/**
 * 角色 单条记录响应结果
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysRoleResult", description = "结果")
public class SysRoleResult {
    @ApiModelProperty(value = "角色id", example = "角色id")
    private Long id;
    @ApiModelProperty(value = "角色名称", example = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间毫秒值", example = "创建时间毫秒值")
    private Long createTime;
    @ApiModelProperty(value = "修改时间的毫秒值", example = "修改时间的毫秒值")
    private Long modifyTime;
}
