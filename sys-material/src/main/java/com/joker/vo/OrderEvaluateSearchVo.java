package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "当前页码不能为空")
    private Long pageNum;

    @NotNull (message = "当前页数量不能为空")
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
