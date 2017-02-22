<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#popSelect").bind("click",popSelected);
	
	$("a",".hykjz_infor").click(function(){
		var a = $(this);
		var mcId= a.attr("mcId");
		var cardNo= a.attr("cardNo");
		
		var rj = {"mcId":mcId,"cardNo":cardNo};
		popSelectCallBack(rj);
	});
});


function popSelected()
{
	if(currentPopTable)
	{
		var tabId = currentPopTable.attr("id");
		var tabNo = currentPopTable.attr("tabNo");
		var rj = {"tabId":tabId,"tabNo":tabNo};
		popSelectCallBack(rj);
	}
}

</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/member/jianka/${action}" method="post" >
<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>会员卡选择</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="line_x"></div>
			<div class="hykjz_wrap">
				<div class="hykjz_left">
					<ul class="hykjz_infor">
						<c:forEach items="${membershipCards.content}" var="membershipCard" varStatus="mstatus">
							<li>
								<a style="cursor:pointer;" mcId="${membershipCard.mcId}" cardNo="${membershipCard.cardNo}" >
									<span class="w_75">${membershipCard.cardNo} </span>
									<span class="w_100">${membershipCard.cardNo}</span>
									<span class="w_200"></span>
								</a>
							</li>
						</c:forEach>
						
					</ul>
				</div>
				<div class="hykjz_right">
					<div class="hykjz">
						<span>会员卡检索</span><input name="" class="input_high" type="text">
					</div>
					<div class="hykjz">
						<span>金额</span><input name="" class="input_high" type="text">
					</div>
					<div class="hykjz_jianpan">
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
							<a class="but_del" style="cursor:pointer;"></a>
							<a class="but_0" style="cursor:pointer;"></a>
							<a class="but_queding" style="cursor:pointer;"></a>
						</div>
					</div>
					<div class="line_z"></div>
					<div class="hykjz">
						<span>已选会员</span><p>上岛咖啡竭斯底里金粉世家发</p>
					</div>
					<div class="hykjz">
						<span>会员卡秘密</span>
						<input name=""  class="input_high" type="password">					
					</div>
					<div class="line_z"></div>
					<div class="but-area-d">
						<a class="but-queding mr_28" style="cursor:pointer;" id="popSelect"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="popBack(1);"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>