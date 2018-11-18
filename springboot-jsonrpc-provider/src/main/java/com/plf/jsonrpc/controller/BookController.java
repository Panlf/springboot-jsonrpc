package com.plf.jsonrpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plf.jsonrpc.entity.Book;
import com.plf.jsonrpc.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
	@Autowired
	private BookService bookService;
	 
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public Book getBookById(@PathVariable String id){
		log.info("访问服务端,参数:{}",id);
		return bookService.findById(id);
	}
}
