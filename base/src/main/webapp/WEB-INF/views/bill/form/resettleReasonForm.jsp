<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<script type="text/javascript" src="${ctx}/static/js/bill/selectSpeOpReason.js"></script>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<title>诺德兰电子点餐系统</title>

</head>

<body>
<form id="popSaveForm" action="${ctx}/bill/resettle/${billId}" method="post" class="form-horizontal">
<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>反结账</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="small_popup_cont">
			<div class="ml_20">
				<div class="h_30">反结账的账单请到前台收银中重新结账。	</div>
				<div class="tuicai_wrap" id="popButtonArea">
					<c:forEach items="${speOpReasons.content}" var="speOpReason" varStatus="status">
						<a style="cursor:pointer;" id="${speOpReason.reaId}" class="tuicai"><span><em>${speOpReason.name}</em></span></a>
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