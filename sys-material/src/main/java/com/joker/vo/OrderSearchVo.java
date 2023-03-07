package com.joker.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/22/15:05
 * @Description:
 */
@Data
public class OrderSearchVo implements Serializable {
    @NotNull(message = "当前页码不能为空")
    private Long pageNum;

    @NotNull (message = "当前页数量不能为空")
    private Long pageSize;

    /**
     * 订单编号
     */
    private String number;

    /**
     * 状态0已下单，1未付款，2已付款，3未发货，4已发货 5已收货 6已取消7申请退货8同意退货9退货发出10收到退货11未退款12已退款
     */
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private ArrayList<Date> datetime;
}
