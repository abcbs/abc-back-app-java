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
<title>前台收银</title>
<link href="${ctx}/static/css/index.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" media="screen and (min-width: 1152px) and (max-device-width: 1279px)" href="${ctx}/static/css/index-1152.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-width: 1280px) and (max-device-width: 1343px)" href="${ctx}/static/css/index-1280.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-width: 1344px) and (max-device-width: 1359px)" href="${ctx}/static/css/index-1344.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-width: 1360px) and (max-device-width: 1365px)" href="${ctx}/static/css/index-1360.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-width: 1366px) and (max-device-width: 1439px)" href="${ctx}/static/css/index-1366.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-width: 1440px) and (max-device-width: 1599px)" href="${ctx}/static/css/index-1440.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-width: 1600px) and (max-device-width: 1919px)" href="${ctx}/static/css/index-1600.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-width: 1920px) and (max-device-width: 3000px)" href="${ctx}/static/css/index-1920.css" />


<script type="text/javascript" src="${ctx}/static/js/contextmenu.js"></script>
<script type="text/javascript" src="${ctx}/static/js/index.js"></script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom,#keywordsbtn');</script>
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
	<!--页面头部end -->
	<!--页面主体begin -->
	<div class="body">
		<div class="list_l" id="ajaxContent" style="position:relative;height:641">
		</div>
		<div class="list_r">
			<div class="gongneng_area_wrap">
				<ul class="gongneng_area">
					<li class="mr_10"><a id="kaitai" class="kaitai"  href="###"><span></span></a></li>
					<li><a id="diancai" class="diancai"  href="###"></a></li>
					<li class="mr_10"><a id="yuding" class="yuding"  href="###"></a></li>
					<li><a id="jiezhang" class="jiezhang_on"  href="###"></a></li>
					<li class="mr_10"><a id="cuicai" class="cuicai"  href="###"></a></li>
					<li><a id="qingtai"  class="qingtai"  href="###"></a></li>
					<li class="mr_10"><a id="waimai" class="waimai"  href="###"></a></li>
					<li><a id="zhuantai" class="zhuantai"  href="###"></a></li>
					<li class="mr_10"><a id="bingtai" class="bingtai"  href="###"></a></li>
					<li><a id="chedan" class="chedan"  href="###"></a></li>
				</ul>
				<ul class="gongneng_area none">
					<li class="mr_10"><a class="chedan" style="cursor:pointer;"><span></span></a></li>
				</ul>
			</div>
			<dl class="zhuangtai_area">
				<dt id="indexStatusShow">餐台状态－全部<shiro:hasPermission name="frontdesk_index_kaitai:create">1</shiro:hasPermission></dt>
				<dd class="mr_10">
					<a class="zt_quanbu" ori="zt_quanbu" id="zt_quanbu" style="cursor:pointer;" onclick="changeTableStatus('',this); "  href="###"><span>全部<br/>0台</span></a>
				</dd>
				<dd>
					<a class="zt_shiyong" ori="zt_shiyong"  id="zt_using" style="cursor:pointer;" onclick="changeTableStatus('2',this);"  href="###"><span>使用中<br/>0台</span></a>
				</dd>
				<dd class="mr_10">
					<a class="zt_jiejiang" ori="zt_jiejiang" id="zt_payed"   style="cursor:pointer;" onclick="changeTableStatus('5',this);" href="###"><span>已结帐<br/>0台</span></a>
				</dd>
				<dd>
					<a class="zt_kongxian" ori="zt_kongxian" id="zt_idle"  style="cursor:pointer;" onclick="changeTableStatus('1',this);" href="###"><span>空闲<br/>0台</span></a>
				</dd>
			</dl>
		</div>
	</div>
	<!--页面主体end 
	<div class="tanchu">
		<a class="index_kaitai" href="#"></a>
		<a class="index_diancai" href="#"></a>
		<a class="index_yuding" href="#"></a>
		<a class="index_jiezhang" href="#"></a>
		<a class="index_cuicai" href="#"></a>
		<a class="index_qingtai" href="#"></a>
		<a class="index_waimai" href="#"></a>
		<a class="index_zhuantai" href="#"></a>
		<a class="index_chedan" href="#"></a>
		<a class="index_bingtai" href="#"></a>
	</div>
-->
<!-- 权限加载 -->
<input type="hidden" id="permission_kaitai" value="<shiro:hasPermission name="frontdesk_index_kaitai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_xiadan" value="<shiro:hasPermission name="frontdesk_index_xiadan:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_cuicai" value="<shiro:hasPermission name="frontdesk_index_cuicai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_diancai" value="<shiro:hasPermission name="frontdesk_bill_diancai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_yuding" value="<shiro:hasPermission name="frontdesk_order_order:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_jiezhang" value="<shiro:hasPermission name="frontdesk_bill_payPage:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_qingtai" value="<shiro:hasPermission name="frontdesk_index_qingtai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_waimai" value="<shiro:hasPermission name="frontdesk_index_waimai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_zhuantai" value="<shiro:hasPermission name="frontdesk_index_zhuantai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_bingtai" value="<shiro:hasPermission name="frontdesk_index_bingtai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_chedan" value="<shiro:hasPermission name="frontdesk_index_chendan:create">1</shiro:hasPermission>">
</body>
</html>