<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>自助选台</title>
<link href="${ctx}/static/css/self-desc.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
</head>
<body>

	<div class="diancan_body">
		<c:forEach items="${set}" var="item" varStatus="status">
			<div class="ewm">
				${item}
			</div>
		</c:forEach>
	</div>
</body>
</html>