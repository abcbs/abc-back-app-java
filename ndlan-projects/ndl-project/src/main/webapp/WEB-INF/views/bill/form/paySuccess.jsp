<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>结账成功</title>
<script type="text/javascript" src="${ctx}/static/js/bill/paySuccess.js"></script>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>结账成功</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
			<div class="ml_100 mt_50">
				<div class="ml_100 mt_50 text_green left">
				结账成功！！
				</div>
			</div>
				<div class="but-area-t">
					<a class="small-but-zrctym mr_28" onclick="window.location='${ctx}/index'"></a>
					<a class="small-but-queding" id="queding"></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>