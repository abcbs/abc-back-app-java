<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<ul class="hykjz_infor">
	<c:forEach items="${membershipCards}" var="card">
		<li>
			<a href="#" id="${card.mcId}"  onclick="setCard('${card.mcId}','${card.cardNo}','${card.restMemberInfo.mbId}','${card.isEmptyPassWord}',this);">
				<input type="hidden" value="${card.mcId}" id="cardMcId"/>
				<input type="hidden" value="${card.balance}" id="balance"/>
				<input type="hidden" value="${card.memberIntegral}" id="memberIntegral"/>
				<span class="w_75">
					<c:choose>
						<c:when test="${fn:length(card.restMemberInfo.name) > 5}">
							${fn:substring(card.restMemberInfo.name,0,4)}..
						</c:when>
						<c:otherwise>
							${card.restMemberInfo.name}
						</c:otherwise>
					</c:choose>
				</span>
				<span class="w_100">${card.restMemberInfo.mobile}</span>
				<span class="w_200">&nbsp;${card.cardNo}</span>
			</a>
		</li>
	</c:forEach>
</ul>
