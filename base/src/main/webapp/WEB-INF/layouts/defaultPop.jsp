<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>xxx-前台管理<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/validate.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.12/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.12/messages_bs_zh.js"></script>
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
<sitemesh:head/>
</head>

<body>
	<sitemesh:body/>
</body>
</html>