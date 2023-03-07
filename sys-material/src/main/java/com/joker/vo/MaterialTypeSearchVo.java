package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/22/11:34
 * @Description:
 */
@Data
public class MaterialTypeSearchVo implements Serializable {

    /**
     * 物资类型名称
     */
    private String name;
    @NotNull(message = "当前页码不能为空")
    private Long pageNum;
    @NotNull (message = "当前页数量不能为空")
    private Long pageSize;
}
