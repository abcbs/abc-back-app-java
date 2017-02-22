<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>自助选台</title>
<link href="${ctx}/static/css/self-desc.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom,#keywordsbtn');</script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		var width = $(window).width();
		if(width == 0)
		{
			width = $(document).width();
		}
		$(".diancan_body").css({marginLeft:((width-1024)/2)+"px"});
	});
	function saveAs(fileName,path){
		
	}
</script>
</head>
<body>

	<div class="diancan_body">
		<div class="diancan_header">
		<%
			if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){
		%>
				<h3 class="diancan_logo"></h3>
				<%
					}
				%>
			
		</div>
		<div class="diancan_text_area">
			<h3>让每一位有手机的顾客都能自己点餐</h3>
			<p>顾客自助点餐系统采用最为流行便利的轻应用模式，免下载，免安装，轻轻一扫桌面上的二维码即可进行点
餐操作，在保证安全的前提下提高了应用的便利性。该功能集成于餐饮管理系统之中<%
				if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){
			%>
				，永久免费
				<%
				}
			%>。</p>
<%
	if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){
%>
				<a href="http://www.xxx.cn/forum.php?mod=viewthread&tid=303&extra=page%3D1"><i>使用教程</i><span></span></a>
				<%
					}
				%>
			
			
		</div>
		<div class="ewm_area">
			<div class="ewm_title" style="position:relative;">
				<h3>餐桌二维码</h3>
				<h4>点击下载保存二维码图片到本地，供印刷使用</h4> 
				<a href="saveAllAs"  target="_parent" class="p_aload">下载全部</a>
			</div>
			<div class="ewm_wrap">
				<c:forEach items="${tableList}" var="table" varStatus="status">
					<div class="ewm">
						<img src="${ctx}/static/qrcode/${table.tabNo}.jpg" width="172px" height="172px">
						<div class="ewm_text">
							<p style="color:#fff;">桌号：${table.tabNo}  <a href="saveAs?fileName=${table.tabNo}.jpg" target="_parent">下载</a> </p>
							<p style="font-size:13px;">手机浏览器访问地址：${table.qrCodeImg}</p>
						</div>
					</div>
				</c:forEach>
				<div class="ewm">
					<img src="${ctx}/static/qrcode/c.jpg" width="172px" height="172px">
					<div class="ewm_text">
						<p style="color:#fff;">桌号：请扫描后选择 <a href="saveAs?fileName=c.jpg"  target="_parent">下载</a></p>
						<p style="font-size:13px;">手机浏览器访问地址：${allTableQr}</p>
					</div>
				</div>
			</div>
			<a class="top" style="display: none;"></a>
		</div>
		<%
			if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){
		%>
				<div class="diancan_footer">
			<p><a href="http://www.xxx.cn/">官方网站</a> <a href="http://www.xxx.cn/forum.php">论坛</a> | QQ群：294279815 310174475 | 北京智腾通达科技有限责任公司</p>
		</div>
				<%}%>
				
		
	</div>
</body>
</html>