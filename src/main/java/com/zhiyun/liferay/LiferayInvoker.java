package com.zhiyun.liferay;

import com.alibaba.fastjson.JSONObject;
import com.zhiyun.liferay.model.LiferayInvokerResult;

/**
 * 
 * LiferayInvoker
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月6日 下午5:24:44
 */
public interface LiferayInvoker {
	
	/**
	 * 
	 * @Title: getSubPath   
	 * @Description: 请求路径 
	 * @author: 孙云涛  
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	String getSubPath();
	
	/**
	 * 
	 * @Title: invoke   
	 * @Description: 远程调用 
	 * @author: 孙云涛  
	 * @param: @param method
	 * @param: @param params
	 * @param: @return      
	 * @return: LiferayInvokerResult      
	 * @throws
	 */
	LiferayInvokerResult invoke(String method , JSONObject params);
	
}
