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
	$("#popPrice").sudokuShow();
	
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
	
});



function save()
{
	var billId = $("#popBillId").val();
	var billType = '${popBillType}';
	var price = $("#popPrice").val();
	var dishNum = $("#dishNum").val();
	if(!price || isNaN(price) || price < 0)
	{
		toastr.error("菜品价格为0-99999元，请重新填写!");
		return;
	}
	if(billId || (!billId && billType=='2')|| (!billId && billType=='4'))
	{
		var tId = $("#tId").val();
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/addRulingPrice/${dishe.dishesId}?billId="+billId+"&billType="+billType +"&dishNum=" + dishNum+"&tId="+tId+"&price="+price,
		    data:null,
		    cache:false,
		    success:function(data){
		    	popCloseBox();
		    	var ibillId = data.rel;
		    	BillChange(ibillId);
		    	refreshDiancaiDishFunction();
		    },
			error:function(){}
		  });
	}
	else
	{
		toastr.error("账单出错!");
	}
	
}
function popCloseBox()
{
	if("undefined" == typeof kuaijiejian)
	{
		closebox();
	}
	else
	{
		closebox(kuaijiejian);
	}
}

</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/bill/pop/paymentType/update" method="post" >
	<input type="hidden" value="${dishe.dishesId}"  id="dishesId" name="dishesId"/>
	<input type="hidden" value="${popBillId}"  id="popBillId" name="popBillId"/>
	<input type="hidden" value="${dishNum}"  id="dishNum" name="dishNum"/>
	
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>时价菜品价格录入</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="zdycp_wrap">
				<div class="zdycp">
					<span class="left">原 价</span>
					<input id="popPrice" name="popPrice" value="${dishe.price}" class="input_high" type="text" maxlength="5">
				</div>
				<div class="small_jianpan_wrap" id="sudoku">
					<div class="small_jianpan">
						<a class="but_1" style="cursor:pointer;"></a>
						<a class="but_2" style="cursor:pointer;"></a>
						<a class="but_3" style="cursor:pointer;"></a>
						<a class="but_4" style="cursor:pointer;"></a>
						<a class="but_5" style="cursor:pointer;"></a>
						<a class="but_6" style="cursor:pointer;"></a>
						<a class="but_7" style="cursor:pointer;"></a>
						<a class="but_8" style="cursor:pointer;"></a>
						<a class="but_9" style="cursor:pointer;"></a>
						<a class="but_dot" style="cursor:pointer;"></a>
						<a class="but_0" style="cursor:pointer;"></a>
						<a class="but_del" style="cursor:pointer;"></a>
					</div>
				</div>
			</div>
			<div class="line_d"></div>
			<br/>
			<div class="but-area-s">
				<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
				<a class="small-but-quxiao"  style="cursor:pointer;" onclick="popCloseBox();"></a>
			</div>
		</div>
	</div>
	<input type="text" style="display:none">
</form>
</body>
</html>