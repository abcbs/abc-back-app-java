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
<div class="text_zh">
	<input id="cardNo" class="text_zh_1" type="text" value="">
</div>
<div class="text_mm">
	<input id="cardPassword" class="text_mm_1" type="password" value="">
</div>
<div class="HYK_right_anniu" onclick="refreshDish();"></div>
<div class="HYK_right_anniu2" onclick="validateCard();"></div>
