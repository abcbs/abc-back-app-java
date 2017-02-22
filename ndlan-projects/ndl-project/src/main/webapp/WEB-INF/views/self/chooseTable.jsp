<%@page import="com.ndlan.canyin.base.entity.ctzh.Table"%>
<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>自助选台</title>
<style type="text/css">
<!--
*{margin:0;padding:0;}
a{text-decoration:none;}
ul,li {list-style: none;}
body {width:1536px;margin:0px; background:#378900 url(${ctx}/static/images/self/bg_zuowei.jpg) no-repeat;}
.zuowei_header{width:1536px;height:100px;}
.logo{width:204px;height:65px;margin:12px 40px;float:left;display:inline;background:url(${ctx}/static/images/self/logo.png);}
.dian_name{float:left;margin-top:20px;font-size:54px;color:#666;}
.zhuozi_wrap{width:1536px;float:left;margin-top:295px;}
.zhuozi_wrap li{width:307px;float:left;height:306px;}
.zhuozi_wrap a{width:184px;height:180px;padding:0 40px 60px 0;margin:45px 0 0 68px;display:inline;color:#fff;font-size:36px;line-height:180px; vertical-align:middle;text-align:center;display:block;background:url(${ctx}/static/images/self/bg_zhuozi.png) -226px 0;}
.zhuozi_wrap a:hover{color:#378900;background:url(${ctx}/static/images/self/bg_zhuozi.png);}
-->
</style>
</head>
<body>
	<div class="zuowei_header">
		<%
			if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){
		%>
				<h1 class="logo"></h1>
				<%}%>
		
		<h2 class="dian_name">${restName}</h2>
	</div>
	<ul class="zhuozi_wrap">
			<c:forEach items="${tableList}" var="table" varStatus="status">
			<c:set var="tabNo" value="${table.tabNo}" />
				<li><a href="${ctx}/self?t=<%=URLEncoder.encode(pageContext.getAttribute("tabNo").toString(), "UTF-8") %>"><c:if test="${fn:length(table.tabNo) <= 5}">${table.tabNo}</c:if><c:if test="${fn:length(table.tabNo) > 5}">${fn:substring(table.tabNo,0,5)}</c:if></a></li>
			</c:forEach>
	</ul>
</body>
</html>