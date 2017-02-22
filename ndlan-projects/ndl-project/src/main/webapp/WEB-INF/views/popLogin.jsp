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
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#popSelect").bind("click",submitLogin);
	
	 var hotKey = new CanYinHotKeys();
		hotKey.init($("body"),[
		{
			keyCode:"esc",
			callBackFunction:function()
			{
				window.location='${ctx}/logout';
			}
		}
		],1);
		
});
function popSelected(evt)
{
	if(evt.keyCode == 13)
	{
		submitLogin();
	}
	else if(evt.keyCode == 27)
	{
		window.location='${ctx}/logout';
	}
	
}


function submitLogin()
{
	var url = $("#popSaveForm").attr("action");
	jQuery.ajax({
		url: url,
		data: $('#popSaveForm').serialize(),
		type: "POST",
		dataType: "json",
		beforeSend: function()
		{  
		},
		success: function(data)
		{
			var isHasPermission = data.message;
			if(isHasPermission == '0')
			{
				//用户名密码错误
				toastr.error("用户名或者密码输入错误!");
			}
			else if(isHasPermission == '1')
			{
				//正确
				pageNoAction();
				closebox();
			}
			else if(isHasPermission == '2')
			{
				toastr.error("不是当前用户，请退出重新登录");
			}
			
		}
	});
}

</script>
</head>
<body>
<form:form id="popSaveForm"  action="${ctx}/index/poplogin" method="post">
<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>登录</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
			<div class="ml_20 mt_30">
				<div class="font_16">
				长时间未登录，请重新输入用户名密码后登录<br/>
				</div>
				<div class="font_16 ml_100 mt_20 left">
					<span class="left mr_10 mt_10">账户</span>
					<input class="input_high" id="username" name="username" type="text" value="" onkeydown="popSelected(event);">
				</div>
				<div class="font_16 ml_100 mt_20 left">
					<span class="left mr_10 mt_10">密码</span>
					<input class="input_high" id="password" name="password" type="password" value=""  onkeydown="popSelected(event);">
				</div>
			</div>
				<div class="but-area-t">
					<a class="but-queding mr_28" style="cursor:pointer;" id="popSelect"></a>
					<a class="but-quxiao" style="cursor:pointer;" onclick="window.location='${ctx}/logout'"></a>
				</div>
			</div>
		</div>
	</div>
	
	
</form:form>
</body>
</html>