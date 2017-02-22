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
<script type="text/javascript" src="${ctx}/static/js/order/confirmForm.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<form id="popSaveForm" action="${ctx}/bill/notes/${billId}" method="post" >
<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>预订审核</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
				<div class="ml_20">
					<div class="h_30">如果用户已经支付了定金，将自动退回其支付帐户。</div>
					<input type="hidden" name="orderId" value="${orderId}">
					<div class="tuicai_wrap">
						<a style="cursor:pointer;" id="confirmYes" class="tuicai"><span><em>审核通过</em></span></a>
						<a style="cursor:pointer;" id="confirmNo" class="tuicai"><span><em>审核失败</em></span></a>
					</div>
				</div>
				
				
				<div class="but-area-s">
					<a class="small-but-queding mr_28" style="cursor:pointer;" onclick="closebox();" id="popSave" ></a>
					<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>

</form>
</body>
</html>