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
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>点餐</title>
<link href="${ctx}/static/css/waimai.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/cloud/cloudBillList.js"></script>

	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.logo,.mainNav,.body_first,.body_second_list1,h3,.but_chedan,.but_dayin,.but_fanjiezhang,#keywordsbtn,.but_beizhu,.b_cantai,.b_zhangdan,.b_huiyuan,.b_yuding,.shezhi,.logo_bottom');</script>
	<![endif]-->
</head>
<body>
<input type="hidden" value="${isBandCloud}"  id="isBandCloud" name="isBandCloud"/>
<input type="hidden" value="${isConnect}"  id="isConnect" name="isConnect"/>
<input type="hidden" id="cloudBillStatus" name="cloudBillStatus" value="0"/>

<c:if test="${isBandCloud == '1'}">
<c:if test="${isConnect == false}">
	<!--页面主体begin -->
	<div id="noConnectDiv" class="body" style="background:#eee;">
	   <div class="bangding_yzh">
         <div class="yzh_title" style="text-align:center;">网络故障！！</div>
         <div class="yzh_content">
           <div class="yzh_content_con" style="text-align:center;">
            请检查您的网络，保持网络畅通，否则无法使用xxx云服务！！
           </div>
         </div>
         <div class="yzh_button"><a href="javascript:refreshCloud();" class="yzh_button_shuaxin"></a></div>
       </div>
	</div>
	<!--页面主体end -->
</c:if>
<c:if test="${isConnect == true}">
<!--页面主体begin -->
	<div class="body">
		<div class="list_l" id="ajaxContent">
		</div>
		<div class="list_r">
			<div class="gongneng_area_wrap">
				<ul class="gongneng_area" style="height:200px;">
					<li class="mr_10">
						<a id="ddshenhe" class="ddshenhe_no" href="###" onclick="openReviewTakeout(this);"><span></span></a>
					</li>
					<li>
						<a id="but_shuaxin" class="shuaxin" href="${ctx}/cloud/list"></a>
					</li>
					<li class="mr_10">
						<a id="ddzhuizong" class="ddzhuizong_no" href="###" onclick="openBillTrack(this);"></a>
					</li>
				</ul>
			</div>
			<dl class="zhuangtai_area">
				<dt>状态过滤－待审核 </dt>
				<dd class="mr_10">
					<a id="allStatus" class="zt_quanbu" href="###" onclick="setCloudBillStatus('',this);"><span style="margin-top:22px;">全部</span></a>
				</dd>
				<dd>
					<a id="daishenheStatus" class="zt_daishenhe_on" href="###" onclick="setCloudBillStatus('0',this);"><span>待审核<br/><span id="waitReview">${noOperationCloudSize}</span单</span></a>
				</dd>
				<dd class="mr_10">
					<a id="tongguoStatus" class="zt_shenhetongguo" href="###" onclick="setCloudBillStatus('1',this);"><span style="margin-top:22px;">审核通过</span></a>
				</dd>
				<dd>
					<a id="weitongguoStatus" class="zt_shenheweitongguo" href="###" onclick="setCloudBillStatus('-1',this);"><span style="margin-top:22px;">审核未通过</span></a>
				</dd>
			</dl>
		</div>
		
	</div>
	<!--页面主体end -->
</c:if>
</c:if>

<c:if test="${isBandCloud == '0'}">
	<!--页面主体begin -->
	<div class="body" style="background:#eee;">
	   <div class="bangding_yzh">
         <div class="yzh_title">未绑定云账户，无法使用xxx云服务！！</div>
         <div class="yzh_content">
           <div class="yzh_content_titles">
                                  请打开登陆器页面，点击
             <span class="titles_span"><img src="${ctx}/static/images/takeout/yzh_title.png" width="223" height="43"></span>
             <span style="padding-left:235px;">,按提示进行云账号的绑定！</span>
           </div>
         </div>
         <div class="yzh_content">
           <div class="yzh_content_title">xxx云备份</div>
           <div class="yzh_content_con">
             除非发生核战争，否则您的数据将永不丢失，即使电脑坏了也可以迅速恢复数据，数据绝对安全保密。
           </div>
         </div>
         <div class="yzh_content">
           <div class="yzh_content_title">xxx云餐厅</div>
           <div class="yzh_content_con">
             为您的餐厅提供一套独立的在线订餐定位网站，顾客可通过互联网实现在线预订、外卖点餐、查看会员卡余额等操作；云订单数据直接与店内xxx餐饮软件互通，顾客网上下单后餐厅通过xxx餐饮系统马上可以处理，审核、下单、派送外卖等。
           </div>
         </div>
         <div class="yzh_content">
           <div class="yzh_content_title">xxx微信餐厅</div>
           <div class="yzh_content_con">
             使用微信餐厅可以增强餐厅与顾客的互动，推送餐厅信息，同时顾客可通过餐厅的微信账号来实现云餐厅的网上预订、外卖点餐等功能。
           </div>
         </div>
         <!-- <div class="yzh_button"><a href="#;" class="yzh_button_bd"></a></div> -->
       </div>
	</div>
	<!--页面主体end -->

</c:if>


	<!-- 权限加载 -->
<input type="hidden" id="permission_cloud_review" value="<shiro:hasPermission name="frontdesk_cloud_review:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_cloud_refresh" value="<shiro:hasPermission name="frontdesk_cloud_refresh:create">1</shiro:hasPermission>">
<input type="hidden" id="permission_cloud_zhuizong" value="<shiro:hasPermission name="frontdesk_cloud_zhuizong:create">1</shiro:hasPermission>">
	
	
</body>
</html>