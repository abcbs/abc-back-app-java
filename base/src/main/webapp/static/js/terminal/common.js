window.ctx = "/";
var url = window.location.href;
if(url.indexOf('canyin-frontdesk') > 0){
	window.ctx = "/canyin-frontdesk";
}
var second = 60;
var secondsTimeout = null;
$(document).ready(function(){
	secondsTimeout = setInterval(timer,"1000");
});

function timer(){
	nowTime();
	var str = $("#daojishi").text();
	if(str != null && str != ''){
		second--;
		$("#daojishi").html(second+"秒");
		if(second <= 0){
			goIndex();
		}
	}
}

function daoshu()
{
	second = 60;
	secondsTimeout = setInterval(timer,"1000");
}

function clearDaoshu()
{
	clearInterval(secondsTimeout);
}

//系统当前时间
function nowTime(){
	var date = new Date();
	var vYear = date.getFullYear();
	var vMon = date.getMonth()+1;
	var vDay = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes(); 
	var se = date.getSeconds(); 
	if(vMon < 10){
		vMon = "0" + vMon;
	}
	if(vDay < 10){
		vDay = "0" + vDay;
	}
	if(h < 10){
		h = "0" + h;
	}
	if(m < 10){
		m = "0" + m;
	}
	if(se < 10){
		se = "0" + se;
	}
	$("#time").html(vYear+"年"+vMon+"月"+vDay+"日    "+h+":"+m+":"+se);
}


/**
 * 重新点单
 */
function reOrder() {
	window.location = window.ctx + '/self/terminal/list';
}

function goIndex() {
	window.location = window.ctx + '/self/terminal/index';
}

var isCanGo = true;
function goAdmin()
{
	if(isCanGo)
	{
		isCanGo = false;
		setTimeout("afterTen()", 10000);
		$.ajax({
		    type:"get",
		    url:window.ctx+"/self/bige/ajax/goAdmin",
		    data:null,
		    cache:false,
		    async:true,
		    success:function(data){
		    	if(data.statusCode == '200')
		    	{
//		    		window.location = window.ctx+'/login/urlLogin?u=admin&p=admin';
		    	}
		    },
			error:function(){
			}
		  });
	}
}

function afterTen()
{
	isCanGo = true;
}
