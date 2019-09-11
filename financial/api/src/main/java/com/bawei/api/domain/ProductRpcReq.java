package com.bawei.api.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ProductRpcReq {

    private List<String> idList;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List<String> statusList;

}
