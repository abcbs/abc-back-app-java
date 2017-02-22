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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>交接班</title>
<link href="${ctx}/static/css/system.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/foot.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/system_p.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/toastr.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/CanYinHotKeys.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/employe/shift.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<form id="shiftForm" action="${ctx}/employe/ajax/shift/create" method="post">
		<!--页面主体begin -->
		<div class="body_system_jiaojieban">
		    <div class="jjb_one_jiaojieban">
			   <div class="title">交接班</div>
	           <div class="back"><a href="${ctx}/index" class="backs">返回</a></div>
			</div>
	        <div class="jiaojieban_content" style="height:723px;">
	          <div class="jiaojieban_title">
	            <ul class="jiaojieban_menu">
	              <li class="jiaojieban_hove"><a href="#;">交接班</a></li>
	              <li onclick="window.location='${ctx}/employe/costExpend'"><a href="${ctx}/employe/costExpend">成本支出</a></li>
	              <li onclick="window.location='${ctx}/employe/sales'"><a href="${ctx}/employe/sales">销量统计</a></li>
	            </ul>
	          </div>
	          <div class="jiaojieban_con">
	            <div class="jj_con_left">
	              <table cellpadding="0" cellspacing="0" width="210">
	                <p>本班收入</p>
	                <c:forEach items="${paymentTypeVOs}" var="payment">
						<c:if test="${payment.enableStatus eq 1}">
							<tr>
								<td class="td">${payment.paymentName} ：</td>
								<td>
									<c:choose>
										<c:when test="${payment.money ne '' && payment.money ne null}">
											<fmt:formatNumber value="${payment.money}" pattern="#.##" type="number"/>
										</c:when>
										<c:otherwise>0</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:if>
					</c:forEach>
	              </table>
	              <table cellpadding="0" cellspacing="0" width="210">
	                <p>本班会员收入</p>
	                <c:forEach items="${paymentTypeVOsForm}" var="payment">
						<c:if test="${payment.enableStatus eq 1}">
							<tr>
								<td class="td">${payment.paymentName} ：</td>
								<td>
									<c:choose>
										<c:when test="${payment.money ne '' && payment.money ne null}">
											<fmt:formatNumber value="${payment.money}" pattern="#.##" type="number"/>
										</c:when>
										<c:otherwise>0</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:if>
					</c:forEach>
	              </table>
	              <table cellpadding="0" cellspacing="0" width="210">
	                <p>预订收入</p>
	                <c:forEach items="${paymentTypeVOsOrder}" var="payment">
						<c:if test="${payment.enableStatus eq 1}">
							<tr>
								<td class="td">${payment.paymentName} ：</td>
								<td>
									<c:choose>
										<c:when test="${payment.money ne '' && payment.money ne null}">
											<fmt:formatNumber value="${payment.money}" pattern="#.##" type="number"/>
										</c:when>
										<c:otherwise>0</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:if>
					</c:forEach>
	              </table>
	            </div>
	            <div class="jj_con_right">
	               <div class="con_fr_con">
	                 <table cellpadding="0" cellspacing="0" width="510">
	                   <tr>
	                     <td class="td">营业员：</td>
	                     <td>${employeeName}</td>
	                   </tr>
	                   <tr>
	                     <td class="td">当前现金总额：</td>
	                     <input type="hidden" id="currentCash2" value="${currentCash}">
						 <input type="hidden" name="currentCash" id="currentCash" value="${currentCash}">
	                     <td id="currentCashLable"><fmt:formatNumber value="${currentCash}" pattern="#.##" type="number"/></td>
	                   </tr>
	                   <tr style="height:60px;">
	                     <td class="td">本班上交现金：</td>
	                     <td>
	                       <div style="position:relative;width:360px;">
	                           <input id="currentHandonCash" name="currentHandonCash" value="${currentCash}" class="input_250" type="text">
	                           <a id="currentHandonCashA" class="but-jianpan"></a>
	                           <div id="sudoku" class="jianpan_wrap" style="display: none;">
	                            <div class="jianpan">
	                                <a class="but_1"></a>
	                                <a class="but_2"></a>
	                                <a class="but_3"></a>
	                                <a class="but_4"></a>
	                                <a class="but_5"></a>
	                                <a class="but_6"></a>
	                                <a class="but_7"></a>
	                                <a class="but_8"></a>
	                                <a class="but_9"></a>
	                                <a class="but_close"></a>
	                                <a class="but_0"></a>
	                                <a class="but_del"></a>							
	                            </div>
	                         </div>
	                       </div>
	                    </td>
	                   </tr>
	                   <tr style="height:60px;">
	                     <td class="td">新增营业周转资金：</td>
	                     <td>
	                       <div style="position:relative;width:360px;">
	                           <input id="currentHandoffCash" name="currentHandoffCash" class="input_250" type="text">
						       <a id="currentHandoffCashA" class="but-jianpan" href="#"></a>
	                           <div id="sudoku" class="jianpan_wrap" style="display: none;">
	                            <div class="jianpan">
	                                <a class="but_1"></a>
	                                <a class="but_2"></a>
	                                <a class="but_3"></a>
	                                <a class="but_4"></a>
	                                <a class="but_5"></a>
	                                <a class="but_6"></a>
	                                <a class="but_7"></a>
	                                <a class="but_8"></a>
	                                <a class="but_9"></a>
	                                <a class="but_close"></a>
	                                <a class="but_0"></a>
	                                <a class="but_del"></a>							
	                            </div>
	                         </div>
	                       </div>
	                     </td>
	                   </tr>
	                   <tr>
	                     <td class="td">前班结余现金：</td>
	                     <input type="hidden" name="lastCurrentHandonCash" value="${lastCurrentHandonCash}">
	                     <td><fmt:formatNumber value="${lastCurrentHandonCash}" pattern="#.##" type="number"/></td>
	                   </tr>
	                   <tr>
	                     <td class="td">本班结余现金：</td>
	                     <input type="hidden" id="currentCashBalance" name="currentCashBalance" value="${currentCash}">
	                     <td id="currentCashBalanceLabel"><fmt:formatNumber value="${currentCash}" pattern="#.##" type="number"/></td>
	                   </tr>
	                   <tr>
	                     <td class="td">上次交班时间：</td>
	                     <td><fmt:formatDate value="${lastShiftTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                   </tr>
	                   <tr>
	                     <td class="td">是否打印：</td>
	                     <td>
	                     	<a id="dayinButton" class="but_a_on mr_28" href="#" onclick="setPrint('1');">是</a>
	                     	<a id="noDayinButton" class="but_a" href="#" onclick="setPrint('0');">否</a>
	                     </td>
	                     <input type="hidden" name="isPrint" id="isPrint" value="1"/>
	                   </tr>
	                 </table>
	               </div>
	               <div class="set_but"><a href="#;" id="shiftSubmit" class="sure">交班并登出</a></div>
	            </div>
	          </div>
	        </div>
		</div>
		<!--页面主体end -->
	</form>
</body>
</html>
