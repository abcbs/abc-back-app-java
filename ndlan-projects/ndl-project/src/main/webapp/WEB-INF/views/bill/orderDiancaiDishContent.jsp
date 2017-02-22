<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input  type="hidden" id="orderId" value="${orderId}"/>
<div class="list_b_in">
			
				<div class="tab_nav_wrap">
					<ul class="tab_nav">
						<li><a style="cursor:pointer;"  onclick="window.location='${ctx}/bill/list'">账单列表(1)</a></li>
						<li class="on"><a style="cursor:pointer;">点餐(2)</a></li>
						<li><a style="cursor:pointer;" onclick="toastr.info('预订账单无法结账');">结账(3)</a></li>
					</ul>
				</div>
				<div class="tab_nav_baby">
					<ul id="diancaiListUl">
						<li><a style="cursor:pointer;"  onclick="dishCatagoryChange('${orderId}','','1','','')" class="first_tag<c:if test="${(categoryId == null || categoryId == '') && (dsCategoryId == null || dsCategoryId == '')}">_on</c:if>">全部</a></li>
						<c:forEach items="${dishesCategorys}" var="dishesCategory" varStatus="status">
							<c:choose>
								<c:when test="${categoryId == dishesCategory.categoryId}">
									<li><a style="cursor:pointer;" href="###" class="<c:if test="${!status.last}">selected</c:if><c:if test="${status.last && fn:length(dishesSetCategorys) != 0}">selected</c:if><c:if test="${status.last && fn:length(dishesSetCategorys) == 0}">last_tag_on</c:if>">${dishesCategory.categoryName}</a></li>
								</c:when>
								<c:otherwise>
									<li><a style="cursor:pointer;" href="###"  onclick="dishCatagoryChange('${orderId}','${dishesCategory.categoryId}','1','','')" class="<c:if test="${status.last && fn:length(dishesSetCategorys) == 0}">last_tag</c:if>">${dishesCategory.categoryName}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:forEach items="${dishesSetCategorys}" var="dishesSetCategory" varStatus="status">
							<c:choose>
								<c:when test="${dsCategoryId == dishesSetCategory.dsCategoryId}">
									<li><a style="cursor:pointer;" href="###" class="<c:if test="${!status.last}">selected</c:if><c:if test="${status.last}">last_tag_on</c:if>">${dishesSetCategory.categoryName}</a></li>
								</c:when>
								<c:otherwise>
									<li><a style="cursor:pointer;" href="###"  onclick="dishCatagoryChange('${orderId}','','1','${dishesSetCategory.dsCategoryId}','')" class="<c:if test="${status.last}">last_tag</c:if>">${dishesSetCategory.categoryName}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
					</ul>
					<a style="cursor:pointer;" class="but_back" id="dish_left"></a>
					<a style="cursor:pointer;" class="but_go" id="dish_right"></a>
				</div>
				
				<div class="diancan_wrap">
					<c:forEach items="${dishes.content}" var="dishe" varStatus="status">
						<c:if test="${dishe.dishAndSetDiv == '1'}">
							<!-- 菜肴 -->
							<div class="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">diancan_green</c:if><c:if test="${dishe.estimate==0}">diancan_red</c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">diancan_yellow</c:if>" onclick="addDishes(this);" tabindex="1" isRulingPrice="${dishe.isRulingPrice}" isTakeout="${dishe.isTakeout}" estimate="${dishe.estimate}">
								<div>
									<p>
									<span id="estimate">
										<c:if test="${dishe.estimate>=0}">
										<fmt:formatNumber value="${dishe.estimate}" type="currency" pattern="#.##"/>
										</c:if>
									</span>
										
									<span id="orderNum" style="display:none;">1</span>
									<span id="dishesName" <c:if test="${dinerBill.billType == '2' }"><c:if test="${dishe.isTakeout == '1'}">style="height:36px;"</c:if></c:if>>
									
									<c:if test="${fn:length(dishe.dishesName) > 18}"><a title="${dishe.dishesName}">${fn:substring(dishe.dishesName,0,17)}..</a></c:if>
									<c:if test="${fn:length(dishe.dishesName) <= 18}">${dishe.dishesName}</c:if>
									
									
									</span>
									<span id="dishesCode" v="${dishe.dishesCode}">
										<c:if test="${dishe.isRulingPrice == '1'}">(时价)</c:if>
										<c:if test="${dinerBill.billType == '2' }"><c:if test="${dishe.isTakeout == '1'}">(外卖)</c:if></c:if>
									</span>
									
									<span id="price" style="display:none;">${dishe.price}</span>
									<span id="dishesId" style="display:none;">${dishe.dishesId}</span>
									<span id="unitName" style="display:none;">${dishe.dishesUnitName}</span>
									</p>
								</div>
							</div>
						</c:if>
						<c:if test="${dishe.dishAndSetDiv == '2'}">
						<!-- 套餐 -->
								<div class="<c:if test="${dishe.estimate == null || dishe.estimate>=10}">diancan_green</c:if><c:if test="${dishe.estimate==0}">diancan_red</c:if><c:if test="${dishe.estimate>0 && dishe.estimate<10}">diancan_yellow</c:if>" onclick="dishesSetForm(this,'${dishe.dishesId}');" tabindex="1" isRulingPrice="0" isTakeout="${dishe.isTakeout}" estimate="${dishe.estimate}">
									<div>
										<p>
										<span id="estimate">
											<c:if test="${dishe.estimate>=0}">
											<fmt:formatNumber value="${dishe.estimate}" type="currency" pattern="#.##"/>
											</c:if>
										</span>
											
										<span id="orderNum" style="display:none;">1</span>
										<span id="dishesName" <c:if test="${dinerBill.billType == '2' }"><c:if test="${dishe.isTakeout == '1'}">style="height:36px;"</c:if></c:if>>
										
										<c:if test="${fn:length(dishe.dishesName) > 18}"><a title="${dishe.dishesName}">${fn:substring(dishe.dishesName,0,17)}..</a></c:if>
										<c:if test="${fn:length(dishe.dishesName) <= 18}">${dishe.dishesName}</c:if>
										
										
										</span>
										<span id="dishesCode" v="${dishe.dishesCode}">
											<c:if test="${dinerBill.billType == '2' }"><c:if test="${dishe.isTakeout == '1'}">(外卖)</c:if></c:if>
										</span>
										
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
				
</div>