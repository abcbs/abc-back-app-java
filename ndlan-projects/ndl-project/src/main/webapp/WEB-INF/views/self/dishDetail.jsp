<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="remoteAddr" value="${pageContext.request.remoteAddr}" />
<c:set var="remotePort" value="${pageContext.request.serverPort}" />
<html> 
　<head> 
　 <title>-自助点餐</title> 
　 <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no"> 
<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>


　 <link rel="stylesheet" href="${ctx}/static/jquerymobile/css/themes/22/22.css" />
 <link rel="stylesheet" href="${ctx}/static/jquerymobile/css/themes/22/jquery.mobile.icons.min.css" />
 <link rel="stylesheet" href="${ctx}/static/jquerymobile/css/themes/default/jquery.mobile.structure-1.4.0.css" />
 <link rel="stylesheet" href="${ctx}/static/css/jquerymobile/custom.css" />
　 <script src="${ctx}/static/jquerymobile/js/jquery.mobile-1.4.0.min.js"></script>

 <link rel="stylesheet" href="${ctx}/static/css/toastrSelf.css" />
　 <script src="${ctx}/static/js/toastr.js"></script>

<link href="${ctx}/static/photoSwipe/styles.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/photoSwipe/photoswipe.css" type="text/css" rel="stylesheet" />
<style type="text/css">
/*评分用*/
.user_rate {font-size:14px; position:relative; padding:20px 0;margin-left: 5px;}
.user_rate p {margin:0; padding:0; display:inline; height:40px; overflow:hidden; position:absolute; top:0; left:100px; margin-left:140px;}
.user_rate p span.s {font-size:36px; line-height:36px; float:left; font-weight:bold; color:#DD5400;}
.user_rate p span.g {font-size:22px; display:block; float:left; color:#DD5400;}
.big_rate_bak {width:140px; height:28px; text-align:left; position:absolute; top:3px; left:85px; display:inline-block; background:url(${ctx}/static/images/self/lajiao.gif) left bottom repeat-x;}
.big_rate_bak b {display:inline-block; width:24px; height:28px; position:relative; z-index:1000; cursor:pointer; overflow:hidden;}
.big_rate_up {width:140px; height:28px; position:absolute; top:0; left:0; background:url(${ctx}/static/images/self/lajiao.gif) left top;}

</style>	
	<script type="text/javascript" src="${ctx}/static/photoSwipe/simple-inheritance.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/photoSwipe/code-photoswipe-1.0.11.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/self/slider.js"></script>
	
	
	<script src="${ctx}/static/jquerymobile/jquery.floatDiv.js"></script>
<script src="${ctx}/static/js/self/common.js"></script>

<script src="${ctx}/static/js/self/mobile.self.leftSlider.js"></script>
	
	
	
	<script type="text/javascript">
		
		// Set up PhotoSwipe with all anchor tags in the Gallery container 
		document.addEventListener('DOMContentLoaded', function(){
			
			Code.photoSwipe('a', '#Gallery');
			
		}, false);
		
		
		/* 
			Overview: 
			---------
			
			Code.photoSwipe(thumbnail-elements, container-element, options);
				
			When you specify a container-element, the helper uses event delegation
			to handle the click event, i.e. rather than having 50 click event listeners
			for each thumbnail, we have one for the container then try and deduce which
			thumbnail was clicked. If you omit a container, an event listener will be placed 
			on each individual thumbnail found.
			
			
			With options:
			-------------
			Code.photoSwipe('a', '#Gallery', { loop: false } );
			
			
			Using preselected elements:
			---------------------------
			var galleryEl = document.getElementById('Gallery');
			var thumbEls = galleryEl.querySelectorAll('a');
			
			Code.photoSwipe(thumbEls, galleryEl);
		
		*/
		$(document).bind("pageinit", function() {
			getShoppingCarInfo();
		});		
		function getShoppingCarInfo(){
			$.post("getShoppingCarInfo", { tabNO:$('#tabNO').val() }, function(data){
			  $("#shopping_car").text(data+"份");
			});
		};
		
		
		function addDishe(o){
			var dishNum = $("#dishSaleNum").val();
			var dishUnitName = $("#dishesUnitName").val();
			
			var lia = $(o);
			if(!dishNum || dishNum < 0 )
			{
				dishNum = 0;
			}
			if(dishNum >= 0)
			{
				dishNum = parseInt(dishNum)+1;
				var dishId=$("#dishId").val();
				
				var dishestimate=$("#estimate").val();
				if(parseInt(dishestimate)<dishNum){
					toastr.info("该菜品已沽清!");
				}else{
					$.post("${ctx}/self/addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:dishNum,isSet:$('#isSet').val()}, function(data) {
						if(data.statusCode == '200')
						{
							$("#dishSaleNum").val(data.value)
							lia.prev(".cy_amount").text(data.value+" " + dishUnitName);
							getShoppingCarInfo();
						}
						else if(data.statusCode == '400')
						{
							toastr.info("请先扫描二维码开台!");
						}
							
					});
				}
				
			}
		}
		
		
		function minusDish(o)
		{
			var dishNum = parseFloat($("#dishSaleNum").val());
			var dishUnitName = $("#dishesUnitName").val();
			var lia = $(o);
			if(dishNum > 0)
			{
				dishNum = dishNum-1;
				var dishId=$("#dishId").val();
				$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:dishNum,isSet:$('#isSet').val()}, function(data) {
					if(data.statusCode == '200')
					{
						$("#dishSaleNum").val(data.value)
						lia.next(".cy_amount").text(data.value+" " + dishUnitName);
						getShoppingCarInfo();
					}
					else if(data.statusCode == '400')
					{
						toastr.info("请先扫描二维码开台!");
					}
					
				});
			}
			else
			{
				lia.next(".cy_amount").text("0 " + dishUnitName);
			}
			
		}
		
		
		//////////////////////////////
	$(function(){
		var pungentLevel = (${dish.pungentLevel}+1-1)>=0?${dish.pungentLevel}+1-1:0;
		pungentLevel=pungentLevel+'0';
		get_rate(pungentLevel);
	})
	function get_rate(rate){
		rate=rate.toString();
		var s;
		var g;
		$("#g").show();
		if (rate.length>=3){
			s=10;	
			g=0;
			$("#g").hide();
		}else if(rate=="0"){
			s=0;
			g=0;
		}else{
			s=rate.substr(0,1);
			g=rate.substr(1,1);
		}
		$("#s").text(s);
		$("#pungentLevel").val(s);
		//$("#g").text("."+ g);
		$(".big_rate_up").animate({width:(parseInt(s)+parseInt(g)/5) * 28,height:26},1000);
		
	}
	function up_rate(rate){
		$(".big_rate_up").width("0");
		get_rate(rate);
	}

	</script>
	

</head>
<body>
<div data-role="page" style="background:#fff;">

<input type="hidden" name="tabNO" id="tabNO" value="${tabNO}"/>
<input type="hidden" name="isSet" id="isSet" value="${isSet}"/>
	
	　<div data-role="header">
			<span class="back" style="cursor: pointer;margin-top:5px;" onclick="window.location='${ctx}/self/main?tabNO=${tabNO}'"></span>
			<h1>菜肴详情</h1>
	  </div>
	
<div id="wrapper">
	
    
        <div id="slider">    
			<c:if test="${fn:length(pics) > 0 }">
            	<img class="scrollButtons left" src="${ctx}/static/images/jquerymobile/leftarrow.png">
			</c:if>
			
			<div style="overflow: hidden;" class="scroll">
	
				<div class="scrollContainer" >
	
					<c:forEach items="${pics}" var="pic" varStatus="status">
						<div class="panel" id="panel_${status.index+1}">
							<div class="inside">
								<img src="http://${serverIp}:${remotePort}/canyin-main/${pic.picUrl1024x1024}" alt="picture" />
							</div>
						</div>
					</c:forEach>
	                
					<c:if test="${dish.dishesPicUrl == null || dish.dishesPicUrl ==''}">
		                <div class="panel">
							<div class="inside">
								<img src="${ctx}/static/images/self/cai.jpg" alt="picture" />
								<h2></h2>
							</div>
						</div>
					</c:if>
                </div>

            </div>

			<c:if test="${fn:length(pics) > 0 }">
				<img class="scrollButtons right" src="${ctx}/static/images/jquerymobile/rightarrow.png">
			</c:if>
			

        </div>
        
    </div>
    

	<div data-role="header" style="overflow:hidden;">
			<span style="margin-left:10px;top:10px;position: absolute;color: black;display: block;">${dish.dishesName}</span>
			<h1 style="margin-right:10px;color: #388000;text-align: right;">${dish.price}元/${dish.dishesUnit.name }</h1>
	</div>
	
	<input type="hidden" name="dishId" id="dishId" value="${dish.dishesId}"/>
	<input type="hidden" name="estimate" id="estimate" value="${dish.estimate}"/>
	<input type="hidden" name="dishSaleNum" id="dishSaleNum" value="${dish.selfDishSaleNum}"/>
	<input type="hidden" name="dishesUnitName" id="dishesUnitName" value="${dish.dishesUnit.name }"/>
	
	
	<div data-role="header" style="overflow:hidden;background-color: white;border: none;">
				<h1 style="margin-left:10px;color: black;text-align: left;width:200px;float:left;padding-top:12px;">辣度：</h1>
				<div class="user_rate" style="margin-top:6px;">
				   <div class="big_rate_bak" style="z-index:0">
				        <b rate="1" >&nbsp;</b>
				        <b rate="2">&nbsp;</b>
				        <b rate="3">&nbsp;</b>
				        <b rate="4">&nbsp;</b>
				        <b rate="5">&nbsp;</b>
				        <div style="width:45px;" class="big_rate_up"></div>
				    </div>
				    <p>
			         <!--<span id="s" class="s"> -->
				    </span><span id="g" class="g"></span></p>
				</div>
	</div>
	 
					
<%-- 	<c:if test="${dish.pungentLevel > 0}"> --%>
<!-- 		<div data-role="header" style="overflow:hidden;background-color: white;border: none;margin-left: 10px;text-align: right;"> -->
<%-- 				<c:forEach begin="1" end="${dish.pungentLevel}"> --%>
<!-- 					<span class="lajiao"></span> -->
<%-- 				</c:forEach> --%>
<!-- 		</div> -->
<%-- 	</c:if> --%>
	
		
	<div data-role="header" style="overflow:hidden;background-color: white;border: none;margin-left: 10px;text-align: right;">
			<span class="cy_but_minus" onclick="minusDish(this);"></span>
			<span class="cy_amount"> ${dish.selfDishSaleNum} ${dish.dishesUnit.name }</span>
			<span class="cy_but_add" onclick="addDishe(this);"></span>
	</div>
	
	  <div data-role="header" style="overflow:hidden;background-color: white;border: none;">
				<h1 style="margin-left:10px;color: black;text-align: left;">销量：${saleNum}</h1>
		</div>
	<c:if test="${categoryName != null && categoryName != ''}">
		<div data-role="header" style="overflow:hidden;background-color: white;border: none;">
				<h1 style="margin-left:10px;color: black;text-align: left;">菜类：${categoryName}</h1>
		</div>
	</c:if>
	<c:if test="${dishesTasteStr != null && dishesTasteStr != ''}">
		<div data-role="header" style="overflow:hidden;background-color: white;border: none;">
				<h1 style="margin-left:10px;color: black;text-align: left;">口味：${dishesTasteStr }</h1>
		</div>
	</c:if>
	
	<c:if test="${dishesMaterialStr != null && dishesMaterialStr != ''}">
	<div data-role="header" style="overflow:hidden;background-color: white;border: none;">
			
			<h1 style="margin-left:10px;color: black;text-align: left;">食材：${dishesMaterialStr }</h1>
	</div>
	</c:if>
	
		<div data-role="header" style="overflow:hidden;background-color: white;border: none;">
				<h1 style="margin-left:10px;color: black;text-align: left;">菜系：${dishesStylesStr }</h1>
		</div>
	
	<div data-role="header" style="overflow:hidden;background-color: white;border: none;height:100%;">
			<span style="margin-left:10px;top:10px;color: black;display: block;">简介：${notes }</span>
			<h1 style="margin-left:10px;color: black;text-align: left;"></h1>
	</div>
	<div style="margin-left:10px;margin-top:10px;"><a herf="#;" style="color:#76CA40;"  onclick="window.location='${ctx}/self/main?tabNO=${tabNO}'">返回>></a></div>
	
	<div data-role="popup" id="popupShop" data-theme="none" style="float: left;">
       	 	<div id="goOrderAAAAA" onclick="window.location='${ctx}/self/showOrderedDishList?tabNO=${tabNO}'"  class="shopping_car"><span><a rel="external" data-ajax="false" id="shopping_car"  href="#" class="show-page-loading-msg" data-textonly="false" data-textvisible="true" data-msgtext="查看您点的菜">0份</a></span></div>
	</div>
	
	
　</div><!-- /content -->
</body>
</html>