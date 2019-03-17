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
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

@Slf4j
public class HttpUtils {
	

	/***
	 * 默认编码
	 */
	private static final String DEF_CHATSET = "UTF-8";
	
	private static String userAgent = "\"Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255";
	private static RequestConfig requestConfig = null;
	private static ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy=null;
	private static PoolingHttpClientConnectionManager cm=null;
	private static HttpRequestRetryHandler httpRequestRetryHandler =null;


	static {
		// 设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(5000).setConnectionRequestTimeout(1000).build();
		httpRequestRetryHandler = new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException exception, int retryTimes, HttpContext context) {
				log.error("retryRequest:"+retryTimes);
				if (retryTimes >= 3) {
					return false;
				}
				if (exception instanceof ConnectTimeoutException || exception instanceof NoHttpResponseException || exception instanceof SocketTimeoutException
						|| !(exception instanceof UnknownHostException) || !(exception instanceof InterruptedIOException) || !(exception instanceof SSLException)
						|| !(exception instanceof SSLHandshakeException)) {
					return true;
				}else {
					log.error("未记录的请求异常：" + exception.getClass());
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求被认为是幂等的，那么就重试。即重复执行不影响程序其他效果的
					return true;
				}
				return false;
			}
		};
		serviceUnavailableRetryStrategy = new ServiceUnavailableRetryStrategy() {
			/**
			 * retry逻辑
			 */
			@Override
			public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
				log.error("retryRequest:"+executionCount);
				if (executionCount <= 3)
					return true;
				else
					return false;
			}

			/**
			 * retry间隔时间
			 */
			@Override
			public long getRetryInterval() {
				log.error("getRetryInterval");
				return 5000;
			}
		};
		cm = new PoolingHttpClientConnectionManager();
		//连接池最大生成连接数200
		cm.setMaxTotal(200);
		//默认设置route最大连接数
		cm.setDefaultMaxPerRoute(100);
	}


	public static CloseableHttpClient createSSLClientDefault(Boolean bool) {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
			if (bool)
				return HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler).setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler).build();
	}


	/**
	 * @Author: sunny
	 * @Date: 10:44 2019/3/17
	 * @Description: map转请求参数型
	 * @param params map集合
	 * @return
	 */
	private static String urlencode(Map<String,Object> params){
		String result = null;
		StringBuilder sb = new StringBuilder();
		try {
			if(params.size()==0)
				return null;
			for(Map.Entry<String, Object> i : params.entrySet()){
				sb.append(i.getKey()).append("=").append(URLDecoder.decode(i.getValue()+"", DEF_CHATSET)).append("&");
			}
			sb.deleteCharAt(sb.length()-1);  //去掉最后一个&
			result = sb.toString();
		} catch (Exception e) {
			log.error("转换失败:{}",e.getMessage());
		}
		return result;
	}


	/**
	 * @Author: sunny
	 * @Date: 10:44 2019/3/17
	 * @Description: 获取服务器响应的信息(适用get或post请求)
	 * @param strUrl 请求地址
	 * @param requestParams 请求参数
	 * @param methodType 请求方法类型(get or post)
	 * @param token token
	 * @param sign sign
	 * @return
	 */
	public static JSONObject responseData(String strUrl,String requestParams,String methodType,String token,String sign){
		if(methodType==null)
			throw new RuntimeException("请求方法类型不能为空!");
		HttpURLConnection conn = null;
		DataOutputStream out = null;
		BufferedReader reader = null;
		StringBuilder sb;
		String result = null;
		try {
			if(StringUtils.isNotBlank(requestParams)){
				Map<String,Object> params = Maps.newHashMap();
				try{
					 params = JSONObject.parseObject(requestParams, Map.class);
				}catch (Exception e){
					log.error("请求参数转map集合报错:{}",e.getMessage());
				}
				requestParams = urlencode(params);
				if(methodType.toUpperCase().equals("GET"))
					strUrl = strUrl.concat("?").concat(requestParams);
			}
			URL url = new URL(strUrl);
			//打开连接
			conn = (HttpURLConnection) url.openConnection();
			if(methodType.toUpperCase().equals("GET")){
				conn.setRequestMethod("GET");
			}else if(methodType.toUpperCase().equals("POST")){
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
			int responseCode = conn.getResponseCode();
			String responseMessage = conn.getResponseMessage();
			if(responseCode!=HttpURLConnection.HTTP_OK){
				log.error(String.format("网络响应错误,响应状态码%s,响应信息为%s",responseCode,responseMessage));
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
			log.error("请求连接失败:{}",e.getMessage());
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					log.error("输入流关闭失败:{}",e.getMessage());
					
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					log.error("输出流关闭失败:{}",e.getMessage());
				}
			}
			if(conn!=null){
				try {
					conn.disconnect();
				} catch (Exception e2) {
					log.error("连接关闭失败:{}",e2.getMessage());
				}
			}
		}
		return JSONObject.parseObject(result);
	}



	/**
	 * @Author: sunny
	 * @Date: 10:44 2019/3/17
	 * @Description: httpGet请求
	 * @param url 请求地址
	 * @param headers 请求头
	 * @return
	 */
	public static JSONObject httpWithGet(String url,Map<String,String> headers){
		log.info(String.format("==========================get请求的地址为:%s ===========================",url));
		// get请求返回结果
		CloseableHttpClient httpClient = createSSLClientDefault(StringUtils.isNotBlank(url) && url.startsWith("https"));
		// 发送get请求
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		if (headers != null && headers.size()>0) {
			for (Map.Entry<String,String> header : headers.entrySet()) {
				request.setHeader(header.getKey(), header.getValue());
			}
		}
		ResponseHandler<String> responseHandler = response -> {
 			//请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = response.getEntity();
				return entity==null ? null :  EntityUtils.toString(entity, "utf-8");
			}
			return null;
		};
		try {
			String result = httpClient.execute(request, responseHandler);
			if(StringUtils.isNotBlank(result))
				return JSONObject.parseObject(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * @Author: sunny
	 * @Date: 10:44 2019/3/17
	 * @Description: httpPost请求
	 * @param url 请求地址
	 * @param jsonObject 请求桉树
	 * @param headers 请求头
	 * @return
	 */
	public static JSONObject httpWithPost(String url, JSONObject jsonObject,Map<String,String> headers) {
		log.info(String.format("==========================post请求的地址为:%s ===========================",url));
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		try {
			httpPost.setEntity(new StringEntity(jsonObject.toString(), DEF_CHATSET));
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
			// 设置请求头
			if (headers != null && headers.size()>0) {
				for (Map.Entry<String,String> header : headers.entrySet()) {
					httpPost.setHeader(header.getKey(), header.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}


	/**
	 * 发送httpPost
	 * @param httpPost
	 * @return
	 */
	private static JSONObject sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity;
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
		return JSONObject.parseObject(responseContent);
	}



	/**
	 * @Author: sunny
	 * @Date: 10:44 2019/3/17
	 * @Description: httpPut请求
	 * @param url 请求地址
	 * @param jsonParam 请求参数
	 * @param headers 请求头
	 * @return
	 */
	public static JSONObject httpWithPut(String url, JSONObject jsonParam, Map<String,String> headers) {
		log.info(String.format("==========================put请求的地址为:%s ===========================",url));
		CloseableHttpClient httpClient = createSSLClientDefault(StringUtils.isNotBlank(url) && url.startsWith("https"));
		JSONObject jsonResult = null;
		HttpPut httpPut = new HttpPut(url);
		// 设置请求和传输超时时间
		httpPut.setConfig(requestConfig);
		if (headers != null && headers.size()>0) {
			for (Map.Entry<String,String> header : headers.entrySet()) {
				httpPut.setHeader(header.getKey(), header.getValue());
			}
		}
		try {
			if (null != jsonParam && !jsonParam.isEmpty()) {
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPut.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPut);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					log.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			log.error("post请求提交失败:" + url, e);
		} finally {
			httpPut.releaseConnection();
		}
		return jsonResult;
	}


	/**
	 * @Author: sunny
	 * @Date: 10:44 2019/3/17
	 * @Description: httpDelete请求
	 * @param url 请求地址
	 * @param headers 请求头
	 * @return
	 */
	public static JSONObject httpWithDelete(String url,Map<String,String> headers){
		log.info(String.format("==========================delete请求的地址为:%s ===========================",url));
		// delete请求返回结果
		CloseableHttpClient httpClient = createSSLClientDefault(StringUtils.isNotBlank(url) && url.startsWith("https"));
		// 发送delete请求
		HttpDelete httpDelete = new HttpDelete(url);
		httpDelete.setConfig(requestConfig);
		if (headers != null && headers.size()>0) {
			for (Map.Entry<String,String> header : headers.entrySet()) {
				httpDelete.setHeader(header.getKey(), header.getValue());
			}
		}
		ResponseHandler<String> responseHandler = response -> {
			int responseCode  = response.getStatusLine().getStatusCode();
			String reasonPhrase = response.getStatusLine().getReasonPhrase();
			//判断响应状态吗
			if (responseCode == HttpStatus.SC_OK) {
				// 读取服务器返回的entity
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			}else{
				log.error(String.format("delete请求响应失败,响应码:%d,信息:%s",responseCode,reasonPhrase));
				return null;
			}
		};
		String responseBody = null;
		try {
			responseBody = httpClient.execute(httpDelete, responseHandler);
			if(StringUtils.isNotBlank(responseBody))
				return JSONObject.parseObject(responseBody);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
