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
 * @Date: 2022/12/21/11:58
 * @Description:
 */
@Data
public class MaterialRequisitionSearchVo implements Serializable {
    private Long pageNum;
    private Long pageSize;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private ArrayList<Date> datetime;
    /**
     * 审核状态0已提交，1审核通过，2审核未通过，3已完成，4已取消
     */
    private Integer status;
    /**
     * 物资名称
     */
    private String materialName;

}
