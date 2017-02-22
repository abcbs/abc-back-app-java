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
<script type="text/javascript" src="${ctx}/static/js/order/orderForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/order/${action}" method="post" >
		<input type="hidden" value="${tableOrder.orderId}"  id="id" name="id"/>
		<input type="hidden" value="${tableOrder.isEnterDiancaiPage}"  id="isEnterDiancaiPage" name="isEnterDiancaiPage"/>
		
<div class="popup_wrap" id="one">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>预订信息</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="popup_cont">
				<fieldset class="kt_yuding">
					<div class="left w_330 h_64">
						<label class="label_100" for="interest"><i class="red">*</i>预  订 人</label>
						<input class="input" type="hidden" name="mbId"  id="mbId" value="${tableOrder.mbId}" />
						<input class="input" type="text" name="orderPeopleName"  id="orderPeopleName" value="${tableOrder.orderPeopleName}" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('预订人','${ctx}/order/pop/member/select','880','644');"></a>
					</div>
					<div class="left w_330 h_64 relative z_99999">
						<label  class="label_100" for="interest"><i class="red">*</i>预订时间</label>
						<input class="input" type="text" value="${tableOrder.orderTime}" id="orderTime"  name="orderTimeStr"/>
						<a class="but-xuanze" style="cursor:pointer;" id="timeSelectBtn"></a>
						<div class="yuding_time_wrap" id="timeSelect">
							<div class="yuding_time">
								<p class="date" id="timeSelect_today">今天，2013年10月31号14:40</p>
								<div class="date_sort">
									<a style="cursor:pointer;" class="add" id="timeSelect_year_add"></a>
									<span id="timeSelect_year">2013年</span>
									<a style="cursor:pointer;" class="reduce" id="timeSelect_year_reduce"></a>
								</div>
								<div class="date_sort">
									<a style="cursor:pointer;" class="add" id="timeSelect_month_add"></a>
									<span id="timeSelect_month">05月</span>
									<a style="cursor:pointer;" class="reduce" id="timeSelect_month_reduce"></a>
								</div>
								<div class="date_sort">
									<a style="cursor:pointer;" class="add" id="timeSelect_day_add"></a>
									<span id="timeSelect_day">18日</span>
									<a style="cursor:pointer;" class="reduce" id="timeSelect_day_reduce"></a>
								</div>
								<div class="date_sort">
									<a style="cursor:pointer;" class="add" id="timeSelect_hour_add"></a>
									<span id="timeSelect_hour">16时</span>
									<a style="cursor:pointer;" class="reduce" id="timeSelect_hour_reduce"></a>
								</div>
								<div class="date_sort">
									<a style="cursor:pointer;" class="add" id="timeSelect_min_add"></a>
									<span id="timeSelect_min">35分</span>
									<a style="cursor:pointer;" class="reduce" id="timeSelect_min_reduce"></a>
								</div>
								<div class="left ml_135">
									<a class="but-queding mr_28" style="cursor:pointer;" id="timeSelect_ok"></a>
									<a class="but-quxiao" style="cursor:pointer;" id="timeSelect_cancel"></a>
								</div>
							</div>
						</div>	
					</div>
					
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest">就餐人数</label>
						
						<input class="input" type="text" id="peopleNum" name="peopleNum" maxlength="4" value="${tableOrder.peopleNum}"/>
						<a class="but-jianpan" style="cursor:pointer;" id="peopleNumSelect"></a>
						<div class="jianpan_wrap" id="sudoku">
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
								<a class="but_close" style="cursor:pointer;"></a>
								<a class="but_0" style="cursor:pointer;"></a>
								<a class="but_del" style="cursor:pointer;"></a>
							</div>
						</div>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest"><i class="red">*</i>联系电话</label>
						<input class="input" type="text" value="${tableOrder.telphone}"  id="telphone" name="telphone" onkeyup="getMemberByTel()" callBackFunction="getMemberByTel()"/>
						<a class="but-jianpan" style="cursor:pointer;" id="telphoneSelect"></a>
						<div class="jianpan_wrap" id="sudoku">
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
								<a class="but_close" style="cursor:pointer;"></a>
								<a class="but_0" style="cursor:pointer;"></a>
								<a class="but_del" style="cursor:pointer;"></a>
							</div>
						</div>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest"><i class="red">*</i>餐台</label>
						
						
						<input class="input" type="text" value="${tableOrder.tabNo}"  id="tabNo" name="tabNo" <c:if test="${action == 'update' }">disabled="disabled"</c:if>/>
						<input class="input" type="hidden" value="${tableOrder.table.tabId }"  id="tabId" name="table.tabId" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('选择餐台','${ctx}/index/pop/table/select?popTableType=2&from=yuding','880','644');"></a>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest">预订押金</label>
						<c:choose>
							<c:when test="${action eq 'update'}">
								<input class="input" type="text" value="${tableOrder.prepay}" disabled="disabled" id="prepay" name="prepay"/>
							</c:when>
							<c:otherwise>
								<input class="input" type="text" value="${tableOrder.prepay}"  id="prepay" name="prepay"/>
								<a class="but-jianpan" style="cursor:pointer;" id="prepaySelect"></a>
							</c:otherwise>
						</c:choose>
						<p class="tishi_2 text_blue">预订押金不予退还。</p>
						<div class="jianpan_wrap" id="sudoku">
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
								<a class="but_close" style="cursor:pointer;"></a>
								<a class="but_0" style="cursor:pointer;"></a>
								<a class="but_del" style="cursor:pointer;"></a>
							</div>
						</div>
					</div>
					
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">服务员</label>
						<input class="input" type="hidden" name="waiterId" id="waiterId" value="${tableOrder.waiter.empId}" />
						<input class="input" type="text" name="waiterName" id="waiterName" value="${tableOrder.waiter.name}" disabled="disabled"/>
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('服务员','${ctx}/index/pop/employee/select?type=3','880','644');"></a>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">营销员</label>
						<input class="input" type="hidden" name="salesmanId" id="salesmanId" value="${tableOrder.salesMg.empId}" />
						<input class="input" type="text" name="salesmanName" id="salesmanName" value="${tableOrder.salesMg.name}"  disabled="disabled"/>
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('营销员','${ctx}/index/pop/employee/select?type=5','880','644');"></a>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">预订方式</label>
						<c:forEach items="${orderWayList}" var="way">
							<input id="orderWay" name="orderWay" type="radio" value="${way.bciCode}" ${tableOrder.orderWay==way.bciCode?"checked":''}>${way.bciDesc}
						</c:forEach>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">付款方式</label>
						<c:forEach items="${paymentTypeList}" var="payment" varStatus="status">
							<c:choose>
								<c:when test="${action eq 'update'}">
									<form:radiobutton path="tableOrder.paymentType.cptId" value="${payment.cptId}" onclick="return false;"/>${payment.paymentName}
								</c:when>
								<c:otherwise>
									<form:radiobutton path="tableOrder.paymentType.cptId" value="${payment.cptId}" />${payment.paymentName}
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="left w_600 h_64">
						<label class="label_100" for="interest">备注</label>
						<input class="input-big" type="text" value="${tableOrder.notes}"  id="notes" name="notes"/>
					</div>
					<c:if test="${action == 'create'}">
						<div class="left w_600 h_64" id="isEnterDiancaiPageDiv">
							<label class="label_100" for="interest"> 进入点餐界面</label>
							<a class="but_a<c:if test="${tableOrder.isEnterDiancaiPage == 1}">_on</c:if> mr_28" value="1">是</a>
							<a class="but_a<c:if test="${tableOrder.isEnterDiancaiPage == 0}">_on</c:if>" value="0">否</a>			
						</div>
					</c:if>
					<div class="but-area-b">
						<a class="but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox(kuaijiejian);"></a>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</form>
</body>
</html>