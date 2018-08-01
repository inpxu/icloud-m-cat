package com.zhiyun.liferay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * 
 * BaseLiferayInvoker
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 上午8:39:29
 */
public abstract class BaseLiferayInvoker implements LiferayInvoker, InitializingBean {

	private static OkHttpClient okHttpClient = new OkHttpClient();

	@Value("${liferay.jsonws.bathUrl}")
	private String bathUrl;

	@Value("${liferay.jsonws.auth}")
	private String auth;

	@Value("${liferay.jsonws.pwd}")
	private String pwd;

	private String requestHeadAuthorization;

	private static final String BASE_AUTHORIZATION = "Basic ";

	private static final String JSON_RPC = "2.0";

	private static final int ID = 1;

	private static final Logger logger = LoggerFactory.getLogger(BaseLiferayInvoker.class);

	@Override
    public void afterPropertiesSet() throws Exception {
		this.requestHeadAuthorization = BASE_AUTHORIZATION
				+ Base64.encodeBase64String((auth + ":" + pwd).getBytes());
	}

	@Override
	public LiferayInvokerResult invoke(String method, JSONObject params) {

		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),
				getRequestJsonString(method, params));

		Request request = new Request.Builder().url(bathUrl + "/" + getSubPath())
				.addHeader("Authorization", this.requestHeadAuthorization).post(requestBody).build();
		Response response = null;
		LiferayInvokerResult result = null;
		try {

			response = okHttpClient.newCall(request).execute();
			String json = response.body().string();

			logger.info("liferay json invoked result is {} ", json);
			result = JSON.parseObject(json, LiferayInvokerResult.class);

		} catch (IOException e) {
			logger.error("liferay json invoked error is {} ", e.getMessage());
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return result;

	}

	/**
	 * 
	 * @Title: getRequestJsonString @Description: @author: 孙云涛 @param: @param
	 *         method @param: @param params @param: @return @return: String @throws
	 */
	private String getRequestJsonString(String method, JSONObject params) {
		JSONObject json = new JSONObject();

		json.put("jsonrpc", JSON_RPC);
		json.put("id", ID);
		json.put("method", method);
		json.put("params", params);

		return json.toJSONString();

	}

}
