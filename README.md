## springboot-jsonrpc
本项目是基于SpringBoot和Json-Rpc(jsonrpc4j.jar)的测试案例。


> JSON-RPC是一个无状态且轻量级的远程过程调用(RPC)协议。 本规范主要定义了一些数据结构及其相关的处理规则。它允许运行在基于socket,http等诸多不同消息传输环境的同一进程中。其使用JSON（RFC 4627）作为数据格式。

更多可以访问[JSON-RPC 2.0 规范](http://wiki.geekdream.com/Specification/json-rpc_2.0.html)，理解相关概念和规范格式。

### jsonrpc4j的调用格式
#### 1、RPC调用接口
 ```
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.plf.jsonrpc.entity.Book;

@JsonRpcService("rpc/books")
public interface BookRpcService {
	public Book findById(String id);
}

 ```

#### 2、配置服务端
 ```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;


@Configuration
public class RpcConfiguration {

    @Bean
    public AutoJsonRpcServiceImplExporter rpcServiceImplExporter(){
        return new AutoJsonRpcServiceImplExporter();
    }
}
 ```

#### 3、配置客户端
 ```
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
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.client.url}") String url,
    @Value("${rpc.client.basePackage}") String basePackage){
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
 ```
#### 4、调用
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plf.jsonrpc.entity.Book;
import com.plf.jsonrpc.rpc.service.BookRpcService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookConsumerService {

	@Autowired
	private BookRpcService bookRpcService;
	
	public Book getBook(String id) {
		log.info("访问消费端Service,参数:{}",id);
		return bookRpcService.findById(id);
	}
}
 ```
