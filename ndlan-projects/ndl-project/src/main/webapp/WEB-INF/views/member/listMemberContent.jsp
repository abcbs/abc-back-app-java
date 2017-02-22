<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

			<div class="hy_nav">
				<ul id="memberListUl">
					<c:choose>
						<c:when  test="${searchMapParams.EQ_membershipCardClass_mcclassId == null || searchMapParams.EQ_membershipCardClass_mcclassId == ''}">
							<li><a class="quanbu_on" style="cursor:pointer;"  href="###">全部</a><span class="dot_on"></span></li>
						</c:when>
						<c:otherwise >	
							<li><a class="quanbu" style="cursor:pointer;"  href="###" onclick="dishCatagoryChange('','search_EQ_membershipCardClass.mcclassId=${membershipCardClass.mcclassId}',1,1)">全部</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach items="${membershipCardClasses}" var="membershipCardClass" varStatus="status">
						<c:choose>
							<c:when  test="${searchMapParams.EQ_membershipCardClass_mcclassId == membershipCardClass.mcclassId}">
								<li><a style="cursor:pointer;"  href="###" class="qita_on">${membershipCardClass.name}</a><span class="dot_on"></span></li>
							</c:when>
							<c:otherwise >	
								<li><a style="cursor:pointer;"  href="###" onclick="dishCatagoryChange('','search_EQ_membershipCardClass.mcclassId=${membershipCardClass.mcclassId}',1,1);" class="qita">${membershipCardClass.name}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<a href="###" class="but_back" id="dish_left"></a>
				<a href="###" class="but_go" id="dish_right"></a>
			</div>
			<div class="hy_table_wrap">
				<table  class="huiyuan_table" id="memberTable">
				<thead>
					<tr>
						<th>会员</th>
						<th width="100px;">联系电话</th>
						<th>卡号</th>
						<th width="30px;"></th>
						<th>卡类型</th>
						<th width="90px;">发卡时间</th>
						<th>余额</th>
						<th>积分</th>
						<th>押金</th>
						<th width="80px;">卡状态</th>
						<th width="80px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${restMemberInfos.content}" var="restMemberInfo" varStatus="rstatus">
					
						        <c:if test="${restMemberInfo.shipCards ==null || fn:length(restMemberInfo.shipCards) == 0}">
									<tr id="${restMemberInfo.mbId}" onclick="rowClick(event,this);">
										<td class="left-td link_blue"  onclick="popForm('会员明细','${ctx}/member/pop/member/createForm/${restMemberInfo.mbId}','880','644');">
											<c:if test="${fn:length(restMemberInfo.name) <= 5}">${restMemberInfo.name}</c:if>
											<c:if test="${fn:length(restMemberInfo.name) > 5}">${fn:substring(restMemberInfo.name,0,5)}..</c:if>
										</td>
										<td class="left-td">
											${restMemberInfo.mobile}
										</td>
										<td></td>
										<td class="left-td"></td>
										
										<td></td>
										<td></td>
										<td class="right-td"></td>
										<td class="right-td"></td>
										<td class="right-td"></td>
										<td class="right-td"></td>
										<td class="right-td"></td>
									</tr>
								</c:if>
								<c:forEach items="${restMemberInfo.shipCards}" var="membershipCard" varStatus="mstatus">
								
									
									<tr id="${restMemberInfo.mbId}"  onclick="rowClick(event,this);" mcId="${membershipCard.mcId}" cardStatus="${membershipCard.cardStatus}" <c:if test="${mstatus.index > 0}">style="display:none;"</c:if>>
										<td class="left-td link_blue" onclick="popForm('会员明细','${ctx}/member/pop/member/createForm/${restMemberInfo.mbId}','880','644');">
											<c:if test="${mstatus.index == 0}">
											<c:if test="${fn:length(restMemberInfo.name) <= 5}">${restMemberInfo.name}</c:if>
											<c:if test="${fn:length(restMemberInfo.name) > 5}">${fn:substring(restMemberInfo.name,0,5)}..</c:if>
											</c:if>
										</td>
										<td class="left-td">
											<c:if test="${mstatus.index == 0}">
											${restMemberInfo.mobile}
											</c:if>
										</td>
										<td class="right-td link_blue" onclick="popForm('会员卡明细','${ctx}/member/pop/opDetail/createForm/${membershipCard.mcId}?showType=0','880','644');">
											${membershipCard.cardNo}
										</td>
										<td class="left-td">
											<c:if test="${fn:length(restMemberInfo.shipCards) > 1 && mstatus.index == 0}">
											<a class="table_but_3" style="cursor:pointer;" href="###"  onclick="showSub(event,this);" >+</a>
											</c:if>
										</td>
										<td class="left-td">${membershipCard.membershipCardClassName}</td>
										<td><fmt:formatDate value="${membershipCard.cardIssueTime}" pattern="yyyy-MM-dd"/></td>
										<td class="right-td">${membershipCard.balance}</td>
										<td class="right-td">${membershipCard.memberIntegral}</td>
										<td class="right-td">${membershipCard.cashPledge}</td>
										<td class="right-td">${membershipCard.cardStatusName}</td>
										<td class="right-td">
											<a onclick="printMemberCardInfo('${membershipCard.mcId}','${membershipCard.cardNo}');" href="###" class="print_a"></a>
										</td>
									</tr>
								</c:forEach>
								
					</c:forEach>
					</tbody>
					
				</table>
			</div>
			<div class="hy_sousuo">
					<div class="search_sec">
						<input name="kewWords" id="kewWords" type="text" value="${kewWords }" onkeydown="return kewWordsEnter(event);">
						<button onclick="dishCatagoryChange(document.getElementById('kewWords').value,currentSearchParams,1,'');"></button>
					</div>
					<div  class="search_sec" style="size: 200">
						<p align="center">更多操作请登录后台管理系统</p>
					</div> 
					<div class="right">
						<tags:paginationdish page="${restMemberInfos}" paginationSize="15"/>
					</div>
				</div>
