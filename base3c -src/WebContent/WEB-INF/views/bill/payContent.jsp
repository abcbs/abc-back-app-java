<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input id="billType" type="hidden" value="${dinerBill.billType}" />
<div class="list_b_in">
			<div class="tab_nav_wrap">
				<ul class="tab_nav">
					<li><a style="cursor:pointer;" onclick="tabChange(this,'${ctx}/bill/list')">账单列表(1)</a></li>
					<li><a style="cursor:pointer;" onclick="tabChange(this,'${ctx}/bill/diancai')">点餐(2)</a></li>
					<li class="on"><a href="###" style="cursor:default;">结账(3)</a></li>
				</ul>
			</div>
				<div class="jiezhang_wrap">
					<div class="jiezhang_list">
						<div class="jiezhang_list_in">
							<p class="w_250 left">会员卡号：${dinerBill.membershipCard.cardNo }</p>
							<p class="w_140 left">会员姓名：${dinerBill.restMemberInfo.name }</p>
							<p class="w_140 right">卡类型：${dinerBill.membershipCard.membershipCardClass.name}</p>
							<p class="w_250 left">余额：<fmt:formatNumber value="${dinerBill.membershipCard.balance}" pattern="#.##" type="number"/></p>
							<p class="w_140 left">剩余积分：<fmt:formatNumber value="${dinerBill.membershipCard.memberIntegral}" pattern="#.##" type="number"/></p>
							<p class="w_140 right">增加积分：<fmt:formatNumber value="${dinerBill.addIntegral}" pattern="#.##" type="number"/></p>
							<p class="w_250">工作单位：${company}</p>
							<%-- <p class="w_250">备注：${dinerBill.notes}</p> --%>
						</div>
					</div>
					<div class="jiezhang_list">
						<div class="jiezhang_list_in">
							<p class="w_250">菜品消费：<fmt:formatNumber value="${dinerBill.oriCost}" pattern="#.##" type="number"/></p>
							<p class="w_400">
								服务费：<fmt:formatNumber value="${dinerBill.serviceChargeMoney}" pattern="#.##" type="number"/>
								<c:if test="${dinerBill.serviceChargeType ne null}">
									<c:if test="${dinerBill.serviceChargeType ne null}">
										 <c:choose>
											<c:when test="${dinerBill.serviceChargeType eq '1'}">
												（按比例<fmt:formatNumber value="${dinerBill.seviceChargeNum}" pattern="#.##" type="number"/>%）
											</c:when>
											<c:when test="${dinerBill.serviceChargeType eq '2'}">
												（按时间<fmt:formatNumber value="${dinerBill.seviceChargeNum}" pattern="#.##" type="number"/>元/5分钟，不足5分钟不计价	）										</c:when>
											<c:when test="${dinerBill.serviceChargeType eq '3'}">
												（固定<fmt:formatNumber value="${dinerBill.seviceChargeNum}" pattern="#.##" type="number"/>元）
											</c:when>
											<c:when test="${dinerBill.serviceChargeType eq '4'}">
												（按人<fmt:formatNumber value="${dinerBill.seviceChargeNum}" pattern="#.##" type="number"/>元/人）
											</c:when>
										</c:choose>
									</c:if>
								</c:if>
								
							</p>
							<p class="jiage text_green">消费合计：<fmt:formatNumber value="${dinerBill.consumeCost}" pattern="#.##" type="number"/></p>
						</div>
					</div>
					<div class="jiezhang_list">
						<div class="jiezhang_list_in">
							<p class="w_250 left">折扣方案：
								<c:choose>
									<c:when test="${dinerBill.isCustomDiscount eq 1}">
										自定义折扣${dinerBill.discount}%
									</c:when>
									<c:otherwise>
										${dinerBill.discountName}
									</c:otherwise>
								</c:choose>
							</p>
							<p class="w_140 left">折后金额：<fmt:formatNumber value="${dinerBill.consumeCost - dinerBill.saveCost}" pattern="#.##" type="number"/></p>
							<p class="jiage text_green">折扣优惠：<fmt:formatNumber value="${dinerBill.saveCost}" pattern="#.##" type="number"/></p>
							<p class="w_400 left" style="margin-left:70px;">
								特价菜肴不参与折扣${dinerBill.dishesTypeDiscountDesc}
								<c:choose>
								       <c:when test="${dinerBill.table.tableArea.isOnSale == 0 && dinerBill.table.tableArea.isSpecialPrice == 0}">
								       		<br/>
											<font color="red">该餐台不参与打折和特价，不能享受折扣和特价优惠</font>
								       </c:when>
								       <c:when test="${dinerBill.table.tableArea.isOnSale == 0}">
								       		<br/>
											<font color="red">该餐台不参与打折，不能享受折扣优惠</font>
								       </c:when>
								  		<c:when test="${dinerBill.table.tableArea.isSpecialPrice == 0}">
								  			<br/>
											<font color="red">该餐台不参与特价，不能享受特价优惠</font>
								       </c:when>
								       <c:otherwise>
								       </c:otherwise>
								</c:choose>
							</p>
						</div>
					</div>
					<div class="jiezhang_list">
						<div class="jiezhang_list_in">
							<p class="w_250 left">抹零：<fmt:formatNumber value="${dinerBill.molingModeCost}" pattern="#.##" type="number"/>  </p>
							<c:if test="${dinerBill.billType eq 2}">
								<p class="w_250 left">送餐费：<fmt:formatNumber value="${dinerBill.deliverCost}" pattern="#.##" type="number"/>  </p>
							</c:if>
							<p class="jiage text_green">应付金额：<lable id="payableCost_lable" ><fmt:formatNumber value="${dinerBill.payableCost}" pattern="#.##" type="number"/></lable></p>
						</div>
					</div>
					<div class="jiezhang_list">
						<div class="jiezhang_list_in">
							<input id="payments" type="hidden" value="${dinerBill.payments}">
							<p class="w_400 text_b"> ${dinerBill.payments} 
							<c:if test="${dinerBill.payments != null && dinerBill.payments != ''}">
								&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							找零：
							<lable id="oddChange_lable">
							<fmt:formatNumber value="${dinerBill.oddChange}" pattern="#.##" type="number"/>
							</lable>
							</p>
							<input id="needMoney" type="hidden" value="${dinerBill.needMoney}">
							<c:if test="${dinerBill.billStatus ne '3'}">
								<p class="jiage text_red">待收金额：<lable id="needMoney_lable"><fmt:formatNumber value="${dinerBill.needMoney}" pattern="#.##" type="number"/></lable></p>
							</c:if>
						</div>
					</div>
					<div class="jiezhang_list">
						<div class="jiezhang_list_in">
							<p>备注：${dinerBill.billNotes}</p>
							<p>
								<c:if test="${dinerBill.isDrawBill == 1}">
									已开发票
								</c:if>
							</p>
						</div>
					</div>
					<c:if test="${dinerBill.billStatus != '3' && dinerBill.billStatus != '8' && dinerBill.billStatus != '10'}">
					<div class="jiezhang_group" id="pay_jiezhang_group">
					<div class="jz_ground_a"> 
					
					
					
						<c:forEach items="${paymentTypes }" var="type" varStatus="status" >
							<c:if test="${type.paymentType != '10' && type.paymentType != '11'}">
								<c:choose>
									<c:when test="${type.subType eq 'MONEY' || type.subType eq 'WEBSITE' || type.subType eq 'OTHER'}">
										<c:set var="width" value="550"/>
									</c:when>
									<c:otherwise>
										<c:set var="width" value="880"/>
									</c:otherwise>
								</c:choose>
								<div class="jiezhang mr_8" onclick="javascript:popPaymentType('${ctx}/bill/pop/paymentType?billId=${dinerBill.billId}&cptId=${type.cptId}&paymentType=${type.paymentType}&money=${type.money}&needMoney=${dinerBill.needMoney}','${type.cptId}','${type.paymentType}','${width}');" id="${type.cptId}">
									<div>
										<p>
											<span>
												<c:choose>
													<c:when test="${type.money eq null}">
														${type.paymentName}
													</c:when>
													<c:otherwise>
														${type.paymentName} <br/> <fmt:formatNumber value="${type.money}" pattern="#.##" type="number"/>
													</c:otherwise>
												</c:choose>
												<c:set var="index" value="${status.index}"></c:set>
											</span>
											<span></span>
										</p>
									</div>
								</div>
							</c:if>
						</c:forEach>
							<c:if test="${index <= 11}">
							<c:forEach begin="1" end="${11-index}">
								<div class="jiezhang_no mr_8">
									<div>
										<p>
											<span></span>							
										</p>
									</div>
								</div>
							</c:forEach>
							</c:if>
							<c:if test="${index <= 17 && index > 11}">
							<c:forEach begin="1" end="${17-index}">
								<div class="jiezhang_no mr_8">
									<div>
										<p>
											<span></span>							
										</p>
									</div>
								</div>
							</c:forEach>
							</c:if>
						
						
						
						<div  class="left w_416">
							<div class="jiezhang mr_8" onclick="popForm('折扣方案','${ctx}/bill/pop/cashDiscount/${dinerBill.billId}','500','400');">
								<div>
									<p>
										<span>折扣方案</span>
										<span></span>							
									</p>
								</div>
							</div>
							
							<c:choose>
								<c:when test="${dinerBill.isMoling eq '1' || dinerBill.isMoling eq null}">
									<div class="moling_on mr_8" style="cursor:pointer;" onclick="javascript:popForm('抹零','${ctx}/bill/pop/molingForm?billId=${dinerBill.billId}',500,644)" id="moling">
										<div>
											<p>
												<span>抹零</span>
											</p>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="jiezhang mr_8" onclick="javascript:popForm('抹零','${ctx}/bill/pop/molingForm?billId=${dinerBill.billId}',500,644)" id="moling">
										<div>
											<p>
												<span>抹零</span>
											</p>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
							
							
							<div class="jiezhang mr_8" onclick="window.location='${ctx}/bill/predict/${dinerBill.billId}'">
								<div>
									<p>
										<span>预结</span>
										<span></span>							
									</p>
								</div>
							</div>
							<c:if test="${dinerBill.billType != '2' && dinerBill.billType != '4'}">
								<div class="jiezhang " onclick="popForm('开台','${ctx}/index/pop/kaitai/update/${dinerBill.table.tabId}','880','644');">
									<div>
										<p>
											<span>开台修改</span>
											<span></span>							
										</p>
									</div>
								</div>
							</c:if>
							<!-- 外卖无开台 -->
							<c:if test="${dinerBill.billType == '2' || dinerBill.billType == '4'}">
								<div class="jiezhang_no">
									<div>
										<p>
											<span></span>
											<span></span>							
										</p>
									</div>
								</div>
							</c:if>
							
							<div class="jiezhang mr_8" onclick="popForm('开发票','${ctx}/bill/pop/drawBill/${dinerBill.billId}','529','644');">
								<div>
									<p>
										<span>开发票</span>
										<span><fmt:formatNumber value="${dinerBill.drawBillAmount}" pattern="#.##" type="number"/></span>							
									</p>
								</div>
							</div>
							<div class="jiezhang mr_8" onclick="popForm('备注','${ctx}/bill/pop/notes/${dinerBill.billId}','529','335');"> 
								<div>
									<p>
										<span>备注</span>
										<span></span>							
									</p>
								</div>
							</div>
							<%-- <div class="jiezhang" onclick="popForm('修改金额','${ctx}/index/pop/permission/create','529','335');"> 
								<div>
									<p>
										<span>修改金额</span>
										<span></span>							
									</p>
								</div>
							</div> --%>
							<div class="jiezhang_no mr_8">
								<div>
									<p>
										<span></span>
										<span></span>							
									</p>
								</div>
							</div>
							<div class="jiezhang_no mr_8">
								<div>
									<p>
										<span></span>
										<span></span>							
									</p>
								</div>
							</div>
						</div>
						<div class="left">
							<c:choose>
								<c:when test="${dinerBill.billStatus eq settle}">
									<div class="jiezhang_big mr_8">
										<div>
											<p>
												<span>已结账</span>
												<span></span>							
											</p>
										</div>
									</div>
									<div class="jiezhang_big">
										<div>
											<p>
												<span>已结账</span>
												<span></span>							
											</p>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="jiezhang_big mr_8" onclick="pay(false,false)" id="payedNotPrint">
										<div>
											<p>
												<span>结账不打印</span>
												<span>(7)</span>							
											</p>
										</div>
									</div>
									<div class="jiezhang_big" onclick="pay(true,false)"  id="payedAndPrint">
										<div>
											<p>
												<span>结账打印</span>
												<span>(8)</span>							
											</p>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
									<div class="jiezhang_big" style="display: none;">
									</div>
						</div>
						
						
						
						</div>
					</div>
					</c:if>
					<c:if test="${dinerBill.billStatus == '3' || dinerBill.billStatus == '8' || dinerBill.billStatus == '10'}">
									<div class="jiezhang_over"><p>${dinerBill.billStatusDesc}</p></div>
					</c:if>
					
					
					
					
				</div>
				</div>