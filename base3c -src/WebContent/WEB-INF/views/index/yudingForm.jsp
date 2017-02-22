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
<script type="text/javascript" src="${ctx}/static/js/index/yudingForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
<form id="popSaveForm" action="${ctx}/index/yuding/${action}" method="post" >
		<input type="hidden" value="${tableOrder.orderId}"  id="id" name="id"/>
<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3 class="kt_yuding-title"></h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="popup_cont">
				<fieldset class="kt_yuding">
					<div class="left w_330 h_64">
						<label class="label" for="interest">预  订 人</label>
						<input class="input" type="hidden" name="memberId"  id="memberId" value="${tableOrder.memberId}" />
						<input class="input" type="text" name="orderPeople"  id="orderPeople" value="${tableOrder.orderId}" />
						<a class="but-xuanze" style="cursor:pointer;"></a>
					</div>
					<div class="left w_330 h_64 relative z_99999">
						<label  class="label" for="interest">预订时间</label>
						<input class="input" type="text" name="orderTime" id="orderTime" value="" />
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
						<label class="label" for="interest">就餐人数</label>
						<input class="input" type="text" name="peopleNum" value="${tableOrder.peopleNum}" id="peopleNum"/>
						<a class="but-jianpan" style="cursor:pointer;" id="sudokuSelect"></a>
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
						<label class="label" for="interest">联系电话</label>
						<input class="input" type="text" name="telphone" id="telphone" value="${tableOrder.telphone}" />
						<a class="but-xuanze" style="cursor:pointer;"></a>
					</div>
					<div class="left w_330 h_64">
						<label class="label" for="interest">餐        桌</label>
						<input class="input" type="text" name="interest" value="" />
						<a class="but-xuanze" style="cursor:pointer;"></a>
					</div>
					<div class="left w_330 h_64">
						<label class="label" for="interest">预订方式</label>
						<input name="orderWay" id="orderWay" type="radio" value="0">电话预订
						<input name="orderWay" id="orderWay" type="radio" value="1">到店预订
					</div>
					<div class="left w_330 h_64">
						<label class="label" for="interest">营  销  员</label>
						<input class="input" type="hidden" name="salesmanId" id="salesmanId" value="${dinerBill.salesmanId}" />
						<input class="input" type="text" name="salesmanName" id="salesmanName" value="${dinerBill.salesmanName}" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('营销员','${ctx}/index/pop/employee/select?type=2','880','644');"></a>
					</div>
					<div class="left w_330 h_64">
						<label class="label" for="interest">服  务  员</label>
						<input class="input" type="hidden" name="waiterId" id="waiterId" value="${dinerBill.waiterId}" />
						<input class="input" type="text" name="waiterName" id="waiterName" value="${dinerBill.waiterName}" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('服务员','${ctx}/index/pop/employee/select?type=1','880','644');"></a>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label" for="interest">预付款</label>
						<input class="input" type="text" name="prepay" id="prepay" value="${tableOrder.prepay}" />
						<a class="but-jianpan" style="cursor:pointer;" id="prepaySudokuSelect"></a>
						<p class="tishi text_blue">预付款不予退还。</p>
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
						<label class="label" for="interest">付款方式</label>
						<input name="" type="radio" value="">现金
						<input name="" type="radio" value="">银行卡
					</div>
					<div class="left w_600 h_64">
						<label class="label" for="interest">备注</label>
						<input class="input-big" type="text"  id="notes" name="notes" value="${tableOrder.notes}" />
					</div>
					<div class="left w_600 h_64">
						<label class="label" for="interest"></label>
						<input name="" type="checkbox" value="">确定后进入点餐界面
					</div>
					<div class="but-area-b">
						<a class="but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox(kuaijiejian);" ></a>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</form>
</body>
</html>