package com.bawei.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString
public class Product implements Serializable {
    @Id
    private String id;
    private String name;
    //起投金额
    private BigDecimal thresholdAmount;
    //投资步长
    private BigDecimal stepAmount;
    //锁定期
    private Integer lockTerm;
    //收益率
    private BigDecimal rewardRate;
    /**
     * @see com.bawei.entity.enums.ProductStatus
     */
    private String status;
    private String memo;
    private Date createAt;
    private String createUser;
    private Date updateAt;
    private String updateUser;
}
