package com.bawei.manager.configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AutoJsonRpcServiceImplExporter 交由spring管理
 */
@Configuration
public class RpcConfiguration {

    @Bean
    public AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter (){

        return new AutoJsonRpcServiceImplExporter();
    }
}
