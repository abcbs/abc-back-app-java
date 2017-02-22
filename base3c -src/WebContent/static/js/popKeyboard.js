function createPopKeyBoard()
{
	var permission_keyboard = $("#permission_keyboard");
	if(permission_keyboard.attr("id"))
	{
		return permission_keyboard;
	}
	var keyHtml = "<div class=\"login_jianpan\" id=\"permission_keyboard\" style=\"display: none;\">"+
		"<a href=\"#\" id=\"49\" class=\"shuzi ml_7\" value=\"1\">1</a>"+
		"<a href=\"#\" id=\"50\" class=\"shuzi\" value=\"2\">2</a>"+
		"<a href=\"#\" id=\"51\" class=\"shuzi\" value=\"3\">3</a>"+
		"<a href=\"#\" id=\"52\" class=\"shuzi\" value=\"4\">4</a>"+
		"<a href=\"#\" id=\"53\" class=\"shuzi\" value=\"5\">5</a>"+
		"<a href=\"#\" id=\"54\" class=\"shuzi\" value=\"6\">6</a>"+
		"<a href=\"#\" id=\"55\" class=\"shuzi\" value=\"7\">7</a>"+
		"<a href=\"#\" id=\"56\" class=\"shuzi\" value=\"8\">8</a>"+
		"<a href=\"#\" id=\"57\" class=\"shuzi\" value=\"9\">9</a>"+
		"<a href=\"#\" id=\"48\" class=\"shuzi\" value=\"10\">0</a>"+
		"<a href=\"#\" id=\"81\" class=\"shuzi ml_7 zimu\" value=\"Q\">q</a>"+
		"<a href=\"#\" id=\"87\" class=\"shuzi zimu\" value=\"W\">w</a>"+
		"<a href=\"#\" id=\"69\" class=\"shuzi zimu\" value=\"E\">e</a>"+
		"<a href=\"#\" id=\"82\" class=\"shuzi zimu\" value=\"R\">r</a>"+
		"<a href=\"#\" id=\"84\" class=\"shuzi zimu\" value=\"T\">t</a>"+
		"<a href=\"#\" id=\"89\" class=\"shuzi zimu\" value=\"Y\">y</a>"+
		"<a href=\"#\" id=\"85\" class=\"shuzi zimu\" value=\"U\">u</a>"+
		"<a href=\"#\" id=\"73\" class=\"shuzi zimu\" value=\"I\">i</a>"+
		"<a href=\"#\" id=\"79\" class=\"shuzi zimu\" value=\"O\">o</a>"+
		"<a href=\"#\" id=\"80\" class=\"shuzi zimu\" value=\"P\">p</a>"+
		"<a href=\"#\" id=\"65\" class=\"shuzi ml_7 zimu\" value=\"A\">a</a>"+
		"<a href=\"#\" id=\"83\" class=\"shuzi zimu\" value=\"S\">s</a>"+
		"<a href=\"#\" id=\"68\" class=\"shuzi zimu\" value=\"D\">d</a>"+
		"<a href=\"#\" id=\"70\" class=\"shuzi zimu\" value=\"F\">f</a>"+
		"<a href=\"#\" id=\"71\" class=\"shuzi zimu\" value=\"G\">g</a>"+
		"<a href=\"#\" id=\"72\" class=\"shuzi zimu\" value=\"H\">h</a>"+
		"<a href=\"#\" id=\"74\" class=\"shuzi zimu\" value=\"J\">j</a>"+
		"<a href=\"#\" id=\"75\" class=\"shuzi zimu\" value=\"K\">k</a>"+
		"<a href=\"#\" id=\"76\" class=\"shuzi zimu\" value=\"L\">l</a>"+
		"<a href=\"#\" id=\"8\" class=\"shuzi jp_delete\" value=\"del\"><img src=\""+window.ctx+"/static/images/images_p/delete.png\"></a>"+
		"<a href=\"#\" class=\"shuzi jp_daxie ml_8\" value=\"shift\"><img src=\""+window.ctx+"/static/images/images_p/daxiao.png\"></a>"+
		"<a href=\"#\" id=\"90\" class=\"shuzi zimu\" value=\"Z\">z</a>"+
		"<a href=\"#\" id=\"88\" class=\"shuzi zimu\" value=\"X\">x</a>"+
		"<a href=\"#\" id=\"67\" class=\"shuzi zimu\" value=\"C\">c</a>"+
		"<a href=\"#\" id=\"86\" class=\"shuzi zimu\" value=\"V\">v</a>"+
		"<a href=\"#\" id=\"66\" class=\"shuzi zimu\" value=\"B\">b</a>"+
		"<a href=\"#\" id=\"78\" class=\"shuzi zimu\" value=\"N\">n</a>"+
		"<a href=\"#\" id=\"77\" class=\"shuzi zimu\" value=\"M\">m</a>"+
		"<a href=\"#\" class=\"shuzi jp_queding\" value=\"confirm\">确认</a>"+
	"</div>";
	permission_keyboard = $(keyHtml).appendTo("body");
	return permission_keyboard;
}

function popKeyBoardConfirm()
{
	var m=createPopKeyBoard();
	m.hide();
}

var currentPopKeyElement = null;
function showPopKeyBoard(e,inputId)
{
	e.stopPropagation();
	var m=createPopKeyBoard();
	
	m.css({
		position: "absolute"
	});
	m.show();
	var l = e.pageX + 2,
	t = e.pageY+25,
	p={
		wh:$(window).height(),
		ww:$(window).width(),
		mh:m.height(),
		mw:m.width()
	}
	t=(t+p.mh)>=p.wh?(t-=p.mh):t;//当菜单超出窗口边界时处理
	l=($(window).width()-732)/2;
	m.css({zIndex:999998, left:l, top:(t-1)});
	
//	$("body").click(function(event){
//		m.hide();
//	});
//	
	
	//焦点input
	var finput = $("#"+inputId);
	currentPopKeyElement = finput;
	currentPopKeyElement.focus();
	
	var board = $("#keyboard");
	$("a",m).unbind("click");
	$("a",m).bind("click",function(evt){
		evt.stopPropagation();
		var key = $(this);
		var keyValue = key.attr("value");
		var textValue = key.text();
		var ev = currentPopKeyElement.val();
		if(keyValue == 'shift')
		{
			if(key.hasClass("jt_daxie"))
			{
				key.removeClass("jt_daxie");
			}
			else
			{
				key.addClass("jt_daxie");
			}
			
			//变大写
			$("a",m).each(function(){
				var ea = $(this);
				
				if(ea.hasClass("zimu"))
				{
					var v1 = ea.attr("value");
					var v2 = ea.text();
					ea.attr("value",v2);
					ea.text(v1);
				}
			});
			return;
		}
		if(keyValue == 'del')
		{
			ev = ev.substring(0,ev.length-1);
			currentPopKeyElement[0].value=ev;
			return;
		}
		if(keyValue == 'confirm')
		{
			popKeyBoardConfirm();
			return;
		}
		
		ev += textValue;
		currentPopKeyElement[0].value=ev;
	});
}