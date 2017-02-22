<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<ul class="hykjz_infor" id="pop_memberContentList">
	<c:forEach items="${restMemberInfos}" var="member">
		<li>
			<a onclick="setMember('${member.mbId}','${member.name}','${member.mobile}',this);">
				<span class="w_75">
					<c:choose>
						<c:when test="${fn:length(member.name) > 5}">
							${fn:substring(member.name,0,4)}..
						</c:when>
						<c:otherwise>
							${member.name}
						</c:otherwise>
					</c:choose>
				</span>
				<span class="w_100">${member.mobile}</span>
			</a>
		</li>
	</c:forEach>
</ul>
