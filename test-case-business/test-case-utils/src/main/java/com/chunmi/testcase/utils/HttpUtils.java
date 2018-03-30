/**
 * This class was created by wangzhifang. It's distributed as
 * part of the chunmitest-util Mod.
 *
 * 版权所有(C) 上海纯米电子科技有限公司 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2017年6月9日, 下午2:28:39 (CST)]
 */
package com.chunmi.testcase.utils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	
	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
	
	/***
	 * 默认编码
	 */
	public static final String DEF_CHATSET = "UTF-8";
	
	/**
	 * 浏览器类型
	 */
	public static String userAgent = "\"Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255";
	
	/**
	 * 
	 * @description: <p class="detail">将map型转为请求参数型</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月9日-下午2:45:10
	 * @param @param params
	 * @param @return
	 * @return String
	 */
	public static String urlencode(Map<String,Object> params){
		String result = null;
		StringBuilder sb = new StringBuilder();
		try {
			if(params.size()==0 || params==null)
				return null;
			for(Map.Entry<String, Object> i : params.entrySet()){
				sb.append(i.getKey()).append("=").append(URLDecoder.decode(i.getValue()+"", DEF_CHATSET)).append("&");
			}
			sb.deleteCharAt(sb.length()-1);  //去掉最后一个&
			result = sb.toString();
		} catch (Exception e) {
			LOGGER.error("转换失败:{}",e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * @description: <p class="detail">获取网络响应数据</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月9日-下午2:50:44
	 * @param @param strUrl
	 * @param @param requestParams
	 * @param @param methodType
	 * @param @return
	 * @return String
	 */
	public static String responseData(String strUrl,String requestParams,String methodType,String token,String sign){
		StringBuilder sb = null;
		String result = null;
		HttpURLConnection conn = null;
		DataOutputStream out = null;
		BufferedReader reader = null;
		try {
			if(!requestParams.isEmpty() && requestParams!=null){
				Map<String,Object> params = (Map<String,Object>)JSONObject.parseObject(requestParams);
				requestParams = urlencode(params);
				if(methodType.equals("GET"))
					strUrl = strUrl+"?"+requestParams;
			}
			//将url字符串转换为url地址
			URL url = new URL(strUrl);
			//打开连接
			conn = (HttpURLConnection) url.openConnection();
			if(methodType.equals("GET")){
				conn.setRequestMethod("GET");
			}else if(methodType.equals("POST")){
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setDoInput(true);
			//设置缓存
			conn.setUseCaches(false);
			// 设置请求头里面的属性
			conn.setRequestProperty(HTTP.USER_AGENT,userAgent);  //设置浏览器类型
			conn.setRequestProperty(HTTP.CONTENT_TYPE,"application/json");
			conn.setRequestProperty(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
			if(token!=null && !token.isEmpty()){
				conn.setRequestProperty("token", token);
			}
			if(sign!=null && !sign.isEmpty()){
				conn.setRequestProperty("sign", sign);
			}
			// 设置该HttpURLConnection实例是否自动执行重定向
			conn.setInstanceFollowRedirects(false);
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            conn.connect();
			//post请求将参数以流的形式隐式提交
			if(methodType.equals("POST") && !requestParams.isEmpty() && requestParams!=null){
				out = new DataOutputStream(conn.getOutputStream());
				//将参数输出到连接
				out.writeBytes(requestParams);
				out.flush();
				out.close();
			}
            //获取响应码
			int code = conn.getResponseCode();
			if(code!=HttpURLConnection.HTTP_OK){
				return null;
			}
			//创建reader，读取响应内容
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEF_CHATSET));
			String strReader = null;
			sb = new StringBuilder();
			while((strReader=reader.readLine())!=null){
				sb.append(strReader);
			}
			result = sb.toString();
				
		} catch (Exception e) {
			LOGGER.error("请求连接失败:{}",e.getMessage());
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					LOGGER.error("输入流关闭失败:{}",e.getMessage());
					
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					LOGGER.error("输出流关闭失败:{}",e.getMessage());
				}
			}
			if(conn!=null){
				try {
					conn.disconnect();
				} catch (Exception e2) {
					LOGGER.error("连接关闭失败:{}",e2.getMessage());
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @description: <p class="detail">http POST请求</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月28日-下午6:27:58
	 * @param @param url
	 * @param @param valueList
	 * @param @param headers
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 */
	public static String httpPostWithJSON(String url, Map<String, Object> valueList, Header[] headers)
			throws Exception {
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		// 创建参数队列
		JSONObject json = new JSONObject();
		for(Map.Entry<String,Object> mapParams : valueList.entrySet()){
			json.put(mapParams.getKey(), mapParams.getValue());
		}
		try {
			httpPost.setEntity(new StringEntity(json.toString(), DEF_CHATSET));
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
			// 设置请求
			if (headers != null) {
				for (Header header : headers) {
					httpPost.addHeader(header);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">发送post请求</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月28日-下午6:29:12
	 * @param @param httpPost
	 * @param @return
	 * @return String
	 */
	private static String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
}
