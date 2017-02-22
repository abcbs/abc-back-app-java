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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>添加外卖</title>
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/date/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/static/js/takeout/takeoutForm.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<form id="takeoutForm" name="takeoutForm" action="${ctx}/takeout/saveTakeout" method="post">
		<input id="sendAtOnce" name="sendAtOnce" type="hidden">
		<input id="tId" name="tId" type="hidden" value="${takeout.tId}">
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>添加外卖</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kamingxi_wrap" style="margin-top:20px;">
				<div class="w_330 left ml_100 mt_20" style="height:360px;">
					<div class="ksfk_row_1 p_label">
						<label>联系人手机</label><input id="mobile" name="mobile" class="input_long" type="text" autocomplete="off" value="${takeout.mobile}" onblur="getCardByMobile();">
                        <div id="mobileListDiv" class="tanchukuang" hidden="true">
                          <ul id="mobileListUl">
                            
                          </ul>
                        </div>
					</div>
					<div class="ksfk_row_1 p_label">
						<label><i class="red">*</i>联系人</label><input id="contactName" name="contactName" class="input_long" value="${takeout.contactName}" type="text">
					</div>
					<div class="ksfk_row_1 p_label" style="height:64px;">
					   <label class="label" for="interest">立即派送	</label>
					   <a class="but_a_p<c:if test='${takeout.sendAtOnce eq 1 || takeout.sendAtOnce eq null && sendAtOnce ne null && sendAtOnce eq 1}'>_on</c:if>" value="1" onclick="setSendAtOnce(this);">是</a>
					   <a class="but_a_p<c:if test='${takeout.sendAtOnce eq 0 || takeout.sendAtOnce eq null && sendAtOnce ne null && sendAtOnce eq 0}'>_on</c:if>" value="0" onclick="setSendAtOnce(this);">否</a>	
                    </div>
					<div class="ksfk_row_2 p_label" style="min-width: 260px">
						<label>会员卡</label>
						<div class="uboxstyle">
							<select name="mcId" id="cardList" onchange="getDiscountName(this);">
								<option value=''>请选择</option>
								<c:forEach items="${membershipCards}" var="each">
	 									<option value="${each.mcId}" ${takeout.mcId eq each.mcId ? 'selected':''}>${each.cardNo}</option>   
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="ksfk_row_1 p_label" style="height:40px;">
						<label>发票</label>
						<input id="invoiceTitle" name="invoiceTitle" class="input_long" value="${takeout.invoiceTitle}" type="text">
					</div>
				</div>
				<div class="w_330 left mt_20" style="height:300px;">
					<div class="ksfk_row_1 p_label">
						<label>联系人电话</label><input id="telephone" name="telephone" value="${takeout.telephone}" class="input_long" type="text">
					</div>
					<div class="ksfk_row_1 p_label">
					    <label>送餐费</label><input id="deliverCost" name="deliverCost" value="<fmt:formatNumber value='${takeout.deliverCost}' pattern="#.##" type="number"/>" class="input_long" type="text">
					</div>
					<div class="ksfk_row_1 p_label">
						<label><i id="sendTime_red" class="red">*</i>送餐时间</label>
						<c:choose>
							<c:when test="${takeout.tId ne null && takeout.tId ne ''}">
								<input name="sendTime" id="sendTime" value="<fmt:formatDate value="${takeout.sendTime}" pattern="yyyy-MM-dd HH:mm"/>" class="input_long" class="Wdate"  type="text">
							</c:when>
							<c:otherwise>
								<input name="sendTime" id="sendTime" value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm"/>" class="input_long" class="Wdate"  type="text">
							</c:otherwise>
						</c:choose>
						
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
					<div class="ksfk_row_2 p_label" style="min-width: 260px">
						<label>折扣方案</label>
						<div class="uboxstyle">
							<select id="ccdId" name="ccdId">
								<option value=''>请选择</option>
							<c:forEach items="${cashDiscountAll.content}" var="each">   
								<option value="${each.ccdId}" ${ccdId eq each.ccdId ? 'selected':''}>${each.discountName}</option>   
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="left w_600 h_64" style="padding-left:118px;position:relative;height:64px;width:630px;">
					<label class="p_label_right"><i class="red">*</i>送餐地址</label>
					<input class="input-big" type="text" id="sendAddress" name="sendAddress" autocomplete="off" value="${takeout.sendAddress}" />
					<a class="but-qingkong_p" onclick="$('#sendAddress').val('');"/>
                    <br />
                    <div id="sendAddressListDiv" class="dizhi_tanchukuang" hidden="true">
                      <ul id="sendAddressListUl">
                      </ul>
                    </div>
				</div>
				<div class="left w_600 h_64" style="padding-left:130px;height:64px;">
					<label class="p_label_right">客户留言</label>
					<input class="input-big" type="text" id="customNote" name="customNote" value="${takeout.customNote}" />
				</div>
				<div class="but-area-e">
					<a class="but-queding mr_28" href="#" onclick="saveTakeout();"></a>
					<a class="but-quxiao" href="#" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>