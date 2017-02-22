<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>预订单详情</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/cloud/orderBillDetail.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>预订单详情</h3>
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
                        <td class="td">云订单号：</td>
                        <td>${userOrder.billNo}</td>
                      </tr>
                      <tr>
                        <td class="td">下单账号：</td>
                        <td>${userOrder.userName}<span style="color:#00F">（${userOrder.billFromDesc}）</span></td>
                      </tr>
                      <tr>
                        <td class="td">下单时间：</td>
                        <td>${userOrder.createTime}</td>
                      </tr>
                      <tr>
                        <td class="td">预订人：</td>
                        <td>
                        	<c:choose>
	                        	<c:when test="${userOrder.orderStatus eq '6'}">
		                        	${userOrder.orderPeople}
		                        	<c:choose>
		                        		<c:when test="${userOrder.gender eq '1'}">
		                        			男士
		                        		</c:when>
		                        		<c:when test="${userOrder.gender eq '0'}">
		                        			女士
		                        		</c:when>
		                        		<c:otherwise>
		                        		</c:otherwise>
		                        	</c:choose>
	                        	</c:when>
	                        	<c:otherwise>
		                        	${order.onlineOrderPeopleName}
		                        	<c:choose>
		                        		<c:when test="${onlineGender eq '1'}">
		                        			男士
		                        		</c:when>
		                        		<c:when test="${onlineGender eq '0'}">
		                        			女士
		                        		</c:when>
		                        		<c:otherwise>
		                        		</c:otherwise>
		                        	</c:choose>
	                        	</c:otherwise>
	                        </c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td class="td">手机号码：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userOrder.orderStatus eq '6'}">
                        			${userOrder.mobile}
                        		</c:when>
                        		<c:otherwise>
                        			${order.onlineMobile}
                        		</c:otherwise>
                        	</c:choose>
                        	
                        </td>
                      </tr>
                      <tr>
                        <td class="td">就餐时间：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userOrder.orderStatus eq '6'}">
                        			${userOrder.eatTime}
                        		</c:when>
                        		<c:otherwise>
                        			<fmt:formatDate value="${order.onlineDiningTime}" pattern="yyyy-MM-dd HH:mm"/>
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td class="td">就餐人数：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userOrder.orderStatus eq '6'}">
                        			${userOrder.peopleNum}人
                        		</c:when>
                        		<c:otherwise>
                        			${order.onlinePeopleNum}人
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td class="td">餐台分区：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userOrder.orderStatus eq '6'}">
                        			${userOrder.tableAreaName}
                        		</c:when>
                        		<c:otherwise>
                        			${order.onlineAreaName}
                        		</c:otherwise>
                        	</c:choose>
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
                        <td style="font-size:12px; line-height:20px;">${userOrder.customNote}</td>
                      </tr>
                      <tr>
                        <td class="td">审核人：</td>
                        <td>${userOrder.checkName}</td>
                      </tr>
                      <tr>
                        <td class="td">审核时间：</td>
                        <td>
                        	${userOrder.checkTime}
                        </td>
                      </tr>
                      <tr>
                        <td class="td">审核结果：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userOrder.orderStatus eq '6'}">
                        			审核未通过
                        		</c:when>
                        		<c:otherwise>
                        			审核通过
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td class="td"></td>
                        <td style="color:#666">${userOrder.failReasonDesc}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="left p_waimaidanright">
                  <div class="titles">
                  	<span>消费合计：<fmt:formatNumber value="${userOrder.oriCostOrder}" pattern="#.##" type="number"/></span>
                  	<span>折扣优惠：<fmt:formatNumber value="${userOrder.oriCostOrder-userOrder.realCostOrder}" pattern="#.##" type="number"/></span>
                  </div>
                  <div class="titles">
                  	<span>应付金额：${userOrder.realCostOrder}</span>
                  	<span>已支付：
                  		<b class="red">
                  			<c:choose>
                  				<c:when test="${userOrder.paymentType eq '3'}">
                  					<fmt:formatNumber value="${userOrder.realCostOrder}" pattern="#.##" type="number"/>
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
	                      <em class="v v02"></em><span style="width:75px;">第${dish.tabNum}桌</span><span style="width:150px;">已点${dish.servings}</span>
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
			  <div class="but-area-e" style="position: absolute;bottom:25px;left:410px;">
				<a class="but-queding mr_28" style="cursor:pointer;" onclick="closebox();"></a>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>