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
	var isSet = $("#popSelect").attr("note_isSet");
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
	var bdId = $("#id").val();
	var type = $("#type").val();
	
	var url = "";
	if(type && type == 'order')
	{
		url = window.ctx+'/bill/orderDishCookingNotes/update/'+bdId+'?avoidArray='+avoidArray+'&tasteArray='+tasteArray+'&pungent='+pungent+'&notes='+notes;
		url = url + "&isSet=" + isSet;//套餐标志
	}else{
		url = window.ctx+'/bill/dishCookingNotes/update/'+bdId+'?avoidArray='+avoidArray+'&tasteArray='+tasteArray+'&pungent='+pungent+'&notes='+notes;
		url = url + "&isSet=" + isSet;//套餐标志
	}
	 $.ajax({
		    type:"get",
		    url:url,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(data){
		    	closebox();
		    	if(type && type == 'order')
		    	{
		    		var orderId = $("#currentOrderId").val();
			    	BillChange(orderId);
		    	}
		    	else
		    	{
		    		var billId = $("#currentBillId").val();
			    	BillChange(billId);
		    	}
		    	
		    },
			error:function(){
			}
	});
	
}