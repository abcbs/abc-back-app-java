$(document).ready(function(){
	toastr.options.target = document.getElementById("small_popup_wrap");
	$("#popSave").bind("click",saveMoling);
	$("#money").bind("focusout",moneyFocusout);
	$("#molingAfter").bind("focusout",molingAfterFocusout);
	$("#money,#moneyButton").sudoku();
	$("#molingAfter,#molingAfterButton").sudoku();
	$("#popSaveForm").CanYinValidate([
  	    {
  		    id:"money",
  		    name:"金额",
  		    require:false,
  		    type:'number'
  		},{
  		    id:"molingAfter",
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
			saveMoling();
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

function validateMoney(){
	var molingBefore = $("#molingBefore").html();
	var molingMoney = $("#money").val();
	if(isNaN(molingMoney)){
		toastr.error("抹零金额必须为数字！");
		return false;
	}
	if(Number(molingMoney) > Number(molingBefore)){
		toastr.error("抹零金额过大！");
		return false;
	}
	return true;
}

function validateMolingAfter(){
	var molingBefore = $("#molingBefore").html();
	var molingAfter = $("#molingAfter").val();
	if(isNaN(molingAfter)){
		toastr.error("抹零后金额必须为数字！");
		return false;
	}
	return true;
}

function moneyFocusout(){
	if(!validateMoney()){
		return;
	}
	var molingBefore = $("#molingBefore").html();
	var molingMoney = $("#money").val();
	var money = Number(molingBefore) - Number(molingMoney);
	money = money.toFixed(2)
	var intMoney = parseInt(money);
	if(Number(money) == intMoney){
		money = intMoney;
	}
	$("#molingAfter").val(money);
}

function molingAfterFocusout(){
	if(!validateMolingAfter()){
		return;
	}
	var molingBefore = $("#molingBefore").html();
	var molingAfter = $("#molingAfter").val();
	var money = Number(molingBefore) - Number(molingAfter);
	money = money.toFixed(2)
	var intMoney = parseInt(money);
	if(Number(money) == intMoney){
		money = intMoney;
	}
	$("#money").val(money);
}

function saveMoling()
{
	if($("#popSaveForm").CanYinValid())
	{
		if(!validateMoney()){
			return;
		}
		
		if(!validateMolingAfter()){
			return;
		}
		
		if(getPermission("moling")){
			$("#popSave").unbind("click",saveMoling);
			submitMoling();
		}
	}
}

function submitMoling(){
	var money = $("#money").val();
	if(money == "" || money == null || typeof(money) == "undefined"){
		money = 0;
		$("#money").val("0");
	}
	var billId = $("#billId").val();
	var url = window.ctx+"/bill/moling/" + billId;;
	jQuery.ajax({
		url: url + '?money=' + money,
		data: null,
		type: "POST",
		dataType: "json",
		beforeSend: function()
		{  
		},
		success: function(data)
		{
			closebox();
			refreshPayPage();
			toastr.options.target = "body";
		}
	});
}
