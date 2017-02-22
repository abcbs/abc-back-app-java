<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>烹饪备注</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/bill/dishCookingNotes.js"></script>
<script type="text/javascript">



</script>
</head>

<body>
<form id="popSaveForm" action="${ctx}/bill/dishCookingNotes/update" method="post" >
<input type="hidden" value="${bdId}"  id="id" name="id"/>
<input type="hidden" value="${type}"  id="type" name="type"/>
<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>烹饪备注</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="line_x"></div>
			<div class="prbz_title">
				<c:if test="${note_isSet== 1}" >${orderBillDishe.dsName}-烹饪备注：</c:if>
				<c:if test="${note_isSet!= 1}" >${orderBillDishe.dishesName}-烹饪备注：</c:if>				
			</div>
			<div class="line_y"></div>
			<dl class="prbz">
				<dt>忌口</dt>
				<dd id="avoidArea">
					<c:forEach items="${dishesAvoidfoods}" var="dishesAvoidfood" varStatus="status">
						<a style="cursor:pointer;" class="green_box <c:if test="${ fn:indexOf(orderBillDishe.avoidfoodIdArray, dishesAvoidfood.cdaId) >= 0}">green_box_on</c:if>" id='${dishesAvoidfood.cdaId}'><span>
						<em>
						<c:if test="${fn:length(dishesAvoidfood.name) > 8}">${fn:substring(dishesAvoidfood.name,0,7)}..</c:if>
						<c:if test="${fn:length(dishesAvoidfood.name) <= 8}">${dishesAvoidfood.name}</c:if>
						</em>
						</span></a>
					</c:forEach>
					
				</dd>
			</dl>
			<div class="line_y"></div>
			<dl class="prbz">
				<dt>口味</dt>
				<dd id="tasteArea">
					<c:forEach items="${dishesTastes}" var="dishesTaste" varStatus="status">
						<a style="cursor:pointer;" class="green_box <c:if test="${ fn:indexOf(orderBillDishe.tasteIdArray, dishesTaste.tasteId) >= 0}">green_box_on</c:if>" id='${dishesTaste.tasteId}'><span>
						<em>
						<c:if test="${fn:length(dishesTaste.name) > 8}">${fn:substring(dishesTaste.name,0,7)}..</c:if>
						<c:if test="${fn:length(dishesTaste.name) <= 8}">${dishesTaste.name}</c:if>
						</em>
						</span></a>
					</c:forEach>
				</dd>
			</dl>
			<div class="line_y"></div>
			<dl class="prbz">
				<dt>辣度</dt>
				<dd id="pungentArea">
					<a style="cursor:pointer;" class="green_box <c:if test="${orderBillDishe.pungentLevel== 1}">green_box_on</c:if>" id="1"><span><em>不辣</em></span></a>
					<a style="cursor:pointer;" class="green_box <c:if test="${orderBillDishe.pungentLevel== 2}">green_box_on</c:if>" id="2"><span><em>微辣</em></span></a>
					<a style="cursor:pointer;" class="green_box <c:if test="${orderBillDishe.pungentLevel== 3}">green_box_on</c:if>" id="3"><span><em>中辣</em></span></a>
					<a style="cursor:pointer;" class="green_box <c:if test="${orderBillDishe.pungentLevel== 4}">green_box_on</c:if>" id="4"><span><em>辣</em></span></a>
					<a style="cursor:pointer;" class="green_box <c:if test="${orderBillDishe.pungentLevel== 5}">green_box_on</c:if>" id="5"><span><em>特辣</em></span></a>
				</dd>
			</dl>
			<div class="line_y"></div>
			<dl class="prbz">
				<dt>备注</dt>
				<dd>
					<input name="popNotes" class="input_bigger" type="text" id="popNotes" value="${orderBillDishe.notes}">
				</dd>
			</dl>
			<div class="line_y"></div>
			<div class="but-area-c">
				<a class="but-queding mr_28" style="cursor:pointer;" id="popSelect" note_isSet="${note_isSet}" ></a>
				<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();" id="popBack"></a>
			</div>
		</div>
	</div>
</form>	
</body>
</html>