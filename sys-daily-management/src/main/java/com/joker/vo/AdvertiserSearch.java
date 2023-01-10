package com.joker.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/04/11:49
 * @Description:
 */
@Data
@ToString
public class AdvertiserSearch implements Serializable {

    private String begin;   //公告创建开始时间
    private String end;   //公告创建结束时间
    private Long page;   //当前页码
    private Long limit;  //当前页数量

}
