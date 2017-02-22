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
	<form id="inputForm" action="${ctx}/dishes/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${dishe.dishesId}"/>
		<fieldset>
			<legend><small>管理菜肴</small></legend>
			<div class="control-group">
				<label for="task_title" class="control-label">菜肴名称:</label>
				<div class="controls">
					<input type="text" id="dishesName" name="dishesName"  value="${dishe.dishesName}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="description" class="control-label">菜肴编码:</label>
				<div class="controls">
					<textarea id="dishesCode" name="dishesCode" class="input-large">${dishe.dishesCode}</textarea>
				</div>
			</div>	
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	
</body>
</html>