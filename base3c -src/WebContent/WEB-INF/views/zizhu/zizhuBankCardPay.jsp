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
	$("#bankCardNo").focus();
	cleatConpareTime();
	
	getBankCardNo();
});

function getBankCardNo()
{
	$.ajax({
	    type:"get",
	    url:"${ctx}/self/zizhu/ajax/getBankCardNo?billId=${bill.billId}",
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	if(data.statusCode == '200')
	    	{
	    		$("#resultCode").val(data.value);
	    		checBankCardInfo(null);
	    	}
	    	else
	    	{
	    		window.location='${ctx}/self/zizhu/payError';
	    	}
	    },
		error:function(){
		}
	  });
}
</script>
	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.but_beizhu,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom');</script>
	<![endif]-->
</head>
<body>

<form action="${ctx}/self/zizhu/zizhuBankCardPay/submit" id="paySubmitForm" method="post">
<input type="hidden" value="${bill.billId}" name="billId" id="billId" />
<input type="hidden" value="" name="resultCode" id="resultCode" />

<div class="header">
		<h1 class="logo"></h1><span class="time">2014年01月11日 11:30</span>
		<a class="close" style="display: none;"></a>
	</div>
	<div class="main">
		<p class="djs">60秒</p>
		<p class="address">www.xxx.cn</p>
		<div class="pay_card_bank"></div>
		<div class="input_area">
			<input name="bankCardNo" id="bankCardNo" type="text" placeholder="银行卡账号输入">
			<input name="bankCardPassword" id="bankCardPassword" type="password">
		</div>
		<div class="but_wrap_3">
			<a class="but_zizu" onclick="changeBody('${ctx}/self/zizhu/zizhuOrder?billId=${bill.billId}')">重新点单</a>
			<a class="but_zizu" onclick="changeBody('${ctx}/self/zizhu/cancelBill?billId=${bill.billId}')">取消订单</a>
			<a class="but_zizu" onclick="checBankCardInfo(this);">确认支付</a>
		</div>
	</div>
</form>
</body>
</html>