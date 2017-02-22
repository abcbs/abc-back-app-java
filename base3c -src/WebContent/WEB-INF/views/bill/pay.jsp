<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>点餐</title>
<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/FloatWindow.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bill/diancaiBill.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bill/pay.js"></script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.but_beizhu,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom');</script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		if('${message}'!=''){
			toastr.success('${message}');
		}
	});
</script>
</head>
<body>
<!--页面主体begin -->

<input  type="hidden" id="currentBillId" value="${billId}"/>
	<div class="body_sec">
		<div class="list_a"  id="ajaxBillContent">
		</div>
		<div class="list_b" id="ajaxPayContent">
		</div>
	</div>
	<!--页面主体end -->
		<!-- 权限加载 -->
<input type="hidden" id="permission_discount" value="<shiro:hasPermission name="frontdesk_bill_discount:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_forcePay" value="<shiro:hasPermission name="frontdesk_bill_forcePay:create">1</shiro:hasPermission>">
	
<input type="hidden" id="permission_payPrint" value="<shiro:hasPermission name="frontdesk_bill_payPrint:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_payNoPrint" value="<shiro:hasPermission name="frontdesk_bill_payNoPrint:create">1</shiro:hasPermission>">
	
	
</body>
</html>