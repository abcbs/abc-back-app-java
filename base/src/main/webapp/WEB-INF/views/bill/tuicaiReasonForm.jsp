<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>退菜</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		$("#popSave").bind("click",save);
		
		var hotKey = new CanYinHotKeys();
		hotKey.init($("body"),[{
			keyCode:"enter",
			callBackFunction:function()
			{
				save();
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
		
		
		$("a","#popButtonArea").click(function(){
			if(!$(this).hasClass("tuicai_on"))
			{
				$("a","#popButtonArea").removeClass("tuicai_on");
				$(this).addClass("tuicai_on");
				
			}
			else
			{
				$(this).removeClass("tuicai_on");
			}
			if($("a","#popButtonArea").hasClass("tuicai_on")){
				$("#newAddReason").attr("disabled",true);
				$("#newAddReason").val("");
			}else{
				$("#newAddReason").attr("disabled",false);
			}
		});
		
		$("a","#materialManager").click(function(){
			if(!$(this).hasClass("but_ylcl_on"))
			{
				$("a","#materialManager").removeClass("but_ylcl_on");
				$(this).addClass("but_ylcl_on");
			}
			else
			{
				$(this).removeClass("but_ylcl_on");
			}
		});
		
		$("#newTuiDishAdd").click(function(){
			var newDishNum = $("#newTuiDishNum").val();
			if(isNaN(newDishNum)){
				toastr.error("请输入正确数值");
				return;
			}
			newDishNum++;
			var maxDishNum = '${dinerBillDishe.unitNum}';
			if(parseFloat(newDishNum) <= parseFloat(maxDishNum))
			{
				//newDishNum = Number(newDishNum)+Number(1);
				newDishNum = newDishNum.toFixed(1);
				var intNewdishNum = parseInt(newDishNum);
				if(Number(newDishNum) == intNewdishNum){
					newDishNum = intNewdishNum;
				}
				$("#newTuiDishNum").val(newDishNum);
			}
		});
		$("#newTuiDishSub").click(function(){
			var newDishNum = $("#newTuiDishNum").val();
			if(isNaN(newDishNum)){
				toastr.error("请输入正确数值");
				return;
			}
			if(newDishNum > 1)
			{
				newDishNum = Number(newDishNum)-Number(1);
				newDishNum = newDishNum.toFixed(1);
				var intNewdishNum = parseInt(newDishNum);
				if(Number(newDishNum) == intNewdishNum){
					newDishNum = intNewdishNum;
				}
				$("#newTuiDishNum").val(newDishNum);
			}
		});
		
	});
	
	
	
	function save()
	{
		var url = $("#popSaveForm").attr("action");
		var cancelReasonId = "";
		var materialHand = "";
		var newAddReason = "";
		newAddReason =  $("#newAddReason").val();
		
		$("a","#popButtonArea").each(function(){
			if($(this).hasClass("tuicai_on"))
			{
				cancelReasonId = $(this).attr("id");
			}
		});
		if(newAddReason != null && newAddReason != ""){
			cancelReasonId = true;
		}
		//获取是否退还原料判断序号
		$("a","#materialManager").each(function(){
			if($(this).hasClass("but_ylcl_on"))
			{
				materialHand = $(this).attr("materialHand");
			}
		});
		
		if(cancelReasonId)
		{
			if(materialHand == ""){
				toastr.error("选择原料处理");
				return;
			}
			var cancelNum = $("#newTuiDishNum").val();
			var maxDishNum = '${dinerBillDishe.unitNum}';
			
			var reg = /^[\u4E00-\u9FA5]+$/; 
			if(reg.test(cancelNum)){ 
				toastr.error("非法数字");
				return;
			}
			
			if(cancelNum > parseFloat(maxDishNum))
			{
				toastr.error("不能大于点菜的数量");
				return;
			}
			if(cancelNum <= 0)
			{
				toastr.error("退菜数量必须大于0");
				return;
			}
			var num = cancelNum.replaceAll("\\.","-");
			var isSet = $("#isSet").val();
			$.ajax({
			    type:"get",
			    url:window.ctx+"/bill/dishTuicai/${dinerBillDishe.bdId}/"+cancelReasonId+"/"+num+"?isSet="+isSet+"&newAddReason="+newAddReason+"&materialHand="+materialHand,
			    data:null,
			    cache:false,
			    success:function(data){
			    	if(data.statusCode != 200)
			    	{
			    		toastr.error(data.message);
			    	}
			    	var ibillId = $("#currentBillId").val();
			    	BillChange(ibillId);
			    	refreshPayPageFunction();
			    	refreshDiancaiDishFunction();
			    },
				error:function(){}
			  });
		}
		else
		{
			toastr.error("选择退菜原因");
			return;
		}
		closebox();
	}
</script>
</head>
<body>
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3 class="tuicai-title">退菜</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<!--新增代码begin -->
			<div class="tuicai_sl">
				<h4>退菜数量</h4>
				<div class="ml_20 left">
					<a class="popup_but_minus mr_10" id="newTuiDishSub"></a>
					<input class="popup_but_blank mr_10" name="newTuiDishNum" id="newTuiDishNum" type="text" value="<fmt:formatNumber value="${dinerBillDishe.unitNum}" type="currency" pattern="#.##"/>">
					<span class="left mr_10 mt_7">份</span>
					<a class="popup_but_add" id="newTuiDishAdd"></a>
				</div>
			</div>
			<!--增加代码end -->
			<div class="line_d"></div>
			<div class="tuicai_box">
				<h4>退菜原因</h4>
				<div class="tuicai_wrap" id="popButtonArea">
					<c:forEach items="${speOpReasons}" var="speOpReason" varStatus="status">
						<c:if test="${status.index <= 12}">
						  <a href="###" style="cursor:pointer;" class="tuicai" id="${speOpReason.reaId}"><span><em>${speOpReason.name}</em></span></a>
						</c:if>
					</c:forEach>
					<input name="newAddReason" id="newAddReason" type="text" class="input108_44">
				</div>
			</div>
			<div class="line_d"></div>
			<div class="tuicai_sl">
				<h4>原料处理</h4>
				<div class="ml_20 left" id="materialManager">
					<a href="#" class="but_ylcl" materialHand="1">退还仓库</a>
					<a href="#" class="but_ylcl" materialHand="0">原料已消耗</a>
				</div>
			</div>
			<p class="ylcl_text">原料处理方式关联到原料数据，以及退菜报表，请慎重选择。</p>
			<div class="line_d"></div>
			<div class="but-area-s">
					<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
					<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
			</div>
		</div>
	</div>
</body>
</html>