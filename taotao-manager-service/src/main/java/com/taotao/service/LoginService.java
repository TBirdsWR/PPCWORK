package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.UserInfo;

public interface LoginService {

	TaotaoResult login(UserInfo info);
	
	TaotaoResult register(UserInfo info);
}
