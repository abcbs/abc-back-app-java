<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="remoteAddr" value="${pageContext.request.remoteAddr}" />
<c:set var="remotePort" value="${pageContext.request.serverPort}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>菜肴图片</title>
	<meta name="author" content="Code Computerlove - http://www.codecomputerlove.com/" />
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<link href="${ctx}/static/photoSwipe/styles.css" type="text/css" rel="stylesheet" />
	
	<link href="${ctx}/static/photoSwipe/photoswipe.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript" src="${ctx}/static/photoSwipe/simple-inheritance.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/photoSwipe/code-photoswipe-1.0.11.min.js"></script>
	
	
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
					
		
	</script>
	
</head>
<body>


<div class="caiyao_title_wrap">
	<a class="back" href="${ctx}/self/main?tabNO=${tabNO}"></a>
	<h3 class="caiyao_title">菜肴图片</h3>
</div>



<div id="MainContent">
	<div id="Gallery">
		<div class="gallery-row">
			<c:forEach items="${pics}" var="pic" varStatus="status">
				<div class="gallery-item"><a href="http://${serverIp}:${remotePort}/canyin-main/${pic.picUrl1024x1024}"><img src="http://${serverIp}:${remotePort}/canyin-main/${pic.picUrl1024x1024}" alt="菜肴图片" /></a></div>
			</c:forEach>
		</div>
	</div>
	
	
</div>	

	


</body>
</html>