package com.bawei.seller.configuration;

import com.bawei.api.ProductRpc;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@ComponentScan(basePackageClasses = {ProductRpc.class})
@Slf4j
public class RpcConfiguration {
    Logger log = LoggerFactory.getLogger(RpcConfiguration.class);

    @Bean
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.manager.url}") String url){
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            log.error("创建rpc服务地址错误，", e);
            e.printStackTrace();
        }
        creator.setScanPackage(ProductRpc.class.getPackage().getName());
        return creator;
    }
}
