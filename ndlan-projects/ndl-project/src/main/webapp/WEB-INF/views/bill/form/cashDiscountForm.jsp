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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>折扣方案</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/bill/cashDiscountForm.js"></script>
<script type="text/javascript">
	function changePopTableArea(searchParams,page){
		var billId = $("#billId").val();
		var thisUrl = '${ctx}/bill/pop/cashDiscount/' + billId + '?page='+page+'&'+searchParams;
		refreshPopForm(thisUrl);
	}
</script>
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
				<h3 class="tuicai-title">折扣方案</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
			<div class="ml_20">
				<div class="h_30">折扣方案选择</div>
				<div class="tuicai_wrap">
					<c:forEach items="${cashDiscounts.content}" var="cash" varStatus="status">
						<c:if test="${status.index == 0}">
							<c:choose>
								<c:when test="${dinerBill.cashDiscount.ccdId eq null}">
									<a href="###" onclick="cancleDiscount('${cash.ccdId}','${dinerBill.billId}');" class="tuicai tuicai_select"><span><em>无折扣</em></span></a>
								</c:when>
								<c:otherwise>
									<a href="###" onclick="cancleDiscount('${cash.ccdId}','${dinerBill.billId}');" class="tuicai"><span><em>无折扣</em></span></a>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:choose>
							<c:when test="${cash.ccdId eq dinerBill.cashDiscount.ccdId}">
								<a href="###" onclick="chooseDiscount('${cash.ccdId}','${dinerBill.billId}');" class="tuicai tuicai_select"><span><em>${cash.discountName}</em></span></a>
							</c:when>
							<c:otherwise>
								<a href="###" onclick="chooseDiscount('${cash.ccdId}','${dinerBill.billId}');" class="tuicai"><span><em>${cash.discountName}</em></span></a>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
				</div>
			</div>
				<div class="but-area-s">
					<tags:paginationpop page="${cashDiscounts}" paginationSize="15"/>
					<a class="small-but-zdyzk mr_28" href="###" id="customDiscount"></a>
					<a class="small-but-quxiao"  href="###" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>