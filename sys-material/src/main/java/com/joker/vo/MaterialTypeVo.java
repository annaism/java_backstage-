package com.joker.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/10/0:18
 * @Description:
 */
@Data
public class MaterialTypeVo implements Serializable {
    /**
     * 物资类型 ID
     */
    private Long id;

    /**
     * 物资类型名称
     */
    private String name;
}
