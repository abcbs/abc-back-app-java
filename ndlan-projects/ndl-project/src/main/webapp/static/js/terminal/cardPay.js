window.ctx = "/";
var url = window.location.href;
if(url.indexOf('canyin-frontdesk') > 0)
{
	window.ctx = "/canyin-frontdesk";
}

$(document).ready(function(){
	
});

function pay(){
	ProgressbarUtil.showG2();
	$("#cy-overlayDiv").css('margin-top', '-1025');
	second = 60;
	var cardNo = $("#cardNo").val();
	var cardPassword = $("#cardPassword").val();
	if(cardNo == "" || cardPassword == ""){
		toastr.error("用户名或密码不能为空！");
		ProgressbarUtil.hideG2();
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
				confirmPay();
			}else{
				toastr.error("用户名或密码错误！");
			}
			ProgressbarUtil.hideG2();
		},
		error:function(){
			ProgressbarUtil.hideG2();
		}
	});
}

function confirmPay(){
	second = 60;
	var cardNo = $("#cardNo").val();
	var cardPassword = $("#cardPassword").val();
	var billId = $("#billId").val();
	$.ajax({
		async:true,
		type:"GET",
		url:window.ctx+"/self/terminal/ajax/cardPayUpdate",
		data:'cardNo=' + cardNo +'&cardPassword=' + escape(cardPassword)+'&billId='+billId,
		dataType:'json',
		success: function(msg){
			if(msg.statusCode == '200'){
				var billId = $("#billId").val();
				window.location = window.ctx + '/self/terminal/paySuccess?billId='+billId;
			}else{
				toastr.error(msg.message);
			}
		}
	});
}

