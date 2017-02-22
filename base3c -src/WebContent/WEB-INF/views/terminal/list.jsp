<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>自助点餐</title>
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery-ui.js" type="text/javascript"></script>
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/static/js/ProgressbarUtil.js"></script>
<script type="text/javascript" src="${ctx}/static/js/popup/dialog.js"></script>
<script type="text/javascript" src="${ctx}/static/js/terminal/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/terminal/index.js"></script>
<script type="text/javascript" src="${ctx}/static/js/terminal/bill.js"></script>

<link type="text/css" rel="stylesheet" href="${ctx}/static/css/terminal/style0.css">
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.but_beizhu,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom');</script>
	<![endif]-->
</head>
<body id="body" oncontextmenu='return false' ondragstart='return false' onselectstart ='return false' onbeforecopy='return false' >

<input type="hidden" id="currentBillId" value="${billId}"/>
<input type="hidden" id="currentBillType" value="${billType}"/>

<div class="HYK_father">
	<div class="header">
    	<ul>
        	<li class="time" id="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li class="hhg_logo"></li>
            <li class="miao" id="daojishi">60秒</li>
            <li class="mixi_logo" onclick="goAdmin();"></li>
        </ul>
    </div>
	
    <div class="HYK_connect">
        <div class="HYK_left" id="billAjaxContent">
        </div>
        <div class="HYK_right" id="dishesAjaxContent">
        </div>
    </div>
</div>

  <div id="jiantou" class="jiantou">
            	<div class="jiantou_1" onclick="clickUp();"></div>
                <div class="jiantou_2" onclick="clickDown();"></div>
            </div>
            
</body>
</html>
