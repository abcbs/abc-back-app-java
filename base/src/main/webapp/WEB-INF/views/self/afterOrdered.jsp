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
<script src="${ctx}/static/jquerymobile/jquery.floatDiv.js"></script>
<script src="${ctx}/static/js/self/common.js"></script>
<script type="text/javascript">
	// $("a.but_control").pageslide({ direction: "left", modal: true });
	$(document).bind("pageinit", function() {
		
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
	function refreshMessage() {
		$.post("refreshMessage", {tabNO:$('#tabNO').val()}, function(data) {
			$('#messageContainer').html(data.message);
		});
		console.log("ids:"+new Date());
	};
	$(document).bind("pageinit", function() {
		$.extend($.mobile, {
			self : {},
			callCenter : function() {
				
			}
		});
		
		var refreshId=setInterval("refreshMessage()", 10000);  
		//window.clearInterval(refreshId);
		
	});		
	
	
	</script>
</head> 
<body>
<input type="hidden" name="tabNO" id="tabNO" value="${tabNO}"/>
	
　<div data-role="page" style="background:#fff;">
	　<div data-role="header" style="overflow:hidden;">
			<h1 style="color: #388000;width: 200px;" >已呼叫菜单 桌号：${tabNO}</h1>
	  </div>
		<div data-role="header" style="overflow:hidden;background-color: white;height:auto;">
			<h1 style="margin-left:5px;width: 100%;color:#ff0000;margin-top: 30px;text-align: left;" id="messageContainer">此订单已呼叫服务员响应，请耐心等待服务员进行确认！</h1>
	  </div>

	<div role="main" class="ui-content">
	 <ul data-role="listview" data-inset="false" id="dishList">
	 	<li data-icon="false" id="${dishe.dishesId}">
	    	<a href="#" style="background:#76ca40;color:#fff;font-size:13px;">
		    	<p>&nbsp;</p>
		    	<span class="ui-li-name" style="font-weight: bolder;left:10px;">名称</span>
		    	<span class="ui-li-price" style="font-weight: bolder;right:8.6em;">单价</span>
		    	<span class="ui-li-totalPrice" style="font-weight: bolder;right:5em;">数量</span>
		    	<span class="ui-li-fenshu" style="font-weight: bolder;right:1em;">总价</span>
	    	</a>
	   	 </li>
	   	 
	  <c:forEach items="${dishList}" var="dishe" varStatus="status">
	  	<c:set value="${sum+dishe.realCost*dishe.saleNum}" var="sum" />  
	   	<li data-icon="false" id="${dishe.dishesId}">
	    	<a href="#"style="background:#fff;" >
		    	<p>&nbsp;</p>
		    	<span class="ui-li-name" style="left:10px;">${dishe.dishesName}</span>
		    	<span class="ui-li-price"><fmt:formatNumber value="${dishe.realCost}" type="currency" pattern="#.##"/></span>
		    	<span class="ui-li-totalPrice">${dishe.saleNum}</span>
		    	<span class="ui-li-fenshu"><fmt:formatNumber value="${dishe.realCost*dishe.saleNum}" type="currency" pattern="#.##"/></span>
	    	</a>
	   	 </li>
	   </c:forEach>
	</ul>
	</div>
	
		<div style="overflow:hidden;font-size:11px;<c:if test="${cookRemark == null || cookRemark == ''}">display:none;</c:if>">
				<h2 id="remarkShow">
					备注：${cookRemark}
				</h2>
		</div>
	
	<div data-theme="none"  style="float: left;background-color: white;border: none;"  data-position="fixed" data-role="footer">
		<h1 style="background:#cccccc;margin-left:0px;width: 100%; font-size:12px;color:#ff6600;"  id="totalPrice">菜品消费：<fmt:formatNumber value="${sum == null ? 0 : sum}" type="currency" pattern="#.##"/>元</h1>
		

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


<!-- 		<script type="text/javascript"> -->
<!-- 		var bdText=${restName}+"  ";</script> -->
<!-- 		<div class="bdsharebuttonbox" data="{'url':'http://yourname.domain.com/shareA.html'}"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_tqf" data-cmd="tqf" title="分享到腾讯朋友"></a><a href="#" class="bds_ibaidu" data-cmd="ibaidu" title="分享到百度个人中心"></a></div> -->
<!-- 		<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":bdText,"bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["weixin","qzone","tsina","tqq","renren","tqf","ibaidu"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["weixin","qzone","tsina","tqq","renren","tqf","ibaidu"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script> -->
		
		<a href="#" data-textonly="false" data-textvisible="true" data-msgtext="去点菜啦" onclick="window.location='${ctx}/self/main?tabNO=${tabNO}'" class="ui-btn ui-btn-inline show-page-loading-msg" style="text-align:center ;text-shadow: none;font-family:'Microsoft YaHei UI';text-decoration:none;background:#ff6600;font-size:13px;float:left;color:#fff;">继续点菜</a>
	</div>	
		
	<div data-role="popup" id="popupBeizhu" data-theme="a" style="float: left;" data-overlay-theme="a">
       	<div style="padding:10px 20px;">
	            <h3>烹饪备注:</h3>
		   	 	<textarea id="cookRemarkText"  name="cookRemarkText" placeholder="请填写烹饪备注">${cookRemark}</textarea>
	            <button type="button"  id="remarkSave" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check">保存</button>
		</div>
	</div>




</body>
</html>