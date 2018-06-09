<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘淘商城后台管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
<div title="退出" style="padding:20px;padding-right:20px;">
		        	
		    </div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'item-add'}">新增商品</li>
	         		<li data-options="attributes:{'url':'item-list'}">查询商品</li>
	         		<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'content'}">内容管理</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		    
		    
		</div>
		<div id="username" title="" style="padding-top: 3px;position: absolute;right: 40px;top: 4px;font-size: 13px;color: #2A2D56;z-index: 999;width: 140px;height: 17px;font-weight: bold;text-align: center;">
		</div>
		<div id="logout" title="退出" style="cursor:pointer ;padding-top: 3px;position: absolute;right: 6px;top: 4px;font-size: 13px;color: #2A2D56;z-index: 999;width: 40px;height: 17px;font-weight: bold;text-align: center;border: 1px solid #80A4C3;border-radius: 5px;">退出
		</div>
    </div>
    
<script type="text/javascript">
$(function(){

	function getParam(){
		search=window.location.search.substr(1,window.location.search.length)
		searchSplit = search.split("&")
		var obj = {};
		for(var i=0;i<searchSplit.length;i++){
			params = searchSplit[i].split("=");
			obj[params[0]] = params[1];
		}
		return obj;
	}

	$("#username").html("Hello "+getParam()["username"]+"!");
	
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});

$("#logout").click(function(){
	$.post("/login/out","", function(data){
		window.location.href="/login";
	});
	
	
});
</script>
</body>
</html>