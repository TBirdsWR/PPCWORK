package com.taotao.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.FtpUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.service.PicService;

@Service
public class PicServiceImpl implements PicService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	
	
	@Value("${IMAGE_BASEURL}")
	private String IMAGE_BASEURL;
	
	
	
	@Override
	public Map upLoadPicture(MultipartFile file)  {
		Map resultMap = new HashMap();
		try {
		//lao名字
		String oldName = file.getOriginalFilename();
		//新文件名
		String newName = IDUtils.genImageName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		
		String imagePath = new DateTime().toString("/yyyy/MM/dd");
		
		boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, 
				imagePath, newName, file.getInputStream());
		if(!result)
		{
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
			return resultMap;
			
		}else{
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASEURL+imagePath+"/"+newName);
			return resultMap;
		}
		
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
			return resultMap;
		}
	}

}
