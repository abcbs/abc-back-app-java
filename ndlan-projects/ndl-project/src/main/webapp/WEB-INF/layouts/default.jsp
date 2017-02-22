<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>前台收银<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />  
<link href="${ctx}/static/css/foot.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/toastr.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery-ui.js" type="text/javascript"></script>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/popup/dialog.js"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/ProgressbarUtil.js" type="text/javascript"></script>
<script src="${ctx}/static/js/PageSizeUtil.js" type="text/javascript"></script>
<script src="${ctx}/static/js/CanYinHotKeys.js" type="text/javascript"></script>


<sitemesh:head/>
</head>

<body>
	<sitemesh:body/>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>