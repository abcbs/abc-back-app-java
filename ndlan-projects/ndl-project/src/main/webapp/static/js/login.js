window.ctx = "/";
var url = window.location.href;
if(url.indexOf('canyin-frontdesk') > 0)
{
	window.ctx = "/canyin-frontdesk";
}

$(document).ready(function(){
	$("#uname, #password").bindkeyboard();
	ad();
	
	ProgressbarUtil.hideG2();
});

function judgeIsFull()
{
	var wh = $(window).height();
	if(wh == 0)
	{
		wh = $(document).height();
	}
	var sh = window.screen.height;
	if(wh < (sh - 10))
	{
		return false;
	}
	else
	{
		return true;
	}
	return false;
}

function ad()
{
	var width = $(window).width();
	var height = $(window).height();
	if(height == 0)
	{
		height = $(document).height();
	}
	if(width == 0)
	{
		width = $(document).width();
	}
	
	var lHeight = (height-238-100-266)/2;
	$(".login_wrap").css({top:lHeight+"px",left:((width-529)/2)+"px"});
	$(".login_jianpan").css({top:(lHeight+337)+"px",left:((width-771)/2)+"px"});
}

$(window).resize(function() {
	ad();
});


function login()
{
	ProgressbarUtil.showG2();
	 var uname = $('#uname').val();
	 var password = $('#password').val();
	 var restId = $('#restId').val();
	 if(!restId)
	 {
		 restId = "";
		 $('#username').val(uname);
	 }
	 else
	 {
		 $('#username').val(uname + "," + restId);
	 }
     setTimeout(function(){
    	 $('#loginForm').submit();
	},200);
}
//禁用鼠标右键
document.oncontextmenu=function(){return false;}