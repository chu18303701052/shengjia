package ${cfg.packageParam};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.util.common.CommonUtil;
/**
 * ${table.comment!} 分页参数
 *
 * @author ${author}
 * @since ${date}
 */
@Setter
@Getter
@ApiModel(value = "${entity}ListParam", description = "参数")
public class ${entity}ListParam {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#-- -----   存在字段填充设置   ----->
    <#if field.propertyName == "id">
    @ApiModelProperty(value = "${field.comment?replace("\n", "-")}", required = true, example = "1")
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
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
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#-- -----   存在字段填充设置   ----->
    <#if field.propertyName == "id">
        if (CommonUtil.isEmpty(${field.propertyName})) {
            return "${field.comment?replace("\n", "-")} 不能为空";
        }
    </#if>
</#list>
        //处理排序逻辑
        if (CommonUtil.isEmpty(sort)) {
            sort = "id-";
        }
        sort = sort.replace("-", " desc,").replace("+", " asc,");
        sort = sort.substring(0, sort.length() - 1);

        return null;
    }

}
