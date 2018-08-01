package com.zhiyun.liferay.model;

/**
 * 
 * LiferayInvokerResult
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 上午8:36:50
 */
public class LiferayInvokerResult {

	private int id;
	
	private String jsonrpc;
	
	private Object error;
	
	private Object Result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Object getResult() {
		return Result;
	}

	public void setResult(Object result) {
		Result = result;
	}
	
	
	
}
