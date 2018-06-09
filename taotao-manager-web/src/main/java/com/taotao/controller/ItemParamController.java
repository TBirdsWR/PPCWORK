package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult getItemParamByPage(Integer page,Integer rows) {
		return itemParamService.getItemParam(page, rows);
	}
	
	@RequestMapping("/item/param/query/itemcatid")
	@ResponseBody
	public TaotaoResult queryItemCatId(@RequestParam(value="catId") long catId) {
		return itemParamService.queryItemCatId(catId);
	}
	
	@RequestMapping(value="/item/param/save/{catId}",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable long catId,String paramData) {
		return itemParamService.savaItemParam(catId,paramData);
	}
}
