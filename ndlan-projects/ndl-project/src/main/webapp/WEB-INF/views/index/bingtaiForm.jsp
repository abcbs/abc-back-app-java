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
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#popSave").bind("click",popSelected);
	bindPopTableAreaEvent();
	
	var left = $("#left").val();
	$("#kexuan_title_ul").css({marginLeft:left+"px"});
});
var currentPopTable= null;
function popSelected()
{
	if(currentPopTable != null && currentPopTable.attr("id"))
	{
		var url = $("#popSaveForm").attr("action");
		jQuery.ajax({
			url: url,
			data: $('#popSaveForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				var isOK = data.statusCode;
				if(isOK == '200')
				{
					refreshTable();
					toastr.success(data.message);
				}
				else
				{
					toastr.error(data.message);
				}
			}
		});
		closebox();
	}
	else
	{
		toastr.error("选择一个餐台");
	}
}


function bindPopTableAreaEvent()
{
	$("div",$(".kexuan")).disableSelection();
	$("div",$(".kexuan")).click(function(event){
		event.stopPropagation();
		if(!$(this).hasClass("on"))
		{
			removeAllPopOnClass();
			$(this).addClass("on");
			currentPopTable = $(this);
			$("#tabId").val(currentPopTable.attr("id"));
		}
	});
	$("#pop_table_left").click(function(){
		var left = $("#kexuan_title_ul").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(left < 0)
		{
			left+= 300;
			$("#kexuan_title_ul").animate({marginLeft:left+"px"},100);
		}
		
	});
	
	$("#pop_table_right").click(function(){
		var left = $("#kexuan_title_ul").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		left-= 300;
		$("#kexuan_title_ul").animate({marginLeft:left+"px"},100);
		
	});
}
function removeAllPopOnClass()
{
	$("div",$(".kexuan")).each(function(){
		if($(this).hasClass("on"))
		{
			$(this).removeClass("on");
		}
	});
}

function changePopTableArea(searchParams,page)
{
	var left = $("#kexuan_title_ul").css("marginLeft");
	left = parseInt(left.replaceAll("px",""));
	var id = $("#oldTabId").val();
	var thisUrl = '${ctx}/index/pop/bingtai/'+id+'?left='+left+'&page='+page+'&'+searchParams;
	refreshPopForm(thisUrl);
}

</script>
</head>

<body>
<form id="popSaveForm" action="${ctx}/index/bingtai/update" method="post" >
<input type="hidden" value="${dinerBill.billId}"  id="id" name="id"/>
<input type="hidden" value="${dinerBill.table.tabId }"  id="oldTabId" name="oldTabId" />
<input type="hidden" value="${dinerBill.table.tabId }"  id="tabId" name="table.tabId" />
<input type="hidden" value="${left}"  id="left" name="left"/>
<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>并 台</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kexuan_title_wrap">
				<div class="kexuan_title">
					<h4>合并到：</h4>
					<ul id="kexuan_title_ul">
						<li>
							<c:choose>
								<c:when  test="${searchMapParams.EQ_tableArea_areaId == null || searchMapParams.EQ_tableArea_areaId == ''}">
									<a class="pop_first_tag_on" style="cursor:pointer;">全部</a>
								</c:when>
								<c:otherwise >	
									<a class="pop_first_tag" style="cursor:pointer;" onclick="changePopTableArea('search_EQ_tableArea.areaId=${tableArea.areaId}',1)">全部</a>
								</c:otherwise>
							</c:choose>
						</li>
						<c:forEach items="${tableAreas}" var="tableArea" varStatus="status">
							<c:choose>
								<c:when  test="${searchMapParams.EQ_tableArea_areaId == tableArea.areaId}">
									<li><a style="cursor:pointer;" class="<c:if test="${!status.last}">pop_selected</c:if><c:if test="${status.last}">pop_last_tag_on</c:if>">${tableArea.name}</a></li>
								</c:when>
								<c:otherwise >	
									<li><a style="cursor:pointer;" onclick="changePopTableArea('search_EQ_tableArea.areaId=${tableArea.areaId}',1);" class="<c:if test="${status.last}">pop_last_tag</c:if>">${tableArea.name}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
					<a href="###" class="but_back" id="pop_table_left"></a>
				  	<a href="###" class="but_go" id="pop_table_right"></a>
				</div>
			</div>
			<div class="kexuan">
				<c:forEach items="${tables.content}" var="table" varStatus="status">
					<div class="shiyong"  id="${table.tabId}" tabNo="${table.tabNo}">
						<span class="zhuozi">${table.tabNo}</span>
						<span class="time"></span>
						<span class="shuliang">${table.lastedTableNormalBillRelation.peopleNum}/${table.seat}</span>
					</div>
				</c:forEach>
			</div>
			<div class="kexuan_under_wrap">
				<div class="kexuan_under">
					<div class="kexuan_under_in">
						<tags:paginationpop page="${tables}" paginationSize="15"/>
						<a class="but-queding mr_7" style="cursor:pointer;" id="popSave"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="closebox(kuaijiejian);"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>	
</body>
</html>