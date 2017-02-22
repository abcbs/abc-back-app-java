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
<title>会员卡支付</title>
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery-ui.js" type="text/javascript"></script>
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/toastr.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/ProgressbarUtil.js"></script>
<script type="text/javascript" src="${ctx}/static/js/terminal/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/terminal/cardPay.js"></script>
<link type="text/css" href="${ctx}/static/css/terminal/style0.css" rel="stylesheet" >
</head>

<body id="body" oncontextmenu='return false' ondragstart='return false' onselectstart ='return false' onbeforecopy='return false'>
<input id="billId" type="hidden" name="billId" value="${billId}">
<div class="YH_father">
	<div class="YH_header">
    	<div id="time" class="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="hhg_logo"></div>
        <div class="miao" id="daojishi">60秒</div>
        <div class="mixi_logo" onclick="goAdmin();"></div>
	    
    </div>
    <p class="YH_miao"></p>
    <div class="HY_ka"></div>
    <div class="HY_zh"><input id="cardNo" type="text" class="HY_text1" value=""></div>
	<div class="HY_mima"><input id="cardPassword" type="password" class="HY_text2" value=""></div>
	<div class="YH_anniu">
    	<ul>
        	<li class="YH_anniu_li1" onclick="history.go(-1);"></li>
            <li class="YH_anniu_li2" onclick="goIndex();"></li>
            <li class="YH_anniu_li3" onclick="pay();"></li>
        </ul>
    </div>   
</div>
</body>
</html>
