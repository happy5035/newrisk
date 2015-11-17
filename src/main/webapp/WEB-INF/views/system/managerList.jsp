<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员列表</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/bootstrap/easyui.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/icon.css" />">
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.easyui.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/locale/easyui-lang-zh_CN.js" />"></script>
	
</head>
<body>
	<div style="padding-top:15px; padding-left:5px; padding-right:15px; ">	
		 
			<div id="managerSd" class="easyui-panel" title="查询条件" style="height:100px;padding:10px; margin-bottom:10px;" data-options="collapsible:true">
		       <form action="" id="managerSdForm">            
		                    登录名：<input type="text" id="loginname" name="loginname">
	                              状态：<input type="text" id="state" name="state">
	           
	            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchLoad()">查询</a>
	            </form> 
		    </div>  
	           	
     	<div id="managerTb" style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-plus fa-lg" plain="true" onclick="newObj()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-pencil-square-o fa-lg" plain="true" onclick="editObj()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-trash-o fa-lg" plain="true" onclick="destroyObj()">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-crosshairs fa-lg" plain="true" onclick="viewObj()">查看</a>
        </div>           
	    <table id="managerDg"  title="管理员列表"   url="manager/list"
	    		style="margin: 100px"
	    		sortName="managerid" sortOrder="asc" rownumbers="true"  
	    		pagination="true" pageSize="20" pageList="[10,20,50,100]"
	            data-options="singleSelect:true,method:'post',toolbar:'#managerTb'">
	        <thead>
	            <tr>
	            	<th field="managerid" sortable="true" hidden="true">ID</th>
	                <th field="loginname" sortable="true">登录名称</th>
	                <th field='nickname' sortable="true">昵称</th>
	                <th field='realname' sortable="true">真实姓名</th>
	                <th field='email' sortable="true">邮箱</th>
	                <th field='mobile' sortable="true">手机号码</th>
	                <th field='phone' sortable="true">固定电话</th>
	                <th field='addr' sortable="true">地址</th>
	                <th field='sex' sortable="true" formatter="msexFormatter">性别</th>
	                <th field='registertime' sortable="true">注册时间</th>
	                <th field='state' sortable="true" formatter="mstateFormatter" >用户状态</th>
	            </tr>
	        </thead>
	    </table> 
	    <div id="dlg" class="easyui-dialog" style="width:400px;height:450px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" href="<c:url value="/manager/info" />">
         </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveObj()" style="width:90px" id="save">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	    </div>
	</div>   
	<script type="text/javascript">	
			
			function msexFormatter(value, row, index){
				if (value == 0){  
	                 return '女';  
	             } else {  
	                 return '男';  
	             }
			}
			function mstateFormatter(value, row, index){
				if (value == 0){  
	                 return '禁用';  
	             } else {  
	                 return '可用';  
	             }
			}
			//获得查询条件
			function dgParams(){
				 var params = {};  
				    $.each($("#managerSdForm").serializeArray(), function(index) {
				    	if(this['value'] != ""){				       
				        	params['params['+this['name']+']'] = this['value'];  
				    	}
				    }); 
				return params;
			}
			//dg在初始化时加入查询条件
			$('#managerDg').datagrid({
				queryParams: dgParams()
			});		
			//按查询条件查询
			function searchLoad(){		
				$('#managerDg').datagrid('options').queryParams=dgParams();
				$('#managerDg').datagrid('load');
			}
			var url;
			//添加
		    function newObj(){
		    	$('#save').show();
		        $('#dlg').dialog('open').dialog('setTitle','添加管理员');
		        $('#managerInfoForm').form('clear');
		        url = "manager/insert";
		    }
		    function editObj(){
		    	$('#save').show();
		        var row = $('#managerDg').datagrid('getSelected');
		        if (row != null){
		            $('#dlg').dialog('open').dialog('setTitle','编辑管理员信息');
		            $('#managerInfoForm').form('load','manager/'+row.managerid+'/edit');
		            url = 'manager/update';
		         }else{
		        	 $.messager.alert('操作提示！','请选择一行','info');
		         }
		    }
		    
		    function viewObj(){
		        var row = $('#managerDg').datagrid('getSelected');
		        if (row != null){
		            $('#dlg').dialog('open').dialog('setTitle','编辑管理员信息');
		            $('#managerInfoForm').form('load','manager/'+row.managerid+'/view');
		            $('#save').hide();
		         }else{
		        	 $.messager.alert('操作提示！','请选择一行','info');
		         }
		    }
		    
		    function saveObj(){
		        $('#managerInfoForm').form('submit',{
		        	url:url,
		            onSubmit: function(){
		            return $(this).form('validate');
		         },
		            success: function(result){
		                var result = eval('('+result+')');
		                if (result.errorMsg){
		                    $.messager.alert('操作失败',result.errorMsg,'error');
		                } else {
		                    $.messager.show({
			                    title: '操作成功',
			                    msg: result.successMsg
			                 });
		                        $('#dlg').dialog('close');        // close the dialog
		                        $('#managerDg').datagrid('reload');    // reload data
		                }
		            }
		        });
		    }
		    
		function destroyObj() {
			var row = $('#managerDg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('Confirm',
						'确定删除该数据？',
						function(r) {
							if (r) {
								$.post('manager/'+row.managerid+'/delete',
										function(result) {
									if (result.successMsg) {
										$.messager.show({
						                    title: '操作成功',
						                    msg: result.successMsg
						                 });
										$('#managerDg').datagrid('reload'); // reload the user data
										
									} else {
										$.messager.show({ // show error message
											title : 'Error',
											msg : result.errorMsg
										});
									}
								}, 'json');
							}
						});
			}
		}
	</script> 
</body>
</html>
