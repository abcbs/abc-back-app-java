<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<ul class="xiaoxi_menu">

						<li onclick="MsgChange('all','0',1);" <c:if test="${type eq 'all'}">class="xx_on"</c:if>>
							<div>
								<p>
									<span class="span"><img src="${ctx}/static/images/popup/xiaoxi_cl_pxd1.png" /></span>
									<span>全部</span>
									<span class="red">
										<c:if test="${allCount >= 10}">(10+)</c:if>
										<c:if test="${allCount != null && allCount > 0 && allCount < 10}">(${allCount})</c:if>
									</span>
								</p>
							</div>
						</li>
						<li onclick="MsgChange('orderCall','0',1);" <c:if test="${type eq 'orderCall' && status == 0}">class="xx_on"</c:if>>
							<div>
								<p>
								<span class="span"><img src="${ctx}/static/images/popup/xiaoxi_cl_pxd2.png" /></span>
									<span>下单呼叫</span>
									<span class="red">
										<c:if test="${xiadanCount >= 10}">(10+)</c:if>
										<c:if test="${xiadanCount != null && xiadanCount > 0 && xiadanCount < 10}">(${xiadanCount})</c:if>
									</span>
								</p>
							</div>
						</li>
						<li onclick="MsgChange('serviceCall','0',1);" <c:if test="${type eq 'serviceCall' && status == '0'}">class="xx_on"</c:if>>
							<div>
								<p>
								<span class="span"><img src="${ctx}/static/images/popup/xiaoxi_cl_pxd3.png" /></span>
									<span>服务呼叫</span>
									<span class="red">
										<c:if test="${fuwuCount >= 10}">(10+)</c:if>
										<c:if test="${fuwuCount != null && fuwuCount > 0 && fuwuCount < 10}">(${fuwuCount})</c:if>
									</span>
								</p>
							</div>
						</li>
						<li onclick="MsgChange('orderCall','1',1);" <c:if test="${type eq 'orderCall' && status == '1'}">class="xx_on"</c:if>>
							<div>
								<p>
								<span class="span"><img src="${ctx}/static/images/popup/xiaoxi_cl_pxd4.png" /></span>
									<span>下单记录</span>
								</p>
							</div>
						</li>
						<li onclick="MsgChange('serviceCall','1',1);" <c:if test="${type eq 'serviceCall' && status == '1'}">class="xx_on"</c:if>>
							<div>
								<p>
								<span class="span"><img src="${ctx}/static/images/popup/xiaoxi_cl_pxd5.png" /></span>
									<span>服务记录</span>
								</p>
							</div>
						</li>
						<li onclick="MsgChange('displayCall','1',1);" <c:if test="${type eq 'displayCall'}">class="xx_on"</c:if>>
							<div>
								<p>
								<span class="span"><img src="${ctx}/static/images/popup/xiaoxi_cl_pxd6.png" /></span>
									<span>来电显示</span>
									<span class="red">
										<c:if test="${xianshiCount != null && xianshiCount > 0}">(${xianshiCount})</c:if>
									</span>
								</p>
							</div>
						</li>
					</ul>
					<div class="xiaoxi_cl">
						<c:if test="${type eq 'displayCall'}">
							<c:forEach items="${selfMessages.content}" var="mes" varStatus="status">
								<div class="xiaoxi_list" style="padding-left:10px;width:710px;">
									<span><fmt:formatDate  value="${mes.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
									<c:if test="${mes.memberName ne null}">
										<span>${mes.memberName}会员来电</span>
									</c:if>
									<span>${mes.content}</span>
									<c:choose>
										<c:when test="${mes.useredHanleStatus eq '1'}"><span style="color:#57b336;">外卖</span></c:when>
										<c:when test="${mes.useredHanleStatus eq '2'}"><span style="color:#57b336;">预订</span></c:when>
										<c:when test="${mes.useredHanleStatus eq '3'}"><span style="color:#f00;">取消</span></c:when>
										<c:otherwise>
											<input type="button" onclick="messageTakeout('${mes.id}','${mes.content}');" class="but_wm_p" value="外卖">
											<input type="button" onclick="messageOrder('${mes.id}','${mes.content}');" class="but_wm_p" value="预订">
											<input type="button" onclick="messageCenterCancel('${mes.id}','${mes.content}');" class="but_wm_p" value="取消">
										</c:otherwise>
									</c:choose>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${type ne 'displayCall'}">
							<c:forEach items="${selfMessages.content}" var="mes" varStatus="status">
							<div class="xiaoxi_list">
								<a class="xiaoxi_switch"  onclick="showNextDishesDetail(this);"></a>
								<span><fmt:formatDate  value="${mes.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								<span>${mes.content}</span>
								<div class="but_xx_wrap">
									<c:if test="${mes.status == '0'}">
										<a class="but_qcl"  onclick="msgHandle('${mes.id}');" <c:if test="${mes.mesType != '0'}">style="display: none;"</c:if>></a>
										<a class="but_hulue" onclick="deleteMsg('${mes.id}');"></a>
									</c:if>
									<c:if test="${mes.status == '1'}">
										<a class="but_hftx" onclick="reStartService('${mes.id}');"></a>
									</c:if>
								</div>
							</div>
							<c:if test="${mes.mesType == '0'}">
								<c:if test="${mes.selfOrder.dishList != null }">
									<div class="xx_details_wrap"  style="display: none;">
										<div class="xx_arrow"></div>
										<table border="0" class="xx_table">
											<tr>
											<td>序号</td>
											<td>菜名</td>
											<td>数量</td>
											<td>状态</td>
											</tr>
											<c:forEach items="${mes.selfOrder.dishList}" var="dish" varStatus="statusd">
												<tr <c:if test="${dish.status == 0}">style="color: red;"</c:if>>
													<td>${statusd.index+1 }</td>
													<td>${dish.dishesName }</td>
													<td>${dish.saleNum }/份</td>
													<td>
													<c:if test="${dish.status == 0}">未处理</c:if>
													<c:if test="${dish.status == 1}">已处理</c:if>
													</td>
												</tr>
											
											</c:forEach>
											<c:if test="${mes.selfOrder.notes != null && mes.selfOrder.notes != '' }">
												<tr>
													<td colspan="3">备注：${mes.selfOrder.notes}</td>
												</tr>
											</c:if>
										</table>
									</div>
								</c:if>
							</c:if>
							</c:forEach>
						</c:if>
					</div>

<div class="swtc_page">
	<tags:paginationcardmsg page="${selfMessages}" paginationSize="15"/>
</div>



					
