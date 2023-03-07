package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/02/23:00
 * @Description:
 */
@Data
public class LiveChatSearchVo implements Serializable {
    @NotNull(message = "当前页码不能为空")
    private Long page;   //当前页码
    @NotNull(message = "当前页数量不能为空")
    private Long limit;  //当前页数量

    private Date timeBegin;

    private Date timeEnd;
}
