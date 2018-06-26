package com.taotao.mapper;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.taotao.pojo.UserInfo;

@Repository("loginDao")
public interface LoginDao {

	UserInfo login(String username);
	
	int register(UserInfo info) throws SQLException;
}
