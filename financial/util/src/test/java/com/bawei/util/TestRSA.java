package com.bawei.util;

import org.junit.Test;

/**
 * 测试加签验签
 */

public class TestRSA {

    private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3iXipbmLKBecbXB5KVTnsKGqp\n" +
            "TtpiYmKc+l5qWh61wI/zRD6zk0ENUsymRo12qlOFy4xwaY/9fAx4dfkQwl5aOM7L\n" +
            "GCe7QpljzlEhzGd6evw7wBNwSlunzVJK/bZJY0zaR3fEWnwXc4ly8ElvOfQZ1NmG\n" +
            "PHZZEOQOzpPKvds+qQIDAQAB";

    static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALeJeKluYsoF5xtc\n" +
            "HkpVOewoaqlO2mJiYpz6XmpaHrXAj/NEPrOTQQ1SzKZGjXaqU4XLjHBpj/18DHh1\n" +
            "+RDCXlo4zssYJ7tCmWPOUSHMZ3p6/DvAE3BKW6fNUkr9tkljTNpHd8RafBdziXLw\n" +
            "SW859BnU2YY8dlkQ5A7Ok8q92z6pAgMBAAECgYEAl7nbuCV7IYuTrDiRCd4+zg3z\n" +
            "bxeyzZaL8z2/A6iQrqwL1I1cUFOxeXQznlnA5JKWDYtiBCQgVutP292bEtG78O9A\n" +
            "y/ZHkNKwh9C2FN8p7o0rAXl1min2KCpnm3yRPlEw9yhoEJaLV/5lals5F7+AkXlU\n" +
            "Kj1XMfyI9qgtYUXKlKECQQDiTCBDzJfthtCehHvRo1Y0Ecsu3Jm+uPlNLC0nduXz\n" +
            "Muv0GxzMK81VJovikv2wdM+KRQwZb3pZrG53/Rkj8zQVAkEAz6CLIzFhmW4K1Nv/\n" +
            "ZvMjuURRmxNE0GAra/4OnvzyzB9faUfqcUXpF+f87ZWK1TPL3YPzVIfEJu9CbzJ3\n" +
            "VZmhRQJAcWt0NGcDsqICR6DhKiYyW3CbpIYebK/rC5nbm88E24qnJja8jTxa8pWa\n" +
            "QNopWlqBdqy6rrjn2rAIwgzRn+oSLQJABG0CqkRiNTgQSQtLfXFPYKKhLrIRdHOo\n" +
            "WUv4GdGAh3NfwEnFKJb56gWK8bGPXQ4WmWk3paSjPMU64E3uF0gIFQJBALrT7mA6\n" +
            "y8atF/+sFivJkf5eqk+stOF5gZgxV7bSe6V5wBKgIDIRINQfZtIc+af+QEiQJW/e\n" +
            "a51FNom1Zgwl6rA=";

    @Test
    public void signTest(){
        String text = "{\"amount\":1000,\"chanId\":\"ID渠道\",\"chanUserId\":\"001\",\"createAt\":\"2019-12-364 11:40:07\",\"orderId\":\"10001\",\"productId\":\"105\"}";
        String sign = RSAUtil.sign(text, privateKey);
        System.err.println(sign);
        boolean verify = RSAUtil.verify(text, sign, publicKey);
        System.err.println(verify);
    }
}
