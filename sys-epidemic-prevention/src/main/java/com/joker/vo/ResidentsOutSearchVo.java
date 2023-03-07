package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/02/07/16:18
 * @Description:
 */
@Data
public class ResidentsOutSearchVo implements Serializable {

    /**
     * 目的地
     */
    private String destination;
    /**
     * 状态(0:待审核,1:审核通过,2:审核不通过)
     */
    private Integer status;
    /**
     * 出去时间
     */
    private ArrayList<Date> outTime;


    @NotNull(message = "当前页码不能为空")
    private Long page;   //当前页码
    @NotNull(message = "当前页数量不能为空")
    private Long limit;  //当前页数量
}
