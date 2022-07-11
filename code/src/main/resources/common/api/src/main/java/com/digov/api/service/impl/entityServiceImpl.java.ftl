package ${package.ServiceImpl};

import com.digov.api.entity.result.R;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import ${package.Entity}.${entity};
import ${cfg.packageParam}.${entity}AddParam;
import ${cfg.packageParam}.${entity}IdParam;
import ${cfg.packageParam}.${entity}IdsParam;
import ${cfg.packageParam}.${entity}UpdateParam;
import ${cfg.packageResult}.${entity}IdResult;
import ${cfg.packageParam}.${entity}ListParam;
import ${cfg.packageResult}.${entity}Result;
import ${cfg.packageResult}.${entity}DetailResult;
import com.digov.api.util.common.CommonUtil;
import java.util.List;

<#--获取主键类型-->
<#assign idDataType="Long"/>
<#list table.fields as field>
    <#if field.propertyName =='id' >
        <#assign idDataType=field.propertyType/>
    </#if>
</#list>
/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public R<String> add${entity}AddParam(${entity}AddParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        ${entity} ${entity?uncap_first} = param.to${entity}();
        ${table.mapperName?uncap_first}.insert(${entity?uncap_first});
        ${idDataType} insertId = ${entity?uncap_first}.getId();
        r.setData(String.valueOf(insertId));
        return r;
    }

    @Override
    public R<${entity}IdResult> get${entity}IdResult(${entity}IdParam param) {
        R<${entity}IdResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        ${entity}IdResult ${entity?uncap_first}IdResult = ${table.mapperName?uncap_first}.get${entity}IdResult(param);
        r.setData(${entity?uncap_first}IdResult);
        return r;
    }

    @Override
    public R<PageInfo<${entity}Result>> get${entity}ListResult(${entity}ListParam param) {
        R<PageInfo<${entity}Result>> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }

        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();
        String sort = param.getSort();
        //分页功能
        PageHelper.startPage(pageNum, pageSize, sort);

        List<${entity}Result> list = ${table.mapperName?uncap_first}.get${entity}ResultList(param);
        PageInfo<${entity}Result> result = new PageInfo<>(list);
        r.setData(result);
        return r;
    }
    @Override
    public R<${entity}DetailResult> get${entity}DetailResult(${entity}IdParam param) {
        R<${entity}DetailResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        ${entity}DetailResult result = ${table.mapperName?uncap_first}.get${entity}DetailResult(param);
        r.setData(result);
        return r;
    }

    @Override
    public R<String> update(${entity}UpdateParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        Long id = param.getId();
        ${entity} ${entity?uncap_first} = ${table.mapperName?uncap_first}.selectById(id);
        if (CommonUtil.isEmpty(${entity?uncap_first})) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }

        ${entity} ${entity?uncap_first}Update = param.to${entity}();
        ${table.mapperName?uncap_first}.updateById(${entity?uncap_first}Update);

        return r;
    }
                                                
    @Override
    public R<String> deleteByIds(${entity}IdsParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        int result = ${table.mapperName?uncap_first}.deleteBatchIds(param.getIds());
        if (0 == result) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }
        return r;
    }
}
