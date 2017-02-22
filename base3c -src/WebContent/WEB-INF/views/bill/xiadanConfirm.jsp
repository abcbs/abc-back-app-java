<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>下单确认</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#popSave").bind("click",saveXiadanConfirm);
	
	function selectXiaDanHou(element){
		if($(element).hasClass("small_but_prbz_on")){
			$(element).removeClass("small_but_prbz_on");
		}
		else{
			/* $("[name=xiadanhou]").removeClass("small_but_prbz_on"); */
			
			$("[name=xiadanhou]").each(function(){
				if($(this).hasClass("small_but_prbz_on"))
				{
					$(this).removeClass("small_but_prbz_on");
				}
			});
			
			$(element).addClass("small_but_prbz_on");
		}
		
	}
	
	$("[name=xiadanhou]").click(function(){
		selectXiaDanHou($(this));
	});
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveXiadanConfirm();
		}
	},
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			closebox();
		}
	}
	],1);
	
	var etype = '${billPlaceEnterDeskOrPay}';
	if(etype == 1)
	{
		/* $("#xiadanEnterCantai").click(); */
		selectXiaDanHou($("#xiadanEnterCantai"));
	}
	else if(etype == 2)
	{
		/* $("#xiadanEnterJiezhang").click(); */
		selectXiaDanHou($("#xiadanEnterJiezhang"));
	}
	
	//最低消费提示
	var tableConsumeLow = $("#tableConsumeLow").val();
	var oriCost = $("#oriCost").val();
	if(Number(oriCost) < Number(tableConsumeLow))
	{
		$("#pop_tableConsumeLow_warn").show();
	}
});

//同步锁，每个函数一个
var syncLock = new SyncLock("popSave");

function saveXiadanConfirm()
{
	var ibillId = $("#id").val();
	var listSize = $("#listSize").val();
	if(listSize <= 0)
	{
		toastr.warning("没有变更，无需下单");
		BillChange(ibillId);
		return;
	}
	var enterType = 0;//原地
	$("[name=xiadanhou]").each(function(){
		if($(this).hasClass("small_but_prbz_on"))
		{
			enterType = $(this).attr("value");
		}
	});
	if(!syncLock.Start()) 
	   {
		   return;
	   }
	$.ajax({
	    type:"get",
	    url:"${ctx}/bill/xiadan/"+ibillId+"?enterType="+enterType,
	    data:null,
	    cache:false,
	    success:function(data){
	    	var billType = data.messageMap.billType;
	    	if(billType == 2){
	    		//跳转外卖模块
	    		window.location = window.ctx+'/takeout/list';
	    		return;
	    	}
	    	if(billType == 4){
	    		//跳转快餐模块
	    		window.location = window.ctx+'/fastfood/diancai';
	    		return;
	    	}
	    	if(data.statusCode && data.statusCode=='3')
	    	{
	    		BillChange(ibillId);
	    		rePrint(ibillId);
	    	}
	    	else if(data.statusCode && data.statusCode=='1')
	    	{
	    		//下单后进入餐台
	    		window.location = '${ctx}/index';
	    		toastr.info(data.message);
	    	}
	    	else if(data.statusCode && data.statusCode=='2')
	    	{
	    		//下单后进入结账
	    		window.location = '${ctx}/bill/payPage/'+data.rel;
	    		toastr.info(data.message);
	    	}
	    	else
	    	{
		    	BillChange(ibillId);
		    	toastr.info(data.message);
		    	if("undefined" == typeof refreshDishCatagoryChange)
				{
					
				}
				else
				{
					if(refreshDishCatagoryChange)
					{
						refreshDishCatagoryChange();
					}
				}
	    	}
	    	syncLock.End();
	    	closebox();
	    },
		error:function(){
			syncLock.End();
		}
	  });
}

</script>
</head>

<body>

	<input type="hidden" value="${dinerBill.billId}"  id="id" name="id"/>
	
	<input type="hidden" value="${fn:length(newList)}"  id="listSize" name="listSize"/>
	
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>下 单</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="small_popup_cont_auto">
			<div class="line_a"></div>
				<div class="xiadan_a">
					<h4 class="text_green left">新增菜品：</h4>
					<ul  style="margin: 0 0 10px 20px;">
						<c:forEach items="${newList}" var="dishe" varStatus="status">
							<li style="margin-right: 15px;width: 165px;">${dishe.dishesName}<div style="float:right;">x ${dishe.unitNumStr}${dishe.unitName}</div></li>
						</c:forEach>
					</ul>
					<h4 class="text_red left" style="display: none;" id="pop_tableConsumeLow_warn">当前账单不满足最低消费！</h4>
					<c:if test="${dinerBill.billType == '2' }"><h4 class="text_green left">外卖单可以在账单->账单列表->外卖单    中查看</h4></c:if>
				</div>
				<c:if test="${dinerBill.billType != '2' && dinerBill.billType != '4'}">
					<div class="line_b"></div>
					<div class="xiadan_b">
						<a name="xiadanhou" class="small_but_prbz ml_100 mr_10" value="1" style="cursor:pointer;" id="xiadanEnterCantai">下单后进入餐台</a>
						<a name="xiadanhou" class="small_but_prbz" style="cursor:pointer;"  value="2" id="xiadanEnterJiezhang">下单后进入结账</a>
					</div>
				</c:if>
				
				<div class="line_c"></div>
				<div class="but-area-s">
					<a class="small-but-queding mr_28"  style="cursor:pointer;" id="popSave"></a>
					<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();" ></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>