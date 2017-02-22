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
<script type="text/javascript" src="${ctx}/static/js/order/selectMemberForm.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>选择会员</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="line_x"></div>
			<div class="hykjz_wrap">
			
				<div class="hykjz_left" id="ajaxContent2">
				</div>
				
				<div class="hykjz_right">
					<div class="hykjz relative">
						<span>会员检索</span>
						<input id="memberSearch" name="memberSearch" class="input_high" type="text" onkeyup="changeMemberContent();" callBackFunction="changeMemberContent();" >
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
						<input id="selectMbId" type="hidden"/>
						<input id="selectTelphone" type="hidden"/>
						<span>已选会员</span><p id="selectMemberName"></p>
					</div>
					<div class="line_z"></div>
					<div class="but-area-d">
						<a class="but-queding mr_28" style="cursor:pointer;" id="selectMember"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="popBack(1);" id="popBack"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>