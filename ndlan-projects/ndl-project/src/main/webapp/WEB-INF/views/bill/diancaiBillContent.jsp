<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="zhangdan_wrap">
				<h3 class="zhangdan_title"></h3>
				<p class="zhangdan_info">
					<input  type="hidden" id="billId" value="${newBill.billId}"/>
					<input  type="hidden" id="billStatus" value="${newBill.billStatus}"/>
					<input  type="hidden" id="billType" value="${newBill.billType}"/>
					
					<input  type="hidden" id="tableConsumeLow" value="${newBill.tableConsumeLow}"/>
					<input  type="hidden" id="oriCost" value="${newBill.oriCost}"/>
					<input  type="hidden" id="billPlaceEnterDeskOrPay" value="${billPlaceEnterDeskOrPay}"/>
					<input  type="hidden" id="isShowPlaceBillConfirmWindow" value="${isShowPlaceBillConfirmWindow}"/>
					<input  type="hidden" id="isSet" value=""/>
					<input  type="hidden" id="isCanPlace" value="${isCanPlace}"/>
					<input  type="hidden" id=createEmployeeName value="${newBill.createEmployee.name}"/>
					
					<input type="hidden" id="consumeLow" value=""/>
					<span class="left w_200" id="diancaiBillContentBillNo">
						单号:${newBill.billNo}
						<c:if test="${newBill.billStatusDesc != null && newBill.billStatusDesc != ''}">
							(<label id="billStatusDesc">${newBill.billStatusDesc}</label>)
						</c:if>
					</span>
					<span>餐台:
						<c:if test="${fn:length(newBill.table.tabName) > 9}"><a title="${newBill.table.tabName}">${fn:substring(newBill.table.tabName,0,7)}..</a></c:if>
						<c:if test="${fn:length(newBill.table.tabName) <= 9}">${newBill.table.tabName}</c:if>
						<c:if test="${newBill.billType == '1' }"></c:if>
						<c:if test="${newBill.billType == '2' }">外卖</c:if>
						<c:if test="${newBill.billType == '3' }">自助点餐</c:if>
						<c:if test="${newBill.billType == '4' }">快餐</c:if>
					</span>
					<span class="left w_200">时间:
					<fmt:formatDate  value="${newBill.billTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /> </span>	
					<span id="consumeLowDesc">
						<c:choose>
							<c:when test="${newBill.tableConsumeLow > 0}">
								<span id="consumeLow" class="text_red">最低消费:${newBill.tableConsumeLow}</span>
							</c:when>
							<c:otherwise>
								<span  class="hideTooLongChar">开单人:${newBill.createEmployee.name}</span>
							</c:otherwise>
						</c:choose>
					</span>
				</p>
				<table border="0" class="zhangdan" id="billList">
					<thead>
					<tr>
						<th width="30">序号</th>
						<th width="120">菜名</th>
						<th width="60">单价</th>
						<th width="70">数量</th>
						<th width="60">金额</th>
					</tr>
					</thead>
					<tbody id="diancaiBillContentDishesTr">
					<c:forEach items="${dinerBillDishes}" var="dinerBillDishe" varStatus="status">
						<tr 
						id="${dinerBillDishe.dishesId}" 
						bdId="${dinerBillDishe.bdId}" 
						isSet="${dinerBillDishe.isSet}" 
						dsId="${dinerBillDishe.dsId}" 
						dishesName="${dinerBillDishe.dishesName}" 
						discountType="${dinerBillDishe.discountType}" 
						dishesStatus="${dinerBillDishe.dishesStatus}" 
						notes="${dinerBillDishe.allNotes}" 
						unitName="${dinerBillDishe.unitName}"
						unitType="${dinerBillDishe.unitType}"
						orderNum="<fmt:formatNumber value="${dinerBillDishe.unitNum}" type="currency" pattern="#.#"/>" 
						class="<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }">text_gray</c:if> <c:if test="${dinerBillDishe.dishesStatus == 5 }">text_red</c:if>" 
						>
							<c:if test="${dinerBillDishe.dishesStatus == 2}"><td id="seq"  style="background: url('/canyin-frontdesk/static/images/zhangdan/dot_right.png') no-repeat scroll 6px 18px transparent"> </c:if>
							<c:if test="${dinerBillDishe.dishesStatus != 2}"><td id="seq"> </c:if> 
							${status.index +1 }
							</td>
							<td id="dishesName" <c:if test="${fn:length(dinerBillDishe.dishesName) > 6}">style="font-size:13px;"</c:if><c:if test="${fn:length(dinerBillDishe.dishesName) > 10}">style="font-size:11px;"</c:if> >
								
								<c:if test="${dinerBillDishe.discountType == 0 && dinerBillDishe.discount < 100}">
									% 
								</c:if>
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"><del></c:if>
								<c:if test="${fn:length(dinerBillDishe.dishesName) > 11}"><a title="${dinerBillDishe.dishesName}">${fn:substring(dinerBillDishe.dishesName,0,10)}..</a></c:if>
								<c:if test="${fn:length(dinerBillDishe.dishesName) <= 11}">${dinerBillDishe.dishesName}</c:if>
								
								
								
								<c:if test="${dinerBillDishe.isUserDefined == '1'}"><span class="text_blue">自</span></c:if>
								<c:if test="${dinerBillDishe.isRulingPrice == '1' }"><span class="text_blue">时</span></c:if>
								
								<c:if test="${dinerBillDishe.discountType == '3' }"><span class="text_blue">赠</span></c:if>
								
								<c:if test="${dinerBillDishe.allNotes != null &&  dinerBillDishe.allNotes != ''}"><br/></c:if>
								<c:if test="${fn:length(dinerBillDishe.allNotes) > 9}"><a title="${dinerBillDishe.allNotes}">${fn:substring(dinerBillDishe.allNotes,0,8)}..</a></c:if>
								<c:if test="${fn:length(dinerBillDishe.allNotes) <= 9}">${dinerBillDishe.allNotes}</c:if>
								
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"></del></c:if>
							</td>
							 <td id="price">
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"><del></c:if>
								<c:if test="${dinerBillDishe.discountType == 1 || dinerBillDishe.discountType == 2}">
								<del>
								<fmt:formatNumber value="${dinerBillDishe.unitPrice}" type="currency" pattern="#.##"/>/
								</del>
								<fmt:formatNumber value="${dinerBillDishe.realUnitPrice}" type="currency" pattern="#.##"/>
								</c:if>
								<c:if test="${dinerBillDishe.discountType == null || dinerBillDishe.discountType == 0 || dinerBillDishe.discountType == 3}">
								<fmt:formatNumber value="${dinerBillDishe.unitPrice}" type="currency" pattern="#.##"/>
								</c:if>
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"></del></c:if>
							</td>
							<td id="orderNum" style="white-space:nowrap;overflow: hidden;">
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"><del></c:if>
								<fmt:formatNumber value="${dinerBillDishe.unitNum}" type="currency" pattern="#.#"/>${dinerBillDishe.unitName}
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"></del></c:if>
							</td>
							<td id="total">
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"><del></c:if>
								<c:if test="${dinerBillDishe.discountType == null || dinerBillDishe.discountType == 0}">
								<fmt:formatNumber value="${dinerBillDishe.oriCost}" type="currency" pattern="#.##"/>
								</c:if>
								<c:if test="${dinerBillDishe.discountType == 1 || dinerBillDishe.discountType == 2 || dinerBillDishe.discountType == 3}">
								<fmt:formatNumber value="${dinerBillDishe.realCost}" type="currency" pattern="#.##"/>
								</c:if>
								<c:if test="${dinerBillDishe.dishesStatus == 3 || dinerBillDishe.dishesStatus == 4 }"></del></c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="but_wrap">
					<div class="but_wrap_in">
						<div class="but_total_consume ">
							<p style="float:left;font-size:14px;margin-left: 10px;">‘%’：折扣菜肴</p>
							<p class="text_green" id="billOriCost" >
							菜品消费：<fmt:formatNumber value="${newBill.oriCost}" type="currency" pattern="#.##"/></p>
							<input type="hidden" id="payableCost_hidden" value="${newBill.payableCost}"/>
						</div>
						<a  style="cursor: pointer;" class="but_beizhu" id="beizhu">
							<c:if test="${newBill.allNotes eq null || fn:trim(newBill.allNotes) eq '' || fn:trim(newBill.allNotes) eq 'null'}">烹饪备注:无</c:if>
							<c:if test="${fn:length(newBill.allNotes) > 20}">${fn:substring(newBill.allNotes,0,19)}..</c:if>
							<c:if test="${fn:length(newBill.allNotes) <= 20}">${newBill.allNotes}</c:if>
						</a>
						<c:choose>
							<c:when test="${isCanPlace == '1'}">
								<a class="but_qx_xiugai" id="quxiao"></a>
							</c:when>
							<c:otherwise>
								<a class=but_qx_xiugai_no id="quxiao"></a>
							</c:otherwise>
						</c:choose>
						<a class="but_qx_left" id="billPageBack"></a>
						<a class="but_qx_right" id="billPageGo"></a>
						
						<c:if test="${newBill.billType == '1' || newBill.billType == '2' || newBill.billType == '3' || newBill.billType == '5'}">
								<c:if test="${isCanPlace == '0'}">
									<a class="but_xiadan_no" id="xiadan" ></a>
								</c:if>
								<c:if test="${isCanPlace == '1'}">
									<a class="but_xiadan" id="xiadan" ></a>
								</c:if>
						</c:if>
						
						<c:if test="${newBill.billType == '4' }">
							<c:if test="${newBill.billStatus == '3' || newBill.billStatus == '8' || newBill.billStatus == '10' }">
								<a class="but_yjz_no" id="jiezhang"></a>
							</c:if>
							<c:if test="${newBill.billStatus == '1' || newBill.billStatus == '2' || newBill.billStatus == '4' || newBill.billStatus == '9'}">
								<a class="but_qjz" id="jiezhang" onclick="jiezhang();"></a>
							</c:if>
						</c:if>
						
					</div>
				</div>
			</div>
			
			
	<div class="popup_zdmx" id="xiadanWindow" style="display: none;">
		<a class="but_cuicai mr_12" style="cursor:pointer;" id="dishCuicai"></a>
		<a class="but_huacai mr_12" style="cursor:pointer;" id="dishHuacai"></a>
		<a class="but_zengcai mr_12" style="cursor:pointer;" id="dishZengcai"></a>
		<a class="but_tuicai mr_12" style="cursor:pointer;" id="dishTuicai"></a>
		<a class="but_czjl_no" id="dishXiugai"></a>
	</div>
	
	
	<div class="popup_zdmx_taller" id="newDishWindow" style="display: none;">
		<div class="popup_zdmx_taller_t" id="fourButtons">
			<a class="but80 mr_4 ml_14" style="cursor: pointer;" id="newDishBeizhu">烹饪备注...</a>
			<a class="but80 mr_4" style="cursor: pointer;"  id="newDishZengcai">赠菜</a>
			<a class="but80 mr_4" style="cursor: pointer;"  id="editSet">编辑套餐</a>
			<a class="but80" style="cursor: pointer;" id="newDishDelete">删除</a>
		</div>
		<div class="popup_zdmx_taller_t" id="threeButtons">
			<a class="but_prbz mr_5 ml_19" style="cursor: pointer;" id="newDishBeizhu">烹饪备注...</a>
			<a class="but_prbz mr_8" style="cursor: pointer;"  id="newDishZengcai">赠菜</a>
			<a class="but_prbz" style="cursor: pointer;" id="newDishDelete">删除</a>
		</div>
		<div class="popup_zdmx_tall_m">
			<h3>数量操作</h3>
			<div class="left w_300 ml_70 h_50">
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
	
	
	<div class="popup_zdmx_tall" id="newDishEditWindow" style="display: none;">
		<div class="popup_zdmx_tall_t"></div>
		<div class="popup_zdmx_tall_m">
			<h3>称重计量<br/>
			<p class="text_red">主要用于鱼、海鲜等先下单后称重的菜肴，不会执行后厨打印。</p>
			</h3>
			<div class="left w_250 ml_70 h_50">
				<a class="but_minus mr_10" style="cursor:pointer;" id="newDishEditSub"></a>
				<input class="but_blank mr_10" name="newDishEditNum" id="newDishEditNum" type="text" value="0">
				<input  name="oldDishEditNum" id="oldDishEditNum" type="hidden" value="0">
				<span>份</span>
				<a class="but_add"  style="cursor:pointer;" id="newDishEditAdd"></a>
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
				<a class="but-queding mr_12" style="cursor:pointer;" id="newDishEditConfirm"></a>
				<a class="but-quxiao" style="cursor:pointer;" id="newDishEditCancel"></a>
			</div>
		</div>
	</div>
<input type="hidden" id="permission_tuicai" value="<shiro:hasPermission name="frontdesk_bill_tuicai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_zengcai" value="<shiro:hasPermission name="frontdesk_bill_dishZengcai:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_xiadan" value="<shiro:hasPermission name="frontdesk_index_xiadan:create">1</shiro:hasPermission>">
			