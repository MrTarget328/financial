package com.bawei.seller.params;

import com.bawei.seller.sign.SignText;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderParam implements SignText {
    private String orderId;
    private String chanId;
    private String productId;
    private String chanUserId;
    private String outerOrderId;
    private BigDecimal amount;
    private String memo;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt;
}
