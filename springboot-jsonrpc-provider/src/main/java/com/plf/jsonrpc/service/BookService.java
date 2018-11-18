package com.plf.jsonrpc.service;

import com.plf.jsonrpc.entity.Book;

public interface BookService {
	public Book findById(String id);
}