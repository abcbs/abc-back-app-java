<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
		<div class="hy_nav">
				<ul id="orderListUl">
					<c:choose>
						<c:when  test="${searchMapParams.EQ_tableArea_areaId == null || searchMapParams.EQ_tableArea_areaId == ''}">
							<li><a class="quanbu_on" style="cursor:pointer;" href="###" >全部</a><span class="dot_on"></span></li>
						</c:when>
						<c:otherwise >	
							<li><a class="quanbu" style="cursor:pointer;"  href="###" onclick="changeTableArea(document.getElementById('kewWords').value,'search_EQ_tableArea.areaId=${membershipCardClass.mcclassId}','',1)">全部</a></li>
						</c:otherwise>
					</c:choose>
					
					<c:forEach items="${tableAreas}" var="tableArea" varStatus="status">
						<c:choose>
							<c:when  test="${searchMapParams.EQ_tableArea_areaId == tableArea.areaId}">
								<li><a style="cursor:pointer;"  href="###" class="qita_on">${tableArea.name}</a><span class="dot_on"></span></li>
							</c:when>
							<c:otherwise>	
								<li><a style="cursor:pointer;"  href="###" onclick="changeTableArea(document.getElementById('kewWords').value,'search_EQ_tableArea.areaId=${tableArea.areaId}','',1);" class="qita">${tableArea.name}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<a href="###" class="but_back" id="dish_left"></a>
				<a href="###" class="but_go" id="dish_right"></a>
			</div>
			<div class="hy_table_wrap">
				<table  class="huiyuan_table" id="orderTable">
				<thead>
					<tr>
						<th sort="createTime" dir="${direction}" width="120px;">预订单号<img src="${ctx}/static/images/huiyuan-yuding/dot_down.png"></th>
						<th>餐台</th>
						<th>预订人</th>
						<th>手机号</th>
						<th width="90px;">就餐人数</th>
						<th sort="orderTime" dir="${direction}">预订时间</th>
						<th>预付款</th>
						<th>预订方式</th>
						<th width="50px;">是否点餐</th>
						<th width="80px;">状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tableOrders.content}" var="tableOrder" varStatus="status">
						<tr orderId="${tableOrder.orderId}"  orderStatus="${tableOrder.orderStatus}">
							<td class="link_blue" onclick="popForm('预订信息','${ctx}/order/pop/order/update/${tableOrder.orderId}','880','644');">
								${tableOrder.orderNo}
							</td>
							<td>${tableOrder.tabNo}</td>
							<td>${tableOrder.orderPeopleName}</td>
							<td>${tableOrder.telphone}</td>
							<td>${tableOrder.peopleNum}</td>
							<td><fmt:formatDate value="${tableOrder.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${tableOrder.prepay}</td>
							<td>${tableOrder.orderWayDesc}</td>
							<td class="link_blue" onclick="window.location='${ctx}/bill/orderDiancai?orderId=${tableOrder.orderId}'">
							<c:choose>
								<c:when test="${tableOrder.orderBillDishes == null || fn:length(tableOrder.orderBillDishes) == 0}">
									否
								</c:when>
								<c:otherwise>
									是
								</c:otherwise>
							</c:choose>
							</td>
							<td>
							<c:if test="${tableOrder.orderStatus == 4}">
								<a class="table_but_3" style="cursor:pointer;" href="###" onclick="window.location='${ctx}/bill/list?billId=${tableOrder.billId}'">${tableOrder.orderStatusDesc}</a>
							</c:if>
							<c:if test="${tableOrder.orderStatus != 4}">
								${tableOrder.orderStatusDesc}
							</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="hy_sousuo">
				<div class="search_sec">
						<input name="sortCol" id="sortCol" type="hidden" value="${sortCol}">
						<input name="inputKewWords" id="inputKewWords" type="text" value="${kewWords}">
						<button onclick="refreshPageBtn();"></button>
					</div>
					<div class="right">
						<tags:paginationorder page="${tableOrders}" paginationSize="15"/>
					</div>
				</div>
				
				
				
