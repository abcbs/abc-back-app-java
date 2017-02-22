<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>手机--自助点餐</title>
<link rel="stylesheet" href="${ctx}/static/jquerymobile/jquery.mobile-1.3.2.css" />
<link rel="stylesheet" href="${ctx}/static/jquerymobile/G2.css" />
<link rel="stylesheet" href="${ctx}/static/jquerymobile/jquery.mobile.simpledialog.css" />
<link rel="stylesheet" href="${ctx}/static/css/self.css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/jquerymobile/jquery.mobile.self.js"></script>
<script src="${ctx}/static/jquerymobile/jquery.mobile-1.3.2.js"></script>
<script src="${ctx}/static/jquerymobile/jquery.mobile.simpledialog2.js"></script>
<script src="${ctx}/static/jquerymobile/jquery.floatDiv.js"></script>
<script src="${ctx}/static/jquerymobile/jquery.pageslide.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$(document).delegate(".dish-filter", "tap", function(event) {
			$.post("jiacai/1/"+$(this).attr("lang")+"'?billId="+$('#billId').val(), { "func": "" },
					   function(data){
					     alert(data+"点菜成功"); 
					   }, "json");
			
			$("#searchform").append($(this).attr("lang") + "\<br/>");
			var tempOrderData = jQuery.data(document.body, 'dishData');
			// alert($.isArray(tempOrderData));
			tempOrderData.push({"dishesId":$(this).attr("lang"),"saleNum":2});
		});
		
		$("#goOrderAAAAA").bind("click", function(event) {
			$.mobile.changePage("#page1", {
				transition : "pop",
				changeHash : true
			});
		});
		$("#p2").bind("click", function(event) {
			$.mobile.changePage("#page1", {
				transition : "pop",
				changeHash : false
			});
		});
		
		
	});
	$("#control_Btn").pageslide({ direction: "left"});
	$(document).delegate('#opendialog', 'click', function() {
		
		  // NOTE: The selector can be whatever you like, so long as it is an HTML element.
		  //       If you prefer, it can be a member of the current page, or an anonymous div
		  //       like shown.
		  $('<div>').simpledialog2({
		    mode: 'blank',
		    headerText: '菜肴详情',
		    dialogAllow: true,
		    headerClose: true,
		    blankContent : 
		      "<ul data-role='listview'><li><img src='http://t2.qpic.cn/mblogpic/54703f15fcb863cc2962/220' class='lazy' /></li><li>菜肴</li><li>菜肴</li></ul>"+
		      "<a rel='close' data-role='button' href='#'>关闭</a>"
		  })
		});
	$(function(){
		$("#goOrderAAAAA").floatdiv({right:"30px",bottom:"50px"});
	});
		
</script>
</head>
<body>
	<input id="billId" name="billId" value="${dinerBill.billId}" />
	<!-- <div data-role="page" class="type-interior"  id="menu-pageAA" style="display: none;"> -->
	<!-- 		<div class="main_nav"> -->
	<!-- 			<a href="#" class="call"></a> -->
	<!-- 			<a href="#" class="tjcj"></a> -->
	<!-- 			<a href="#" class="jc"></a> -->
	<!-- 			<a href="#" class="cc_on"></a> -->
	<!-- 			<a href="#" class="dj"></a> -->
	<!-- 			<a href="#" class="qt_on"></a> -->
	<!-- 			<a href="#" class="help"></a> -->
	<!-- 		</div> -->
	<!-- 		<a href="#" class="but_control"></a> -->
	<!-- 	</div> -->

	<div data-role="page" class="type-interior" id="menu-page">
		<!-- incubator -->
		<div data-role="header" class="ui-bar ui-bar-b">
			<div id="searchform" style="padding: 5px 0; display: block;" orderId="${dinerBill.billId}">
				<p>
				<a href="javascript:$.pageslide({ direction: 'left', href='#page1' })">Link text</a>
				
					<a href="#" id="opendialog" data-role="button">菜肴详情</a> <a class="but_control" href="#">AAA</a> <a id="goOrderAAAAA" data-role="button" data-transition="slide" style="width: 150px" href="orderedDish">(30份)去下单</a> 单号：${dinerBill.billNo}
					<c:if test="${dinerBill.billStatusDesc != null && dinerBill.billStatusDesc != ''}">
							(${dinerBill.billStatusDesc})
						</c:if>
					<span>桌号： <c:if test="${fn:length(dinerBill.tabNo) > 9}">
							<a title="${dinerBill.tabNo}">${fn:substring(dinerBill.tabNo,0,7)}..</a>
						</c:if> <c:if test="${fn:length(dinerBill.tabNo) <= 9}">${dinerBill.tabNo}</c:if> <c:if test="${dinerBill.billType == '2' }">外卖</c:if>
					</span> <span class="left w_200">时间： <fmt:formatDate value="${dinerBill.billTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
					</span> <span id="billStatusDesc"> <c:choose>
							<c:when test="${dinerBill.tableConsumeLow > 0}">
								<span class="text_red">最低消费:${dinerBill.tableConsumeLow}</span>
							</c:when>
							<c:otherwise>
								<span>开单人：${dinerBill.createEmployee.name}</span>
							</c:otherwise>
						</c:choose>
					</span> 
					 <a id="goOrder" data-role="button" data-transition="slide" style="width: 120px" href="#">去下单</a>
					 <a id="control_Btn11" data-ajax='false' rel="external" data-ajax="false" data-role="button" data-transition="slide" style="width: 120px" href="http://baidu.com">侧边栏a</a> 
					 <a id="control_Btn" data-role="button" data-transition="slide" style="width: 120px" href="#page1">侧边栏a</a>
				</p>
				餐台号：${tableNo} <a href="${ctx}/self/menu?tn=006" data-rel="popup">Open</a> <small>test<a href="#api-popup" data-rel="popup" data-transition="slideup" data-position-to="window">点击提示</a>.
				</small>
			</div>
		</div>
		<div data-role="navbar" style="width: 320px">
			<ul>
				<li><a href="${ctx}/self/preCall" class="zhu-ui-bar-e">下单确认</a></li>
				<li><a href="${ctx}/self/afterCall" class="zhu-ui-bar-e">呼叫服务员</a></li>
				<li><a href="#page1" class="zhu-ui-bar-e">继续加菜</a></li>
				<li><a href="y" class="zhu-ui-bar-e">全单删除</a></li>
			</ul>
		</div>
		<!-- incubator -->
		<!-- 	test -->
		<div data-role="popup" id="api-popup" class="home-pop ui-content" data-theme="d" data-overlay-theme="b">
			<p>搜索菜肴</p>
			<a href="#" data-role="button" data-inline="true" data-rel="back" data-mini="true" data-theme="c">取消</a> <a href="http://www.xxx.cn" class="jqm-button" data-ajax="false" data-role="button" data-inline="true" data-mini="true" data-icon="arrow-r" data-iconpos="right" data-theme="f"></a>
		</div>
		<!-- 	test	end -->

		<div data-role="header" class="ui-bar ui-bar-b">
			<div id="searchform" style="padding: 5px 0; display: block;">
				<form action="" method="get">
					<input type="hidden" name="type" value="1" />
					<div class="ui-input-search ui-shadow-inset ui-btn-corner-all ui-btn-shadow ui-icon-searchfield ui-body-c">
						<input type="search" data-role="none" name="key" id="search" placeholder="搜索菜肴" class="ui-input-text ui-body-c" />
					</div>
				</form>
			</div>
		</div>

		<div data-role="navbar" id="dish-navbar-filter">
			<ul>
				<li><a href="#dishList" data-rel="dialog" data-transition="pop" class="zhu-ui-bar-a">全部</a></li>
				<li><a href="b.html" class="zhu-ui-bar-a">菜类</a></li>
				<li><a href="a.html" class="zhu-ui-bar-a">菜系</a></li>
				<li><a href="b.html" class="zhu-ui-bar-a">销量</a></li>
				<li><a href="a.html" class="zhu-ui-bar-a">价格</a></li>

			</ul>
		</div>



		<div id="dishList" data-role="content" style="margin-top: 3px; padding-top: 15px;"></div>

		<div data-role="footer" data-id="foo1" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="${ctx}/self/menu" class="ui-btn-active ui-state-persist" data-icon="search" data-iconpos="notext" data-theme="a" data-inline="true" data-transition="pop">菜谱</a></li>
					<li><a href="${ctx}/self/message" data-icon="grid" data-iconpos="notext" data-theme="a" data-inline="true" data-transition="flip">呼叫中心</a></li>
					<li><a href="${ctx}/self/more" data-icon="gear" data-iconpos="notext" data-theme="a" data-inline="true">更多</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /footer -->


	</div>
	<!--end  菜谱主菜单 -->

	<div data-role="page" id="page1">

		<ul data-role="listview">
			<li><a data-role="button" href="#menu-page" data-theme="b">back</a></li>
		</ul>
		<div data-role="navbar" style="width: 620px">
			<ul>
				<li><a href="${ctx}/self/preCall" class="zhu-ui-bar-e">下单确认</a></li>
				<li><a href="${ctx}/self/afterCall" class="zhu-ui-bar-e">呼叫服务员</a></li>
				<li><a href="b.html" class="zhu-ui-bar-e">继续加菜</a></li>
				<li><a href="a.html" class="zhu-ui-bar-e">全单删除</a></li>
				<li><a data-role="button" href="#page2" data-rel="dialog">默认情况下打开</a></li>
			</ul>
		</div>

	</div>


	<div data-role="page" id="page2" data-close-btn-text="关闭">
		<div data-role="header" data-theme="b">
			<h1>欢迎访问xfs</h1>
		</div>
		<div data-role="content">
			<div data-role="collapsible" data-content-theme="a" data-theme="b">
				<h3>This is header</h3>
				<p>
					This is content!<br />data-content-theme="a" data-theme="b"
				</p>
			</div>
			<a data-role="button" data-rel="back">back</a>
		</div>
		<div data-role="footer" data-theme="a">
			<h1>欢迎访问PG99.NET</h1>
		</div>
	</div>
</body>
</html>