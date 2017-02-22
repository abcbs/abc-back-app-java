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
<link href="${ctx}/static/bige/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/jquery/jquery-ui.js" type="text/javascript"></script>
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/toastrZiZhu.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/popup/dialog.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ProgressbarUtil.js"></script>
<script src="${ctx}/static/js/CanYinHotKeys.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bige/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bige/zizhuOrder.js" type="text/javascript"></script>
<script  type="text/javascript">
$(document).ready(function() {
});
</script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.but_beizhu,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom');</script>
	<![endif]-->
</head>
<body>
<div class="header">
		<div class="bige_logo"></div>
		<p class="time">AM10:10</p>
		<div class="G2_logo" onclick="goAdmin();"></div>
	</div>
	<div class="index_main">
		<div class="pic_area_1"></div>
	</div>
	<div class="footer">
		<a class="but_ksdc" onclick="changeBody('${ctx}/self/bige/zizhuOrder');"></a>
	</div>

</body>
</html>