package com.ynu.aiagri.service.dev.entity;

import com.ynu.aiagri.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author star
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Dev对象", description="")
public class Dev extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "设备材质")
    private String material;

    @ApiModelProperty(value = "设备规格")
    private String specification;

    @ApiModelProperty(value = "设备型号")
    private String type;

    @ApiModelProperty(value = "是否删除：1：删除，0：未删除")
    @TableLogic
    private Integer isDeleted;


}
