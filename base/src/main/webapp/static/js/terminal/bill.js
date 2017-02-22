
window.ctx = "/";
var url = window.location.href;
if(url.indexOf('canyin-frontdesk') > 0)
{
	window.ctx = "/canyin-frontdesk";
}
$(document).ready(function(){
	refreshBill();
	
	var m=$('#jiantou');
	m.css({
		position: "absolute"
	});
	m.show();
	m.css({zIndex:999998, left:1200, top:790});
});

function refreshBill()
{
	second = 60;
	var billId = $("#currentBillId").val();
	var billType = $("#currentBillType").val();
	billChange(billId,billType);
}

function cardSelect(){
	second = 60;
	$.ajax({
		type:"get",
		url:window.ctx + '/self/terminal/ajax/cardSelect',
		data:null,
		cache:false,
		async:true,
		success:function(html) {
			$("#dishesAjaxContent").html(html);
		},
		error : function() {
		}
	});
}

function cardPay(){
	second = 60;
	var billId = $("#currentBillId").val();
	if(billId == null || billId == ""){
		toastr.error("请先点餐！");
		return;
	}
	window.location = window.ctx + '/self/terminal/cardPay?billId='+billId;
}

function bankPay(){
	second = 60;
	var billId = $("#currentBillId").val();
	if(billId == null || billId == ""){
		toastr.error("请先点餐！");
		return;
	}
	ProgressbarUtil.showG2();
	$("#cy-overlayDiv").css('margin-top', '-1025');
	$.ajax({
	    type:"get",
	    url:window.ctx+"/self/terminal/ajax/getBankCardNo?billId="+billId,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	if(data.statusCode == '200')
	    	{
	    		clearDaoshu();
	    		checkBillIsSettled(data.value);
//				window.location = window.ctx + '/self/terminal/paySuccess?billId='+billId;
	    	}
	    	else
	    	{
	    		window.location = window.ctx + '/self/terminal/payFailure';
	    	}
	    },
		error:function(){
			ProgressbarUtil.hideG2();
		}
	  });
}


var checkBillIsSettledTime = null;
var checkBillIsSettledTimes = 0;
function checkBillIsSettled(billId)
{
	//alert("checkBillIsSettled：第"+checkBillIsSettledTimes+"次");
	checkBillIsSettledTimes++;
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/ajax/checkBillIsSettled?billId="+billId,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	if(data.statusCode == '200')
	    	{
	    		clearTimeout(checkBillIsSettledTime);
	    		ProgressbarUtil.hideG2();
	    		window.location = window.ctx + '/self/terminal/paySuccess?billId='+billId;
	    	}
	    	else if(data.statusCode == '300')
	    	{
	    		//没有结账成功
	    		if(checkBillIsSettledTimes <= 30)
    			{
	    			checkBillIsSettledTime=setTimeout(function(){
		    			checkBillIsSettled(data.value);
		    		},5000);
    			}
	    		else
    			{
	    			ProgressbarUtil.hideG2();
	    			daoshu();
	    			dialogBoxConfirm("支付确认","操作超时,重新点菜请取消!",function(){
		    			window.location=window.ctx + '/self/terminal/index';
		    		},function(){
		    			window.location=window.ctx + '/self/terminal/index';
		    		});
    			}
	    	}
	    	else if(data.statusCode == '400')
	    	{
	    		//退出了
	    		ProgressbarUtil.hideG2();
	    		daoshu();
	    	}
	    	else if(data.statusCode == '500')
	    	{
	    		//Message
	    		ProgressbarUtil.hideG2();
	    		daoshu();
	    		dialogBoxConfirm("支付出错","点击确定重新支付，点击取消退出。"+data.message,function(){
	    		},function(){
	    			window.location=window.ctx + '/self/terminal/index';
	    		});
	    	}
	    },
		error:function(){
			ProgressbarUtil.hideG2();
			daoshu();
			clearTimeout(checkBillIsSettledTime);
			toastr.warning("支付出错！");
		}
	  });
}


function diancan(){
	second = 60;
	$.ajax({
		type:"get",
		url:window.ctx + '/self/terminal/ajax/diancan',
		data:null,
		cache:false,
		async:true,
		success:function(html) {
			$("#dishesAjaxContent").html(html);
		},
		error : function() {
		}
	});
}

function cardInfo(){
	second = 60;
	var cardNo = $("#cardNo").val();
	$.ajax({
		type:"get",
		url:window.ctx + '/self/terminal/ajax/cardInfo',
		data:'cardNo='+cardNo,
		cache:false,
		async:true,
		success:function(html) {
			$("#dishesAjaxContent").html(html);
		},
		error : function() {
		}
	});
}

var diancanDish_billId ='';
var diancanDish_billType ='';
var diancanDish_page =1;
function billChange(billId,billType)
{
	second = 60;
	diancanDish_billId = billId;
	diancanDish_billType = billType;
	$.ajax({
	    type:"get",
	    url:window.ctx+'/self/terminal/ajax/billContent?billId='+billId+'&billType='+billType,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(html){
	    	$("#billAjaxContent").html(html);
	    },
		error:function(){
		}
	  });
}


function deleteDish(p,bdId,isSet,dname){
	$(p).removeAttr("onclick");
	second = 60;
	var ibillId = $("#currentBillId").val();
	if(bdId)
	{
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/dishDelete/"+ibillId+"/"+bdId + "?isSet=" + isSet,
		    data:null,
		    cache:false,
		    success:function(data){
		    	refreshBill();
		    },
			error:function(){}
		  });	
	}
}

function deleteAllDish(){
	second = 60;
	var ibillId = $("#currentBillId").val();
	if(ibillId)
	{
		$.ajax({
		    type:"get",
		    url:window.ctx+"/self/terminal/dishDeleteAll/"+ibillId,
		    data:null,
		    cache:false,
		    success:function(data){
		    	refreshBill();
		    },
			error:function(){}
		  });	
	}
}


function validateCard(){
	second = 60;
	var cardNo = $("#cardNo").val();
	var cardPassword = $("#cardPassword").val();
	if(cardNo == "" || cardPassword == ""){
		toastr.error("用户名或密码不能为空！");
		return;
	}
	
	$.ajax({
		async:true,
		type:"GET",
		url:window.ctx+"/bill/validateCardNo",
		data:'cardNo=' + cardNo +'&cardPassword=' + escape(cardPassword),
		dataType:'json',
		success: function(msg){
			if(msg.message == 'true'){
				cardInfo();
			}else{
				toastr.error("用户名或密码错误！");
			}
		}
	});
}

function clickLeft(){
	second = 60;
	var st = $("div.FP_link_2").scrollLeft();
	$("div.FP_link_2").scrollLeft(st-147);
}

function clickRight(){
	second = 60;
	var st = $("div.FP_link_2").scrollLeft();
	$("div.FP_link_2").scrollLeft(st+147);
}

function clickUp(){
	second = 60;
	var st = $("div.box").scrollTop();
	$("div.box").scrollTop(st-225);
}

function clickDown(){
	second = 60;
	var st = $("div.box").scrollTop();
	$("div.box").scrollTop(st+225);
}


var synAddDishFlag = false;

function addDishes(dishesId){
	second = 60;
	if(!synAddDishFlag)
	{
		
		synAddDishFlag = true;
		var billStatus =$("#billStatus").val();
		if(billStatus == 3 || billStatus == 8)
		{
			//结账，反结账，撤单，不能再加菜
			toastr.warning("此账单已完结，不能再加菜!");
			synAddDishFlag = false;
			return;
		}
			//加菜
			var currentBillType = $("#currentBillType").val();
			var currentBillId = $("#currentBillId").val();
			$.ajax({
			    type:"get",
			    url:window.ctx+"/bill/jiacai/"+currentBillType+"/"+dishesId+"?billId="+currentBillId+'&isJudgeRm=1',
			    data:null,
			    cache:false,
			    success:function(data){
			    	synAddDishFlag = false;
			    	if(data.statusCode == '200')
			    	{
			    		//第一次外卖需要处理
			    		$("#currentBillId").val(data.value);
			    		$("#billType").val(data.type);
			    		refreshBill();
			    	}
			    	else if(data.statusCode == 400)
			    	{
			    		//沽清
			    		toastr.warning("此菜品数量不足!");
			    	}
			    },
				error:function(){
					synAddDishFlag = false;
				}
			  });
		
	}
	else
	{
		toastr.info("下单太快了，稍等片刻");
	}
}