package com.taotao.controller;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentService;

@Controller
public class ContentController {

	public Log log = LogFactory.getLog(ContentController.class);

	
	@Autowired
	private ContentService  contentService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EUTreeNode> getContentCategory(@RequestParam(value="id",defaultValue="0") long parentId){
		log.debug("----debug---------------"+parentId+"-----debug--------------");
		log.info("----info---------------"+parentId+"----info---------------");
		List<EUTreeNode> list = contentService.getContentCategory(parentId);
		return list;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult addContentCategory(long parentId,String name){
		
		TaotaoResult result = contentService.addContentCategory(parentId,name);
		return result;
	}
	
	@RequestMapping("/content/category/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(long id,String name){
		
		TaotaoResult result = contentService.updateContentCategory(id,name);
		return result;
	}
	
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(long id){
		
		TaotaoResult result = contentService.deleteContentCategory(id);
		return result;
	}
}
