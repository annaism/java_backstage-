package com.joker.fegin;


import com.joker.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/03/15:21
 * @Description:
 */
@FeignClient(name = "sys-acl")
public interface UserFeginService {
    @RequestMapping("/api/user/check")
    R getUserInfoByAccount(@RequestParam("phone") String phone);


    @RequestMapping("/api/acl/permission/getassign")
    R toAssignPermission(@RequestBody List<Long> roleIds);

    @RequestMapping("/api/user/toalassign")
    R toAssignRole(@RequestParam(name = "userId") Long userId);


}
