package com.joker.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
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

    @NotNull(message = "当前页码不能为空")
    private Long page;   //当前页码
    @NotNull(message = "当前页数量不能为空")
    private Long limit;  //当前页数量
    @JsonFormat(pattern = "yyyy-MM-dd")
    private ArrayList<Date> date = new ArrayList<>();

}
