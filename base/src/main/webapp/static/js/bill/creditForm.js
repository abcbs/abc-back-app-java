$(document).ready(function(){
	changeCardContent();
	$("#selectCard").bind("click",selectCard);
	$("#consume").bind("click",consume);
	$("#discount").bind("click",discount);
	$("#cancleMemberCard").bind("click",cancleMemberCard);
	$("#cardNoInput").bind("focusout",loadCardInfo);
	$("#cardSearch").bind("change",changeCardContent);
	$("#passwordDiv").attr("style","display:none");
	$("#money,#moneyButton").sudoku();
	$("#cardSearch,#cardSearchButton").sudoku();
	$("#cardPassword,#cardPasswordButton").sudoku();
	$("#cardNoInput").focus();
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			//判断如果焦点在会员卡输入框里时不执行提交动作
//			var isFocus = $("#cardNoInput").is(":focus");
//			if(isFocus = true){
//				loadCardInfo();
//				return;
//			}
			selectCard();
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

//使用会员卡消费
function consume(){
	//不选中 使用会员卡打折
	$("#discount").removeClass("but_huk_on");
	$("#discount").addClass("but_huk");
	//选中 使用会员卡消费
	$("#consume").removeClass("but_huk");
	$("#consume").addClass("but_huk_on");
	$("#membercardPayType").val('1');
	$("#moneyLable").show();
	$("#money").show();
	$("#moneyButton").show();
	$("#money").focus();
}

//使用会员卡打折
function discount(){
	//选中 使用会员卡打折
	$("#discount").removeClass("but_huk");
	$("#discount").addClass("but_huk_on");
	//不选中 使用会员卡消费
	$("#consume").removeClass("but_huk_on");
	$("#consume").addClass("but_huk");
	$("#membercardPayType").val('2');
	$("#moneyLable").hide();
	$("#money").hide();
	$("#moneyButton").hide();
}

function loadCardInfo(){
	var cardNo = $("#cardNoInput").val();
	if(!cardNo){
		setCardValues("","","","true","","");
		$("#money").val("");
		return;
	}
	//去除左边选中效果
	$("ajaxContent2").find("li").each(function(){
		if($(this).hasClass("hykjz_infor_on")){
			$(this).removeClass("hykjz_infor_on");
		}
	});
	//异步获取会员卡信息
	$.ajax({
		async:true,
		type:"get",
		url:window.ctx+"/bill/pop/memberCardInfo/"+cardNo,
		data:null,
		dataType:'json',
		success: function(msg){
			alert('xxx');
			//是否存在此会员卡
			var isHas = msg.statusCode;
			if(isHas == '1'){
				//会员卡状态
				var sign = msg.sign;
				if(sign == '1'){//正常
					var cardValues = msg.value;
					var cardInfo = cardValues.split(',');
					var mcId = cardInfo[0];
					var balance = cardInfo[1];
					var memberIntegral = cardInfo[2];
					var mbId = cardInfo[3];
					var isEmptyPassWord = cardInfo[4];
					setCardValues(mcId,cardNo,mbId,isEmptyPassWord,balance,memberIntegral);
					setNeedMoney(mcId);
					setCardUserType(balance);
				}else if(sign == '2'){//停用
					toastr.error("您输入的会员卡已经停用！");
					setCardValues("",cardNo,"","true","","");
					return;
				}else if(sign == '3'){//挂失
					toastr.error("您输入的会员卡已经挂失！");
					setCardValues("",cardNo,"","true","","");
					return;
				}
			}else if(isHas == '0'){
				toastr.error("您输入的会员卡不存在！");
				setCardValues("",cardNo,"","true","","");
				return;
			}
		}
	});
}

function setCard(mcId,cardNo,mbId,isEmptyPassWord,html){
	selectRow(html);
	var balance = $(html).find("#balance").val();
	var memberIntegral = $(html).find("#memberIntegral").val();
	setCardValues(mcId,cardNo,mbId,isEmptyPassWord,balance,memberIntegral);
	setNeedMoney(mcId);
	setCardUserType(balance);
}

function setCardUserType(balance){
	if(balance != 'null' && Number(balance) == 0){
		$("#discount").click();
	}else{
		$("#consume").click();
	}
}

//异步获取需要支付的金额
function setNeedMoney(mcId){
	//如果没有绑定会员卡
	var selectedMembershipCardId = $("#membershipCardId").val();
	if(!selectedMembershipCardId){
		//根据会员卡不同，结账需要金额不同
		var billId = $("#billId").val();
		$.ajax({
			async:true,
			type:"GET",
			url:window.ctx+"/bill/needMoney",
			data:'billId=' + billId +'&mcId=' + mcId,
			dataType:'json',
			success: function(msg){
				$("#money").val(msg.value);
			}
		});
	}
}

//设置会员卡信息
function setCardValues(mcId,cardNo,mbId,isEmptyPassWord,balance,memberIntegral){
	if(memberIntegral == 'null' || memberIntegral == ""){
		memberIntegral = "0";
	}
	if(balance == 'null' || balance == ""){
		balance = "0";
	}
	if(Number(balance) == parseInt(balance)){
		balance = parseInt(balance);
	}
	if(Number(memberIntegral) == parseInt(memberIntegral)){
		memberIntegral = parseInt(memberIntegral);
	}
	$("#mcId").val(mcId);
	$("#cardNo").val(cardNo);
	$("#mbId").val(mbId);
	$("#isEmptyPassWord").val(isEmptyPassWord);
	$("#cardNoLable").text(cardNo);
	$("#cardNoInput").val(cardNo);
	$("#cardNoYuELable").text("余额："+balance);
	$("#cardNoJifenLable").text("积分："+memberIntegral);
	if(isEmptyPassWord == 'true'){
		$("#passwordDiv").attr("style","display:none");
	}else{
		$("#passwordDiv").removeAttr("style");
		$("#cardPassword").focus();
	}
}

var popLastDl = null;
function selectRow(html){
	var a = $(html);
	if(popLastDl){
		popLastDl.removeClass("hykjz_infor_on");
	}
	a.addClass("hykjz_infor_on");
	
	popLastDl = a;
	
	$("#cardPassword").focus();
}

function validateAjax()
{
	var cardNo = $("#cardNo").val();
    var cardPassword = $("#cardPassword").val();
	$.ajax({
		async:true,
		type:"GET",
		url:window.ctx+"/bill/validateCardNo",
		data:'cardNo=' + cardNo +'&cardPassword=' + escape(cardPassword),
		dataType:'json',
		success: function(msg){
			if(msg.message == 'true'){
				submitCardForm();
			}else{
				toastr.error("密码错误！");
			}
		}
	});	
}

//同步锁，每个函数一个
var syncLock = new SyncLock("selectCard");

/**
 * 异步提交form表单
 */
function submitCardForm(){
	if(!syncLock.Start()){
		return;
	}
	$("#selectCard").unbind("click",selectCard);
	var url = $("#popSaveForm").attr("action");
	jQuery.ajax({
		url: url,
		data: $('#popSaveForm').serialize(),
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			if(data.rel == 'false'){
				$("#selectCard").bind("click",selectCard);
				toastr.error(data.message);
			}else{
				closebox();
				refreshPayPage(1);
			}
			syncLock.End();
		},
		error:function(){
			syncLock.End();
		}
	});
}

function selectCard()
{
	//表单验证
	if(!validateCardForm()){
		return;
	}
	
	//如果密码非空才验证
	var isEmptyPassWord = $("#isEmptyPassWord").val();
	if(isEmptyPassWord == 'false'){
		//会员卡号和密码验证 && 验证余额
		validateAjax();
	}else{
		submitCardForm();
	}
}

function changeCardContent()
{
	var billId = $("#billId").val();
	var kewWords = $("#cardSearch").val();
	var paymentType = $("#paymentType").val();
	ProgressbarUtil.show("ajaxContent2",594);
	 $.ajax({
		    type:"get",
		    url:window.ctx+'/bill/ajax/card/list?billId='+billId+'&kewWords='+kewWords+'&paymentType='+paymentType,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(html){
		    	$("#ajaxContent2").html(html);
		    	
		    	//默认选择会员卡
		    	var selectedMembershipCardId = $("#membershipCardId").val();
		    	if($("#ajaxContent2").find("a[id='"+selectedMembershipCardId+"']").attr("id"))
		    	{
		    		$("#ajaxContent2").find("a[id='"+selectedMembershipCardId+"']").click();
		    	}
		    	initWidth();
		    },
			error:function(){
			}
	});
}

/**
 * 根据电话查找会员
 */
function getMemberByTel()
{
	changeCardContent();
}

function validateCardForm(){
	//校验
	if(cyValidRequire("money") && isNaN($("#money").val())){
		toastr.error("请正确填写金额");
		return false;
	}
	if(!cyValidRequire("mcId")){
		toastr.error("请选择会员卡");
		return false;
	}
	
	var isEmptyPassWord = $("#isEmptyPassWord").val();
	if(isEmptyPassWord == 'false'){
		if(!cyValidRequire("cardPassword")){
			toastr.error("请填写密码");
			return false;
		}
	}
	return true;
}

function closeConfirm(){
	$dialogConfirm.dialog( "close" );
	if("undefined" == typeof CanYinHotKeysGetLastHotKeys)
	{
		
	}
	else
	{
		if(CanYinHotKeysGetLastHotKeys)
		{
			CanYinHotKeysGetLastHotKeys(0);
		}
	}
}

function cancleMemberCard(){
	$("#cancleMemberCard").hide();
	var billId = $("#billId").val();
	var cptId= $("#cptId").val();
	$.ajax({
	    type:"POST",
	    url:window.ctx+"/bill/pop/cancleCashDiscount/"+billId+"?cptId="+cptId,
	    data:null,
	    cache:false,
	    async:true,
	    dataType: "json",
	    success:function(data){
	    	if(data.callbackType == 1){
	    		toastr.info("已取消与此会员卡关联的折扣方案");
	    	}
			BillChange(billId);
	    	resetCardForm(data);
	    	refreshPayPage(1);
	    },
		error:function(){
		}
	  });
}

//取消会员卡操作后，对表单进行重置
function resetCardForm(data){
	$("#cardNoInput").val("");
	$("#cardNoYuELable").text("");
	$("#cardNoJifenLable").text("");
	$("#passwordDiv").removeAttr("style");
	$("#money").val(data.rel);
	$("#mcId").val("");
	$("#cardNo").val("");
	$("#mbId").val("");
	$("#dbpId").val("");
	$("#isEmptyPassWord").val("");
	$("#cardNoLable").text("");
	$("#cardSearch").val("");
	$("#membershipCardId").val("");
	$("#cancleCashDiscount").attr("hidden","true");
	changeCardContent();
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