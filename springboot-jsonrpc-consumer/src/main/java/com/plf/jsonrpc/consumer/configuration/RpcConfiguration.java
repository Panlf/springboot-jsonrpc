package com.plf.jsonrpc.consumer.configuration;

import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RpcConfiguration {

    @Bean
    @ConditionalOnProperty(value={"rpc.client.url","rpc.client.basePackage"})
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.client.url}") String url,@Value("${rpc.client.basePackage}") String basePackage){
        log.info("加载rpc配置,url:{},basePackage:{}",url,basePackage);
    	AutoJsonRpcClientProxyCreator clientProxyCreator = new AutoJsonRpcClientProxyCreator();
        try{
            clientProxyCreator.setBaseUrl(new URL(url));
        }catch (Exception e){
        	log.error("创建rpc服务地址错误",e.getMessage());
        }

        clientProxyCreator.setScanPackage(basePackage);
      
        return clientProxyCreator;
    }
}
