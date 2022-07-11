package ${package.Entity};

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
<#list table.importPackages as pkg>
<#if pkg?contains("com.baomidou.mybatisplus.annotation") || pkg?contains("Serializable")>
<#else>
import ${pkg};
</#if>
</#list>

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
 */
@Setter
@Getter
@TableName(value = "${table.name}")
public class ${entity} {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment?js_string!}
     */
    </#if>
    <#if field.propertyName == "id">
    @TableId(value = "id",type = IdType.AUTO)
    </#if>
    <#if field.propertyName =='createTime' >
    @TableField(fill = FieldFill.INSERT)
    </#if>
    <#if field.propertyName =='modifyTime' >
    @TableField(fill = FieldFill.INSERT_UPDATE)
    </#if>
    <#-- -----   存在字段填充设置   ----->
    private ${field.propertyType} ${field.propertyName};
</#list>
}
