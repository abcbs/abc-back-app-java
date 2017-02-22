<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery-ui.js" type="text/javascript"></script>
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/static/js/terminal/common.js"></script>
<link href="${ctx}/static/css/terminal/style2.css" rel="stylesheet" type="text/css" />
</head>
<div class="KS_father" oncontextmenu='return false' ondragstart='return false' onselectstart ='return false' onbeforecopy='return false'>
	<div class="ks_header">
    	<div class="time" id="time"></div>
        <div class="hhg_logo"></div>
        <div class="mixi_logo" onclick="goAdmin();"></div>
    
    </div>
	
    <div class="KS_central">	    
    </div>
    
    <p class="zzdcxt">自助点餐系统</p>
    <div class="KS_bottom" onclick="reOrder();"></div>




</div>
<body>


</body>
</html>
