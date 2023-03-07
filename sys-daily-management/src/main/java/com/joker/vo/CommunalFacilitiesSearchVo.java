package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/02/15/13:15
 * @Description:
 */
@Data
public class CommunalFacilitiesSearchVo implements Serializable {

    @NotNull(message = "当前页码不能为空")
    private Long page;   //当前页码
    @NotNull(message = "当前页数量不能为空")
    private Long limit;  //当前页数量

    private Integer status;

    /**
     * 设施名称
     */
    private String name;

    /**
     * 设施位置
     */
    private String address;
}
