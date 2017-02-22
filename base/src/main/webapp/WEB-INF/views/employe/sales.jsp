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
<title>销量统计</title>
<link href="${ctx}/static/css/system.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/foot.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/system_p.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/toastr.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>

<link href="${ctx}/static/css/small_popup.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/jquery/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/popup/dialog.js" type="text/javascript"></script>
<script src="${ctx}/static/js/CanYinHotKeys.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx}/static/date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/employe/sales.js"></script>
	<!--[if IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,div,a,h3');</script>
	<![endif]-->
</head>
<body>
	<!--页面主体begin -->
	<div class="body_system_jiaojieban">
	    <div class="jjb_one_jiaojieban">
		   <div class="title">销量统计</div>
           <div class="back"><a href="${ctx}/index" class="backs">返回</a></div>
		</div>
        <div class="jiaojieban_content">
          <div class="jiaojieban_title">
            <ul class="jiaojieban_menu">
              <li onclick="window.location='${ctx}/employe/shift'"><a href="${ctx}/employe/shift">交接班</a></li>
              <li onclick="window.location='${ctx}/employe/costExpend'"><a href="${ctx}/employe/costExpend">成本支出</a></li>
              <li class="jiaojieban_hove"><a href="#;" >销量统计</a></li>
            </ul>
          </div>
          <div class="jiaojieban_con">
             <div class="zhichu_con">
               <div class="zhichu_lu">
                <fieldset>
				  <label>销售时间段：</label>
				  <input class="myinput" type="text" value="${startDate}" id="startDate" name="startDate" 
				  				style="width:160px;padding-left:8px;border:1px solid #deded3;height:28px;" 
	                  			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,isShowToday:false,readOnly:true});" class="Wdate" >
				  <input class="myinput" type="text" value="${endDate}" id="endDate" name="endDate" 
				  				style="width:160px;padding-left:8px;border:1px solid #deded3;height:28px;" 
	                  			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,isShowToday:false,readOnly:true});" class="Wdate" >
				  <a href="#" onclick="getSales();" class="cx_but">查询</a>
				 </fieldset>
               </div>
               <div style="height:35px;color:#009800;font-size:14px;">菜肴量统计</div>
               <table cellpadding="0" cellspacing="0" width="780">
                 <style type="text/css">
                 .zhichu_con table tr{height:45px;line-height:45px;}
				 .zhichu_con table a.bj_but{margin-right:0px;}
                 </style>
                 <tr class="tr">
                   <td width="194">菜肴分类</td>
                   <td width="194">销售数量</td>
                   <td width="194">原价合计</td>
                   <td width="194">操作</td>
                 </tr>
                 <c:forEach var="dishes" items="${disheSales}">
                 	<tr>
	                   <td>${dishes.categoryName}</td>
	                   <td><fmt:formatNumber value="${dishes.unitNumSum}" pattern="#.##" type="number"/></td>
	                   <td><fmt:formatNumber value="${dishes.unitPriceSum}" pattern="#.##" type="number"/></td>
	                   <td><a href="#" onclick="dishesSalesDetail('${dishes.categoryId}','${dishes.categoryName}','${startDate}','${endDate}');" class="bj_but">查看</a></td>
	                 </tr>
                 </c:forEach>
               </table>
               <div style="height:35px;color:#009800;font-size:14px;margin-top:30px;">套餐量统计</div>
               <table cellpadding="0" cellspacing="0" width="780">
                 <style type="text/css">
                 .zhichu_con table tr{height:45px;line-height:45px;}
				 .zhichu_con table a.bj_but{margin-right:0px;}
                 </style>
                 <tr class="tr">
                   <td width="194">套餐分类</td>
                   <td width="194">销售数量</td>
                   <td width="194">原价合计</td>
                   <td width="194">操作</td>
                 </tr>
                 <c:forEach var="disheSet" items="${disheSetSales}">
                 	<tr>
	                   <td>${disheSet.categoryName}</td>
	                   <td><fmt:formatNumber value="${disheSet.unitNumSum}" pattern="#.##" type="number"/></td>
	                   <td><fmt:formatNumber value="${disheSet.unitPriceSum}" pattern="#.##" type="number"/></td>
	                   <td><a href="#;" onclick="dishesSetSalesDetail('${disheSet.dsCategoryId}','${disheSet.categoryName}','${startDate}','${endDate}');" class="bj_but">查看</a></td>
	                 </tr>
                 </c:forEach>
               </table>
             </div>
          </div>
        </div>
	</div>
	<!--页面主体end -->
</body>
</html>
