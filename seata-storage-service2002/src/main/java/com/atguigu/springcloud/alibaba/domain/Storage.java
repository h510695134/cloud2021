package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wanghh
 * @date 2021-04-7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage implements Serializable {

    private Long id;

    private Long productId;

    private Integer total;

    private Integer used;

    private Integer residue;
}
