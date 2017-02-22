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
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/member/chongzhiForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/member/chongzhi/create" method="post" >
	<input type="hidden" value="${membershipCard.mcId}"  id="mcId" name="mcId"/>
	<input type="hidden" value="${membershipCard.cardNo}"  id="cardNo" name="cardNo"/>
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>充值</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="tuika_wrap">
				<div class="tuika_other mt_30"><span class="left">卡号2</span>${membershipCard.cardNo}</div>
				<div class="tuika_other"><span class="left">会员姓名</span>${membershipCard.restMemberInfo.name}</div>
				<div class="tuika_other"><span class="left">余额</span>${membershipCard.balance}</div>
				<div class="tuika">
					<span class="left"><i class="text_red">*</i>充值金额</span><input id="rechargeCash" name="rechargeCash" class="input_high" type="text">
				</div>
				<div class="ksfk_row_2">
					<label><i class="red">*</i>付款方式</label>
					<div class="uboxstyle">
						<select name="new_paymentType" id="new_paymentType">					
							<option value="">请选择</option>
							<c:forEach items="${paymentTypes}" var="paymentType" varStatus="status">				
								<option value="${paymentType.cptId}" <c:if test="${paymentType.paymentType eq 1}">selected="selected"</c:if>>${paymentType.paymentName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
						
						
				<div class="tuika">
					<span class="left"><i class="text_red">*</i>实收金额</span><input id="paidinCash" name="paidinCash" class="input_high" type="text" onchange="chongzhi(this);">
				</div>
				<div class="tuika">
					<span class="left">积分</span><input id="new_memberIntegral" name="new_memberIntegral" class="input_high" type="text" value="0">
				</div>
				<div class="tuika_other"><span class="left">充值日期</span><fmt:formatDate  value="${now}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></div>
				<div class="tuika ml_20 mt_30">
					<a class="small_but_piao" style="cursor:pointer;" onclick="newDrawBill(this);">开发票</a>
					<input type="hidden" name="isDrawBill" id="isDrawBill" value="0"/>
					
					<a class="small_but_piao" style="cursor:pointer;" onclick="newPrintBill(this);">打印小票</a>
					<input type="hidden" name="isPrint" id="isPrint" value="0"/>
					
				</div>
			</div>
			<div class="but-area-t">
				<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
				<a class="small-but-quxiao"  style="cursor:pointer;" onclick="closebox();"></a>
			</div>
		</div>
	</div>
</form>
</body>
</html>