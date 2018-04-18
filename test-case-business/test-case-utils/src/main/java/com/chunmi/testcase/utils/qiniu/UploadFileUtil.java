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
 * File Created @ [2017年2月6日, 下午2:33:34 (CST)]
 */
package com.chunmi.testcase.utils.qiniu;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileUtil {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

	private static final String LOGGERINFO1 = "的图片不符合限制";
	
	private static String prefix = "P000"; 
	
	private static final String IMAGE = "image";
	/** 最大文件大小 */
	private static long maxSize = 10 * 1024 * 1024;
	/** 定义允许上传的文件扩展名 */
	private static HashMap<String, String> extMap = new HashMap<String, String>();
	// 初始化
	static {
		extMap.put(IMAGE, "gif,jpg,jpeg,png,bmp,ico");
	}

	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月7日-下午3:05:19
	 * @param @param file
	 * @param @return
	 * @return boolean
	 */
	private static boolean checkFile(FileItem file) {
		boolean bool = true;
		// 检查文件大小
		if (file.getSize() > maxSize) {
			logger.error("=============>上传" + file.getFieldName() + "文件大小超过限制");
			bool = false;
		}
		String fileName = file.getName();
		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get(IMAGE).split(",")).contains(fileExt)) {
			logger.error("上传文件" + file.getFieldName() + "扩展名是不允许的扩展名。\n只允许" + extMap.get(IMAGE) + "格式。");
			bool = false;
		}
		return bool;
	}

	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月7日-下午3:05:24
	 * @param @param muFile
	 * @param @return
	 * @return boolean
	 */
	private static boolean checkFileForSpringUpload(MultipartFile muFile) {
		boolean bool = true;
		// 检查文件大小
		if (muFile.getSize() > maxSize) {
			logger.error("=============>上传" + muFile.getOriginalFilename() + "文件大小超过限制");
			bool = false;
		}
		String fileName = muFile.getOriginalFilename();
		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get(IMAGE).split(",")).contains(fileExt)) {
			logger.error("上传文件" + muFile.getOriginalFilename() + "扩展名是不允许的扩展名。\n只允许" + extMap.get(IMAGE) + "格式。");
			bool = false;
		}
		return bool;
	}

	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月7日-下午3:05:40
	 * @param @return
	 * @return Properties
	 */
	public static Properties readValue() {
		Properties p = new Properties();
		try (InputStream inputStream = UploadFileUtil.class.getClassLoader()
				.getResourceAsStream("com/ningpai/web/config/upload.properties")) {
			p.load(inputStream);
		} catch (IOException e1) {
			logger.error("读取Properties属性文件错误：=>", e1);
		}
		return p;
	}
	
	/***
	 * 
	 * @description:<p class="detail">上传文件</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月6日-下午3:05:07
	 * @param @param item
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws IOException 
	 */
	public static synchronized String uploadFileOneByFile(FileItem item, HttpServletRequest request) {
		String result = null;

		if (item != null && item.getSize() != 0) {
			// 检查上传的图片的大小和扩展名
			if (!checkFile(item)) {
				throw new RuntimeException("上传的图片不符合限制");
			}
			String originalFilename = item.getName();
			String[] fileName = originalFilename.split("\\.");
			String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileName[fileName.length - 1];
			try {
				result = QiniuUtil.uploadFileByStream(item.getInputStream(), prefix + newFileName, QiniuUtil.TYPE_GOODS);
				logger.debug("文件地址: {}", result);
				return result;
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
		}
		return result;
	}
	
	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月7日-下午3:01:23
	 * @param @param muFile
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws IOException 
	 */
	public static synchronized String uploadFile(MultipartFile muFile, HttpServletRequest request) {
		String result = "";
		if (muFile != null && muFile.getSize() != 0) {
			// 检查上传的图片的大小和扩展名
			if (!checkFileForSpringUpload(muFile)) {
				throw new RuntimeException("上传" + muFile.getOriginalFilename() + LOGGERINFO1);
			}

			String originalFilename = muFile.getOriginalFilename();
			String[] fileName = originalFilename.split("\\.");
			String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileName[fileName.length - 1];

			try {
				return QiniuUtil.uploadFileByStream(muFile.getInputStream(), prefix + newFileName, QiniuUtil.TYPE_GOODS);
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}

		}
		return result;
	}
	
	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017年2月7日-下午2:20:19
	 * @param @param muFile
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws IOException 
	 */
	public static synchronized String uploadFileOne(MultipartFile muFile, HttpServletRequest request) {
		String result = "";
		if (muFile != null && muFile.getSize() != 0) {
			if (!checkFileForSpringUpload(muFile)) {
			}

			String originalFilename = muFile.getOriginalFilename();
			String[] fileName = originalFilename.split("\\.");
			String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileName[fileName.length - 1];

			try {
				result = QiniuUtil.uploadFileByStream(muFile.getInputStream(), prefix + newFileName, QiniuUtil.TYPE_GOODS);
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			logger.debug("文件地址: {}", result);
			return result;
		}
		return null;
	}
}
