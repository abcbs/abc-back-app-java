var $dialog;	
var dialogPage = -1;
var dialogPageContentArray = new Array();
var closebox=function(){
	dialogPage = -1;
	$dialog.empty();
	$dialog.dialog( "close" );
	if("undefined" == typeof CanYinHotKeysGetLastHotKey)
	{
		
	}
	else
	{
		if(CanYinHotKeysGetLastHotKey)
		{
			CanYinHotKeysGetLastHotKey();
		}
	}
}
/**
 * blind，bounce，clip，drop，explode，fade，fold，
 * highlight，pulsate，puff，scale，size，shake，slide，transfer
 * @param title
 * @param content
 * @param width
 * @param height
 * @param popId
 */
function dialogBox(title,content,width,height,popId){
	
	     var dHeight = height;
		 if(!$dialog){
			
			 var temp_float=new String ;
			 temp_float="<div class=\"popup_wrap\" id=\""+popId+"\">";
			 temp_float+="</div>";
			
			 $dialog = $(temp_float).dialog({
				 modal:true,
				 autoOpen: false,
				 hideTitle:true,
				 zIndex:1000,
				 resizable:false,
				 draggable:true,
				 widgetClass:"ui-widget-content-no",
				 overlay: {
			        backgroundColor: 'black',
			        opacity: 0.5
			     },
//				 show: {
//					 effect: "drop",
//					 duration: 300
//				 },
				 close:function(){
				 },
				 width:width,
				 height:dHeight
			});
		 }
		 
	var dialogOpts = { width:width,height:dHeight};
	$dialog.dialog( dialogOpts );
	if(dialogPage == -1)
	{
	  dialogPageContentArray = new Array();
	  dialogPage=0;
	}
	else
	{
		dialogPage++;
	}
		 
   //初始化title部分
	var pageDialog = popAddPage(title,dialogPage,width); 
	pageDialog.hide();
	
	var content_array=new Array;
	var contentType=content.substring(0,content.indexOf(":"));
	content=content.substring(content.indexOf(":")+1,content.length);
	switch(contentType){
	  case "url":
	  content_array=content.split("?");
	  $.ajax({
	    type:content_array[0],
	    url:content_array[1],
	    data:content_array[2],
	    cache:false,
	    async:true,
	    success:function(html){
	    	//session过期时候，如果是登录页面，则直接调整到登录页
	    	//TODO 登录页面判断不严谨
	    	if(html.indexOf("/login") > 0)
    		{
    			window.location = window.ctx+"/login?relogin=1";
    		}
	    	else
	    	{
	    		pageDialog.append(html);
	    		pageDialog.fadeIn(300);
	    	}
	    },
		error:function(){
			pageDialog.find(".popup-foot").before("error...");
		}
	  });
	  break;
	  case "text":
		  pageDialog.find(".popup-foot").before("<p>"+content+"</p><div><button onclick=\"closebox();\">关闭</button></div>");
	  break;
	  case "id":
		  pageDialog.find(".popup-foot").before($("#"+content+"").html());
	  break;
	  case "iframe":
		  pageDialog.find(".popup-foot").before("<iframe src=\""+content+"\" width=\"100%\" height=\""+(parseInt(height)-30)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
	}
	$dialog.dialog( "open" );
}
function popAddPage(title,_dialogPage,height)
{
	if(!$dialog.find("#popChangeContent"+_dialogPage).attr("id"))
	{
		var temp_float="<div id=\"popChangeContent"+_dialogPage+"\" class=\"popup_wrap_in\">";
		 
		 temp_float+="</div>";
		 $dialog.append(temp_float);
		 var pageDialog = $dialog.find("#popChangeContent"+_dialogPage);
		 
		 
	}
	
	var dwidth = $dialog.find("#popChangeContent"+_dialogPage).width();
	
	if(dialogPage == 0)
	{
		$dialog.find("#popChangeContent"+_dialogPage).css({"left":dwidth+"px"}).show();
	}
	else if(dialogPage > 0)
	{
		var d = 500;
		$dialog.find("#popChangeContent"+(_dialogPage-1)).animate({"left": "-"+dwidth+"px"}, d);
		$dialog.find("#popChangeContent"+_dialogPage).css({"left": dwidth+"px"}).show().animate({"left": "0px"}, d);
	}
	
	return pageDialog;
}

var popRefresh = function(content,callBackFunction)
{
	var pageDialog=$dialog.find("#popChangeContent"+dialogPage);
	
	var content_array=new Array;
	var contentType=content.substring(0,content.indexOf(":"));
	content=content.substring(content.indexOf(":")+1,content.length);
	switch(contentType){
	  case "url":
	  content_array=content.split("?");
	  $.ajax({
	    type:content_array[0],
	    url:content_array[1],
	    data:content_array[2],
	    cache:false,
	    success:function(html){
	    	pageDialog.empty();
	    	pageDialog.append(html);
	    	if(typeof callBackFunction == 'function')
	    	{
	    		callBackFunction.call();
	    	}
	    },
		error:function(){
			pageDialog.find(".popup-foot").before("error...");
		}
	  });
	  break;
	  case "text":
		  pageDialog.find(".popup-foot").before("<p>"+content+"</p><div><button onclick=\"closebox();\">关闭</button></div>");
	  break;
	  case "id":
		  pageDialog.find(".popup-foot").before($("#"+content+"").html());
	  break;
	  case "iframe":
		  pageDialog.find(".popup-foot").before("<iframe src=\""+content+"\" width=\"100%\" height=\""+(parseInt(height)-30)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
	}
}
var popSelectCallBack = function(params)
{
	var form = $dialog.find("#popChangeContent"+(dialogPage-1)).find("form");
	for(var e in params)
	{
		form.find("input[id='"+e+"']").val(params[e]);
	}
	for(var e in params)
	{
		form.find("a[id='"+e+"']").html(params[e]);
	}
	popBack(dialogPage);
	
	if("undefined" == typeof CanYinHotKeysGetLastHotKeys)
	{
		
	}
	else
	{
		if(CanYinHotKeysGetLastHotKeys)
		{
			CanYinHotKeysGetLastHotKeys(1);
		}
	}
}

var popBack = function(_dialogPage)
{
	var d = 500;
	var width = $dialog.find("#popChangeContent"+_dialogPage).width();
	$dialog.find("#popChangeContent"+_dialogPage).animate({"left": width+"px"}, d,function(){
		$(this).remove();
	});
	$dialog.find("#popChangeContent"+(_dialogPage-1)).animate({"left": "0px"}, d);
	
	dialogPage = _dialogPage  -1;
	if("undefined" == typeof CanYinHotKeysGetLastHotKeys)
	{
		
	}
	else
	{
		if(CanYinHotKeysGetLastHotKeys)
		{
			CanYinHotKeysGetLastHotKeys(1);
		}
	}
}

var $dialogConfirm;
var closeboxConfirm=function(){
	$dialogConfirm.dialog( "close" );
	if("undefined" == typeof CanYinHotKeysGetLastHotKey)
	{
		
	}
	else
	{
		if(CanYinHotKeysGetLastHotKey)
		{
			CanYinHotKeysGetLastHotKey();
		}
	}
}

function dialogBoxConfirm(title,content,callBackAction,cancleAction){
		 if(!$dialogConfirm){
			 var temp_float=new String ;
			 temp_float="<div id=\"dialog-confirm\" class=\"small_popup_wrap\">";
			 
			 temp_float+="<div class=\"small_popup_header\">";
			 temp_float+="<div class=\"small_popup_header_l\"></div>";
			 temp_float+="<div class=\"small_popup_header_c\">";
			 temp_float+="<h3 id=\"smallTitle\"></h3>";
			 temp_float+="</div>";
			 temp_float+="<div class=\"small_popup_header_r\"></div>";
			 temp_float+="</div>";
			 
			 temp_float+="<div class=\"small_popup_body\">";
			 temp_float+="<div id=\"smallContent\" class=\"small_popup_cont\">";
			
			 temp_float+="<div class=\"but-area-s\">";
			 temp_float+="<a id=\"smallOk\" style=\"cursor:pointer;\" class=\"small-but-queding mr_28\"></a>";
			 temp_float+="<a id=\"smallCancle\" class=\"small-but-quxiao\" style=\"cursor:pointer;\"></a>";
			 temp_float+="</div>";
			 temp_float+="</div>";
			 temp_float+="</div>";
			 temp_float+="</div>";
			 
			 $dialogConfirm =  $(temp_float).dialog({
				 modal:true,
				 autoOpen: false,
				 hideTitle:true,
				 zIndex:1000,
				 resizable:false,
				 draggable:true,
				 widgetClass:"ui-widget-content-no",
				 overlay: {
			        backgroundColor: 'black',
			        opacity: 0.5
			     },
				 close:function(){
					 //closeboxConfirm();
				 },
				 width:529,
				 height:335
			});
		 }
		 
		 $dialogConfirm.find("#smallTitle").text(title);
		 
		 //撤单的
		 var chedant ="<p  id=\"dcontent\" >"+content+"</p>";
		$dialogConfirm.find("#dcontent").remove();
		$dialogConfirm.find(".but-area-s").before(chedant); 
		
		$dialogConfirm.find("#smallOk").unbind("click");
		 $dialogConfirm.find("#smallOk").click(function(){
			 closeboxConfirm();
			 callBackAction.call();
		 });
		 
		 $dialogConfirm.find("#smallCancle").unbind("click");
		 $dialogConfirm.find("#smallCancle").click(function(){
			 closeboxConfirm();
			 if("undefined" == typeof cancleAction)
				{
				}
				else
				{
					if(cancleAction)
					{
						cancleAction.call();
					}
				}
			 
		 });
		 $dialogConfirm.dialog( "open" );
		 
		 var hotKey = new CanYinHotKeys();
			hotKey.init($("body"),[{
				keyCode:"enter",
				callBackFunction:function()
				{
					$("#smallOk").click();
				}
			},
			{
				keyCode:"esc",
				callBackFunction:function()
				{
					closeboxConfirm();
				}
			}
			],CanYinHotKeysArray.length);
			
}


var $dialogAlert;
var closeboxAlert=function(){
	$dialogAlert.dialog( "close" );
}

function dialogBoxAlert(title,content){
		 if(!$dialogAlert){
			 var temp_float=new String ;
			 temp_float="<div id=\"dialog-confirm\" class=\"pop-dialog\" title=\""+title+"\">";
			 temp_float+="<p id=\"dialog-content\">"+content+"</p>";
			 temp_float+="</div>";
			 
			 $dialogAlert = $(temp_float).dialog({
				 resizable: false,
				 height:150,
				 modal: true,
				 hide: {
					 effect: "explode",
					 duration: 500
				 }
			});
		 }
		 $dialogAlert.find("#dialog-content").html(content);
		 $dialogAlert.dialog( "open" );
}
function ieValue(){
	var ieValue ;
		if(window.XMLHttpRequest){
			ieValue="Mozilla,Safari,IE7" ;
 		if(!window.ActiveXObject){
  			ieValue = "Mozilla, Safari";
 		}else{
  			ieValue = "IE7";
 		}
	}else{
 		ieValue = "IE6";
	}
	return ieValue ;
}


var $dialogHtml;
function dialogHtmlBox(htmlId,width,height){
	
		 if(!$dialogHtml){
			 $dialogHtml = $("#"+htmlId).dialog({
				 modal:true,
				 autoOpen: false,
				 hideTitle:true,
				 zIndex:1000,
				 resizable:false,
				 draggable:true,
				 widgetClass:"ui-widget-content-no",
				 overlay: {
			        backgroundColor: 'black',
			        opacity: 0.5
			     },
				 close:function(){
				 },
				 width:width,
				 height:height,
				 position: [417,300]
			});
		 }
	$dialogHtml.dialog( "open" );
}

var closeboxHtml=function(){
	$dialogHtml.dialog( "close" );
}