$(document).ready(function(){
	initFrameSize();
	payFirstEnterPage();

	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"1",
		callBackFunction:function()
		{
			var billId = $("#currentBillId").val();
			var jump = window.ctx+"/bill/list?billId=" + billId;
			window.location = jump;
		}
	},
	{
		keyCode:"2",
		callBackFunction:function()
		{
			var billId = $("#currentBillId").val();
			if(billId)
			{
				var jump = window.ctx+"/bill/diancai?billId=" + billId;
				window.location = jump;
			}
			else
			{
				toastr.info("请选择账单");
			}
		}
	}
	,
	{
		keyCode:"3",
		callBackFunction:function()
		{
			var billId = $("#currentBillId").val();
			if(billId)
			{
				var jump = window.ctx+"/bill/payPage/" + billId;
				window.location = jump;
			}
			else
			{
				toastr.info("请选择账单");
			}
		}
	}
	,
	{
		keyCode:"4",
		callBackFunction:function()
		{
			$("#xiadan").click();
		}
	}
	,
	{
		keyCode:"enter",
		callBackFunction:function()
		{
			var jiezhangDiv = $("#pay_jiezhang_group").find("div[class*='jiezhang_on']");
			if(jiezhangDiv && jiezhangDiv.length > 0)
			{
				var obj = jiezhangDiv.eq(0);
				obj.click();
			}
		}
	}
	,
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/member';
		}
	}
	,
	{
		keyCode:"left",
		callBackFunction:function()
		{
			var jiezhangDiv = $("#pay_jiezhang_group").find("div[class^='jiezhang']");
			
			var ci = tabKeyAreaIndex;
			if(ci <= -1)
			{
				ci = -1;
			}
			else
			{
				ci=tabKeyAreaIndex-1;
			}
			tabKeyAreaIndex=ci;
			
			if(tabKeyAreaIndex >= -1)
			{
				jiezhangDiv.eq(tabKeyAreaIndex+1).removeClass("jiezhang_on");
			}
			jiezhangDiv.eq(tabKeyAreaIndex).addClass("jiezhang_on");
			
		}
	}
	,
	{
		keyCode:"right",
		callBackFunction:function()
		{
			var jiezhangDiv = $("#pay_jiezhang_group").find("div[class^='jiezhang']");
			
			var ci = tabKeyAreaIndex;
			if(ci >= jiezhangDiv.length-1)
			{
				ci = 0;
			}
			else
			{
				ci=tabKeyAreaIndex+1;
			}
			tabKeyAreaIndex=ci;
			
			if(tabKeyAreaIndex > 0)
			{
				jiezhangDiv.eq(tabKeyAreaIndex-1).removeClass("jiezhang_on");
			}
			jiezhangDiv.eq(tabKeyAreaIndex).addClass("jiezhang_on");
			
		}
	}
	,
	{
		keyCode:"7",
		callBackFunction:function()
		{
			$("#payedNotPrint").click();
		}
	}
	,
	{
		keyCode:"8",
		callBackFunction:function()
		{
			$("#payedAndPrint").click();
		}
	}
	],0);
	
});

var tabKeyAreaIndex = -1;
/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body_sec").css("width",1000+subWidth+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_sec").css("height",672+subHeight+"px");
		$(".list_a").css("height",670+subHeight+"px");
		$(".list_b").css("height",670+subHeight+"px");
	}
}

function payFirstEnterPage()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".list_b").css("width",659+subWidth+"px").effect("slide",300);
	}
}

function initPayWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".list_b").css("width",659+subWidth+"px");
		$(".list_b_in").css("width",658+subWidth+"px");
		$(".jiezhang_wrap").css("width",658+subWidth+"px");
		$(".tab_nav_wrap").css("width",658+subWidth+"px");
		$(".tab_nav").css("width",658+subWidth+"px");
		$(".jiezhang_list").css("marginLeft",10+subWidth/2+"px");
		$(".jiezhang_group").css("marginLeft",10+subWidth/2+"px");
		$(".jiezhang_over").css("width",658+subWidth+"px");
		
//		$(".jiezhang_list_in").css("width",596+subWidth+"px");
		
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".list_b_in").css("height",670+subHeight+"px");
		$(".jiezhang_wrap").css("height",620+subHeight+"px");
		$(".jiezhang_over").css("height",296+subHeight+"px");
		$(".jiezhang_over p").css("marginTop",115+subHeight/2+"px");
	}
}

//若当前选择支付方式为挂账，查询是否已经有挂账的支付方式，若有则不能再选择挂账的支付方式（只有一种挂账的支付方式）
function popPaymentType(url,cptId,paymentType,width){
	var billId = $("#currentBillId").val();
	jQuery.ajax({
		url: window.ctx + '/bill/haveCredit/'+billId+'?cptId='+cptId+'&paymentType='+paymentType,
		data: null,
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			if(data.value == '1'){
				toastr.error(data.message);
			}else{
				popForm('收款',url,width,'644');
			}
			
		}
	});
}

//账单结账
function billPay(isPrint,isForce){
	var billId = $("#currentBillId").val();
	//判断是否结账
	var isSettled = $.ajax({
		url : window.ctx + "/bill/ajax/isSettled",
		data : "billId=" + billId,
		async : false,
		dataType : String
	}).responseText;
	
	if(isSettled == '1'){
		toastr.warning("此账单已结账");
		refreshPayPage();
		return;
	}
	
	var payments = $("#payments").val();
	var needMoney = $("#needMoney").val();
	//验证账单是否有支付方式
	if(payments == null || payments == ""){
		dialogBoxConfirm('快速结账确认','现金支付：' + needMoney + '元',function(){
			jQuery.ajax({
				url: window.ctx + '/bill/saveMoneyPayment/' + billId + '?money=' + needMoney+ '&rid='+MathUtil.random(),
				data: null,
				type: "POST",
				dataType: "json",
				beforeSend: function(){
				},
				success: function(data){
					if(data.rel == '1'){
						payFinal(billId,isPrint,isForce);
					}else if(data.rel == '2')
						dialogBoxConfirm("结账","当前账单消费金额不满足最低消费金额 ，确认结账吗 ？",function(){
							payFinal(billId,isPrint,'true');
						});
					else{
						toastr.error("现金支付错误");
					}
				}
			});
		});
	}else{
		dialogBoxConfirm('结账确认','确认结账吗？',function(){
			payFinal(billId,isPrint,isForce);
		});
	}
}

function payFinal(billId,isPrint,isForce){
	//设置'结账打印'和'结账不打印'按钮点击不可用，防止重复提交重复结账
	$("#payedNotPrint").removeAttr("onclick");
	$("#payedAndPrint").removeAttr("onclick");
	jQuery.ajax({
		url: window.ctx + '/bill/pay/' + billId + '?isPrint=' + isPrint + '&isForce=' + isForce + '&rid='+MathUtil.random(),
		data: null,
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			//data.rel = 1:付款金额不足，不能结账,2:不满足最低消费，不能结账,3:打印失败
			if(data.rel == 1){
				$("#payedNotPrint").attr("onclick","pay(false,false)");
				$("#payedAndPrint").attr("onclick","pay(true,false)");
				if(!getPermissionNoToast("forcePay"))
				{
					var params = billId + ","+isPrint;
					popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=2&functionId='+params,'529','353');
					return;
				}
				
				//跳转强制结账界面
				popForm('无法完成结账',window.ctx + '/bill/pop/forcePay/' + billId + '?isPrint=' + isPrint,'529','335');
			}else if(data.rel == 2){
				dialogBoxConfirm("结账","当前账单消费金额不满足最低消费金额 ，确认结账吗 ？",function(){
					payFinal(billId,isPrint,'true');
				},function(){
					$("#payedNotPrint").attr("onclick","pay(false,false)");
					$("#payedAndPrint").attr("onclick","pay(true,false)");
				});
			}else if(data.rel == 3){
				$("#payedNotPrint").attr("onclick","pay(false,false)");
				$("#payedAndPrint").attr("onclick","pay(true,false)");
				toastr.error(data.message);
			}else if(data.rel == 0){
				var billType = $("#billType").val();
				var isPayEnterDesk = data.navTabId;//0:哪也不去,1:进入餐台页面,2:进入快餐页面
				if(isPayEnterDesk == '1')
				{
					//结账后立即进入餐台页面
					var jump = window.ctx+"/index";
					window.location = jump;
				}
				else if(isPayEnterDesk == '2')
				{
					//跳转快餐模块
		    		window.location = window.ctx+'/fastfood/diancai';
		    		return;
				}
				else
				{
					//结账成功，跳转结账成功界面
					popForm('结账成功',window.ctx + '/bill/pop/paySuccess','529','335');
					//刷新账单明细状态
					BillChange(billId);
				}
			}else{
				refreshPayPage();
				toastr.error(data.message);
			}
		}
	});
}

//结账
function pay(isPrint,isForce)
{
	if(isPrint == '1' && !getPermissionNoToast("payPrint"))
	{
		var params = isPrint + ","+isForce;
		popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=1&functionId='+params,'529','353');
		return;
	}
	if(isPrint == '0' && !getPermissionNoToast("payNoPrint"))
	{
		var params = isPrint + ","+isForce;
		popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=1&functionId='+params,'529','353');
		return;
	}
	
	var billStatus = $("#billStatus").val();
	if(billStatus == 9){
		dialogBoxConfirm("结账","部分菜肴未下单，确认结账吗?",function(){
			billPay(isPrint,isForce);
		});
	}else{
		billPay(isPrint,isForce);
	}
}


function permissionCallBack(isHasPermission,functionType,id)
{
	if(functionType == '1')
	{
		var params = id.split(",");
		var isPrint = params[0];
		var isForce = params[1];
		var billStatus = $("#billStatus").val();
		if(billStatus == 9){
			dialogBoxConfirm("结账","部分菜肴未下单，确认结账吗?",function(){
				billPay(isPrint,isForce);
			});
		}else{
			billPay(isPrint,isForce);
		}
	}
	else if(functionType == '2')
	{
		//跳转强制结账界面
		var params = id.split(",");
		var billId = params[0];
		var isPrint = params[1];
		popForm('无法完成结账',window.ctx + '/bill/pop/forcePay/' + billId + '?isPrint=' + isPrint,'529','335');
	}
	else if(functionType == '3')
	{
		popForm('退菜原因',window.ctx+'/bill/pop/tuicaiReason/'+id,'529','644');
	}else if(functionType == '4'){
		//套餐退菜
		var isSet = $("#isSet").val();
		popForm('退菜原因',window.ctx+'/bill/pop/tuicaiReason/'+id+"?isSet="+isSet,'529','644');
	}
	else if(functionType == '5'){
		//赠菜
		var ids = id.split(",");
		diancaiBill_zengcai(ids[0],ids[1]);
	}
}

function refreshPayPage(isMemeberCard)
{
	var billId = $("#currentBillId").val();
	payChange(billId,isMemeberCard);
}
function payChange(billId,isMemeberCard)
{
	$.ajax({
	    type:"get",
	    url:window.ctx+'/bill/ajax/payContent/'+billId,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(html){
	    	$("#ajaxPayContent").html(html);
	    	initPayWidth();
	    	if(isMemeberCard){
	    		BillChange(billId);
	    	}
	    	
	    	/**
	    	 * 客显处理
	    	 */
	    	var payableCost = $("#payableCost_lable").text();
	    	var oddChange = $("#oddChange_lable").text();
	    	var needMoney = $("#needMoney_lable").text();
	    	
	    	kexian(payableCost,'1');//客显 显示应付金额
	    	
	    	if(needMoney == "0"){
	    		setTimeout(function(){kexian(eval(payableCost + "+" + oddChange)+"",'2');}, 1000);//收款
	    		setTimeout(function(){kexian(oddChange,'3');}, 2000);//找回
	    		setTimeout(function(){kexian(payableCost,'1');}, 4000);
	    	}
	    },
		error:function(){
		}
	  });
}
