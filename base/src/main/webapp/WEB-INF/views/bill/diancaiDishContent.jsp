<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input  type="hidden" id="billId" value="${billId}"/>
<div class="list_b_in">
				<div class="tab_nav_wrap">
					<ul class="tab_nav">
						<c:if test="${billType != '4' || (billId != null && billId != '' )}">
							<li><a style="cursor:pointer;"  onclick="tabChange(this,'${ctx}/bill/list')">账单列表(1)</a></li>
							<li class="on"><a href="###" style="cursor:default;">点餐(2)</a></li>
							<li><a style="cursor:pointer;" onclick="tabChange(this,'${ctx}/bill/payPage')">结账(3)</a></li>
						</c:if>
						<c:if test="${billType == '4' && billId == '' }">
							<li class="on"><a href="###" style="cursor:default;">点餐</a></li>
						</c:if>
					</ul>
				</div>
				<div class="tab_nav_baby">
					<ul id="diancaiListUl">
						<li><a style="cursor:pointer;"  onclick="showFirstLeve(event,this);" onmouseover="showFirstLeve(event,this);" class="first_tag">${firstCategoryName}&nbsp;↓</a></li>
						<c:forEach items="${dishesCategorys}" var="dishesCategory" varStatus="status">
							<c:choose>
								<c:when test="${categoryId == dishesCategory.categoryId}">
									<li><a style="cursor:pointer;" href="###" class="<c:if test="${!status.last}">selected</c:if><c:if test="${status.last && fn:length(dishesSetCategorys) != 0}">selected</c:if><c:if test="${status.last && fn:length(dishesSetCategorys) == 0}">last_tag_on</c:if>">${dishesCategory.categoryName}</a></li>
								</c:when>
								<c:otherwise>
									<li><a style="cursor:pointer;" href="###"  onclick="dishCatagoryChange('${billId}','${dishesCategory.categoryId}','1','','')" class="<c:if test="${status.last && fn:length(dishesSetCategorys) == 0}">last_tag</c:if>">${dishesCategory.categoryName}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:forEach items="${dishesSetCategorys}" var="dishesSetCategory" varStatus="status">
							<c:choose>
								<c:when test="${dsCategoryId == dishesSetCategory.dsCategoryId}">
									<li><a style="cursor:pointer;" href="###" class="<c:if test="${!status.last}">selected</c:if><c:if test="${status.last}">last_tag_on</c:if>">${dishesSetCategory.categoryName}</a></li>
								</c:when>
								<c:otherwise>
									<li><a style="cursor:pointer;" href="###"  onclick="dishCatagoryChange('${billId}','','1','${dishesSetCategory.dsCategoryId}','')" class="<c:if test="${status.last}">last_tag</c:if>">${dishesSetCategory.categoryName}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
					</ul>
					<a style="cursor:pointer;" class="but_back" id="dish_left"></a>
					<a style="cursor:pointer;" class="but_go" id="dish_right"></a>
				</div>
				
				<div class="diancan_wrap"  id="diancaiDishDiv">
					<c:forEach items="${dishes.content}" var="dishe" varStatus="status">
						<c:if test="${dishe.dishAndSetDiv == '1'}">
							<!-- 菜肴 -->
							<div id="${dishe.dishesId}"  class="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">diancan_green<c:if test="${dishe.isOrdered == '1'}">_order</c:if></c:if><c:if test="${dishe.estimate==0}">diancan_red<c:if test="${dishe.isOrdered == '1'}">_order</c:if></c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">diancan_yellow<c:if test="${dishe.isOrdered == '1'}">_order</c:if></c:if>"   onclick="addDishes(this);" ondblclick="dbcAddDishes(this,window.event);" dishAndSetDiv="${dishe.dishAndSetDiv}" dishesId="${dishe.dishesId} tabindex="1" isRulingPrice="${dishe.isRulingPrice}" isTakeout="${dishe.isTakeout}" estimate="${dishe.estimate}">
								<div>
									<p>
									<c:if test="${dishe.estimate>=0}">
										<span id="estimate" >
											<fmt:formatNumber value="${dishe.estimate}" type="currency" pattern="#.##"/>
										</span>
									</c:if>
									<span id="orderNum" style="display:none;">1</span>
									<span id="dishesName" >
									<c:if test="${fn:length(dishe.dishesName) > 18}"><a title="${dishe.dishesName}">${fn:substring(dishe.dishesName,0,17)}..</a></c:if>
									<c:if test="${fn:length(dishe.dishesName) <= 18}">${dishe.dishesName}</c:if>
									<c:if test="${dishe.isRulingPrice == '1'}"><br/>(时价)</c:if>
									<c:if test="${billType == '2' }"></c:if>
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
						<!-- 套餐 -->
								<div class="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">diancan_green<c:if test="${dishe.isOrdered == '1'}">_order</c:if></c:if><c:if test="${dishe.estimate==0}">diancan_red<c:if test="${dishe.isOrdered == '1'}">_order</c:if></c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">diancan_yellow<c:if test="${dishe.isOrdered == '1'}">_order</c:if></c:if>" onclick="dishesSetForm(this,'${dishe.dishesId}');" dishAndSetDiv="${dishe.dishAndSetDiv}" dishesId="${dishe.dishesId}" tabindex="1" isRulingPrice="0" isTakeout="${dishe.isTakeout}" estimate="${dishe.estimate}">
									<div>
										<p>
											<c:if test="${dishe.estimate>=0}">
												<span id="estimate">
													<fmt:formatNumber value="${dishe.estimate}" type="currency" pattern="#.##"/>
												</span>
											</c:if>
										
											
										<span id="orderNum" style="display:none;">1</span>
										<span id="dishesName" >
										
										<c:if test="${fn:length(dishe.dishesName) > 18}"><a title="${dishe.dishesName}">${fn:substring(dishe.dishesName,0,17)}..</a></c:if>
										<c:if test="${fn:length(dishe.dishesName) <= 18}">${dishe.dishesName}</c:if>
										
										<c:if test="${billType == '2' }"></c:if>
										<br/>${dishe.dishesCode}
										</span>
										<span id="dishesCode" v="${dishe.dishesCode}">
											
										</span>
										<span></span>
										<span id="price" style="display:none;">${dishe.price}</span>
										<span id="dishesId" style="display:none;">${dishe.dishesId}</span>
										<span id="unitName" style="display:none;"></span>
										</p>
									</div>
								</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="diancan_but_area">
					<div class="search_sec">
						<input name="keywords" id="keywords" type="text"  tabindex="1" value="${keywords}" placeholder="输入简拼快速筛选">
						<button style="cursor:pointer;" onclick="keyWordSearch();"></button>
					</div>
					<a style="cursor:pointer;" class="but_zdycp" id="userDefined"></a>
					<tags:paginationdiancai page="${dishes}" paginationSize="15"/>
				</div> 
				
				
					<div class="dc_box_wrap hide" id="dc_box_wrap">
						<div class="dc_box">
							<div class="dc_box_in">
								<a onclick="dishCatagoryChange('${billId}','','1','','','all')">全部</a>
								<c:forEach items="${firstDishesCategorys}" var="dishesCategory" varStatus="status">
									<a class="hideTooLongChar" onclick="dishCatagoryChange('${billId}','${dishesCategory.categoryId}','1','','','${dishesCategory.categoryId}')" >${dishesCategory.categoryName}</a>
								</c:forEach>
								
								<c:forEach items="${firstDishesSetCategorys}" var="each" varStatus="status">
									<a class="hideTooLongChar" onclick="dishCatagoryChange('${billId}','','1','${each.dsCategoryId}','','${each.dsCategoryId}')" >${each.categoryName}</a>
								</c:forEach>
								
								
							</div>
						</div>
					</div>
				
</div>