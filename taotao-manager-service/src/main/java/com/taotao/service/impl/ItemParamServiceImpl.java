package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper paramMapper;
	
	@Autowired
	private TbItemCatMapper catMapper;
	
	@Override
	public EUDataGridResult getItemParam(int page, int rows) {
		
		EUDataGridResult  result = new EUDataGridResult();
		
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = paramMapper.selectByExampleWithBLOBs(example);
		if(!CollectionUtils.isEmpty(list)) {
			for(TbItemParam item:list) {
				Long id = item.getItemCatId();
				TbItemCat cat = catMapper.selectByPrimaryKey(id);
				item.setItemCatName(cat.getName());
			}
		}else{
			list = new ArrayList<TbItemParam>();
		}
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult queryItemCatId(long catId) {
		TaotaoResult result = new TaotaoResult();
		
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(catId);
		List<TbItemParam> list = paramMapper.selectByExampleWithBLOBs(example);
		if(CollectionUtils.isEmpty(list)) {
			result.setStatus(200);
			result.setMsg("not exist");
			return result;
		}else{
			result.setStatus(200);
			result.setData(list.get(0));
			result.setMsg("exist");
			return result;
		}
		
	}

	@Override
	public TaotaoResult savaItemParam(long catId, String paramData) {
		TaotaoResult result = new TaotaoResult();
		TbItemParam record = new TbItemParam();
		record.setItemCatId(catId);
		record.setParamData(paramData);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		
		paramMapper.insert(record);
		
		result.setStatus(200);
		result.setMsg("success");
		return result;
	}

}
