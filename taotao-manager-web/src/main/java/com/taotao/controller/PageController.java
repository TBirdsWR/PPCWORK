package com.taotao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.UserInfo;
import com.taotao.service.LoginService;

@Controller
public class PageController {

	public Log log = LogFactory.getLog(ContentController.class);

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/index")
	public String showIndex()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(null == username || username == "")
		{
			try {
				response.sendRedirect("login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "index";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@RequestMapping("/register/in")
	@ResponseBody
	public TaotaoResult registerIn(UserInfo info)
	{
		TaotaoResult result = loginService.register(info);
		return result;
	}

	@RequestMapping("/login/in")
	@ResponseBody
	public TaotaoResult loginIn(UserInfo info)
	{
		log.debug("----debug------loginIn_info:"+info.toString()+"--------------");
		TaotaoResult result = loginService.login(info);
		if(result.getStatus() == 200){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("username", info.getUsername());
		}
		return result;
	}
	
	@RequestMapping("/login/out")
	public String loginOut(UserInfo info)
	{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.removeAttribute("username");
		return "login";
	}
	
	
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page)
	{
		return page;
	}
}
