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
 * @Date: 2022/12/20/23:23
 * @Description:
 */
@Data
public class MaterialDetailsVo implements Serializable {

    /**
     * 物资 ID
     */
    private Long id;

    /**
     * 物资名称
     */
    private String name;

    /**
     * 物资售价
     */
    private Double outPrice;

    /**
     * 物资类型名称
     */
    private String typeName;

    /**
     * 物资有效期(月)
     */
    private Integer lifespan;

    /**
     * 物资生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date produceTime;

    /**
     * 物资现存数量（除去社区自用）
     */
    private Integer nowQuantity;

    /**
     * 资使用类型---0可申领，1可购买，2社区自用，3全部（包含0，1，2）
     */
    private Integer useType;

    /**
     * 用户可申领数量
     */
    private Integer applyCount;


    /**
     * 社区自用物资锁定数量
     */
    private Integer communityLock;

    ArrayList<PicUrl> picUrls = new ArrayList<>();
}
