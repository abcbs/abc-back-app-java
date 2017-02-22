$(document).ready(function(){
	$("#popSave").bind("click",saveNotes);
	$("#popSaveForm").CanYinValidate([
	    {
		    id:"notes",
		    name:"备注",
		    require:true,
		    length:[1,1024]
		}
	  ]);
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveNotes();
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

function saveNotes()
{
	//if($("#popSaveForm").CanYinValid())
	//{
		var url = $("#popSaveForm").attr("action");
		jQuery.ajax({
			url: url,
			data: $('#popSaveForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				closebox();
				refreshPayPage();
			}
		});
	//}
		
	
}