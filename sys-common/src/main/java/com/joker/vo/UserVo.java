package com.joker.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/03/23:33
 * @Description:
 */
@Data
@ToString
public class UserVo implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

}
