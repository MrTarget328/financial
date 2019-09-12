package com.bawei.seller.sign;

import com.bawei.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
@Aspect
public class SignAop {

    @Autowired
    private SignService signService;

    //限制为 任何返回值 com.bawei.seller.controller目录下的任何类的任何方法的任意参数并且参数限制为(authId,sign,text,以及任何参数)
    @Before(value = "execution(* com.bawei.seller.controller.*.*(..)) && args(authId,sign,text,..)")
    public void verify(String authId,String sign,SignText text){
        System.err.println(text.toText()+"|*********************");
        log.info("拦截请求，authId:{},sign:{},text:{}", authId,sign,text);
        String publicKey = signService.getPublicKey(authId);
        log.info("拦截请求，获取到的公钥:{}", publicKey);
        Assert.isTrue(RSAUtil.verify(text.toText(),sign,publicKey), "验签失败");
    }
}
