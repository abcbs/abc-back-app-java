<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<script type="text/javascript" src="${ctx}/static/js/order/cancleOrderForm.js"></script>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<title>诺德兰电子点餐系统</title>

</head>

<body>
<form id="popSaveForm" action="${ctx}/order/cancle/${orderId}" method="post" class="form-horizontal">
<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>取消预订</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
			<div class="ml_20">
				<div class="h_30">如果用户已经在线支付，将自动退回其支付帐户。	</div>
				<div class="tuicai_wrap" id="popButtonArea">
					<c:forEach items="${speOpReasons.content}" var="speOpReason" varStatus="status">
						<a style="cursor:pointer;" mes="${speOpReason.name}" id="${speOpReason.reaId}" onclick="javascrpt: $("#cancleReason").val(${speOpReason.name});" class="tuicai"><span><em>${speOpReason.name}</em></span></a>
					</c:forEach>
				</div>
			</div>
				<div class="but-area-s">
					<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave" ></a>
					<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>
	
	
</form>	
	
</body>
</html>