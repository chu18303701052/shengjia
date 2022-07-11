package com.digov.api.entity.result.first;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
/**
 *  单条记录参数
 *
 * @author system_code
 * @since 2022-07-11
 */
@Setter
@Getter
@ApiModel(value = "FirstDetailResult", description = "参数")
public class FirstDetailResult {
    @ApiModelProperty(value = "id", example = "id")
    private Integer id;
    @ApiModelProperty(value = "名称", example = "名称")
    private String name;
    /**
     * 返回参数处理
     */
    public void hanleResult(){

    }
}
