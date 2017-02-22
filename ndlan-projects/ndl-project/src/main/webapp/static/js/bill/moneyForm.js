$(document).ready(function(){
	$("#money").sudokuShow();
	$("#popSave").bind("click",savePay);
	$("#popSaveForm").CanYinValidate([
  	    {
  		    id:"money",
  		    name:"金额",
  		    require:false,
  		    type:'number'
  		}
  	  ]);
	
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			savePay();
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

//同步锁，每个函数一个
var syncLock = new SyncLock("popSave");

function savePay()
{
	$("#popSave").unbind("click",savePay);
	if($("#popSaveForm").CanYinValid())
	{
		//针对自助终端，可以支持银联支付的,非终端版本要注释掉此段
		/*****2014-05-15 add by daiyu start ********/
//		var paymentType = $("#paymentType").val();
//		if(paymentType == 2)
//		{
//			bankCardPay();
//			return;
//		}
		/*****2014-05-15 add by daiyu end ********/
		
		if(!syncLock.Start()){
			return;
		}
		
		var url = $("#popSaveForm").attr("action");
		jQuery.ajax({
			url: url+'?rid='+MathUtil.random(),
			data: $('#popSaveForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				closebox();
				refreshPayPage();
				syncLock.End();
			},
			error:function(){
				syncLock.End();
			}
		});
	}
	return false;
}



var isFirstSubmit = true;
function bankCardPay()
{
	if(isFirstSubmit)
	{
		isFirstSubmit = false;
		ProgressbarUtil.showG2();
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/ajax/bankCardPay",
		    data:$('#popSaveForm').serialize(),
		    cache:false,
		    async:true,
		    success:function(data){
		    	ProgressbarUtil.hideG2();
		    	isFirstSubmit = true;
		    	if(data.statusCode == '200')
		    	{
	    			checkBillStatus(data.value);
		    	}
		    	else if(data.statusCode == '300')
		    	{
		    		toastr.warning("应付金额应该等于刷卡金额!");
		    	}
		    	else
		    	{
		    		toastr.warning("支付出错！");
		    	}
		    	closebox();
		    },
			error:function(){
				isFirstSubmit = true;
				ProgressbarUtil.hideG2();
			}
		  });
	}
}


function checkBillStatus(billId)
{
	dialogBoxConfirm("支付确认","支付成功请点击确定，失败点取消后重新点击刷卡!",function(){
		BillChange(billId);
		refreshPayPage();
	});
	
//	clearInterval(checkBillStatusTime);
//	checkBillStatusTime = setTimeout(function(){  
//		alert("check");
//		$.ajax({
//		    type:"get",
//		    url:window.ctx+"/bill/ajax/bankCardPay",
//		    data:$('#popSaveForm').serialize(),
//		    cache:false,
//		    async:true,
//		    success:function(data){
//		    	isFirstSubmit = true;
//		    	if(data.statusCode == '200')
//		    	{
//		    		BillChange(data.value);
//		    		refreshPayPage();
//		    	}
//		    	else
//		    	{
//		    		toastr.warning("支付出错！");
//		    	}
//		    	ProgressbarUtil.hideG2();
//		    	checkBillStatus();
//		    },
//			error:function(){
//			}
//		  });
//		
//	},3000);  


}
