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
<title>会员</title>
<link href="${ctx}/static/css/huiyuan.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/member/list.js"></script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		if('${message}'!=''){
			//toastr.success('${message}');
		}
	});
</script>
</head>
<body>

<!--页面头部begin -->
	<div class="header">
		<h1 class="logo"></h1>
	</div>
	<!--页面头部end -->
	<!--页面主体begin -->
	<div class="body_huiyuan">
		<div class="list_h" id="ajaxContent">
			
		</div>
		<div class="list_y">
			<div class="hy_gongneng_1">
				<a id="jianka"  class="hy_ksfk mr_8"  href="###"></a>
				<a id="guashi"  class="hy_guashi_no" href="###"></a>
				<a id="tingyong"  class="hy_tingyong_no mr_8" href="###"></a>
				<a id="tuika"  class="hy_tuika_no" href="###"></a>
				<a id="chongzhi"  class="hy_chongzhi_no" href="###"></a>
			</div>
			<div class="hy_gongneng_2">
				<h3 id="memberListShow">会员卡状态－全部</h3>
				<a style="cursor:pointer;" onclick="dishCatagoryChange('',currentSearchParams,1,'');" href="###">全部</a>
				<a style="cursor:pointer;" class="hy_on" onclick="dishCatagoryChange('',currentSearchParams,1,1);" href="###">正常</a>
				<a style="cursor:pointer;" onclick="dishCatagoryChange('',currentSearchParams,1,2);" href="###">停用</a>
				<a style="cursor:pointer;" onclick="dishCatagoryChange('',currentSearchParams,1,3);" href="###">挂失</a>
				<!-- <a style="cursor:pointer;" onclick="dishCatagoryChange('',currentSearchParams,1,5);" href="###">退卡</a> -->
			</div>
			
		</div>
	</div>
	<!--页面主体end -->
	
			<!-- 权限加载 -->
<input type="hidden" id="permission_jianka" value="<shiro:hasPermission name="frontdesk_member_jianka:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_guashi" value="<shiro:hasPermission name="frontdesk_member_guashi:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_tingyong" value="<shiro:hasPermission name="frontdesk_member_tingyong:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_tuika" value="<shiro:hasPermission name="frontdesk_member_tuika:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_chongzhi" value="<shiro:hasPermission name="frontdesk_member_chongzhi:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_memberPrint" value="<shiro:hasPermission name="frontdesk_member_print:create">1</shiro:hasPermission>">
		
	
	
</body>
</html>