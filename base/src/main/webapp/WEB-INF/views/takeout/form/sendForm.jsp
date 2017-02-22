<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/takeout/sendForm.js"></script> 
<title>派送</title>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<form id="senderForm" action="${ctx}/takeout/saveSender">
		<input id="tId" name="tId" value="${tId}" type="hidden">
		<div class="small_popup_wrap">
			<div class="small_popup_header">
				<div class="small_popup_header_l"></div>
				<div class="small_popup_header_c">
					<h3>派   送</h3>
				</div>
				<div class="small_popup_header_r"></div>
			</div>
			<div class="small_popup_body_auto">
				<div class="tuika_wrap" style="height:315px;width:430px;margin-left:50px;">
					<div class="tuika_other p_h_65 mt_30"><span class="left span">联系人：</span>${takeout.contactName}</div>
					<div class="tuika_other p_h_65"><span class="left span">联系人手机：</span>${takeout.mobile}</div>
					<div class="tuika_other p_h_65"><span class="left span">联系人电话：</span>${takeout.telephone}</div>
					<div class="tuika_other" style="width:430px;height:120px;overflow-y:auto;overflow-x:hidden;">
					<div style="float:left;width:105px;"><span class="left span">派送地址：</span></div>
					<div style="float:left;width:300px;font-size:12px;line-height:28px;">${takeout.sendAddress}</div>
					</div>
				</div>
				<div class="kaifapiao_wrap mt_10" style="height:70px;margin-left:55px;">
			      <div class="kaifapiao" style="width:400px;">
					<span style="width:90px;"><i class="text_red">*</i>派送员：</span>
					<div class="uboxstyle">
						<select name="senderBy" id="cpfl">
							<c:forEach var="employee" items="${employees.content}">
								<option value="${employee.empId}">${employee.name}</option>
							</c:forEach>
						</select>
					</div>
				  </div>
				</div>
				<div class="line_d" style="margin-bottom:30px;"></div>
				<div class="but-area-s">
					<a class="small-but-queding mr_28" id="buttonSend" href="#"></a>
					<a class="small-but-quxiao" href="#" onclick="closebox();"></a>
				</div>
			</div>	
		</div>
	</form>
</body>
</html>