package com.qf.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer status =0;
    private Date createTime =new Date();
}
