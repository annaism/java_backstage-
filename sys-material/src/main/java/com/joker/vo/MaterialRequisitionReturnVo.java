package com.joker.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/21/12:08
 * @Description:
 */
@Data
public class MaterialRequisitionReturnVo implements Serializable {

    private Long id;
    /**
     * 物资ID
     */
    private Long materialId;

    /**
     * 物资名称
     */
    private String materialName;

    /**
     * 物资类型
     */
    private String typeName;

    /**
     * 申领人姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 居住地址
     */
    private String address;

    /**
     * 申请原因
     */
    private String reason;

    /**
     * 申请日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date materialTime;

    /**
     * 审核状态0已提交，1审核通过，2审核未通过，3已完成，4已取消
     */
    private Integer status;

    /**
     * 审核回复
     */
    private String apply;
}
