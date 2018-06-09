package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
//	@RequestMapping(value = "/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
//	@ResponseBody
//	public String getItenCatList(String callback){
//		CatResult catResult = itemCatService.getCatList();
//		String result = callback + "("+JsonUtils.objectToJson(catResult)+");";
//		return result;
//		
//	}
	
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object  getItenCatList(String callback){
		CatResult catResult = itemCatService.getCatList();
		MappingJacksonValue result = new MappingJacksonValue(catResult);
		result.setJsonpFunction(callback);
		return result;
		
	}
}
