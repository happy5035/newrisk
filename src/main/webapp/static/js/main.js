/**
 * 
 */
//打开标签页
function openTab(url, title){
		if ($('#tt').tabs('exists',title)){
				$('#tt').tabs('select',title);
			} else {
				$('#tt').tabs('add',{
					title:title,
					href:url,
					closable:true
				});
			}
}



function submitFormAjax(ff){
	$(ff).form('submit');	
}

function submitFormValidate(ff){
    $(ff).form('submit',{
        onSubmit:function(){
            return $(this).form('enableValidation').form('validate');
        }
    });
}

function clearForm(ff){
    $(ff).form('clear');
}



function submitForm(ff){
	$(ff).submit();
}

$("#uForm a").click(function (e){
	e.preventDefault();
	$('#uForm').submit();
});
$("#gForm a").click(function (e){
	e.preventDefault();
	$('#gForm').submit();
});

$("#dForm a").click(function (e){
	e.preventDefault();
	$('#dForm').submit();
});
$("#cForm a").click(function (e){
	e.preventDefault();
	$('#cForm').submit();
});