package com.joker.realm;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joker.entity.*;
import com.joker.service.*;
import com.joker.utils.JwtToken;
import com.joker.utils.JwtUtils;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/03/16:55
 * @Description:
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

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



    /**
     * 限定这个realm只能处理JwtToken（不加的话会报错）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权(授权部分这里就省略了，先把重心放在认证上)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("==========执行授权");

        // 1. 获取已认证的用户数据
        User user = (User) principals.getPrimaryPrincipal();

        // 2. 根据用户数据获取用户的权限信息（所有角色，所有权限）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();       // 所有角色
        Set<String> perms = new HashSet<>();       // 所有权限

        //3.获取用户所有角色ID
        QueryWrapper<AclUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());

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
            perms.add( permission.getPath() );
        }

        info.setStringPermissions(perms);
        info.setRoles(roles);

        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {

        String token = (String) auth.getCredentials();  //JwtToken中重写了这个方法了
        UserVo userVo = JwtUtils.getUser(token);   // 获得useraccout

        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        if(userVo == null)
            throw new UnknownAccountException();

        //根据用户名，查询数据库获取到正确的用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty( userVo.getPhone() )){
            wrapper.eq("phone",userVo.getPhone());
        }
        if(!StringUtils.isEmpty( userVo.getOpenid() )){
            wrapper.or().eq("openid",userVo.getOpenid());
        }
        User user = userService.getOne(wrapper);

        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        if(user == null)
            throw new UnknownAccountException();

        //密码错误(这里获取到password，就是3件套处理后的保存到数据库中的凭证，作为密钥)
        if (! JwtUtils.verifyToken(token, userVo, user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        //token过期
        if(JwtUtils.isExpire(token)){
            throw new ExpiredCredentialsException();
        }

        return new SimpleAuthenticationInfo(user, token, getName());
    }

}
