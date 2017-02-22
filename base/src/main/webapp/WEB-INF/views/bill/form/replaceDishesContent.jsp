<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:forEach var="replaces" items="${dishesSetDishesReplaces}">
	<div class="th_cai"  dsDishesId="${dishesSetDishes.dsDishesId}" replaceDisheId="${replaces.replaceDishe.dishesId}" unitNum="${replaces.unitNum}" unitName="${replaces.unitName}" dishesName="${replaces.dishesName}" dishesCode="${replaces.replaceDishe.dishesCode}" onclick="replaces(this);">
		<div>
			<p>
				<span>
					${replaces.dishesName}
					<br/>
					${replaces.unitNum}${replaces.unitName}
					<br/>
					${replaces.replaceDishe.dishesCode}
				</span>
				<span></span>
				<span></span>								
			</p>
		</div>
	</div>
</c:forEach>
