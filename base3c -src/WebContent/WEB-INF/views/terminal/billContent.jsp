<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<input  type="hidden" id="billStatus" value="${newBill.billStatus}"/>
<div class="HYK_left_top"><span class="danhao">单号${newBill.billNo}</span><span class="sanchu" onclick="deleteAllDish();">全部删除</span></div> 
<div class="HYK_left_connect">
	<ul>
		<c:forEach items="${dinerBillDishes}" var="dinerBillDishe" varStatus="status">
			<li>
				<p class="bianhao">${status.index+1}</p>
				<p class="caipin">${dinerBillDishe.dishesName}</p>
				<p class="yuan">${dinerBillDishe.realUnitPrice}元</p>
				<p class="jianhao" onclick="deleteDish(this,'${dinerBillDishe.bdId}','${dinerBillDishe.dishesName}','0');"></p>
			</li>
		</c:forEach>
    </ul>
</div> 
<div class="HYK_left_bottom">
	<div class="HYK_left_bottom_top">总计：<span><fmt:formatNumber value="${newBill.oriCost}" type="currency" pattern="#.##"/></span>元</div>
	<input type="hidden" id="billNeedMoney" value="${newBill.needMoney}"/>
    <div class="HYK_left_bottom_bottom">
    	<ul>
        	<li class="HYK_left_bottom_bottom_li1" onclick="cardSelect();"></li>
            <li class="HYK_left_bottom_bottom_li2" onclick="cardPay();"></li>
            <li class="HYK_left_bottom_bottom_li3" onclick="bankPay();"></li>
        </ul>
    </div>
  
    
</div>