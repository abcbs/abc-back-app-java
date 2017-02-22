<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html> 
　<head> 
　 <title>-自助点餐</title> 
　 <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no"> 
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
　 <link rel="stylesheet" href="${ctx}/static/jquerymobile/css/themes/22/22.css" />
 <link rel="stylesheet" href="${ctx}/static/jquerymobile/css/themes/22/jquery.mobile.icons.min.css" />
 <link rel="stylesheet" href="${ctx}/static/jquerymobile/css/themes/default/jquery.mobile.structure-1.4.0.css" />
 <link rel="stylesheet" href="${ctx}/static/css/jquerymobile/custom.css" />
  <link rel="stylesheet" href="${ctx}/static/css/toastrSelf.css" />
　 <script src="${ctx}/static/jquerymobile/js/jquery.mobile-1.4.0.min.js"></script>
<script src="${ctx}/static/jquerymobile/jquery.floatDiv.js"></script>
 <script src="${ctx}/static/js/toastr.js"></script>
<script src="${ctx}/static/js/self/common.js"></script>
<script type="text/javascript">
	// $("a.but_control").pageslide({ direction: "left", modal: true });
	$(document).bind("pageinit", function() {
		// 全单删除
		$("#deleteAllDish").click(function() {

			$.post("delAllDish", {
				tabNO : $('#tabNO').val()
			}, function(data) {
				//alert(data.message);
				$("li[id]","#dishList").remove();
				calTotalPrice();
				calTotalCount();
				$.mobile.loading( "hide" );
			});

		});

		// 呼叫下单
		$("#callOrder").click(function() {
			if($("#totalDishCount").val()<1){
				toastr.error("请先点菜再呼叫下单!");
				return false;
			}else{
				window.location='${ctx}/self/commitOrder?tabNO=${tabNO}';
				return true;
			}
			
			
		});
		$(".but_call__").click(function() {
			$.ajax({
				url : "commitOrder",
				// data: $("#Form1, #Form2, #Form3").serialize(),
				data : {
					tabNO : $('#tabNO').val()
				},
				type : "post",
				dataType : "json",
				success : function(data) {
					alert("but_call:" + data.message);
					toastr.info(data.message);
				}
			});
		});
	});
	
	$(document).delegate('#remarkSave', 'click', function() {
		$.post("cookRemark", {tabNO:$('#tabNO').val(),cookRemark:$('#cookRemarkText').val()}, function(data) {
			$("#remarkShow").text("备注："+$('#cookRemarkText').val());
			if($('#cookRemarkText').val())
			{
				$("#remarkShow").parent().show();
			}
			else
			{
				$("#remarkShow").parent().hide();
			}
			$("#popupBeizhu").popup("close");
		});
	});
	
	$(document).delegate('.dishItem', 'taphold', function() {
		swich_=false;
		var amount=$(this).children(".ui-li-count");
		amount.text(amount.text()-1+11);
		var dishId=$(this).parent().attr("id");
		
		$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:amount.text()}, function(data) {
			getShoppingCarInfo();
		});
		
		$(this).attr('data-ajax', false)
        .live('click', function(e){
                e.preventDefault();
                e.stopPropagation(); 
        });
		
		return false;
	});
	
	function addDishe(o){
		var lia = $(o);
		var num = lia.children(".ui-li-count").text();
		if(!num || num < 0 )
		{
			num = 0;
		}
		if(num >= 0)
		{
			num = parseInt(num)+1;
			var dishId=lia.parent().attr("id");
			$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:num}, function(data) {
				if(data.statusCode == '200')
				{
					lia.children(".ui-li-count").show();
					lia.next().removeClass("ui-state-disabled");
					lia.next().addClass("red");
					lia.children(".ui-li-count").text(data.value);
					lia.children(".ui-li-totalPrice").text(data.sign);
					calTotalPrice();
				}
			});
		}
	}
	
	
	function minusDish(o)
	{
		var lia = $(o).prev();
		var num = parseInt(lia.children(".ui-li-count").text());
		if(num > 0)
		{
			num = num-1;
			var dishId=lia.parent().attr("id");
			$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:num}, function(data) {
				if(data.statusCode == '200')
				{
					lia.children(".ui-li-count").text(data.value);
					lia.children(".ui-li-totalPrice").text(data.sign);
					if(data.value == 0)
					{
						$(o).parent().remove();
					}
					calTotalPrice();
					calTotalCount();
				}
			});
		}
		else
		{
			lia.children(".ui-li-count").text(0);
		}
		
	}
	
	function calTotalCount()
	{
		
		var tc = 0;
		$("li[id]","#dishList").each(function(){
			var li = $(this);
			var count = li.find(".ui-li-count").text();
			tc +=parseFloat(count);
		});
		
		$("#totalDishCount").val(tc);
	}
	
	function calTotalPrice()
	{
		var oderedPriceTr = $("#orderSum").val();
		var oderedPrice = oderedPriceTr ? parseFloat(oderedPriceTr) : 0;
		
		var tp = 0;
		$("li[id]","#dishList").each(function(){
			var li = $(this);
			var price = li.find(".ui-li-totalPrice").text();
			tp +=parseFloat(price);
		});
		
		$("#totalPrice").text("未下单菜品消费："+tp+"元，总消费："+(tp+oderedPrice)+"元");
	}
	
	
	</script>
</head> 
<body>
<input type="hidden" name="tabNO" id="tabNO" value="${tabNO}"/>
	
　<div data-role="page"   style="background:#fff;">
	　<div data-role="header" style="overflow:hidden;">
			<h1 style="margin-left:50px;color: #388000;width: 200px;">已点菜单 桌号：${tabNO}</h1>
	  </div>
	

	<div role="main" class="ui-content">
	 <ul data-role="listview" data-inset="false" id="dishList">
	 	<li data-icon="false" style="background:#fff;">
	    	<a href="#" style="background:#76ca40;color:#fff;font-size:13px;">
	    		<p>&nbsp;</p>
	    		<span class="ui-li-name" style="font-weight: bolder;left:0.2em;">数量</span>
		    	<span class="ui-li-name" style="font-weight: bolder;left:4.2em;">名称</span>
		    	<span style="font-weight: bolder;right: 11em;" class="ui-li-price">单价</span>
		    	<span style="font-weight: bolder;right: 7em;" class="ui-li-totalPrice">总价</span>
		    	<span style="font-weight: bolder;right:4em;" class="ui-li-fenshu">状态</span>
	    	</a>
	   	 </li>
	   	 
	   	 <input type="hidden" id="totalDishCount" value="${fn:length(dishList)}"/>
	  <c:forEach items="${dishList}" var="dishe" varStatus="status">
	  	<c:set value="${sum+dishe.realCost*dishe.saleNum}" var="sum" />  
	   	<li data-icon="false" id="${dishe.dishesId}" style="background:#fff;">
	    	<a href="#" onclick="addDishe(this);" style="background:#fff;"  class="dishItem">
	    		<p>&nbsp;</p>
	    		<span class="ui-li-count" style="left:2px;right: auto;background-color:#ff6600;color:white;" <c:if test="${dishe.saleNum == null || dishe.saleNum == 0}">style="display: none;"</c:if>>${dishe.saleNum}</span>
		    	<span class="ui-li-name">${dishe.dishesName}</span>
		    	<span class="ui-li-price"><fmt:formatNumber value="${dishe.realCost}" type="currency" pattern="#.##"/></span>
		    	<span class="ui-li-totalPrice"><fmt:formatNumber value="${dishe.realCost*dishe.saleNum}" type="currency" pattern="#.##"/></span>
		    	<span class="ui-li-fenshu">未处理</span>
	    	</a>
	    	<a  style="background:#fff;width: 40px;" data-icon="minus"onclick="minusDish(this);" class="<c:if test="${dishe.saleNum == null || dishe.saleNum == 0}">ui-state-disabled</c:if><c:if test="${dishe.saleNum != null && dishe.saleNum > 0}">red</c:if>" href="#purchase" data-rel="popup" data-position-to="window" data-transition="pop"></a>
	   	 </li>
	   </c:forEach>
	   
	  <c:forEach items="${ordereddishList}" var="dishe" varStatus="status">
	  	<c:set value="${orderSum+dishe.realCost*dishe.saleNum}" var="orderSum" />  
	   	<li data-icon="false" style="background:#fff;">
	    	<a href="#" style="background:#b2b2b2;cursor: text;">
	    		<p>&nbsp;</p>
	    		<span class="ui-li-count" style="left:2px;right: auto;background-color:#ff6600;color:white;" <c:if test="${dishe.saleNum == null || dishe.saleNum == 0}">style="display: none;"</c:if>>${dishe.saleNum}</span>
		    	<span class="ui-li-name">${dishe.dishesName}</span>
		    	<span class="ui-li-price"><fmt:formatNumber value="${dishe.realCost}" type="currency" pattern="#.##"/></span>
		    	<span class="ui-li-totalPrice"><fmt:formatNumber value="${dishe.realCost*dishe.saleNum}" type="currency" pattern="#.##"/></span>
		    	<span class="ui-li-fenshu">已下单</span>
	    	</a>
	    	<a  style="background:#b2b2b2;cursor: text;width: 40px;" data-icon="minus" href="#purchase" data-rel="popup" data-position-to="window" data-transition="pop"></a>
	   	 </li>
	   </c:forEach>
	   
	   
	</ul>
	</div>
	
	
		<div style="overflow:hidden;font-size:11px;<c:if test="${cookRemark == null || cookRemark == ''}">display:none;</c:if>">
				<h2 id="remarkShow">
					备注：${cookRemark}
				</h2>
		</div>
	
	
<input type="hidden" name="orderSum" id="orderSum" value="${orderSum}"/>	
	<div data-theme="none" style="float: left;background-color: white;border: none;"  data-position="fixed" data-role="footer">
		<h1 style="background:#cccccc;margin-left:0px;width: 100%; font-size:12px;color:#ff6600;" id="totalPrice">未下单菜品消费：<fmt:formatNumber value="${sum == null ? 0 : sum}" type="currency" pattern="#.##"/>元，总消费:<fmt:formatNumber value="${sum+orderSum}" type="currency" pattern="#.##"/></h1>
		
		<a href="#popupBeizhu" data-rel="popup" data-transition="slidedown" data-position-to="window"  class="ui-btn ui-btn-inline" style="text-shadow: none;font-family:'Microsoft YaHei UI';text-decoration:none;background:#ff6600;font-size:11px;float:left;color:#fff;">烹饪备注</a>
		<a href="#" id="deleteAllDish" class="ui-btn ui-btn-inline show-page-loading-msg" data-textonly="false" data-textvisible="true" data-msgtext="删除中" style="text-shadow: none;font-family:'Microsoft YaHei UI';text-decoration:none;background:#ff6600;font-size:11px;float:left;color:#fff;">全单删除</a>
		<a href="#" onclick="window.location='${ctx}/self/main?tabNO=${tabNO}'"  data-textonly="false" data-textvisible="true" data-msgtext="去点菜啦" class="ui-btn ui-btn-inline show-page-loading-msg" style="text-shadow: none;font-family:'Microsoft YaHei UI';text-decoration:none;background:#ff6600;font-size:11px;float:left;color:#fff;">继续点菜</a>
		<a id="callOrder" href="#"  data-textonly="false" data-textvisible="true" data-msgtext="呼叫下单" class="ui-btn ui-btn-inline show-page-loading-msg" style="text-shadow: none;font-family:'Microsoft YaHei UI';text-decoration:none;background:#ff6600;font-size:11px;float:left;color:#fff;">呼叫下单</a>
	</div>	
		
		
		<!-- Baidu Button BEGIN -->
	<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare" data="{'text':'${transmit} ','url':'http://xxx.cn'}">
	    <span class="bds_more">更多</span>
	    <a class="bds_tsina">新浪</a>
	    <a class="bds_tqq">腾讯</a>
	    <a class="bds_renren">人人</a>
	</div>
	<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
    <script type="text/javascript" id="bdshell_js"></script>
    <script type="text/javascript">
        document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
    </script>
<!-- Baidu Button END -->


	<div data-role="popup" id="popupBeizhu" data-theme="a" style="float: left;" data-overlay-theme="a">
       	 	<div style="padding:10px 20px;">
	            <h3>烹饪备注:</h3>
		   	 	<textarea id="cookRemarkText"  name="cookRemarkText" placeholder="请填写烹饪备注">${cookRemark}</textarea>
	            <button type="button"  id="remarkSave" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check">保存</button>
			</div>
	</div>
	</div>
</body>
</html>