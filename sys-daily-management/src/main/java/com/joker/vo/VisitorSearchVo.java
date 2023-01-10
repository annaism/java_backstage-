package com.joker.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/02/22:46
 * @Description:
 */
@Data
public class VisitorSearchVo implements Serializable {

    private Long page;   //当前页码
    private Long limit;  //当前页数量

    /**
     * 申请志愿时间段
     */
    private Date timeBegin;

    private Date timeEnd;
}
