package com.plf.jsonrpc.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plf.jsonrpc.consumer.service.BookConsumerService;
import com.plf.jsonrpc.entity.Book;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
	@Autowired
	private BookConsumerService bookConsumerService;
	 
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public Book getBookById(@PathVariable String id){
		log.info("访问消费端,参数:{}",id);
		return bookConsumerService.getBook(id);
	}
}
