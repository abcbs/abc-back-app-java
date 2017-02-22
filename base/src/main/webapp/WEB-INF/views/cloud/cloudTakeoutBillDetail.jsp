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
<script type="text/javascript" src="${ctx}/static/js/cloud/cloudTakeoutBillDetail.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

	<input type="hidden" value="${userTakeout.billId}"  id="billId" name="billId"/>
	
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>外卖单审核</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kamingxi_wrap" style="margin-top:10px;">
              <div style="width:870px; text-align:center;margin-bottom:15px;color:#666;">审核通过的外卖单可在外卖模块中查看和修改</div>
              <div class="waimaidan" style="overflow-y:auto;">
				<div class="left p_waimaidanleft p_waimaidanlefts">
                  <table cellpadding="0" cellspacing="0">
                    <tbody>
                      <tr>
                        <td class="td" style="color:#F60">交易历史：</td>
                        <td style="color:#F60;">已有${userTakeout.placeTimes}次网络下单，已审核通过${userTakeout.passTimes}次</td>
                      </tr>
                      <tr>
                        <td class="td">云订单号：</td>
                        <td>${userTakeout.billNo}</td>
                      </tr>
                      <tr>
                        <td class="td">下单账号：</td>
                        <td>${userTakeout.userName}<span style="color:#00F">(${userTakeout.billFromDesc})</span></td>
                      </tr>
                      <tr>
                        <td class="td">下单时间：</td>
                        <td>${userTakeout.createTime}</td>
                      </tr>
                      <tr class="p_h_35">
                        <td class="td"><i class="i">*</i>联系人：</td>
                        <td><input name="orderPeople" id="orderPeople" class="input_long" type="text" value="${userTakeout.orderPeople}"></td>
                      </tr>
                      <tr class="p_h_35">
                        <td class="td">手机号码：</td>
                        <td><input name="mobile" id="mobile" class="input_long" type="text" value="${userTakeout.mobile}"></td>
                      </tr>
                      <tr class="p_h_35">
                        <td class="td">联系电话：</td>
                        <td><input name="linkmanTel" id="linkmanTel" class="input_long" type="text" value="${userTakeout.linkmanTel}"></td>
                      </tr>
                      <tr class="p_h_35">
                        <td class="td"><i class="i">*</i>送餐时间：</td>
                        <td>
                        	<input name="sendTime" id="sendTime" class="input_long" type="text" value="${userTakeout.sendTime}">
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
                        <td class="td">送餐费：</td>
                        <td><input name="totalCost" id="totalCost" class="input_long" type="text" value="${userTakeout.deliverCost}"></td>
                      </tr>
                      <tr class="p_h_55">
                        <td class="td" valign="top"><i class="i">*</i>送餐地址：</td>
                        <td>
                          <textarea name="sendAddress" id="sendAddress" style="font-size:12px;height:40px;width:187px;padding:5px;margin-top:3px;border:none;background:url(${ctx}/static/images/takeout/textarea.png) no-repeat;resize:none;">${userTakeout.sendAddress}</textarea>
                        </td>
                      </tr>
                       <c:if test="${userTakeout.invoiceType != '0' && userTakeout.invoiceType != '' && userTakeout.invoiceType != null}">
	                      <tr class="p_h_35">
	                        <td class="td">发票：</td>
	                        <td>
	                        	<input name="invoiceType" type="radio" value="1" <c:if test="${userTakeout.invoiceType eq '1'}">checked="checked"</c:if>> 个人 &nbsp;&nbsp;&nbsp;&nbsp;
	                        	<input name="invoiceType" type="radio" value="2" <c:if test="${userTakeout.invoiceType eq '2'}">checked="checked"</c:if>> 单位
	                        </td>
	                      </tr>
	                      <tr class="p_h_35">
	                        <td class="td"></td>
	                        <td><input name="invoiceTitle" id="invoiceTitle" class="input_long" type="text" value="${userTakeout.invoiceTitle}"></td>
	                      </tr>
                      </c:if>
                      <tr>
                        <td class="td">会员卡：</td>
                        <td>${userTakeout.cardNo}  ${userTakeout.cardClass}</td>
                      </tr>
                      <tr>
                        <td class="td">折扣方案：</td>
                        <td>${userTakeout.discountName}</td>
                      </tr>
                      <tr>
                        <td class="td">付款方式：</td>
                        <td>${userTakeout.paymentTypeDesc}</td>
                      </tr>
                      <tr>
                        <td class="td" valign="top">客户留言：</td>
                        <td>${userTakeout.customNote}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="left p_waimaidanright" id="takeoutDishContent" style="height:auto;">
                  	<div class="titles">
						<span>已点：${userTakeout.servings}份</span>
						<span>消费合计：${userTakeout.oriCostOrder}</span>
						<span>折扣优惠：<fmt:formatNumber value="${userTakeout.oriCostOrder-userTakeout.totalCost+userTakeout.deliverCost-userTakeout.molingModeCost}" pattern="#.##" type="number"/></span>
					</div>
					<div class="titles">
						<span>抹零金额：<fmt:formatNumber value="${userTakeout.molingModeCost}" pattern="#.##" type="number"/></span>
					</div>
			        <div class="titles">
				        <span>送餐费：${userTakeout.deliverCost}</span>
				        <span>应付金额：${userTakeout.totalCost}</span>
				        <span>
	                  		已支付：
	                  		<b class="red">
	                  			<c:choose>
	                  				<c:when test="${userTakeout.paymentType eq '3'}">
	                  					${userTakeout.totalCost}
	                  				</c:when>
	                  				<c:otherwise>
	                  					0
	                  				</c:otherwise>
	                  			</c:choose>
	                  		</b>
	                  	</span>
			        </div>
			        <table cellpadding="0" cellspacing="0" width="335" class="p_table">
			          <tbody>
			            <tr class="title">
			              <td width="150" class="td">菜肴名称</td>
			              <td width="110">数量</td>
			              <td width="80">金额</td>
			            </tr>
			            <c:forEach items="${dishes}" var="each" varStatus="status">
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
					              <td>${each.unitNum}${each.unitName }</td>
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
                </div>
              </div>
			  <div class="but-area-e" style="position: absolute;bottom:25px;left:188px;">
			  	<c:choose>
			  		<c:when test="${userTakeout.orderStatus == '1'}">
			  			<a class="but-shtg_xiadan" href="javascript:cloudBillReviewPass('${userTakeout.billId}','1','1');"></a>
						<a class="but-shtg_buxiadan" href="javascript:cloudBillReviewPass('${userTakeout.billId}','1','0');"></a>
						<a class="but-shbtg" href="javascript:cloudBillReviewFailed('${userTakeout.billId}','1');"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
			  		</c:when>
			  		<c:otherwise>
			  			<a class="but-shtg_xiadan but-shtg_xiadan_bukeyong" href="###"></a>
						<a class="but-shtg_buxiadan but-shtg_buxiadan_bukeyong" href="###"></a>
						<a class="but-shbtg but-shbtg_bukeyong" href="###"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
			  		</c:otherwise>
			  	</c:choose>
			  </div>
			</div>
		</div>
	</div>
	
</body>
</html>