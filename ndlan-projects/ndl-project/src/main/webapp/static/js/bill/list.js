$(document).ready(function(){
	initFrameSize();
	listFirstEnterPage();
	
	//异步请求
	var billId = $("#currentBillId").val();
	var site_url = window.location.href;
	if(site_url.indexOf("search_EQ_billStatus=takeOut") > 0 ){
		currentDishCatagoryBillType = '2';//外卖
		currentDishCatagoryPageType='unPay';
	}
	
	if(site_url.indexOf("search_EQ_billStatus=4") > 0 ){
		currentDishCatagoryBillStatus = '4';
		currentDishCatagoryPageType='unPay';
	}
	
	refreshDishCatagoryChange();
	
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
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/member';
		}
	}
	,
	{
		keyCode:"right",
		callBackFunction:function()
		{
			$(".but_qx_right").click();
		}
	}
	,
	{
		keyCode:"left",
		callBackFunction:function()
		{
			$(".but_qx_left").click();
		}
	}
	,
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			//搜索框得到焦点
			$("#keywords").focus();
			setFocus(document.getElementById("keywords"));
		}
	}
	],0);
	
});


function listFirstEnterPage()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".list_b").css("width",659+subWidth+"px").effect("slide",300);
	}
}

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
		$(".body_sec").css("height",670+subHeight+"px");
		$(".list_a").css("height",670+subHeight+"px");
		$(".list_b").css("height",670+subHeight+"px");
	}
}
function initWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".list_b").css("width",659+subWidth+"px");
		$(".list_b_in").css("width",658+subWidth+"px");
		$(".tab_nav_wrap").css("width",658+subWidth+"px");
		$(".tab_nav").css("width",658+subWidth+"px");
		$(".tab_nav_baby").css("width",644+subWidth+"px");
		$(".zdlb").css("width",658+subWidth+"px");
		$(".zdlb_but_area").css("width",658+subWidth+"px");
		$(".search_sec").css("width",200+subWidth+"px");
		$(".zdhj_wrap").css("width",658+subWidth+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".list_b_in").css("height",670+subHeight+"px");
//		$(".but_wrap").css("margin-top",10+subHeight/2+"px");
	}
	
}

function keyWordSearch()
{
	var keywords = $("#inputKeywords").val();
	$("#keywords").val(keywords);
	var billId = $("#currentBillId").val();
	dishCatagoryChange(billId,currentDishCatagoryBillStatus,currentDishCatagoryPage,keywords,currentDishCatagoryPageType,currentDishCatagoryBillType);
}


function refreshDishCatagoryChange()
{
	var billId = $("#currentBillId").val();
	dishCatagoryChange(billId,currentDishCatagoryBillStatus,currentDishCatagoryPage,currentDishCatagoryKeywords,currentDishCatagoryPageType,currentDishCatagoryBillType);
}

var currentDishCatagoryBillStatus='';
var currentDishCatagoryKeywords='';
var currentDishCatagoryPage=1;
var currentDishCatagoryPageType='today';
var currentDishCatagoryBillType='';

function dishCatagoryChange(billId,billStatus,page,keywords,pageType,billType)
{
	ProgressbarUtil.show("ajaxBilllistContent",670);
	keywords = $("#keywords").val();
	currentDishCatagoryBillStatus = billStatus;
	currentDishCatagoryKeywords= keywords ? keywords : '';
	currentDishCatagoryPage = page;
	currentDishCatagoryPageType = pageType;
	currentDishCatagoryBillType = billType;
	
	ajaxUtil.getUrlContent(window.ctx+'/bill/ajax/listBillsContent?billId='+billId+'&pageType='+pageType+'&page='+page+'&keywords='+currentDishCatagoryKeywords+'&billType='+billType+'&billStatus='+billStatus,"ajaxBilllistContent",function(){
		initWidth();
    	billListInitEvent();
	});
	
}

var tabKeyAreaIndex = 0;
function billListInitEvent()
{
	$(".list_b_in").unbind("keydown");
	$(".list_b_in").keydown(function(evt) {
		if(evt.keyCode == 9)
		{
			var lis = $("#biiiListUl").children("li");
			var ci = tabKeyAreaIndex;
			if(ci >= lis.length-1)
			{
				ci = 0;
			}
			else
			{
				ci=tabKeyAreaIndex+1;
			}
			tabKeyAreaIndex=ci;
			lis.eq(tabKeyAreaIndex).children("a").click();
		}
	});
	
	//搜索框得到焦点
//	$("#keywords").focus();
//	setFocus(document.getElementById("keywords"));
	CanYinHotKeysGetLastHotKeys(0);
	$("body").find("input").focusin(function(){
		$("body").unbind("keydown");
	}).focusout(function(){
		CanYinHotKeysGetLastHotKeys(0);
	});
}


//账单搜索的enter事件
function kewWordsEnter(event){
	var keyCode = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
	 if (keyCode ==13){
		 keyWordSearch();
	 }
	 else
	 {
		 e.stopPropagation();
	 }
	 
}