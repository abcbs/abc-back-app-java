var currentKeyElement = null;
var keyElementArray = null;
jQuery.fn.bindkeyboard = function()
{
	var element = jQuery(this);
	currentKeyElement = $(element[0]);
	keyElementArray = element;
	element.each(function() {
		$(this).focusin(function(){
			currentKeyElement = $(this);
		});
	});
	element.keyboard();
};


jQuery.fn.keyboard = function()
{
	var element = jQuery(this);
	
	var board = $("#keyboard");
	
	$("a",board).bind("click",function(evt){
		var key = $(this);
		var keyValue = key.attr("value");
		var textValue = key.text();
		var ev = currentKeyElement.val();
		if(keyValue == 'shift')
		{
			if(key.hasClass("jp_daxie"))
			{
				key.removeClass("jp_daxie");
				key.addClass("jp_daxie_on");
			}
			else
			{
				key.removeClass("jp_daxie_on");
				key.addClass("jp_daxie");
			}
			
			//变大写
			$("a",board).each(function(){
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
			currentKeyElement[0].value=ev;
			return;
		}
		if(keyValue == 'ok')
		{
			login();
			return;
		}
		if(keyValue == 'dot')
		{
			keyValue = ".";
		}
		ev += textValue;
		currentKeyElement[0].value=ev;
		
	});
	
	element.keydown(function(evt) {
		//a-z
		if(evt.keyCode >= 65 && evt.keyCode <= 90)
		{
			
		}
		//0-9
		if(evt.keyCode >= 48 && evt.keyCode <= 57)
		{
			
		}
		
		//del
		if(evt.keyCode == 8)
		{
			
		}
		//ok
		if(evt.keyCode == 13)
		{
//			$('#loginForm').submit();
		}
		//dot
		if(evt.keyCode == 190)
		{
			
		}
		//exc hide
		if(evt.keyCode == 27)
		{
			
		}
		
		if(evt.keyCode == 13 || evt.keyCode == 27)
		{
			if(!board.find("#"+evt.keyCode).hasClass("big_on"))
			{
				board.find("#"+evt.keyCode).addClass("big_on");
			}
		}
		else
		{
			if(!board.find("#"+evt.keyCode).hasClass("on"))
			{
				board.find("#"+evt.keyCode).addClass("on");
			}
		}
	}).keyup(function(evt) {
		//a-z
		if(evt.keyCode >= 65 && evt.keyCode <= 90)
		{
			
		}
		//ok
		if(evt.keyCode == 13)
		{
			login();
		}
		if(evt.keyCode == 13 || evt.keyCode == 27)
		{
			board.find("#"+evt.keyCode).removeClass("big_on");
		}
		else
		{
			board.find("#"+evt.keyCode).removeClass("on");
		}
		
	});
	
};
