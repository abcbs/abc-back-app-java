var currentTable = null;
var timeRefreshTable = null;
$(document).ready(function(){
	initFrameSize();
	kaitai();
	cuicai();
	diancai();
	yuding();
	jiezhang();
	chedan();
	qingtai();
	zhuantai();
	bingtai();
	waimai();
	buttonInit();
	kuaijiejian();
//	getNoWaimaiAndResettle();
	changeTableArea(currentSearchParams,1);
	
	checkTableStatusNumber();
});

//异步获得 未完结外卖单和反结账 总数
//function getNoWaimaiAndResettle()
//{
//	$.ajax({
//	    type:"get",
//	    url:window.ctx+'/index/ajax/getNoWaimaiAndResettle',
//	    cache:false,
//	    async:true,
//	    dataType: "json",
//	    success:function(data){
//	    	$("#resettlebillsSize").text(data.rel);
//	    	$("#noPayWaimaiSize").text(data.value);
//	    },
//		error:function(){
//			
//		}
//	});
//}

var indexTableAraaUlMarginLeft=0;
/**
 * 预订成调用
 */
function refreshPage()
{
	changeTableArea(currentSearchParams,1);
}
/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body").animate({width:998+subWidth},window.animate_speed);
		$(".list_l").animate({width:804+subWidth},window.animate_speed);
	}
	
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body").css("height",668+subHeight+"px");
		$(".list_r").css("height",664+subHeight+"px");
	}
}
function initWidth()
{
	setTimeout(function(){
		var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
		var subHeight = (windowAreaParams.screenHeight - 768) < 0 ? 0 : (windowAreaParams.screenHeight - 768);
		if(subWidth > 0)
		{
			$(".main_nav").css("width",804+subWidth+"px");
			
			$(".index_box").css("width",764+subWidth+"px");
			$(".search_wrap").css("width",740+subWidth+"px");
		}
		$(".index_box").css("height",540+subHeight+"px");
		
		//分区个数
		var mwidth = $(".main_nav").width();
		var areaNum = parseInt(mwidth/115);
		if($("#indexTableAraaUl").find("li").length <= areaNum)
		{
			$("#dish_left").hide();
			$("#dish_right").hide();
		}
		$(".main_nav").find("ul").css("width",($("#indexTableAraaUl").find("li").length+1)*115+"px");
		
		$("#indexTableAraaUl").css({marginLeft:indexTableAraaUlMarginLeft+"px"});
	},10);
}
 
var hotKey = null;
function kuaijiejian()
{
	hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:1,
		callBackFunction:function()
		{
			$("#kaitai").click();
		}
	}
	,
	{
		keyCode:2,
		callBackFunction:function()
		{
			$("#diancai").click();
		}
	}
	,
	{
		keyCode:3,
		callBackFunction:function()
		{
			$("#yuding").click();
		}
	}
	,
	{
		keyCode:4,
		callBackFunction:function()
		{
			$("#jiezhang").click();
		}
	}
	,
	{
		keyCode:5,
		callBackFunction:function()
		{
			$("#cuicai").click();
		}
	}
	,
	{
		keyCode:6,
		callBackFunction:function()
		{
			$("#qingtai").click();
		}
	}
	,
	{
		keyCode:7,
		callBackFunction:function()
		{
			$("#waimai").click();
		}
	}
	,
	{
		keyCode:8,
		callBackFunction:function()
		{
			$("#zhuantai").click();
		}
	}
	,
	{
		keyCode:9,
		callBackFunction:function()
		{
			$("#bingtai").click();
		}
	}
	,
	{
		keyCode:"enter",
		callBackFunction:function()
		{
			if(tabKeyTableIndex != -1)
			{
				setdblCaozuoStatus(currentTable.attr("dinnerStatus"),currentTable.attr("billStatus"));
			}
			else
			{
				search();
			}
		}
	}
	,
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/fastfood/diancai';
		}
	}
	,
	{
		keyCode:"right",
		callBackFunction:tabKeyChangeTableRight
	}
	,
	{
		keyCode:"left",
		callBackFunction:tabKeyChangeTableLeft
	}
	,
	{
		keyCode:"up",
		callBackFunction:tabKeyChangeTableUp
	}
	,
	{
		keyCode:"down",
		callBackFunction:tabKeyChangeTableDown
	}
	,
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			//搜索框得到焦点
			var isFocus=$("#keywords").is(":focus");  
			$("#keywords").focus();
			setFocus(document.getElementById("keywords"));
		}
	}
	],0);
}
/**
 * 默认的开始索引为-1
 */
var tabKeyAreaIndex = 0;
var tabKeyTableIndex = -1;
function tabKeyChangeTableUp(e)
{
	var col = PageSizeUtil.getIndexTableCol();
	var tables = $("#indexTableContent").children("div");
	var nextIndex = 0;
	if(tabKeyTableIndex == -1)
	{
		tabKeyTableIndex = 0;
		nextIndex = 0;
	}
	else if(tabKeyTableIndex >= col)
	{
		nextIndex = tabKeyTableIndex - col;
	}
	else
	{
		nextIndex= tabKeyTableIndex;
	}
	if(nextIndex >= 0 && nextIndex < tables.length)
	{
		tabKeyTableIndex = nextIndex;
		tableDivClick(e,tables.eq(tabKeyTableIndex));
	}
}

function tabKeyChangeTableDown(e)
{
	var col = PageSizeUtil.getIndexTableCol();
	var tables = $("#indexTableContent").children("div");
	var nextIndex = tabKeyTableIndex + col;
	if(tabKeyTableIndex == -1)
	{
		tabKeyTableIndex = 0;
		nextIndex = 0;
	}
	if(nextIndex >= 0 && nextIndex < tables.length)
	{
		tabKeyTableIndex = nextIndex;
		tableDivClick(e,tables.eq(tabKeyTableIndex));
	}
}
function tabKeyChangeTableLeft(e)
{
	var tables = $("#indexTableContent").children("div");
	if(tabKeyTableIndex > 0)
	{
		tabKeyTableIndex--;
		tableDivClick(e,tables.eq(tabKeyTableIndex));
	}
	
}
function tabKeyChangeTableRight(e)
{
	var tables = $("#indexTableContent").children("div");
	if(tabKeyTableIndex < tables.length-1)
	{
		tabKeyTableIndex++;
		tableDivClick(e,tables.eq(tabKeyTableIndex));
	}
}
/**
 * 点击餐台区域
 */
var currentSearchParams = '';
var currentPage = 1;
function changeTableArea(searchParams,page)
{
	ProgressbarUtil.show("ajaxContent",594);
	currentSearchParams = searchParams;
	currentPage = page;
	
	ajaxUtil.getUrlContent(getScreenSizeUrl('/index/ajax/table/list?page='+currentPage+'&'+searchParams,'index'),"ajaxContent",function(){
		tableStatusCss(null);
    	initWidth();
    	pageMove();
    	bindTableAreaEvent();
    	checkTableStatusNumber();
    	
    	index_showComponet();
	});
}


function index_showComponet(){
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
	if(subWidth > 0)
	{
		$(".search_wrap").css("width",740+subWidth+"px");
	}
	$("#pageArea").show();
}


function search()
{
	var key = $("#keywords").val();
	var sp = "search_LIKE_tabNo="+key;
	changeTableArea(sp,1);
	
}
function changeTableStatus(status,a)
{
	ProgressbarUtil.show("ajaxContent",594);
	var sp = "search_EQ_dinnerStatus="+status;
	if(currentSearchParams != null &&
	currentSearchParams.indexOf('search_EQ_dinnerStatus=') >= 0)
	{
		var st = currentSearchParams.substring(currentSearchParams.indexOf('search_EQ_dinnerStatus='),currentSearchParams.indexOf('search_EQ_dinnerStatus=')+24);
		currentSearchParams = currentSearchParams.replaceAll(st,"");
	}
	ajaxUtil.getUrlContent(getScreenSizeUrl('/index/ajax/table/list?page=1&'+currentSearchParams+"&"+sp,'index'),"ajaxContent",function(){
		initWidth();
    	tableStatusCss(a);
    	pageMove();
    	bindTableAreaEvent();
	});
}
/**
 * 餐台状态按钮的样式
 * @param a
 */
function tableStatusCss(a)
{
	
	$("a",".zhuangtai_area").each(function(){
		var ecl = $(this).attr("ori");
		$(this).removeClass().attr("class",ecl);
	});
	if(a)
	{
		var ao = $(a);
		var cl = ao.attr("class");
		if(cl.indexOf("_on") < 0)
		{
			ao.addClass(cl+"_on");
		}
		
		
		if(cl == 'zt_shiyong')
		{
			$("#indexStatusShow").text("餐台状态－使用中");
		}
		else if(cl == 'zt_jiejiang')
		{
			$("#indexStatusShow").text("餐台状态－已结账");
		}
		else if(cl == 'zt_kongxian')
		{
			$("#indexStatusShow").text("餐台状态－空闲");
		}
	}
	else
	{
		//右下角状态样式改变
		if(currentSearchParams != null &&
				currentSearchParams.indexOf('search_EQ_dinnerStatus=') >= 0)
		{
			var st = currentSearchParams.substring(currentSearchParams.indexOf('search_EQ_dinnerStatus=')+23,currentSearchParams.indexOf('search_EQ_dinnerStatus=')+24);
			if(st == '1')
			{
				var o = $("#zt_idle")[0];
				tableStatusCss(o);
			}
			else if(st == '5')
			{
				var o = $("#zt_payed")[0];
				tableStatusCss(o);
			}
			else if(st == '2')
			{
				var o = $("#zt_using")[0];
				tableStatusCss(o);
			}
			else
			{
				$("#zt_quanbu").addClass("zt_quanbu_on");
				$("#indexStatusShow").text("餐台状态－全部");
			}
		}
		else
		{
			$("#zt_quanbu").addClass("zt_quanbu_on");
			$("#indexStatusShow").text("餐台状态－全部");
		}
	}
}



function bindTableAreaEvent()
{
	tabKeyTableIndex = -1;
	var intervalTimer = null;
	
	$("#keywords").unbind();
	$("#keywords").keydown(function(evt) {
		//0-9
		if(evt.keyCode == 13)//1
		{
			search();
		}
	});
	
	$(".index_box").unbind("keydown");
	$(".index_box").keydown(function(evt) {
		if(evt.keyCode == 9)
		{
			var lis = $("#indexTableAraaUl").children("li");
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
	
//	$("div",$(".left",".index_box")).dblclick(function(event){
//		event.stopPropagation();
//		clearTimeout(intervalTimer);
//		var div = $(this);
//		if(!div.hasClass("on"))
//		{
//			removeAllOnClass();
//			div.addClass("on");
//			currentTable = div;
//			setdblCaozuoStatus($(this).attr("dinnerStatus"),$(this).attr("billStatus"));
//		}
//		else
//		{
//			div.removeClass("on");
//			buttonInit();
//		}
//	});
	$(".index_box").bind('contextmenu', function() {
	      return false;
	    });
	$(".index_box").click(function(event){
		event.stopPropagation();
		removeAllOnClass();
		buttonInit();
		currentTable = null;
		if($("#contextmenu").attr("id"))
    	{
			$("#contextmenu").remove();
    	}
	});
//	$("div",$("#indexTableContent")).click(function(event){
//		event.stopPropagation();
//		var div = $(this);
//        // click 事件的处理
//		currentTable = div;
//		if(!div.hasClass("on"))
//		{
//			removeAllOnClass();
//			div.addClass("on");
//			setCaozuoStatus(div.attr("dinnerStatus"),div.attr("billStatus"));
//		}
//		else
//		{
//			setdblCaozuoStatus(div.attr("dinnerStatus"),div.attr("billStatus"));
//		}
//	});
	$('div',"#indexTableContent").contextmenu({
		items : [{
			text :'开台',
			style:'index_kaitai',
			action: function(event,target){
				$("#kaitai").click();
			}
		},{
			text :'点菜',
			style:'index_diancai',
			action: function(event,target){
				$("#diancai").click();
			}
		},{
            text :'预订',
			style:'index_yuding',
            action: function(event,target){
            	$("#yuding").click();
            }
        },{
            text :'结账',
			style:'index_jiezhang',
            action: function(event,target){
            	$("#jiezhang").click();
            }
        },{
            text :'催菜',
			style:'index_cuicai',
            action: function(event,target){
            	$("#cuicai").click();
            }
        },{
            text :'清台',
			style:'index_qingtai',
            action: function(event,target){
            	$("#qingtai").click();
            }
        },{
            text :'外卖',
			style:'index_waimai',
            action: function(event,target){
            	$("#waimai").click();
            }
        },{
            text :'转台',
			style:'index_zhuantai',
            action: function(event,target){
            	$("#zhuantai").click();
            }
        },{
            text :'并台',
			style:'index_bingtai',
            action: function(event,target){
            	$("#bingtai").click();
            }
        },{
            text :'撤单',
			style:'index_chedan',
            action: function(event,target){
            	$("#chedan").click();
            }
        }
		]
	});	
	
	$("#dish_left").click(function(){
		var left = $("#indexTableAraaUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(left < 0)
		{
			left+= 500;
			indexTableAraaUlMarginLeft = left;
			$("#indexTableAraaUl").animate({marginLeft:left+"px"},100);
		}
	});
	
	$("#dish_right").click(function(){
		var mainTabWidth = $(".main_nav").width();
		var mainCatagoryTabWidth = $("#indexTableAraaUl").width();
		var left = $("#indexTableAraaUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(mainCatagoryTabWidth + left < mainTabWidth)
		{
			return;
		}
		
		left-= 500;
		indexTableAraaUlMarginLeft = left;
		$("#indexTableAraaUl").animate({marginLeft:left+"px"},100);
	});
	
	clearInterval(timeRefreshTable);
	timeRefreshTable = setInterval("refreshTable()",30000);
}

//餐台 的左右键点击事件处理
function tableDivClick(event,divDom)
{
	
	event=event?event:window.event;
    event.cancelBubble=true;
	
	var div = $(divDom);
	if(!div.attr("id"))
	{
		return false;
	}
	
    // click 事件的处理
	currentTable = div;
	
	//如果是左键
	if(event.which == 1 || event.which != 3)
	{
		if(document.getElementById("contextmenu")){

			document.getElementById("contextmenu").style.display="none";
		}
		if(!div.hasClass("on"))
		{
			removeAllOnClass();
			div.addClass("on");
			setCaozuoStatus(div.attr("dinnerStatus"),div.attr("billStatus"),div.attr("isChedan"));
			
		}
		else
		{
			setdblCaozuoStatus(div.attr("dinnerStatus"),div.attr("billStatus"));
			
		}
	}
	else if(event.which == 3)
	{
		//右键
		if(!div.hasClass("on"))
		{
			removeAllOnClass();
			div.addClass("on");
		
		}
		setCaozuoStatus(div.attr("dinnerStatus"),div.attr("billStatus"),div.attr("isChedan"));
	}
	
	//判断是否有预订
	var validateYD = orderJudge(currentTable);
	var time = currentTable.find(".time").text();
	if(validateYD == 1){
		toastr.info("本桌"+time+" 被客人预订");
	}
	else if(validateYD == '2'){
		dialogBoxConfirm("锁   定","本桌"+time+" 被客人预订，已经锁定!",function(){
		});
		return false;
	}
	else{
	}
	return true;
}
/**
 * 预订判断
 * 返回值为 1 该餐台在预订预警时间范围
 * 返回值为 2 该餐台已经被预订锁定
 */
function orderJudge(table)
{
	//预订判断
	//是否有预订
	var isHasTableOrder = table.attr("isHasTableOrder");
	if(isHasTableOrder && isHasTableOrder == '1')//该餐台进入预警时间与到期保留时间区间
	{
		var isAtOrderWarnTime = table.attr("isAtOrderWarnTime");
		if(isAtOrderWarnTime != '1')//非预警时间内均需要锁定餐桌
		{
//			dialogBoxConfirm("锁   定","本桌"+time+" 被客人预订，已经锁定!",function(){
//			});
			return 2;
		}
		else{
//			toastr.info("本桌"+time+" 被客人预订");
			return 1;
		}
	}
	return 0;
}



function refreshTable()
{
	checkTableStatus();
	checkTableStatusNumber();
}

function checkTableStatusNumber()
{
	$.ajax({
	    type:"get",
	    url:window.ctx+'/index/getTableStatusNumber',
	    cache:false,
	    async:true,
	    dataType: "json",
	    success:function(data){
	    	var json = eval(data.message);
	    	var ts = json[0];
	    	$("#zt_quanbu").html("<span>全部<br/>"+ts.all+"台</span>");
	    	$("#zt_using").html("<span>使用中<br/>"+ts.using+"台</span>");
	    	$("#zt_payed").html("<span>已结账<br/>"+ts.payed+"台</span>");
	    	$("#zt_idle").html("<span>空闲<br/>"+ts.idle+"台</span>");
	    	
	    	//异步获得 未完结外卖单和反结账 总数
	    	if(ts.resettlebillsSize != '0'){
	    		$("#resettlebillsSize").text("反结账："+ts.resettlebillsSize);
	    	}
	    	if(ts.noPayWaimaiSize != '0'){
	    		$("#noPayWaimaiSize").text("未完结外卖单："+ts.noPayWaimaiSize);
	    	}
	    	
	    },
		error:function(){
			
		}
	});
}

function checkTableStatus()
{
	$.ajax({
	    type:"get",
	    url:window.ctx+'/index/checkAllTableStatus',
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	var json = eval(data.message);
	    	for(var i=0;i<json.length;i++)
	    	{
	    		var tab = json[i];
	    		var tabObj = $("#"+tab.tabId);
	    		if(tabObj == null)//非本页的餐桌或出现新餐桌，暂不做处理
	    			continue;
	    		var oraDinnerStatus = tabObj.attr("dinnerStatus");
	    		var dinnerStatus = tab.dinnerStatus;//餐台状态
	    		var billStatus = tab.billStatus;//账单状态
	    		var isChedan = tab.isChedan;//是否允许撤单
	    		var peopleNum = tab.peopleNum;//就餐人数
	    		var isHasTableOrder = tab.isHasTableOrder;//是否进入预订预警与到期保留区间
	    		var isAtOrderWarnTime = tab.isAtOrderWarnTime;//预订账单是否在预警时间范围
//	    		if(oraDinnerStatus != dinnerStatus)
//	    		{
	    			tabObj.attr("dinnerStatus",dinnerStatus);
		    		tabObj.attr("billStatus",billStatus);
		    		tabObj.attr("billId",tab.billId);
		    		tabObj.attr("isChedan",isChedan);
		    		tabObj.attr("isHasTableOrder",isHasTableOrder);
		    		tabObj.attr("isAtOrderWarnTime",isAtOrderWarnTime);
		    		var isSelected = tabObj.hasClass("on") ? true : false;
	    			var cuClass = tabObj.attr("class");
	    			
		    		if(dinnerStatus == '1' || dinnerStatus=='')
		    		{
		    			if(isHasTableOrder == '1' && isAtOrderWarnTime == '0'){//是预订账单且在预订预警与保留区间内且已过预警时间
		    				tabObj.removeClass().addClass("yuding_1");
		    				var orderTime = tab.orderTime;
		    				tabObj.find(".time").html(orderTime);
		    				tabObj.find(".shuliang").html(peopleNum+"/"+tab.seatNum);
		    			}
		    			else{
		    				tabObj.removeClass().addClass("kongxian");
		    				tabObj.find(".time").html("");
		    				tabObj.find(".shuliang").html('0'+"/"+tab.seatNum);
		    			}
		    		}
		    		else if(dinnerStatus == '2')
		    		{
		    			var cc = tabObj.attr("class");
		    			if(cc.indexOf('shiyong_w') >= 0)
		    			{
		    				tabObj.removeClass().addClass("shiyong_w");
		    			}
		    			else 
		    			{
		    				tabObj.removeClass().addClass("shiyong");
		    			}
		    			
		    			var billTime = tab.billTime;
		    			tabObj.find(".time").html(billTime);
		    			tabObj.find(".shuliang").html(peopleNum+"/"+tab.seatNum);
		    		}
		    		else if(dinnerStatus == '4')
		    		{
		    			tabObj.remove();
		    		}
		    		else if(dinnerStatus == '5')
		    		{
		    			tabObj.removeClass().addClass("yi_jiezhang");
		    			tabObj.find(".time").html("");
		    			tabObj.find(".shuliang").html('0'+"/"+tab.seatNum);
		    		}
		    		else if(dinnerStatus == '6')
		    		{
		    			tabObj.removeClass().addClass("yuding_1");
		    			var orderTime = tab.orderTime;
	    				tabObj.find(".time").html(orderTime);
	    				tabObj.find(".shuliang").html(peopleNum+"/"+tab.seatNum);
		    		}
		    		if(isSelected)
	    			{
		    			tabObj.addClass("on"); 
		    			setCaozuoStatus(dinnerStatus,billStatus,tabObj.attr("isChedan"));
	    			}
//	    		}
	    		var tableSeat = $("#tableSeat").val();
    			var peopleNum = tab.peopleNum;
    			tabObj.find(".shuliang").html(peopleNum+'/'+tableSeat);
	    	}
	    },
		error:function(){
		}
	});

}


function setCaozuoStatus(dinnerStatus,billStatus,isChedan)
{
	if(dinnerStatus)
	{
		if(dinnerStatus == 1)
		{
			//空闲 	
			setButtonKaiTaiOn();
			setButtonDianCaiOff();
			setButtonYuDingOn();
			setButtonJieZhangOff();
			setButtonCuiCaiOff();
			setButtonCheDanOff();
			setButtonZhuanTaiOff();
			setButtonBingTaiOff();
			setButtonQingTaiOff();
		}
		else if(dinnerStatus == 2)
		{
			//使用中
			setButtonKaiTaiOn();
			setButtonDianCaiOn();
			setButtonYuDingOn();
			setButtonJieZhangOn();
			
			if(billStatus == 1){
				setButtonCuiCaiOff();
			}
			else{
				setButtonCuiCaiOn();
			}
			
			//if(isChedan == "1"){
				setButtonCheDanOn();
			//}else{
				//setButtonCheDanOff();
			//}
			
			setButtonZhuanTaiOn();
			setButtonBingTaiOn();
			setButtonQingTaiOff();
		}
		else if(dinnerStatus == 3)
		{
			//待清台
		}
		else if(dinnerStatus == 4)
		{
			//停用
		}
		else if(dinnerStatus == 5)
		{
			//已结账
			setButtonKaiTaiOff();
			setButtonDianCaiOff();
			setButtonYuDingOn();
			setButtonJieZhangOff();
			setButtonCuiCaiOff();
			setButtonCheDanOff();
			setButtonZhuanTaiOff();
			setButtonBingTaiOff();
			
			setButtonQingTaiOn();
		}
		else if(dinnerStatus == 6)
		{
			//预订
			setButtonKaiTaiOff();
			setButtonDianCaiOff();
			setButtonYuDingOn();
			setButtonJieZhangOff();
			setButtonCuiCaiOff();
			setButtonCheDanOff();
			setButtonZhuanTaiOff();
			setButtonBingTaiOff();
			setButtonQingTaiOff();
		}
	}
	else
	{
		//null
	}
}

function setdblCaozuoStatus(dinnerStatus,billStatus)
{
	if(dinnerStatus)
	{
		if(dinnerStatus == 1)
		{
			//空闲 	
			kaitaiFunction();
		}
		else if(dinnerStatus == 2)
		{
			//使用中
			if(billStatus == 1)
			{
				//只有未下单的
				diancaiFunction();
			}
			else
			{
				jiezhangFunction();
			}
		}
		else if(dinnerStatus == 3)
		{
			//待清台
			qingtaiFunction();
		}
		else if(dinnerStatus == 4)
		{
			//停用
			
		}
		else if(dinnerStatus == 5)
		{
			//已结账，双击是清台
			qingtaiFunction();
		}
		else if(dinnerStatus == 6)
		{
			//预订
			kaitaiFunction();
		}
	}
	else
	{
		//null
	}
}
function buttonInit()
{
	setButtonKaiTaiOn();
	setButtonDianCaiOff();
	setButtonYuDingOn();
	setButtonJieZhangOff();
	setButtonCuiCaiOff();
	setButtonCheDanOff();
	setButtonZhuanTaiOff();
	setButtonBingTaiOff();
	setButtonQingTaiOff();
}
function setButtonKaiTaiOn()
{
	$("#kaitai").removeClass();
	$("#kaitai").addClass("kaitai");
}
function setButtonKaiTaiOff()
{
	$("#kaitai").removeClass();
	$("#kaitai").addClass("kaitai_on");
}
function setButtonDianCaiOn()
{
	$("#diancai").removeClass();
	$("#diancai").addClass("diancai");
	if($("#contextmenu").attr("id"))
	{
		$("#index_diancai").removeClass();
		$("#index_diancai").addClass("index_diancai");
	}
}
function setButtonDianCaiOff()
{
	$("#diancai").removeClass();
	$("#diancai").addClass("diancai_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_diancai").removeClass();
		$("#index_diancai").addClass("index_diancai_on");
	}
}
function setButtonYuDingOn()
{
	$("#yuding").removeClass();
	$("#yuding").addClass("yuding");
	if($("#contextmenu").attr("id"))
	{
		$("#index_yuding").removeClass();
		$("#index_yuding").addClass("index_yuding");
	}
}
function setButtonYuDingOff()
{
	$("#yuding").removeClass();
	$("#yuding").addClass("yuding_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_yuding").removeClass();
		$("#index_yuding").addClass("index_yuding_on");
	}
}

function setButtonJieZhangOn()
{
	$("#jiezhang").removeClass();
	$("#jiezhang").addClass("jiezhang");
	if($("#contextmenu").attr("id"))
	{
		$("#index_jiezhang").removeClass();
		$("#index_jiezhang").addClass("index_jiezhang");
	}
}
function setButtonJieZhangOff()
{
	$("#jiezhang").removeClass();
	$("#jiezhang").addClass("jiezhang_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_jiezhang").removeClass();
		$("#index_jiezhang").addClass("index_jiezhang_on");
	}
}
function setButtonCuiCaiOn()
{
	$("#cuicai").removeClass();
	$("#cuicai").addClass("cuicai");
	if($("#contextmenu").attr("id"))
	{
		$("#index_cuicai").removeClass();
		$("#index_cuicai").addClass("index_cuicai");
	}
}
function setButtonCuiCaiOff()
{
	$("#cuicai").removeClass();
	$("#cuicai").addClass("cuicai_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_cuicai").removeClass();
		$("#index_cuicai").addClass("index_cuicai_on");
	}
}
function setButtonCheDanOn()
{
	$("#chedan").removeClass();
	$("#chedan").addClass("chedan");
	if($("#contextmenu").attr("id"))
	{
		$("#index_chedan").removeClass();
		$("#index_chedan").addClass("index_chedan");
	}
}
function setButtonCheDanOff()
{
	$("#chedan").removeClass();
	$("#chedan").addClass("chedan_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_chedan").removeClass();
		$("#index_chedan").addClass("index_chedan_on");
	}
}
function setButtonZhuanTaiOn()
{
	$("#zhuantai").removeClass();
	$("#zhuantai").addClass("zhuantai");
	if($("#contextmenu").attr("id"))
	{
		$("#index_zhuantai").removeClass();
		$("#index_zhuantai").addClass("index_zhuantai");
	}
}
function setButtonZhuanTaiOff()
{
	$("#zhuantai").removeClass();
	$("#zhuantai").addClass("zhuantai_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_zhuantai").removeClass();
		$("#index_zhuantai").addClass("index_zhuantai_on");
	}
}

function setButtonQingTaiOn()
{
	$("#qingtai").removeClass();
	$("#qingtai").addClass("qingtai");
	if($("#contextmenu").attr("id"))
	{
		$("#index_qingtai").removeClass();
		$("#index_qingtai").addClass("index_qingtai");
	}
}
function setButtonQingTaiOff()
{
	$("#qingtai").removeClass();
	$("#qingtai").addClass("qingtai_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_qingtai").removeClass();
		$("#index_qingtai").addClass("index_qingtai_on");
	}
}
function setButtonBingTaiOn()
{
	$("#bingtai").removeClass();
	$("#bingtai").addClass("bingtai");
	if($("#contextmenu").attr("id"))
	{
		$("#index_bingtai").removeClass();
		$("#index_bingtai").addClass("index_bingtai");
	}
}
function setButtonBingTaiOff()
{
	$("#bingtai").removeClass();
	$("#bingtai").addClass("bingtai_on");
	if($("#contextmenu").attr("id"))
	{
		$("#index_bingtai").removeClass();
		$("#index_bingtai").addClass("index_bingtai_on");
	}
}

function kaitai()
{
	
	$("#kaitai").click(function(){
	
		if(!$(this).hasClass("kaitai_on"))
		{
			kaitaiFunction();
		}
		
			
	});
	
}

function kaitaiFunction()
{
	if(getPermission("kaitai"))
	{
		if(!currentTable)
		{
			popForm('开台',window.ctx+'/index/pop/kaitai/create','880','644');
		}
		else
		{
			var cid = currentTable.attr("id");
			var dinnerStatus = currentTable.attr("dinnerStatus");
			
			var validateYD = orderJudge(currentTable);
			//判断是否有预订
			if(validateYD == 2)
			{
				return false;
			}
			if(dinnerStatus == 2)
			{
				//使用中，则修改
				popForm('开台',window.ctx+'/index/pop/kaitai/update/'+cid,'880','644');
			}
			else
			{
				popForm('开台',window.ctx+'/index/pop/kaitai/create?tabId='+cid,'880','644');
			}
		}
	}
}
function diancai()
{
	
	$("#diancai").click(function(){
		if(!$(this).hasClass("diancai_on"))
		{
			diancaiFunction();
		}
	});
}

function diancaiFunction()
{
	if(getPermission("diancai"))
	{
		if(currentTable)
		{
			//已开台的
			var billId = currentTable.attr("billId");
			$("#diancai").attr("href",window.ctx+'/bill/diancai?billId='+billId);
			window.location.href = window.ctx+'/bill/diancai?billId='+billId;
		}
		else
		{
			//外卖的
			$("#diancai").attr("href",window.ctx+'/bill/diancai');
			window.location.href = window.ctx+'/bill/diancai';
		}
	}
}
function yuding()
{
	$("#yuding").click(function(){
		if(!$(this).hasClass("yuding_on"))
		{
			if(getPermission("yuding"))
			{
				if(currentTable)
				{
					var cid = currentTable.attr("id");
					popForm('预订信息',window.ctx+'/order/pop/order/create?tabId='+cid,'880','644');
				}
				else
				{
					popForm('预订信息',window.ctx+'/order/pop/order/create','880','644');
				}
			}
		}
	});
}
function jiezhang()
{
	
	$("#jiezhang").click(function(){
		if(!$(this).hasClass("jiezhang_on"))
		{
			jiezhangFunction();
		}
	});
}
function jiezhangFunction()
{
	if(getPermission("jiezhang"))
	{
		if(currentTable)
		{
			//已开台的
			var billId = currentTable.attr("billId");
			if(billId)
			{
				$("#jiezhang").attr("href",window.ctx+'/bill/payPage/'+billId);
				window.location = window.ctx+'/bill/payPage/'+billId;
			}
		}
	}
}
	
function cuicai()
{
	$("#cuicai").click(function(){
		if(!$(this).hasClass("cuicai_on"))
		{
			if(getPermission("cuicai"))
			{
				if(currentTable)
				{
					var billId = currentTable.attr("billId");
					var tabNo = currentTable.children(".zhuozi").text();
					dialogBoxConfirm("催菜","餐台号："+tabNo+" 确定整桌催菜吗？",function(){
						$.ajax({
						    type:"get",
						    url:window.ctx+"/index/cuicai/"+billId,
						    data:null,
						    cache:false,
						    async:true,
						    dataType: "json",
						    success:function(data){
						    	if(data.statusCode == '200')
					    		{
					    			toastr.success(data.message);
					    		}
						    	else
						    	{
						    		toastr.warning(data.message);
						    	}
						    },
							error:function(){
							}
						  });
					});
				}
			}
		}
	});
}

function chedan(){
	$("#chedan").click(function(){
		if(!$(this).hasClass("chedan_on"))
		{
			if(currentTable.attr("isChedan") != "1"){
				dialogBoxConfirm('撤单失败！','部分菜肴已经下单，请执行退菜通知后厨才能继续撤单！！',function(){});
				return;
			}
			
			if(getPermission("chedan"))
			{
				if(currentTable)
				{
					var tabId = currentTable.attr("id");
					var billId = currentTable.attr("billId");
					var tabNo = currentTable.children(".zhuozi").text();
					popForm('撤单',window.ctx+'/index/pop/chedan/'+billId,'529','335');
				}
			}
		}
	});
}

/**
 * 撤单的返回函数
 */
function chedanPopBack()
{
	currentTable.attr("billId","");
	refreshTable();
}

function qingtai(){
	$("#qingtai").click(function(){
		if(!$(this).hasClass("qingtai_on"))
		{
			qingtaiFunction();
		}
	});
}

function qingtaiFunction()
{
	if(getPermission("qingtai"))
	{
		if(currentTable)
		{
			var tabId = currentTable.attr("id");
			var tabNo = currentTable.children(".zhuozi").text();
			dialogBoxConfirm("清台","餐台号："+tabNo+" 确定清台？",function(){
				ajaxUtil.submitUrl(window.ctx+"/index/qingtai/"+tabId,function(data){
//					toastr.success(data.message);
					refreshTable();
				});
			});
		}
	}
}

function waimai(){
	$("#waimai").click(function(){
		if(!$(this).hasClass("waimai_on"))
		{
			if(getPermission("waimai"))
			{
				//跳转到外卖
				window.location = window.ctx+'/takeout/list';
			}
		}
	});
}


function bingtai()
{
	$("#bingtai").click(function(){
		if(!$(this).hasClass("bingtai_on"))
		{
			if(getPermission("bingtai"))
			{
				var tabId = currentTable.attr("id");
				popForm('并台',window.ctx+'/index/pop/bingtai/'+tabId,'880','644');
			}
		}
	});
}

function zhuantai()
{
	$("#zhuantai").click(function(){
		if(!$(this).hasClass("zhuantai_on"))
		{
			if(getPermission("zhuantai"))
			{
				var tabId = currentTable.attr("id");
				popForm('转台',window.ctx+'/index/pop/zhuantai/'+tabId,'880','644');
			}
		}
	});
}


function removeAllOnClass()
{
	$("div",$(".left",".index_box")).each(function(){
		if($(this).hasClass("on"))
		{
			$(this).removeClass("on");
		}
	});
}
function ajaxErrorFunction()
{
	toastr.error("出错了");
}

function pageMove(){
}

//点击进入反结账界面
function resettlebillsSizeClick(){
	window.location = window.ctx+'/bill/list?search_EQ_billStatus=4';
}

//点击进入外卖界面
function noPayWaimaiSizeClick(){
	window.location = window.ctx+'/bill/list?search_EQ_billStatus=takeOut';
}