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
 <script src="${ctx}/static/jquerymobile/js/jquery.mobile-1.4.0.min.js"></script>
 <script src="${ctx}/static/jquerymobile/jquery.mobile.simpledialog2.js"></script>
 <link rel="stylesheet" href="${ctx}/static/jquerymobile/jquery.mobile.simpledialog.css" />
 
 <link rel="stylesheet" href="${ctx}/static/css/toastrSelf.css" />
 <script src="${ctx}/static/js/toastr.js"></script>
<style type="text/css">
 #gotop{
     display:none;
     width:55px;
     height:55px;
     position:fixed;
     right:135px;
     bottom:50px;
     background:url(${ctx}/static/jquerymobile/images/backtop9.png) no-repeat -70px 0px;    
 }
 </style>

<script src="${ctx}/static/jquerymobile/jquery.floatDiv.js"></script>
<script src="${ctx}/static/js/self/common.js"></script>

<script src="${ctx}/static/js/self/mobile.self.leftSlider.js"></script>

<script type="text/javascript">
	var swich_=true; 
	// $("a.but_control").pageslide({ direction: "left", modal: true });
	$(document).bind("pageinit", function() {
		loadDish(0,0,null,'');
		getShoppingCarInfo();
		var refreshId=setInterval("refreshMessage()", 20000);  
		//$("#handMsg").click();
	});
	function refreshMessage() {
		$.post("showCalledMessage", {tabNO:$('#tabNO').val()}, function(data) {
			if(data.statusCode=='201'){
				$('#messageContainer').html(data.message);
				$("#handMsg").click();
			}
		});
		console.log("ids:"+new Date());
	};
	function getShoppingCarInfo(){
		$.post("getShoppingCarInfo", { tabNO:$('#tabNO').val() }, function(data){
		  $("#shopping_car").text(data+"份");
		});
	};
	
	$(document).delegate('.dishItem', 'taphold', function(e) {
		swich_=false;
		var amount=$(this).children(".ui-li-count");
		amount.text(amount.text()-1+11);
		var dishId=$(this).parent().attr("id");
		var isSet=$(this).parent().attr("isSet");
		$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:amount.text(),isSet:isSet}, function(data) {
			getShoppingCarInfo();
		});
		
		$(this).attr('data-ajax', false);
		e.preventDefault();
        e.stopPropagation(); 
		return false;
	});
	
	function addDishe(o){
		if(!swich_){
		//	return false;
		}
		
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
			var isSet=lia.parent().attr("isSet");
			var dishestimate=lia.parent().attr("lang");
			if(parseInt(dishestimate)<num){
				toastr.info("该菜品已沽清!");
			}else{
				$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:num,isSet:isSet}, function(data) {
					if(data.statusCode == '200')
					{
						lia.children(".ui-li-count").show();
						lia.next().removeClass("ui-state-disabled");
						lia.next().addClass("red");
						lia.children(".ui-li-count").text(num);
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
		var lia = $(o).prev();
		var num = parseInt(lia.children(".ui-li-count").text());
		if(num > 0)
		{
			num = num-1;
			var dishId=lia.parent().attr("id");
			var isSet=lia.parent().attr("isSet");
			$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:num,isSet:isSet}, function(data) {
				if(data.statusCode == '200')
				{
					lia.children(".ui-li-count").text(num);
					getShoppingCarInfo();
					if(num == 0)
					{
						lia.children(".ui-li-count").hide();
						$(o).addClass("ui-state-disabled");
						$(o).removeClass("red");
					}
				}
				else if(data.statusCode == '400')
				{
					toastr.info("请先扫描二维码开台!");
				}
				
			});
		}
		else
		{
			lia.children(".ui-li-count").text(0);
		}
		
	}
	
	
	
	function loadDishByType(sortType,a){
		var sortTypeVal=$('#typeVal').val();
		var SaleNumVal=$('#SaleNumVal').val();
		var SaleNumValtem=$('#SaleNumVal').val();
		 if(sortType=='1'){
		 	if(SaleNumVal=='saleNum_ASC'){
				SaleNumVal='saleNum_DESC';
			}else if(SaleNumVal=='saleNum_DESC'){
				SaleNumVal='saleNum_ASC';
			}else{
				SaleNumVal='saleNum_DESC';
			}		
			$('#SaleNumVal').val(SaleNumVal);
			var dishCategory=$('#dishCategoryVal').val();
			currentPageNum = 1;
			loadDish(SaleNumValtem,dishCategory,null,'',$('#isSet').val());
		}else if(sortType=='2'){
			if(sortTypeVal=='price_ASC'){
				sortTypeVal='price_DESC';
			}else{
				sortTypeVal='price_ASC';
			}	
			$('#typeVal').val(sortTypeVal);
			var dishCategory=$('#dishCategoryVal').val();
			currentPageNum = 1;
			loadDish(sortTypeVal,dishCategory,null,'',$('#isSet').val());
		}else if(sortType=='3'){
			var sortType="showSeqRecommend_ASC";
			$('#typeVal').val(sortType);
			var dishCategory=$('#dishCategoryVal').val();
			$("#isRecommend").val(1);
			currentPageNum = 1;
			loadDish(sortType,dishCategory,null,'1',$('#isSet').val());
		}
		else
		{
			currentPageNum = 1;
			loadDish(0,0,null,'',$('#isSet').val());
		}
		 
		// $(a).addClass("ui-btn-active");
		
	};
	function loadDishByCategory(dishCategory,name,a,isSet){
		$('#dishCategoryVal').val(dishCategory);
		$('#dishCategoryValA').text(name);
		$('#isSet').val(isSet);
		
		removeAllCatgoryOnClass(a);
		
		$("#dlistview").listview("refresh");
		var sortType=$('#typeVal').val();
		var isRecommend = $("#isRecommend").val();
		currentPageNum = 1;
		$("#popupNested").panel("close");
		loadDish(sortType,dishCategory,null,isRecommend,isSet);
	};
	function removeAllCatgoryOnClass(a)
	{
		var sid = $(a).attr("id");
		//.removeClass('dishesCategory_on').addClass('dishesCategory_off');;
		$("li",".dlistview").each(function(){
			var li = $(this);
			if(li.attr("id") == sid)
			{
				$(a).removeClass('dishesCategory_off').addClass('dishesCategory_on');
			}
			else
			{
				li.removeClass('dishesCategory_on').addClass('dishesCategory_off');
			}
		});
	}
	
	var currentPageNum = 1;
	function loadDish(sortType,dishCategory,keyWord,isRecommend,isSet){
		if(dishCategory == 0)
		{
			$('#dishCategoryVal').val(0);
			$('#dishCategoryValA').text("菜肴分类");
		}
		$("#isRecommend").val(isRecommend);
		$("#typeVal").val(sortType);
		$('#isSet').val(isSet);
		
		jQuery.ajax({
			url: "dishList",
			data: {sortType:sortType,dishCategory:dishCategory,tabNO:$('#tabNO').val(),keywords:keyWord,
				search_LIKE_dishesName:keyWord,search_LIKE_dishesCode:keyWord,page:currentPageNum,isRecommend:isRecommend,isSet:$('#isSet').val()},
			type: "POST",
			dataType: "html",
			beforeSend: function()
			{  
				
			},
			success: function(data)
			{
				if(currentPageNum == 1)
				{
					$("#dishList").html(data);
				}
				else
				{
					$("#dishList").append(data);
				}
				$("#dishList").listview("refresh");
				currentPageNum++;
				pageInit();
				
			}
		});
		
	};

	function nextPage(a)
	{
		var dishCategory = $('#dishCategoryVal').val();
		var sortType=$('#typeVal').val();
		var isRecommend = $("#isRecommend").val();
		var isSet=$("#isSet").val();
		loadDish(sortType,dishCategory,null,isRecommend,isSet);
	}
	
	function pageInit()
	{
		var btnMore = $("#btnMore");
		var totalPages = $("#totalPages").val();
		if(parseInt(currentPageNum) > parseInt(totalPages))
		{
			btnMore.hide();
		}
		else
		{
			btnMore.show();
		}
		$.mobile.loading( "hide" );
	}
	
	
	function goDishDetail(event,url)
	{
		event.stopPropagation();
		window.location=url;
	}
	
	function areYouSure(text1, text2, button, callback) {
		  $("#sure .sure-1").text(text1);
		  $("#sure .sure-2").text(text2);
		  $("#sure .sure-do").text(button).on("click.sure", function() {
		    callback();
		    $(this).off("click.sure");
		  });
		  $.mobile.changePage("#sure");
		}
// 	areYouSure("Are you sure?", "---description---", "Exit", function() {
// 		  // user has confirmed, do stuff
// 		});
	</script>
<script type="text/javascript">
	  function goTop()
	  {
	      $(window).scroll(function(e) {
	          //若滚动条离顶部大于100元素
	          if($(window).scrollTop()>100)
	              $("#gotop").fadeIn(1000);//以1秒的间隔渐显id=gotop的元素
	          else
	              $("#gotop").fadeOut(1000);//以1秒的间隔渐隐id=gotop的元素
	     });
	 };
	 $(function(){
	     //点击回到顶部的元素
	     $("#gotop").click(function(e) {
	             //以1秒的间隔返回顶部
	             $('body,html').animate({scrollTop:0},1000);
	     });
	  	 $("#gotop").mouseover(function(e) {
	         $(this).css("background","url(${ctx}/static/jquerymobile/images/backtop9.png) no-repeat 0px 0px");
	     });
	     $("#gotop").mouseout(function(e) {
	         $(this).css("background","url(${ctx}/static/jquerymobile/images/backtop9.png) no-repeat -70px 0px");
	     });
	     goTop();//实现回到顶部元素的渐显与渐隐
	     
	     $("#keyWordSearchButton").click( function () {
//	 			alert($("#keyWord").val());
				currentPageNum=1;
				loadDish(0,0,$("#keyWord").val(),'');
				$("#keyWord").val();
			});
 	 });
 </script>
</head> 
<body>
　<div data-role="page" style="background:#fff;">

<input type="hidden" name="tabNO" id="tabNO" value="${tabNO}"/>
	<input type="hidden" name="SaleNumVal" id="SaleNumVal" value="saleNum_DESC"/>
	<input type="hidden" name="typeVal" id="typeVal" value="0"/>
	<input type="hidden" name="dishCategoryVal" id="dishCategoryVal" value="0"/>
	<input type="hidden" name="isRecommend" id="isRecommend" value=""/>
	<input type="hidden" name="isSet" id="isSet" value="0"/>
	
	<div id="search_div" style="width:100%;height:32px;">
	  <div class="ui-input-search ui-shadow-inset ui-btn-corner-all ui-btn-shadow ui-icon-searchfield ui-body-c" style="width:70%;float:left;">
		<input type="search" data-role="none" name="keyWord" id="keyWord" placeholder="搜索菜肴" class="ui-input-text ui-body-c" />
	  </div> 
	  <div style="width:28%;float:right;">
	    <a id="keyWordSearchButton" data-role="button" data-transition="slide" style="width: 100%;height:10px;padding:8px 0px 15px 0px;"  href="" >搜索</a>
	  </div>
	</div>
					
					
	　<div data-role="header">
	<%
		if(com.ndlan.canyin.core.common.CoreConstant.currentPublicVersion==1){
	%>
				<span class="G2logo"></span>
				<%}%>
			
			<h1>${restName}</h1>
		    <a style="background:#CCCCC;" id="dishCategoryValA" name="dishCategoryValA" data-rel="panel"  class="ui-btn-right ui-corner-all ui-shadow ui-btn-inline ui-icon-bars ui-btn-icon-left ui-btn ui-btn-active" href="#popupNested" >菜肴分类</a>
	  </div>
	
	<div data-role="navbar" data-grid="c" data-iconpos="left">
	    <ul>
	        <li><a onclick="loadDishByType(0,this);" class="show-page-loading-msg ui-btn-active" style="font-size:12px;text-align:center;"  data-textonly="false" data-textvisible="true" data-msgtext="加载菜肴...">全部</a></li>
	        <li><a onclick="loadDishByType(1,this);" style="font-size:12px;text-align:center;" class="show-page-loading-msg" data-textonly="false" data-textvisible="true" data-msgtext="加载菜肴...">销量</a></li>
	        <li><a onclick="loadDishByType(2,this);" style="font-size:12px;text-align:center;" class="show-page-loading-msg" data-textonly="false" data-textvisible="true" data-msgtext="加载菜肴...">价格</a></li>
	        <li><a onclick="loadDishByType(3,this);" style="font-size:12px;text-align:center;" class="show-page-loading-msg" data-textonly="false" data-textvisible="true" data-msgtext="加载菜肴...">招牌菜</a></li>
	    </ul>
	</div><!-- /navbar -->

	<div role="main" class="ui-content">
	 <ul data-role="listview" data-inset="false" id="dishList">
	<!--   	 <li data-icon="false" id="wli" >
	    	<a href="#popupParis" data-rel="popup" data-position-to="window" data-transition="fade" onclick="addDishe(this);">
		        <img alt="Paris, France" class="popphoto" src="${ctx}/static/images/self/cy_cai.jpg" height="80px;">
		    	<h2>例子</h2>
		    	<p>0/份</p>
		    	<span class="ui-li-count" style="display: none;">0</span>
	    	</a>
	      	<a data-icon="minus"onclick="minusDish(this);" class="ui-state-disabled" href="#purchase" data-rel="popup" data-position-to="window" data-transition="pop"></a>
	   	
	   	 </li>
	   	
	   <c:forEach items="${dishes.content}" var="dishe" varStatus="status">
	   	<li data-icon="false" id="${dishe.dishesId}">
	    	<a href="#" onclick="addDishe(this);">
		        <img src="${ctx}/static/images/self/cy_cai.jpg" height="80px;">
		    	<h2>${dishe.dishesName}</h2>
		    	<p>${dishe.price}/份</p>
		    	<span class="ui-li-count" <c:if test="${dishe.saleNum == null || dishe.saleNum == 0}">style="display: none;"</c:if>>${dishe.saleNum}</span>
	    	</a>
	    	<a data-icon="minus"onclick="minusDish(this);" class="<c:if test="${dishe.saleNum == null || dishe.saleNum == 0}">ui-state-disabled</c:if><c:if test="${dishe.saleNum != null && dishe.saleNum > 0}">red</c:if>" href="#purchase" data-rel="popup" data-position-to="window" data-transition="pop"></a>
	   	 </li>
	   </c:forEach>
	    -->
	</ul>
	</div>
	  
	  <div id="moreDiv">
	 	 <button id="btnMore" style="background:#fff;" data-icon="refresh" onclick="nextPage(this);" class="show-page-loading-msg" data-textonly="false" data-textvisible="true" data-msgtext="加载菜肴..." >加载更多</button>
	  </div>
	  
	  
	<div data-role="panel"  id="popupNested" data-theme="none" data-position="right" data-display="overlay" style="padding: 0px">
	
		<c:forEach  items="${dishesCategorys}" var="firstDishesCategory" varStatus="status">
			<c:if test="${firstDishesCategory.categoryLevel eq 1}">
				<div data-role="collapsible" data-mini="true">
				    <h4 style="margin-top: -8px;"><li id="0" data-icon="false" data-rel="back" class="show-page-loading-msg dishesCategory_on" onclick="loadDishByCategory('${firstDishesCategory.categoryId}','${firstDishesCategory.categoryName }',this,0);"> ${firstDishesCategory.categoryName }</li></h4>
				    <ul data-role="listview" data-inset="false" id="dlistview" class="dlistview">
			    		<c:forEach  items="${dishesCategorys}" var="dishesCategory" varStatus="status">
			    			<c:if test="${dishesCategory.categoryLevel eq 2&& firstDishesCategory.categoryId eq dishesCategory.parentCategoryId}">
			    			 <li id="${dishesCategory.categoryId}" data-icon="false" class="ui-li-static ui-body-inherit show-page-loading-msg dishesCategory_off " data-rel="back" data-textonly="false" data-textvisible="true" data-msgtext="加载菜肴..." onclick="loadDishByCategory('${dishesCategory.categoryId}','${dishesCategory.categoryName }',this,0);" style="<c:if test="${fn:length(dishesCategory.categoryName) <= 4}">font-size:18px;</c:if><c:if test="${fn:length(dishesCategory.categoryName) > 4}">font-size:12px;</c:if>text-align:center;">
			    			 ${dishesCategory.categoryName }
			    			 </li>
			    			 </c:if>
			    		</c:forEach>
			        </ul>
				</div>
			</c:if>
			<c:if test="${firstDishesCategory.categoryLevel eq ''}">
				<div data-role="collapsible" data-mini="true">
				    <h4 style="margin-top: -8px;"><li id="0" data-icon="false" data-rel="back" class="show-page-loading-msg dishesCategory_on" onclick="loadDishByCategory('${firstDishesCategory.categoryId}',' ${firstDishesCategory.categoryName }',this,0);"> ${firstDishesCategory.categoryName }</li></h4>
				    <ul data-role="listview" data-inset="false" id="dlistview" class="dlistview">
			        </ul>
				</div>
			</c:if>
		</c:forEach>
		
		<c:forEach  items="${dishesSetCategorys}" var="firstDishesCategory" varStatus="status">
			<c:if test="${firstDishesCategory.categoryLevel eq 1}">
				<div data-role="collapsible" data-mini="true">
				    <h4 style="margin-top: -8px;"><li id="0" data-icon="false" data-rel="back" class="show-page-loading-msg dishesCategory_on" onclick="loadDishByCategory('${firstDishesCategory.dsCategoryId}',' ${firstDishesCategory.categoryName }',this,1);"> ${firstDishesCategory.categoryName }</li></h4>
				    <ul data-role="listview" data-inset="false" id="dlistview" class="dlistview">
			    		<c:forEach  items="${dishesSetCategorys}" var="dishesCategory" varStatus="status">
			    			<c:if test="${dishesCategory.categoryLevel eq 2&& firstDishesCategory.dsCategoryId eq dishesCategory.parentCategoryId}">
			    			 <li id="${dishesCategory.dsCategoryId}" data-icon="false" class="ui-li-static ui-body-inherit show-page-loading-msg dishesCategory_off " data-rel="back" data-textonly="false" data-textvisible="true" data-msgtext="加载菜肴..." onclick="loadDishByCategory('${dishesCategory.dsCategoryId}','${dishesCategory.categoryName }',this,1);" style="<c:if test="${fn:length(dishesCategory.categoryName) <= 4}">font-size:18px;</c:if><c:if test="${fn:length(dishesCategory.categoryName) > 4}">font-size:12px;</c:if>text-align:center;">
			    			 ${dishesCategory.categoryName }
			    			 </li>
			    			 </c:if>
			    		</c:forEach>
			        </ul>
				</div>
			</c:if>
			<c:if test="${firstDishesCategory.categoryLevel eq ''}">
				<div data-role="collapsible" data-mini="true">
				     <h4 style="margin-top: -8px;"><li id="0" data-icon="false" data-rel="back" class="show-page-loading-msg dishesCategory_on" onclick="loadDishByCategory('${firstDishesCategory.dsCategoryId}',' ${firstDishesCategory.categoryName }',this,1);"> ${firstDishesCategory.categoryName }</li></h4>
				    <ul data-role="listview" data-inset="false" id="dlistview" class="dlistview">
			        </ul>
				</div>
			</c:if>
		</c:forEach>
		
	</div>
	
	
	

	<!-- /popup -->

	<a id="service" class="but_control" data-transition="slideup"  data-rel="panel"  href="#popupService"></a>

	<div data-role="panel" id="popupService" data-theme="none" style="float: left;background-color: #363636;width: 84px;"  data-position="left" data-display="overlay">
	    		<div class="main_nav_wrap">
					<div class="main_nav" id="leftSlider_">
						<a href="#" class="call" title="call"></a>
						<a href="#" class="tjcj" title="tjcj"></a>
						<a href="#" class="canju" title="canju"></a>
						<a href="#" class="jiezhang" title="jiezhang"></a>
						<a class="qt" title="qt" href="#popupBeizhu" data-rel="popup" data-transition="slidedown" data-position-to="window" ></a>
						
						<a  id="handMsg"  href="#popupBillMsg" data-rel="popup" data-transition="slidedown" data-position-to="window" ></a>
					</div>
					<a id="servicea" class="but_controla" data-transition="slideup"  data-rel="panel"  href="#popupService"></a>
				</div>
	</div><!-- /popup -->
	
	<div data-role="popup" id="popupBeizhu" data-theme="a" style="float: left;" data-overlay-theme="a">
       	 	<div style="padding:10px 20px;">
	            <h3>想说的话:</h3>
		   	 	<textarea id="otherMsgText"  name="cookRemarkText" placeholder="想和服务员说什么..."></textarea>
	            <button type="button"  id="otherMsgSave" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check">发送</button>
		</div>
	</div>
	
	<div data-role="dialog" id="sure" data-title="Are you sure?">
	  <div data-role="content">
	    <h3 class="sure-1">???</h3>
	    <p class="sure-2">???</p>
	    <a href="#" class="sure-do" data-role="button" data-theme="b" data-rel="back">Yes</a>
	    <a href="#" data-role="button" data-theme="c" data-rel="back">No</a>
	  </div>
	</div>
	
	<div data-role="popup" id="popupBillMsg"  data-inset="false"  data-theme="a" style="float: left;">
       	 	<div style="padding:10px 20px;">
	            <h3 id="messageContainer">您的自助点餐服务已经被处理</h3>
	            <button type="button" onclick="window.location='${ctx}/self/showOrderedDishList?tabNO=${tabNO}'"  id="viewMsg" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check">去查看</button>
			</div>
	</div>
	
	<div data-role="popup" id="popupShop" data-theme="none" style="float: left;">
       	 	<div id="goOrderAAAAA" onclick="window.location='${ctx}/self/showOrderedDishList?tabNO=${tabNO}'"  class="shopping_car"><span><a rel="external" data-ajax="false" id="shopping_car"  href="#" class="show-page-loading-msg" data-textonly="false" data-textvisible="true" data-msgtext="查看您点的菜">0份</a></span></div>
	</div>
	<div id="gotop"></div>

	　<!-- /content -->
	</div>
</body>
</html>