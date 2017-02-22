<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
.zhuozi{width:50px;height:36px;float:left;margin:65px 0 0 0;*margin-top:58px;padding-left:3px;color:#fff;font-weight:bold;font-size:16px;display:table;*position:relative;}
.zhuozi em.p_indexem{width:50px;height:35px;overflow:hidden; margin: 0 auto;text-align:center;font-weight:bold;line-height:17px;display:block;*position: relative; *top:-50%; font-size:14px;word-wrap:break-word; word-break:break-all; }
</style>
<input type="hidden" name="isHasAllArea" id="isHasAllArea" value="${isHasAllArea}"/>
			<div class="main_nav">
				<ul id="indexTableAraaUl">
					<li>
						<c:choose>
							<c:when  test="${searchMapParams.EQ_tableArea_areaId == null || searchMapParams.EQ_tableArea_areaId == ''}">
								<a class="a_on" style="cursor:pointer;">
									全部
								</a>
							</c:when>
							<c:otherwise >	
								<a class="a_c" style="cursor:pointer;"  href="###" onclick="changeTableArea('search_EQ_tableArea.areaId=${tableArea.areaId}',1)">全部</a>
							</c:otherwise>
						</c:choose>
					</li>
				<c:forEach items="${roleTableAreas}" var="tableArea" varStatus="status">
					<c:choose>
						<c:when  test="${searchMapParams.EQ_tableArea_areaId == tableArea.areaId}">
							<li><a style="cursor:pointer;" class="a_on">${tableArea.name}</a></li>
						</c:when>
						<c:otherwise >	
							<li><a style="cursor:pointer;"  href="###"  onclick="changeTableArea('search_EQ_tableArea.areaId=${tableArea.areaId}',1);" class="a_c">${tableArea.name}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</ul>
				<a href="###" class="but_back" id="dish_left"></a>
				<a href="###" class="but_go" id="dish_right"></a>
			</div>
			<div class="index_box">
					<div class="left" id="indexTableContent" style="height: 632px;">
						<c:forEach items="${tables.content}" var="table" varStatus="status">
							<input id="tableSeat" type="hidden" value="${table.seat}">
						<c:choose>
						 <c:when test="${table.dinnerStatus eq null || table.dinnerStatus eq 1}"><!-- 空闲 (空闲状态的餐桌要进行预订状态的处理)-->
						 	<c:choose>
							 	<c:when test="${table.isHasTableOrder eq 1 && table.isAtOrderWarnTime eq 0}"><!-- 在预警时间与到期保留时间的区间 并且 不是预警时间(即需要锁定餐台)-->
						 			<div class="yuding_1" id="${table.tabId}" onclick="tableDivClick(event,this);" dinnerStatus="${table.dinnerStatus}" billStatus="${table.currentTableBillRelation.billStatus}" isHasTableOrder="${table.isHasTableOrder}" isAtOrderWarnTime="${table.isAtOrderWarnTime}"  isChedan="0">
									<div class="zhuozi">
										<span><em class="p_indexem">
										<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
										<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
										</em></span>
									</div>
									<span class="time"><fmt:formatDate  value="${table.lastedTableOrderBillRelation.tableOrder.orderTime}" type="both" pattern="HH:mm" /></span>
									<span class="shuliang">${table.lastedTableOrderBillRelation.peopleNum}/${table.seat}</span>
									</div>
							 	</c:when>
							 	<c:when test="${table.isHasTableOrder eq 1 && table.isAtOrderWarnTime eq 1}"><!-- 在预警时间(需要给与预警)-->
						 			<div class="yuding_1" id="${table.tabId}" onclick="tableDivClick(event,this);" dinnerStatus="${table.dinnerStatus}" billStatus="${table.currentTableBillRelation.billStatus}" isHasTableOrder="${table.isHasTableOrder}" isAtOrderWarnTime="${table.isAtOrderWarnTime}"  isChedan="0">
									<div class="zhuozi">
										<span><em class="p_indexem">
										<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
										<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
										</em></span>
									</div>
									<span class="time"><fmt:formatDate  value="${table.lastedTableOrderBillRelation.tableOrder.orderTime}" type="both" pattern="HH:mm" /></span>
									<span class="shuliang">${table.lastedTableOrderBillRelation.peopleNum}/${table.seat}</span>
									</div>
							 	</c:when>
							 	<c:otherwise><!-- 不需要预警也不需要锁定餐台 -->
							 		<div class="kongxian"  id="${table.tabId}" onclick="tableDivClick(event,this);" dinnerStatus="${table.dinnerStatus}" billStatus="${table.currentTableBillRelation.billStatus}" isChedan="0">
											<div class="zhuozi"><span><em class="p_indexem">
											<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
											<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
											</em></span></div>
											<span class="time"></span>
											<span class="shuliang">0/${table.seat}</span>
										</div>
							 	</c:otherwise>
						 	</c:choose>
						 </c:when>
						 <c:when test="${table.dinnerStatus==2}"><!-- 使用中 -->
						 		<c:if test="${table.currentTableBillRelation.dinerBill.billStatus == 1 || table.currentTableBillRelation.dinerBill.billStatus == 9}">
									<div class="shiyong_w" id="${table.tabId}" onclick="tableDivClick(event,this);" billId ="${table.currentTableBillRelation.dinerBill.billId}" dinnerStatus="${table.dinnerStatus}" billStatus="${table.currentTableBillRelation.billStatus}" isChedan="${table.currentTableBillRelation.dinerBill.isChedan}">
										<div class="zhuozi"><span><em class="p_indexem">
										<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
											<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
											</em></span></div>
										<span class="time"><fmt:formatDate  value="${table.currentTableBillRelation.billTime}" type="both" pattern="HH:mm" /></span>
										<span class="shuliang">${table.currentTableBillRelation.peopleNum}/${table.seat}</span>
									</div>
								</c:if>
								<c:if test="${table.currentTableBillRelation.dinerBill.billStatus == 2 || table.currentTableBillRelation.dinerBill.billStatus == 4}">
									<div class="shiyong" id="${table.tabId}" onclick="tableDivClick(event,this);" billId ="${table.currentTableBillRelation.dinerBill.billId}" dinnerStatus="${table.dinnerStatus}" billStatus="${table.currentTableBillRelation.billStatus}" isChedan="${table.currentTableBillRelation.dinerBill.isChedan}">
										<div class="zhuozi"><span><em class="p_indexem">
										<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
											<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
											</em></span></div>
										<span class="time"><fmt:formatDate  value="${table.currentTableBillRelation.billTime}" type="both" pattern="HH:mm" /></span>
										<span class="shuliang">${table.currentTableBillRelation.peopleNum}/${table.seat}</span>
									</div>
								</c:if>
								
								<!-- 此问题出现是bug。餐桌是使用中，但是账单关联和账单表都是已结账状态。出此下次。以后再改 TODO-->
								<c:if test="${table.currentTableBillRelation.dinerBill.billStatus == 3}">
									<div class="yi_jiezhang"  id="${table.tabId}" onclick="tableDivClick(event,this);" dinnerStatus="3" billStatus="${table.currentTableBillRelation.billStatus}" isChedan="0">
										<div class="zhuozi"><span><em class="p_indexem">
											<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
											<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
											</em></span></div>
										<span class="time"><fmt:formatDate  value="${table.currentTableBillRelation.billTime}" type="both" pattern="HH:mm" /></span>
										<span class="shuliang">${table.currentTableBillRelation.peopleNum}/${table.seat}</span>
									</div>
								</c:if>
								<!-- 此问题出现是bug。餐桌是使用中，但是没有关联的账单表信息了。出此下次。以后再改TODO -->
								<c:if test="${table.currentTableBillRelation == null}">
									<div class="yi_jiezhang"  id="${table.tabId}" onclick="tableDivClick(event,this);" dinnerStatus="3" billStatus="${table.currentTableBillRelation.billStatus}" isChedan="0">
										<div class="zhuozi"><span><em class="p_indexem">
											<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
											<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
											</em></span></div>
										<span class="time"><fmt:formatDate  value="${table.currentTableBillRelation.billTime}" type="both" pattern="HH:mm" /></span>
										<span class="shuliang">${table.currentTableBillRelation.peopleNum}/${table.seat}</span>
									</div>
								</c:if>
								
								
						 	</c:when>
							<c:when test="${table.dinnerStatus==3}"><!-- 待清台 -->
							</c:when>
							<c:when test="${table.dinnerStatus==4}"><!-- 停用 -->
							</c:when>
							<c:when test="${table.dinnerStatus==5}"><!-- 已结账 (待清台)-->
								<div class="yi_jiezhang"  id="${table.tabId}" onclick="tableDivClick(event,this);" dinnerStatus="${table.dinnerStatus}" billStatus="${table.currentTableBillRelation.billStatus}" isChedan="0">
										<div class="zhuozi"><span><em>
											<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
											<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
											</em></span></div>
										<span class="time"><fmt:formatDate  value="${table.currentTableBillRelation.billTime}" type="both" pattern="HH:mm" /></span>
										<span class="shuliang">${table.currentTableBillRelation.peopleNum}/${table.seat}</span>
									</div>
							</c:when>
							<c:otherwise><!-- 6预订yuding_1 yuding_2 -->
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div id="pageArea" class="search_wrap" style="position:absolute;bottom:0px;display: none;">
						<tags:pagination page="${tables}" paginationSize="15"/>
						<p class="zhang_infor">
							<span>
								<a id="resettlebillsSize" style="color: red;" href="javascript:resettlebillsSizeClick();"></a>
							</span>
							<span>
								<a id="noPayWaimaiSize" style="color: red;" href="javascript:noPayWaimaiSizeClick();"></a>
							</span>
						</p>
						<div class="search">
							<input name="keywords" id="keywords" type="text" value="${searchMapParams.LIKE_tabNo}">
							<button onclick="search();" id="keywordsbtn"></button>
						</div>
					</div>
			</div>