package ${cfg.packageParam};

<#list table.importPackages as pkg>
<#if pkg?contains("com.baomidou.mybatisplus.annotation") || pkg?contains("Serializable")>
<#else>
import ${pkg};
</#if>
</#list>
import ${package.Entity}.${entity};
import com.digov.api.util.common.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;

/**
 * ${table.comment!} 新增参数
 *
 * @author ${author}
 * @since ${date}
 */
<#-- 定义不需要生成的字段、隐藏的问题-------->
<#assign noCreateList=["flag", "createTime"] hiddenList=[ "modifyTime", "createUserId"]>
@Setter
@Getter
@ApiModel(value = "${entity}UpdateParam", description = "参数")
public class ${entity}UpdateParam {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#-- -----   存在字段填充设置   ----->
    <#if !noCreateList?seq_contains(field.propertyName)>
    @ApiModelProperty(value = "${field.comment?replace("\n", "-")}", example = "${field.comment?replace("\n", "-")}" <#if hiddenList?seq_contains(field.propertyName)>, hidden = true</#if>)
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
<#list table.fields as field>
<#-- -----   存在字段填充设置   ----->
    <#if !noCreateList?seq_contains(field.propertyName) && !hiddenList?seq_contains(field.propertyName)>
//        if (CommonUtil.isEmpty(${field.propertyName})) {
//            return "${field.comment?replace("\n", "-")}(不能为空)";
//        }
    </#if>
</#list>
        return null;
    }

    /**
    * 转为DB数据
    * @return
    */
    public ${entity} to${entity}(){
        ${entity} ${entity?uncap_first} = new ${entity}();

<#list table.fields as field>
<#-- -----   存在字段填充设置   ----->
    <#if !noCreateList?seq_contains(field.propertyName) && field.propertyName != "createTime" && field.propertyName != "modifyTime">
        ${entity?uncap_first}.set${field.propertyName?cap_first}(${field.propertyName});
    </#if>
</#list>
        return ${entity?uncap_first};
    }
}
