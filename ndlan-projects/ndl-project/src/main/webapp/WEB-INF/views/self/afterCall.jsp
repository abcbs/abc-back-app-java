<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>手机--下単确认</title>
<link rel="stylesheet" href="${ctx}/static/jquerymobile/jquery.mobile-1.3.2.css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>

<script type="text/javascript">
function refreshMessage() {
	console.log("ids:"+new Date());
};
$(document).bind("pageinit", function() {
	$.extend($.mobile, {
		self : {},
		callCenter : function() {
			console.log("ids:");
		}
	});
	
	var refreshId=setInterval("refreshMessage()", 2000);  
	//window.clearInterval(refreshId);
	
});
</script>
<script src="${ctx}/static/jquerymobile/jquery.mobile.call.js"></script>
<script src="${ctx}/static/jquerymobile/jquery.mobile-1.3.2.js"></script>
</head>
<body>
	<div data-role="page" class="type-interior">
		<!-- incubator -->
		<div data-role="header" class="ui-bar ui-bar-b">
			<div id="searchform" style="padding:5px 0;display:block;">
				<p>
					<a id="goOrder" data-role="button" data-transition="slide" style="width: 120px" href="#">去下单</a>
				</p>
				餐台号：${tableNo} <a href="${ctx}/self/menu?tn=006" data-rel="popup">Open</a> <small>test<a href="#api-popup" data-rel="popup" data-transition="slideup" data-position-to="window">点击提示</a>.
				</small>
			</div>
		</div>
		<div data-role="navbar" style="width: 320px">
			<ul>
				<li><a href="${ctx}/self/preCall" class="zhu-ui-bar-e">下单确认</a></li>
				<li><a href="${ctx}/self/afterCall" class="zhu-ui-bar-e">呼叫服务员</a></li>
				<li><a href="b.html" class="zhu-ui-bar-e">继续加菜</a></li>
				<li><a href="a.html" class="zhu-ui-bar-e">全单删除</a></li>
			</ul>
		</div>
		
	</div>
</body>
</html>