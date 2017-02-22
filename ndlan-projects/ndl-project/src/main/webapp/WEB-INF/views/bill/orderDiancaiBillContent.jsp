<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="zhangdan_wrap">
		<input  type="hidden" id="orderStatus" value="${tableOrder.orderStatus}"/>
				<h3 class="zhangdan_title"></h3>
				<p class="zhangdan_info">
					<input  type="hidden" id="orderId" value=" ${tableOrder.orderId }"/>
					<input  type="hidden" id="orderStatus" value=" ${tableOrder.orderStatus }"/>
					<span class="left w_200">单号：${tableOrder.orderNo } </span>
					<span>桌号：${tableOrder.tabNo}</span>
					<span class="left w_200">时间：<fmt:formatDate  value="${tableOrder.orderTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /> </span>	
					<span id="billStatusDesc">
						<c:if test="${tableOrder.orderStatusDesc != null && tableOrder.orderStatusDesc != ''}">
						(${tableOrder.orderStatusDesc})
						</c:if>
					</span>
				</p>
				<table border="0" class="zhangdan" id="billList">
					<thead>
					<tr>
						<th width="30">序号</th>
						<th>菜名</th>
						<th width="80">单价</th>
						<th width="60">数量</th>
						<th width="30">金额</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${orderBillDishes}" var="orderBillDishe" varStatus="status">
						
						<tr id="${orderBillDishe.dishe.dishesId}" bdId="${orderBillDishe.bdId}" orderNum="${orderBillDishe.unitNum}" isSet="${orderBillDishe.isSet}" class="text_red" style="display:none;">
							<td id="seq">${status.index +1 }
							</td>
							<td id="dishesName">
								<c:if test="${fn:length(orderBillDishe.dishesName) > 6}"><a title="${orderBillDishe.dishesName}">${fn:substring(orderBillDishe.dishesName,0,4)}..</a></c:if>
								<c:if test="${fn:length(orderBillDishe.dishesName) <= 6}">${orderBillDishe.dishesName}</c:if>
								
								<c:if test="${orderBillDishe.isUserDefined == '1'}"><span class="text_blue">自</span></c:if>
								<c:if test="${orderBillDishe.isRulingPrice == '1' }"><span class="text_blue">时</span></c:if>
								<c:if test="${orderBillDishe.allNotes != null &&  orderBillDishe.allNotes != ''}"><br/></c:if>
								${orderBillDishe.allNotes}
							</td>
							<td id="price">
								<c:if test="${!orderBillDishe.priceDifferent}"><del><fmt:formatNumber value="${orderBillDishe.unitPrice}" type="currency" pattern="#.##"/>/</del></c:if>
								<fmt:formatNumber value="${orderBillDishe.realUnitPrice}" type="currency" pattern="#.##"/>
							</td>
							<td id="orderNum">
								<fmt:formatNumber value="${orderBillDishe.unitNum}" type="currency" pattern="#.##"/>
								${orderBillDishe.unitName}
							</td>
							<td id="total">
								<fmt:formatNumber value="${orderBillDishe.realCost}" type="currency" pattern="#.##"/>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="but_wrap">
					<div class="but_wrap_in">
						<div class="but_total_consume">
							<p class="text_green">菜品消费：<fmt:formatNumber value="${tableOrder.oriCost}" type="currency" pattern="#.##"/></p>
						</div>
						<div  style="cursor: pointer;" class="but_beizhu" id="beizhu">
						<c:choose>
							<c:when test="${tableOrder.allNotes eq null || fn:trim(tableOrder.allNotes) eq '' || fn:trim(tableOrder.allNotes) eq 'null'}">
							烹饪备注...
							</c:when>
							<c:otherwise>
							${tableOrder.allNotes}
							</c:otherwise>
						</c:choose>
						</div>
						<div class="yuding_hou_wrap">
							<a style="cursor: pointer;" class="but_qx_left" id="billPageBack"></a>
							<a style="cursor: pointer;" class="but_qx_right" id="billPageGo"></a>
							<a style="cursor: pointer;" class="big-but-queding" onclick="window.location='${ctx}/order'"></a>
						</div>
					</div>
				</div>
			</div>
			
			
	<div class="popup_zdmx" id="xiadanWindow" style="display: none;">
		<a class="but_cuicai mr_28" style="cursor:pointer;" id="dishCuicai"></a>
		<a class="but_huacai mr_28" style="cursor:pointer;" id="dishHuacai"></a>
		<a class="but_tuicai" style="cursor:pointer;" id="dishTuicai"></a>
	</div>	
	
	
	<div class="popup_zdmx_taller" id="newDishWindow" style="display: none;">
		<div class="popup_zdmx_taller_t">
			<a class="but_prbz mr_5 ml_15" style="cursor: pointer;" id="newDishBeizhu">烹饪备注...</a>
			<a class="but_prbz" style="cursor: pointer;" id="newDishDelete">删除</a>
		</div>
		<div class="popup_zdmx_tall_m">
			<h3>数量操作</h3>
			<div class="left w_250 ml_70 h_50">
				<a class="but_minus mr_10" style="cursor:pointer;" id="newDishSub"></a>
				<input class="but_blank mr_10" name="newDishNum" id="newDishNum" type="text" value="0">
				<input  name="oldDishNum" id="oldDishNum" type="hidden" value="0">
				<span>份</span>
				<a class="but_add"  style="cursor:pointer;" id="newDishAdd"></a>
			</div>
			<div class="small_jianpan_wrap">
				<div class="small_jianpan" id="sudoku">
					<a class="but_1" style="cursor:pointer;"></a>
					<a class="but_2" style="cursor:pointer;"></a>
					<a class="but_3" style="cursor:pointer;"></a>
					<a class="but_4" style="cursor:pointer;"></a>
					<a class="but_5" style="cursor:pointer;"></a>
					<a class="but_6" style="cursor:pointer;"></a>
					<a class="but_7" style="cursor:pointer;"></a>
					<a class="but_8" style="cursor:pointer;"></a>
					<a class="but_9" style="cursor:pointer;"></a>
					<a class="but_dot" style="cursor:pointer;"></a>
					<a class="but_0" style="cursor:pointer;"></a>
					<a class="but_del" style="cursor:pointer;"></a>
				</div>
			</div>
		</div>
		<div class="popup_zdmx_tall_b">
			<div class="">
				<a class="but-queding mr_12" style="cursor:pointer;" id="newDishConfirm"></a>
				<a class="but-quxiao" style="cursor:pointer;" id="newDishCancel"></a>
			</div>
		</div>
	</div>
	
	
