package com.joker.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/01/20:29
 * @Description: 封装查询条件
 */
@Data
@Getter
@Setter
@ToString
public class UserSearchVo implements Serializable {

    private String name;    //用户名
    @NotNull(message = "当前页码不能为空")
    private Long page;   //当前页码
    @NotNull(message = "当前页数量不能为空")
    private Long limit;     //当前页数量
    private String address; //地址
    private String level;  //权限等级

    public UserSearchVo() {
    }

    public UserSearchVo(String name, Long current, Long limit, String address,String level) {
        this.name = name;
        this.page = current;
        this.limit = limit;
        this.address = address;
        this.level = level;
    }
}
