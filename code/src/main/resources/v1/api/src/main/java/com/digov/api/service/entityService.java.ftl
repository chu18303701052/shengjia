package ${package.Service};

import ${cfg.packageParam}.${entity}AddParam;
import ${cfg.packageParam}.${entity}IdParam;
import ${cfg.packageParam}.${entity}IdsParam;
import ${cfg.packageParam}.${entity}UpdateParam;
import ${cfg.packageParam}.${entity}ListParam;
import ${cfg.packageResult}.${entity}Result;
import ${cfg.packageResult}.${entity}IdResult;
import ${cfg.packageResult}.${entity}DetailResult;
import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName} {
    /**
     * 用户表-新增
     *
     * @param param
     * @return
     */
    R<String> add${entity}AddParam(${entity}AddParam param);

    /**
     * 用户表-获取单条记录
     *
     * @param param
     * @return
     */
    R<${entity}IdResult> get${entity}IdResult(${entity}IdParam param);

    /**
     * 用户表-信息分页
     *
     * @param param
     * @return
     */
    R<PageInfo<${entity}Result>> get${entity}ListResult(${entity}ListParam param);

    /**
     * 获取列表详情数据
     *
     * @param param
     * @return
     */
    R<${entity}DetailResult> get${entity}DetailResult(${entity}IdParam param);

    /**
     * 单条数据的更新接口
     *
     * @param param
     * @return
     */
    R<String> update(${entity}UpdateParam param);
    
    /**
    * 多条数据的删除接口
    *
    * @param param
    * @return
    */
    R<String> deleteByIds(${entity}IdsParam param);
}
