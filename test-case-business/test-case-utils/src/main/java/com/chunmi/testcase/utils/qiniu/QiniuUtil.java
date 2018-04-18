/**
 * This class was created by Kimmyzhao. It's distributed as
 * part of the kstore_core Mod.
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
 * File Created @ [2017年2月6日, 下午4:38:32 (CST)]
 */
package com.chunmi.testcase.utils.qiniu;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunmi.testcase.utils.PropertieUtil;
import com.chunmi.testcase.utils.UtilDate;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class QiniuUtil {

	private static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

	public static final String TYPE_GOODS = "1";
	
	public static final String TYPE_COMMENT = "2";
	
	private static String domain;// 域名
	
	private static String bucketname;// 空间
	
	private static String AccessKey;
	
	private static String SecretKey;
	
	private static String production;
	
	private static String base64URrl;
	static {

		Properties p = PropertieUtil.readPropertiesFile(
				QiniuUtil.class.getClassLoader().getResourceAsStream("config/qiniuyun.properties"));

		domain = p.getProperty("qiniu.domain");
		bucketname = p.getProperty("qiniu.bucketname");
		AccessKey = p.getProperty("qiniu.AccessKey");
		SecretKey = p.getProperty("qiniu.SecretKey");
		production = p.getProperty("upload.production");
		base64URrl = p.getProperty("qiniu.base64url");
	}
	/***
	 * 
	 * @description: <p class="detail">上传文件</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月6日-下午4:47:33
	 * @param @param fileName
	 * @param @param orignalFilename
	 * @param @return
	 * @return String
	 */
	public static String uploadFile(String fileName, String orignal, String type) {
		String result = "";
		try {

			String path = MessageFormat.format(production, UtilDate.todayFormatString(new Date()), fileName);
			
			// 密钥配置
			Auth auth = Auth.create(AccessKey, SecretKey);

			// 第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
			Zone z = Zone.autoZone();
			Configuration c = new Configuration(z);

			UploadManager uploadManager = new UploadManager(c);
			Response res = uploadManager.put(orignal, path, auth.uploadToken(bucketname));
			logger.debug("上传文件的返回信息: {}", res.bodyString());

			result = domain + path;
		} catch (QiniuException e) {
			Response r = e.response;
			logger.error("上传图片异常: {}", r.toString());
			try {
				logger.error("上传图片异常的文本信息: {}", r.bodyString());
			} catch (QiniuException e1) {

			}
		}
		return result;
	}
	
	/***
	 * 
	 * @description: <p class="detail">通过base64编码图片到七牛云</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月9日-下午12:55:55
	 * @param @param base64
	 * @param @param length
	 * @param @return
	 * @return String
	 * <li>1.upload.qiniu.com 上传域名适用于华东空间。华北空间使用 upload-z1.qiniu.com，华南空间使用 upload-z2.qiniu.com，北美空间使用 upload-na0.qiniu.com。</li>
     * <li>2.var url = "http://upload.qiniu.com/putb64/20264"; 这里的20264是你的图片的没经过base64处理的原图的字节大小。</li>
     * <li>3.xhr.setRequestHeader("Authorization", "UpToken 填写你从服务端获取的上传token"); 这里的UpToken与后面的字符串保留一个空格。后面跟上你在服务端请求的token的字符串。具体你通过什么样子的请求方式获得是客户自己要关心的事情。</li>
     * <li>4.获取文件大小的时候，切记要通过文件流的方式获取。而不是通过图片标签然后转换后获取。</li>
     * <li>5.var url = "http://upload.qiniu.com/putb64/20264"; 中可以扩展为以下方式：http://upload.qiniu.com/putb64/Fsize/key/EncodedKey/mimeType/EncodedMimeType/x:user-var/EncodedUserVarVal</li>
     * <li>Fsize: 文件大小，必选。支持传入 -1 表示文件大小以 http request body 为准。</li>
     * <li>EncodedKey: 可选，如果没有指定则：如果 uptoken.SaveKey 存在则基于 SaveKey 生产 key，否则用 hash 值作 key。 EncodedKey需要经过base64编码.具体可以参照: http://developer.qiniu.com/docs/v6/api/overview/appendix.html#urlsafe-base64</li>
     * <li>EncodedMimeType: 文件的 MIME 类型。可选，默认是 application/octet-stream。</li>
     * <li>举例：上传后并指定自定义的key： http://upload.qiniu.com/putb64/12345/key/usxxeigng=</li>
	 */
	public String uploadFileWithBase64(String base64, int length) {
		String key = "image/comment/" + String.copyValueOf(getRandNum(20));
		String url = base64URrl + length + "/key/" + UrlSafeBase64.encodeToString(key);
		// 非华东空间需要根据注意事项 1 修改上传域名
		RequestBody rb = RequestBody.create(null, base64);
		Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/octet-stream")
				.addHeader("Authorization", "UpToken " + getUpToken()).post(rb).build();
		System.out.println(request.headers());

		OkHttpClient client = new OkHttpClient();
		okhttp3.Response response;
		try {
			response = client.newCall(request).execute();
			logger.error("上传文件===>", response);
		} catch (IOException e) {
			logger.error("上传文件异常===>", e);
		}
		return null;
	}
	
	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月9日-下午12:53:55
	 * @param @return
	 * @return String
	 */
	public static String getUpToken() {
		// 密钥配置
		Auth auth = Auth.create(AccessKey, SecretKey);
		return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));

	}
	
	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月9日-下午12:57:43
	 * @param @param n
	 * @param @return
	 * @return char[]
	 */
	public static char[] getRandNum(int n) {
		char[] rand = new char[n];
		String str = "23456789qwertyuipkjhgfdsacvbnmQWERTYUPLKJHGFDSACVBNM";
		for (int i = 0; i < n; i++) {
			Random rd = new Random();
			int index = rd.nextInt(str.length());

			// 通过下标获取字符
			rand[i] = str.charAt(index);
		}
		return rand;
	}

	/****
	 * 
	 * @description: <p class="detail">以流的形式上传文件到七牛</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年6月22日-上午10:30:37
	 * @param @param inputStream
	 * @param @param fileName
	 * @param @param typeGoods
	 * @param @return
	 * @return String
	 */
	public static String uploadFileByStream(InputStream inputStream, String fileName, String typeGoods) {
		String result = "";

		try {

			String uploadTime = UtilDate.todayFormatString(new Date());

			String path = MessageFormat.format(production, uploadTime, fileName);

			// 第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
			Zone z = Zone.autoZone();
			Configuration c = new Configuration(z);

			UploadManager uploadManager = new UploadManager(c);

			uploadManager.put(inputStream, path, getUpToken(), null, null);

			result = domain + path;
		} catch (QiniuException e) {
			Response r = e.response;
			logger.error("上传图片异常: {}", r.toString());
			try {
				logger.error("上传图片异常的文本信息: {}", r.bodyString());
			} catch (QiniuException e1) {

			}

		}

		return result;
	}

	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年6月20日-上午10:45:32
	 * @param @param filePath
	 * @param @param key
	 * @param @return
	 * @return String
	 */
	public static String upload(String filePath, String key) {
		try {
			key = key.replaceAll("\\\\", "/");

			Zone z = Zone.autoZone();
			Configuration c = new Configuration(z);

			UploadManager uploadManager = new UploadManager(c);
			uploadManager.put(filePath, key, getUpToken());
			return domain + key;
		} catch (QiniuException e) {
			Response r = e.response;
			logger.error("上传图片异常: {}", r.toString());
			try {
				logger.error("上传图片异常的文本信息: {}", r.bodyString());
			} catch (QiniuException e1) {

			}

		}
		return null;
	}
}
