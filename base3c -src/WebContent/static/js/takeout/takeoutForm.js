$(document).ready(function(){
	$("#sendTime").timeSelect();
	$("#mobile").bind("click",function(event){
		getMobileList();
		event.stopPropagation();//取消事件冒泡
		$("#mobileListDiv").show();
	});
	$("#sendAddress").bind("click",function(event){
//		getSendAddressList();
		event.stopPropagation();//取消事件冒泡
		$("#sendAddressListDiv").show();
	});
	$("#mobile").bind("keyup",getMobileList);
	$(".popup_body").click(function(event){
		$("#mobileListDiv").hide();
		$("#sendAddressListDiv").hide();
	});
	$("#takeoutForm").CanYinValidate([
 	    {
 		    id:"contactName",
 		    name:"联系人",
 		    require:true,
 			length:[1,20]
 		},{
 		    id:"mobile",
 		    name:"手机",
 		    require:false,
 		    type:'mobile',
 			length:[11,11]
 		},
 		{
 		    id:"sendAddress",
 		    name:"送餐地址",
 		    require:true,
 		    length:[1,500]
 		},{
 		    id:"invoiceTitle",
 		    name:"发票",
 		    require:false,
 		    length:[1,500]
 		},{
 		    id:"customNote",
 		    name:"客户留言",
 		    require:false,
 		    length:[1,500]
 		},{
 		    id:"deliverCost",
 		    require:false,
 		    type:'money'
 		},{
 		    id:"telephone",
 		    require:false,
 		    type:'telephone'
 		}
 	  ]);
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveTakeout();
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
	
	initSendTime('',false);
	
});

/**
 * 根据手机号获取会员卡
 */
function getCardByMobile(){
	var mobile = jQuery.trim($("#mobile").val());
	if(mobile == ""){
		$("#cardList").html("<option value=''>请选择</option>");
		return;
	}
	$.ajax({
	    type:"get",
	    url:window.ctx+"/takeout/ajax/getMemberCardList",
	    data:"mobile="+mobile,
	    cache:false,
	    success:function(data){
    		var cardList = data.messageObject.membershipCards;
	    	var memeberName = data.messageObject.memeberName;
	    	var htmlContent = "<option value=''>请选择</option>";
	    	$(cardList).each(function(index){
	    		var val = cardList[index];
	    		var mcId = val.mcId;
	    		var cardNo = val.cardNo;
	    		htmlContent += "<option value='"+ mcId +"'>"+ cardNo +"</option>";
	    	});
	    	$("#cardList").html(htmlContent);
	    	if($.trim(memeberName).length != 0){
	    		$("#contactName").val(memeberName);
	    	}
	    	getSendAddressList();
	    	$("#ccdId").val("");
	    },
		error:function(){
		}
	});
}

/**
 * 根据会员卡Id获取折扣方案名称
 */
function getDiscountName(element){
	var mcId = $(element).val();
	if(mcId == ""){
		getCashDiscounts(element,'0');
		return;
	}
	getCashDiscounts(element,'1');
}

/**
 * 获取折扣方案
 * @param isOnlyMember 是否限制会员
 */
function getCashDiscounts(element,isOnlyMember){
	$.ajax({
	    type:"get",
	    url:window.ctx+"/takeout/ajax/getCashDiscounts",
	    data:"isOnlyMember="+isOnlyMember,
	    cache:false,
	    success:function(data){
	    	var discountList = data.messageObject.discountList;
	    	var htmlContent = "<option value=''>请选择</option>";
	    	$(discountList).each(function(index){
	    		var val = discountList[index];
	    		var ccdId = val.ccdId;
	    		var discountName = val.discountName;
	    		htmlContent += "<option value='"+ ccdId +"'>"+ discountName +"</option>";
	    	});
	    	$("#ccdId").html(htmlContent);
	    	
	    	if(isOnlyMember){
	    		//获取对应的折扣方案、并选中
	    		getDiscountInfo(element);
	    	}
	    },
		error:function(){
		}
	});
}

/**
 * 获取对应的折扣方案、并选中
 */
function getDiscountInfo(element){
	var mcId = $(element).val();
	$.ajax({
	    type:"get",
	    url:window.ctx+"/takeout/ajax/getDiscountInfo",
	    data:"mcId="+mcId,
	    cache:false,
	    success:function(data){
	    	var ccdId = data.messageObject.ccdId;
	    	$("#ccdId").val(ccdId);
	    },
		error:function(){
		}
	});
}

/**
 * 校验
 */
function validateTakeout(){
	if(!$("#takeoutForm").CanYinValid()){
		return false;
	}
	var mobile = jQuery.trim($("#mobile").val());
	var telephone = jQuery.trim($("#telephone").val());
	
	if(mobile == '' && telephone == ''){
		toastr.error("联系人手机和联系人电话至少有一个！");
		return false;
	}
	return true;
}

/**
 * 获取手机列表
 */
function getMobileList(){
	var mobileInput = $("#mobile").val();
	var mobileList = $.ajax({
		url:window.ctx+'/takeout/getMobileList',
		data:"mobile="+mobileInput,
		async:false, 
		dataType:String
	}).responseText;
	mobileList = eval(mobileList);
	var mobileUl = "";
	$(mobileList).each( function(index){
        var mobile = mobileList[index];
        mobileUl += "<li><a href='javascript:selectMoblie("+mobile+");'>"+ mobile +"</a></li>";
	});
	$("#mobileListUl").html(mobileUl);
}

/**
 * 获取外卖送餐地址
 */
function getSendAddressList(){
	var mobileInput = $("#mobile").val();
	var telephoneInput = $("#telephone").val();
	if(jQuery.trim(mobileInput) == "" && jQuery.trim(telephoneInput) == ""){
		$("#sendAddressListUl").html("");
		return;
	}
	var sendAddressInput = $("#sendAddress").val();
	var sendAddressList = $.ajax({
		url:window.ctx+'/takeout/getSendAddressList',
		data:"mobile="+mobileInput+"&telephone="+telephoneInput,
		async:false, 
		dataType:String
	}).responseText;
	sendAddressList = eval(sendAddressList);
	var sendAddressUl = "";
	$(sendAddressList).each( function(index){
        var sendAddress = sendAddressList[index];
        sendAddressUl += "<li><a href=\"javascript:selectAddress('"+sendAddress+"');\">"+ sendAddress +"</a></li>";
        $("#sendAddress").val(sendAddress);
	});
	$("#sendAddressListUl").html(sendAddressUl);
}

/**
 * 选择送餐地址
 */
function selectAddress(sendAddress){
	if(sendAddress == ""){
		return;
	}
	$("#sendAddress").val(sendAddress);
}

/**
 * 选择手机号码
 * @param element
 */
function selectMoblie(mobile){
	if(mobile == ""){
		return;
	}
	$("#mobile").val(mobile);
	getCardByMobile();
}

/**
 * 保存外卖
 */
function saveTakeout(){
	if(!validateTakeout()){
		return;
	}
	
	if(!validateSendTime()){//校验送餐时间
		return;
	}
	
	if(!checkSendTime()){
		toastr.error("送餐时间不能小于当前时间！");
		return;
	}
	$("#sendAtOnce").val($(".but_a_p_on").attr("value"));
	$.ajax({
	    type:"post",
	    url:window.ctx+"/takeout/saveTakeout",
	    data:$("#takeoutForm").serialize(),
	    cache:false,
	    success:function(data){
	    	if(data.statusCode == 200){
	    		toastr.success("新增外卖成功！");
	    		var tId = data.value;
	    		window.location = window.ctx + "/bill/diancai?billType=2&tId="+tId+"&billId=";
	    	}
	    },
		error:function(){
		}
	});
}

/**
 * 校验送餐时间
 * @returns {Boolean}
 */
function validateSendTime(){
	var sendAtOnce = $(".but_a_p_on").attr("value");
	if(sendAtOnce == '1'){//立即送餐
		return true;
	}
	else{
		var value = $("#sendTime").val();
		
		if($.trim(value).length == 0) {
			toastr.error("未勾选立即派送，请选择一个送餐时间!");
			return false;
		}
		else if(/Invalid|NaN/.test(new Date(value.replaceAll("-", "/")).toString())){
			toastr.error("送餐时间 日期格式不正确!");
			return false;
		}
	}
	
	return true;
}

/**
 * 判断送餐时间是否大于当前时间
 * @returns {Boolean}
 */
function checkSendTime(){
	var sendTime=$("#sendTime").val();
	var sendTimeDate = new Date(sendTime.replace("-", "/").replace("-", "/"));
	var currentTime = new Date(C_CURRENTTIME.replace("-", "/").replace("-", "/"));
	var sendTimeDate = sendTimeDate.format("yyyy-MM-dd hh:mm");
	var currentTime = currentTime.format("yyyy-MM-dd hh:mm");
	if(new Date(currentTime) > new Date(sendTimeDate)){
	 	return false;
	}else{
		return true;
	}
}

/**
 * 设置是否立刻派送
 */
function setSendAtOnce(element){
	if($(element).hasClass("but_a_p_on")){
		return;
	}else{
		$(".but_a_p_on:first").removeClass().addClass("but_a_p");
		$(element).removeClass().addClass("but_a_p_on");
		initSendTime($(element).attr("value"),true);
	}
}


/**
 * 根据是否立即送餐来控制送餐时间输入框的disabled属性
 * @param sendTimeType 传人空值将自动检索页面查找是否勾选了立即送餐
 * @param shiFouTanChu 是否触发送餐时间文本框的点击事件 TRUE 是 FALSE 不是
 */
function initSendTime(sendTimeType,shiFouTanChu){
	sendTimeType = $.trim(sendTimeType).length == 0 ?$(".but_a_p_on").attr("value"):sendTimeType;
	
	if($.trim(sendTimeType).length == 0){
		$(".but_a_p:first").removeClass("but_a_p").addClass("but_a_p_on");
		sendTimeType=$(".but_a_p_on").attr("value");
	}
	
	if(sendTimeType == '1'){
		$("#sendTime").attr("disabled","disabled");
		$("#sendTime").val("立即派送");
		$("#sendTime_red").hide();
	}
	else{
		$("#sendTime").removeAttr("disabled");
		$("#sendTime_red").show();
		if(shiFouTanChu){
			setTimeout(
				function () {
					$("#sendTime").val("");
					$("#sendTime").click();
			},0);
		}
	}
}

/** 
 * 时间对象的格式化; 
 */  
Date.prototype.format = function(format) {  
    /* 
     * eg:format="yyyy-MM-dd hh:mm:ss"; 
     */  
    var o = {  
        "M+" : this.getMonth() + 1, // month  
        "d+" : this.getDate(), // day  
        "h+" : this.getHours(), // hour  
        "m+" : this.getMinutes(), // minute  
        "s+" : this.getSeconds(), // second  
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S" : this.getMilliseconds()  
        // millisecond  
    }  
  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
                        - RegExp.$1.length));  
    }  
  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1  
                            ? o[k]  
                            : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}  


