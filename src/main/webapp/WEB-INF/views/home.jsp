<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理首页</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/bootstrap/easyui.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/icon.css" />">
		<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.easyui.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/static/easyui/1.4/locale/easyui-lang-zh_CN.js" />"></script>
		<script type="text/javascript" src="<c:url value="/static/js/main.js" />"></script>
		<script type="text/javascript" src="<c:url value="/static/js/validateboxRules.js" />"></script>
		<link href="<c:url value="/static/css/font-awesome-4.2.0/css/font-awesome.min.css" />" rel="stylesheet">
		
		
	</head>
	<body class="easyui-layout" style="text-align:left">
		<div region="north" border="false" style="background:#666;text-align:center">
						<div id="header-inner">
				<table cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						
						<td style="height:52px;">
							<div style="color:#fff;font-size:22px;font-weight:bold;">
								寄存e站后台管理系统
							</div>
							<div style="color:#fff">
								让您的生活更加便捷!
							</div>
						</td>
						<td style="padding-right:5px;text-align:right;vertical-align:bottom;">
							
						</td>
					</tr>
				</table>
			</div>
			
		</div>
		<div region="west" split="true" title="菜单" style="width:250px;padding:5px;">
			<ul class="easyui-tree">
				<li iconCls="icon-base">
					<span ><div style="font-size: 14px; font-weight:bold ">用户管理</div></span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="openTab('test', '测试')">测试</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="openTab('user/list', '用户管理')">用户管理</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="openTab('manager', '管理员')">管理员</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">用户角色</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>用户管理</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">用户列表</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">用户权限</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">用户角色</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>用户管理</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">用户列表</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">用户权限</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">用户角色</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>用户管理</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">用户列表</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">用户权限</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">用户角色</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>用户管理</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">用户列表</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">用户权限</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">用户角色</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">用户角色</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>Base</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">parser</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">easyloader</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">draggable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">droppable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">resizable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">pagination</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">searchbox</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">progressbar</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">tooltip</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>Base</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">parser</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">easyloader</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">draggable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">droppable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">resizable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">pagination</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">searchbox</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">progressbar</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">tooltip</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>Base</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">parser</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">easyloader</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">draggable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">droppable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">resizable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">pagination</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">searchbox</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">progressbar</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">tooltip</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>Base</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">parser</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">easyloader</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">draggable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">droppable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">resizable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">pagination</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">searchbox</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">progressbar</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">tooltip</a></li>
					</ul>
				</li>
				<li iconCls="icon-base">
					<span>Base</span>
					<ul>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('parser')">parser</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('easyloader')">easyloader</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('draggable')">draggable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('droppable')">droppable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('resizable')">resizable</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('pagination')">pagination</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('searchbox')">searchbox</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('progressbar')">progressbar</a></li>
						<li iconCls="icon-gears"><a class="e-link" href="#" onclick="open1('tooltip')">tooltip</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div region="center">
			<div id="tt" class="easyui-tabs" fit="true" border="false" plain="true">
				<div title="welcome" href="welcome"></div>
			</div>
		</div>
		<div id="mm" class="easyui-menu" style="width:50px;">
	        
	        <div id="mm-refresh" name="6" data-options="iconCls:'fa fa-refresh'" >刷新</div>
	        <div class="menu-sep"></div>
	        <div id="mm-tabcloseall" name="2" data-options="iconCls:'fa fa-ban'" >全部关闭</div>
	        <div id="mm-tabcloseother" name="3" data-options="iconCls:'fa fa-times-circle'">关闭其他</div>
	        <div class="menu-sep"></div>
	        <div id="mm-tabcloseright" name="4" data-options="iconCls:'fa fa-share'">关闭右侧</div>
	        <div id="mm-tabcloseleft" name="5" data-options="iconCls:'fa fa-reply'">关闭左侧</div>
	        <div class="menu-sep"></div>
	        <div id="mm-tabclose" name="1" name="6" data-options="iconCls:'fa fa-times'">关闭</div>
   		 </div>
		<script type="text/javascript">
		$(document).ready(function(){
			//监听右键事件，在tabs上创建右键菜单
			$('#tt').tabs({
                onContextMenu:function(e, title,index){
                    e.preventDefault();
                    if(index>0){
                        $('#mm').menu('show', {
                            left: e.pageX,
                            top: e.pageY
                        }).data("tabTitle", title);
                    }
                }
            });
			//菜单项click
            $("#mm").menu({
                onClick : function (item) {
                    closeTab(this, item.name);
                }
            });
		    
		});
		
		//删除Tabs
        function closeTab(menu, type) {
            var allTabs = $("#tt").tabs('tabs');
            var allTabtitle = [];
            $.each(allTabs, function (i, n) {
                var opt = $(n).panel('options');
                if (opt.closable)
                    allTabtitle.push(opt.title);
            });
            var curTabTitle = $(menu).data("tabTitle");
            var curTabIndex = $("#tt").tabs("getTabIndex", $("#tt").tabs("getTab", curTabTitle));
            switch (type) {
                case "1"://关闭当前
                    $("#tt").tabs("close", curTabTitle);
                    return false;
                    break;
                case "2"://全部关闭
                    for (var i = 0; i < allTabtitle.length; i++) {
                        $('#tt').tabs('close', allTabtitle[i]);
                    }
                    break;
                case "3"://除此之外全部关闭
                    for (var i = 0; i < allTabtitle.length; i++) {
                        if (curTabTitle != allTabtitle[i])
                            $('#tt').tabs('close', allTabtitle[i]);
                    }
                    $('#tt').tabs('select', curTabTitle);
                    break;
                case "4"://当前侧面右边
                    for (var i = curTabIndex; i < allTabtitle.length; i++) {
                        $('#tt').tabs('close', allTabtitle[i]);
                    }
                    $('#tt').tabs('select', curTabTitle);
                    break;
                case "5": //当前侧面左边
                    for (var i = 0; i < curTabIndex - 1; i++) {
                        $('#tt').tabs('close', allTabtitle[i]);
                    }
                    $('#tt').tabs('select', curTabTitle);
                    break;
                case "6": //刷新
                    var panel = $("#tt").tabs("getTab", curTabTitle).panel("refresh");
                    $('#tt').tabs('select', curTabTitle);
                    break;
            }

        }
		</script>
	</body>
</html>

