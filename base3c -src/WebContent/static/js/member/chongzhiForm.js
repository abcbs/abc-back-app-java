$(document).ready(function(){
	$("#popSave").unbind("click");
	$("#popSave").bind("click",cardChongzhi);
	
	$("#popSaveForm").CanYinValidate([
 	    {
 		    id:"rechargeCash",
 		    name:"充值金额",
 		    require:true,
 		    type:'numberLargeZero'
 		},
 		{
 		    id:"paidinCash",
 		    name:"实收金额",
 		    require:true,
 		    type:'numberLargeZero'
 		}
 		,
 		{
 		    id:"new_memberIntegral",
 		    name:"积分",
 		    require:false,
 		    type:'numberThanZero'
 		}
 	  ]);
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			cardChongzhi();
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
	
	
});

function chongzhi(money)
{
	var mv = $(money).val();
//	$("#new_memberIntegral").val(mv);
}
function cardChongzhi()
{
	//校验
	if(!$("#popSaveForm").CanYinValid())
	{
		return;
	}
	
	if(!cyValidRequire("new_paymentType"))
	{
		toastr.error("请选择付款方式!");
		return;
	}
	
	var rechargeCash = $("#rechargeCash").val();//充值金额
	var paidinCash = $("#paidinCash").val();//实收金额
	var zs = parseFloat(rechargeCash) - parseFloat(paidinCash);
	if((zs / parseFloat(paidinCash)) >= 3)
	{
		dialogBoxConfirm("充值","赠送金额超过实收3倍，赠送金额过大",function(){
		});
	}
	else if(zs >= parseFloat(paidinCash))
	{
		dialogBoxConfirm("充值","赠送金额超过或者等于实收金额，您确定继续吗？",function(){
			cardChongzhiSubmit();
		});
	}
	else if(zs > 0)
	{
		var cardNo = $("#cardNo").val();
		dialogBoxConfirm("充值","确定给予会员卡"+cardNo+"，"+zs+"元的优惠吗？",function(){
			cardChongzhiSubmit();
		});
	}
	else if(zs == 0)
	{
		var cardNo = $("#cardNo").val();
		dialogBoxConfirm("充值","确定给予会员卡"+cardNo+"，充值吗？",function(){
			cardChongzhiSubmit();
		});
	}
	else if(zs < 0)
	{
		dialogBoxConfirm("充值","实收不能大于充值金额",function(){
		});
	}
	
		
		return false;
}

function cardChongzhiSubmit()
{
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
			closebox();
			toastr.info(data.message);
			refreshPage();
		}
	});
}
function newDrawBill(html)
{
	if($(html).hasClass("small_but_piao_on"))
	{
		$(html).removeClass("small_but_piao_on").addClass("small_but_piao");
		$("#isDrawBill").val(0);
	}
	else
	{
		$(html).addClass("small_but_piao_on");
		$("#isDrawBill").val(1);
	}
}
function newPrintBill(html)
{
	if($(html).hasClass("small_but_piao_on"))
	{
		$(html).removeClass("small_but_piao_on").addClass("small_but_piao");
		$("#isPrint").val(0);
	}
	else
	{
		$(html).addClass("small_but_piao_on");
		$("#isPrint").val(1);
	}
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