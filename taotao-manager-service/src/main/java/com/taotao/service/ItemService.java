package com.taotao.service;



import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	TbItem getItemById(long id);
	
	EUDataGridResult getItemsByPage(int page,int rows);
	
	TaotaoResult saveItem(TbItem item,String desc,String itemParams)throws Exception;
	
	TaotaoResult updateItem(TbItem item,String desc,String itemParams)throws Exception;
}
