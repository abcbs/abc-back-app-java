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
<link href="${ctx}/static/css/zizhu/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/jquery/jquery-ui.js" type="text/javascript"></script>
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/toastrZiZhu.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script src="${ctx}/static/js/zizhu/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/zizhu/zizhuOrder.js" type="text/javascript"></script>
<script  type="text/javascript">
$(document).ready(function() {
	conpareTime();
});
</script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.but_beizhu,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom');</script>
	<![endif]-->
</head>
<body>
<div class="header">
		<h1 class="logo"></h1><span class="time">2014年01月11日 11:30</span>
		<a class="close" style="display: none;"></a>
	</div>
	<div class="main">
		<p class="address">www.xxx.cn</p>
		<div class="dc_menu">
			<a class="bell"></a>
			<a class="tea"></a>
			<span class="G2"></span>
			<a class="drink_glass"></a>
			<a class="plate"></a>
		</div>
		<p class="system">自助点餐系统</p>
		
		<c:forEach items="${buttets}" var="buttet" varStatus="status">
			<c:if test="${buttet.buffetType == '0' }">
				<dl id="ziZhuTime" buffetStartTime="${buttet.buffetStartTime}" buffetEndTime="${buttet.buffetEndTime}" class="zizu_wu1" onclick="indexBuffetClick('${ctx}','${buttet.cbsId}',this)">
					<dt>${buttet.buffetStartTime}-${buttet.buffetEndTime}</dt>
					<dd>
						<span>${buttet.buffetName}</span>
						<i><fmt:formatNumber value="${buttet.price}" type="currency" pattern="#.##"/>/位</i>
					</dd>
				</dl>
			</c:if>
			<c:if test="${buttet.buffetType == '1' }">
				<dl id="ziZhuTime" buffetStartTime="${buttet.buffetStartTime}" buffetEndTime="${buttet.buffetEndTime}" class="zizu_wu2" onclick="indexBuffetClick('${ctx}','${buttet.cbsId}',this)">
					<dt>${buttet.buffetStartTime}-${buttet.buffetEndTime}</dt>
					<dd>
						<span>${buttet.buffetName}</span>
						<i><fmt:formatNumber value="${buttet.price}" type="currency" pattern="#.##"/>/位</i>
					</dd>
				</dl>
			</c:if>
			<c:if test="${buttet.buffetType == '2' }">
				<dl id="ziZhuTime" buffetStartTime="${buttet.buffetStartTime}" buffetEndTime="${buttet.buffetEndTime}" class="zizu_wan1" onclick="indexBuffetClick('${ctx}','${buttet.cbsId}',this)">
					<dt>${buttet.buffetStartTime}-${buttet.buffetEndTime}</dt>
					<dd>
						<span>${buttet.buffetName}</span>
						<i><fmt:formatNumber value="${buttet.price}" type="currency" pattern="#.##"/>/位</i>
					</dd>
				</dl>
			</c:if>
			<c:if test="${buttet.buffetType == '3' }">
				<dl id="ziZhuTime" buffetStartTime="${buttet.buffetStartTime}" buffetEndTime="${buttet.buffetEndTime}" class="zizu_wan2" onclick="indexBuffetClick('${ctx}','${buttet.cbsId}',this)">
					<dt>${buttet.buffetStartTime}-${buttet.buffetEndTime}</dt>
					<dd>
						<span>${buttet.buffetName}</span>
						<i>${buttet.price}/位</i>
					</dd>
				</dl>
			</c:if>
		</c:forEach>
		
		
		
		
		
	</div>

</body>
</html>