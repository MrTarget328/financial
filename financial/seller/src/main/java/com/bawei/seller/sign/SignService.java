package com.bawei.seller.sign;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SignService {

    static Map<String,String> PUBLIC_KEYS = new HashMap<>();

    static {
        PUBLIC_KEYS.put("10000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3iXipbmLKBecbXB5KVTnsKGqp\n" +
                "TtpiYmKc+l5qWh61wI/zRD6zk0ENUsymRo12qlOFy4xwaY/9fAx4dfkQwl5aOM7L\n" +
                "GCe7QpljzlEhzGd6evw7wBNwSlunzVJK/bZJY0zaR3fEWnwXc4ly8ElvOfQZ1NmG\n" +
                "PHZZEOQOzpPKvds+qQIDAQAB");
    }
    /**
     * 根据授权编号authId获取公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId){
        return PUBLIC_KEYS.get(authId);
    }
}
