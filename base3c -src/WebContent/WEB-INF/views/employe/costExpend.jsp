<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>交接班</title>
<link href="${ctx}/static/css/system.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/foot.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/system_p.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/toastr.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>

<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css"/>

<script src="${ctx}/static/jquery/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/popup/dialog.js" type="text/javascript"></script>
<script src="${ctx}/static/js/CanYinHotKeys.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if('${message}'!=''){
			toastr.success('${message}');
		}
		
		initFrameSize();
		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		$("#costExpendForm_sb").click( function () { 
			$('#costExpendForm').submit();
		});
		$(".sc_but").click( function () { 
			var the=$(this);
			dialogBoxConfirm("删除确认框","确认删除该条录入吗 ？",function(){
				window.location=the.attr('lang');
			});
			return;
		});
		$(".bj_but").click( function () { 
			var the=$(this);
			popForm('成本录入',the.attr('lang'),'880','644');
			return;
		});
		
		
	});
	function parseStringToDate(dateStr,pattern){
		if(pattern=='yyyy-MM-dd hh:mm:ss'){
			
		}	
		if(pattern=='yyyy-MM-dd'){
			var arr = new Array();
			arr = dateStr.split("-");
			var date = new Date(arr[0],arr[1],arr[2]);
			return date;
		}	
	}
	function validate(updateTime_ge,updateTime_le){
		var date1 = parseStringToDate(updateTime_ge,'yyyy-MM-dd');
		var date2 = parseStringToDate(updateTime_le,'yyyy-MM-dd');
		if(date1>date2){
			toastr.info("开始时间需小于结束时间");
		}else{
			$("#costExpendForm").submit();
		}
		
	};
	/**
	 * 自适应浏览器宽度
	 */
	function initFrameSize()
	{
		var subWidth = windowAreaParams.screenWidth - 1000 - 25;

		if(subWidth > 0)
		{
			$(".body_system_jiaojieban").css("width",998+subWidth+"px");
			$(".jjb_one_jiaojieban").css("width",994+subWidth+"px");
			$(".jiaojieban_content").css("width",994+subWidth+"px");
			$(".jiaojieban_title").css("width",994+subWidth+"px");
		}
		var subHeight = windowAreaParams.screenHeight - 768;
		if(subHeight > 0)
		{
			$(".body_system_jiaojieban").css("height",712+subHeight+"px");
			$(".jiaojieban_content").css("height",705+subHeight+"px");
			$(".jj_con_left").css("height",650+subHeight+"px");
		}
	}
</script>
<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<form id="shiftForm" action="${ctx}/employe/ajax/shift/create"
		method="post"></form>
		<!--页面主体begin -->
		<div class="body_system_jiaojieban">
			<div class="jjb_one_jiaojieban">
				<div class="title">支出管理</div>
				<div class="back">
					<a
						href="${ctx}/index"
						class="backs">返回</a>
				</div>
			</div>
			<div class="jiaojieban_content">
				<div class="jiaojieban_title">
					<ul class="jiaojieban_menu">
						<li onclick="window.location='${ctx}/employe/shift'"><a href="${ctx}/employe/shift">交接班</a></li>
						<li class="jiaojieban_hove"><a href="${ctx}/employe/costExpend">成本支出</a></li>
						<li onclick="window.location='${ctx}/employe/sales'"><a href="#;">销量统计</a></li>
					</ul>
				</div>

				<div class="jiaojieban_con">
					<div class="zhichu_con">
						<div class="zhichu_lu">
							
							<fieldset>
								<form action="#" id="costExpendForm" name="costExpendForm">
									<label>录入日期：</label> 
									<input class="myinput" type="text" value="${startDate}" id="startDate" name="startDate"> 
									<input class="myinput" type="text"  value="${endDate}" id="endDate" name="endDate"> 
									<a id="costExpendForm_sb" href="#" class="cx_but" onclick='validate($("#startDate").val(),$("#endDate").val());'>查询</a>
									 <a href="#" onclick="popForm('成本录入','${ctx}/employe/costExpendEntering','880','644');" class="lr_but">继续录入</a>
								</form>
							</fieldset>
							
						</div>
						<table cellpadding="0" cellspacing="0" width="780">
							<tr class="tr">
								<td width="120">支出类别</td>
								<td width="120">费用名称</td>
								<td width="120">支出金额</td>
								<td width="120">录入员工</td>
								<td width="120">录入时间</td>
								<td width="180">操作</td>
							</tr>
							<c:forEach items="${costExpends.content}" var="costExpend" varStatus="status">
								<c:if test="${costExpend.materialConsumption ne null}">
										<tr>
											<td class="left-td text_red">原料成本支出</td>
											<td>原料消耗</td>
											<td class="left-td">${costExpend.materialConsumption}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/> </td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=materialConsumption" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=MaterialConsumption"  class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.transportCost ne null}">
										<tr>
											<td class="left-td text_red">原料成本支出</td>
											<td>运输费</td>
											<td class="left-td">${costExpend.transportCost}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=transportCost" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=TransportCost" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.materialOther ne null}">
										<tr>
											<td class="left-td text_red">原料成本支出</td>
											<td>原料成本支出-其他</td>
											<td class="left-td">${costExpend.materialOther}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=materialOther" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=MaterialOther" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								
								<c:if test="${costExpend.salary ne null}">
										<tr>
											<td class="left-td text_red">人工成本支出</td>
											<td>工资</td>
											<td class="left-td">${costExpend.salary}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=salary" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=Salary" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.foodCost ne null}">
										<tr>
											<td class="left-td text_red">人工成本支出</td>
											<td>伙食费</td>
											<td class="left-td">${costExpend.foodCost}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=foodCost" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=FoodCost" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.employeeBenefit ne null}">
										<tr>
											<td class="left-td text_red">人工成本支出</td>
											<td>员工福利</td>
											<td class="left-td">${costExpend.employeeBenefit}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=employeeBenefit" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=EmployeeBenefit" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.laborOther ne null}">
										<tr>
											<td class="left-td text_red">人工成本支出</td>
											<td>人工成本支出-其他</td>
											<td class="left-td">${costExpend.laborOther}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=laborOther" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=LaborOther" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.chummage ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>房租</td>
											<td class="left-td">${costExpend.chummage}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=chummage" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=Chummage" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.taxesCost ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>税费</td>
											<td class="left-td">${costExpend.taxesCost}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=taxesCost" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=TaxesCost" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.maintain ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>维修</td>
											<td class="left-td">${costExpend.maintain}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=maintain" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=Maintain" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.consumables ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>消耗品</td>
											<td class="left-td">${costExpend.consumables}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=consumables" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=Consumables" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.waterCost ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>水费</td>
											<td class="left-td">${costExpend.waterCost}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=waterCost" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=WaterCost" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.electricCost ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>电费</td>
											<td class="left-td">${costExpend.electricCost}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=electricCost" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=ElectricCost" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.natgas ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>天然气</td>
											<td class="left-td">${costExpend.natgas}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=natgas" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=Natgas" class="sc_but">删除</a></td>
										</tr>
								</c:if>
								<c:if test="${costExpend.costOther ne null}">
										<tr>
											<td class="left-td text_red">其他成本支出</td>
											<td>人工成本支出-其他</td>
											<td class="left-td">${costExpend.costOther}</td>
											<td  class="right-td">${costExpend.createEmployee.name}</td>
											<td><fmt:formatDate value="${costExpend.createTime}" pattern="yyyy-MM-dd"/></td>
											<td><a href="#" lang="${ctx}/employe/costExpendEntering?id=${costExpend.id}&toEdit=costOther" class="bj_but">编辑</a>
											<a href="#" lang="${ctx}/employe/costExpendDelete/${costExpend.id}?pn=CostOther" class="sc_but">删除</a></td>
										</tr>
								</c:if>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--页面主体end -->
	
</body>
</html>
