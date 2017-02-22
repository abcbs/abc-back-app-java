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
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script  type="text/javascript">
$(document).ready(function() {
	isDaoshu = true;
	stopSecond = 30;
	seconds = 30;
});
</script>
</head>
<body>
<div class="header">
		<div class="bige_logo"></div>
		<p class="time">AM10:10</p>
		<div class="G2_logo" onclick="goAdmin();"></div>
	</div>
	<div class="zhifu">
		<div class="success">
			<div class="success_img"></div>
			<p class="font24">恭喜您支付成功！共支付￥${realCost}</p>
			<p class="font18">Congratulations on your successful payment! To pay a total of $ ${realCost}</p>
			<p class="font18">지불성공! 총지불금액${realCost}원</p>
		</div>
		<img src="${ctx}/static/bige/imgs/logo_pizza.png" class="pizza">
	</div>
	<div class="footer">
		<a class="but_back ml433" onclick="window.location='${ctx}/self/bige/index'"></a>
	</div>

</body>
</html>