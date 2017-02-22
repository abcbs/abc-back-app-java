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
<title>外卖单追踪</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>外卖单追踪</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="dd_zhuizong">
              <div class="dd_zhuizong_con">
                <div class="zhuizong_title">云订单号：${onlineBillNo}</div>
                <c:forEach var="cloudLog" items="${cloudBillLogs}">
                	<div class="zhuizong_con">
	                  <span class="span_wt210"><fmt:formatDate value="${cloudLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
	                  <span class="span_wt150"></span>
	                  <span class="span_wt90">${cloudLog.billOpTypeDesc}</span>
	                </div>
                </c:forEach>
              </div>
              <div class="p_line_d"></div>
              <c:if test="${billNo ne null && billNo != ''}">
	              <div class="dd_zhuizong_con">
	                <div class="zhuizong_title"><a href="${ctx}/takeout/list?keywords=${billNo}" style="float:right; text-decoration:underline;margin-right:10px;">查看外卖</a>外卖单号：${billNo}</div>
	                <c:forEach var="billLog" items="${dinerBillLogs}">
	                	<div class="zhuizong_con">
		                  <span class="span_wt210"><fmt:formatDate value="${billLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
		                  <span class="span_wt150">${billLog.createEmployee.name}</span>
		                  <span class="span_wt90">${billLog.billOpTypeDesc}</span>
		                </div>
	                </c:forEach>
	              </div>
              </c:if>
            </div>
			<div class="line_d" style="margin-bottom:0px;"></div>
			<div class="but-area-s" style="bottom:40px;padding-left:50px;">
				<a class="small-but-queding mr_28" href="javaScript:closebox();"></a>
			</div>
		</div>	
	</div>
</body>
</html>