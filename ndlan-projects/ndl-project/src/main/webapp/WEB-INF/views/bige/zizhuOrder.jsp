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
<title>比格自助点餐系统-成人自助</title>
<script  type="text/javascript">
$(document).ready(function() {
	isDaoshu = true;
	totalPrice = 0;
	stopSecond = 60;
	seconds = 60;
});

var isFirstSubmit = true;
function getBankCardNo(a)
{
	if(isFirstSubmit)
	{
		isFirstSubmit = false;
		var dinerBillZiZhuDisheStr = combineYouhuiDishes();
		if(dinerBillZiZhuDisheStr)
		{
			ProgressbarUtil.showG2();
			clearDaoshu();
			var billId = $("#billId").val();
			$.ajax({
			    type:"get",
			    url:"${ctx}/self/bige/ajax/getBankCardNo?dinerBillZiZhuDisheStr="+dinerBillZiZhuDisheStr+"&billId="+billId,
			    data:null,
			    cache:false,
			    async:true,
			    success:function(data){
			    	isFirstSubmit = true;
			    	checkBillIsSettledTime = null;
			    	checkBillIsSettledTimes = 0;
			    	if(data.statusCode == '200')
			    	{
			    		checkBillIsSettled(data.value);
		    			$("#billId").val(data.value);
			    	}
			    	else
			    	{
			    		changeBody('${ctx}/self/bige/payError');
			    	}
			    },
				error:function(){
					daoshu();
					isFirstSubmit = true;
					checkBillIsSettledTime = null;
			    	checkBillIsSettledTimes = 0;
					ProgressbarUtil.hideG2();
				}
			  });
		}
		else
		{
			isFirstSubmit = true;
		}
	}
}

var checkBillIsSettledTime = null;
var checkBillIsSettledTimes = 0;
function checkBillIsSettled(billId)
{
	//alert("checkBillIsSettled：第"+checkBillIsSettledTimes+"次");
	checkBillIsSettledTimes++;
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/ajax/checkBillIsSettled?billId="+billId,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	if(data.statusCode == '200')
	    	{
	    		clearTimeout(checkBillIsSettledTime);
	    		ProgressbarUtil.hideG2();
	    		daoshu();
	    		changeBody('${ctx}/self/bige/paySuccess?realCost='+data.type);
	    	/*	dialogBoxConfirm("支付确认","支付成功,如没有打印小票请马上联系工作人员！",function(){
	    			window.location='${ctx}/self/bige/index';
	    		},function(){
	    			window.location='${ctx}/self/bige/index';
	    		});
	    	*/
	    	}
	    	else if(data.statusCode == '300')
	    	{
	    		//没有结账成功
	    		if(checkBillIsSettledTimes <= 30)
    			{
	    			checkBillIsSettledTime=setTimeout(function(){
		    			checkBillIsSettled(data.value);
		    		},5000);
    			}
	    		else
    			{
	    			ProgressbarUtil.hideG2();
	    			dialogBoxConfirm("支付确认","操作超时,重新点菜请取消!",function(){
		    			window.location='${ctx}/self/bige/index';
		    		},function(){
		    			window.location='${ctx}/self/bige/index';
		    		});
    			}
	    	}
	    	else if(data.statusCode == '400')
	    	{
	    		//退出了
	    		ProgressbarUtil.hideG2();
	    	}
	    	else if(data.statusCode == '500')
	    	{
	    		//Message
	    		ProgressbarUtil.hideG2();
	    		dialogBoxConfirm("支付出错","点击确定重新支付，点击取消退出。"+data.message,function(){
	    		},function(){
	    			window.location='${ctx}/self/bige/index';
	    		});
	    	}
	    },
		error:function(){
			ProgressbarUtil.hideG2();
			clearTimeout(checkBillIsSettledTime);
			toastr.warning("支付出错！");
			daoshu();
		}
	  });
}

function checkBillStatus(billId)
{
	dialogBoxConfirm("支付确认","如果支付成功请点击确定，失败请取消!",function(){
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/ajax/checkBillStatus?billId="+billId,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(data){
		    	if(data.statusCode == '200')
		    	{
		    		changeBody('${ctx}/self/bige/paySuccess?realCost='+data.type);
		    	}
		    	else if(data.statusCode == '800')
		    	{
		    		checkBillStatus(billId);
		    	}
		    	else
		    	{
		    		toastr.warning("支付出错！");
		    		checkBillStatus(billId);
		    	}
		    },
			error:function(){
			}
		  });
	},function(){
		window.location='${ctx}/self/bige/index';
	});
}
function getMemberCardNo()
{
	return;
	var dinerBillZiZhuDisheStr = combineYouhuiDishes();
	if(dinerBillZiZhuDisheStr)
	{
		$.ajax({
		    type:"get",
		    url:"${ctx}/self/bige/ajax/getMemberCardNo?dinerBillZiZhuDisheStr="+dinerBillZiZhuDisheStr,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(data){
		    	if(data.statusCode == '200')
		    	{
		    		window.location='${ctx}/self/bige/paySuccess';
		    	}
		    	else
		    	{
		    		window.location='${ctx}/self/bige/payError';
		    	}
		    },
			error:function(){
			}
		  });
	}
}
</script>
</head>
<body id="body">

<form action="${ctx}/self/zizhu/zizhuMemberPay" id="payForm" method="post">

<input type="hidden" value="" name="billId" id="billId" />
<input type="hidden" value="60" name="unitPrice" id="unitPrice" />
<input type="hidden" value="0" name="type" id="type" />
<input type="hidden" value="0" name="realCost" id="realCost" />
<input type="hidden" value="" name="dinerBillZiZhuDisheStr" id="dinerBillZiZhuDisheStr" />


<div class="header">
		<div class="bige_logo"></div>
		<p class="time">AM10:10</p>
		<div class="G2_logo" onclick="goAdmin();"></div>
	</div>
	<div class="zizhu_main">
		<p class="zizhu_text">
		温馨提示：午餐时间为11：00—14：00，晚餐时间为17：00—21：00，请在规定时间内用餐，祝您用餐愉快！</br>
		Tips: 11:00-14:00 lunch time, dinner time is 17:00-21:00, please dine within the specified time, I wish you a happy meal!</br>
		안내말씀: 오전식사시간  11:00-14:00    저녁식사시간  17:00-21:00, 식사시간은 규정한 식사시간내에 하시길 바랍니다. 유쾌한 식사시간 되십시요! </br>		
		</p>
		<div class="box">
			<div class="list_first">
				<a class="but_adult_on" onclick="changeDinnerType(this,0,'${ctx}/static/bige/imgs/adult_img.png',60);">
				</a>
				<a class="but_child" onclick="changeDinnerType(this,1,'${ctx}/static/bige/imgs/child_img.png',39);"></a>
				<c:if test="${dayOfWeek==2}">
					<a class="but_man" onclick="changeDinnerType(this,2,'${ctx}/static/bige/imgs/man_img.png',49);"></a>
				</c:if>
				<c:if test="${dayOfWeek!=2}">
					<a class="but_man_disable" ></a>
				</c:if>
				
				<c:if test="${dayOfWeek==3}">
					<a class="but_woman" onclick="changeDinnerType(this,3,'${ctx}/static/bige/imgs/woman_img.png',49);"></a>
				</c:if>
				<c:if test="${dayOfWeek!=3}">
					<a class="but_woman_disable" ></a>
				</c:if>
				
				<c:if test="${dayOfWeek==1}">
					<a class="but_old" onclick="changeDinnerType(this,4,'${ctx}/static/bige/imgs/old_img.png',35);"></a>
				</c:if>
				<c:if test="${dayOfWeek!=1}">
					<a class="but_old_disable"></a>
				</c:if>
				
			</div>
			<div class="list_third">
				<div class="fenshu_area">
					<a class="but_left" onclick="minus(this,60,0);"></a>
					<div class="fenshu" contenteditable="true">00</div>
					<a class="but_right" onclick="add(this,60,0);"></a>
					<div class="fenshu1">位</div>
					
				</div>
				<div class="fenshu_area">
					<a class="but_left" onclick="minus(this,39,1);"></a>
					<div class="fenshu" contenteditable="true">00</div>
					<a class="but_right" onclick="add(this,39,1);"></a>
					<div class="fenshu1">位</div>
				</div>
				
				<c:if test="${dayOfWeek==2}">
					<div class="fenshu_area">
						<a class="but_left" onclick="minus(this,49,2);"></a>
						<div class="fenshu" contenteditable="true">00</div>
						<a class="but_right" onclick="add(this,49,2);"></a>
						<div class="fenshu1">位</div>
					</div>
				</c:if>
				<c:if test="${dayOfWeek!=2}">
					<div class="fenshu_area">
						<a class="but_left1"></a>
						<div class="fenshu3" contenteditable="true">00</div>
						<a class="but_right1"></a>
						<div class="fenshu1">位</div>
					</div>
				</c:if>
				
				<c:if test="${dayOfWeek==3}">
					<div class="fenshu_area">
						<a class="but_left" onclick="minus(this,49,3);"></a>
						<div class="fenshu" contenteditable="true">00</div>
						<a class="but_right" onclick="add(this,49,3);"></a>
						<div class="fenshu1">位</div>
					</div>
				</c:if>
				<c:if test="${dayOfWeek!=3}">
					<div class="fenshu_area">
						<a class="but_left1"></a>
						<div class="fenshu3" contenteditable="true">00</div>
						<a class="but_right1"></a>
						<div class="fenshu1">位</div>
					</div>
				</c:if>
				
				<c:if test="${dayOfWeek==1}">
					<div class="fenshu_area">
						<a class="but_left" onclick="minus(this,35,4);"></a>
						<div class="fenshu" contenteditable="true">00</div>
						<a class="but_right" onclick="add(this,35,4);"></a>
						<div class="fenshu1">位</div>
					</div>
				</c:if>
				<c:if test="${dayOfWeek!=1}">
					<div class="fenshu_area">
						<a class="but_left1"></a>
						<div class="fenshu3" contenteditable="true">00</div>
						<a class="but_right1"></a>
						<div class="fenshu1">位</div>
					</div>
				</c:if>
				
				
				
				
			
			</div>
			
			<div class="list_sec">
				<img src="${ctx}/static/bige/imgs/adult_img.png" id="changeImg">
				<div class="zongjia_wrap">
					<div class="left">
						<p><span class="font36">合计</span></br>Total</br>합계</p>
						<div class="shuzi" contenteditable="true"  id="buyNum">0</div>
						<p><span class="font36">位</span></br>Per</br>다섯분</p>
					</div>
					<div class="right">
						<p><span class="font36">合计</span></br>Total</br>합계</p>
						<div class="shuzi" contenteditable="true" id="realCostShow">0</div>
						<p><span class="font36">元</span></br>Yuan</br>원</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="but_wrap3">
			<a class="but_cxdc" onclick="reOrder();"></a>
			<a class="but_yhkzf" onclick="getBankCardNo(this);"></a>
			<a class="but_fh" onclick="window.location='${ctx}/self/bige/index'"></a>
		</div>
	</div>


</form>
</body>
</html>