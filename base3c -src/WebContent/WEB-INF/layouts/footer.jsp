<%@page import="com.ndlan.canyin.core.common.TrueFalseEnum"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java" import="com.ndlan.canyin.frontdesk.util.UserSettingCache" %>
<%@ page language="java" import="com.ndlan.canyin.frontdesk.service.security.ShiroDbRealm.ShiroUser" %>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<!--页面底部begin -->

	<%
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String userId = user.id;
		String loginName = user.loginName;
	%>
	
	<input type="hidden" id="currentUserName" name="currentUserName" value="<%=loginName%>"/>	
	
	<div class="footer_wrap"  id="foot">
		<div class="footer">
			<div class="footer_in">
				<!--  <div class="hujiao">
					<div id="messCont">
						<p class="message" id="newMsg" style="top:-57px">
						</p>
						<p class="message" id="currentMsg">
							欢迎使用<%if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){%>G2<%}%>点餐系统
						</p>
					</div>
					<a href="javascript:void(0)" id="msgHandle">
						<span class="cishu">0</span>
					</a>
				</div>-->
				
				
				<div class="yonghu"><a id="foot_dating" onclick="window.location='${ctx}/employe/shift'" href="###">当前用户<br/><%=UserSettingCache.getInstance().getUserCache(userId).getUserName()%></a></div>
				<ul class="bottom_nav">
				
					<%
										if(TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsShowModuleDesk())){
									%>
						<li><a class="b_cantai" id="foot_cantai" onclick="window.location='${ctx}/index'" href="###"></a></li>
					<%
						}
					%>
					<%
						if(TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsShowModuleFastfood())){
					%>
					<li><a class="b_kuaican"  id="foot_kuaican" onclick="window.location='${ctx}/fastfood/diancai'" href="###"></a></li>
					<%
						}
					%>
					<%
						if(TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsShowModuleWaimai())){
					%>
					<li style="position:relative;"><a class="b_waimai"  id="foot_waimai" onclick="window.location='${ctx}/takeout/list'" href="###"></a>
						<span id="noPayTakeoutSize" hidden="true" style="position:absolute;right:0px;top:0px;background:url(${ctx}/static/images/hujiao_bg.png) no-repeat -39px -4px;width:20px;height:20px; text-align:center;line-height:20px;color:#fff;">
							
						</span>
					</li>
					<%
						}
					%>
					<%
						if(TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsShowModuleBill())){
					%>
					<li><a class="b_zhangdan"  id="foot_zhangdan" onclick="window.location='${ctx}/bill/list'" href="###"></a></li>
					<%
						}
					%>
					
					<%
											if(TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsShowModuleMember())){
										%>
					<li><a class="b_huiyuan"  id="foot_huiyuan" onclick="window.location='${ctx}/member'" href="###"></a></li>
					<%
						}
					%>
					<%
						if(TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsShowModuleOrder())){
					%>
					<li><a class="b_yuding"  id="foot_yuding" onclick="window.location='${ctx}/order'" href="###"></a></li>
					<%
						}
					%>
					<%
						if(TrueFalseEnum.TRUE.getCode().equals(UserSettingCache.getInstance().getUserCache(userId).getIsShowModuleGuqing())){
					%>
					<li><a class="b_guqing" id="foot_guqing" onclick="window.location='${ctx}/estimate'" href="###"></a></li>
					<%
						}
					%>
					<li style="position:relative;">
						<a class="b_yundingdan" id="foot_yundingdan" onclick="window.location='${ctx}/cloud/list'"  href="###"></a>
						<span id="noOperationCloudSize" hidden="true" style="position:absolute;right:0px;top:0px;background:url(${ctx}/static/images/hujiao_bg.png) no-repeat -39px -4px;width:20px;height:20px; text-align:center;line-height:20px;color:#fff;">
						
						</span>
					</li>
					<li style="position:relative;" id="foot_xiaoxi" onclick="popForm('消息处理',window.ctx+'/self/message/pop/msgHandle','880','644');">
						<a href="javascript:void(0)" id="msgHandle" class="b_xiaoxizhongxin" href="###" ></a>
						<span id="noReadSelfMessagesSize" hidden="true" style="position:absolute;right:0px;top:0px;background:url(${ctx}/static/images/hujiao_bg.png) no-repeat -39px -4px;width:20px;height:20px; text-align:center;line-height:20px;color:#fff;">
						
						</span>
					</li>
				</ul>
				<div class="shezhi">
					<a onclick="window.location='${ctx}/system/setting'" id="foot_shezhi" class="but_shezhi" href="###"></a>
					<a onclick="window.location='${ctx}/logout'" class="btu_kaiguan" href="###"></a>
				</div>
				<p class="date_bottom" >
					<label id="currentDate"></label>
				</p>
				<p class="time_bottom">
					<label id="currentTime"></label>
				</p>
				<%
					if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion!=com.ndlan.canyin.core.common.CoreConstant.packageDiv_nologo){
				%>
				<div class="logo_bottom"></div>
				<%}%>
			</div>
		</div>
	</div>
	<!--页面底部end -->