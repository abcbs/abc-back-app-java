$(document).ready(function(){
	$("#eatTime").timeSelect();
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
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
	
	$("input[name^='selectTabId_']").bind('input propertychange', function() {
		alert("c");
	});
	
});


$(function(){
	//菜单隐藏展开
	var tabs_i=0
	$('.vtitle').click(function(){
		var _self = $(this);
		var j = $('.vtitle').index(_self);
		if( tabs_i == j ) return false; tabs_i = j;
		$('.vtitle em').each(function(e){
			if(e==tabs_i){
				$('em',_self).removeClass('v01').addClass('v02');
			}else{
				$(this).removeClass('v02').addClass('v01');
			}
		});
		$('.vcon').slideUp().eq(tabs_i).slideDown();
	});
});

function cloudSelectTab(ctx,index,tableAreaId,a)
{
	popForm('选择餐台',ctx+'/index/pop/table/select?popTableType=2&from=yuding&tabId=selectTabId_'+index+'&tabNo=selectTabNo_'+index+'&tableAreaId='+tableAreaId,'880','644');
}

function cloudBillReviewPass(billId,takeoutOrderDiv)
{
	if(!$("#orderPeople").canyinValidateCh(40)) {
		toastr.error("请正确填写预订人!");
		return;
	}
	
	if(!$("#mobile").canyinVlidateMobile()) {
		toastr.error("请正确填写手机号!");
		return;
	}
	
	if(!$("#eatTime").canyinValidateRequire()) {
		toastr.error("请选择就餐时间!");
		return;
	}
	
	if(!$("#peopleNum").canyinValidateNumber(1,20)) {
		toastr.error("请正确填写就餐人数!");
		return;
	}

	//先判断是否选择了餐台
	var isOk = true;
	var inputs = $("input[id^='selectTabId_']");
	
	var billNo = "";
	var tabIds = "";
	inputs.each(function(i){
		var input = $(this);
		var tabNo = i+1;
		if(!input.val())
		{
			billNo += tabNo;
			if(inputs.length != i+1){
				billNo += ",";
			}
			isOk = false;
		}
		
		tabIds += input.val() + ","
	});
	
	if(!isOk)
	{
		toastr.error("没有选择餐桌");
		return;
	}
	
	if(!checkSendTime()){
		toastr.error("就餐时间不能小于当前时间！");
		return;
	}
	
	if(billId)
	{
		var orderPeople = $("#orderPeople").val();
		var mobile = $("#mobile").val();
		var eatTime = $("#eatTime").val();
		var peopleNum = $("#peopleNum").val();
		var gender = $("input[name='gender']:checked").val();
		popForm('审核通过',
				window.ctx+'/cloud/pop/cloudBillReviewPass?billId='+billId
				+"&takeoutOrderDiv="+takeoutOrderDiv
				+"&tabIds="+tabIds
				+"&isXiadan=0"
				+"&orderPeople="+orderPeople
				+"&mobile="+mobile
				+"&eatTime="+eatTime
				+"&peopleNum="+peopleNum
				+"&gender="+gender
				,'880','644');
	}
}

function cloudBillReviewFailed(billId,takeoutOrderDiv)
{
	if(billId)
	{
		popForm('审核未通过',window.ctx+'/cloud/pop/cloudBillReviewFailed?billId='+billId+"&takeoutOrderDiv="+takeoutOrderDiv,'880','644');
	}
}

/**
 * 判断送餐时间是否大于当前时间
 * @returns {Boolean}
 */
function checkSendTime(){
	var sendTime=$("#eatTime").val();
	var sendTimeDate = new Date(sendTime.replace("-", "/").replace("-", "/"));
	var currentTime = new Date(C_CURRENTTIME.replace("-", "/").replace("-", "/"));
	if(currentTime > sendTimeDate){
	 	return false;
	}else{
		return true;
	}
}