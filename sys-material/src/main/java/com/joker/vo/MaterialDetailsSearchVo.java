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
 * @Date: 2022/12/20/22:57
 * @Description:
 */
@Data
public class MaterialDetailsSearchVo implements Serializable {

    private Long pageNum;
    private Long pageSize;
    private String name;
    private String productNumber;
    private String typeName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private ArrayList<Date> datetime;
    private String useType;
}
