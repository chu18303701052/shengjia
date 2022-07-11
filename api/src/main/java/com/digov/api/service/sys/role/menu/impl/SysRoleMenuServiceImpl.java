package com.digov.api.service.sys.role.menu.impl;

import com.digov.api.entity.result.R;
import com.digov.api.repository.sys.role.menu.SysRoleMenuRepository;
import com.digov.api.service.sys.role.menu.SysRoleMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.digov.api.entity.db.sys.role.menu.SysRoleMenu;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuAddParam;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuIdParam;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuUpdateParam;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuIdResult;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuListParam;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuResult;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuDetailResult;
import com.digov.api.util.common.CommonUtil;
import java.util.List;

/**
 * 角色与菜单对应关系 服务实现类
 *
 * @author system_code
 * @since 2021-05-06
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Resource
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Override
    public R<String> addSysRoleMenuAddParam(SysRoleMenuAddParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysRoleMenu sysRoleMenu = param.toSysRoleMenu();
        sysRoleMenuRepository.insert(sysRoleMenu);
        Long insertId = sysRoleMenu.getId();
        r.setData(String.valueOf(insertId));
        return r;
    }

    @Override
    public R<SysRoleMenuIdResult> getSysRoleMenuIdResult(SysRoleMenuIdParam param) {
        R<SysRoleMenuIdResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysRoleMenuIdResult sysRoleMenuIdResult = sysRoleMenuRepository.getSysRoleMenuIdResult(param);
        r.setData(sysRoleMenuIdResult);
        return r;
    }

    @Override
    public R<PageInfo<SysRoleMenuResult>> getSysRoleMenuListResult(SysRoleMenuListParam param) {
        R<PageInfo<SysRoleMenuResult>> r = new R<>();
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

        List<SysRoleMenuResult> list = sysRoleMenuRepository.getSysRoleMenuResultList(param);
        PageInfo<SysRoleMenuResult> result = new PageInfo<>(list);
        r.setData(result);
        return r;
    }
    @Override
    public R<SysRoleMenuDetailResult> getSysRoleMenuDetailResult(SysRoleMenuIdParam param) {
        R<SysRoleMenuDetailResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysRoleMenuDetailResult result = sysRoleMenuRepository.getSysRoleMenuDetailResult(param);
        r.setData(result);
        return r;
    }

    @Override
    public R<String> update(SysRoleMenuUpdateParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        Long id = param.getId();
        SysRoleMenu sysRoleMenu = sysRoleMenuRepository.selectById(id);
        if (CommonUtil.isEmpty(sysRoleMenu)) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }

        SysRoleMenu sysRoleMenuUpdate = param.toSysRoleMenu();
        sysRoleMenuRepository.updateById(sysRoleMenuUpdate);

        return r;
    }
}
