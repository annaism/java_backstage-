package com.joker.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/02/17:54
 * @Description:
 */
@Data
public class ReportingSearchVo implements Serializable {

    /**
     * 状态--0未提交，已处理，
     */
    private Integer status;

    /**
     * 预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private ArrayList<Date> datetime;
    /**
     * 处理人员
     */
    private String worker;


    private Long page;   //当前页码
    private Long limit;  //当前页数量
}
