package com.taotao.service;

import java.util.List;
import java.util.Map;

import com.taotao.common.pojo.EUTreeNode;

public interface ItemCatService {

	List<EUTreeNode> getItemCatList(long parentId);

}
