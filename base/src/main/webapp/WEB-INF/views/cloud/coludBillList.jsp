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
<title>云餐厅主页</title>
<link href="${ctx}/static/css/waimai.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" >

$(document).ready(function(){
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body").animate({width:998+subWidth},0);
		$(".list_l").animate({width:804+subWidth},0);
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body").css("height",668+subHeight+"px");
		$(".list_l").css("height",665+subHeight+"px");
		$(".list_r").css("height",665+subHeight+"px");
	}
	
	$("#foot_xiaoxi").removeAttr("onclick");
	setTimeout(function(){
		connectMixi();
	}, 300 );
	
});


function connectMixi()
{
	changeBody(ctx+'/cloud/cloudIndex');
}

function changeBody(url)
{
	$.ajax({
	    type:"get",
	    url:url,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(html){
	    	$("body").html(html);
	    },
		error:function(){
		}
	  });
}

</script>

	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.but_chedan,.but_dayin,.but_fanjiezhang,#keywordsbtn,.but_beizhu,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom');</script>
	<![endif]-->
</head>
<body>
	<!--页面主体begin -->
	<div id="noConnectDiv" class="body" style="background:#eee;">
	   <div class="bangding_yzh">
         <div class="yzh_title" style="text-align:center;">正在连接云餐厅</div>
         <div class="yzh_content">
           <div class="yzh_content_con" style="text-align:center;">
            请检查您的网络，保持网络畅通，否则无法使用xxx云服务！！
           </div>
         </div>
         <div class="yzh_button"><a href="javascript:connectMixi();" class="yzh_button_shuaxin"></a></div>
       </div>
	</div>
	<!--页面主体end -->


	
</body>
</html>