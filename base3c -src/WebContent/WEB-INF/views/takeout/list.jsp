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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>外卖</title>
<link href="${ctx}/static/css/waimai.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/contextmenu.js"></script>
<script type="text/javascript" src="${ctx}/static/js/takeout/list.js"></script>

	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.shiyong,.yi_jiezhang,.on,div,a');</script>
	<![endif]-->
</head>
<body>
	<!--页面主体begin -->
	<div class="body">
		<form id="takeoutListForm" action="${ctx}/takeout/ajax/takeoutContent">
			<input id="sendTimeType" name="sendTimeType" type="hidden">
			<input id="billStatus" name="billStatus" type="hidden">
			<input id="keywords" name="keywords" type="hidden" value="${keywords}">
			<input id="tId" name="tId" type="hidden">
			<input id="billId" name="billId" type="hidden">
				<div style="width:788px;height:665px;border-left:1px solid #ababab;float:left;position:relative;">
					<div class="main_nav">
						<ul>
							<li class="quanbu"><a id="timeAll" href="#" onclick="setTimeType('',this);">全部送餐时间</a></li>
							<li><a id="time0" href="#" class="yuding" onclick="setTimeType('0',this);">预订</a></li>
							<li><a id="time15" href="#" class="shiwufen" onclick="setTimeType('15',this);">15分钟</a></li>
							<li><a id="time30" href="#" class="sanshifen" onclick="setTimeType('30',this);">30分钟</a></li>
							<li><a id="timeLong" href="#" class="gengjiu" onclick="setTimeType('-1',this);">更久</a>	</li>
						</ul>
					</div>
					<div id="takeoutContent" class="index_box">
					  
					</div>
				</div>
			<div class="list_r">
				<div class="gongneng_area_wrap">
					<ul class="gongneng_area">
						<li class="mr_10"><a id="xinzeng" class="kaitai" href="#"><span></span></a></li>
						<li><a id="diancai" class="diancai_no" href="#"></a></li>
						<li class="mr_10"><a id="xiadan" class="yuding_no" href="#"></a></li>
						<li><a id="chedan" class="jiezhang_no" href="#"></a></li>
						<li class="mr_10"><a id="paisong" class="cuicai_no" href="#"></a></li>
						<li><a id="jiezhang" class="qingtai_no" href="#"></a></li>
					</ul>
				</div>
				<dl class="zhuangtai_area">
					<dt id="billStatusDT">状态过滤－全部</dt>
					<dd class="mr_10">
						<a class="zt_quanbu" href="#" onclick="setStatus('',this,'全部');"><span style="margin-top:22px;">全部</span></a>
					</dd>
					<dd>
						<a class="zt_quanbu" href="#" onclick="setStatus('-1',this,'未完结');"><span id="weiwanjieCountSpan"></span></a>
					</dd>
					<dd class="mr_10">
						<a class="zt_quanbu" href="#" onclick="setStatus('1',this,'未下单');"><span id="weixiadanCountSpan"></span></a>
					</dd>
					<dd>
						<a class="zt_quanbu" href="#" onclick="setStatus('2',this,'已下单');"><span id="yixiadanCountSpan"></span></a>
					</dd>
					<dd class="mr_10">
						<a class="zt_quanbu" href="#" onclick="setStatus('11',this,'派送中');"><span id="paisongzhongCountSpan"></span></a>
					</dd>
					<dd>
						<a class="zt_quanbu" href="#" onclick="setStatus('3',this,'已完结');"><span id="yiwanjieCountSpan"></span></a>
					</dd>
				</dl>
			</div>
		</form>
	</div>
	<input type="hidden" id="from" value="${from}">
	<input type="hidden" id="preMobile" value="${preMobile}">
	<!--页面底部end 
	<div class="tanchu">
		<a class="index_xiugai" href="#"></a>
		<a class="index_diancai" href="#"></a>
		<a class="index_xiadan" href="#"></a>
		<a class="index_chedan" href="#"></a>
		<a class="index_jiezhang" href="#"></a>
		<a class="index_dayin" href="#"></a>
	</div>-->
	<!-- 权限加载 -->
<input type="hidden" id="permission_takeout_addTakeout" value="<shiro:hasPermission name="frontdesk_takeout_addTakeout:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_takeout_diancai" value="<shiro:hasPermission name="frontdesk_takeout_diancai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_takeout_xiadan" value="<shiro:hasPermission name="frontdesk_takeout_xiadan:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_takeout_chedan" value="<shiro:hasPermission name="frontdesk_takeout_chedan:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_takeout_paisong" value="<shiro:hasPermission name="frontdesk_takeout_paisong:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_takeout_jiezhang" value="<shiro:hasPermission name="frontdesk_takeout_jiezhang:create">1</shiro:hasPermission>">
</body>
</html>