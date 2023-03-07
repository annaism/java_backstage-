package com.joker.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/20/22:57
 * @Description:
 */
@Data
public class MaterialDetailsSearchVo implements Serializable {
    @NotNull(message = "当前页码不能为空")
    private Long pageNum;
    @NotNull (message = "当前页数量不能为空")
    private Long pageSize;
    private String name;
    private String productNumber;
    private String typeName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private ArrayList<Date> datetime;
    private String useType;
}
