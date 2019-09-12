package com.bawei.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "order_t")
@ToString
public class Order {
    @Id
    private String orderId;
    private String chanId;
    private String productId;
    private String chanUserId;
    /**
     * @see com.bawei.entity.enums.OrderType
     */
    private String orderType;
    /**
     * @see com.bawei.entity.enums.OrderStatus
     */
    private String orderStatus;
    private String outerOrderId;
    private BigDecimal amount;
    private String memo;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updateAt;

}
