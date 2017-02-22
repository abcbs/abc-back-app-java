$(document).ready(function(){
	$("#sendTime").timeSelect();
//	refreshDish();
});

function cloudBillReviewPass(billId,takeoutOrderDiv,isXiadan)
{
	if(!$("#orderPeople").canyinValidateCh(40)) {
		toastr.error("请正确填写联系人!");
		return;
	}
	
	if($.trim($("#mobile").val()).length != 0 && !$("#mobile").canyinValidateNumber(11,11)) {
		toastr.error("请正确填写手机号!");
		return;
	}
	
	if(!$("#linkmanTel").canyinValidateTelephone()) {
		toastr.error("请正确填写电话号码!");
		return;
	}
	
	var valueMobile = $.trim($("#mobile").val());
	var valueLinkmanTel = $.trim($("#linkmanTel").val());
	if(valueMobile.length == 0 && valueLinkmanTel.length == 0) {
		toastr.error("手机号电话号码必填一项!");
		return;
	}
	
	if(!$("#sendTime").canyinValidateRequire()) {
		toastr.error("请选择送餐时间!");
		return;
	}
	
	if(!$("#totalCost").canyinValidateMoney()) {
		toastr.error("请正确填写送餐费!");
		return
	}
	
	if(!$("#sendAddress").canyinValidateLength(1,1000)) {
		toastr.error("请正确填写送餐地址!");
		return;
	}

	if(!checkSendTime()){
		toastr.error("送餐时间不能小于当前时间！");
		return;
	}
	
	if(billId)
	{
		var orderPeople = $("#orderPeople").val();
		var mobile = $("#mobile").val();
		var linkmanTel = $("#linkmanTel").val();
		var sendTime = $("#sendTime").val();
		var totalCost = $("#totalCost").val();
		var sendAddress = $("#sendAddress").val();
		var invoiceType = $("input[name='invoiceType']:checked").val();
		var invoiceTitle = $("#invoiceTitle").val();
		if(typeof invoiceType == "undefined"){
			invoiceType = "";
		}
		
		if(typeof invoiceTitle == "undefined"){
			invoiceTitle = "";
		}
		popForm('审核通过',
				window.ctx+"/cloud/pop/cloudBillReviewPass?billId="+billId
				+"&takeoutOrderDiv="+takeoutOrderDiv
				+"&isXiadan="+isXiadan
				+"&orderPeople="+orderPeople
				+"&mobile="+mobile
				+"&linkmanTel="+linkmanTel
				+"&sendTime="+sendTime
				+"&totalCost="+totalCost
				+"&sendAddress="+encodeURIComponent(sendAddress)
				+"&invoiceType="+invoiceType
				+"&invoiceTitle="+invoiceTitle
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
	var sendTime=$("#sendTime").val();
	var sendTimeDate = new Date(sendTime.replace("-", "/").replace("-", "/"));
	var currentTime = new Date(C_CURRENTTIME.replace("-", "/").replace("-", "/"));
	if(currentTime > sendTimeDate){
	 	return false;
	}else{
		return true;
	}
}

function refreshDish(){
	var billId = $("#billId").val();
	ajaxUtil.getUrlContent(window.ctx+'/cloud/ajax/billDetail/list?billId='+billId,"takeoutDishContent",function(){
	});
}
