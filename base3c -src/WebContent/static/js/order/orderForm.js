$(document).ready(function(){
	$("#popSave").bind("click",saveOrder);
	$("#telphone,#telphoneSelect").sudoku();
	$("#peopleNum,#peopleNumSelect").sudoku();
	$("#prepay,#prepaySelect").sudoku();
	$("#orderTime,#timeSelectBtn").timeSelect();
	
	 $("#popSaveForm").CanYinValidate([
      	    {
      		    id:"orderPeopleName",
      		    name:"预订人",
      		    require:true,
      			length:[1,15]
      		}
      	    ,
    		{
    		    id:"orderTime",
    		    name:"预订时间",
    		    require:true,
    		    type:'date'
    		}
      	    ,
      		{
      		    id:"telphone",
      		    name:"联系电话",
      		    require:true,
      			length:[11,11],
      			type:"number"
      		}
      		,
      		{
      		    id:"tabNo",
      		    name:"餐台",
      		    require:true
      		}
      		,
      		{
      		    id:"peopleNum",
      		    name:"就餐人数",
      		    require:false,
      			type:"intNumberThanZero",
      			length:[0,4]
      		}
      		,
      		{
      		    id:"prepay",
      		    name:"预付款",
      		    require:false,
      			type:"numberThanZero"
      		}
      	  ]);
	 
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveOrder();
		}
	},
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			closebox();
		}
	}
	],1);
	
	//是否进入点餐
	$("a","#isEnterDiancaiPageDiv").bind("click",function(){
		var a = $(this);
		if(!a.hasClass("but_a_on"))
		{
			$("a","#isEnterDiancaiPageDiv").removeClass("but_a_on").addClass("but_a");
			a.removeClass("but_a").addClass("but_a_on");
			$("#isEnterDiancaiPage").val(a.attr("value"));
		}
	});
	
});


/**
 * 根据电话查找会员
 */
function getMemberByTel()
{
	var v = $("#telphone").val();
	if(v && v.length == 11)
	{
		$.ajax({
		    type:"get",
		    url:window.ctx+"/member/ajax/getMemberByTel/"+v,
		    data:null,
		    cache:false,
		    success:function(data){
		    	if(data.statusCode == '200')
	    		{
		    		$("#mbId").val(data.rel);
		    		$("#orderPeopleName").val(data.message);
	    		}
		    	else
		    	{
		    		toastr.warning("没有此会员信息");
		    	}
		    	
		    },
			error:function(){}
		  });
		
	}
}

//同步锁，每个函数一个
var syncLock = new SyncLock("popSave");

function saveOrder()
{
	    //校验
	if(!$("#popSaveForm").CanYinValid())
	{
		return;
	}
	
	var orderTime=new Date(($("#orderTime").val()).replace(/-/g,"/"));
    var nowTime=new Date();
    if(orderTime < nowTime)
    {
    	toastr.error("预订时间不能小于当前时间!");
       return ;
    }
	   
	   if(!syncLock.Start()) 
	   {
		   return;
	   }
	   
	   $("#popSave").unbind();
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
				var isOK = data.statusCode;
				if(isOK == '200')
				{
					//预订成功
					var isEnterDiancaiPage = data.rel;
					if(isEnterDiancaiPage && isEnterDiancaiPage=='1')
					{
						window.location = window.ctx + '/bill/orderDiancai?orderId='+data.forwardUrl;
					}
					else
					{
						refreshPage();
						closebox();
						toastr.success(data.message);
					}
				}
				else
				{
					toastr.error(data.message);
				}
				syncLock.End();
				$("#popSave").bind("click",saveOrder);
			},
			error:function(){
				syncLock.End();
				$("#popSave").bind("click",saveOrder);
			}
		});
		return false;
}

function cyValidRequire(inputId){
	var obj = $("#"+inputId);
	var v = obj.val();
	if(!v)
	{
		return false;
	}
	return true;
}

