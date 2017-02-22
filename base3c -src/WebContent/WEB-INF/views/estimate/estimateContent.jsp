<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

			<div class="guqing_nav">
					<ul id="indexDishesAraaUl">
						<li>
							<c:choose>
								<c:when  test="${(categoryId == null || categoryId == '') && (dsCategoryId == null || dsCategoryId == '')}">
									<a class="a_on" style="cursor:pointer;">全部</a>
								</c:when>
								<c:otherwise >	
									<a class="p_quanbu" style="cursor:pointer;"  href="javascript:void(0);" onclick="dishCatagoryChange('${billId}','','1','','')" >全部</a>
								</c:otherwise>
							</c:choose>
						
						</li>
						<c:forEach items="${dishesCategorys}" var="dishesCategory" varStatus="status">
							<c:choose>
								<c:when  test="${categoryId == dishesCategory.categoryId}">
									<li><a style="cursor:pointer;" class="a_on">${dishesCategory.categoryName}</a></li>
								</c:when>
								<c:otherwise >	
									<li><a style="cursor:pointer;"  href="javascript:void(0);"  onclick="dishCatagoryChange('','${dishesCategory.categoryId}','1','','')" class="a_c">${dishesCategory.categoryName}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:forEach items="${dishesSetCategorys}" var="dishesSetCategory" varStatus="status">
							<c:choose>
								<c:when test="${dsCategoryId == dishesSetCategory.dsCategoryId}">
									<li><a style="cursor:pointer;" href="javascript:void(0);" class="a_on">${dishesSetCategory.categoryName}</a></li>
								</c:when>
								<c:otherwise>
									<li><a style="cursor:pointer;" href="javascript:void(0);"  onclick="dishCatagoryChange('','','1','${dishesSetCategory.dsCategoryId}','')" class="<c:if test="${status.last}">last_tag</c:if>">${dishesSetCategory.categoryName}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
					</ul>
					<a href="javascript:void(0);" class="but_back" id="dish_left"></a>
					<a href="javascript:void(0);" class="but_go" id="dish_right"></a>
			</div>
			<div class="guqing_wrap" id="indexDishesContent">
						<c:forEach items="${dishes.content}" var="dishe" varStatus="status">
							<c:if test="${dishe.dishAndSetDiv == '1'}">
								<div class="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">guqing_green</c:if><c:if test="${dishe.estimate<=0}">guqing_red</c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">guqing_yellow</c:if>" oldClass="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">guqing_green</c:if><c:if test="${dishe.estimate==0}">guqing_red</c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">guqing_yellow</c:if>" onclick="disheDivClick(event,this);" dishesId="${dishe.dishesId}" dishesType="1"  tabindex="1" isRulingPrice="${dishe.isRulingPrice}" estimate="${dishe.estimate}" dishesName="${dishe.dishesName}">
									<div>
										<p>
										<c:if test="${dishe.estimate>=0}">
										<span id="estimate">
											<fmt:formatNumber value="${dishe.estimate}" type="currency" pattern="#.##"/>
										</span>
										</c:if>
										<span  id="dishesName" <c:if test="${dinerBill.billType == '2' }"><c:if test="${dishe.isTakeout == '1'}">style="height:36px;"</c:if></c:if>>
										<c:if test="${fn:length(dishe.dishesName) > 12}"><a title="${dishe.dishesName}">${fn:substring(dishe.dishesName,0,11)}..</a></c:if>
										<c:if test="${fn:length(dishe.dishesName) <= 12}">${dishe.dishesName}</c:if>
										<br/>
										<span class="hideTooLongChar">${dishe.dishesCode}</span>
										</span>
										<span id="dishesCode" v="${dishe.dishesCode}"></span>
										<span></span>
										<span id="price" style="display:none;">${dishe.price}</span>
										<span id="dishesId" style="display:none;">${dishe.dishesId}</span>
										<span id="unitName" style="display:none;">${dishe.dishesUnitName}</span>
										</p>
									</div>
								</div>
							</c:if>
							
							<c:if test="${dishe.dishAndSetDiv == '2'}">
								<div class="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">guqing_green</c:if><c:if test="${dishe.estimate<=0}">guqing_red</c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">guqing_yellow</c:if>" oldClass="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">guqing_green</c:if><c:if test="${dishe.estimate==0}">guqing_red</c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">guqing_yellow</c:if>" onclick="disheDivClick(event,this);" dishesId="${dishe.dishesId}" dishesType="2" tabindex="1" isRulingPrice="${dishe.isRulingPrice}" estimate="${dishe.estimate}" dishesName="${dishe.dishesName}">
									<div>
										<p>
										<c:if test="${dishe.estimate>=0}">
										<span id="estimate">
											<fmt:formatNumber value="${dishe.estimate}" type="currency" pattern="#.##"/>
										</span>
										</c:if>
										<span id="dishesName" <c:if test="${dinerBill.billType == '2' }"><c:if test="${dishe.isTakeout == '1'}">style="height:36px;"</c:if></c:if>>
										<c:if test="${fn:length(dishe.dishesName) > 18}"><a title="${dishe.dishesName}">${fn:substring(dishe.dishesName,0,17)}..</a></c:if>
										<c:if test="${fn:length(dishe.dishesName) <= 18}">${dishe.dishesName}</c:if>
										<br/>
										</span>
										<span id="dishesCode" v="${dishe.dishesCode}">${dishe.dishesCode}</span>
										<span></span>
										<span id="price" style="display:none;">${dishe.price}</span>
										<span id="dishesId" style="display:none;">${dishe.dishesId}</span>
										<span id="unitName" style="display:none;">${dishe.dishesUnitName}</span>
										</p>
									</div>
								</div>
							</c:if>
							
						</c:forEach>
			</div>
			
			<div class="hy_sousuo">
				<div class="search_sec">
						<input name="keywords" id="keywords" type="text"  tabindex="1" value="${keywords}">
						<button style="cursor:pointer;" onclick="keyWordSearch();"></button>
					</div>
					<div class="right">
						<tags:paginationdiancai page="${dishes}" paginationSize="15"/>
					</div>
			</div>