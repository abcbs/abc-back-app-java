<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>前台收银-登录</title>
<link rel="icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />  
<link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>

<script type="text/javascript" src="${ctx}/static/js/open/test.js"></script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.login_header_l,.login_header_r,.but_login,.login_jianpan,.small_delete,span,div,a');</script>
	<![endif]-->
<script type="text/javascript">
$(document).ready(function(){
        
});

</script>

</head>

<body id="body">

	<div class="login_wrap">
		<div class="">
			<div class="">
				<div class="login">
					<select id="functionName" name="functionName" onchange="document.getElementById('url').value=this.value;">
						<option value="/open/dishe/category_list" selected="selected">菜肴类别列表</option>
						<option value="/open/dishe/dish_list">菜肴信息列表</option>
					</select>
				</div>
				<div class="login">
					<input id="url" name="url" value="/open/dishe/category_list" type="text">
				</div>
				<div class="login">
					<textarea id="text" name="text" cols="100" rows="20">
					</textarea>
				</div>
				<div class=login_hint>
				</div>
				<div class="login">
					<a class="but_login" href="###" style="cursor:pointer;" onclick="test();"></a>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
