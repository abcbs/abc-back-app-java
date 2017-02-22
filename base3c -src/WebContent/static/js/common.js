window.ctx = "/";
var url = window.location.href;
if(url.indexOf('canyin-frontdesk') > 0)
{
	window.ctx = "/canyin-frontdesk";
}

var windowAreaParams = {
	indexTablePageSize:20
};

var stand_screenHeight = 768;
var stand_screenWidth = 1000;
var animate_speed = 300;
var initFrameSize_subWidth = 25;//页面宽度减去的值

//当前时间
var C_CURRENTTIME = "";
//是否需要刷新云订单
var needRefreshCloud = "0";

$(document).ready(function(){
	displayScreenSize();
	initMainNav();
	changeBrowser();
	//消息
//	messageInit();
	
	screenAdapt();
	initPageSize();//初始化每个功能列表的分页数
	
	pageNoAction();//页面无操作监听
	//定时器系统时间
	/*setInterval(timer,"1000");*/
	initMessage();
//	callerIDDisplay('18612181817');
});

//远程测试
function callerIDDisplay(telephoneNo){
	jQuery.ajax({
		url: window.ctx + "/self/message/callerIDDisplay",
		data:{telephoneNo : telephoneNo},
		type: "POST",
		dataType: "JSON",
		success: function(data){
			var messageId = data.messageId;
			var name = data.name;
			var time = data.time;
			var telephoneNo = data.telephoneNo;
			var content = "<div class='p-ldxs-main' id='messageContent_"+messageId+"'>";
			if(typeof(name) != "undefined") {
				content = content + "<div class='p-ldxs-main-font p-ldxs-main-fonts'><label>来电会员：" + name + "</label></div>";
			}
			content = content + "<div class='p-ldxs-main-font'><label>来电号码：" + telephoneNo + "</label></div>"+
								"<div class='p-ldxs-main-font'><label>来电时间：" + time + "</label></div>"+
							"<div class='p-ldxs-main-but'>"+
								'<input type=\'button\' value=\'外卖\' class=\'but_wm_p\' onclick=\"messageTakeout(\''+messageId+'\',\''+telephoneNo+'\');\">'+
								'<input type=\'button\' value=\'预订\' class=\'but_yd_p\' onclick=\"messageOrder(\''+messageId+'\',\''+telephoneNo+'\');\">'+
								'<input type=\'button\' value=\'取消\' class=\'but_qx_p\' onclick=\"messageCancel(\''+messageId+'\');\">'+
							"</div>"+
						  "</div>";
			$(content).dialog({resizable:false,position: ['right', 'bottom'],width:529,title:"来电记录",closeText:"关闭"});
		},
		error:function(){
		}
	});
}

function messageTakeout(messageId,telephoneNo) {
	jQuery.ajax({
		url: window.ctx + "/self/message/pop/msgHandle/displayCall",
		data:{
			messageId : messageId,
			handleType : '1'
		},
		type: "POST",
		dataType: "JSON",
		success: function(data){
			var status = data.statusCode;
			if(status == '200')
			{
				$("#messageContent").dialog('close');
				
				var href = $("#foot_waimai").attr("onclick");
				var endpos = href.lastIndexOf("'");
				href = href.substring(0,endpos);
				href = href + "?from=message&preMobile=" + telephoneNo + "'";
				$("#foot_waimai").attr("onclick",href);
				$("#foot_waimai").click();
			}
		},
		error:function(){
			
		}
	});
	
}

function messageOrder(messageId,telephoneNo) {
	jQuery.ajax({
		url: window.ctx + "/self/message/pop/msgHandle/displayCall",
		data:{
			messageId : messageId,
			handleType : '2'
		},
		type: "POST",
		dataType: "JSON",
		success: function(data){
			var status = data.statusCode;
			if(status == '200')
			{
				$("#messageContent").dialog('close');
				
				var href = $("#foot_yuding").attr("onclick");
				var endpos = href.lastIndexOf("'");
				href = href.substring(0,endpos);
				href = href + "?from=message&preMobile=" + telephoneNo + "'";
				$("#foot_yuding").attr("onclick",href);
				$("#foot_yuding").click();
			}
		},
		error:function(){
			
		}
	});
}

function messageCancel(messageId) {
	jQuery.ajax({
		url: window.ctx + "/self/message/pop/msgHandle/displayCall",
		data:{
			messageId : messageId,
			handleType : '3'
		},
		type: "POST",
		dataType: "JSON",
		success: function(data){
			var status = data.statusCode;
			if(status == '200')
			{
				$("#messageContent_" + messageId).dialog('close');
			}
		},
		error:function(){
			
		}
	});
}

//系统当前时间
function timer(){
	jQuery.ajax({
		url: window.ctx + "/common/getDateTime",
		type: "POST",
		dataType: "JSON",
		success: function(data){
			var statusCode = data.statusCode;
			//未登录
			if(statusCode == 600){
				window.location = window.ctx+"/login?relogin=1";
				return;
			}
			//保存当前时间
			C_CURRENTTIME = data.nowDate + " " + data.nowTime;
			$("#currentDate").html(data.nowDate);
			$("#currentTime").html(data.nowTime);
			
		},
		error:function(){
		}
	});
}

/**
 * 初始化信息
 */
function initMessage(){
	getMessages();
	setInterval(getMessages,"60000");
}

/**
 * 定时获取消息
 */
function getMessages(){
	jQuery.ajax({
		url: window.ctx + "/common/getMessages",
		type: "POST",
		dataType: "JSON",
		success: function(data){
			var statusCode = data.statusCode;
			//未登录
			if(statusCode == 600){
				window.location = window.ctx+"/login?relogin=1";
				return;
			}
			
			var noPayTakeoutSize = data.noPayTakeoutSize;
			var noReadSelfMessagesSize = data.noReadSelfMessagesSize;
			var noOperationCloudSize = data.noOperationCloudSize;
			if(noPayTakeoutSize > 0){
				$("#noPayTakeoutSize").html(noPayTakeoutSize);
				$("#noPayTakeoutSize").show();
			}
			if(noPayTakeoutSize == 0){
				$("#noPayTakeoutSize").html("");
				$("#noPayTakeoutSize").hide();
			}
			if(noReadSelfMessagesSize > 0){
				$("#noReadSelfMessagesSize").html(noReadSelfMessagesSize);
				$("#noReadSelfMessagesSize").show();
			}
			if(noReadSelfMessagesSize == 0){
				$("#noReadSelfMessagesSize").html("");
				$("#noReadSelfMessagesSize").hide();
			}
			if(noOperationCloudSize > 0){
				$("#waitReview").text(noOperationCloudSize);
				$("#noOperationCloudSize").html(noOperationCloudSize);
				$("#noOperationCloudSize").show();
			}
			if(noOperationCloudSize == 0){
				$("#waitReview").text("");
				$("#noOperationCloudSize").html("");
				$("#noOperationCloudSize").hide();
			}
			needRefreshCloud = data.needRefreshCloud;
			if(needRefreshCloud == '1'){
				//播放声音
				playSound();
				if(url.indexOf("/cloud") >= 0){
					if(typeof(eval("refreshCloudBill")) == "function") {
						refreshCloudBill();
					}
				}
			}
		},
		error:function(){
		}
	});
}

/**
 * 初始化每个功能列表的分页数
 */
function initPageSize()
{
	//主页餐台
	windowAreaParams.indexTablePageSize = 20;
}
function screenAdapt()
{
	var subWidth = windowAreaParams.screenWidth - stand_screenWidth - 25;
	$(".footer_wrap").css("width",1000+subWidth+"px");
	$(".footer_in").css("width",1000+subWidth+"px");
}

function displayScreenSize()
{
	windowAreaParams.bodyWidth=document.body.clientWidth;      //网页可见区域宽
	windowAreaParams.bodyHeight=document.body.clientHeight;     //网页可见区域高
	windowAreaParams.screenWidth=window.screen.width;            //屏幕分辨率的宽
//	windowAreaParams.screenHeight=window.screen.height;           //屏幕分辨率的高
	windowAreaParams.screenHeight=$(window).height();
	windowAreaParams.scrollWidth=document.body.scrollWidth;
	windowAreaParams.scrollHeight=document.body.scrollHeight;
}
$(window).resize(function() {
	displayScreenSize();
	screenAdapt();
	if("undefined" == typeof initFrameSize)
	{
	}
	else
	{
		if(initFrameSize)
		{
			initFrameSize();
		}
	}
});
/*********************************页面无操作计时器*******************************************************/
function pageNoAction()
{
	if(userSetLockSeconds == null)
	{
		//去服务器取时间
		jQuery.ajax({
			url: window.ctx + "/index/getLockLeaveTime",
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				userSetLockSeconds = data.message;
				if(userSetLockSeconds == 0)
				{
					//无锁屏
				}
				else
				{
					bodyMouseAction();
					mouseOpTimer();
				}
			},
			error:function(){
				userSetLockSeconds = 0;
			}
		});
	}
}

var userSetLockSeconds = null;//用户定义的锁屏时间
var mouseOpTime = null;  
var secondClock = 0;
function mouseOpTimer()
{	
	clearInterval(mouseOpTime);
	mouseOpTime = setTimeout(function(){  
		secondClock += 30;
		//toastr.error(secondClock+"秒钟没有操作了！"  + (userSetLockSeconds-secondClock) + "秒后锁屏");
		if(secondClock >= userSetLockSeconds)
		{
			popForm('登录',window.ctx+'/index/pop/poplogin','529','335');
		}
		else
		{
			mouseOpTimer();
		}
	},30000);  
}
	
function bodyMouseAction(){  
	$("body").bind("mouseover",function(){
		secondClock = 0;
	});
}
	
	
	
	
/**************消息提示 start***************************************************************/

var timeRadSlideModule = null;
function messageInit(){
	
	currentMsg = $("#currentMsg");
	newMsg = $("#newMsg");
	$(".cishu").hide();
	
	$("#msgHandle").bind("click",function(){
		popForm('消息处理',window.ctx+'/self/message/pop/msgHandle','880','644');
	});
	getCustomerOrderInfo();
	clearInterval(timeRadSlideModule);
	timeRadSlideModule = setInterval("getCustomerOrderInfo()",10000);
}

var currentMsg = null;
var msgLastCalledTime = '';
function getCustomerOrderInfo()
{
	$.ajax({
	    type:"get",
	    url:window.ctx+'/self/message/getCustomerOrderInfo?lastCalledTime='+msgLastCalledTime,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
			var status = data.statusCode;
			if(status == '200')
			{
				var message =data.message;
				msgLastCalledTime = data.value;//最后一个呼叫时间
				var msgNum =data.sign;//自助点餐数量
				messageArraive(message,msgNum);
			}
			else if(status == '600')
			{
			}
			else
			{
				jumpMsgAlert(0);
			}
	    },
		error:function(){
		}
	});
}


function addMessage(message)
{
	newMsg.html(message);
	slideMessage();
}
function slideMessage()
{
	var d=1500;
	currentMsg.animate({"top": "57px"}, d,function(){
		newMsg.css({"top": "-57px"});
	});
	newMsg.animate({"top": "0px"}, d);
	
	var temp = currentMsg;
	currentMsg = newMsg;
	newMsg = temp;
	
}

function messageArraive(message,msgNum)
{
	if(!msgNum || msgNum == 0 )
	{
		$(".cishu").hide();
	}
	else{
		$(".cishu").show();
	}
	$(".cishu").html(msgNum);
	jumpMsgAlert(msgNum);
    addMessage(message);
}

function jumpMsgAlert(msgNum)
{
	if(msgNum > 0)
	{
		$(".cishu")
	    .animate({top:"-16px"}, 200).animate({top:"-10px"}, 200) // first jump
	    .animate({top:"-13px"}, 100).animate({top:"-10px"}, 100) // second jump
	    .animate({top:"-12px"}, 100).animate({top:"-10px"}, 100); // the last jump
	}
	else
	{
		$(".cishu").hide();
	}
	
}
	
/**************消息提示 end**************************************************************/
function changeBrowser()
{
	var width = $(document).width();
	width = 1000;
	$(".footer_wrap").css({width:width+"px"});
	$(".footer_in").css({width:(width-1)+"px"});
}

function initMainNav()
{
	var site_url = window.location.href.toLowerCase();	
	switch (true) {
	case site_url.indexOf("/index") > 0 : 
		$(".bottom_nav").find("#foot_cantai").removeClass().addClass("b_cantai_on");
		break;
	case site_url.indexOf("/fastfood") > 0 : 
		$(".bottom_nav").find("#foot_kuaican").removeClass().addClass("b_kuaican_on");
		break;	
	case site_url.indexOf("/takeout") > 0 : 
		$(".bottom_nav").find("#foot_waimai").removeClass().addClass("b_waimai_on");
		break;
	case site_url.indexOf("/bill") > 0 :
		$(".bottom_nav").find("#foot_zhangdan").removeClass().addClass("b_zhangdan_on");
		break;	
	case site_url.indexOf("/member") > 0 : 
		$(".bottom_nav").find("#foot_huiyuan").removeClass().addClass("b_huiyuan_on");
		break;	
	case site_url.indexOf("/order") > 0 : 
		$(".bottom_nav").find("#foot_yuding").removeClass().addClass("b_yuding_on");
		break;	
	case site_url.indexOf("/cloud") > 0 : 
		$(".bottom_nav").find("#foot_yundingdan").removeClass().addClass("b_yundingdan_on");
		break;	
	case site_url.indexOf("/estimate") > 0 : 
		$(".bottom_nav").find("#foot_guqing").removeClass().addClass("b_guqing_on");
		break;
	case site_url.indexOf("/employe") > 0 : 
		$("#foot_dating").removeClass().addClass("yonghu_a_on");
		break;	
	case site_url.indexOf("/system") > 0 : 
		$("#foot_shezhi").removeClass().addClass("but_shezhi_on");
		break;	
	default :
	}
}

function webBrowse()
{
	//改变。.body .list_l .main_nav .box_1 .box_2 宽度
}
/**
 * statusCode 200 成功
 * message 
 * navTabId
 * rel
 * callbackType
 * forwardUrl 跳转到url
 */
var AjaxService = {
	getJsonData : function (url,callBackFunction,errorFunction){
		
		var errorF =  typeof errorFunction == 'function' ? errorFunction : error;
		$.ajax({
		    type:"get",
		    url:window.ctx+url,
		    data:null,
		    cache:false,
		    success:callBackFunction,
			error:errorF
		  });
	},
	error:function()
	{
		alert("error");
	}
};

function popForm(title,thisUrl,width,height) {
	$("body").unbind("keydown");
	var id = "firstPop";
	var url = "url:get?" + thisUrl;
	new dialogBox(title, url,width,height, id);
}
function refreshPopForm(thisUrl,callBackFunction) {
	var url = "url:get?" + thisUrl;
	popRefresh(url,callBackFunction);
}
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
} 

jQuery.fn.sudoku = function()
{
	var element = jQuery(this);
	var inputE = $(element[0]);
	for(var i=0;i<element.length;i++)
	{
		if(element[i].nodeName.toLowerCase() == 'input')
		{
			inputE = $(element[i]);
		}
	}
	var sudoku = inputE.parent().find("#sudoku");
	function sudokuEvent()
	{
		$("a",sudoku).unbind("click");
		$("a",sudoku).click(function(event){
			event.stopPropagation();
			var cl = $(this).attr("class");
			var val = cl.replaceAll("but_","");
			if(val == 'del')
			{
				inputE.val('');
				inputE.focus();
			}
			else if(val == 'close')
			{
				sudoku.hide();
			}
			else if(val == 'dot')
			{
				var cv = inputE.val();
				cv += '.';
				inputE.val(cv);
				inputE.focus();
			}
			else
			{
				var cv = inputE.val();
				if(cv != ""){
					cv += val;
				}else{
					cv = val;
				}
				inputE.val(cv);
				inputE.focus();
			}
			if(inputE.attr("callBackFunction"))
			{
				var cf = inputE.attr("callBackFunction");
				eval(cf).call();
			}
			
		});
	}
	
	$("body").click(function(event){
		event.stopPropagation();
		var target = $(event.target);
		
		if(target.attr("id") && target.attr("id") == inputE.attr("id"))
		{
		}
		else
		{
			sudoku.hide();
		}
	});
	
	element.each(function(event){
		var dom = this;
		if(dom.nodeName.toLowerCase() == 'input')
		{
			$(dom).focusin(function(e){
				$(".jianpan_wrap").hide();
				e.stopPropagation();
				sudoku.show();
			});
		}
		else
		{
			$(dom).click(function(e){
				e.stopPropagation();
				sudoku.toggle();
				inputE.focus();
			});
		}
	});
	
	
//	element.bind("click",function(event){
//		event.stopPropagation();
//		sudoku.toggle();
//	});
	
//	element.each(function(){
//		var e = $(this);
//		if(this.nodeName.toLowerCase() != 'input')
//		{
//			e.click(function(event){
//				event.stopPropagation();
//				sudoku.toggle();
//			});
//		}
//	});
	
	sudokuEvent();
};

jQuery.fn.sudokuShow = function()
{
	var element = jQuery(this);
	var inputE = $(element[0]);
	var sudoku = inputE.parent().parent().find("#sudoku");
	
	function sudokuShowEvent()
	{
		$("a",sudoku).unbind("click");
		$("a",sudoku).bind("click",function(event){
			event.stopPropagation();
			var cl = $(this).attr("class");
			var val = cl.replaceAll("but_","");
			if(val == 'del')
			{
				inputE.val('');
//				inputE.focus();
			}
			else if(val == 'dot')
			{
				var cv = inputE.val();
				inputE.val(cv+".");
//				inputE.focus();
			}
			else
			{
				var cv = inputE.val();
				if(cv != ""){
					cv += val;
				}else{
					cv = val;
				}
				inputE.val(cv);
//				inputE.focus();
			}
		});
	}
	sudokuShowEvent();
};


/**
 * 数学函数
 */
var MathUtil = {
		random : function(){
			return Math.random();
		}
};


jQuery.fn.timeSelect = function()
{
	var monthDays = new Array();
	var element = jQuery(this);
	var inputE = $(element[0]);
	for(var i=0;i<element.length;i++)
	{
		if(element[i].nodeName.toLowerCase() == 'input')
		{
			inputE = $(element[i]);
		}
	}
	var timeSelect = inputE.parent().find("#timeSelect");
	timeSelect.hide();
	function timeEvent()
	{
		
		$("a",timeSelect).click(function(event){
		});
	}
	
	function isLeapYear (Year) {
		if (((Year % 4)==0) && ((Year % 100)!=0) || ((Year % 400)==0)) {
			return (true);
		} else { 
			return (false); 
		}
	}
	
	
	function judgeMonthMaxDay(year,month)
	{
		var maxDay = 31;
		if(month == 4 || month == 6 || month == 9 || month == 11)
		{
			maxDay = 30;
		}
		else if(month == 2)
		{
			if(isLeapYear(year))
			{
				maxDay = 29;
			}
			else
			{
				maxDay = 28;
			}
		}
		return maxDay;
	}


	
	$(".popup_body").click(function(event){
		timeSelect.hide();
	});
	
	element.bind("click",function(event){
		event.stopPropagation();
		timeSelect.toggle();
	});
	
	var d = new Date();
	var year = d.getFullYear();
	var month = (d.getMonth()+1);
	var day = d.getDate();
	var hour = d.getHours();
	var min = d.getMinutes();
	
	//今天，2013年10月31号14:40
	$("#timeSelect_today",timeSelect).text("今天，"+year + "年"+month + "月"+day + "日"+hour + "时"+min + "分");
	$("#timeSelect_year",timeSelect).text(year+"年");
	$("#timeSelect_month",timeSelect).text(month+"月");
	$("#timeSelect_day",timeSelect).text(day+"日");
	$("#timeSelect_hour",timeSelect).text(hour+"时");
	$("#timeSelect_min",timeSelect).text(min+"分");
	
	
	
	$("#timeSelect_year_add",timeSelect).click(function(event){
		event.stopPropagation();
		year ++;
		$("#timeSelect_year",timeSelect).text(year+"年");
		//月份变化的时候判断一下本月的天数是否符合
		var maxDay = judgeMonthMaxDay(year,month);
		if(day > maxDay)
		{
			day = maxDay;
			$("#timeSelect_day",timeSelect).text(day+"日");
		}
	});
	$("#timeSelect_year_reduce",timeSelect).click(function(event){
		event.stopPropagation();
		if(year > 0)
		{
			year --;
			$("#timeSelect_year",timeSelect).text(year+"年");
			//月份变化的时候判断一下本月的天数是否符合
			var maxDay = judgeMonthMaxDay(year,month);
			if(day > maxDay)
			{
				day = maxDay;
				$("#timeSelect_day",timeSelect).text(day+"日");
			}
		}
	});
	
	
	$("#timeSelect_month_add",timeSelect).click(function(event){
		event.stopPropagation();
		if(month < 12)
		{
			month ++;
			$("#timeSelect_month",timeSelect).text(month+"月");
			//月份变化的时候判断一下本月的天数是否符合
			var maxDay = judgeMonthMaxDay(year,month);
			if(day > maxDay)
			{
				day = maxDay;
				$("#timeSelect_day",timeSelect).text(day+"日");
			}
			
		}
	});
	$("#timeSelect_month_reduce",timeSelect).click(function(event){
		event.stopPropagation();
		if(month > 1)
		{
			month --;
			$("#timeSelect_month",timeSelect).text(month+"月");
			//月份变化的时候判断一下本月的天数是否符合
			var maxDay = judgeMonthMaxDay(year,month);
			if(day > maxDay)
			{
				day = maxDay;
				$("#timeSelect_day",timeSelect).text(day+"日");
			}
		}
	});
	
	$("#timeSelect_day_add",timeSelect).click(function(event){
		event.stopPropagation();
		var maxDay = judgeMonthMaxDay(year,month);
		if(day < maxDay)
		{
			day ++;
			$("#timeSelect_day",timeSelect).text(day+"日");
		}
	});
	$("#timeSelect_day_reduce",timeSelect).click(function(event){
		event.stopPropagation();
		if(day > 1)
		{
			day --;
			$("#timeSelect_day",timeSelect).text(day+"日");
		}
	});
	
	$("#timeSelect_hour_add",timeSelect).click(function(event){
		event.stopPropagation();
		if(hour < 23)
		{
			hour ++;
			$("#timeSelect_hour",timeSelect).text(hour+"时");
		}
	});
	$("#timeSelect_hour_reduce",timeSelect).click(function(event){
		event.stopPropagation();
		if(hour > 0)
		{
			hour --;
			$("#timeSelect_hour",timeSelect).text(hour+"时");
		}
	});
	
	$("#timeSelect_min_add",timeSelect).click(function(event){
		event.stopPropagation();
		if(min < 59)
		{
			min ++;
			$("#timeSelect_min",timeSelect).text(min+"分");
		}
	});
	$("#timeSelect_min_reduce",timeSelect).click(function(event){
		event.stopPropagation();
		if(min > 0)
		{
			min --;
			$("#timeSelect_min",timeSelect).text(min+"分");
		}
	});
	
	$("#timeSelect_ok",timeSelect).click(function(event){
		event.stopPropagation();
		var minStr = min + "";
		if(min < 10 && minStr.length == 1)
		{
			min = "0"+min;
		}
		inputE.val(year + "-"+month + "-"+day + " "+hour + ":"+min+ ":00");
		timeSelect.hide();
	});
	
	$("#timeSelect_cancel",timeSelect).click(function(event){
		event.stopPropagation();
		timeSelect.hide();
	});
	timeEvent();
}
/**
 * 删除异步请求的html中的头部
 * @param html
 * @returns
 */
function removeBody(html)
{
	var s = html.substring(html.indexOf("<body>") ,html.length);
	if(s.indexOf("/login") > 0)
	{
//		toastr.error('长时间没有使用，请重新登录！');
		window.location = window.ctx+"/login?relogin=1";
	}
	return s;
}
function removeBodyAnd(html)
{
	html = html.replaceAll("<body>","");
	html = html.replaceAll("</body>","");
	return html;
}
Array.prototype.insertAt = function(index, obj) {
    this.splice(index, 0, obj);
}
Array.prototype.moveTo = function(oldIndex, newIndex) {
	var old = this[oldIndex];
	this.remove(oldIndex);
    this.splice(newIndex, 0, old);
}
Array.prototype.remove = function(index) {
    this.splice(index, 1);
}

Array.prototype.del=function(index){
    if(isNaN(index)||index>=this.length){
        return false;
    }
    for(var i=0,n=0;i<this.length;i++){
        if(this[i]!=this[index]){
            this[n++]=this[i];
        }
    }
    this.length-=1;
};
var ajaxUtil = {
		getUrlContent : function(url,innerId,callBackFunction,errorFunction){
			 $.ajax({
				    type:"get",
				    url:url,
				    data:null,
				    cache:false,
				    async:true,
				    success:function(html){
				    	html = removeBody(html);
				    	html = removeBodyAnd(html);
				    	$("#"+innerId).html(html);
				    	if("undefined" == typeof callBackFunction)
				    	{
				    		
				    	}
				    	else
				    	{
				    		if(callBackFunction)
				    		{
				    			callBackFunction.call();
				    		}
				    	}
				    },
					error:function(){
						if(errorFunction)
			    		{
			    			errorFunction.call();
			    		}
					}
				  });
		},
		submitUrl : function(url,callBackFunction,errorFunction){
			 $.ajax({
				    type:"get",
				    url:url,
				    data:null,
				    cache:false,
				    async:true,
				    dataType: "json",
				    success:function(data){
				    	if("undefined" == typeof callBackFunction)
				    	{
				    		
				    	}
				    	else
				    	{
				    		if(callBackFunction)
				    		{
				    			callBackFunction.call(data);
				    		}
				    	}
				    },
					error:function(){
						if(errorFunction)
			    		{
			    			errorFunction.call();
			    		}
					}
				  });
		}
};

/**
 * 判断按钮权限，根据id
 * @param id
 * @returns {Boolean}
 */
function getPermission(id)
{
	var obj = $("#permission_"+id).val();
	if(obj)
	{
		return true;
	}
	else
	{
		//toastr.warning("没有权限!请联系管理员!");
		return true;
	}
}
function getPermissionNoToast(id)
{
	var obj = $("#permission_"+id).val();
	if(obj)
	{
		return true;
	}
	else
	{
		return true;
	}
	
}
function getScreenSizeUrl(url,type)
{
	var rurl = window.ctx+url;
	if(url.indexOf("?") >= 0)
	{
		rurl = window.ctx+url + "&pagzSize="+PageSizeUtil.getSizeByType(type);
	}
	else
	{
		rurl = window.ctx+url + "?pagzSize="+PageSizeUtil.getSizeByType(type);
	}
	rurl = rurl.replaceAll("&&","&");
	return rurl;
}
var cursorPosition = {
	    get: function (textarea) {
	        var rangeData = {text: "", start: 0, end: 0 };
	     
	        if (textarea.setSelectionRange) { // W3C    
	            textarea.focus();
	            rangeData.start= textarea.selectionStart;
	            rangeData.end = textarea.selectionEnd;
	            rangeData.text = (rangeData.start != rangeData.end) ? textarea.value.substring(rangeData.start, rangeData.end): "";
	        } else if (document.selection) { // IE
	            textarea.focus();
	            var i,
	                oS = document.selection.createRange(),
	                oR = document.body.createTextRange();
	            oR.moveToElementText(textarea);
	             
	            rangeData.text = oS.text;
	            rangeData.bookmark = oS.getBookmark();
	             
	            for (i = 0; oR.compareEndPoints('StartToStart', oS) < 0 && oS.moveStart("character", -1) !== 0; i ++) {
	 
	                if (textarea.value.charAt(i) == '\r' ) {
	                    i ++;
	                }
	            }
	            rangeData.start = i;
	            rangeData.end = rangeData.text.length + rangeData.start;
	        }
	         
	        return rangeData;
	    },
	     
	    set: function (textarea, rangeData) {
	        var oR, start, end;
	        if(!rangeData) {
	            alert("You must get cursor position first.")
	        }
	        textarea.focus();
	        if (textarea.setSelectionRange) { // W3C
	            textarea.setSelectionRange(rangeData.start, rangeData.end);
	        } else if (textarea.createTextRange) { // IE
	            oR = textarea.createTextRange();
	            if(textarea.value.length === rangeData.start) {
	                oR.collapse(false);
	                oR.select();
	            } else {
	                oR.moveToBookmark(rangeData.bookmark);
	                oR.select();
	            }
	        }
	    },
	 
	    add: function (textarea, rangeData, text) {
	        var oValue, nValue, oR, sR, nStart, nEnd, st;
	        this.set(textarea, rangeData);
	         
	        if (textarea.setSelectionRange) { // W3C
	            oValue = textarea.value;
	            nValue = oValue.substring(0, rangeData.start) + text + oValue.substring(rangeData.end);
	            nStart = nEnd = rangeData.start + text.length;
	            st = textarea.scrollTop;
	            textarea.value = nValue;
	            if(textarea.scrollTop != st) {
	                textarea.scrollTop = st;
	            }
	            textarea.setSelectionRange(nStart, nEnd);
	        } else if (textarea.createTextRange) { // IE
	            sR = document.selection.createRange();
	            sR.text = text;
	            sR.setEndPoint('StartToEnd', sR);
	            sR.select();
	        }
	    }
	}
//焦点不在文本框中时，按下BACKSPACE页面不回退
document.onkeydown =function(){
    if(event.keyCode==8){
        var elem = event.srcElement;
        var name = elem.nodeName;
          
        if(name!='INPUT' && name!='TEXTAREA'){
            event.returnValue = false ;
            return ;
        }
        var type_e = elem.type.toUpperCase();
        if(name=='INPUT' && (type_e!='TEXT' && type_e!='TEXTAREA' && type_e!='PASSWORD' && type_e!='FILE')){
            event.returnValue = false ;
            return ;
        }
        if(name=='INPUT' && (elem.readOnly==true || elem.disabled ==true)){
            event.returnValue = false ;
            return ;
        }
    }
}

/**
 * 以下两个方法是让输入框获得焦点，并且不选择文字的代码,setFocus,myFocus
 */
function myFocus(sel,start,end){
	if (sel.setSelectionRange){
		sel.focus();
		sel.setSelectionRange(start,end);
	}else if (sel.createTextRange) {
		var range = sel.createTextRange();
		range.collapse(true);
		range.moveEnd('character', end);
		range.moveStart('character', start);
		range.select();
	}
}

function setFocus(sel) {
	length = sel.value.length;
	myFocus(sel, length, length);
}
	
/**
 * 同步锁，保存方法调用
 */
function SyncLock(buttonId,loadingClass){
	this.lock = false;
	this.button = $("#"+buttonId);
	this.className = this.button.attr("class");
	this.loadingClassName = loadingClass ? loadingClass : "small-but-loading";
	this.text = this.button.text();
}

SyncLock.prototype.Start= function (){
	if(!this.lock)
	{
		this.lock = true;
		//提示正在提交中...
		this.button.removeClass(this.className);
		this.button.addClass(this.loadingClassName);
		this.button.text("");
		return true;
	}
	if(this.lock)
	{
		this.lock = true;
		return false;
	}
}
SyncLock.prototype.End= function (){
	this.lock = false;
	this.button.removeClass(this.loadingClassName);
	this.button.addClass(this.className);
	this.button.text(this.text);
}

var statusCurrentPage = 0;
var domPage = function(parent,children,_itemPerPage,_currentPage,_insertHtml)
{
	var itemPerPage = _itemPerPage;
	var currentPage = _currentPage == "last" ? 0 : statusCurrentPage;
	var toLastPage = false;
	var totalSize = 0;
	var toLastPage = false;
	
	function getTotal()
	{
		parent.children(children).each(function(i){
			var tr = $(this);
			var seq = i;
			if(tr.attr("id") != 'blank')
			{
				totalSize++;
			}
		});
		var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
		if(totalPage <= currentPage)
		{
			currentPage--;
		}
	}
	
	
	function initPage()
	{
		getTotal();
		if(toLastPage)
		{
			goLastPage();
			toLastPage = false;
		}
		var start = currentPage * itemPerPage;
		var end = start + itemPerPage;
		showTrs(start,end);
		
		$("#billPageBack").click(function(){
			var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
			if(currentPage > 0)
			{
				currentPage --;
				var start = currentPage * itemPerPage;
				var end = start + itemPerPage;
				showTrs(start,end);
				statusCurrentPage = currentPage;
			}
			
		});
		
		$("#billPageGo").click(function(){
			var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
			if(currentPage < totalPage-1)
			{
				currentPage ++;
				var start = currentPage * itemPerPage;
				var end = start + itemPerPage;
				showTrs(start,end);
				statusCurrentPage = currentPage;
			}
		});
	}

	function goLastPage() 
	{
		var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
		currentPage = totalPage -1;
		statusCurrentPage = currentPage;
	}

	function refreshPage(currentPage)
	{
		var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
		$("#billPageBack").removeClass();
		$("#billPageGo").removeClass();
		if(currentPage <= 0)
		{
			$("#billPageBack").addClass('but_qx_left_no');
		}
		else
		{
			$("#billPageBack").addClass('but_qx_left');
		}
		if(currentPage == totalPage-1 || totalPage == 0)
		{
			$("#billPageGo").addClass('but_qx_right_no');
		}
		else
		{
			$("#billPageGo").addClass('but_qx_right');
		}
	}

	function showTrs(start,end)
	{
		var showNum = 0;
		parent.find(children).each(function(i){
			var tr = $(this);
			var seq = i;
			if(tr.attr("id"))
			{
				tr.hide();
				if(seq >= start && seq < end)
				{
					tr.show();
					showNum++;
					if(tr.attr("id") != 'blank')
					{
						tr.find("#seq").text(seq+1);
					}
				}
			}
		});
		if(showNum < itemPerPage)
		{
			for(var i=0;i<itemPerPage-showNum;i++)
			{
				var billStr = "<tr id=\"blank\"><td id=\"seq\">&nbsp;</td><td id=\"dishesName\"></td><td id=\"price\"></td><td id=\"orderNum\"></td><td id=\"total\"></td></tr>";
				parent.append(billStr);
			}
		}
		
		refreshPage(currentPage);
	}
	if(_insertHtml)
	{
		parent.children(children).each(function(i){
			var tr = $(this);
			var seq = i;
			if(tr.attr("id") == 'blank')
			{
				tr.remove();
			}
		});
		parent.append(_insertHtml);
		toLastPage = true;
	}
	
	if("last" == _currentPage)
	{
		toLastPage = true;
	}
	
	initPage();
}

$.extend({
    tipsBox: function(options) {
        options = $.extend({
            obj: null,  //jq对象，要在那个html标签上显示
            str: "<b style='font-family:Microsoft YaHei;'>+1</b>",  //字符串，要显示的内容;也可以传一段html，如: "<b style='font-family:Microsoft YaHei;'>+1</b>"
            startSize: "30px",  //动画开始的文字大小
            endSize: "40px",    //动画结束的文字大小
            interval: 600,  //动画时间间隔
            color: "#FF0000",    //文字颜色
            callback: function() {}    //回调函数
        }, options);
        $("body").append("<span class='num'>"+ options.str +"</span>");
        var box = $(".num");
        var left = options.obj.offset().left + 20;
        var top = options.obj.offset().top - 30;
        box.css({
            "position": "absolute",
            "left": left + "px",
            "top": top + "px",
            "z-index": 9999,
            "font-size": options.startSize,
            "line-height": options.endSize,
            "color": options.color
        });
        box.animate({
            "font-size": options.endSize,
            "opacity": "0",
            "top": top - parseInt(options.endSize) + "px"
        }, options.interval , function() {
            box.remove();
            options.callback();
        });
    }
});

//播放声音
function playSound(){
	var srcMp3 = window.ctx + "/static/media/sent.mp3";
	var borswer = window.navigator.userAgent.toLowerCase();
	if(borswer.indexOf("ie") >= 0){
		//IE内核浏览器
		var strEmbed = "<embed name='embedPlay' src='"+ srcMp3 +"' autostart='true' hidden='true' loop='false'></embed>";
		if($("body").find("embed").length <= 0){
			$("body").append(strEmbed);
		}
		var embed = document.embedPlay;
		//浏览器不支持 audion，则使用 embed 播放
		embed.volume = 100;
		embed.play();
	}else{
		//非IE内核浏览器
		var strAudio = "<audio id='audioPlay' src='"+ srcMp3 +"' hidden='true'>";
		if($("body").find("audio").length <= 0){
			$("body").append(strAudio);
		}
		var audio = document.getElementById("audioPlay");
		//浏览器支持 audion
		audio.play();
  }
}


//禁用鼠标右键
document.oncontextmenu=function(){return false;}
document.onselectstart=function(){return false;}