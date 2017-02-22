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
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/order/orderForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/order/${action}" method="post" >
		<input type="hidden" value="${tableOrder.orderId}"  id="id" name="id"/>
		<input type="hidden" value="${tableOrder.isEnterDiancaiPage}"  id="isEnterDiancaiPage" name="isEnterDiancaiPage"/>
		
<div class="popup_wrap" id="one">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>预订信息</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="popup_cont">
				<fieldset class="kt_yuding">
					<div class="left w_330 h_64">
						<label class="label_100" for="interest"><i class="red">*</i>预  订 人：</label>
						<input class="input" type="text" name="orderPeopleName" disabled="disabled" id="orderPeopleName" value="${tableOrder.orderPeopleName}" />
					</div>
					<div class="left w_330 h_64 relative z_99999">
						<label  class="label_100" for="interest"><i class="red">*</i>预订时间：</label>
						<input class="input" type="text" value="${tableOrder.orderTime}" disabled="disabled" id="orderTime"  name="orderTimeStr"/>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest">就餐人数：</label>
						<input class="input" type="text" id="peopleNum" disabled="disabled" name="peopleNum" value="${tableOrder.peopleNum}"/>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest"><i class="red">*</i>联系电话：</label>
						<input class="input" type="text" value="${tableOrder.telphone}"  id="telphone" disabled="disabled" name="telphone"/>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest"><i class="red">*</i>餐     台：</label>
						<input class="input" type="text" value="${tableOrder.tabNo}"  id="tabNo" disabled="disabled" name="tabNo" <c:if test="${action == 'update' }">disabled="disabled"</c:if>/>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest">预付款：</label>
						<input class="input" type="text" value="${tableOrder.prepay}"  id="prepay" disabled="disabled" name="prepay"/>
					</div>
					
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">服务员：</label>
						<input class="input" type="text" name="waiterName" id="waiterName" value="${tableOrder.waiter.name}" disabled="disabled"/>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">营销员：</label>
						<input class="input" type="text" name="salesmanName" id="salesmanName" value="${tableOrder.salesMg.name}"  disabled="disabled"/>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">预订方式：</label>
						<c:forEach items="${orderWayList}" var="way">
							<input id="orderWay" name="orderWay" disabled="disabled" type="radio" value="${way.bciCode}" ${tableOrder.orderWay==way.bciCode?"checked":''}>${way.bciDesc}
						</c:forEach>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">付款方式：</label>
						<c:forEach items="${paymentTypeList}" var="payment" varStatus="status">
								<input id="paymentType_radio" disabled="disabled" type="radio" value="${payment.cptId}" ${tableOrder.paymentType.cptId==payment.cptId?"checked":''}>${payment.paymentName}
						</c:forEach>
					</div>
					<div class="left w_600 h_64">
						<label class="label_100" for="interest">备注：</label>
						<input class="input-big" type="text" value="${tableOrder.notes}" disabled="disabled" id="notes" name="notes"/>
					</div>
					<div class="left w_600 h_64">
						<label class="label_100" for="interest">取消原因：</label>
						<label class="label_100" for="interest">${tableOrder.cancleReason}</label>
					</div>
					<div class="but-area-b">
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox(kuaijiejian);"></a>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</form>
</body>
</html>