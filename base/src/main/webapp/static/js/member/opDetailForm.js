function changTab(cardOperationType,tab)
{
	var mcId = $("#mcId").val();
	var showType = $("#showType").val();
	var url = window.ctx+'/member/pop/opDetail/createForm/'+mcId+'?cardOperationType='+cardOperationType+'&showType='+showType;
	
	refreshPopForm(url,function(){
	});
}


function goPage(cardOperationType,pageNum)
{
	var mcId = $("#mcId").val();
	if(cardOperationType == 1 || cardOperationType ==2)
	{
		var url = window.ctx+'/member/pop/opDetail/createForm/'+mcId+'?cardOperationType='+cardOperationType+'&page='+pageNum;
		
		refreshPopForm(url,function(){
		});
	}
	
}
function changeCardStatus(status)
{
	if(status == 2)
	{
		//停用
		if(!getPermission("tingyong"))
		{
			return;
		}
		
	}
	if(status == 3)
	{
		//挂失
		if(!getPermission("guashi"))
		{
			return;
		}
	}
	
	$(".tqyhyk").find("a").removeClass("tqyhyk_on");
	$("#changeCardStatus_"+status).addClass("tqyhyk_on");
	$("#popCardStatus").val(status);
}

function popConfirm(cardOperationType)
{
	if(cardOperationType == 1 || cardOperationType ==2)
	{
		closebox();
	}
	else
	{
		var mcId = $("#mcId").val();
		var cardStatus = $("#popCardStatus").val();
		if(mcId && cardStatus)
		{
			 $.ajax({
				    type:"get",
				    url:window.ctx+'/member/changeCardStatus/'+mcId+'/'+cardStatus,
				    data:null,
				    cache:false,
				    async:true,
				    success:function(data){
				    	closebox();
				    	toastr.success(data.message);
				    	refreshPage();
				    },
					error:function(){
						toastr.warning("操作失败");
					}
			 });
			
		}
		
	}
}