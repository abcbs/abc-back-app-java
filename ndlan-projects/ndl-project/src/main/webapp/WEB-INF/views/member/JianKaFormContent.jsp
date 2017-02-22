<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
				<div class="left">
						<div class="ksfk_right_top">
							<input name="popSearchKeyWord" id="popSearchKeyWord" value="${searchMapParams.LIKE_cardNo}" class="input_longer_p" type="text">
                            <input name="buttonSearch" id="buttonSearch" type="button" class="input_button_p" onclick="popSerachKeyWords($('#popSearchKeyWord'));">
							<div  class="page_wrap_other" style="margin-top:25px;">
								<tags:paginationcardpop paginationSize="15" page="${membershipCards}"></tags:paginationcardpop>
							</div>
						</div>
						<dl class="ksfk_infor" id="popCardList">
							<dt>
								<span class="w_200 left">卡号</span>
								<span class="w_100 left">卡类型</span>
								<span class="w_100 left">余额</span>
							</dt>
								<c:forEach items="${membershipCards.content}" var="membershipCard" varStatus="mstatus">
									<dd>
										<a style="cursor:pointer;" onclick="addCardToMember('${membershipCard.mcId}','${membershipCard.cardNo}',this,'${membershipCard.cashPledge}','${membershipCard.cardPassword}','${membershipCard.membershipCardClass.name}','${membershipCard.balance}','${membershipCard.membershipCardClass.mcclassId}');"  mcId="${membershipCard.mcId}" cardNo="${membershipCard.cardNo}">
											<span class="w_200 left">${membershipCard.cardNo}</span>
											<span class="w_100 left">${membershipCard.membershipCardClass.name}</span>
											<span class="w_100">${membershipCard.balance}</span>
										</a>
									</dd>
								</c:forEach>
						</dl>
					</div>