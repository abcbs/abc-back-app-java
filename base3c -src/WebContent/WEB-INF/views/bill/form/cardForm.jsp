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
<link href="css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/bill/cardForm.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<form id="popSaveForm" action="${ctx}/bill/pop/paymentType/update" method="post">
		<input type="hidden" id="billId" value="${billId}" name="billId"/>
		<input type="hidden" id="cptId" value="${cptId}" name="cptId"/>
		<input type="hidden" id="mbId" value="${mbId}" name="mbId"/>
		<input type="hidden" id="paymentType" value="${paymentType}" name="paymentType"/>
		<input type="hidden" id="dbpId" value="${dinerBillPayment.dbpId}" name="dbpId"/>
		<input type="hidden" id="membershipCardId" value="${membershipCard.mcId}" name="membershipCardId"/>
		<input type="hidden" id="mcId" name="mcId" value="${membershipCard.mcId}"/>
		<input type="hidden" id="cardNo" name="cardNo" value="${membershipCard.cardNo}"/>
		<input type="hidden" id="isEmptyPassWord" value="${membershipCard.isEmptyPassWord}"/>
		<input type="hidden" id="membercardPayType" name="membercardPayType" value="${membercardPayType}"/>
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
					<div class="hykjz_left">
						<div class="hukjs relative">
							<div class="fdj"></div>
							<input id="cardSearch" name="cardSearch" type="text" onkeyup="getMemberByTel()" callBackFunction="getMemberByTel()" value="${kewWords}">
							<a id="cardSearchButton" class="but-jianpan"></a>
							<div class="jianpan_wrap" id="sudoku">
								<div class="jianpan">
									<a class="but_1"></a>
									<a class="but_2"></a>
									<a class="but_3"></a>
									<a class="but_4"></a>
									<a class="but_5"></a>
									<a class="but_6"></a>
									<a class="but_7"></a>
									<a class="but_8"></a>
									<a class="but_9"></a>
									<a class="but_close"></a>
									<a class="but_0"></a>
									<a class="but_del"></a>							
								</div>
							</div>
						</div>
						<div class="hykjz_infor_wrap">
							<ul class="hykjz_infor" id="ajaxContent2">
							</ul>
						</div>
					</div>
					<div class="hykjz_right" style="padding-top:20px;">
						<div class="left w_330 h_64 ml_50" style="height:45px;">
							<label class="label_100" for="interest">已选会员卡</label>
							<input id="cardNoInput" class="input" type="text" name="interest" value="" />
							<a class="but-del" href="#" id="cancleMemberCard" style="<c:if test="${membershipCard eq null || isWebPay eq '1'}">display:none;</c:if>"></a>
						</div>
						<div id="passwordDiv" class="left w_330 h_64 relative ml_50">
							<label class="label_100" for="interest">会员卡密码</label>
							<input id="cardPassword" class="input" type="password" name="interest" value="" />
							<a id="cardPasswordButton" class="but-jianpan" href="#"></a>
							<div class="jianpan_wrap" id="sudoku">
								<div class="jianpan">
									<a class="but_1"></a>
									<a class="but_2"></a>
									<a class="but_3"></a>
									<a class="but_4"></a>
									<a class="but_5"></a>
									<a class="but_6"></a>
									<a class="but_7"></a>
									<a class="but_8"></a>
									<a class="but_9"></a>
									<a class="but_close"></a>
									<a class="but_0"></a>
									<a class="but_del"></a>							
								</div>
							</div>
						</div>
						<div class="left w_330 h_50 ml_85" style="height:35px;">
							<label class="label_100" for="interest">余额</label>
							<span id="cardNoYuELable"></span>
						</div>
						<div class="left w_330 h_50 ml_85" style="height:35px;">
							<label class="label_100" for="iml_100nterest">积分</label>
							<span id="cardNoJifenLable"></span>
						</div>
						<div class="line_z"></div>
						<div class="left ml_50 mt_40" style="margin-top:20px;">
							<c:choose>
								<c:when test="${membercardPayType eq '2'}">
									<a id="consume" class="but_huk mr_15">使用会员卡消费</a>
									<a id="discount" class="but_huk_on">使用会员卡打折</a>
								</c:when>
								<c:otherwise>
									<a id="consume" class="but_huk_on mr_15">使用会员卡消费</a>
									<a id="discount" class="but_huk">使用会员卡打折</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="left w_330 h_64 relative ml_50 mt_40"> 
							<label  id="moneyLable" class="label_100" for="interest">金额</label>
							<c:if test="${money ne null && money ne ''}">
								<c:set var="needMoney" value="${money+needMoney}"/>
							</c:if>
							<input id="money" class="input" type="text" name="money" value="${needMoney}" />
							<a id="moneyButton" class="but-jianpan" href="#"></a>
							<div class="jianpan_wrap" id="sudoku">
								<div class="jianpan">
									<a class="but_1"></a>
									<a class="but_2"></a>
									<a class="but_3"></a>
									<a class="but_4"></a>
									<a class="but_5"></a>
									<a class="but_6"></a>
									<a class="but_7"></a>
									<a class="but_8"></a>
									<a class="but_9"></a>
									<a class="but_close"></a>
									<a class="but_0"></a>
									<a class="but_del"></a>							
								</div>
							</div>
						</div>
						
						<div class="line_z"></div>
						<div class="but-area-d left">
							<a id="selectCard" class="but-queding mr_28" href="#"></a>
							<a class="but-quxiao" href="#" onclick="closebox();"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>