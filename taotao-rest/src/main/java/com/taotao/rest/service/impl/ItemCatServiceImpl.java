package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper catMapper;
	
	@Override
	public CatResult getCatList() {
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		
		return catResult;
	}
	
	private List<?> getCatList(long parentId){
		TbItemCatExample  example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = catMapper.selectByExample(example);
		List nodeList = new ArrayList<>();
		int count = 0;
		for (TbItemCat itemCat : list) {
			if(itemCat.getIsParent()){
				CatNode catNode = new CatNode();
				if(itemCat.getParentId() == 0){
					catNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
					count ++;
				}else{
					catNode.setName(itemCat.getName());

				}
				
				catNode.setUrl("/products/"+itemCat.getId()+".html");
				catNode.setItem(getCatList(itemCat.getId()));
				nodeList.add(catNode);
				if(itemCat.getParentId() == 0 && count>= 14){
					break;
				}
			}else{
				nodeList.add("/products/"+itemCat.getId()+".html|" + itemCat.getName());
			}
		}
		return nodeList;
		
	}

}
