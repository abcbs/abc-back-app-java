<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="list_b_in">
			<div class="tab_nav_wrap">
				<ul class="tab_nav">
					<li class="on"><a href="###" style="cursor:default;">账单列表(1)</a></li>
					<li><a style="cursor:pointer;"  href="###" onclick="tabChange(this,'${ctx}/bill/diancai')">点餐(2)</a></li>
					<li><a style="cursor:pointer;"  href="###" onclick="tabChange(this,'${ctx}/bill/payPage')">结账(3)</a></li>
				</ul>
			</div>
				<ul class="tab_nav_baby" id="biiiListUl">
					<li><a style="cursor:pointer;"  onclick="dishCatagoryChange('${billId}','','1','${keywords}','${pageType}','')" class="first_tag<c:if test="${(billType == null || billType == '') && (billStatus == null || billStatus == '')}">_on</c:if>">全部</a></li>
					<c:forEach items="${billStatusList}" var="each">
						<c:choose>
						<c:when test="${billStatus == each.code}">
							<li><a onclick="dishCatagoryChange('${billId}','${each.code}','1','${keywords}','${pageType}','')" href="###"  class="selected">${each.desc}</a></li>
						</c:when>
						<c:otherwise>
							<li><a onclick="dishCatagoryChange('${billId}','${each.code}','1','${keywords}','${pageType}','')" style="cursor:pointer;" href="###"  >${each.desc }</a></li>
						</c:otherwise>
						</c:choose>	
						
					</c:forEach>
					<!-- <li><a onclick="dishCatagoryChange('${billId}','','1','${keywords}','${pageType}','2')" style="cursor:pointer;"  href="###" class="last_tag<c:if test="${billType == '2'}">_on</c:if>">外卖单</a></li>
				 	-->
				 	<li><a onclick="dishCatagoryChange('${billId}','','1','${keywords}','${pageType}','2')" style="cursor:pointer;"  href="###" class="<c:if test="${billType == '2'}">selected</c:if>">外卖单</a></li>
					<li><a onclick="dishCatagoryChange('${billId}','','1','${keywords}','${pageType}','4')" style="cursor:pointer;"  href="###" class="last_tag<c:if test="${billType == '4'}">_on</c:if>">快餐</a></li>
				
				</ul>
				<table border="0" class="zdlb">
					<thead>
					<tr>
						<th width="100">账单编号</th>
						<th>餐台</th>
						<th>开单人</th>
						<th>收银员</th>
						
					<c:if test="${searchMapParams.EQ_billStatus != '8' }">
						<th>结账时间</th>
					</c:if>
						<th width="110">应收合计</th>
						<th width="110">实收金额</th>
						<th width="100">状态</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${dinerBillList.content }" var="dinerBill">
						<tr onclick="billListChange('${dinerBill.billId}',this);" billStatus="${dinerBill.billStatus}" isChedan="${dinerBill.isChedan}" >
							<td class="left-td link_blue"><a href="${ctx}/bill/payPage/${dinerBill.billId}">${dinerBill.billNo}</a></td>
							<td>
								${dinerBill.tabNo}
								<c:if test="${dinerBill.billType == '2' }">外卖</c:if>
								<c:if test="${dinerBill.billType == '3' }">自助点餐</c:if>
								<c:if test="${dinerBill.billType == '4' }">快餐</c:if>
								
							</td>
							<td class="left-td">${dinerBill.createEmployee.name}</td>
							<td class="left-td">${dinerBill.cashierEmployee.name}</td>
							
						<c:if test="${searchMapParams.EQ_billStatus != '8' }">
							<td>
								<c:choose>
									<c:when test="${dinerBill.intraday eq true}">
										<fmt:formatDate value="${dinerBill.payTime}" pattern="HH:mm:ss"/>
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${dinerBill.payTime}" pattern="MM-dd HH:mm:ss"/>
									</c:otherwise>
								</c:choose>
							</td>
						</c:if>
							<td class="right-td"><fmt:formatNumber value="${dinerBill.payableCost}" pattern="#.##" type="number"/></td>
							<td class="right-td"><fmt:formatNumber value="${dinerBill.realCost}" pattern="#.##" type="number"/></td>
							<td class="left-td <c:if test="${dinerBill.billStatus == 4}">link_red</c:if><c:if test="${dinerBill.billStatus == 8}">link_orange</c:if>">${dinerBill.billStatusDesc}</td>
							
							
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="zdhj_wrap">
					<span>今日已结：<fmt:formatNumber value="${realCostTotal}" pattern="#.##" type="number"/></span>
					<span>今日未结：<fmt:formatNumber value="${payableCostTotal}" pattern="#.##" type="number"/></span>
					<a class="zdhj<c:if test="${pageType == '7day'}">_on</c:if>" onclick="dishCatagoryChange('${billId}','','1','','7day','')">最近7天账单</a>
					<a class="zdhj<c:if test="${pageType == 'today'}">_on</c:if>" onclick="dishCatagoryChange('${billId}','','1','','today','')">今日账单</a>
					<a class="zdhj<c:if test="${pageType == 'unPay'}">_on</c:if>" onclick="dishCatagoryChange('${billId}','','1','','unPay','')">未结账单</a>
				</div>
				
				<div class="zdlb_but_area">
					<div class="search_sec">
						<input name="keywords" id="inputKeywords" type="text" value="${keywords}"  onkeydown="return kewWordsEnter(event);">
						<button onclick="keyWordSearch();" id="keywordsbtn"></button>
					</div>
					<a onclick="listChendan();" id="listChendan" class="but_chedan_on"></a>
					<a onclick="listPrint();" id="listPrint" class="but_dayin_on"></a>
					<a onclick="resettle();" id="fanjzbt" class="but_fanjiezhang_on"></a>
					<tags:paginationbills page="${dinerBillList}" paginationSize="15"/>
				</div> 
			</div>