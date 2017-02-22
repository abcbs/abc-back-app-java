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
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/member/memberForm.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/member/memberdetail/create" method="post" >
	<input type="hidden" value="${restMemberInfo.mbId}"  id="id" name="id"/>
	
	<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>持卡人：${restMemberInfo.name}</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="line_x"></div>
			<div class="kamingxi_wrap">
				<div class="w_330 left ml_100 mt_20">
					<div class="ksfk_row_1">
						<label><i class="red">*</i>姓名</label><input value="${restMemberInfo.name}" id="name" name="name" class="input_long" type="text">
					</div>
					<div class="ksfk_row_1">
						<label><i class="red">*</i>性别</label>
						<div class="left w_200">
							<form:radiobutton path="restMemberInfo.gender" value="1"/>男  
							<form:radiobutton path="restMemberInfo.gender" value="0"/>女
						</div>
					</div>
					<div class="ksfk_row_1">
						<label>生日</label><input value="<fmt:formatDate  value="${restMemberInfo.birthday}" type="both" pattern="yyyy-MM-dd"/>"  id="birthday" name="birthdayStr" class="input_long" type="text">
					</div>
					<div class="ksfk_row_1">
						<label><i class="red">*</i>手机</label><input value="${restMemberInfo.mobile}"  id="mobile" name="mobile" class="input_long" type="text" maxlength="11">
					</div>
					<div class="ksfk_row_2">
						<label>职业</label>
						<div class="uboxstyle">
							<select name="work">
								<option value="">请选择</option>
								<c:forEach items="${works}" var="work" varStatus="status">
									<option value="${work.bciCode}" <c:if test="${restMemberInfo.work == work.bciCode}">selected="selected"</c:if>>${work.bciName}</option>						
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="w_330 left mt_13">
					<div class="ksfk_row_2">
						<label>学历</label>
						<div class="uboxstyle">
							<select name="edu">					
								<option value="">请选择</option>
								<c:forEach items="${edus}" var="edu" varStatus="status">				
									<option value="${edu.bciCode}" <c:if test="${restMemberInfo.edu == edu.bciCode}">selected="selected"</c:if>>${edu.bciName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="ksfk_row_1">
						<label>邮箱</label><input id="email" name="email" maxlength="256" value="${restMemberInfo.email}" class="input_long" type="text">
					</div>
					
					<div class="ksfk_row_1">
						<label>营销员</label>
						<input class="input" type="hidden" name="salesmanId" id="salesmanId" value="${restMemberInfo.salesman.empId}"/>
						<input class="input" type="text" name="salesmanName" id="salesmanName" value="${restMemberInfo.salesman.name}" />
						<a class="but-xuanze" style="cursor:pointer;" onclick="popForm('营销员','${ctx}/index/pop/employee/select?type=5','880','644');"></a>
					</div>
					
					<div class="ksfk_row_1" style="margin-top:10px;">
						<label>备注</label><input id="notes" name="notes" maxlength="256" value="${restMemberInfo.notes}" class="input_long" type="text">
					</div>
					
				</div>
				<div class="table-area-e">
					<table  class="renmingxi_table">
						<thead>
							<tr>
								<th width="240" >会员卡</th>
								<th width="160">卡类型</th>
								<th width="140">办卡日期</th>
								<th width="60">余额</th>
								<th width="70">积分</th>
								<th width="100">状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${restMemberInfo.membershipCards}" var="membershipCard" varStatus="mstatus">
								<tr>
									<td><a style="cursor:pointer;" class="table_but_4" onclick="popForm('会员卡明细','${ctx}/member/pop/opDetail/createForm/${membershipCard.mcId}?showType=1','880','644');">${membershipCard.cardNo}</a></td>
									<td>${membershipCard.membershipCardClass.name}</td>
									<td><fmt:formatDate value="${membershipCard.createTime}" pattern="yyyy-MM-dd"/></td>
									<td>${membershipCard.balance}</td>
									<td>${membershipCard.memberIntegral}</td>
									<td>${membershipCard.cardStatusName}</td>
								</tr>	
							</c:forEach>
							
						</tbody>	
					</table>
				</div>
				<div class="but-area-e">
					<a class="but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
					<a class="but-quxiao" style="cursor:pointer;" onclick="javascript:closebox();"></a>
				</div>
			</div>
		</div>
	</div>
	
	
	
</form>
</body>
</html>