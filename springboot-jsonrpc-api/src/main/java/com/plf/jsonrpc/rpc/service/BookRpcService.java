package com.plf.jsonrpc.rpc.service;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.plf.jsonrpc.entity.Book;

@JsonRpcService("rpc/books")
public interface BookRpcService {
	public Book findById(String id);
}
