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
	$("#popSave").bind("click",save);
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			save();
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
	
	$("a","#popButtonArea").click(function(){
		if(!$(this).hasClass("tuicai_on"))
		{
			$("a","#popButtonArea").removeClass("tuicai_on");
			$(this).addClass("tuicai_on");
		}
		else
		{
			$(this).removeClass("tuicai_on");
		}
	});
});



function save()
{
	var url = $("#popSaveForm").attr("action");
	var cancelReasonId = "";
	$("a","#popButtonArea").each(function(){
		if($(this).hasClass("tuicai_on"))
		{
			cancelReasonId = $(this).attr("id");
		}
	});
	if(cancelReasonId)
	{
		jQuery.ajax({
			url: url,
			data: $('#popSaveForm').serialize()+"&cancelReasonId="+cancelReasonId,
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				popCloseBox();
				if(data.statusCode == '200')
				{
					chedanPopBack();
				}
				else
				{
					toastr.error(data.message);
				}
				if("undefined" == typeof refreshDishCatagoryChange)
				{
					
				}
				else
				{
					if(refreshDishCatagoryChange)
					{
						refreshDishCatagoryChange();
					}
				}
			}
		});
	}
	else
	{
		toastr.error("选择撤单原因");
		return;
	}
}

function popCloseBox()
{
	closebox();
}

</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/index/pop/chedan/update" method="post" >
		<input type="hidden" value="${dinerBill.billId}"  id="id" name="id"/>
		
		<input type="hidden" value="${dinerBill.table.tabId }"  id="tabId" name="table.tabId" />
	   <div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>撤单</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
			
			<div class="tuicai_wrap">
				<h4> 撤单原因</h4>
				<div class="tuicai_wrap" id="popButtonArea">
					<c:forEach items="${speOpReasons}" var="speOpReason" varStatus="status">
						<a href="###" style="cursor:pointer;" class="tuicai" id="${speOpReason.reaId}"><span><em>${speOpReason.name}</em></span></a>
					</c:forEach>
				</div>
			</div>
			
				<div class="but-area-s">
					<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
					<a class="small-but-quxiao" style="cursor:pointer;" onclick="popCloseBox();" ></a>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>