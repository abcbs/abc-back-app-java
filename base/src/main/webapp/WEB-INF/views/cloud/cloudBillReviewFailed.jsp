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
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/cloud/cloudBillReviewFailed.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popReviewForm" action="${ctx}/cloud/cloudBillReviewFailed/update" method="post" >
	<input type="hidden" value="${billId}"  id="billId" name="billId"/>
	<input type="hidden" value="${takeoutOrderDiv}"  id="takeoutOrderDiv" name="takeoutOrderDiv"/>
	<input type="hidden" value=""  name="failReasonDesc" id="failReasonDesc"/>
	<div class="small_popup_wrap"  style="margin-left: 170px;">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3 class="tuicai-title">审核不通过原因</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="tuicai_box" style="height:480px;">
				<h2>请选择或者填写您不通过的原因！</h2>
				<div class="tuicai_wrap">
					<c:forEach items="${speOpReasons}" var="speOpReason" varStatus="status">
						<a href="###" style="cursor:pointer;" name="tuicai" class="tuicai" id="${speOpReason.reaId}"><span><em>${speOpReason.name}</em></span></a>
					</c:forEach>
				</div>
                <div style="clear:both;"></div>
                <div class="yuanyin_beizhu">
					<div class="beizhu_biao">其它原因</div>
					<textarea id="notes" name="notes" style="height:40px;width:370px;margin-top:3px;background:url(${ctx}/static/images/takeout/textarea1.png) no-repeat;border:none;resize:none;padding:5px;" onfocus="focusNotes();"></textarea>
                </div>
                <div class="beizhu_font">
                  <p>执行删除和修改等操作，请到后台管理-》特殊操作原因模块</p>
                  <p>顾客支付的预付款将会按照其支付方式退回。</p>
                </div>
			</div>
			<div class="line_d"></div>
			<div class="but-area-s">
				<a id="popSave" class="small-but-queding mr_28" href="javascript:submitReviewFailedForm();"></a>
				<a class="small-but-quxiao" style="cursor:pointer;" onclick="popBack(1);" id="popBack"></a>
			</div>
		</div>
	</div>
	
</form>
</body>
</html>