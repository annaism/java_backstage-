package com.joker.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/02/21:58
 * @Description:
 */
@Data
public class VolunteerSearchVo implements Serializable {

    private Long page;   //当前页码
    private Long limit;  //当前页数量
    /**
     * 志愿者姓名
     */
    private String name;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 申请志愿时间段
     */
    private ArrayList<Date> datetime;

}
