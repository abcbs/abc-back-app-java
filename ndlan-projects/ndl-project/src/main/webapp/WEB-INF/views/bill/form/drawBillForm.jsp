<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bill/drawBillForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/bill/drawBill/${dinerBill.billId}" method="post" >
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>开发票</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="kaifapiao_wrap mt_50">
				<div class="kaifapiao">
					<span class="left">金 额</span>
					<input id="money" name="money" value="${drawBillAmount}" class="input_high" type="text">
				</div>
				<div class="small_jianpan_wrap"  id="sudoku">
					<div class="small_jianpan">
						<a class="but_1" style="cursor:pointer;"></a>
						<a class="but_2" style="cursor:pointer;"></a>
						<a class="but_3" style="cursor:pointer;"></a>
						<a class="but_4" style="cursor:pointer;"></a>
						<a class="but_5" style="cursor:pointer;"></a>
						<a class="but_6" style="cursor:pointer;"></a>
						<a class="but_7" style="cursor:pointer;"></a>
						<a class="but_8" style="cursor:pointer;"></a>
						<a class="but_9" style="cursor:pointer;"></a>
						<a class="but_dot" style="cursor:pointer;"></a>
						<a class="but_0" style="cursor:pointer;"></a>
						<a class="but_del" style="cursor:pointer;"></a>
					</div>
				</div>
			</div>
			<div class="line_d"></div>
			<div class="kaifapiao_wrap mt_10">
				<div class="kaifapiao">
				<span>发票单位</span>
				<div class="uboxstyle">
					<select id="company" name="company">
						<option value="">请选择</option>
						<c:forEach items="${companys}" var="varCompany">
							<c:if test="${varCompany eq dinerBill.company}">
								<c:set var="isNew" value="1"/>
							</c:if>
							<option value="${varCompany}"  <c:if test="${varCompany eq dinerBill.company}">selected="selected"</c:if>>${varCompany}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="kaifapiao h_60">
			<!-- 
				<c:choose>
					<c:when test="${isNew eq 1}">
						<span>新单位</span><input id="newCompany" name="newCompany" class="input_high" type="text">
					</c:when>
					<c:otherwise>
						<span>新单位</span><input id="newCompany" name="newCompany" class="input_high" type="text" value="${dinerBill.company}">
					</c:otherwise>
				</c:choose>
			-->	
			<span>新单位</span><input id="newCompany" name="newCompany" class="input_high" type="text" value="${dinerBill.takeout.invoiceTitle}">
			</div>
			</div>
			<div class="line_d"></div>
			<div class="but-area-s">
				<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
				<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
			</div>
		</div>	
	</div>
</form>
</body>
</html>