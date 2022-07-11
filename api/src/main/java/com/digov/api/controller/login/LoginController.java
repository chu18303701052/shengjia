package com.digov.api.controller.login;

import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.entity.param.login.LoginParam;
import com.digov.api.entity.param.sys.user.SysUserIdParam;
import com.digov.api.entity.result.R;
import com.digov.api.entity.result.login.LoginResult;
import com.digov.api.entity.result.sys.user.SysUserDetailResult;
import com.digov.api.service.sys.user.SysUserService;
import com.digov.api.util.common.CommonUtil;
import com.digov.api.util.shiro.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/pc/login")
@Slf4j
public class LoginController {

    @Resource
    private SysUserService sysUserService;


    @ApiOperation(value = "账号密码登陆(无需短信验证码)", notes = "用户登录成功后，每个请求头中带入 Authorization ={{sessionId}}")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<LoginResult> login(@RequestBody LoginParam loginParam) {
        R<LoginResult> r = new R<>();
        LoginResult loginResult = new LoginResult();
        String checkParam = loginParam.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            r.setData(loginResult);
            return r;
        }
        // 获取用户名和密码
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        try {
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();


//            password = new Sha1Hash(password).toHex().toUpperCase();

            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()) {
                SysUser sysUser = (SysUser) subject.getPrincipal();
                Session session = subject.getSession();
                //设置会话失效前的空闲时间 先设置为 1 天
                session.setTimeout(1 * 24 * 60 * 60 * 1000L);
                String sessionIdStr = session.getId().toString();
                loginResult.setSessionId(sessionIdStr);
                r.setData(loginResult);
                return r;
            }else{
                r.setSuccess(false);
                r.setErrMsg("用户登陆失败");
                return r;
            }
        }catch (IncorrectCredentialsException | UnknownAccountException e){
            log.error(CommonUtil.getExceptionInfo(e));
            r.setSuccess(false);
            r.setErrMsg("账号或密码错误");
            return r;
        }catch (DisabledAccountException e){
            log.error(CommonUtil.getExceptionInfo(e));
            r.setSuccess(false);
            r.setErrMsg(e.getMessage());
            return r;
        }catch (Exception e){
            e.printStackTrace();
            log.error(CommonUtil.getExceptionInfo(e));
            r.setSuccess(false);
            r.setErrMsg("系统错误");
            return r;
        }
    }

    /**
     * 未授权，shiro 应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public R<String> unauth() {
        R<String> stringR = new R<>();
        stringR.setSuccess(false);
        stringR.setStatus(10002);
        stringR.setErrMsg("用户未授权");
        return stringR;
    }

    /**
     * 未登录，shiro 应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/defaultlogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public R<String> defaultlogin() {
        R<String> stringR = new R<>();
        stringR.setSuccess(false);
        stringR.setStatus(10001);
        stringR.setErrMsg("用户未登录");
        return stringR;
    }


    /**
     * 用户退出登陆
     * @return
     */
    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/loginout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> loginOut() {
        R<String> stringR = new R<>();
        SecurityUtils.getSubject().logout();
        return stringR;
    }

    @ApiOperation(value = "获取登录信息")
    @RequestMapping(value = "/getloginresult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<SysUserDetailResult> getloginresult() {

        SysUser sysUser = ShiroUtil.getSysUser();
        if (CommonUtil.isEmpty(sysUser)) {
            R<SysUserDetailResult> r = new R<>();
            r.setSuccess(false);
            r.setStatus(10001);
            r.setErrMsg("用户未登录");
            return r;
        }

        Long sysUserId = sysUser.getId();
        SysUserIdParam param = new SysUserIdParam();
        param.setId(sysUserId);

        R<SysUserDetailResult> r = sysUserService.getSysUserDetailResult(param);
        return r;
    }

}
