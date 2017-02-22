$(document).ready(function(){
	$("#popSelect").bind("click",popSelected);
	
	
	$("a","#avoidArea").click(function(){
		if(!$(this).hasClass("green_box_on"))
		{
			$(this).addClass("green_box_on");
		}
		else
		{
			$(this).removeClass("green_box_on");
		}
	});
	
	$("a","#tasteArea").click(function(){
		if(!$(this).hasClass("green_box_on"))
		{
			$(this).addClass("green_box_on");
		}
		else
		{
			$(this).removeClass("green_box_on");
		}
	});
	
	$("a","#pungentArea").click(function(){
		if(!$(this).hasClass("green_box_on"))
		{
			$("a","#pungentArea").removeClass("green_box_on");
			$(this).addClass("green_box_on");
		}
		else
		{
			$(this).removeClass("green_box_on");
		}
	});
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			popSelected();
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


function popSelected()
{
	var avoidArray = "";
	$("a","#avoidArea").each(function(){
		if($(this).hasClass("green_box_on"))
		{
			avoidArray += ","+$(this).attr("id");
		}
	});
	
	var tasteArray = "";
	$("a","#tasteArea").each(function(){
		if($(this).hasClass("green_box_on"))
		{
			tasteArray += ","+$(this).attr("id");
		}
	});
	
	var pungent = 0;
	$("a","#pungentArea").each(function(){
		if($(this).hasClass("green_box_on"))
		{
			pungent = $(this).attr("id");
		}
	});
	var notes = $("#popNotes").val();
	
	var billId = $("#id").val();
	var type = $("#type").val();
	
	var url = window.ctx+'/bill/cookingNotes/update/'+billId+'?avoidArray='+avoidArray+'&tasteArray='+tasteArray+'&pungent='+pungent+'&notes='+notes;
	if(type && type == 'order')
	{
		url = window.ctx+'/bill/orderCookingNotes/update/'+billId+'?avoidArray='+avoidArray+'&tasteArray='+tasteArray+'&pungent='+pungent+'&notes='+notes;
	}
	 $.ajax({
		    type:"get",
		    url:url,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(data){
		    	closebox();
		    	BillChange(billId);
		    },
			error:function(){
			}
	});
	
}