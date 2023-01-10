package com.joker.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/22/16:20
 * @Description:
 */
@Data
public class OrderEvaluateSearchVo implements Serializable {

    private Long pageNum;
    private Long pageSize;

    /**
     * 商品ID
     */
    private Long materialId;

    /**
     * 评论人id
     */
    private Long userId;
}
