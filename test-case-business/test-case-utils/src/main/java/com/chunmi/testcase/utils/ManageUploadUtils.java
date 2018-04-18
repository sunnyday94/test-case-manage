package com.chunmi.testcase.utils;

import java.io.File;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chunmi.testcase.utils.qiniu.QiniuUtil;

public class ManageUploadUtils {
	
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Response uploadImage(@RequestParam(value = "filename", required = true) MultipartFile filename,
			HttpServletRequest request) {
		String newFileName = null;
		try {
			String s1 = DateUtil.getyearmonthday();
			String tempPath = request.getContextPath() + "/home/boss/static/tmp";
			File tp = new File(tempPath);
			if (!tp.exists()) {
				tp.mkdirs();
			}

			String originalFilename = filename.getOriginalFilename();
			String[] fileName = originalFilename.split("\\.");
			newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileName[fileName.length - 1];
			File newFile = new File(tempPath, newFileName);
			FileUtils.copyInputStreamToFile(filename.getInputStream(), newFile);

			String result = QiniuUtil.upload(tempPath + "/" + newFileName, "upload/" + s1 + newFileName);

			return Response.getSuccess(result);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}


}
