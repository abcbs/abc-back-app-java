$(document).ready(function(){
	$("#popSave").unbind("click");
	$("#popSave").bind("click",saveRestMemberInfo);
	
	$("#birthday").datepicker({
	      changeMonth: true,
	      changeYear: true
	    });	
	
	 CardListChange('',1);
	 
	 $("#popSaveForm").CanYinValidate([
   	    {
   		    id:"name",
   		    name:"会员名称",
   		    require:true,
   			length:[1,20]
   		},
   		{
   		    id:"mobile",
   		    name:"手机",
   		    require:true,
   		    type:'mobile',
   			length:[11,11]
   		}
   		,
   		{
   		    id:"email",
   		    name:"邮箱",
   		    require:false,
   		    type:'email'
   		}
   		,
   		{
   		    id:"birthday",
   		    name:"生日",
   		    require:false,
   		    type:'dateISO'
   		}
   	  ]);
 
	 var hotKey = new CanYinHotKeys();
		hotKey.init($("body"),[{
			keyCode:"enter",
			callBackFunction:function()
			{
				//判断如果焦点在会员卡输入框里时不执行提交动作
				var isFocus = $("#popSearchKeyWord").is(":focus");
				if(isFocus){
					$("#buttonSearch").click();
					return;
				}
				saveRestMemberInfo();
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

var currentSearchParams = '';
var currentPage = 1;
function CardListChange(searchParams,page)
{
	ProgressbarUtil.show("popMemberCardList",440);
	
	currentSearchParams = searchParams;
	currentPage = page;
	
	 $.ajax({
		    type:"get",
		    url:window.ctx+'/member/pop/jianka/createFormContent?page='+currentPage+'&'+searchParams,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(html){
		    	$("#popMemberCardList").html(html);
		    	popLastDl = null;
		    	$("#mcId").val('');
		    	setFocus(document.getElementById("popSearchKeyWord"));
		    },
			error:function(){
			}
	});
}
function popSerachKeyWords(input)
{
	var v = $(input).val();
	CardListChange('search_LIKE_cardNo='+v,1);
}

var popLastDl = null;
function addCardToMember(mcId,cardNo,html,cashPledge,cardPassword,mcclassName,balance,mcclassId)
{
	if(cashPledge == '0.00')
	{
		cashPledge = '';
	}
	if(balance == '0.00')
	{
		balance = '';
	}
	
	$("#new_cardPassword").val('');
	$("#new_rechargeCash").val('');
	$("#new_paidinCash").val('');
	$("#new_memberIntegral").val('');
	
	var a = $(html);
	if(popLastDl)
	{
		popLastDl.removeClass("ksfk_infor_a_on");
	}
	a.addClass("ksfk_infor_a_on");
	$("#mcId").val(mcId);
	popLastDl = a;
	
	$("#popMemberCardList").hide();
	$("#popNewMemberCard").show();
	//选择卡后，内容填写
	$("#new_cardNo").val(cardNo);
	$("#new_cardNo").attr("disabled","disabled");
	
	
	$("#new_cashPledge").val(cashPledge);
	
	if(cardPassword)
	{
		$("#new_cardPassword").val(cardPassword);
	}
	$("#new_rechargeCash").val(balance);
	$("#new_membershipCardClasseType").val(mcclassId);
	$("#new_membershipCardClasseType_area").children("div").hide();
	$("#new_membershipCardClasseType_area").children("#new_cardTypeDesc").show().html(mcclassName);
}

function backToCardList()
{
	$("#popMemberCardList").show();
	$("#popNewMemberCard").hide();
	$("#new_cardNo_a").show();
}

function newDrawBill(html)
{
	if($(html).hasClass("but_piao_on"))
	{
		$(html).removeClass("but_piao_on");
		$("#new_drawBill").val(0);
	}
	else
	{
		$(html).addClass("but_piao_on");
		$("#new_drawBill").val(1);
	}
}


function changePaidinCash(html){
	//赠送金额=充值金额-实收
	var new_rechargeCash = $("#new_rechargeCash").val();
	var new_paidinCash = $("#new_paidinCash").val();
	if(!new_rechargeCash)
	{
		new_rechargeCash = 0;
	}
	if(!new_paidinCash)
	{
		new_paidinCash = 0;
	}
//	$("#new_memberIntegral").val(parseFloat(new_rechargeCash));
	if((parseFloat(new_rechargeCash) - parseFloat(new_paidinCash)) <= 0)
	{
		$("#new_giveCash_2").html(0);
	}
	else
	{
		$("#new_giveCash_2").html((parseFloat(new_rechargeCash) - parseFloat(new_paidinCash)));
	}
	
}
function newPrint(html)
{
	if($(html).hasClass("but_piao_on"))
	{
		$(html).removeClass("but_piao_on");
		$("#new_print").val(0);
	}
	else
	{
		$(html).addClass("but_piao_on");
		$("#new_print").val(1);
	}
}

var popJiankaType = 0;
function popShow(showId,hideId,input){
	
	$("#"+showId).show();
	$("#"+hideId).hide();
	
	popJiankaType = $(input).val();
	backToEmpty();
	if(popJiankaType == 1)
	{
		//使用已有的
		backToCardList();
	}
	else
	{
		$("#new_membershipCardClasseType_area").children("div").show();
		$("#new_membershipCardClasseType_area").children("#new_cardTypeDesc").hide();
		$("#new_cardNo_a").hide();
	}
	
}

function backToEmpty()
{
	$("#mcId").val('');
	if(popLastDl)
	{
		popLastDl.removeClass("ksfk_infor_a_on");
	}
	$("#new_cardNo").val('');
	$("#new_cardNo").removeAttr("disabled");
	
	
	$("#new_cashPledge").val('');
	$("#new_cardPassword").val('');
	$("#new_rechargeCash").val('');
	$("#new_paidinCash").val('');
	$("#new_memberIntegral").val('');
	$("#new_giveCash_2").html('0');
	
}

function saveRestMemberInfo()
{
	//校验
	if(!$("#popSaveForm").CanYinValid())
	{
		return;
	}
	if(popJiankaType == 1 && !$("#mcId").val())
	{
		toastr.error("请选择一个会员卡!");
		return;
	}
	if(!cyValidRequire("new_cardNo"))
	{
		toastr.error("请填写发卡的卡号!");
		return;
	}
	if(!cyValidRequire("new_membershipCardClasseType"))
	{
		toastr.error("请选择会员卡类型!");
		return;
	}
	if(!ValidNumberCashPledge("new_cashPledge"))
	{
		toastr.error("请正确填写押金!");
		return;
	}
	if(!ValidNumberThanZero("new_rechargeCash"))
	{
		toastr.error("请正确填写充值金额!");
		return;
	}
	if(!cyValidRequire("new_paymentType"))
	{
		toastr.error("请选择付款方式!");
		return;
	}
	if(!ValidNumberThanZero("new_paidinCash"))
	{
		toastr.error("请正确填写实收金额!");
		return;
	}
	
//	var rechargeCash_value = parseFloat($("#new_rechargeCash").val());
//	var paidinCash_value = parseFloat($("#new_paidinCash").val());
//	if(rechargeCash_value>paidinCash_value){
//		var offset = rechargeCash_value - paidinCash_value;
//		if(offset>paidinCash_value*3){
//			toastr.error("赠送金额超过实收3倍，赠送金额过大！");
//			return;					
//		}
//	}
//	
	var rechargeCash = $("#new_rechargeCash").val();//充值金额
	var paidinCash = $("#new_paidinCash").val();//实收金额
	var zs = parseFloat(rechargeCash) - parseFloat(paidinCash);
	if((zs / parseFloat(paidinCash)) >= 3)
	{
		dialogBoxConfirm("充值","赠送金额超过实收3倍，赠送金额过大",function(){
		});
		return;	
	}
	else if(zs >= parseFloat(paidinCash))
	{
		dialogBoxConfirm("充值","赠送金额超过或者等于实收金额，您确定继续吗？",function(){
			memberSubmit(popJiankaType);
		});
		return;	
	}
	else if(zs > 0)
	{
		dialogBoxConfirm("充值","确定给予会员卡，"+zs+"元的优惠吗？",function(){
			memberSubmit(popJiankaType);
		});
		return;	
	}
	else if(zs == 0)
	{
	}
	else if(zs < 0)
	{
		dialogBoxConfirm("充值","实收不能大于充值金额",function(){
		});
		return;	
	}
			
	
	
	memberSubmit(popJiankaType);	
	return false;
}

//同步锁，每个函数一个
var syncLock = new SyncLock("popSave");
function memberSubmit(popJiankaType)
{
	if(popJiankaType == 0 &&!repeatMemberCardNo())
	{
		toastr.error("会员卡号重复了!");
		return;
	}
	//防止双击
	if(!syncLock.Start()){
		return;
	}
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
			if(data.statusCode == "200")
			{
				toastr.info(data.message);
				refreshPage();
				closebox();
			}
			else
			{
				toastr.error(data.message);
			}
			syncLock.End();
		},
		error:function(){
			syncLock.End();
		}
	});
}
	
/**
 * 改变会员卡类型
 */
function changeMemberCardType()
{
	var v = $("#new_membershipCardClasseType").val();
	 $.ajax({
		    type:"get",
		    url:window.ctx+'/member/ajax/getMembeCardTypeById?cid='+v,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(data){
			 	if(data.statusCode = '200')
			 	{
			 		$("#new_cashPledge").val(data.message);
		 		}
		    },
			error:function(){
			}
	});
}
	
/**
 * 会员卡号是否重复
 * @returns {Boolean}
 */
function repeatMemberCardNo()
{
	var cardNo = $("#new_cardNo").val();
	var url=ctx+'/member/cc?cardNo='+cardNo+'&id=';
	var message = $.ajax({
		url:url,
		async:false, 
		dataType:String
	}).responseText;
	if(message=='true'){
		return true;
	}else{
		return false;
	}
}

function cyValidRequire(inputId){
	var obj = $("#"+inputId);
	var v = obj.val();
//	if(!v)
	if(typeof(v) == "undefined" || v == "")
	{
		return false;
	}
	return true;
}

function cyValidNumber(inputId){
	if(cyValidRequire(inputId) && ValidNumber(inputId))
	{
		return true;
	}
	return false;
}

function ValidNumber( inputId) {
	var obj = $("#"+inputId);
	var value = obj.val();
	return  /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
}
function ValidNumberCashPledge(inputId)
{
	var obj = $("#"+inputId);
	var v = obj.val();
	if(v == "" || v == null)
	{
		return true;
	}
	if(!ValidNumber(inputId))
	{
		return false;
	}
	return true;
}

function ValidNumberThanZero(inputId)
{
	if(!cyValidRequire(inputId))
	{
		return false;
	}
	else if(!ValidNumber(inputId))
	{
		return false;
	}
	else
	{
		var obj = $("#"+inputId);
		var value = parseFloat(obj.val());
		if(value < 0)
		{
			return false;
		}
	}
	return true;
}