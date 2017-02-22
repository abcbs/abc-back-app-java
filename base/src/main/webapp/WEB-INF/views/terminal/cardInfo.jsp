<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="miao" id="daojishi">60秒</div>
<div class="hycx">会员卡查询</div>
<div class="huiyuanka"></div>
<div>
	<p>姓名：${membershipCard.restMemberInfo.name}</p>
	<p>储蓄余额：<fmt:formatNumber value="${membershipCard.balance}" type="currency" pattern="#"/>元</p>
	<p>消费累计：<fmt:formatNumber value="${consumeCashSum}" type="currency" pattern="#"/>元</p>
	<p>剩余积分：<fmt:formatNumber value="${membershipCard.memberIntegral}" type="currency" pattern="#"/></p>
</div>
<div class="HYK_right_anniu" onclick="reOrder();"></div>
