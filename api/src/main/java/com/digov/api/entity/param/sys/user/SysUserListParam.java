package com.digov.api.entity.param.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.util.common.CommonUtil;
/**
 * 系统用户 分页参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysUserListParam", description = "参数")
public class SysUserListParam {
    @ApiModelProperty(value = "系统用户id", required = true, example = "1")
    private Long id;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", required = true, example = "10")
    private Integer pageSize;

    /**
     * 排序字段: 格式：字段 + (+/-)
     */
    @ApiModelProperty(value = "排序字段: 格式：字段 + (+/-)", hidden = true)
    private String sort;

    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
        if (CommonUtil.isEmpty(id)) {
            return "系统用户id 不能为空";
        }
        //处理排序逻辑
        if (CommonUtil.isEmpty(sort)) {
            sort = "id-";
        }
        sort = sort.replace("-", " desc,").replace("+", " asc,");
        sort = sort.substring(0, sort.length() - 1);

        return null;
    }

}
