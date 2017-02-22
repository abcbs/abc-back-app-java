$(document).ready(function(){
	$("#money").sudokuShow();
	$("#popSave").bind("click",saveDrawBill);
	
	
	$("#popSaveForm").CanYinValidate([
	    {
		    id:"money",
		    name:"金额",
		    require:false,
		    type:'numberThanZero'
		}
	  ]);
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveDrawBill();
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

function saveDrawBill()
{
	if($("#popSaveForm").CanYinValid())
	{
		$("#popSaveForm").submit();
	}
}
