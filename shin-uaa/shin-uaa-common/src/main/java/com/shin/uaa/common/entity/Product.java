package com.shin.uaa.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Author shin
 * @Date 2024/3/31 10:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends Model<Product> {
    private static final long serialVersionUID = -6317831928469269987L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
