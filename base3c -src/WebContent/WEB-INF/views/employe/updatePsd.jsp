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
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>会员</title>
<link href="${ctx}/static/css/system.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/employe/updatePsd.js"></script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		if('${message}'!=''){
			//toastr.success('${message}');
		}
	});
</script>
</head>
<body>

<form id="psdForm" action="${ctx}/employe/ajax/psdUpdate" method="post">
<input type="hidden" name="id" value="${empId}">
<!--页面主体begin -->
	<div class="body_system">
		<div class="jjb_one">
			<div class="jjb_nav_wrap">
				<ul class="jjb_nav">
					<li><a class="but_jjb" onclick="window.location='${ctx}/employe/shift'" style="cursor:pointer;">交接班</a></li>
					<li><a class="but_xgmm_on">修改密码</a><span class="jjb_dot_on"></span></li>
				</ul>
			</div>
		</div>
		<div class="jjb_two"></div>
		<div class="jjb_three">
			<div class="xgmm">
				<h3 class="text_green">修改密码</h3>
				<div>
					<label>原始密码：</label>
					<input id="password" name="oldLoginPassword" class="input_150" type="password" rangelength="3,6">
				</div>
				<div>
					<label>新密码：</label>
					<input id="loginPassword" name="loginPassword" class="input_150" type="password" rangelength="3,6">
				</div>
				<div>
					<label>确认新密码：</label>
					<input id="confirmPassword" name="confirmPassword" class="input_150" type="password" equalTo="#password">
				</div>
			</div>
		</div>
		<div class="stystem_three">
			<div class="stystem_three_in">
				<p>
					<a class="but-queding mr_28" id="psdSubmit"  style="cursor:pointer;"></a>
				</p>
			</div>
		</div>
	</div>
	<!--页面主体end -->
</form>


</body>
</html>