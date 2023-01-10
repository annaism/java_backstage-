package com.joker.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/21/11:09
 * @Description:
 */
@Data
public class SelfUseVo implements Serializable {

    private Long pageNum;
    private Long pageSize;
    private String materialName;
    private String typeName;
}
