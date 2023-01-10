package com.joker.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/21/15:25
 * @Description:
 */
@Data
public class MaterialCheckVo implements Serializable {

    /**
     * 物资申领记录ID
     */
    private Long id;

    /**
     * 审核状态0已提交，1审核通过，2审核未通过，3已取消
     */
    private Integer status;

    /**
     * 审核回复
     */
    private String apply;
}
