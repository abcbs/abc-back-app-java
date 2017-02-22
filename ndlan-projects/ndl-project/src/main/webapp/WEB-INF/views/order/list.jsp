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
<title>预订</title>
<link href="${ctx}/static/css/huiyuan.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/order/list.js"></script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		if('${message}'!=''){
			//toastr.success('${message}');
		}
	});
</script>
</head>
<body>
	<input type="hidden" id="kewWords" name="kewWords" value="${kewWords}">
	<!--页面头部begin -->
	<div class="header">
		<h1 class="logo"></h1>
	</div>
	<!--页面头部end -->
	<!--页面主体begin -->
	<div class="body_huiyuan">
		<div class="list_h" id="ajaxContent">
		</div>
		
		<div class="list_y">
			<div class="hy_gongneng_1">
				<a id="newOrder" class="yd_xinzeng mr_8" ></a>
				<a id="confirm" class="yd_shqr_no"></a>
				<a id="kaitai"  class="yd_zkt_no mr_8"></a>
				<a id="cancle" class="yd_qxyd_no"></a>
			</div>
			<div class="yd_gongneng_2">
				<h3 id="orderListStatusShow">预订状态－全部</h3>
				<a id="search_EQ_orderStatus_" class="yd_gongneng_2_a" style="cursor:pointer;"  onclick="changeTableArea('','','',1);" href="###"><span><i>全部</i></span></a>
				<a id="search_EQ_orderStatus_2" class="yd_gongneng_2_a" style="cursor:pointer;" onclick="changeTableArea('','','search_EQ_orderStatus=2',1);" href="###"><span><i>预订中</i></span></a>
				<a id="search_EQ_orderStatus_4" class="yd_gongneng_2_a" style="cursor:pointer;" onclick="changeTableArea('','','search_EQ_orderStatus=4',1);"  href="###"><span><i>完结</i></span></a>
				<a id="search_EQ_orderStatus_1" class="yd_gongneng_2_a" style="cursor:pointer;" onclick="changeTableArea('','','search_EQ_orderStatus=1',1);" href="###"><span><i>待确认</i></span></a>
				<a id="search_EQ_orderStatus_5" class="yd_gongneng_2_a" style="cursor:pointer;" onclick="changeTableArea('','','search_EQ_orderStatus=5',1);" href="###"><span><i>取消</i></span></a>
				<a id="search_EQ_orderStatus_7" class="yd_gongneng_2_a" style="cursor:pointer;" onclick="changeTableArea('','','search_EQ_orderStatus=7',1);" href="###"><span><i>过期</i></span></a>
			</div>
		</div>
	</div>
	<input type="hidden" id="from" value="${from}">
	<input type="hidden" id="preMobile" value="${preMobile}">
	<!--页面主体end -->
	<!-- 权限加载 -->
<input type="hidden" id="permission_confirm" value="<shiro:hasPermission name="frontdesk_order_confirm:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_kaitai" value="<shiro:hasPermission name="frontdesk_order_kaitai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_cancle" value="<shiro:hasPermission name="frontdesk_order_cancle:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_yuding" value="<shiro:hasPermission name="frontdesk_order_order:create">1</shiro:hasPermission>">
	
</body>
</html>