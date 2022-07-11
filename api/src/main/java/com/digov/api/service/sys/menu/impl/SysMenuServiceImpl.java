package com.digov.api.service.sys.menu.impl;

import com.digov.api.entity.result.R;
import com.digov.api.repository.sys.menu.SysMenuRepository;
import com.digov.api.service.sys.menu.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.digov.api.entity.db.sys.menu.SysMenu;
import com.digov.api.entity.param.sys.menu.SysMenuAddParam;
import com.digov.api.entity.param.sys.menu.SysMenuIdParam;
import com.digov.api.entity.param.sys.menu.SysMenuUpdateParam;
import com.digov.api.entity.result.sys.menu.SysMenuIdResult;
import com.digov.api.entity.param.sys.menu.SysMenuListParam;
import com.digov.api.entity.result.sys.menu.SysMenuResult;
import com.digov.api.entity.result.sys.menu.SysMenuDetailResult;
import com.digov.api.util.common.CommonUtil;
import java.util.List;

/**
 * 菜单管理 服务实现类
 *
 * @author system_code
 * @since 2021-05-06
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuRepository sysMenuRepository;

    @Override
    public R<String> addSysMenuAddParam(SysMenuAddParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysMenu sysMenu = param.toSysMenu();
        sysMenuRepository.insert(sysMenu);
        Long insertId = sysMenu.getId();
        r.setData(String.valueOf(insertId));
        return r;
    }

    @Override
    public R<SysMenuIdResult> getSysMenuIdResult(SysMenuIdParam param) {
        R<SysMenuIdResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysMenuIdResult sysMenuIdResult = sysMenuRepository.getSysMenuIdResult(param);
        r.setData(sysMenuIdResult);
        return r;
    }

    @Override
    public R<PageInfo<SysMenuResult>> getSysMenuListResult(SysMenuListParam param) {
        R<PageInfo<SysMenuResult>> r = new R<>();
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

        List<SysMenuResult> list = sysMenuRepository.getSysMenuResultList(param);
        PageInfo<SysMenuResult> result = new PageInfo<>(list);
        r.setData(result);
        return r;
    }
    @Override
    public R<SysMenuDetailResult> getSysMenuDetailResult(SysMenuIdParam param) {
        R<SysMenuDetailResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysMenuDetailResult result = sysMenuRepository.getSysMenuDetailResult(param);
        r.setData(result);
        return r;
    }

    @Override
    public R<String> update(SysMenuUpdateParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        Long id = param.getId();
        SysMenu sysMenu = sysMenuRepository.selectById(id);
        if (CommonUtil.isEmpty(sysMenu)) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }

        SysMenu sysMenuUpdate = param.toSysMenu();
        sysMenuRepository.updateById(sysMenuUpdate);

        return r;
    }
}
