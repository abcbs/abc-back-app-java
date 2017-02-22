<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>前台收银</title>
<link href="${ctx}/static/css/error.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function(){
		initFrameSize();
		initMainNav();
	});
	/**
	 * 自适应浏览器宽度
	 */
	function initFrameSize()
	{
		var subWidth = window.screen.width - 1000 - 25;
		if(subWidth > 0)
		{
			$(".body").css("width",998+subWidth+"px");
		}
		
		$(".body").css("height",window.screen.height-12+"px");
	}
	
	
	function initMainNav()
	{
		var site_url = window.location.href.toLowerCase();	
		switch (true) {
		case site_url.indexOf("/self/zizhu") > 0 :
			window.location = '${ctx}/self/zizhu/index';
			break;	
		default :
		}
	}
</script>
</head>
<body>
	
	<!--页面主体begin -->
	<div id="noConnectDiv" class="body" style="background:#eee;">
	   <div class="bangding_yzh">
         <div class="yzh_title" style="text-align:center;"> 页面不存在！！</div>
         <div class="yzh_content">
           <div class="yzh_content_con" style="text-align:center;">
           
           </div>
         </div>
         <div class="yzh_button"><a href="${ctx}/logout" class="yzh_button_shuaxin"></a></div>
       </div>
	</div>
	<!--页面主体end -->
	
	
</body>
</html>