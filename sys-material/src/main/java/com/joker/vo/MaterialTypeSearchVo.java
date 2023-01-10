package com.joker.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/22/11:34
 * @Description:
 */
@Data
public class MaterialTypeSearchVo implements Serializable {

    /**
     * 物资类型名称
     */
    private String name;

    private Long pageNum;
    private Long pageSize;
}
