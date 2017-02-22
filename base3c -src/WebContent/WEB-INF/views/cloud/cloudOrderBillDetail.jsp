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
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/cloud/cloudOrderBillDetail.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
<form id="popCloudOrderForm" action="${ctx}/index/bingtai/update" method="post" >
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>预订单审核</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kamingxi_wrap" style="margin-top:10px;">
              <div style="width:870px; text-align:center;margin-bottom:15px;color:#666;">审核通过的预订单可在预订模块中查看和修改</div>
              <div class="waimaidan">
				<div class="left p_waimaidanleft p_waimaidanlefts">
                  <table cellpadding="0" cellspacing="0">
                    <tbody>
                      <tr>
                        <td class="td" style="color:#F60">交易历史：</td>
                        <td style="color:#F60;">已有${userOrder.placeTimes}次网络下单，已审核通过${userOrder.passTimes}次</td>
                      </tr>
                      <tr>
                        <td class="td">云订单号：</td>
                        <td>${userOrder.billNo}</td>
                      </tr>
                      <tr>
                        <td class="td">下单账号：</td>
                        <td>${userOrder.userName}<span style="color:#00F">(${userOrder.billFromDesc})</span></td>
                      </tr>
                      <tr>
                        <td class="td">下单时间：</td>
                        <td>${userOrder.createTime}</td>
                      </tr>
                      <tr class="p_h_35">
                        <td class="td"><i class="i">*</i>预订人：</td>
                        <td><input name="orderPeople" id="orderPeople" value="${userOrder.orderPeople}" class="input_long_p" type="text"> &nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="gender" id="gender_1" type="radio" value="1" <c:if test="${userOrder.gender eq '1'}">checked="checked"</c:if>> 男 &nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="gender" id="gender_2" type="radio" value="0" <c:if test="${userOrder.gender eq '0'}">checked="checked"</c:if>> 女</td>
                        
                        
                      </tr>
                      <tr class="p_h_35">
                        <td class="td"><i class="i">*</i>手机号码：</td>
                        <td><input name="mobile" id="mobile" class="input_long" type="text" value="${userOrder.mobile}"></td>
                      </tr>
                      <tr class="p_h_35">
                        <td class="td"><i class="i">*</i>就餐时间：</td>
                        <td>
                        	<input name="eatTime" id="eatTime" class="input_long" type="text" value="${userOrder.eatTime}">
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
                        </td>
                      </tr>
                      <tr class="p_h_35">
                        <td class="td"><i class="i">*</i>就餐人数：</td>
                        <td><input name="peopleNum" id="peopleNum" class="input_long" type="text" value="${userOrder.peopleNum}" style="margin-top:2px;">&nbsp;&nbsp;&nbsp;人</td>
                      </tr>
                      <tr class="p_h_55">
                        <td class="td">餐台分区：</td>
                        <td style="height:60px;">
							<div class="uboxstyle">
							<select name="tableAreaId" id="tableAreaId">
									<option value="">请选择</option>
								<c:forEach items="${tableAreas}" var="tableArea" varStatus="status">
									<option value="${tableArea.areaId}" <c:if test="${tableArea.areaId == userOrder.tableAreaId}">selected="selected"</c:if>>${tableArea.name}</option>
								</c:forEach>
							</select>
							</div>
                        </td>
                      </tr>
                      <tr>
                        <td class="td">会员卡：</td>
                        <td>${userOrder.cardNo}   ${userOrder.cardClass}</td>
                      </tr>
                      <tr>
                        <td class="td">折扣方案：</td>
                        <td>${userOrder.discountName}</td>
                      </tr>
                      <tr>
                        <td class="td">付款方式：</td>
                        <td>${userOrder.paymentTypeDesc}</td>
                      </tr>
                      <tr>
                        <td class="td" valign="top">客户留言：</td>
                        <td>${userOrder.customNote}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="left p_waimaidanright">
                  <div class="titles">
                  	<span>消费合计：<fmt:formatNumber value="${userOrder.oriCostOrder}" pattern="#.##" type="number"/></span>
                  	<span>折扣优惠：<fmt:formatNumber value="${userOrder.oriCostOrder-userOrder.realCostOrder-userOrder.molingModeCost}" pattern="#.##" type="number"/></span>
                  	<span>抹零金额：<fmt:formatNumber value="${userOrder.molingModeCost}" pattern="#.##" type="number"/></span>
                  </div>
                  <div class="titles">
                  	<span>应付金额：${userOrder.realCostOrder}</span>
                  	<span>
                  		已支付：
                  		<b class="red">
                  			<c:choose>
                  				<c:when test="${userOrder.paymentType eq '3'}">
                  					${userOrder.realCostOrder}
                  				</c:when>
                  				<c:otherwise>
                  					0
                  				</c:otherwise>
                  			</c:choose>
                  		</b>
                  	</span>
                  </div>
                 
                 <c:forEach items="${dishes}" var="dish" varStatus="status">
	                  <div class="wmd_con">
	                    <h3 class="h_2 vtitle">
	                      <em class="v v02"></em><span style="width:75px;"></span><span style="width:150px;">已点${dish.servings}</span>
	                      <span>
	                      	<a href="###" onclick="cloudSelectTab('${ctx}','${status.index}','${userOrder.tableAreaId}',this)" id="selectTabNo_${status.index}">选择餐台</a>
	                      	<input type="hidden" value=""  id="selectTabId_${status.index}" name="selectTabId_${status.index}"/>
	                      </span>
	                    </h3>
	                    
	                    <div class="vcon"  <c:if test="${status.index > 0}">style="display: none;"</c:if>>
	                      <table cellpadding="0" cellspacing="0" width="335" class="p_table">
	                        <tbody>
	                          <tr class="title">
	                            <td width="150" class="td">菜肴名称</td>
	                            <td width="110">数量</td>
	                            <td width="80">金额</td>
	                          </tr>
	                          <c:forEach items="${dish.dishMap}" var="each" varStatus="status">
		                         <c:if test="${each.dishSetDiv == '1'}">
				           			 <tr>
						              <td class="td">${each.dishesName}</td>
						              <td>${each.unitNum}${each.unitName }</td>
						              <td>${each.realCost}</td>
						            </tr>
				           		</c:if>
				           		<c:if test="${each.dishSetDiv == '2'}">
				           			<tr>
						              <td class="td">${each.dishesName}</td>
						              <td>${each.unitNum}${each.unitName}</td>
						              <td>${each.realCost}</td>
						            </tr>
						            <c:forEach items="${each.dsDishesList}" var="eachDishes" varStatus="status">
					            	<tr class="zicaiyao">
					              		<td class="td">${eachDishes.dishesName}</td>
					              		<td>${eachDishes.unitNum}${eachDishes.unitName}</td>
					              		<td></td>
					            	</tr>
					            	</c:forEach>
				           		</c:if>
	                          </c:forEach>
	                        </tbody>
	                      </table>
	                      <div style="color:#999;">烹饪备注：${dish.note}</div>
	                      <div class="titles" style="color:#F60;">
	                      	<span>消费合计：${dish.oriCostBill}</span>
	                      	<span>折扣优惠：<fmt:formatNumber value="${dish.oriCostBill-dish.realCostBill}" pattern="#.##" type="number"/></span>
	                      	<span>应付金额：${dish.realCostBill}</span>
	                      </div>
	                    </div>
	                  </div>
                  </c:forEach>
                  
                </div>
              </div>
			  <div class="but-area-e" style="position: absolute;bottom:25px;left:278px;">
			  	<c:if test="${userOrder.orderStatus == '1'}">
					<a class="but-shtg" href="javascript:cloudBillReviewPass('${userOrder.billId}','2');"></a>
					<a class="but-shbtg" href="javascript:cloudBillReviewFailed('${userOrder.billId}','2');"></a>
					<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
				</c:if>
				<c:if test="${userOrder.orderStatus != '1'}">
					<a class="but-shtg but-shtg_bukeyong"></a>
					<a class="but-shbtg but-shbtg_bukeyong"></a>
					<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
				</c:if>
				
			  </div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>