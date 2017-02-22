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
<link href="${ctx}/static/css/toastr.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>

<link type="text/css" href="${ctx}/static/css/terminal/style0.css" rel="stylesheet" >
<script type="text/javascript" src="${ctx}/static/js/terminal/common.js"></script>
</head>

<body oncontextmenu='return false' ondragstart='return false' onselectstart ='return false' onbeforecopy='return false'>
<div class="YH_father">
    <div class="YH_header">
	   	<div class="time" id="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	    <div class="hhg_logo"></div>
	    <div class="miao" id="daojishi">60秒</div>
	    <div class="mixi_logo" onclick="goAdmin();" style="cursor: pointer;"></div>
    </div>
    
    <p class="YH_miao"></p>
 	<div class="Sbai_bq"></div>
    <div class="Sbai_wz">支付失败&nbsp;&nbsp;请重新点单</div>

    <div class="Sbai_tc">
    	<div class="Sbai_tc_tc" onclick="goIndex();"></div>
        <div class="Sbai_tc_cd" onclick="reOrder();"></div>
    </div>
    
    
</div>
</body>
</html>
