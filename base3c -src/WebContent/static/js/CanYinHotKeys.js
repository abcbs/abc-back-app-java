function CanYinHotKeys(){};

var CanYinHotKeysArray = new Array();
var CanYinHotKeysArray_currentIndex = 0;

CanYinHotKeys.prototype = {
  body:null,  
  options:null,
  hotkeys:null,
  index:null,
  isExInputFocus:true,
  setHotKey : function(currentIndex)
  {
	  CanYinHotKeysArray_currentIndex = currentIndex;
	  var ihotkeys = this.hotkeys;
	  this.body.unbind("keydown");
	  this.body.keydown(function(evt) {
		
	   if(evt.shiftKey && evt.keyCode == 13)
		{
		   ihotkeys.keyCodeFunction("shift_enter",evt);
		   return false;
		}
		else if(evt.keyCode == 13)//ok
		{
			ihotkeys.keyCodeFunction("enter",evt);
			return false;
		}
		if(evt.keyCode == 27)//ESC
		{
			ihotkeys.keyCodeFunction("esc",evt);
		}
		//0-9
		if(evt.keyCode >= 48 && evt.keyCode <= 57)
		{
			ihotkeys.keyCodeFunction(evt.keyCode-48,evt);
		}
		//小键盘0-9
		if(evt.keyCode >= 96 && evt.keyCode <= 109)
		{
			ihotkeys.keyCodeFunction(evt.keyCode-96,evt);
		}
		if(evt.keyCode == 37)//left
		{
			ihotkeys.keyCodeFunction("left",evt);
		}
		if(evt.keyCode == 38)//up
		{
			ihotkeys.keyCodeFunction("up",evt);
		}
		if(evt.keyCode == 39)//right
		{
			ihotkeys.keyCodeFunction("right",evt);
		}
		if(evt.keyCode == 40)//down
		{
			ihotkeys.keyCodeFunction("down",evt);
		}
		if(evt.shiftKey && evt.keyCode == 9)//down
		{
			ihotkeys.keyCodeFunction("shift_tab",evt);
		}
		else if(evt.keyCode == 9)//down
		{
			ihotkeys.keyCodeFunction("tab",evt);
		}
		
	});
	  if(this.isExInputFocus)
	 {
		  ihotkeys.exInputFocus();
	 }
  }
	,
	keyCodeFunction : function(keyCode,evt)
	{
		for(var i=0;i<this.options.length;i++)
		{
			var option = this.options[i];
			if(keyCode == option.keyCode)
			{
				return option.callBackFunction.call(this,evt);
			}
		}
		return this;
	}
	,
	exInputFocus : function()
	{
		if(this.index == 0)
		{
			var ibody = this.body;
			this.body.find("input").focusin(function(){
				var inp = $(this);
				ibody.unbind("keydown");
			}).focusout(function(){
				CanYinHotKeysGetLastHotKeys(0);
			});
		}
		return this;
	}
};

CanYinHotKeys.prototype.init = function(body,options,index)
{
	this.body = body;
	this.options = options;
	this.hotkeys = this;
	CanYinHotKeysArray[index]=this; 
	this.index = index;
	this.setHotKey(index);
	return this;
};

CanYinHotKeys.prototype.nullFunction = function()
{
	return this;
};


var CanYinHotKeysGetLastHotKeys = function(index)
{
	var hot = CanYinHotKeysArray[index];
	hot.setHotKey(index);
};

/**
 * 查找上一个快捷键存储
 */
var CanYinHotKeysGetLastHotKey = function()
{
	var lastIndex =CanYinHotKeysArray_currentIndex > 1 ? CanYinHotKeysArray_currentIndex-1 : 0;
	var hot = CanYinHotKeysArray[lastIndex];
	if(!hot)
	{
		lastIndex--;
		hot = CanYinHotKeysArray[lastIndex];
	}
	for(var i=0;i<(CanYinHotKeysArray.length - lastIndex-1);i++)
	{
		CanYinHotKeysArray.pop();
	}
	hot.setHotKey(lastIndex);
};

