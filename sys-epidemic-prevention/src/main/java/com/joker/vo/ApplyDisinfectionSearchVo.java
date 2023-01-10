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
 * @Date: 2023/01/02/11:43
 * @Description:
 */
@Data
public class ApplyDisinfectionSearchVo implements Serializable {
    /**
     * 住户姓名
     */
    private String name;
    /**
     * 预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ArrayList<Date> datetime;

    /**
     * 状态--0已提交，1已安排人员，2已完成
     */
    private Integer status;

    private Long pageNum;   //当前页码
    private Long limit;  //当前页数量
}
