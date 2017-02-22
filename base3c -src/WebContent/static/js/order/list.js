$(document).ready(function(){
	initFrameSize();
	changeTableArea('',currentTableArea,currentOrderStatus,1);
	createButton();
	confirm();
	kaitai();
	cancle();
	kuaijiejian();
	messageListen();
});

function messageListen() {
	var from = $('#from').val();
	if(from == "message") {
		var preMobile = $('#preMobile').val();
		popForm('预订信息',window.ctx+'/order/pop/order/create?preMobile='+preMobile,'880','644');
	}
}

var hotKey =null;
function kuaijiejian()
{
	hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"1",
		callBackFunction:function()
		{
			$("#newOrder").click();
		}
	}
	,
	{
		keyCode:"2",
		callBackFunction:function()
		{
			$("#confirm").click();
		}
	}
	,
	{
		keyCode:"3",
		callBackFunction:function()
		{
			$("#kaitai").click();
		}
	}
	,
	{
		keyCode:"4",
		callBackFunction:function()
		{
			$("#cancle").click();
		}
	}
	,
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/estimate';
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
			$("#inputKewWords").focus();
			setFocus(document.getElementById("kewWords"));
		}
	}
	],0);
	
}

/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body_huiyuan").animate({width:998+subWidth},window.animate_speed);
		$(".list_h").animate({width:807+subWidth},window.animate_speed);
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_huiyuan").css("height",670+subHeight+"px");
		$(".list_h").css("height",664+subHeight+"px");
		$(".list_y").css("height",664+subHeight+"px");
	}
}

var orderListUlMarginLeft = 0;

function initWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".hy_nav").css("width",806+subWidth+"px");
		$(".hy_table_wrap").css("width",807+subWidth+"px");
		$(".huiyuan_table").css("width",807+subWidth+"px");
		$(".hy_sousuo").css("width",795+subWidth+"px");
		$("tr",".huiyuan_table").css("width",807+subWidth+"px");
	}
	
	//分区个数
	var mwidth = $(".hy_nav").width();
	var areaNum = parseInt(mwidth/115);
	if($("#orderListUl").find("li").length <= areaNum)
	{
		$("#dish_left").hide();
		$("#dish_right").hide();
	}
	$(".hy_nav").find("ul").css("width",($("#orderListUl").find("li").length+1)*115+"px");
	
	$("#orderListUl").css({marginLeft:orderListUlMarginLeft+"px"});
	
}

function refreshPage()
{
	changeTableArea(currentKewWords,currentTableArea,currentOrderStatus,currentPage);
}

function refreshPageBtn()
{
	currentKewWords = $("#inputKewWords").val();
	refreshPage();
}

/**
 * 点击餐台区域
 */
var currentSearchParams = '';

var currentTableArea = '';
var currentOrderStatus = '';

var currentKewWords = '';
var currentPage = 1;
var currentSortCol = "createTime";
var currentDirection = "desc";

function changeTableArea(kewWords,tableArea,orderStatus,page)
{
	ProgressbarUtil.show("ajaxContent",594);
	if(tableArea)
	{
		currentTableArea = tableArea;
	}
	currentOrderStatus = orderStatus;
	
	
	currentSearchParams = currentTableArea + "&" + currentOrderStatus;
	currentKewWords = $("#kewWords").val();
	kewWords = currentKewWords;
	currentPage = page;
	ajaxUtil.getUrlContent(getScreenSizeUrl('/order/ajax/order/list?page='+currentPage+'&'+currentSearchParams+'&kewWords='+kewWords+"&sortCol="+currentSortCol+"&direction="+currentDirection,'member'),"ajaxContent",function(){
		initTableEvent();
    	initWidth();
    	setOrderStatusNum();
	});
}
var currentRow = null;
function initTableEvent()
{
	var tbody = $("tbody","#orderTable");
	var thead = $("thead","#orderTable");
	tbody.children("tr").click(function(){
		var tr = $(this);
		
		if(tr.hasClass("on_hy_tr")){
			tr.removeClass("on_hy_tr");
			currentRow = null;
			setHuise();
		}
		else{
			tbody.children("tr").removeClass("on_hy_tr");
			tr.addClass("on_hy_tr");
			currentRow = tr;
			var orderStatus = currentRow.attr("orderStatus");
			setOrderStatus(orderStatus);
		}
	});
	
	$("th",thead).each(function(){
		var th = $(this);
		var dr = th.attr("dir");
		var sortCol =$("#sortCol").val();
		if(th.attr("sort"))
		{
			th.find("img").remove();
			if(sortCol == th.attr("sort"))
			{
				if(dr == 'asc')
				{
					th.append("<img src='"+window.ctx+"/static/images/huiyuan-yuding/dot_up.png'>");
				}
				else
				{
					th.append("<img src='"+window.ctx+"/static/images/huiyuan-yuding/dot_down.png'>");
				}
			}
			
			th.click(function(){
				var thr = $(this);
				var sort = thr.attr("sort");
				var dir = thr.attr("dir");
				if(dir == 'asc')
				{
					dir = "desc";
				}
				else
				{
					dir = "asc";
				}
				currentSortCol = sort;
				currentDirection = dir;
				refreshPage();
			 });
		}
	 });
	
	
	
	
	$("#inputKewWords").unbind();
	$("#inputKewWords").keydown(function(evt) {
		//0-9
		if(evt.keyCode == 13)//1
		{
			currentKewWords = $("#inputKewWords").val();
			$("#kewWords").val(currentKewWords);
			refreshPage();
		}
	});
	
	$(".body_huiyuan").unbind("keydown");
	$(".body_huiyuan").keydown(function(evt) {
		if(evt.keyCode == 9)
		{
			var lis = $("#orderListUl").children("li");
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
	
	hotKey.exInputFocus();
	
	CanYinHotKeysGetLastHotKeys(0);
	$("body").find("input").focusin(function(){
		$("body").unbind("keydown");
	}).focusout(function(){
		CanYinHotKeysGetLastHotKeys(0);
	});
	
	$(".yd_gongneng_2").children("a").each(function(){
		$(this).removeClass("yd_gongneng_2_a_on")
	});
	if(currentOrderStatus == null || currentOrderStatus == '')
	{
		currentOrderStatus = "search_EQ_orderStatus_";
	}
	$(".yd_gongneng_2").children("#"+currentOrderStatus.replaceAll("=","_")).addClass("yd_gongneng_2_a_on");
	
	var moji = $(".yd_gongneng_2").children("#"+currentOrderStatus.replaceAll("=","_")).text();
	$("#orderListStatusShow").text("预订状态－"+moji);
	
	
	$("#dish_left").click(function(){
		var left = $("#orderListUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(left < 0)
		{
			left+= 500;
			orderListUlMarginLeft = left;
			$("#orderListUl").animate({marginLeft:left+"px"},100);
		}
	});
	
	$("#dish_right").click(function(){
		var mainTabWidth = $(".hy_nav").width();
		var mainCatagoryTabWidth = $("#orderListUl").width();
		var left = $("#orderListUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(mainCatagoryTabWidth + left < mainTabWidth)
		{
			return;
		}
		
		left-= 500;
		orderListUlMarginLeft = left;
		$("#orderListUl").animate({marginLeft:left+"px"},100);
	});
	setHuise();
	
}
var tabKeyAreaIndex = 0;
function setHuise(){
	$("#cancle").removeClass("yd_qxyd").addClass("yd_qxyd_no");
	$("#kaitai").removeClass("yd_zkt").addClass("yd_zkt_no");
	$("#confirm").removeClass("yd_shqr").addClass("yd_shqr_no");
}

function createButton()
{
	$("#newOrder").click(function(){
			if(getPermission("yuding"))
			{
				popForm('预订信息',window.ctx+'/order/pop/order/create','880','644');
			}
	});
}

function confirm()
{
	$("#confirm").click(function(){
		if(!$("#confirm").hasClass("yd_shqr_no")){
			if(getPermission("confirm"))
			{
				if(currentRow){
					var orderId = currentRow.attr("orderId");
					popForm('预订审核',window.ctx+'/order/pop/confirm/'+orderId,'529','335');
				}
				else
				{
					toastr.warning("请选择一个审核信息!");
				}
			}
		}
	});
}

function kaitai()
{
	$("#kaitai").click(function(){
		if(!$("#kaitai").hasClass("yd_zkt_no")){
			if(getPermission("kaitai"))
			{
				if(currentRow){
					var orderId = currentRow.attr("orderId");
					popForm('开台',window.ctx+'/order/pop/kaitai/create/'+orderId,'880','644');
				}
				else
				{
					toastr.warning("请选择一个开台信息!");
				}
			}
		}
		
	});
}

function cancle()
{
	$("#cancle").click(function(){
		if(!$("#cancle").hasClass("yd_qxyd_no")){
			if(getPermission("cancle"))
			{
				if(currentRow){
					var orderId = currentRow.attr("orderId");
					popForm('取消预订',window.ctx+'/order/pop/cancle/'+orderId,'529','335');
				}
				else{
					toastr.warning("请选择一个预订信息!");
				}
			}
			
		}
		
	});
}

/**
 * 刷新预订的数量
 */
function setOrderStatusNum()
{
	$.ajax({
	    type:"get",
	    url:window.ctx+'/order/ajax/getOrderStatusNum',
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	var json = eval(data.message);
	    	var order = parseInt(json[0].order);
	    	var wait = parseInt(json[0].wait);
	    	if(order > 0)
	    	{
	    		$("#search_EQ_orderStatus_2").children("span").html("<i>预订中<br/>"+order+"单</i>");
	    	} else {
	    		$("#search_EQ_orderStatus_2").children("span").html("<i>预订中</i>");
	    	}
	    	if(wait > 0)
	    	{
	    		$("#search_EQ_orderStatus_1").children("span").html("<i>待确认<br/>"+wait+"单</i>");
	    	} else {
	    		$("#search_EQ_orderStatus_1").children("span").html("<i>待确认</i>");
	    	}
	    },
		error:function(){
		}
	  });
}
function setOrderStatus(status){
	if(currentRow && (status == 1 || status == 2)){
		$("#cancle").removeClass("yd_qxyd_no").addClass("yd_qxyd");
	}else{
		$("#cancle").removeClass("yd_qxyd").addClass("yd_qxyd_no");
	}
	if(currentRow && status == 2){
		$("#kaitai").removeClass("yd_zkt_no").addClass("yd_zkt");
	}else{
		$("#kaitai").removeClass("yd_zkt").addClass("yd_zkt_no");
	}
	
	if(currentRow && status == 1){
		$("#confirm").removeClass("yd_shqr_no").addClass("yd_shqr");
	}else{
		$("#confirm").removeClass("yd_shqr").addClass("yd_shqr_no");
	}
}
