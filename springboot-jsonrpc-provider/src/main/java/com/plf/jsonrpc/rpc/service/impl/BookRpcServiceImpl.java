package com.plf.jsonrpc.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.plf.jsonrpc.entity.Book;
import com.plf.jsonrpc.rpc.service.BookRpcService;
import com.plf.jsonrpc.service.BookService;

import lombok.extern.slf4j.Slf4j;

@AutoJsonRpcServiceImpl
@Service
@Slf4j
public class BookRpcServiceImpl implements BookRpcService{

	@Autowired
	private BookService bookService;
	
	@Override
	public Book findById(String id) {
		log.info("访问服务端JSON-RPC,参数:{}",id);
		return bookService.findById(id);
	}

}
