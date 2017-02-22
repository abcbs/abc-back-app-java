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
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/member/jiankaForm.js"></script>
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript">
</script>
</head>

<body>

<form id="popSaveForm" action="${ctx}/member/jianka/${action}" method="post" >
		<input type="hidden" value="${restMemberInfo.mbId}"  id="id" name="id"/>
		<input type="hidden" name="popMcId" id="mcId" value=""/>
		
		<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>快速发卡</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="line_x"></div>
			<div class="ksfk_wrap">
				<div class="ksfk_left">
					<div class="ksfk">
						<div class="ksfk_row_1">
							<label><i class="red">*</i>姓名</label><input  value="${restMemberInfo.name}" id="name" name="name" class="input_long" type="text">
						</div>
						<div class="ksfk_row_1">
							<label><i class="red">*</i>性别</label>
							<div class="left w_200">
								<form:radiobutton path="restMemberInfo.gender" value="1"/>男  
								<form:radiobutton path="restMemberInfo.gender" value="0"/>女
							</div>
						</div>
						<div class="ksfk_row_1">
							<label>生日</label><input  value="<fmt:formatDate  value="${restMemberInfo.birthday}" type="both" pattern="yyyy-MM-dd"/>"  id="birthday" name="birthdayStr" class="input_long" type="text">
						</div>
						<div class="ksfk_row_1">
							<label><i class="red">*</i>手机</label><input value="${restMemberInfo.mobile}"  id="mobile" name="mobile" class="input_long" type="text"  maxlength="11">
						</div>
						<div class="ksfk_row_1">
							<label>职业</label>
							<div class="uboxstyle">
								<select name="work" id="work">
									<option value="">请选择</option>
									<c:forEach items="${works}" var="work" varStatus="status">
										<option value="${work.bciCode}">${work.bciName}</option>						
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="ksfk_row_1">
							<label>学历</label>
							<div class="uboxstyle">
								<select name="edu" id="edu">					
									<option value="">请选择</option>
									<c:forEach items="${edus}" var="edu" varStatus="status">				
										<option value="${edu.bciCode}">${edu.bciName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="ksfk_row_1">
							<label>邮箱</label><input id="email" name="email" maxlength="256" value="${restMemberInfo.email}" class="input_long" type="text">
						</div>
						<div class="ksfk_row_1">
							<label>备注</label><input id="notes" name="notes" maxlength="256" value="${restMemberInfo.notes}" class="input_long" type="text">
						</div>
						<div class="ksfk_row_1">
							<label>营销员</label>
							
							<input class="input" type="hidden" name="salesmanId" id="salesmanId" value="${restMemberInfo.salesman.empId}"/>
							<input class="input" type="text" name="salesmanName" id="salesmanName" value="${restMemberInfo.salesman.name}" />
							<a class="but-xuanze" style="cursor:pointer;" onclick="javascript:popForm('营销员','${ctx}/index/pop/employee/select?type=5','880','644');"></a>
						</div>
						<div class="ksfk_row_1">
							<label>会员卡</label>
							<div class="left">
								<input name="jiankaType" type="radio" value="0" onclick="popShow('popNewMemberCard','popMemberCardList',this);" checked="checked" > 快速创建会员卡
								<input name="jiankaType" type="radio" value="1" onclick="popShow('popMemberCardList','popNewMemberCard',this);"> 使用已有会员卡
							</div>
						</div>
					</div>
				</div>
				
				
				<!-- 会员卡列表 -->
				<div class="ksfk_right" id="popMemberCardList" style="display: none;">
					
				</div>
				
				<!-- 新建会员卡 -->
				<div class="ksfk_right_2" id="popNewMemberCard">
					<div class="ksfk">
						<div class="ksfk_row_1" id="new_cardNo_area">
							<label><i class="red">*</i>卡号</label>
							<input name="new_cardNo" id="new_cardNo" class="input_long" type="text">
							<a id="new_cardNo_a" class="but-xuanze" style="cursor:pointer; display: none;" onclick="javascript:backToCardList();"></a>
						</div>
						<div class="ksfk_row_2" id="new_membershipCardClasseType_area" style="min-width: 350px;">
							<label><i class="red">*</i>卡类型</label>
							<label id="new_cardTypeDesc"></label>
							<div class="uboxstyle">
								<select name="new_membershipCardClasseType" id="new_membershipCardClasseType" onchange="changeMemberCardType()">					
									<option value="">请选择</option>
									<c:forEach items="${membershipCardClasses}" var="membershipCardClasse" varStatus="status">				
										<option value="${membershipCardClasse.mcclassId}">${membershipCardClasse.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="ksfk_row_1">
							<label>押金</label><input name="new_cashPledge" id="new_cashPledge" value="0" class="input_long" type="text">
						</div>
						<div class="ksfk_row_1">
							<label>密码</label><input name="new_cardPassword" id="new_cardPassword" value="" class="input_long" type="password">
						</div>
						<div class="ksfk_row_1">
							<label><i class="red">*</i>充值金额</label><input name="new_rechargeCash" id="new_rechargeCash" value="" class="input_long" type="text" onchange="changePaidinCash(this);">
						</div>
						<div class="ksfk_row_2" style="min-width: 350px;">
							<label><i class="red">*</i>付款方式</label>
							<div class="uboxstyle">
								<select name="new_paymentType" id="new_paymentType">					
									<option value="">请选择</option>
									<c:forEach items="${paymentTypes}" var="paymentType" varStatus="status">				
										<option value="${paymentType.cptId}" <c:if test="${paymentType.paymentType eq 1}">selected="selected"</c:if>>${paymentType.paymentName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="ksfk_row_1">
							<label><i class="red">*</i>实收金额</label><input name="new_paidinCash"  id="new_paidinCash" value="" class="input_long" type="text" onchange="changePaidinCash(this);">
						</div>
						<div class="ksfk_row_1" id="new_giveCash" >
							<label>赠送金额</label>
							<label id="new_giveCash_2">0</label>
						</div>
						<div class="ksfk_row_2">
							<label>积分</label>
							<input name="new_memberIntegral" id="new_memberIntegral" value="0" class="input_long" type="text">
						</div>
						<div class="ksfk_row_2" id="new_cardOpType_div">
							<a class="but_piao mr_7" style="cursor:pointer;" onclick="newDrawBill(this);">开发票</a>
							<a class="but_piao" style="cursor:pointer;" onclick="newPrint(this);">打印小票</a>
							
							<input type="hidden" name="new_drawBill" id="new_drawBill" value="0"/>
							<input type="hidden" name="new_print" id="new_print" value="0"/>
							
						</div>
					</div>
				</div>
				
				
				
				<div class="line_w"></div>
				<div class="but-area-e">
					<a class="but-queding mr_28" style="cursor:pointer;" id="popSave"></a>
					<a class="but-quxiao" style="cursor:pointer;" onclick="closebox();"></a>
				</div>
			</div>
		</div>
	</div>
	
	
</form>
</body>
</html>