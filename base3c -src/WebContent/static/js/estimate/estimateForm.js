$(document).ready(function(){
	$("#estimateNum").sudokuShow();
	$("#popSave").bind("click",saveEstimate);
	$("#popSaveForm").CanYinValidate([
  	    {
  		    id:"estimateNum",
  		    name:"沽清数量",
  		    require:false,
  		    type:'number'
  		}
  	  ]);
	
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveEstimate();
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

function saveEstimate()
{
	$("#popSave").unbind("click",saveEstimate);
	if($("#popSaveForm").CanYinValid())
	{
		var estimateNum = $("#estimateNum").val();
		if(estimateNum < 0){
			toastr.error("沽清数量不能为负数");
			return;
		}
		var url = $("#popSaveForm").attr("action");
		jQuery.ajax({
			url: url+'?rid='+MathUtil.random(),
			data: $('#popSaveForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				closebox();
				refreshPage();
			}
		});
	}
	return false;
}