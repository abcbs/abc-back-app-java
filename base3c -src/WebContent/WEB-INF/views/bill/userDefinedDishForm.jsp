<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#popSave").bind("click",saveUserDish);
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveUserDish();
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
	
	//是否打折
	$("a","#isOnsaleDiv").bind("click",function(){
		var a = $(this);
		if(!a.hasClass("but_a_on"))
		{
			$("a","#isOnsaleDiv").removeClass("but_a_on").addClass("but_a");
			a.removeClass("but_a").addClass("but_a_on");
			$("#isOnsale").val(a.attr("value"));
		}
	});
	
});
function saveUserDish()
{
	var url = $("#popSaveForm").attr("action");
	//验证
	var dishsName = $("#dishsName").val();
	var dishsCatagoryId = $("#dishsCatagoryId").val();
	var unitPrice = $("#unitPrice").val();
	if(!dishsName || dishsName.length == 0)
	{
		toastr.error("菜肴名称不能为空!");
		return;
	}
	if(dishsName.length > 20)
	{
		toastr.error("菜肴名称不能大于20个字符!");
		return;
	}
	if(!dishsCatagoryId)
	{
		toastr.error("菜肴分类不能为空!");
		return;
	}
	if(!unitPrice || isNaN(unitPrice) || unitPrice < 0)
	{
		toastr.error("菜肴价格为0-99999元，请重新填写!");
		return;
	}
	var tId = $("#tId").val();
	jQuery.ajax({
		url: url,
		data: $('#popSaveForm').serialize()+"&tId="+tId,
		type: "POST",
		dataType: "json",
		beforeSend: function()
		{  
		},
		success: function(data)
		{
			closebox();
	    	BillChange(data.value);
		}
	});
	
	
}

</script>
</head>
<body>

<form id="popSaveForm" action="<c:if test="${type == 'order'}">${ctx}/bill/orderUserdefined/create/${billId}</c:if><c:if test="${type == null}">${ctx}/bill/userdefined/create?billId=${billId}</c:if>" method="post" >
<input type="hidden" value="${billId}"  id="id" name="id"/>
<input type="hidden" value="${type}"  id="type" name="type"/>
<input type="hidden" value="${billType}"  id="billType" name="billType"/>
<input type="hidden" value="1"  id="isOnsale" name="isOnsale"/>
<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>自定义菜肴</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="zdycp_wrap">
				<div class="zdycp">
					<span><i class="red">*</i>菜品名称</span><input name="dishsName" id="dishsName" class="input_high" type="text" maxlength="15">
				</div>
				<br/>
				<div class="zdycp">
					<span><i class="red">*</i>菜肴分类</span>
					<div class="uboxstyle">
						<select name="dishsCatagoryId" id="dishsCatagoryId">
							<option value="">请选择</option>
							<c:forEach items="${dishesCategorys}" var="dishesCategory" varStatus="status">
								<option value="${dishesCategory.categoryId}">${dishesCategory.categoryName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="zdycp">
					<span>菜肴单位</span>
					<div class="uboxstyle">
						<select name="unitId" id="unitId">
							<c:forEach items="${dishesUnits}" var="dishesUnit" varStatus="status">
								<option value="${dishesUnit.unitId}">${dishesUnit.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="zdycp">
					<span><i class="red">*</i>单 价</span><input name="unitPrice" id="unitPrice" class="input_high" type="text" maxlength="5">
				</div>
				
				<div class="zdycp" id="isOnsaleDiv">
					<span><i class="red">*</i>是否打折</span>
					<a class="but_a_on mr_28" value="1">是</a>
					<a class="but_a" value="0">否</a>			
				</div>
						
				<div class="zdycp">
					<span>备 注</span>
					<textarea name="notes" id="notes"  cols="22" rows="3"></textarea>
				</div>
			</div>
			<div class="but-area-s">
				<a class="small-but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
				<a class="small-but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
			</div>
		</div>
	</div>
	<input type="text" style="display:none">
</form>
</body>
</html>