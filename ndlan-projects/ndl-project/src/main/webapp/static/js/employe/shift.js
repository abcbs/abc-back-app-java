$(document).ready(function(){
	$("#currentHandonCash,#currentHandonCashA").sudoku();
	$("#currentHandoffCash,#currentHandoffCashA").sudoku();//新增营业周转资金
	$("#currentHandonCash").bind("focusout",changeCurrentCashBalance);
	$("#currentHandoffCash").bind("focusout",changeCurrentCash);
	$("#shiftSubmit").bind("click",saveShift);
	initFrameSize();
	
	$("#shiftForm").CanYinValidate([
	    {
 		    id:"currentHandonCash",
 		    name:"本班上交现金",
 		    require:true,
 		    type:'number'
 		}
	  ]);
	
	hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[
	{
		keyCode:"enter",
		callBackFunction:function()
		{
		}
	}
	],1);
	
});

var hotKey = null;
//改变当前现金总额
function changeCurrentCash(){
	var currentHandoffCash = $("#currentHandoffCash").val();
	if(isNaN(currentHandoffCash) || Number(currentHandoffCash) < 0){
		toastr.error("请正确填写");
		return ;
	}
	var currentCash2 = $("#currentCash2").val();
	var currentCash = Number(currentCash2) + Number(currentHandoffCash);
	//防止出现多位小数的问题
	currentCash = Math.round(currentCash*Math.pow(10, 2))/Math.pow(10, 2)
	$("#currentCash").val(currentCash);
	$("#currentCashLable").text(currentCash);
	setCurrentCashBalance();
}

//验证本班结余现金合法性
function validateCurrentCashBalance(){
	//本班上交现金
	var currentHandonCash = $("#currentHandonCash").val();
	if(isNaN(currentHandonCash) || Number(currentHandonCash) < 0){
		toastr.error("请正确填写本班上交现金");
		return false;
	}
	//当前现金总额
	var currentCash = $("#currentCash").val();
	//本班上交现金不能大于当前现金总额
	if(Number(currentHandonCash) > Number(currentCash)){
		toastr.error("本班上交现金不能大于当前现金总额");
		return false;
	}
	return true;
}

//改变本班结余现金
function changeCurrentCashBalance(){
	//改变本班结余现金
	if(!validateCurrentCashBalance()){
		return;
	}
	setCurrentCashBalance();
}

//设置本班结余现金
function setCurrentCashBalance(){
	//本班上交现金
	var currentHandonCash = $("#currentHandonCash").val();
	if(isNaN(currentHandonCash) || Number(currentHandonCash) < 0){
		toastr.error("请正确填写本班上交现金");
		return false;
	}
	//当前现金总额
	var currentCash = $("#currentCash").val();
	//本班上交现金
	var currentHandonCash = $("#currentHandonCash").val();
	var currentCashBalance = Number(currentCash) - Number(currentHandonCash)
	//防止出现多位小数的问题
	currentCashBalance = Math.round(currentCashBalance*Math.pow(10, 2))/Math.pow(10, 2)
	$("#currentCashBalance").val(currentCashBalance);
	$("#currentCashBalanceLabel").text(currentCashBalance);
	return true;
}

function saveShift()
{
	if(!validateCurrentCashBalance()){
		return;
	}
	if($("#shiftForm").CanYinValid())
	{
		$("#shiftSubmit").unbind("click",saveShift);
		var url = $("#shiftForm").attr("action");
		jQuery.ajax({
			url: url,
			data: $('#shiftForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function(){
			},
			success: function(data){
				toastr.success(data.message);
				//同时退出系统，返回到系统登录页面
				setTimeout(function(){
					window.location=window.ctx+'/logout';
	    		},1000);
				
			}
		});
	}
	return false;
}


/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
	if(subWidth > 0)
	{
		$(".body_system_jiaojieban").css("width",998+subWidth+"px");
		$(".jjb_one_jiaojieban").css("width",994+subWidth+"px");
		$(".jiaojieban_content").css("width",994+subWidth+"px");
		$(".jiaojieban_title").css("width",994+subWidth+"px");
		$(".jiaojieban_con").css("width",994+subWidth+"px");
		$(".jj_con_right").css("width",994+subWidth-235+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_system_jiaojieban").css("height",712+subHeight+"px");
		$(".jiaojieban_content").css("height",705+subHeight+"px");
		$(".jj_con_left").css("height",650+subHeight+"px");
	}
}

//打印按钮
function setPrint(val){
	if(val == '1'){
		$("#dayinButton").removeClass().addClass("but_a_on mr_28");
		$("#noDayinButton").removeClass().addClass("but_a mr_28");
	}else if(val == '0'){
		$("#dayinButton").removeClass().addClass("but_a mr_28");
		$("#noDayinButton").removeClass().addClass("but_a_on mr_28");
	}
	$("#isPrint").val(val);
}
