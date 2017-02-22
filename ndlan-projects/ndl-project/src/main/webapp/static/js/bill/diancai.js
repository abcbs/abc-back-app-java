$(document).ready(function(){
	initFrameSize();
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"1",
		callBackFunction:function()
		{
			var fourStatus = $("#fourButtons").is(":hidden");
			var threeStatus = $("#threeButtons").is(":hidden");
			if(!fourStatus == true || !threeStatus == true){
				return;
			}
			var billId = $("#currentBillId").val();
			var jump = window.ctx+"/bill/list?billId=" + billId;
			window.location = jump;
		}
	},
	{
		keyCode:"2",
		callBackFunction:function()
		{
			var fourStatus = $("#fourButtons").is(":hidden");
			var threeStatus = $("#threeButtons").is(":hidden");
			if(!fourStatus == true || !threeStatus == true){
				return;
			}
			var billId = $("#currentBillId").val();
			if(billId)
			{
				var jump = window.ctx+"/bill/diancai?billId=" + billId;
				window.location = jump;
			}
			else
			{
				toastr.info("请选择账单");
			}
		}
	}
	,
	{
		keyCode:"3",
		callBackFunction:function()
		{
			var fourStatus = $("#fourButtons").is(":hidden");
			var threeStatus = $("#threeButtons").is(":hidden");
			if(!fourStatus == true || !threeStatus == true){
				return;
			}
			var billId = $("#currentBillId").val();
			if(billId)
			{
				var jump = window.ctx+"/bill/payPage/" + billId;
				window.location = jump;
			}
			else
			{
				toastr.info("请选择账单");
			}
		}
	}
	,
	{
		keyCode:"4",
		callBackFunction:function()
		{
			var fourStatus = $("#fourButtons").is(":hidden");
			var threeStatus = $("#threeButtons").is(":hidden");
			if(!fourStatus == true || !threeStatus == true){
				return;
			}
			$("#xiadan").click();
		}
	}
	,
	{
		keyCode:"enter",
		callBackFunction:function()
		{
		}
	}
	,
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			var site_url = window.location.href.toLowerCase();	
			if(site_url.indexOf("fastfood") > 0)
			{
				window.location=window.ctx+'/takeout/list';
			}
			else
			{
				window.location=window.ctx+'/member';
			}
		}
	}
	,
	{
		keyCode:"shift_enter",
		callBackFunction:function()
		{
			$("#diancaiDishDiv").children("div").eq(0).click();
			isShiftAndEnter = true;
		}
	}
	,
	{
		keyCode:"right",
		callBackFunction:function()
		{
			$(".but_qx_right").click();
		}
	}
	,
	{
		keyCode:"left",
		callBackFunction:function()
		{
			$(".but_qx_left").click();
		}
	}
	],0);
});
/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body_sec").css("width",1000+subWidth+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_sec").css("height",672+subHeight+"px");
		$(".list_a").css("height",670+subHeight+"px");
		$(".list_b").css("height",670+subHeight+"px");
	}
}