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
<title>xxx自助点餐系统</title>
<script  type="text/javascript">
$(document).ready(function() {
	totalPrice = 0;
	cleatConpareTime();
});
</script>
</head>
<body>

<form action="${ctx}/self/zizhu/zizhuMemberPay" id="payForm" method="post">

<input type="hidden" value="${billId}" name="billId" id="billId" />
<input type="hidden" value="0" name="realCost" id="realCost" />
<input type="hidden" value="${bill.dinerBillZiZhuDisheStr}" name="dinerBillZiZhuDisheStr" id="dinerBillZiZhuDisheStr" />
<input type="hidden" value="${buffetSetting.cbsId}" name="cbsId" id="cbsId" />

<div class="header">
		<h1 class="logo"></h1><span class="time">2014年01月11日 11:30</span>
		<a class="close" style="display: none;"></a>
	</div>
	<div class="main">
		<p class="djs">60秒</p>
		<p class="address">www.xxx.cn</p>
		<div class="<c:if test="${buffetSetting.buffetType == '0' }">white_box1</c:if><c:if test="${buffetSetting.buffetType == '1' }">white_box2</c:if><c:if test="${buffetSetting.buffetType == '2' }">white_box3</c:if><c:if test="${buffetSetting.buffetType == '3' }">white_box4</c:if>">
			<h3 class="<c:if test="${buffetSetting.buffetType == '0' }">title_1</c:if><c:if test="${buffetSetting.buffetType == '1' }">title_2</c:if><c:if test="${buffetSetting.buffetType == '2' }">title_3</c:if><c:if test="${buffetSetting.buffetType == '3' }">title_4</c:if>">${buffetSetting.buffetName}</h3>
			
			
			<div class="can_list" price="${oriPrice}" ziZhuYouhuiName="成人">
				<p>成人</p>
				<span>￥<fmt:formatNumber value="${oriPrice}" type="currency" pattern="#.##"/>/位</span>
				<div class="caozuo_wrap">
					<a class="minus" onclick="minus(this,'${oriPrice}');"></a>
					<input name="orderNum" id="orderNum" type="text" value="0">
					<a class="add" onclick="add(this,'${oriPrice}');"></a>				
				</div>
			</div>
			
			<c:forEach items="${youhuis}" var="youhui" varStatus="status">
				<div class="can_list"  price="${youhui.price}"  ziZhuYouhuiName="${youhui.name}">
					<p>${youhui.name}</p>
					<span>￥<fmt:formatNumber value="${youhui.price}" type="currency" pattern="#.##"/>/位</span>
					<div class="caozuo_wrap">
						<a class="minus" onclick="minus(this,'${youhui.price}');"></a>
						<input name="orderNum" id="orderNum" type="text" value="0">
						<a class="add" onclick="add(this,'${youhui.price}');"></a>				
					</div>
					
					<div class="tixing"  <c:if test="${youhui.name != '老人儿童'}">style="display: none;"</c:if>>亲，该优惠仅限70岁以上老人及身高1.2米以下儿童享用，选错补差价噢！</div>
				</div>
			</c:forEach>
			
			
			<div class="zongjia"><p>总价</p><span>￥<span id="realCostShow">0</span></span></div>
			<div class="but_area">
				<a class="but_zizu" onclick="reOrder();">重新点单</a>
				<a class="but_zizu" onclick="zizhuMemberPay('${ctx}','${billId}')">会员卡支付</a>
				<a class="but_zizu" onclick="zizhuBankCardPay('${ctx}','${billId}')">银行卡支付</a>
				<a class="but_zizu" onclick="window.location='${ctx}/self/zizhu/cancelBill?billId=${billId}'">取消订单</a>
			</div>
		</div>
	</div>
</form>
</body>
</html>