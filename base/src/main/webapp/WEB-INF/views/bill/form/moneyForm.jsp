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
<script type="text/javascript" src="${ctx}/static/js/bill/moneyForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/bill/pop/paymentType/update" method="post" >
	<input type="hidden" value="${billId}"  id="billId" name="billId"/>
	<input type="hidden" value="${cptId}"  id="cptId" name="cptId"/>
	<input type="hidden" value="${paymentType}"  id="paymentType" name="paymentType"/>
	<input type="hidden" value="${dinerBillPayment.dbpId}"  id="dbpId" name="dbpId"/>
	<input type="hidden" value="0"  id="isSuc" name="isSuc"/>
	
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>${paymentTypeDesc}</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="zdycp_wrap">
				<div class="zdycp">
					<span class="left">金 额</span>
							<c:if test="${money ne null && money ne ''}">
								<c:set var="needMoney" value="${money+needMoney}"/>
							</c:if>
							<input id="money" name="money" value="${needMoney}" class="input_high" type="text" onfocus="this.value = ''">
				</div>
				<div class="small_jianpan_wrap" id="sudoku">
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
			<br/>
			<div class="but-area-s">
				<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
				<a class="small-but-quxiao"  style="cursor:pointer;" onclick="closebox();"></a>
			</div>
		</div>
	</div>
	<input type="text" style="display:none">
</form>
</body>
</html>