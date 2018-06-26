package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.FtpUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper descMapper;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	
	@Override
	public TbItem getItemById(long id) {
		//TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		TbItemExample example =  new TbItemExample();
		Criteria ca = example.createCriteria();
		ca.andIdEqualTo(id);
		List<TbItem> tbItems = itemMapper.selectByExample(example);
		if(tbItems != null && tbItems.size() > 0 && tbItems.get(0) != null)
		{
			TbItem tbItem = tbItems.get(0);
			return tbItem;
		}
		
		return null;
	}
	@Override
	public EUDataGridResult getItemsByPage(int page, int rows) {
		
		EUDataGridResult  result = new EUDataGridResult();
		TbItemExample example =  new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> tbItems = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
		result.setRows(tbItems);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}
	
	
	@Override
	public TaotaoResult saveItem(TbItem item,String desc,String itemParams) throws Exception {
		long itemId = IDUtils.genItemId();
		item.setCreated(new Date());
		item.setUpdated(new Date());
		item.setId(itemId);
		item.setStatus((byte)1);
		itemMapper.insert(item);
		TaotaoResult result = insertDesc(item.getId(),desc);
		TaotaoResult result2 = saveTbItemParamItem(itemParams, itemId);
		if(200 != result.getStatus() || 200 != result2.getStatus())
		{
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	private TaotaoResult saveTbItemParamItem(String itemParams, long itemId) {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setParamData(itemParams);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setUpdated(new Date());
		tbItemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}
	
	private TaotaoResult insertDesc(Long id,String desc){
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		descMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult updateItem(TbItem item, String desc, String itemParams) throws Exception {
		item.setUpdated(new Date());
		item.setStatus((byte)1);
		itemMapper.updateByPrimaryKey(item);
		updateTbItemParamItem(itemParams,item.getId());
		updateDesc(item.getId(),desc);
		return null;
	}
	
	private TaotaoResult updateTbItemParamItem(String itemParams, long itemId) {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setParamData(itemParams);
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setUpdated(new Date());
		tbItemParamItemMapper.updateByPrimaryKey(tbItemParamItem);
		return TaotaoResult.ok();
	}
	
	private TaotaoResult updateDesc(Long id,String desc){
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		descMapper.updateByPrimaryKey(itemDesc);
		return TaotaoResult.ok();
	}

}
