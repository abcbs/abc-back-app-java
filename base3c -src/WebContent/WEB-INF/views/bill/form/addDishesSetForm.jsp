<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>商务套餐</title>
<script type="text/javascript" src="${ctx}/static/js/bill/addDishesSetForm.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>${dishesSet.dsName}</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="swtc_wrap">
				<div class="mr_wrap">
					<input type="hidden" id="isTakeout" value="${dishesSet.isTakeout}">
					<input type="hidden" id="dsId" value="${dishesSet.dsId}">
					<input type="hidden" id="type" value="${type}">
					<input type="hidden" id="isOrder" value="${isOrder}">
					<input type="hidden" id="orderId" value="${orderId}">
					<input type="hidden" id="bdsId" value="${bdsId}">
					<div class="mr_title"><h3>默认菜肴</h3><span>（${fn:length(dishesSet.dishesSetDishes)}份）</span></div>
					<div class="mr_cont" style='OVERFLOW: auto;'>
						<c:choose>
							<c:when test="${type eq 'editSet'}">
								<c:forEach var="dishesSetDishesVo" items="${dishesSetDishesVoList}" varStatus="status">
									<div class="mr_cai mrcy" onclick="replaceList(this,'${dishesSet.dsId}','${dishesSetDishesVo.dsDishesId}');" 
										 dsDishesId="${dishesSetDishesVo.dsDishesId}" 
										 dishesId="${dishesSetDishesVo.dishesId}" 
										 unitNum="${dishesSetDishesVo.unitNum}" 
										 unitName="${dishesSetDishesVo.unitName}" 
										 dishesName="${dishesSetDishesVo.dishesName}" 
										 dishesCode="${dishesSetDishesVo.dishesCode}" 
										 mr_dishesId="${dishesSetDishesVo.mrDishesId}" 
										 mr_unitNum="${dishesSetDishesVo.mrUnitNum}" 
										 mr_unitName="${dishesSetDishesVo.mrUnitName}" 
										 mr_dishesName="${dishesSetDishesVo.mrDishesName}" 
										 mr_dishesCode="${dishesSetDishesVo.mrDishesCode}"> 
										<div>
											<p>
												<span>
													${dishesSetDishesVo.dishesName}
													<br/>
													${dishesSetDishesVo.unitNum}${dishesSetDishesVo.unitName}
													<br/>
													${dishesSetDishesVo.dishesCode}
												</span>
												<span></span>
												<span></span>
											</p>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="dishesSetDishe" items="${dishesSet.dishesSetDishes}" varStatus="status">
									<div class="mr_cai mrcy" onclick="replaceList(this,'${dishesSet.dsId}','${dishesSetDishe.dsDishesId}');" 
										 dsDishesId="${dishesSetDishe.dsDishesId}" 
										 dishesId="${dishesSetDishe.dishe.dishesId}" 
										 unitNum="${dishesSetDishe.unitNum}" 
										 unitName="${dishesSetDishe.unitName}" 
										 dishesName="${dishesSetDishe.dishesName}" 
										 dishesCode="${dishesSetDishe.dishe.dishesCode}" 
										 mr_dishesId="${dishesSetDishe.dishe.dishesId}" 
										 mr_unitNum="${dishesSetDishe.unitNum}" 
										 mr_unitName="${dishesSetDishe.unitName}" 
										 mr_dishesName="${dishesSetDishe.dishesName}" 
										 mr_dishesCode="${dishesSetDishe.dishe.dishesCode}"> 
										<div>
											<p>
												<span>
													${dishesSetDishe.dishesName}
													<br/>
													${dishesSetDishe.unitNum}${dishesSetDishe.unitName}
													<br/>
													${dishesSetDishe.dishe.dishesCode}
												</span>
												<span></span>
												<span></span>
											</p>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="swtc_page">
						<a href="###" class="but_up" onclick="mrUp();"></a>
						<a href="###" class="but_down" onclick="mrDown();"></a>
					</div>
				</div>
				<div class="th_wrap">
					<div class="swtc_arrow"></div>
					<div class="th_title"><h3>替换菜肴</h3></div>
					<div class="th_cont" id="replaceDishes" style='OVERFLOW: auto;'>
						
					</div>	
					<div class="swtc_page">
						<a href="###" class="but_up"  onclick="reUp();"></a>
						<a href="###" class="but_down"  onclick="reDown();"></a>
					</div>
				</div>
				<div class="line_w"></div>
			</div>
			<div class="but-area-e">
				<a id="addDishesSet" class="but-queding mr_28" href="###"></a>
				<a class="but-quxiao" href="###" onclick="closebox();"></a>
			</div>
		</div>
	</div>
</body>
</html>