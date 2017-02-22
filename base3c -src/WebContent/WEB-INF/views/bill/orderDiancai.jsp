<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>点餐</title>
<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/FloatWindow.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bill/orderDiancai.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bill/orderDiancaiDish.js"></script>
</head>
<body>
<!--页面主体begin -->
<input  type="hidden" id="currentOrderId" value="${orderId}"/>
	<div class="body_sec">
		<div class="list_a"  id="ajaxBillContent">
		</div>
		<div class="list_b"  id="ajaxContent">
		</div>
	</div>
	<!--页面主体end -->
</body>
</html>