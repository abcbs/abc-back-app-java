<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<input id="yudingCount" type="hidden" value="${yudingCount}">
<input id="shiwuCount" type="hidden" value="${shiwuCount}">
<input id="sanshiCount" type="hidden" value="${sanshiCount}">
<input id="longCount" type="hidden" value="${longCount}">

<input id="weiwanjieCount" type="hidden" value="${weiwanjieCount}">
<input id="weixiadanCount" type="hidden" value="${weixiadanCount}">
<input id="yixiadanCount" type="hidden" value="${yixiadanCount}">
<input id="paisongzhongCount" type="hidden" value="${paisongzhongCount}">
<input id="yiwanjieCount" type="hidden" value="${yiwanjieCount}">
					
<div id="takeoutListDiv" class="lefts">
	<c:forEach var="takeout" items="${takeoutList.content}">
		<c:choose>
			<c:when test="${takeout.billStatus == 3}">
				<c:set var="classStyle" value="yijiezhang" />
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${takeout.minuteDiff <= 0}">
						<c:set var="classStyle" value="yudingzhong" />
					</c:when>
					<c:when test="${takeout.minuteDiff > 0 && takeout.minuteDiff <= 15}">
						<c:set var="classStyle" value="paisong" />
					</c:when>
					<c:when test="${takeout.minuteDiff > 15 && takeout.minuteDiff <= 30}">
						<c:set var="classStyle" value="weixiadan" />
					</c:when>
					<c:when test="${takeout.minuteDiff > 30}">
						<c:set var="classStyle" value="yixiadan" />
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		<div id="takeoutDiv" class="${classStyle}" p="px" billStatus="${takeout.billStatus}" tId="${takeout.tId}" billId="${takeout.billId}" onclick="tableDivClick(event,this);" ondblclick="ondblclickTakeout(this);">
			<div class="content <c:if test='${takeout.billStatus == 3}'>hui</c:if>">
				<p>
					<span>
						<b>￥<c:choose><c:when test="${takeout.totalCost == null || takeout.totalCost eq ''}">0</c:when><c:otherwise><fmt:formatNumber value="${takeout.totalCost}" pattern="#.##" type="number"/></c:otherwise></c:choose></b>
					</span>
					<c:choose><c:when test="${takeout.sendDate eq takeout.nowDate}"><span class="time"><fmt:formatDate value="${takeout.sendTime}" pattern="HH:mm"/></span></c:when><c:otherwise><span class="time"><fmt:formatDate value="${takeout.sendTime}" pattern="MM-dd HH:mm"/></span></c:otherwise></c:choose>
				</p>
				<p>
					<span>${takeout.contactName}</span>
					<c:choose>
						<c:when test="${takeout.mobile eq null || takeout.mobile == ''}">
							<span class="time">${takeout.telephone}</span>
						</c:when>
						<c:otherwise>
							<span class="time">${takeout.mobile}</span>
						</c:otherwise>
					</c:choose>
				</p>
				<p class="dizhi">${takeout.sendAddress}</p>
			</div>
			<div class="title">
				<c:choose>
					<c:when test="${takeout.billStatus == 1}">
						未下单
					</c:when>
					<c:when test="${takeout.billStatus == 2}">
						已下单
					</c:when>
					<c:when test="${takeout.billStatus == 3}">
						已结账
					</c:when>
					<c:when test="${takeout.billStatus == 4}">
						反结账
					</c:when>
					<c:when test="${takeout.billStatus == 8}">
						已撤单
					</c:when>
					<c:when test="${takeout.billStatus == 11}">
						派送中
					</c:when>
					<c:otherwise>
						未下单
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:forEach>
</div>

<div class="search_wrap">
	<div class="page">
		<tags:paginationtakeout page="${takeoutList}" paginationSize="5"/>
		<!-- <a href="#" class="page_back"></a> <a href="#" class="page_go"></a> -->
	</div>
	<p class="zhang_infor" style="width:700px;">
		<span>未结金额：<fmt:formatNumber value="${notSettleSum}" pattern="#.##" type="number"/>元</span>
		<span>已结金额：<fmt:formatNumber value="${settleSum}" pattern="#.##" type="number"/>元</span>
		<span>今日外卖：${takeoutSize}单</span>
	</p>
	<div class="search">
		<input id="inputKeywords" type="text" value="${keywords}">
		<button id="inputKeywordsBut" onclick="setKeywords();" type="button"></button>
	</div>
</div>