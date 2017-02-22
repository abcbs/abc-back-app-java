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
<link href="${ctx}/static/css/keyboard_p.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/static/js/popKeyboard.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#popSelect").bind("click",popSelected);
	
	 var hotKey = new CanYinHotKeys();
		hotKey.init($("body"),[{
			keyCode:"enter",
			callBackFunction:function()
			{
				popSelected();
			}
		},
		{
			keyCode:"esc",
			callBackFunction:function()
			{
				closebox();
			}
		}
		],1);
		
		
		
});
function popSelected()
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
				closebox();
				permissionCallBack(isHasPermission,data.rel,data.sign);
			}
			else if(isHasPermission == '2')
			{
				toastr.error("您输入的用户没有此权限!");
			}
			
		}
	});
	
	
	
}

</script>
</head>
<body>
<form:form id="popSaveForm"  action="${ctx}/index/permission/get" method="post">
<input  id="functionType" name="functionType" type="hidden" value="${functionType}">
<input  id="functionId" name="functionId" type="hidden" value="${functionId}">

<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>获取临时权限</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body">
			<div class="small_popup_cont">
			<div class="ml_20 mt_30">
				<div class="font_16">
				您的权限不足，无法使用当前操作！<br/>
				请使用高权限的账户登录，或在下方输入高权限账户密码获得临时权限
				</div>
				<div class="font_16 ml_100 mt_20 left">
					<span class="left mr_10 mt_10">账户</span>
					<input class="input_high" id="username" name="username" type="text">
					<a id="moneyButton" class="but-jianpan" href="#" onclick="showPopKeyBoard(event,'username',this);"></a>
				</div>
				<div class="font_16 ml_100 mt_20 left">
					<span class="left mr_10 mt_10">密码</span>
					<input class="input_high" id="password" name="password" type="password">
					<a id="moneyButton" class="but-jianpan" href="#" onclick="showPopKeyBoard(event,'password',this);"></a>
				</div>
			</div>
				<div class="but-area-t">
					<a class="small-but-hdlsqx mr_28" style="cursor:pointer;" id="popSelect"></a>
					<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>
	
	
	
</form:form>



</body>
</html>