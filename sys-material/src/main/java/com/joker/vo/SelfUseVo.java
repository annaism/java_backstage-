package com.joker.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "请选择需要删除的数据")
    private Long pageNum;
    @NotNull(message = "当前页数量不能为空")
    private Long pageSize;
    private String materialName;
    private String typeName;
}
