<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-touch-fullscreen" content="YES">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>手机--自助点餐</title> 
	<link rel="stylesheet" href="${ctx}/static/jquerymobile/jquery.mobile-1.3.2.css" />
	<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquerymobile/jquery.mobile.self.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquerymobile/jquery.mobile-1.3.2.js"></script>
	<script type="text/javascript">
	
	</script>
</head>
<body>
	<div data-role="page" class="type-interior">
	
	<div data-role="header" class="ui-bar ui-bar-b">
		<div id="searchform" style="padding:5px 0;display:block;">
			餐台号：${tableNo}
			<a href="${ctx}/self/menu?tn=006" data-rel="popup">Open</a>
		</div>	
	</div>

	<div data-role="header" class="ui-bar ui-bar-b">
		<div id="searchform" style="padding:5px 0;display:block;">
			<form action="" method="get">
			<input type="hidden" name="type" value="1"/> 
			<div class="ui-input-search ui-shadow-inset ui-btn-corner-all ui-btn-shadow ui-icon-searchfield ui-body-c">
			<input type="search" data-role="none" name="key" id="search" placeholder="搜索菜肴" class="ui-input-text ui-body-c"/>
			</div>
			</form>
		</div>	
	</div>

	<div data-role="navbar">
		<ul>
			<li><a href="a.html" class="zhu-ui-bar-a">全部</a></li>
			<li><a href="b.html" class="zhu-ui-bar-a">招牌</a></li>
			<li><a href="a.html" class="zhu-ui-bar-a">推荐</a></li>
			<li><a href="b.html" class="zhu-ui-bar-a">好评</a></li>
			<li><a href="a.html" class="zhu-ui-bar-a">最热</a></li>
			
		</ul>
	</div>
	
	<div data-role="content" style="margin-top:3px;padding-top:15px;">
		<ul data-role="listview">
			<li data-role="list-divider">分类菜谱</li>
			
			<c:forEach items="${dishes.content}" var="dishe" varStatus="status">
			<li><a href="">
				<img src="http://t2.qpic.cn/mblogpic/54703f15fcb863cc2962/220"  class="lazy"/>
				<h3>${dishe.dishesName}---${dishe.foreignName}  </h3>
				<p> ${dishe.price}元  </p>
				<p>普通面粉 紫苋菜汁 
				</p>
			</a></li>
			</c:forEach>
		</ul>
		
		
	</div>
	
	<div data-role="footer" data-id="foo1" data-position="fixed">
		<div data-role="navbar">
			<ul>
				<li><a href="${ctx}/self/menu"  class="ui-btn-active ui-state-persist" data-icon="search" data-iconpos="notext" data-theme="a" data-inline="true">菜谱</a></li>
				<li><a href="${ctx}/self/message" data-icon="grid" data-iconpos="notext" data-theme="a" data-inline="true">呼叫中心</a></li>
				<li><a href="${ctx}/self/more" data-icon="gear" data-iconpos="notext" data-theme="a" data-inline="true">更多</a></li>
				
			</ul>
		</div><!-- /navbar -->
	</div><!-- /footer -->
</div>
</body>
</html>