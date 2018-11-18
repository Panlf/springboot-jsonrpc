package com.plf.jsonrpc.consumer.service;

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
