<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="remoteAddr" value="${pageContext.request.remoteAddr}" />
<c:set var="remotePort" value="${pageContext.request.serverPort}" />
<input  type="hidden" id="billId" value="${billId}"/>
       		<div class="FP_link">
                <div class="FP_link_2">
                	<ul onselect="return false">
                		<c:forEach items="${dishesCategorys}" var="dishesCategory" varStatus="status">
                			<li id="<c:if test="${categoryId == dishesCategory.categoryId}">link_active</c:if>" onclick="dishCatagoryChange('','${dishesCategory.categoryId}',1,'');">
                				${dishesCategory.categoryName}
                			</li>
                		</c:forEach>
                    </ul>
                </div>
            </div>
            
            <div class="box">    					<!----------菜品图区--------->
            	<c:forEach items="${dishes.content}" var="dishe" varStatus="status">
            		<div class="caip" onmousedown="addDishes('${dishe.dishesId}');">        
	                    <div>
	                   	 	<img src="http://${serverIp}/canyin-main${dishe.picUrl}" draggable="false"/>
	                    	<div class="jgbj">${dishe.price}</div>
	                        <div class="cm">
	                        	<c:if test="${fn:length(dishe.dishesName) > 18}">
		                       		${fn:substring(dishe.dishesName,0,17)}..
		                       	</c:if>
								<c:if test="${fn:length(dishe.dishesName) <= 18}">
									${dishe.dishesName}
								</c:if>
	                        </div>
	                    </div>
	           	  	</div>
            	</c:forEach>
            </div>
