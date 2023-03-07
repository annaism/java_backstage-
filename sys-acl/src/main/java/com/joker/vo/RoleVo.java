package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/04/17:20
 * @Description:
 */
@Data
public class RoleVo implements Serializable {

    @NotNull(message = "当前页码不能为空")
    private Long pageNum;   //当前页码
    @NotNull(message = "当前页数量不能为空")
    private Long limit;     //当前页数量
    private String roleName;
}
