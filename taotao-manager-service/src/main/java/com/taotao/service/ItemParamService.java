package com.taotao.service;

import java.util.Map;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {
	
	EUDataGridResult getItemParam(int page,int rows);
	
	
	TaotaoResult queryItemCatId(long catId);
	
	TaotaoResult savaItemParam(long catId,String paramData);
}
