package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem geTbItemById(@PathVariable Long itemId)
	{
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemsByPage(Integer page,Integer rows)
	{
		EUDataGridResult result = itemService.getItemsByPage(page, rows);
		
		return result;
	}
	
	@RequestMapping(value="/item/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult update(TbItem item,String desc,String itemParams)
	{
		TaotaoResult result = TaotaoResult.ok();
		try {
			result = itemService.updateItem(item,desc,itemParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  result;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(TbItem item,String desc,String itemParams)
	{
		TaotaoResult result = TaotaoResult.ok();
		try {
			result = itemService.saveItem(item,desc,itemParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  result;
		
		
	}
}
