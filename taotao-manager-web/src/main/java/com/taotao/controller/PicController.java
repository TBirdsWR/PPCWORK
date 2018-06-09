package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PicService;

@Controller
public class PicController {

	@Autowired
	private PicService picService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map uploadPicture(MultipartFile uploadFile)
	{
		return picService.upLoadPicture(uploadFile);
	}
	
}
