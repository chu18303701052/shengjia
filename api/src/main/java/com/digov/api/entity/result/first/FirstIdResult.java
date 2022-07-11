package com.digov.api.entity.result.first;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.entity.db.first.First;
import com.digov.api.util.common.CommonUtil;
/**
 *  单条记录响应结果
 *
 * @author system_code
 * @since 2022-07-11
 */
@Setter
@Getter
@ApiModel(value = "FirstIdResult", description = "响应结果")
public class FirstIdResult {
    @ApiModelProperty(value = "id", example = "id")
    private Integer id;
    @ApiModelProperty(value = "名称", example = "名称")
    private String name;

}
