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
<title>外卖单</title>
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
				<h3>外卖单</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kamingxi_wrap" style="margin-top:20px;">
              <div class="waimaidan">
				<div class="left p_waimaidanleft">
                  <table cellpadding="0" cellspacing="0">
                    <tbody>
                      <tr>
                        <td class="td">云订单号：</td>
                        <td>${takeout.onlineTakeoutNo}</td>
                      </tr>
                      <tr>
                        <td class="td">订单来源：</td>
                        <td>${takeout.billFromDesc}</td>
                      </tr>
                      <tr>
                        <td class="td">订单状态：</td>
                        <td>${takeout.dinerBill.billStatusDesc}</td>
                      </tr>
                      <tr>
                        <td class="td">联系人：</td>
                        <td>${takeout.contactName}</td>
                      </tr>
                      <tr>
                        <td class="td">手机号：</td>
                        <td>${takeout.mobile}</td>
                      </tr>
                      <tr>
                        <td class="td">电话号码：</td>
                        <td>${takeout.telephone}</td>
                      </tr>
                      <tr>
                        <td class="td">送餐时间：</td>
                        <td><fmt:formatDate value="${takeout.sendTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                      </tr>
                      <tr>
                        <td class="td">派送员：</td>
                        <td>${takeout.senderName}</td>
                      </tr>
                      <tr>
                        <td class="td">送餐费：</td>
                        <td>
                        	<fmt:formatNumber value="${takeout.deliverCost}" pattern="#.##" type="number"/>
                        	<c:if test="${takeout.deliverCost ne null && takeout.deliverCost ne ''}">
                        		元
                        	</c:if>
                        </td>
                      </tr>
                      <tr>
                        <td class="td" valign="top">送餐地址：</td>
                        <td>${takeout.sendAddress}</td>
                      </tr>
                      <tr>
                        <td class="td">发票：</td>
                        <td>${takeout.invoiceTitle}</td>
                      </tr>
                      <tr>
                        <td class="td">会员卡：</td>
                        <td>${takeout.dinerBill.membershipCard.cardNo}   ${takeout.dinerBill.membershipCard.membershipCardClass.name}</td>
                      </tr>
                      <tr>
                        <td class="td">折扣方案：</td>
                        <td>${takeout.dinerBill.discountName}</td>
                      </tr>
                      <tr>
                        <td class="td" valign="top">客户留言：</td>
                        <td>${takeout.customNote}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="left p_waimaidanright">
                  <div class="titles">
                  	<span>已点：${fn:length(takeout.dinerBill.dinerBillDishes)+fn:length(takeout.dinerBill.dinerBillDishesSets)} 份</span>
                  	<span>送餐费：<fmt:formatNumber value="${takeout.deliverCost}" pattern="#.##" type="number"/></span>
                  	<span>应付金额：<fmt:formatNumber value="${takeout.dinerBill.payableCost}" pattern="#.##" type="number"/></span>
                  </div>
                  <table cellpadding="0" cellspacing="0" width="350" class="p_table">
                    <tbody>
                      <tr class="title">
                        <td width="150" class="td">菜肴名称</td>
                        <td width="110">数量</td>
                        <td width="80">金额</td>
                      </tr>
                      <c:forEach var="dishes" items="${dinerBillDishes}">
                      	<tr class="<c:if test='${dishes.dishesStatus eq 3 || dishes.dishesStatus eq 4}'>p_tuicai</c:if>">
	                        <td class="td">${dishes.dishesName}</td>
	                        <td>${dishes.unitNum}${dishes.unitName}</td>
	                        <td><fmt:formatNumber value="${dishes.realCost}" pattern="0.00" type="number"/></td>
	                    </tr>
	                    <c:if test="${dishes.isSet eq 1}">
	                    	<c:forEach var="dsDishes" items="${dishes.dishesSetDishesList}">
		                      <tr class="zicaiyao <c:if test='${dishes.dishesStatus eq 3 || dishes.dishesStatus eq 4}'>p_tuicai</c:if>">
		                        <td class="td">${dsDishes.dishesName}</td>
		                        <td>${dsDishes.unitNum}${dsDishes.unitName}</td>
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
				<a class="but-queding mr_28" href="#" onclick="closebox();"></a>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>