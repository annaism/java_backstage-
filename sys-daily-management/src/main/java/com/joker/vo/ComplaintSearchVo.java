package com.joker.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/02/23:12
 * @Description:
 */
@Data
public class ComplaintSearchVo implements Serializable {

    private Long page;   //当前页码
    private Long limit;  //当前页数量

    /**
     * 处理状态
     */
    private Integer status;

}
