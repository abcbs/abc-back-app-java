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
<title>外卖单详情</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
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
				<h3>外卖单详情</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kamingxi_wrap" style="margin-top:10px;">
              <div style="width:870px; text-align:center;margin-bottom:15px;color:#666;">审核通过的外卖单可在外卖模块中查看和修改</div>
              <div class="waimaidan">
				<div class="left p_waimaidanleft">
                  <table cellpadding="0" cellspacing="0">
                    <tbody>
                      <tr>
                        <td class="td">云订单号：</td>
                        <td>${userTakeout.billNo}</td>
                      </tr>
                      <tr>
                        <td class="td">下单帐号：</td>
                        <td>${userTakeout.userName}<span style="color:#00F">(${userTakeout.billFromDesc})</span></td>
                      </tr>
                      <tr>
                        <td class="td">下单时间：</td>
                        <td>${userTakeout.createTime}</td>
                      </tr>
                      <tr>
                        <td class="td">联系人：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userTakeout.orderStatus eq '3'}">
                        			${userTakeout.orderPeople}
                        		</c:when>
                        		<c:otherwise>
                        			${takeout.contactName}
                        		</c:otherwise>
                        	</c:choose>
                        	
                        </td>
                      </tr>
                      <tr>
                        <td class="td">手机号码：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userTakeout.orderStatus eq '3'}">
                        			${userTakeout.mobile}
                        		</c:when>
                        		<c:otherwise>
                        			${takeout.mobile}
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td class="td">联系电话：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userTakeout.orderStatus eq '3'}">
                        			${userTakeout.linkmanTel}
                        		</c:when>
                        		<c:otherwise>
                        			${takeout.telephone}
                        		</c:otherwise>
                        	</c:choose>
                        	
                        </td>
                      </tr>
                      <tr>
                        <td class="td">送餐时间：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userTakeout.orderStatus eq '3'}">
                        			${userTakeout.sendTime}
                        		</c:when>
                        		<c:otherwise>
                        			<fmt:formatDate value="${takeout.sendTime}" pattern="yyyy-MM-dd HH:mm"/>
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                      </tr>
                      <!--<tr>
                        <td class="td">派送员：</td>
                        <td>${takeout.senderName}</td>
                      </tr>
                      <tr>
                        <td class="td">送餐费：</td>
                        <td>${takeout.deliverCost}</td>
                      </tr>-->
                      <tr>
                        <td class="td" valign="top">送餐地址：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userTakeout.orderStatus eq '3'}">
                        			${userTakeout.sendAddress}
                        		</c:when>
                        		<c:otherwise>
                        			${takeout.sendAddress}
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td class="td">发票：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userTakeout.orderStatus eq '3'}">
                        			${userTakeout.invoiceTitle}
                        		</c:when>
                        		<c:otherwise>
                        			${takeout.invoiceTitle}
                        		</c:otherwise>
                        	</c:choose>
                        	
                        </td>
                      </tr>
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
                      <tr>
                        <td class="td">审核人：</td>
                        <td>
                        	${userTakeout.checkName}
                        </td>
                      </tr>
                      <tr>
                        <td class="td">审核时间：</td>
                        <td>${userTakeout.checkTime}</td>
                      </tr>
                      <tr>
                        <td class="td">审核结果：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${userTakeout.orderStatus eq '3'}">
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
                        <td style="color:#666">${userTakeout.failReasonDesc}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="left p_waimaidanright" id="takeoutDishContent" style="height:auto;">
                  	<div class="titles">
						<span>已点：${userTakeout.servings}份</span>
						<span>消费合计：${userTakeout.totalCost}</span>
						<span>折扣优惠：<fmt:formatNumber value="${userTakeout.oriCostOrder-userTakeout.realCostOrder}" pattern="#.##" type="number"/></span>
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
			  <div class="but-area-e" style="position: absolute;bottom:25px;left:410px;">
				<a class="but-queding mr_28" style="cursor:pointer;" onclick="closebox();"></a>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>