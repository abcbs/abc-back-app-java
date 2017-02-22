$(document).ready(function(){
	$("#queding").bind("click",queding);
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			queding();
		}
	},
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			closebox();
		}
	}
	],1);
	
});

function queding(){
	closebox();
	refreshPayPage();
}