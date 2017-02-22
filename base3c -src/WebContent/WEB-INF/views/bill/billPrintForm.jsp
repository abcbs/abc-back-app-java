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
<script type="text/javascript" src="${ctx}/static/js/bill/billPrintForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<div class="small_popup_wrap" style="height:680px;">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3 class="tuicai-title">打印</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body" style="height:634px;">
			<div class="small_popup_cont">
				<div class="dayin_wrap"  style="height:500px;">
					<c:choose>
						<c:when test="${printers == null || fn:length(printers) == 0}">
							<h3 class="tuicai-title">无匹配打印机</h3>
						</c:when>
						<c:otherwise>
							<c:forEach items="${printers}" var="printer">
								<a class="dayin" href="#" onclick="billPrint('${billId}','${printer.printerId}');">${printer.name}</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="but-area-b">
					<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>