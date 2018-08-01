package com.zhiyun.util;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

	private static OkHttpClient okHttpClient = new OkHttpClient();

	public static String post(String url, JSONObject params) {

		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),
				params.toJSONString());

		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = null;
		try {

			response = okHttpClient.newCall(request).execute();

			String json = response.body().string();

			System.out.println("http post json result is " + json);

			return json;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return null;

	}

}
