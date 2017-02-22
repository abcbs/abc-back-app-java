$(document).ready(function(){
	$("#popSave").bind("click",saveCustomDiscount);
	$("#discount").sudokuShow();
	
	
	$("#customDiscountForm").CanYinValidate([
       	    {
       		    id:"discount",
       		    name:"任意折扣",
       		    require:true,
       		    type:'intNumberThanZero'
       		}
   	  ]);
	
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveCustomDiscount();
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

function saveCustomDiscount()
{
	if(!$("#customDiscountForm").CanYinValid())
	{
		return;
	}
	var discount = $("#discount").val();
	if(discount > 100)
	{
		toastr.error("任意折扣为0-100之间的折扣");
		return;
	}
	var discount = $("#discount").val();
	if(discount){
		var url = $("#customDiscountForm").attr("action");
		jQuery.ajax({
			url: url,
			data: $('#customDiscountForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function(){
			},
			success: function(data){
				closebox();
				refreshPayPage(true);
			}
		});
	}
	else
	{
		toastr.error("请输入正确的数值");
	}
	
}