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
<script type="text/javascript" src="${ctx}/static/js/cloud/cloudBillReviewPass.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popReviewForm" action="${ctx}/cloud/cloudBillReviewPass/update" method="post" >
	<input type="hidden" value="${billId}"  id="billId" name="billId"/>
	<input type="hidden" value="${takeoutOrderDiv}"  id="takeoutOrderDiv" name="takeoutOrderDiv"/>
	<input type="hidden" value="${tabIds}"  id="tabIds" name="tabIds"/>
	<input type="hidden" value="${isXiadan}"  id="isXiadan" name="isXiadan"/>
	
	<input type="hidden" value="${orderPeople}"  id="orderPeople" name="orderPeople"/>
	<input type="hidden" value="${mobile}"  id="mobile" name="mobile"/>
	<input type="hidden" value="${linkmanTel}"  id="linkmanTel" name="linkmanTel"/>
	<input type="hidden" value="${sendTime}"  id="sendTime" name="sendTime"/>
	<input type="hidden" value="${totalCost}"  id="totalCost" name="totalCost"/>
	<input type="hidden" value="${sendAddress}"  id="sendAddress" name="sendAddress"/>
	
	<input type="hidden" value="${eatTime}"  id="eatTime" name="eatTime"/>
	<input type="hidden" value="${peopleNum}"  id="peopleNum" name="peopleNum"/>
	<input type="hidden" value="${gender}"  id="gender" name="gender"/>
	<input type="hidden" value="${invoiceType}"  id="invoiceType" name="invoiceType"/>
	<input type="hidden" value="${invoiceTitle}"  id="invoiceTitle" name="invoiceTitle"/>
	<input type="hidden"  id="isJudgeRm" name="isJudgeRm"/>
	
	<div class="small_popup_wrap" style="margin-left: 170px;margin-top: 150px;">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3><c:if test="${takeoutOrderDiv == '1'}">外卖</c:if><c:if test="${takeoutOrderDiv == '2'}">预订</c:if>审核通过确认</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="tuicai_box" style="height:240px;">
				<h2>
					您确定审核通过此来自网络的
					<c:if test="${takeoutOrderDiv == '1'}">
						外卖
					</c:if>
					<c:if test="${takeoutOrderDiv == '2'}">
						预订
					</c:if>
					单吗？
				</h2>
                <div class="yuanyin_beizhu">
					<div class="beizhu_biao">审核备注</div><textarea id="remarks" name="reviewPassNotes" style="height:40px;width:370px;margin-top:3px;background:url(${ctx}/static/images/takeout/textarea1.png) no-repeat;border:none;resize:none;padding:5px;"></textarea>
                </div>
                <div class="beizhu_font">
                  <p>
                  	请仔细审核
                  	<c:if test="${takeoutOrderDiv == '1'}">
                  		外卖
                  	</c:if>
                  	<c:if test="${takeoutOrderDiv == '2'}">
                  		预订
                  	</c:if>
                  	信息！
                  </p>
                  <p>
                  	审核通过的
                  	<c:if test="${takeoutOrderDiv == '1'}">
                  		外卖
                  	</c:if>
                  	<c:if test="${takeoutOrderDiv == '2'}">
                  		预订
                  	</c:if>
                  	单信息可在
                  	<c:if test="${takeoutOrderDiv == '1'}">
                  		外卖
                  	</c:if>
                  	<c:if test="${takeoutOrderDiv == '2'}">
                  		预订
                  	</c:if>
                  	模块中修改和下单。
                  </p>
                </div>
			</div>
			<div class="line_d"></div>
			<div class="but-area-s">
				<a id="popSave" class="small-but-queding mr_28" href="javascript:submitReviewPassForm(0);"></a>
				<a class="small-but-quxiao" style="cursor:pointer;" onclick="popBack(1);" id="popBack"></a>
			</div>
		</div>
	</div>
	
</form>
</body>
</html>