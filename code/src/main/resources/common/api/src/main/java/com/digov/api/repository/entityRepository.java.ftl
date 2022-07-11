package ${package.Mapper};

import ${package.Entity}.${entity};
import ${cfg.packageParam}.${entity}ListParam;
import ${cfg.packageResult}.${entity}Result;
import ${cfg.packageParam}.${entity}IdParam;
import ${cfg.packageResult}.${entity}IdResult;
import ${cfg.packageResult}.${entity}DetailResult;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * ${table.comment!} Repository 接口
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.mapperName} extends BaseMapper<${entity}> {

    /**
     * 分页获取列表
     * @param param
     * @return
     */
    List<${entity}Result> get${entity}ResultList(${entity}ListParam param);

    /**
     * 获取编辑回显数据
     * @param param
     * @return
     */
    ${entity}IdResult get${entity}IdResult(${entity}IdParam param);

    /**
     * 获取列表详情数据
     * @param param
     * @return
     */
    ${entity}DetailResult get${entity}DetailResult(${entity}IdParam param);
}
