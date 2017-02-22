<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>会员卡结账</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/bill/creditForm.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<form id="popSaveForm" action="${ctx}/bill/pop/paymentType/update" method="post">
		<input type="hidden" value="${billId}"  id="billId" name="billId"/>
		<input type="hidden" value="${cptId}"  id="cptId" name="cptId"/>
		<input type="hidden" value="${mbId}"  id="mbId" name="mbId"/>
		<input type="hidden" value="${paymentType}"  id="paymentType" name="paymentType"/>
		<input type="hidden" value="${dinerBillPayment.dbpId}"  id="dbpId" name="dbpId"/>
		<input type="hidden" value="${membershipCard.mcId}"  id="membershipCardId" name="membershipCardId"/>
		
		<div class="popup_wrap">
			<div class="popup_header">
				<div class="popup_header_l"></div>
				<div class="popup_header_c">
					<h3>${paymentTypeDesc}</h3>
				</div>
				<div class="popup_header_r"></div>
			</div>
			<div class="popup_body">
				<div class="line_x"></div>
				<div class="hykjz_wrap">
					
					<div class="hykjz_infor_wrap" id="ajaxContent2">
					</div>
					
					<div class="hykjz_right">
						<div class="hykjz relative">
							<span>会员卡检索</span>
							<input id="cardSearch" name="cardSearch" class="input_high" type="text" onkeyup="getMemberByTel()" callBackFunction="getMemberByTel()" value="${kewWords}">
							<div class="jianpan_wrap" id="sudoku">
								<div class="jianpan">
									<a class="but_1" style="cursor:pointer;"></a>
									<a class="but_2" style="cursor:pointer;"></a>
									<a class="but_3" style="cursor:pointer;"></a>
									<a class="but_4" style="cursor:pointer;"></a>
									<a class="but_5" style="cursor:pointer;"></a>
									<a class="but_6" style="cursor:pointer;"></a>
									<a class="but_7" style="cursor:pointer;"></a>
									<a class="but_8" style="cursor:pointer;"></a>
									<a class="but_9" style="cursor:pointer;"></a>
									<a class="but_close" style="cursor:pointer;"></a>
									<a class="but_0" style="cursor:pointer;"></a>
									<a class="but_del" style="cursor:pointer;"></a>
								</div>
							</div>
						</div>
						<div class="hykjz relative">
							<span>金额</span>
							<c:if test="${money ne null && money ne ''}">
								<c:set var="needMoney" value="${money+needMoney}"/>
							</c:if>
							<input id="money" name="money" class="input_high" type="text" value="${needMoney}">
							<div class="jianpan_wrap" id="sudoku">
								<div class="jianpan">
									<a class="but_1" style="cursor:pointer;"></a>
									<a class="but_2" style="cursor:pointer;"></a>
									<a class="but_3" style="cursor:pointer;"></a>
									<a class="but_4" style="cursor:pointer;"></a>
									<a class="but_5" style="cursor:pointer;"></a>
									<a class="but_6" style="cursor:pointer;"></a>
									<a class="but_7" style="cursor:pointer;"></a>
									<a class="but_8" style="cursor:pointer;"></a>
									<a class="but_9" style="cursor:pointer;"></a>
									<a class="but_close" style="cursor:pointer;"></a>
									<a class="but_0" style="cursor:pointer;"></a>
									<a class="but_del" style="cursor:pointer;"></a>
								</div>
							</div>
						</div>
						
						<div class="line_z"></div>
						<div class="hykjz">
							<input type="hidden" id="mcId" name="mcId" value="${membershipCard.mcId}">
							<input type="hidden" id="cardNo" name="cardNo" value="${membershipCard.cardNo}">
							<input type="hidden" id="isEmptyPassWord" value="${membershipCard.isEmptyPassWord}">
							<span>已选会员卡</span><p id="cardNoLable">${membershipCard.cardNo}</p>
							<a class="but_delete" href="###" id="cancleMemberCard" style="<c:if test="${membershipCard eq null || isWebPay eq '1'}">display:none;</c:if>"></a>
						</div>
						
						<div class="hykjz">
							<span></span><p id="cardNoYuELable"></p>
							<span></span><p id="cardNoJifenLable"></p>
						</div>
						
						
						<div id="passwordDiv" class="hykjz">
							<span>会员卡密码</span>
							<input id="cardPassword" name="cardPassword"  class="input_high" type="password">					
						</div>
						<div class="line_z"></div>
						<div class="but-area-d">
							<a class="but-queding mr_28"  style="cursor:pointer;" id="selectCard"></a>
							<a class="but-quxiao"  style="cursor:pointer;" onclick="closebox();"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>