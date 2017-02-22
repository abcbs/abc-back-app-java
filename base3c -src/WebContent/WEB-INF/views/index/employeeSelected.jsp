<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	 
});

function selected(empId,name)
{
	var type = $("#type").val();
	if(type == '3')
	{
		//服务员
		var rj = {"waiterId":empId,"waiterName":name};
		popSelectCallBack(rj);
	}
	else if(type == '5')
	{
		//营销员
		var rj = {"salesmanId":empId,"salesmanName":name};
		popSelectCallBack(rj);
	}
}

function changePopTableArea(searchParams,page)
{
	var type = $("#type").val();
	var thisUrl = '${ctx}/index/pop/employee/select?type='+type+'&page='+page+'&'+searchParams;
	refreshPopForm(thisUrl);
}
</script>
</head>

<body>
<input type="hidden" value="${type}"  id="type" name="type"/>
<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3 class="kaitai-title">
				<c:if test="${type == '3'}">
						服务员
					</c:if>
					<c:if test="${type == '5'}">
						营销员
					</c:if>
				</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kexuan_title_wrap">
				<div class="kexuan_title">
					<c:if test="${type == null || type == '1'}">
						<h4>可选服务员</h4>
					</c:if>
					<c:if test="${type == '2'}">
						<h4>可选营销员</h4>
					</c:if>
				</div>
			</div>
			<div class="kexuan">
				<c:forEach items="${employees.content}" var="employee" varStatus="status">
					<div class="kxfyw" onclick="selected('${employee.empId}','${employee.name}');" style="cursor: pointer;">
						<div>
							<p>
								<span>${employee.name}</span>
								<span>${employee.empNum}</span>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="kexuan_under_wrap">
				<div class="kexuan_under">
					<div class="kexuan_under_in">
						<tags:paginationpop page="${employees}" paginationSize="15"/>
						<a class="but-queding mr_7" style="cursor:pointer;" onclick="popBack(1);"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="popBack(1);"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>