<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<input type="hidden" value="${netDataType}"  id="netDataType" name="netDataType"/>


<div class="main_nav">
	<ul>
		<li class="quanbu"><a href="javascript:cloudBillChange('',1,'');" <c:if test="${param.searchType == ''}">class="hover"</c:if>>全部</a></li>
		<li><a href="javascript:cloudBillChange('2',1,'');" style="color: #41ad48;" <c:if test="${param.searchType == '2'}">class="hover"</c:if>>预订</a></li>
		<li><a href="javascript:cloudBillChange('1',1,'');" style="color: #fa6767;"<c:if test="${param.searchType == '1'}">class="hover"</c:if>>外卖</a></li>
	</ul>
</div>

<c:choose>
	<c:when test="${isConnect eq '0'}">
		<div class="index_box">
			<div class="bangding_yzh">
				<div class="yzh_title">网络故障！！</div>
				<div class="yzh_content">
					<div class="yzh_content_con">请检查您的网络，保持网络畅通，否则无法使用xxx云服务！！</div>
				</div>
				<div class="yzh_button" style="text-align:left;"><a href="${ctx}/cloud/list" class="yzh_button_shuaxin"></a></div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${bills eq null || bills.size eq 0}">
				<div class="index_box">
					<div class="bangding_yzh">
		                 <div class="yzh_title">没有云订单。请加大云餐厅、微信餐厅的推广力度！！</div>
		                 <div class="yzh_content">
		                   <div class="yzh_content_title">您可以：</div>
		                   <div class="yzh_content_con" style="color:#fff;">
		                    <p>在餐厅醒目位置张贴上餐厅的云餐厅地址和微信关注二维码！</p><p>在收银小票上加上云餐厅地址和微信关注二维码！</p><p>在宣传单上加印云餐厅地址和微信关注二维码！</p>
		                   </div>
		                 </div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="index_box">
					<div class="left" id="left" style="float:left;height: 632px;">
					<!--daishenhe shenhetongguo shenheweitongguo-->
					<c:forEach items="${bills.content}" var="each" varStatus="status">
							<c:set var="billClass" value=""/>
							<c:choose>
								<%-- 外卖 --%>
								<c:when test="${each.billType == '1'}">
									<c:choose>
										<c:when test="${each.status == '1'}">
											<c:set var="billClass" value="daishenhe"/>
										</c:when>
										<c:when test="${each.status == '2' || each.status == '4' || each.status == '5' || each.status == '6' || each.status == '7' || each.status == '8'}">
											<c:set var="billClass" value="shenhetongguo"/>
										</c:when>
										<c:when test="${each.status == '3'}">
											<c:set var="billClass" value="shenheweitongguo"/>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</c:when>
								<%-- 预订 --%>
								<c:when test="${each.billType == '2'}">
									<c:choose>
										<c:when test="${each.status == '1'}">
											<c:set var="billClass" value="daishenhe"/>
										</c:when>
										<c:when test="${each.status == '2' || each.status == '4' || each.status == '5' || each.status == '7' || each.status == '8'}">
											<c:set var="billClass" value="shenhetongguo"/>
										</c:when>
										<c:when test="${each.status == '6'}">
											<c:set var="billClass" value="shenheweitongguo"/>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							<div class="${billClass}" onclick="billDivClick(event,this);" ondblclick="billDivDBClick(this);" id="${each.billId}" billType="${each.billType}" billStatus="${each.status}" billNo="${each.billNo}">
								<c:if test="${each.billType == '1'}">
									<div class="waimai_biao"></div>
								</c:if>
								
								<c:if test="${each.billType == '2'}">
									<div class="yuding_biao"></div>
								</c:if>
								
								<p>
									<span class="jiage"><b>￥${each.totalCost}</b></span>${fn:substring(each.eatTime,11,16)}
								</p>
								<p>
									<span style="width:150px;">${each.billFromDesc}</span>
									<c:if test="${each.isVip == '1'}">
										vip
									</c:if>
								</p>
								<p>
									<span style="float: left;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">${each.peopleName}</span>
									<span style="white-space: nowrap;">${each.mobile}</span>
								</p>
								<p class="dizhi">${each.address}</p>
							</div>
					</c:forEach>
					</div>
					<div class="search_wrap" style="position:absolute;bottom:5px;">
						<div class="page">
							<tags:paginationcloudbill paginationSize="15" page="${bills}"></tags:paginationcloudbill>
						</div>
						<p class="zhang_infor">
							<span id="cloudNumDiv">今日云订单：${mapData.takeoutCountToday+mapData.orderCountToday}单(${mapData.takeoutCountToday}单外卖 ,${mapData.orderCountToday}单预订)</span>
						</p>
						<div class="search">
							<input name="keywords" type="text" value="" id="keywords" placeholder="请输入客户姓名、电话、单号">
							<button id="but_keyWords" onclick="searchBill();"></button>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>