<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#popSave").unbind("click");
	$("#popSave").bind("click",cardTuika);
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			cardTuika();
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
function cardTuika()
{
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
			toastr.info(data.message);
			refreshPage();
		}
	});
	return false;
}

	function popTuika(type,ob)
	{
		var jo = $(ob);
		
		if(jo.hasClass("small_but_piao_on"))
		{
			jo.removeClass().addClass("small_but_piao");
			if(type == 1)
			{
				//退还押金
				$("#tuika_cashPledge").val(0);
			}
			else if(type == 2)
			{
				//退还余额
				$("#tuika_balance").val(0);
			}
		}
		else
		{
			jo.removeClass().addClass("small_but_piao_on");
			if(type == 1)
			{
				//退还押金
				$("#tuika_cashPledge").val(1);
			}
			else if(type == 2)
			{
				//退还余额
				$("#tuika_balance").val(1);
			}
		}
		
		caMoney();
	}
	
	function caMoney()
	{
		var tuikaBalance = $("#tuika_balance").val();
		var tuikaCashPledge = $("#tuika_cashPledge").val();
		
		var total = 0;
		var balance = $("#balance").val();
		var cashPledge = $("#cashPledge").val();
		if(!balance || balance == null || balance == '')
		{
			balance = 0;
		}
		if(!cashPledge || cashPledge == null || cashPledge == '')
		{
			cashPledge = 0;
		}
		if(tuikaBalance == 1)
		{
			total += parseFloat(balance);
		}
		if(tuikaCashPledge == 1)
		{
			total += parseFloat(cashPledge);
		}
		$("#popTuikaMoney").html("退款金额:"+total);
	}
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/member/tuika/save" method="post" >
	<input type="hidden" value="${membershipCard.mcId}"  id="mcId" name="mcId"/>
	
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>退卡</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
			<div class="tuika_wrap">
				<div class="tuika ml_20">卡号：${membershipCard.cardNo}</div>
				<div class="tuika ml_20">余额：${membershipCard.balance}</div>
				<div class="tuika ml_20">押金：${membershipCard.cashPledge}</div>
				<div class="tuika ml_20">
					<a class="small_but_piao" style="cursor:pointer;" onclick="popTuika(1,this);">退还押金</a>
					<a class="small_but_piao" style="cursor:pointer;" onclick="popTuika(2,this);">退还余额</a>
					
					<input type="hidden" name="tuikaBalance" id="tuika_balance" value="0"/>
					<input type="hidden" name="tuikaCashPledge" id="tuika_cashPledge" value="0"/>
					
					<input type="hidden" name="balance" id="balance" value="${membershipCard.balance}"/>
					<input type="hidden" name="cashPledge" id="cashPledge" value="${membershipCard.cashPledge}"/>
					
				</div>
				<div class="tuika" id="popTuikaMoney">退款金额：0</div>
			</div>
				<div class="but-area-t">
					<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
					<a class="small-but-quxiao"  style="cursor:pointer;" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>