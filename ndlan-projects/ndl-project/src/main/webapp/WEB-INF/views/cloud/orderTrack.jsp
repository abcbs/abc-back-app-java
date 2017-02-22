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
<title>预订单追踪</title>
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
				<h3>预订单追踪</h3>
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
              <c:forEach var="orderMap" items="${orderList}">
              	<c:if test="${orderMap.orderNo ne null && orderMap.orderNo != ''}">
		              <div class="dd_zhuizong_con">
		                <div class="zhuizong_title"><a href="${ctx}/order?kewWords=${orderMap.orderNo}" style="float:right; text-decoration:underline;margin-right:10px;">查看预订</a>预订单号：${orderMap.orderNo}</div>
		                <c:forEach var="orderLog" items="${orderMap.orderBillLog}">
		                	<div class="zhuizong_con">
			                  <span class="span_wt210"><fmt:formatDate value="${orderLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			                  <span class="span_wt150">${orderLog.createEmployee.name}</span>
			                  <span class="span_wt90">${orderLog.billOpTypeDesc}</span>
			                </div>
		                </c:forEach>
		              </div>
	            </c:if>
              </c:forEach>
              
			  
			  <div class="p_line_d"></div>
			  <c:if test="${billNo ne null && billNo != ''}">
	              <div class="dd_zhuizong_con">
	                <div class="zhuizong_title"><a href="${ctx}/bill/list?keywords=${billNo}" style="float:right; text-decoration:underline;margin-right:10px;">查看账单</a>账单号：${billNo}</div>
	                <c:forEach var="billLogs" items="${dinerBillLogs}">
	                	<div class="zhuizong_con">
		                  <span class="span_wt210"><fmt:formatDate value="${billLogs.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
		                  <span class="span_wt150">${billLogs.createEmployee.name}</span>
		                  <span class="span_wt90">${billLogs.billOpTypeDesc}</span>
		                </div>
	                </c:forEach>
	              </div>
			  </c:if>
			  
            </div>
			<div class="line_d" style="margin-bottom:0px;"></div>
			<div class="but-area-s" style="bottom:30px; left:230px;">
				<a class="small-but-queding" href="javaScript:closebox();"></a>
			</div>
		</div>	
	</div>
</body>
</html>