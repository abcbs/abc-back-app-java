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
<title>${categoryName}&nbsp;销售明细</title>
<script type="text/javascript" src="${ctx}/static/js/employe/dishesSalesDetail.js"></script>
</head>
<body>
	<div class="small_popup_wrap">
		<div class="small_popup_header">
			<div class="small_popup_header_l"></div>
			<div class="small_popup_header_c">
				<h3>${categoryName}&nbsp;销售明细</h3>
			</div>
			<div class="small_popup_header_r"></div>
		</div>
		<div class="small_popup_body_auto">
			<div class="dd_zhuizong" style="overflow-y:auto;">
              <div class="xlmx_tck">
                <table cellpadding="0" cellspacing="0">
                  <tr class="tr">
                    <td width="150">菜肴名称</td>
                    <td width="150">销售数量</td>
                    <td width="150">原价合计</td>
                  </tr>
                  <c:forEach var="dishes" items="${dishesSalesDetailList}">
                  	  <tr>
	                    <td>${dishes.dishesName}</td>
	                    <td><fmt:formatNumber value="${dishes.unitNumSum}" pattern="#.##" type="number"/></td>
	                    <td><fmt:formatNumber value="${dishes.oriCostSum}" pattern="#.##" type="number"/></td>
	                  </tr>
                  </c:forEach>
                </table>
              </div>
            </div>
			<div class="line_d" style="margin-bottom:0px;"></div>
			<div class="but-area-s" style="bottom:30px; left:180px;">
				<a class="small-but-dayin mr_28" href="#" onclick="print('${categoryId}','${startDate}','${endDate}');"></a>
				<a class="small-but-guanbi" href="#" onclick="closebox();">关闭</a>
			</div>
		</div>	
	</div>
</body>
</html>