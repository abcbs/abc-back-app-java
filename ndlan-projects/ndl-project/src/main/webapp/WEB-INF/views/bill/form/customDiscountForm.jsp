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
<title>自定义折扣</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/bill/customDiscountForm.js"></script>
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
				<h3>自定义折扣</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<form id="customDiscountForm" action="${ctx}/bill/customDiscount/${billId}" method="post">
			<div class="small_popup_body_auto">
				<div class="zdycp_wrap">
					<div class="zdycp">
						<span class="left">任意折扣</span>
						<input id="discount" name="discount" class="input_high" type="text">
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
				</div>
				<div class="line_d"></div>
				<br/>
				<div class="but-area-s">
					<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
					<a class="small-but-quxiao"  style="cursor:pointer;" onclick="closebox();"></a>
				</div>
			</div>
			<input type="text" style="display:none">
		</form>
	</div>
</body>
</html>