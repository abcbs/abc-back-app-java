<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/member/opDetailForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/member/opDetail/create" method="post" >
	<input type="hidden" value="${membershipCard.mcId}"  id="mcId" name="mcId"/>
	<input type="hidden" value="${showType}"  id="showType" name="showType"/>
	<input type="hidden" value="${membershipCard.cardStatus}"  id="popCardStatus" name="popCardStatus"/>
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>会员卡：${membershipCard.cardNo}</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kamingxi_wrap">
				<div  class="page_wrap">
					 <c:if test="${cardOperationType == 2 || cardOperationType == 1}">
					 	<tags:paginationcard paginationSize="15" page="${membershipCardOperations}"></tags:paginationcard>
					 </c:if>
					
				</div>
				<ul id="popTab" class="kamingxi_tab">
					<li><a style="cursor:pointer;" onclick="changTab(2,this);" <c:if test="${cardOperationType == 2}">class="kmx_on"</c:if>>消费记录 </a></li>
					<li><a style="cursor:pointer;" onclick="changTab(1,this);" <c:if test="${cardOperationType == 1}">class="kmx_on"</c:if>>充值记录 </a></li>
					<li><a style="cursor:pointer;" onclick="changTab(3,this);" <c:if test="${cardOperationType == 3}">class="kmx_on"</c:if>>停启用会员卡 </a></li>
				</ul>
				
				<c:if test="${cardOperationType == 1}">
					<table  class="kamingxi_table">
					<thead>
						<tr>
							<th width="100">充值金额</th>
							<th width="100">实付金额</th>
							<th width="100">优惠金额</th>
							<th width="100">增加积分</th>
							<th width="100">充值后积分</th>
							<th width="100">充值类型</th>
							<th width="190">操作日期</th>
							<th width="110">操作人</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${membershipCardOperations.content}" var="membershipCardOperation" varStatus="status">
							<tr>
								<td><fmt:formatNumber value="${membershipCardOperation.rechargeCash}" pattern="#.##" type="number"/></td>
								<td><fmt:formatNumber value="${membershipCardOperation.paidinCash}" pattern="#.##" type="number"/></td>
								<td><fmt:formatNumber value="${membershipCardOperation.rechargeCash - membershipCardOperation.paidinCash}" pattern="#.##" type="number"/></td>
								<td><fmt:formatNumber value="${membershipCardOperation.addIntegral}" pattern="#.##" type="number"/></td>
								<td><fmt:formatNumber value="${membershipCardOperation.totalIntegral}" pattern="#.##" type="number"/></td>
								<td>${membershipCardOperation.cardOperationTypeDesc}</td>
								<td><fmt:formatDate  value="${membershipCardOperation.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${membershipCardOperation.createEmployee.name}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${cardOperationType == 2}">
					<table  class="kamingxi_table">
					<thead>
						<tr>
							<th width="190">消费日期</th>
							<th width="110">收银员</th>
							<th width="80">消费金额</th>
							<th width="80">实收金额</th>
							<th width="80">增加积分</th>
							<th width="240">付款详情</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${membershipCardOperations.content}" var="membershipCardOperation" varStatus="status">
							<tr>
								<td align="center"><fmt:formatDate  value="${membershipCardOperation.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center">${membershipCardOperation.createEmployee.name}</td>
								<td align="center"><fmt:formatNumber value="${membershipCardOperation.consumeCash}" pattern="#.##" type="number"/></td>
								<td align="center"><fmt:formatNumber value="${membershipCardOperation.dinerBill.realCost}" pattern="#.##" type="number"/></td>
								<td align="center"><fmt:formatNumber value="${membershipCardOperation.addIntegral}" pattern="#.##" type="number"/></td>
								<td>
									<c:if test="${fn:length(membershipCardOperation.remarks) > 30}"><a title="${membershipCardOperation.remarks}">${fn:substring(membershipCardOperation.remarks,0,29)}..</a></c:if>
									<c:if test="${fn:length(membershipCardOperation.remarks) <= 30}">${membershipCardOperation.remarks}</c:if>
								
								</td>
							</tr>
						</c:forEach>
						
						</tbody>
					</table>
				</c:if>
				<c:if test="${cardOperationType == 3}">
					<div class="kamingxi_box">
						<ul class="tqyhyk">
							<li><a style="cursor:pointer;" onclick="changeCardStatus(1);" id="changeCardStatus_1" <c:if test="${membershipCard.cardStatus == 1}">class="tqyhyk_on"</c:if>>正常</a></li>
							<li><a style="cursor:pointer;" onclick="changeCardStatus(2);" id="changeCardStatus_2" <c:if test="${membershipCard.cardStatus == 2}">class="tqyhyk_on"</c:if>>停用</a></li>
							<li><a style="cursor:pointer;" onclick="changeCardStatus(3);" id="changeCardStatus_3" <c:if test="${membershipCard.cardStatus == 3}">class="tqyhyk_on"</c:if>>挂失</a></li>
						</ul>
					</div>
				</c:if>
				<div class="line_w"></div>
				<div class="but-area-e">
					<c:if test="${showType == 0 }">
						<a class="but-queding mr_28" style="cursor:pointer;" onclick="popConfirm(${cardOperationType});"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
					</c:if>
					<c:if test="${showType == 1 }">
						<a class="but-queding mr_28" style="cursor:pointer;" onclick="popConfirm(${cardOperationType});"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="popBack(1);"></a>
					</c:if>
					
				</div>
			</div>
		</div>
	</div>
</form>


<input type="hidden" id="permission_guashi" value="<shiro:hasPermission name="frontdesk_member_guashi:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_tingyong value="<shiro:hasPermission name="frontdesk_member_tingyong:create">1</shiro:hasPermission>">
	
</body>
</html>