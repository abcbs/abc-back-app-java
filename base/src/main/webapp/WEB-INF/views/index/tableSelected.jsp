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
<style type="text/css">
.zhuozi em.p_em{width:90px;height:17px;overflow:hidden; margin: 0 auto;text-align:center;font-weight:bold;line-height:17px;display:block;*position: relative; *top:-50%; font-size:14px;word-wrap:break-word; word-break:break-all; }
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#popSelect").bind("click",popSelected);
	bindPopTableAreaEvent();
	var left = $("#left").val();
	$("#kexuan_title_ul").css({marginLeft:left+"px"});
	var tableAreaId = $("#selectTableAreaId").val();
	if(tableAreaId != ""){
		$("#selectTableAreaId").val("");//防止递归死循环
		changePopTableArea('search_EQ_tableArea.areaId='+tableAreaId,1);
	}
});
var currentPopTable= null;
function popSelected()
{
	var from = $("#from").val();//是否预订
	if(currentPopTable)
	{
		var tabId = currentPopTable.attr("id");
		var tabNo = currentPopTable.attr("tabNo");
		var tabName = currentPopTable.attr("tabName");
		
		var waiterId = currentPopTable.attr("waiterId");
		var waiterName = currentPopTable.attr("waiterName");
		
		//预订判断
		//是否有预订
		var isHasTableOrder = currentPopTable.attr("isHasTableOrder");
		if(from != 'yuding' && isHasTableOrder && isHasTableOrder == '1')//不是预订功能中的选餐台，则要判断是否已被锁定
		{
			var isAtOrderWarnTime = currentPopTable.attr("isAtOrderWarnTime");
			var isAtOrderLockTime = currentPopTable.attr("isAtOrderLockTime");
			var time = currentPopTable.find(".time").text();
			if(isAtOrderLockTime == '1')
			{
				//toastr.error("本桌"+time+" 被客人预订，已经锁定");
				return;
			}
		}
		var tabIdKey = $("#selectTabId").val();
		var tabNoKey = $("#selectTabNo").val();
		if(!tabIdKey)
		{
			tabIdKey = "tabId";
		}
		if(!tabNoKey)
		{
			tabNoKey = "tabNo";
		}
		//var rj = '[{"'+tabIdKey+'":"'+tabId+'","'+tabNoKey+'":"'+tabNo+'","waiterId":"'+waiterId+'","waiterName":"'+waiterName+'"}]';
		var rj = '[{"'+tabIdKey+'":"'+tabId+'","'+tabNoKey+'":"'+tabNo+'","tabName":"'+tabName+'","waiterId":"'+waiterId+'","waiterName":"'+waiterName+'"}]';
		popSelectCallBack(eval(rj)[0]);
	}
	else
	{
		$("#popBack")[0].click();
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
			popSelected();
		}
	});
	
	$("#pop_dish_left").click(function(){
		var left = $("#kexuan_title_ul").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		
		if(left < 0)
		{
			left = left + 300 > 0?0:left + 300;
			$("#kexuan_title_ul").animate({marginLeft:left+"px"},100);
		}
		
	});
	
	$("#pop_dish_right").click(function(){
		var left = $("#kexuan_title_ul").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		var width = 0;
		$("#kexuan_title_ul").children('li').each(function(){
			width = eval(width + "+" + $(this).css("width").replaceAll("px",""));
		});
		var divWidth = $(".popup_body").width() - $(".but_back").width() * 2 - 3;
		if((left + width) > divWidth){
			left-= 300;
			if((left+width) < divWidth){
				left = divWidth -  width;
			}
			$("#kexuan_title_ul").animate({marginLeft:left+"px"},100);
		}
		
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
	var tabIdKey = $("#selectTabId").val();
	var tabNoKey = $("#selectTabNo").val();
	var left = $("#kexuan_title_ul").css("marginLeft");
	left = parseInt(left.replaceAll("px",""));
	var popTableType = $("#popTableType").val();
	var thisUrl = '${ctx}/index/pop/table/select?tabId='+tabIdKey+'&tabNo='+tabNoKey+'&left='+left+'&popTableType='+popTableType+'&page='+page+'&'+searchParams;
	refreshPopForm(thisUrl);
}

</script>
</head>

<body>
<input type="hidden" value="${left}"  id="left" name="left"/>
<input type="hidden" value="${popTableType}"  id="popTableType" name="popTableType"/>

<input type="hidden" value="${tabId}"  id="selectTabId" name="selectTabId"/>
<input type="hidden" value="${tabNo}"  id="selectTabNo" name="selectTabNo"/>
<input type="hidden" value="${tableAreaId}"  id="selectTableAreaId" name="selectTableAreaId"/>

<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3 class="kaitai-title">可选餐台</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kexuan_title_wrap">
				<div class="kexuan_title">
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
					 <a href="###" class="but_back" id="pop_dish_left"></a>
				  	<a href="###" class="but_go" id="pop_dish_right"></a>
				</div>
			</div>
			<input type="hidden" id="from" value="${from}" />
			<div class="kexuan">
				<c:choose>
					<c:when test="${from == 'yuding'}">
						<c:forEach items="${tables.content}" var="table" varStatus="status">
							<c:choose>
								<c:when test="${table.isHasTableOrder == '1' && table.isAtOrderWarnTime == '0'}">
									<div class="yuding_1"  id="${table.tabId}" tabNo="${table.tabNo}" tabName="${table.tabName}" waiterId="${table.waiter.empId}" waiterName="${table.waiter.name}" isHasTableOrder="${table.isHasTableOrder}" isAtOrderWarnTime="${table.isAtOrderWarnTime}" >
										<div class="zhuozi">
										<span><em class="p_em">
										<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
										<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
										</em></span>
										</div>
										<span class="time"><fmt:formatDate  value="${table.lastedTableOrderBillRelation.tableOrder.orderTime}" type="both" pattern="HH:mm" /></span>
										<span class="shuliang">${table.lastedTableOrderBillRelation.peopleNum}/${table.seat}</span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="kongxian"  id="${table.tabId}" tabNo="${table.tabNo}" tabName="${table.tabName}" waiterId="${table.waiter.empId}" waiterName="${table.waiter.name}" isHasTableOrder="${table.isHasTableOrder}" isAtOrderWarnTime="${table.isAtOrderWarnTime}" >
										<span class="zhuozi"><span><em class="p_em">${table.tabName}</em></span></span>
										<span class="time"></span>
										<span class="shuliang">0/${table.seat}</span>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise><!-- 开台操作选择餐台 后台将空闲状态的餐桌集合传到页面-->
						<c:forEach items="${tables.content}" var="table" varStatus="status">
						<c:choose>
							<c:when test="${table.isHasTableOrder == '1' && table.isAtOrderWarnTime == '0'}">
								<div class="yuding_1" id="${table.tabId}" tabNo="${table.tabNo}" tabName="${table.tabName}" waiterId="${table.waiter.empId}" waiterName="${table.waiter.name}" onclick="tableDivClick(event,this);" dinnerStatus="${table.dinnerStatus}" billStatus="${table.lastedTableOrderBillRelation.billStatus}" isHasTableOrder="${table.isHasTableOrder}" isAtOrderWarnTime="${table.isAtOrderWarnTime}" >
									<div class="zhuozi">
										<span>
											<em class="p_em">
												<c:if test="${fn:length(table.tabName) > 8}">${fn:substring(table.tabName,0,7)}..</c:if>
												<c:if test="${fn:length(table.tabName) <= 8}">${table.tabName}</c:if>
											</em>
										</span>
									</div>
									<span class="time"><fmt:formatDate  value="${table.lastedTableOrderBillRelation.tableOrder.orderTime}" type="both" pattern="HH:mm" /></span>
									<span class="shuliang">${table.lastedTableOrderBillRelation.peopleNum}/${table.seat}</span>
								</div>
							</c:when>
							<c:otherwise>
								<div class="kongxian"  id="${table.tabId}" tabNo="${table.tabNo}" tabName="${table.tabName}" waiterId="${table.waiter.empId}" waiterName="${table.waiter.name}" isHasTableOrder="${table.isHasTableOrder}" isAtOrderWarnTime="${table.isAtOrderWarnTime}" >
									<span class="zhuozi"><span><em class="p_em">${table.tabName}</em></span></span>
									<span class="time"></span>
									<span class="shuliang">0/${table.seat}</span>
								</div>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="kexuan_under_wrap">
				<div class="kexuan_under">
					<div class="kexuan_under_in">
						<tags:paginationpop page="${tables}" paginationSize="15"/>
						<a class="but-queding mr_7" style="cursor:pointer;" id="popSelect"></a>
						<a class="but-quxiao" style="cursor:pointer;" onclick="popBack(1);" id="popBack"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>