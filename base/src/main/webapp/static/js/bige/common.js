window.ctx = "/";
var isDaoshu = false;
var stopSecond = 60;
var url = window.location.href;
if (url.indexOf('canyin-frontdesk') > 0) {
	window.ctx = "/canyin-frontdesk";
}

$(document).ready(function() {
	pageInit();
});
function pageInit()
{
	display();
	clearDaoshu();
	daoshu();
	bodyMouseAction();
	$("body").disableSelection();
}

function bodyMouseAction(){  
	$("body").bind("mouseover",function(){
		seconds = stopSecond;
	});
	$("body").bind("click",function(){
		seconds = stopSecond;
	});
	$("body").bind("keydown",function(){
		seconds = stopSecond;
	});
}


function changeBody(url)
{
	$.ajax({
	    type:"get",
	    url:url,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(html){
	    	$("body").html(html);
	    	pageInit();
	    },
		error:function(){
		}
	  });
}


function formSubmit(id)
{
	var form = $("#"+id);
	var url = form.attr("action");
	jQuery.ajax({
		url: url,
		data: form.serialize(),
		type: "POST",
		dataType: "html",
		beforeSend: function()
		{  
			
		},
		success: function(data)
		{
			$("body").html(data);
		}
	});
}


var seconds = stopSecond;
var secondsTimeout = null;
function daoshu()
{
	if(seconds < 0)
	{
		seconds = stopSecond;
		window.location=window.ctx+'/self/bige/exit';
	}
	if(isDaoshu)
	{
//		$(".G2_logo").text(seconds+"秒");
		seconds--;
		secondsTimeout = setTimeout("daoshu()", 1000);
	}
}
function clearDaoshu()
{
	clearInterval(secondsTimeout);
}

function display() {
	var Digital = new Date();
	var fullYear = Digital.getFullYear();
	var month = Digital.getMonth()+1;       //获取当前月份(0-11,0代表1月)
	var day = Digital.getDate();        //获取当前日(1-31)
	var hours = Digital.getHours();
	var minutes = Digital.getMinutes();
	var seconds = Digital.getSeconds();
	// 以上两个if让小时显示限制在1~12之间
	if (minutes <= 9) {
		minutes = "0" + minutes;
	}
	if (seconds <= 9) {
		seconds = "0" + seconds;
	}
	// 改变字体
	var amOrPm = "AM";
	if(hours > 12)
	{
		hours = hours - 12;
		amOrPm = "PM";
	}
	if (hours <= 9) {
		hours = "0" + hours;
	}
	
	myclock =  amOrPm + " " + hours + ":" + minutes + "";
	$(".time").text(myclock);
	
	//比较当前时间和自助餐时间
	setTimeout("display()", 1000);
}

var conpareTimeOut = null;
function conpareTime()
{
	var now = $(".time").text();
	var nowHMS = now.substring(11,now.length);
	$("dl[id='ziZhuTime']").each(function(){
		var div = $(this);
		var buffetStartTime = div.attr("buffetStartTime")+":00";
		var buffetEndTime = div.attr("buffetEndTime")+":00";
		var name = div.find("span").text();
		if(compare_hms(toDate(nowHMS),toDate(buffetStartTime)) == 1 &&
				compare_hms(toDate(nowHMS),toDate(buffetEndTime)) == -1)
		{
			div.addClass("text_yellow");
		}
	});
	clearInterval(conpareTimeOut);
	conpareTimeOut = setTimeout("conpareTime()", 10000);
}

function cleatConpareTime()
{
	clearInterval(conpareTimeOut);
}


function toDate(str)
{
	var time=new Date("2000-01-01 "+str);
	return time;
}
function compare_hms(a,b){
    var i= a.getHours()*60*60+ a.getMinutes()*60+ a.getSeconds();
    var n= b.getHours()*60*60+ b.getMinutes()*60+ b.getSeconds();
    if(i>n){
    	return 1;
    }else if(i<n){
    	return -1;
    }else{
    	return 0;
    }
}


function indexBuffetClick(ctx,cbsId,dl)
{
	var div = $(dl);
	var now = $(".time").text();
	var nowHMS = now.substring(10,now.length);
	var buffetStartTime = div.attr("buffetStartTime")+":00";
	var buffetEndTime = div.attr("buffetEndTime")+":00";
	var name = div.find("span").text();
	if(compare_hms(toDate(nowHMS),toDate(buffetStartTime)) == 1 &&
			compare_hms(toDate(nowHMS),toDate(buffetEndTime)) == -1)
	{
		changeBody(ctx+'/self/zizhu/zizhuOrder?cbsId='+cbsId);
	}
	else
	{
		toastr.warning("时间未到，请稍后...");
	}
}

function checkMemberCardInfo(a) {
	var form = $(a).parents("#paySubmitForm");
	var cardNo = $("#cardNo").val();
	var cardPassword = $("#cardPassword").val();
	$.ajax({
		type : "get",
		url : window.ctx + '/self/terminal/ajax/getMemberCardInfo?cardNo='
				+ cardNo + '&cardPassword=' + cardPassword,
		data : null,
		cache : false,
		async : true,
		success : function(data) {
			if (data.statusCode == '200') {
				formSubmit("paySubmitForm");
			} else {
				toastr.error("卡号活密码错误!");
			}
		},
		error : function() {
		}
	});
}

function checBankCardInfo(a) {
	formSubmit("paySubmitForm");
}
