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
<title>xxx自助点餐系统</title>
<script  type="text/javascript">
$(document).ready(function() {
	cleatConpareTime();
});
</script>
</head>
<body>
<div class="header">
		<h1 class="logo"></h1><span class="time">2014年01月11日 11:30</span>
		<a class="close" style="display: none;"></a>
	</div>
	<div class="main">
		<p class="djs">60秒</p>
		<p class="address">www.xxx.cn</p>
		<div class="zizu_face"></div>
		<div class="zizu_text_wrap">
			<p class="zizu_text">恭喜您支付成功！共支付￥<span><fmt:formatNumber value="${bill.realCost}" type="currency" pattern="#.##"/></span>元</p>
		</div>
		<p class="zizu_text_yellow">请取您的小票并妥善保存！</p>
		<div class="but_wrap_1">
			<a class="but_zizu" onclick="window.location='${ctx}/self/zizhu/exit'">安全退出</a>
		</div>
	</div>

</body>
</html>