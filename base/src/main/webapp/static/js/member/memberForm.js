$(document).ready(function(){
	$("#popSave").unbind("click");
	$("#popSave").bind("click",saveRestMemberInfo);
	$("#birthday").datepicker({
	      changeMonth: true,
	      changeYear: true
	    });
	$("#popSaveForm").CanYinValidate([
	    {
		    id:"name",
		    name:"会员名称",
		    require:true,
			length:[1,20]
		},
		{
		    id:"mobile",
		    name:"手机",
		    require:true,
			length:[11,11]
		}
		,
		{
		    id:"email",
		    name:"邮箱",
		    require:false,
		    type:'email'
		}
		,
		{
		    id:"birthday",
		    name:"生日",
		    require:false,
		    type:'dateISO'
		}
		,
		{
			keyCode:"shift_tab",
			callBackFunction:function()
			{
				window.location=window.ctx+'/order';
			}
		}
	  ]);
	
	
});


function saveRestMemberInfo()
{
	if(!$("#popSaveForm").CanYinValid()){
		return;
	}
	var namePattern=/[`~!@#$%^&*() _+<>?:"{},.\/;'[\]]/im;
	if(namePattern.test($("#name").val())){
    	toastr.error("不能包含特殊字符!");
        return;
    }
	var mobilePattern = /^1[3|4|5|8][0-9]\d{4,8}$/; 
    if(!(mobilePattern.test($("#mobile").val()))){ 
        toastr.error("请正确填写手机号码！");
        return; 
    }
	var url = $("#popSaveForm").attr("action");
	jQuery.ajax({
		url: url,
		data: $('#popSaveForm').serialize(),
		type: "POST",
		dataType: "json",
		beforeSend: function()
		{  
		},
		success: function(data)
		{
			if(data.statusCode == "200")
			{
				closebox();
				toastr.info(data.message);
				refreshPage();
			}
			else
			{
				toastr.error(data.message);
			}
			
			
		}
	});
	return false;
}
