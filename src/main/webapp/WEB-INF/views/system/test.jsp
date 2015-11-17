<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
      <title>Basic DataList - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/bootstrap/easyui.css" />">
		 <link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/default/easyui.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/icon.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/color.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/demo/demo.css" />">
		<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.min.js" />"></script> 
		<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.easyui.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/static/easyui/1.4/locale/easyui-lang-zh_CN.js" />"></script>
		<script type="text/javascript" src="<c:url value="/static/js/main.js" />"></script>
		<script type="text/javascript" src="<c:url value="/static/js/validateboxRules.js" />"></script>
    	<link href="<c:url value="/static/css/font-awesome-4.2.0/css/font-awesome.min.css" />" rel="stylesheet">
</head>
<body>
<div style="padding-top:15px; padding-left:5px; padding-right:15px; ">	
		 
			<div id="managerSd" class="easyui-panel" title="查询条件" style="height:100px;padding:10px; margin-bottom:10px;" data-options="collapsible:true">
		       <form action="" id="managerSdForm"> 
		       <div>
		        	   姓名：<input type="text" id="name" name="name">
	                                                          班级：<input type="text" id="classname" name="classname">
		       </div>           
		        <div>
		        	  电话：<input type="text" id="cellphone" name="cellphone">
	                                                          邮箱：<input type="text" id="email" name="email">
	                 <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchLoad()">查询</a>
		       </div>         
	           
	            
	            </form> 
		    </div>  
	           	
     	<div id="managerTb" style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-plus fa-lg" plain="true" onclick="newObj()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-pencil-square-o fa-lg" plain="true" onclick="editObj()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-trash-o fa-lg" plain="true" onclick="destroyObj()">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="fa fa-crosshairs fa-lg" plain="true" onclick="viewObj()">查看</a>
        </div>           
	    <table id="managerDg"  title="管理员列表"   url="list"
	    		style="width:700px;height:620px"
	    		sortName="id" sortOrder="asc" rownumbers="true"  
	    		pagination="true" pageSize="20" pageList="[10,20,50,100]"
	            data-options="singleSelect:true,method:'post',toolbar:'#managerTb'">
	        <thead>
	            <tr>
	            	<th field="id" sortable="true" hidden="true" >ID</th>
	                <th field="name" sortable="true" width="100px">姓名</th>
	                <th field='classname' sortable="true" width="100px">班级</th>
	                <th field='cellphone' sortable="true" width="100px">电话</th>
	                <th field='email' sortable="true" width="100px">邮箱</th>
	                
	            </tr>
	        </thead>
	    </table> 
	    <div id="dlg" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" href="<c:url value="/newtest/info" />">
         </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveObj()" style="width:90px" id="save">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	    </div>
	</div> 
	<script type="text/javascript">
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
        $('#dlg').dialog('open').dialog('setTitle','添加学生');
        $('#studentInfoForm').form('clear');
        url = "insert";
    }
    function editObj(){
    	$('#save').show();
        var row = $('#managerDg').datagrid('getSelected');
        if (row != null){
            $('#dlg').dialog('open').dialog('setTitle','编辑学生信息');
            $('#studentInfoForm').form('load',row.id+'/edit');
            url = 'update';
         }else{
        	 $.messager.alert('操作提示！','请选择一行','info');
         }
    }
    
    function viewObj(){
        var row = $('#managerDg').datagrid('getSelected');
        if (row != null){
            $('#dlg').dialog('open').dialog('setTitle','查看学生信息');
            $('#studentInfoForm').form('load',row.id+'/view');
            $('#save').hide();
         }else{
        	 $.messager.alert('操作提示！','请选择一行','info');
         }
    }
    
    function saveObj(){
        $('#studentInfoForm').form('submit',{
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
	console.log(row);
	if (row) {
		$.messager.confirm('Confirm',
				'确定删除该数据？',
				function(r) {
					if (r) {
						$.post(row.id+'/delete',
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
<style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
</html>