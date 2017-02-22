<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
</head>

<body>
餐饮管理系统

   <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>任务</th><th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${dishes}" var="dishe">
			<tr>
				<td><a href="${ctx}/dishes/update/${dishe.dishesId}">${dishe.dishesName}</a></td>
				<td><a href="${ctx}/dishes/delete/${dishe.dishesId}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div><a class="btn" href="${ctx}/dishes/create">创建任务</a></div>
</body>
</html>