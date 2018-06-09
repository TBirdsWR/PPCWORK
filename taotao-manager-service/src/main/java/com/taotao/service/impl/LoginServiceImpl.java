package com.taotao.service.impl;

import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.LoginDao;
import com.taotao.pojo.UserInfo;
import com.taotao.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	public Log log = LogFactory.getLog(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public TaotaoResult login(UserInfo info) {
		UserInfo userInfo = loginDao.login(info.getUsername());
		
		if(null == userInfo)
		{
			return TaotaoResult.build(100, "user not exist!");
		}
		log.debug("--query_userinfo--"+userInfo.toString());
		if(!info.getPassword().equals(userInfo.getPassword()))
		{
			return TaotaoResult.build(101, "password is not right!");
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult register(UserInfo info) {
		String id = IDUtils.genUserId();
		info.setId(id);
		try {
			loginDao.register(info);
		} catch (SQLException e) {
			return  TaotaoResult.build(100,"register faild");
		}
		
		return TaotaoResult.ok();
	}

}
