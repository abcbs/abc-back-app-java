$(document).ready(function(){
	initFrameSize();
	
	$("#moduleSubmit").bind("click",saveModule);
	$("#orderSubmit").bind("click",saveOrder);
	$("#opSubmit").bind("click",saveOp);
	$("#otherSubmit").bind("click",saveOther);
	
  	  
	syncLock = new SyncLock("moduleSubmit","sure-loading");	
});
var syncLock = null;

  function setTab(name,cursel,n){
	  
	for (i=1;i<=n ;i++ )
	{
		var oMenu = document.getElementById(name+i);
		var oCon = document.getElementById('system_'+name+'_'+i);
		oMenu.className = i==cursel?'system_hove':'';
		oCon.className = i==cursel?'current':'undis';
	}
	//功能设置
	if(cursel == '1')
	{
		syncLock = new SyncLock("moduleSubmit","sure-loading");	
	}
	//操作设置
	if(cursel == '2')
	{
		syncLock = new SyncLock("opSubmit","sure-loading");	
	}
	
	//预订设置
	if(cursel == '3')
	{
		syncLock = new SyncLock("orderSubmit","sure-loading");	
		$("#orderForm").CanYinValidate([
  	    {
  		    id:"messageTel",
  		    name:"短信通知号码",
  		    require:false,
  			length:[11,11]
  		},
  		{
  		    id:"orderWarnTime",
  		    name:"预警时间",
  		    require:true,
  		    type:'intNumberThanZero'
  		},
  		{
  		    id:"orderLockTime",
  		    name:"锁定时间",
  		    require:true,
  		    type:'intNumberThanZero'
  		},
  		{
  		    id:"orderExpireTime",
  		    name:"到期保留时间",
  		    require:true,
  		    type:'intNumberThanZero'
  		}
  	  ]);
	}
	
	//其他设置
	if(cursel == '4')
	{
		syncLock = new SyncLock("otherSubmit","sure-loading");	
	}
	
	//修改密码
	if(cursel == '5')
	{
		syncLock = new SyncLock("psdSubmit","sure-loading");	
		$("#psdForm").CanYinValidate([
  	    {
  		    id:"password",
  		    name:"原始密码",
  		    require:true,
  			length:[1,30]
  		},
  		{
  		    id:"loginPassword",
  		    name:"新密码",
  		    require:true,
  			length:[1,30]
  		}
  		,
  		{
  		    id:"confirmPassword",
  		    name:"确认新密码",
  		    require:true,
  			length:[1,30],
  			equalTo:"loginPassword",
  			equalToName:"新密码"
  		}
  	  ]);
	}
	
	//联系我们
	if(cursel == '6')
	{
		
	}
	
  }
  function saveModule()
{
		if(!syncLock.Start()) 
		   {
			   return;
		   }
		var url = $("#moduleForm").attr("action");
		jQuery.ajax({
			url: url,
			data: $('#moduleForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function(){
			},
			success: function(data){
				toastr.success(data.message);
				$("#moduleId").val(data.rel);
				syncLock.End();
				window.location = window.ctx + "/system/setting";
			},
			error:function(){
				syncLock.End();
			}
		});
	
	
}

function saveOrder()
{
	if($("#orderForm").CanYinValid())
	{
		if(!syncLock.Start()) 
		   {
			   return;
		   }
		var url = $("#orderForm").attr("action");
		jQuery.ajax({
			url: url,
			data: $('#orderForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function(){
			},
			success: function(data){
				toastr.success(data.message);
				$("#orderForm").children("#id").val(data.rel);
				syncLock.End();
			},
			error:function(){
				syncLock.End();
			}
		});
	}
	
	
}


function saveOp(){
	 if(!syncLock.Start()) 
	   {
		   return;
	   }
	var url = $("#opForm").attr("action");
	jQuery.ajax({
		url: url,
		data: $('#opForm').serialize(),
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			toastr.success(data.message);
			$("#opForm").children("#id").val(data.rel);
			syncLock.End();
		},
		error:function(){
			syncLock.End();
		}
	});
}
function saveOther(){
	if(!syncLock.Start()) 
	   {
		   return;
	   }
	var url = $("#otherForm").attr("action");
	jQuery.ajax({
		url: url,
		data: $('#otherForm').serialize(),
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			toastr.success(data.message);
			$("#otherForm").children("#otherId").val(data.rel);
			syncLock.End();
		},
		error:function(){
			syncLock.End();
		}
	});
}
	
/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
	if(subWidth > 0)
	{
		$(".body_system").css("width",998+subWidth+"px");
		$(".title_system").css("width",994+subWidth+"px");
		$(".system_content").css("width",994+subWidth+"px");
		$(".system_title").css("width",994+subWidth+"px");
		$("h3",".title_system").css("width",524+subWidth+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_system").css("height",668+subHeight+"px");
		$(".system_content").css("height",616+subHeight+"px");
	}
	
}

function changeModule(obj)
{
	var jq = $(obj);
	var id = jq.attr("id");
	if(jq.hasClass("but_stystem_on_p"))
	{
		jq.removeClass("but_stystem_on_p").addClass("but_stystem_p");
		$("#"+id).val(0);
	}
	else
	{
		jq.removeClass("but_stystem_p").addClass("but_stystem_on_p");
		$("#"+id).val(1);
	}
}

