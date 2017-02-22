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
<title>系统设置</title>
<link href="${ctx}/static/css/system.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/system_p.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/system/setting.js"></script>
<script type="text/javascript" src="${ctx}/static/js/employe/updatePsd.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if('${message}'!=''){
		toastr.success('${message}');
	}
});

</script>
</head>
<body>

	<!--页面主体begin -->
	<div class="body_system">
		<div class="title_system">
			<span></span>
			<h3 class="text_green">前台设置</h3>
		</div>
        <div class="system_content">
          <div class="system_title">
            <ul class="system_menu">
              <li class="system_hove" id="news1" onClick="setTab('news',1,6)">功能设置</li>
              <li id="news2" onClick="setTab('news',2,6)">操作设置</li>
              <li id="news3" onClick="setTab('news',3,6)">预订设置</li>
              <li id="news4" onClick="setTab('news',4,6)">其他设置</li>
              <li id="news5" onClick="setTab('news',5,6)">修改密码</li>
              <li id="news6" onClick="setTab('news',6,6)">联系我们</li>
            </ul>
            <div class="system_banben">软件版本：${sysVersion}</div>
          </div>
          <div class="system_box">
             <div id="system_news_1">
             	<form id="moduleForm"  action="${ctx}/system/setting/updateModule" method="post">
            			<input type="hidden" name="moduleId" id="moduleId" value="${cashierDeskSetting.ccdsId}"/>
            			<input type="hidden" name="isShowModuleDesk" id="isShowModuleDesk" value="${cashierDeskSetting.isShowModuleDesk}"/>
            			<input type="hidden" name="isShowModuleFastfood" id="isShowModuleFastfood" value="${cashierDeskSetting.isShowModuleFastfood}"/>
            			<input type="hidden" name="isShowModuleWaimai" id="isShowModuleWaimai" value="${cashierDeskSetting.isShowModuleWaimai}"/>
            			<input type="hidden" name="isShowModuleBill" id="isShowModuleBill" value="${cashierDeskSetting.isShowModuleBill}"/>
            			<input type="hidden" name="isShowModuleMember" id="isShowModuleMember" value="${cashierDeskSetting.isShowModuleMember}"/>
            			<input type="hidden" name="isShowModuleOrder" id="isShowModuleOrder" value="${cashierDeskSetting.isShowModuleOrder}"/>
            			<input type="hidden" name="isShowModuleGuqing" id="isShowModuleGuqing" value="${cashierDeskSetting.isShowModuleGuqing}"/>
		               <div class="set_gn">
		                  <p>请选择您需要使用的功能模块，未勾选的模块将不在前台收银中显示，此设置只针对当前用户！</p>
		                  <ul class="system_caozuo_p">
		                  
								<li class="mr_28"><a href="#" class="but_stystem_<c:if test="${cashierDeskSetting.isShowModuleDesk == '1'}">on_</c:if>p" id="isShowModuleDesk" onclick="changeModule(this);">餐台</a></li>
								<li class="mr_28"><a href="#" class="but_stystem_<c:if test="${cashierDeskSetting.isShowModuleFastfood == '1'}">on_</c:if>p" id="isShowModuleFastfood" onclick="changeModule(this);">快餐</a></li>
								<li><a href="#" class="but_stystem_<c:if test="${cashierDeskSetting.isShowModuleWaimai == '1'}">on_</c:if>p" id="isShowModuleWaimai" onclick="changeModule(this);">外卖</a></li>
								<li class="mr_28"><a href="#" class="but_stystem_<c:if test="${cashierDeskSetting.isShowModuleBill == '1'}">on_</c:if>p" id="isShowModuleBill" onclick="changeModule(this);">账单</a></li>
								<li class="mr_28"><a href="#" class="but_stystem_<c:if test="${cashierDeskSetting.isShowModuleMember == '1'}">on_</c:if>p" id="isShowModuleMember" onclick="changeModule(this);">会员</a></li>
								<li><a href="#" class="but_stystem_<c:if test="${cashierDeskSetting.isShowModuleOrder == '1'}">on_</c:if>p" id="isShowModuleOrder" onclick="changeModule(this);">预订</a></li>
								<li class="mr_28"><a href="#" class="but_stystem_<c:if test="${cashierDeskSetting.isShowModuleGuqing == '1'}">on_</c:if>p" id="isShowModuleGuqing" onclick="changeModule(this);">沽清</a></li>
								
					       </ul>
		               </div>
		               <div class="set_but"><a href="#;" class="sure" id="moduleSubmit">确定</a></div>
	               </form>
             </div>
             <div class="undis" id="system_news_2">
             <form id="opForm"  action="${ctx}/system/setting/update" method="post">
            			<input type="hidden" name="id" id="id" value="${cashierDeskSetting.ccdsId}"/>
             	
               <div class="set_cz">
                 <p>根据您的需要设置，此设置只针对当前用户！</p>
                 <table cellpadding="0" cellspacing="0">
                   <tr>
                     <td class="td">开台后：</td>
                     <td>
                         <div class="uboxstyle">
                           <select name="isStartEnterOrder" id="isStartEnterOrder">
                             <option value="1" <c:if test="${cashierDeskSetting.isStartEnterOrder == '1'}">selected="selected"</c:if>>进入点餐页面</option>
                             <option value="0" <c:if test="${cashierDeskSetting.isStartEnterOrder == '0'}">selected="selected"</c:if>>哪也不去</option>
                           </select>
                         </div>
                     </td>
                     <td class="td">预订成功后：</td>
                     <td>
                         <div class="uboxstyle">
                           <select name="isOrderEnterDesk" id="isOrderEnterDesk">
                             <option value="1" <c:if test="${cashierDeskSetting.isOrderEnterDesk == '1'}">selected="selected"</c:if>>进入点餐页面</option>
                             <option value="0" <c:if test="${cashierDeskSetting.isOrderEnterDesk == '0'}">selected="selected"</c:if>>哪也不去</option>
                           </select>
                         </div>
                     </td>
                   </tr>
                    <tr>
                     <td class="td">显示下单确认窗口：</td>
                     <td>
                         <div class="uboxstyle">
                           <select name="isShowPlaceBillConfirmWindow" id="isShowPlaceBillConfirmWindow">
                             <option value="1" <c:if test="${cashierDeskSetting.isShowPlaceBillConfirmWindow == '1'}">selected="selected"</c:if>>开启</option>
                             <option value="0" <c:if test="${cashierDeskSetting.isShowPlaceBillConfirmWindow == '0'}">selected="selected"</c:if>>关闭</option>
                           </select>
                         </div>
                     </td>
                     <td class="td">下单后：</td>
                     <td>
                         <div class="uboxstyle">
                           <select name="billPlaceEnterDeskOrPay" id="billPlaceEnterDeskOrPay">
                             <option value="0" <c:if test="${cashierDeskSetting.billPlaceEnterDeskOrPay == '0'}">selected="selected"</c:if>>哪也不去</option>
                             <option value="1" <c:if test="${cashierDeskSetting.billPlaceEnterDeskOrPay == '1'}">selected="selected"</c:if>>进入餐台页面</option>
                             <option value="2" <c:if test="${cashierDeskSetting.billPlaceEnterDeskOrPay == '2'}">selected="selected"</c:if>>进入结账页面 </option>
                           </select>
                         </div>
                     </td>
                   </tr>
                   <tr>
                     
                     <td class="td">结账后自动清台：</td>
                     <td>
                         <div class="uboxstyle">
                           <select name="isAutoQingtai" id="isAutoQingtai">
                             <option value="1" <c:if test="${cashierDeskSetting.isAutoQingtai == '1'}">selected="selected"</c:if>>开启</option>
                             <option value="0" <c:if test="${cashierDeskSetting.isAutoQingtai == '0'}">selected="selected"</c:if>>关闭</option>
                           </select>
                         </div>
                     </td>
                      <td class="td">结账后：</td>
                     <td>
                         <div class="uboxstyle">
                           <select name="isPayEnterDesk" id="isPayEnterDesk">
                             <option value="0" <c:if test="${cashierDeskSetting.isPayEnterDesk == '0'}">selected="selected"</c:if>>哪也不去</option>
                             <option value="1" <c:if test="${cashierDeskSetting.isPayEnterDesk == '1'}">selected="selected"</c:if>>进入餐台页面</option>
                             <option value="2" <c:if test="${cashierDeskSetting.isPayEnterDesk == '2'}">selected="selected"</c:if>>进入快餐页面 </option>
                           </select>
                         </div>
                     </td>
                   </tr>
                   <tr>
                    
                     <td class="td">快餐下单打印：</td>
                     <td>
                         <div class="uboxstyle">
                           <select name="isFastfoodBillPrint" id="isFastfoodBillPrint">
                             <option value="1" <c:if test="${cashierDeskSetting.isFastfoodBillPrint == '1'}">selected="selected"</c:if>>开启</option>
                             <option value="0" <c:if test="${cashierDeskSetting.isFastfoodBillPrint == '0'}">selected="selected"</c:if>>关闭</option>
                           </select>
                         </div>
                     </td>
                     
                   </tr>
                 </table>
               </div>
               <div class="set_but"><a href="#;" class="sure" id="opSubmit">确定</a></div>
              </form> 
               
             </div>
             <div class="undis" id="system_news_3">
             	 <form id="orderForm"  action="${ctx}/system/setting/updateOrder" method="post">
            			<input type="hidden" name="id" id="id" value="${commonSettingCashierDeskSetting.ccdsId}"/>
	               <div class="set_yd">
	                 <p>修改此设置将影响所有收银台的预订设置！</p>
	                 <table cellpadding="0" cellspacing="0">
	                   <tr>
	                     <td class="td">网络预订通知号码：</td>
	                     <td width="260"><input name="messageTel" id="messageTel" value="${commonSettingCashierDeskSetting.messageTel}" type="text" class="input" maxlength="11"></td>
	                     <td width="50"></td>
	                   </tr>
	                   <tr>
	                     <td class="td">预警时间：</td>
	                     <td><input name="orderWarnTime" id="orderWarnTime" value="${commonSettingCashierDeskSetting.orderWarnTime}" type="text" class="input"></td>
	                     <td width="50">分钟</td>
	                   </tr>
	                   <tr>
	                     <td class="td">锁定时间：</td>
	                     <td><input name="orderLockTime" id="orderLockTime" value="${commonSettingCashierDeskSetting.orderLockTime}" type="text" class="input"></td>
	                     <td width="50">分钟</td>
	                   </tr>
	                   <tr>
	                     <td class="td">到期保留时间：</td>
	                     <td><input name="orderExpireTime" id="orderExpireTime" value="${commonSettingCashierDeskSetting.orderExpireTime}" type="text" class="input"></td>
	                     <td width="50">分钟</td>
	                   </tr>
	                 </table>
	               </div>
	               <div class="set_but"><a href="#;" class="sure" id="orderSubmit">确定</a></div>
               </form>
             </div>
             <div class="undis" id="system_news_4">
              <form id="otherForm"  action="${ctx}/system/setting/updateOther" method="post">
            			<input type="hidden" name="otherId" id="otherId" value="${cashierDeskSetting.ccdsId}"/>
	               <div class="set_else">
	                 <p>此设置只针对当前用户！</p>
	                 <table cellpadding="0" cellspacing="0">
	                   <tr>
	                     <td class="td">收银打印机选择：</td>
	                     <td width="400">
	                         <div class="uboxstyle">
	                           <select name="printerId" id="printerId">
	                            <option value="">全部收银打印机</option>
	                          	 <c:forEach items="${printerlist}" var="print" varStatus="status">
	                          	 	
	                            	<option value="${print.printerId }" <c:if test="${print.printerId == cashierDeskSetting.printerId }">selected="selected"</c:if>>${print.name}</option>
	                             </c:forEach>
	                           </select>
	                         </div>
	                     </td>
	                   </tr>
	                   <tr class="tr">
	                     <td class="td"></td>
	                     <td>请到后台管理-餐厅综合-打印机设置 模块中添加和修改打印机！</td>
	                   </tr>
	                   <tr>
	                     <td class="td">离开锁屏设置：</td>
	                     <td>
	                         <div class="uboxstyle">
	                           <select name="leaveTime" id="leaveTime">
									<option value="0" <c:if test="${cashierDeskSetting.leaveTime == 0}">selected="selected"</c:if>>不锁屏</option>
									<option value="5" <c:if test="${cashierDeskSetting.leaveTime == 5}">selected="selected"</c:if>>5分钟</option>
									<option value="10" <c:if test="${cashierDeskSetting.leaveTime == 10}">selected="selected"</c:if>>10分钟</option>
									<option value="20" <c:if test="${cashierDeskSetting.leaveTime == 20}">selected="selected"</c:if>>20分钟</option>
									<option value="30" <c:if test="${cashierDeskSetting.leaveTime == 30}">selected="selected"</c:if>>30分钟</option>
									<option value="60" <c:if test="${cashierDeskSetting.leaveTime == 60}">selected="selected"</c:if>>60分钟</option>
								</select>
	                         </div>
	                     </td>
	                   </tr>
	                 </table>
	               </div>
	               <div class="set_but"><a href="#;" class="sure" id="otherSubmit">确定</a></div>
               </form>
             </div>
             <div class="undis" id="system_news_5">
             	<form id="psdForm" action="${ctx}/employe/ajax/psdUpdate" method="post">
					<input type="hidden" name="id" value="${empId}">
	               <div class="set_passwd">
	                 <table cellpadding="0" cellspacing="0">
	                   <tr>
	                     <td class="td">原始密码：</td>
	                     <td width="250"><input type="password" id="password" name="oldLoginPassword" class="input" rangelength="3,6"></td>
	                   </tr>
	                   <tr>
	                     <td class="td">新密码：</td>
	                     <td><input type="password" id="loginPassword" name="loginPassword" class="input" rangelength="3,6"></td>
	                   </tr>
	                   <tr>
	                     <td class="td">确认新密码：</td>
	                     <td><input type="password" id="confirmPassword" name="confirmPassword" class="input" rangelength="3,6" equalTo="#password"></td>
	                   </tr>
	                 </table>
	               </div>
	               <div class="set_but"><a style="cursor:pointer;" class="sure" id="psdSubmit" >确定</a></div>
               </form>
             </div>
             <div class="undis" id="system_news_6">
               <div class="set_us">
                  <table cellpadding="0" cellspacing="0">
                    <tr>
                      <td class="td" valign="top">在线客服：</td>
                      <td>
                        <span class="kf_one">2758393942</span>
                        <span class="kf_one">2922606505</span>
                        <span class="kf_one">2914644026</span>
                        <span class="kf_one">2193585901</span>
                        <span class="kf_one">2752469772</span>
                      </td>
                    </tr>
                    <tr>
                      <td class="td" valign="top">客服QQ群：</td>
                      <td>
                        <span class="kf_two">294279815</span>
                        <span class="kf_two">310174475</span>
                      </td>
                    </tr>
                  </table>
               </div>
               <div class="set_but">
               <%
               	if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){
               %>
				<a class="yijian" style="cursor:pointer;" onclick="window.location='http://www.xxx.cn'" target="_blank">意见反馈</a>
				<a class="yijian" style="cursor:pointer;" onclick="window.location='http://www.xxx.cn/forum.php'" target="_blank">论坛交流</a>
				<a class="yijian" style="cursor:pointer;" onclick="window.location='http://www.xxx.cn/misc.php?mod=faq&action=faq&id=1&messageid=3'" target="_blank">关于我们</a>
				<%}%>
               	
               </div>
             </div>
          </div>
        </div>
	</div>
	<!--页面主体end -->
</body>
</html>
