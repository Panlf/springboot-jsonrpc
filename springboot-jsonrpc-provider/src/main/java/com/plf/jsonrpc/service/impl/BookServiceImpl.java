package com.plf.jsonrpc.service.impl;

import org.springframework.stereotype.Service;

import com.plf.jsonrpc.entity.Book;
import com.plf.jsonrpc.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService{

	@Override
	public Book findById(String id) {
		log.info("访问服务端Service,参数:{}",id);
		if(id==null || id.trim().length()<=0){
			return null;
		}
		//查询数据库或者缓存
		Book book = new Book();
		book.setId(id);
		book.setName("JSON-RPC");
		book.setPrice(99.9);
		return book;
	}	
}
