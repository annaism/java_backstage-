package com.joker.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joker.entity.*;
import com.joker.interceptor.LoginUserInterceptor;
import com.joker.service.*;
import com.joker.utils.JwtToken;
import com.joker.utils.JwtUtils;
import com.joker.utils.R;
import com.joker.utils.SaltUtils;
import com.joker.vo.LoginUser;
import com.joker.vo.PermissionVo;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.joker.constant.Constant.REDIS_TOKEN_CACHE_PREFIX;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/02/10:17
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class LoginConntroller {

    @Autowired
    private UserService userService;

    @Autowired
    private AclUserRoleService userRoleService;

    @Autowired
    private AclRolePermissionService rolePermissionService;

    @Autowired
    private AclPermissionService permissionService;

    @Autowired
    private AclRoleService roleService;

    @Autowired
    StringRedisTemplate redisTemplate;

////login
//    @PostMapping("login")
//    public R login() {
//
//        return R.ok().data("token","admin");
//    }
//
//    //info
//    @GetMapping("info")
//    public R info() {
//        System.out.println("又来了");
//        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//    }

    //info
    @GetMapping("info")
    public R info() {

        UserVo userVo = LoginUserInterceptor.userMainData.get();
        log.info("{{}}",userVo);

        Set<String> roles = new HashSet<>();       // 所有角色
        Set<PermissionVo> perms = new HashSet<>();       // 所有权限

        //3.获取用户所有角色ID
        QueryWrapper<AclUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userVo.getId());

        List<AclUserRole> list = userRoleService.list(wrapper);
        List<Long> roleIds = list.stream().map((userRole) -> {
            return userRole.getRoleId();
        }).collect(Collectors.toList());


        List<AclRole> aclRoles = roleService.listByIds(roleIds);
        for (AclRole role:aclRoles) {
            roles.add( role.getRoleName() );
        }

        List<Long> rolePermissionIDs = new ArrayList<>();
        //4.获取角色的权限
        for (Long roleId:roleIds) {

            QueryWrapper<AclRolePermission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id",roleId);

            List<AclRolePermission> rolePermissionslist = rolePermissionService.list(queryWrapper);

            List<Long> collect = rolePermissionslist.stream().map((rolePermission) -> {
                return rolePermission.getPermissionId();
            }).collect(Collectors.toList());

            rolePermissionIDs.addAll( collect );
        }

        List<AclPermission> aclPermissions = permissionService.listByIds(rolePermissionIDs);
        for (AclPermission permission:aclPermissions) {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtils.copyProperties(permission,permissionVo);
            perms.add( permissionVo );
        }

        log.info("{{}}",perms);
        log.info("{{}}",roles);


        return R.ok().data("permission",perms).data("roles",roles).data("name",userVo.getNickName()).data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

    }



    @PostMapping("/login")
    public R register(@RequestBody LoginUser loginUser){

        log.info("{{}}",loginUser);

        //登录账户 --- 手机号或者openid
        String account = loginUser.getAccount();
        String password = loginUser.getPassword();

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if( account.length() == 11){
            wrapper.eq("phone",account);
        }
        else {
            wrapper.eq("openid",account);
        }

        //根据用户名获取正确用户信息
        User user = userService.getOne(wrapper);
        if(user == null)
            return R.error().data("msg","无效用户，用户不存在");

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);

        //盐 + 输入的密码(注意不是用户的正确密码) + 1024次散列，作为token生成的密钥
        String salt = user.getSalt();
        Md5Hash md5Hash = new Md5Hash(password, salt, 1024);

        log.info(md5Hash.toString());

        //生成token字符串
        String token = JwtUtils.getJwtToken(userVo, md5Hash.toHex());   //toHex转换成16进制，32为字符
        JwtToken jwtToken = new JwtToken(token);        //转换成jwtToken（才可以被shiro识别）

        //拿到Subject对象
        Subject subject = SecurityUtils.getSubject();

        R res = null;
        //进行认证
        try {
            subject.login(jwtToken);
            res = R.ok().data("msg", "登录成功").data("token", token);
            subject.hasRole("test");

            //将token存入redis
            redisTemplate.opsForValue().set(REDIS_TOKEN_CACHE_PREFIX + userVo.getId().toString() ,token);
        } catch (UnknownAccountException e){
            res = R.error().data("msg","无效用户，用户不存在");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            res = R.error().data("msg","密码输入错误");
            e.printStackTrace();
        } catch (ExpiredCredentialsException e){
            res = R.error().data("msg","token过期，请重新登录");
            e.printStackTrace();
        } finally {
            return res;
        }
    }


}
