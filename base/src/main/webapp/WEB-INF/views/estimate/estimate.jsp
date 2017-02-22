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
<link href="${ctx}/static/css/huiyuan.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/estimate/estimate.js"></script>
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
				<a href="javascript:estimate();"  class="guqing_no mr_8" id="guqing"></a>
				<a href="javascript:qxestimate();" class="qx_guqing_no" id="qxguqing"></a>
				<a href="javascript:allQxestimate();" class="qbqx_guqing mr_8" id="allQxguqing"></a>
			</div>
			<div class="guqing_zhuangtai">
				<h3>状态过滤－全部</h3>
				<a href="javascript:void(0);" onclick="changeDishesStatus('',this);" class="gqzt_green gqzt_green_on" oriClass="gqzt_green">
					<span>全部</span>
					<span id="es_quanbu">0份</span>
				</a>
				<a href="javascript:void(0);" onclick="changeDishesStatus(1,this);" class="gqzt_green" oriClass="gqzt_green">
					<span>已沽清</span>
					<span id="es_yiguqing">0份</span>
				</a>
				<a href="javascript:void(0);" onclick="changeDishesStatus(2,this);"  class="gqzt_red" oriClass="gqzt_red">
					<span>已售罄</span>
					<span id="es_yishouxin">0份</span>
				</a>
				<a href="javascript:void(0);" onclick="changeDishesStatus(3,this);"  class="gqzt_yellow" oriClass="gqzt_yellow">
					<span>即将售完</span>
					<span id="es_jiangshouwan">0份</span>
				</a>
				<a href="javascript:void(0);" onclick="changeDishesStatus(4,this);"  class="gqzt_green" oriClass="gqzt_green">
					<span>未沽清</span>
					<span id="es_weiguqing">0份</span>
				</a>
			</div>
		</div>
	</div>
	<!--页面主体end -->

</body>
</html>