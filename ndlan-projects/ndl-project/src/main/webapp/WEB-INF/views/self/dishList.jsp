<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="remoteAddr" value="${pageContext.request.remoteAddr}" />
<c:set var="remotePort" value="${pageContext.request.serverPort}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${dishes.content}" var="dishe" varStatus="status">
	<c:if test="${dishe.estimate eq null or dishe.estimate ne null and dishe.estimate>0 }"> 
	   	<li data-icon="false" id="${dishe.dishesId}" lang="${dishe.estimate}" isSet="${dishe.dishAndSetDiv }" title="${dishe.dishAndSetDiv }">
	    	<a href="#" onclick="addDishe(this);" style="background:#fff;" class="dishItem">
		        <c:if test="${dishe.dishesPicUrl == null || dishe.dishesPicUrl ==''}">
				 <img class="dish"  src="${ctx}/static/images/self/cai.jpg" height="60px;" style="margin-left: 6px;" onclick="goDishDetail(event,'${ctx}/self/dishDetail?tabNO=${tabNO}&dishId=${dishe.dishesId}&isSet=${dishe.dishAndSetDiv }')" />
				</c:if>
				<c:if test="${dishe.dishesPicUrl != null && dishe.dishesPicUrl !=''}">
				<img class="dish"  src="http://${serverIp}:${remotePort}/canyin-main/${dishe.dishesPicUrl200x200}" height="60px;"   style="margin-left: 6px;"  onclick="goDishDetail(event,'${ctx}/self/dishDetail?tabNO=${tabNO}&dishId=${dishe.dishesId}&isSet=${dishe.dishAndSetDiv }')" />
				</c:if>
		    	<h2>${dishe.dishesName}</h2>
		    	<p><c:if test="${dishe.specialPrice !=null}"><del></c:if><fmt:formatNumber value="${dishe.price}" type="currency" pattern="#.##"/><c:if test="${dishe.specialPrice !=null}"></del>/</c:if><fmt:formatNumber value="${dishe.specialPrice}" type="currency" pattern="#.##"/> 元${dishe.dishesUnit.name} <font color="gray" style="margin-left:10px;">销量：${dishe.saleNum}</font></p>
		    	<span style="background-color:#ff6600;color:white;<c:if test="${dishe.selfDishSaleNum == null || dishe.selfDishSaleNum == 0}">display: none;</c:if>" class="ui-li-count" >${dishe.selfDishSaleNum}</span>
	    	</a>
	    	<a style="background:#fff;width: 40px;" data-icon="minus" onclick="minusDish(this);" class="<c:if test="${dishe.selfDishSaleNum == null || dishe.selfDishSaleNum == 0}">ui-state-disabled</c:if><c:if test="${dishe.selfDishSaleNum != null && dishe.selfDishSaleNum > 0}">red</c:if>" href="#purchase" data-rel="popup" data-position-to="window" data-transition="pop"></a>
	   	 </li>
	</c:if>
</c:forEach>

<input type="hidden" name="totalPages" id="totalPages" value="${dishes.totalPages }"/>
<input type="hidden" name="number" id="number" value="${dishes.number+1}"/>