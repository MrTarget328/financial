package com.bawei.entity.enums;

import lombok.Getter;

@Getter
public enum OrderType {

    APPLY("申购"),
    REDEEM("赎回");
    private String desc;
    OrderType(String desc) {
        this.desc = desc;
    }
}
