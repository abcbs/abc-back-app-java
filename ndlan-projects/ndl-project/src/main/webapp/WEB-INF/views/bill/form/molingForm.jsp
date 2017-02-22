<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>抹零</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/bill/molingForm.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<div class="small_popup_wrap" id="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>抹零</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<input id="billId" type="hidden" name="billId" value="${billId}">
		<div class="small_popup_body_auto">
			<div class="moling_wrap">
				<div class="moling">
					<label class="label_100">抹零前</label>
					<span id="molingBefore">${dinerBill.consumeCost-dinerBill.saveCost}</span>
				</div>
				<div class="moling">
					<label class="label_100">抹零金额</label>
					<input id="money" name="money" value="${dinerBill.molingModeCost}"  class="input_high" type="text" name="interest" value="" />
					<a id="moneyButton" class="but-jianpan-small"></a>
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
				<div class="moling" style="height:40px;margin:0px;color:#f00;font-size:13px;">
					<label></label>
					<div id="molingDesc">${molingDesc}</div>
				</div>
				<div class="moling">
					<label class="label_100">抹零后</label>
					<input id="molingAfter" name="molingAfter" value="${dinerBill.payableCost}" class="input_high" type="text" name="interest" value="" />
					<a id="molingAfterButton" class="but-jianpan-small"></a>
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
			</div>
			<div class="line_d"></div>
			<br/>
			<div class="but-area-s">
				<a class="small-but-queding mr_28" href="#" id="popSave"></a>
				<a class="small-but-quxiao" href="#" onclick="closebox();toastr.options.target='body';"></a>
			</div>
		</div>
	</div>
	<input type="hidden" id="permission_moling" value="<shiro:hasPermission name="frontdesk_bill_moling:create">1</shiro:hasPermission>">
</body>
</html>
