<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/index/kaitaiForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/index/kaitai/${action}" method="post" >
		<input type="hidden" value="${dinerBill.billId}"  id="id" name="id"/>
		<input type="hidden" value="${orderId}"  id="orderId" name="orderId"/>
		<input type="hidden" value="${dinerBill.isEnterDiancaiPage}"  id="isEnterDiancaiPage" name="isEnterDiancaiPage"/>
		
<div class="popup_wrap" id="one">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>
					<c:if test="${orderId != null}">预 定 转 </c:if>
					开 台<c:if test="${action == 'update'}"> 修 改</c:if>
				</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="popup_cont">
				<fieldset class="kt_yuding">
					<div class="left w_330 h_50">
						<label class="label_100" for="interest">单      号 </label>
						<span>
							<c:choose>
								<c:when test="${orderId != null}">
									${orderNo}
								</c:when>
								<c:otherwise>
									${dinerBill.billNo}
								</c:otherwise>
							</c:choose>
						
						</span>
						<input type="hidden" value="${dinerBill.billNo}"  id="billNo" name="billNo" />
						<input class="input" type="hidden" value="${dinerBill.table.tabNo }"  id="tabNo" name="table.tabNo" />
					</div>
					<div class="left w_330 h_50">
						<label class="label_100" for="interest">开台员工</label>
						<span>${dinerBill.createEmployee.name }</span>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest">餐台</label>
						<input class="input" type="text" value="${dinerBill.table.tabName }"  id="tabName" name="table.tabName" />
						<input class="input" type="hidden" value="${dinerBill.table.tabId }"  id="tabId" name="table.tabId" />
						<input class="input" type="hidden" value="${dinerBill.table.tabId }"  id="oldTabId" name="oldTabId" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('选择餐台','${ctx}/index/pop/table/select','880','644');"></a>
						<p class="tishi text_blue" style="display:none">每小时30元，不参与特价，不打折。</p>
					</div>
					<div class="left w_330 h_64 relative">
						<label class="label_100" for="interest">就餐人数</label>
						<input class="input" type="text" value="${dinerBill.peopleNum}"  id="peopleNum" name="peopleNum"/>
						<a class="but-jianpan" style="cursor:pointer;" id="sudokuSelect"></a>
						<div class="jianpan_wrap" id="sudoku">
							<div class="jianpan">
								<a class="but_1" style="cursor:pointer;"></a>
								<a class="but_2" style="cursor:pointer;"></a>
								<a class="but_3" style="cursor:pointer;"></a>
								<a class="but_4" style="cursor:pointer;"></a>
								<a class="but_5" style="cursor:pointer;"></a>
								<a class="but_6" style="cursor:pointer;"></a>
								<a class="but_7" style="cursor:pointer;"></a>
								<a class="but_8" style="cursor:pointer;"></a>
								<a class="but_9" style="cursor:pointer;"></a>
								<a class="but_close" style="cursor:pointer;"></a>
								<a class="but_0" style="cursor:pointer;"></a>
								<a class="but_del" style="cursor:pointer;"></a>
							</div>
						</div>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">服务员</label>
						<input class="input" type="hidden" name="waiterId" id="waiterId" value="${dinerBill.waiterId}" />
						<input class="input" type="text" name="waiterName" id="waiterName" value="${dinerBill.waiterName}" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('服务员','${ctx}/index/pop/employee/select?type=3','880','644');"></a>
					</div>
					<div class="left w_330 h_64">
						<label class="label_100" for="interest">营销员</label>
						<input class="input" type="hidden" name="salesmanId" id="salesmanId" value="${dinerBill.salesmanId}" />
						<input class="input" type="text" name="salesmanName" id="salesmanName" value="${dinerBill.salesmanName}" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('营销员','${ctx}/index/pop/employee/select?type=5','880','644');"></a>
					</div>
					<div class="left w_600 h_64">
						<label class="label_100" for="interest">备注</label>
						<c:choose>
							<c:when test="${dinerBill.billStatus eq '2'}">
								<input class="input-big" type="text" disabled="disabled" value="${dinerBill.notes}"  id="notes" name="notes"/>
							</c:when>
							<c:otherwise>
								<input class="input-big" type="text" value="${dinerBill.notes}"  id="notes" name="notes"/>
							</c:otherwise>
						</c:choose>
						
					</div>
					<c:if test="${action == 'create'}">
						<div class="left w_600 h_64" id="isEnterDiancaiPageDiv">
							<label class="label_100" for="interest"> 进入点餐界面</label>
							<a class="but_a<c:if test="${dinerBill.isEnterDiancaiPage == 1}">_on</c:if> mr_28" value="1">是</a>
							<a class="but_a<c:if test="${dinerBill.isEnterDiancaiPage == 0}">_on</c:if>" value="0">否</a>			
						</div>
					</c:if>
					<div class="but-area-b">
						<a class="but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</form>
</body>
</html>