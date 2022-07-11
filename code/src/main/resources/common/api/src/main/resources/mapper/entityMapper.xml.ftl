<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
    <#-- 列表中不需要生成的字段-------->
    <#assign noCreateListByGetList=["createUserId", "flag"]>

    <select id="get${entity}ResultList"
            parameterType="${cfg.packageParam}.${entity}ListParam"
            resultType="${cfg.packageResult}.${entity}Result">
        select
    <#list table.fields as field>
        <#if !noCreateListByGetList?seq_contains(field.propertyName)>
            <#if field.propertyName != "id">
                ,a1.${field.name} `${field.propertyName}`
                <#else>
                a1.${field.name} `${field.propertyName}`
            </#if>
        </#if>
    </#list>
        from ${table.name} a1
        where a1.flag=0
    </select>

    <#assign noCreateListByGetDetailResult=["createUserId", "createTime", "modifyTime", "flag"]>
    <resultMap id="${entity}DetailResultMap" type="${cfg.packageResult}.${entity}DetailResult">
<#list table.fields as field>
    <#if !noCreateListByGetDetailResult?seq_contains(field.propertyName)>
    <#if field.propertyName != "id">
        <result column="${field.propertyName}" property="${field.propertyName}"></result>
    <#else>
        <id column="${field.propertyName}" property="${field.propertyName}"></id>
    </#if>
    </#if>
</#list>
    </resultMap>

    <select id="get${entity}DetailResult"
            parameterType="${cfg.packageParam}.${entity}IdParam"
            resultMap="${entity}DetailResultMap">
        select
        <#list table.fields as field>
        <#if !noCreateListByGetDetailResult?seq_contains(field.propertyName)>
            <#if field.propertyName != "id">
                ,a1.${field.name} `${field.propertyName}`
            <#else>
                a1.${field.name} `${field.propertyName}`
            </#if>
        </#if>
        </#list>
        from ${table.name} a1
        where a1.flag=0
        and a1.id=${r'#{id}'}
    </select>

    <#--不需要生成的字段-->
    <#assign noCreateListByGetIdResult=["createUserId", "createTime", "modifyTime", "flag"]>
    <select id="get${entity}IdResult"
            parameterType="${cfg.packageParam}.${entity}IdParam"
            resultType="${cfg.packageResult}.${entity}IdResult">
        select
        <#list table.fields as field>
            <#if !noCreateListByGetIdResult?seq_contains(field.propertyName)>
            <#if field.propertyName != "id">
                ,a1.${field.name} `${field.propertyName}`
            <#else>
                a1.${field.name} `${field.propertyName}`
            </#if>
            </#if>
        </#list>
        from ${table.name} a1
        where a1.flag=0
        and a1.id=${r'#{id}'}
    </select>

</mapper>
