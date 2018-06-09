package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getContentCategory(long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria  criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		List<EUTreeNode> treeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode euTreeNode = new EUTreeNode();
			euTreeNode.setId(tbContentCategory.getId());
			euTreeNode.setText(tbContentCategory.getName());
			euTreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			treeList.add(euTreeNode);
		}
		return treeList;
	}

	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		TbContentCategory recordp = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		recordp.setIsParent(true);
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria  criteria = example.createCriteria();
		criteria.andIdEqualTo(parentId);
		tbContentCategoryMapper.updateByExample(recordp, example);
		TbContentCategory record = new TbContentCategory();
		record.setCreated(new Date());
		record.setId(IDUtils.genItemId());
		record.setParentId(parentId);
		record.setName(name);
		record.setUpdated(new Date());
		record.setStatus(1);
		record.setSortOrder(1);
		record.setIsParent(false);
		tbContentCategoryMapper.insert(record);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		TbContentCategory record = tbContentCategoryMapper.selectByPrimaryKey(id);
		record.setName(name);
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria  criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		tbContentCategoryMapper.updateByExample(record, example);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContentCategory(long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria  criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		tbContentCategoryMapper.deleteByExample(example);
		return TaotaoResult.ok();
	}

}
