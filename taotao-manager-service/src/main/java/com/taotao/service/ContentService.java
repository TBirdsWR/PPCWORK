package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentService {
	
	List<EUTreeNode> getContentCategory(long parentId);
	
	TaotaoResult addContentCategory(long parentId,String name);
	
	TaotaoResult updateContentCategory(long id,String name);
	
	TaotaoResult deleteContentCategory(long id);
}
