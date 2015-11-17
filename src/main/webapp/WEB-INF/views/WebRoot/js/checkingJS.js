$(function (){
	//验证是否为空
	$.validator.addMethod("notnull", function (value, element){
    	var customMsg = '';
		var result    = true;
		if (value!="") {
		     result    = true;
		} else {
		     customMsg = '不能为空';
		     result    = false;
		}
		$.validator.messages.notnull = customMsg;
		return result;
    }); 
	
   //验证长度,value:input value值 ,param,传入的值
	$.validator.addMethod('studentIdLength', function (value, element,param) {
		var customMsg = '';
		var result    = true;
		if(value.length==0){
			customMsg = '不能为空';
		     result    = false;
		} else if (value.length < param[0]) {
		     customMsg = '长度太短';
		     result    = false;
		} else if (value.length > param[1]){
		     customMsg = '长度太长';
		     result    = false;
		}
		$.validator.messages.studentIdLength = customMsg;
		return result;
	});

	//通用方法post验证,value:input value值 ,param:param[0],当前input对象.param[1]:url路径
	$.validator.addMethod('studentIdChecking', function (value, element ,param) {
		var _data;
		$.ajax({
		   type: "POST",
		   url: param[1],
		   data: "value="+$(param[0]).attr("value")+"&title="+$(param[0]).attr("name"),
		   success: function(data){
			_data = data;
		   },
		   async: false
		});
		var customMsg = '';
		var result = true;
		if (_data!="") {
		     customMsg = _data;
		     result    = false;
		} else {
			if($(param[0]).attr("name")=="studentID"){
				var error = addReturnChecking(param[0],$(param[0]).attr("name"));
			}else{
				var error = "";
			}
			if(error!=""){
				customMsg = error;
			    result = false;
			}
		}
		$.validator.messages.studentIdChecking = customMsg;
		return result;
	});
	
	//通用方法post验证,value:input value值 ,param:param[0],当前input对象.param[1]:url路径
	$.validator.addMethod('borrowName', function (value, element ,param) {
		var _data;
		$.ajax({
		   type: "POST",
		   url: param[1],
		   data: "value="+$(param[0]).attr("value")+"&value1="+$(param[2]).attr("value")+"&title="+$(param[0]).attr("name"),
		   success: function(data){
			_data = data;
		   },
		   async: false
		});
		var customMsg = '';
		var result = true;
		if (_data!="") {
		     customMsg = _data;
		     result    = false;
		} else {
			if($(param[0]).attr("name")=="studentID"){
				var error = addReturnChecking(param[0],$(param[0]).attr("name"));
			}else{
				var error = "";
			}
			if(error!=""){
				customMsg = error;
			    result = false;
			}
		}
		$.validator.messages.borrowName = customMsg;
		return result;
	});
	
	//验证时间大小,value:input value值 ,param[0]:url路径
	$.validator.addMethod('timeChecking', function (value, element ,param) {
		var _data;
		$.ajax({
		   type: "POST",
		   url: param[0],
		   data: "value="+$("#timeBegin").attr("value")+"&valueEnd="+$("#timeEnd").attr("value")+"&title=timeEnd",
		   success: function(data){
			_data = data;
		   },
		   async: false
		});
		var customMsg = '';
		var result = true;
		if (_data!="") {
		     customMsg = _data;
		     result    = false;
		}
		
		$.validator.messages.timeChecking = customMsg;
		return result;
	});
	
	//验证登陆,value:input value值 ,param:param[0],当前input对象.param[1]:url路径
	$.validator.addMethod('lognChecking', function (value, element ,param) {
		var _data;
		$.ajax({
		   type: "POST",
		   url: "logo/LognChecking",
		   data:"value="+$("#logo").attr("value")+
		   		"&valuePassword="+$("#password").attr("value"),
		   success: function(data){
			_data = data;
		   },
		   async: false
		});
		var customMsg = '';
		var result = true;
		if (_data!="") {
		     customMsg = _data;
		     result    = false;
		}
		$.validator.messages.lognChecking = customMsg;
		return result;
	});
});