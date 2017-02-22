<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>诺德兰电子点餐系统</title>
<link href="${ctx}/static/css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var current_type = "";
var current_status = "";

$(document).ready(function(){
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			closebox();
		}
	}
	],1);

	MsgChange('all','0',1);
});


function MsgChange(type,status,pageNum)
{
	current_type = type;
	current_status = status;
	var parm = "type="+type+"&status="+status+"&page="+pageNum;
	ProgressbarUtil.show("ajaxMsgContent",670);
	ajaxUtil.getUrlContent(window.ctx+'/self/message/ajax/pop/msgHandleContent?'+parm,"ajaxMsgContent",function(){
	});
}

function refreshPage()
{
	MsgChange(current_type,current_status,1);
}

function refreshDisplayPage()
{
	var current = $("#currentPage").val();
	MsgChange(current_type,current_status,current);
}

function msgGo(type,status,pageNum)
{
	MsgChange(type,status,pageNum);
}



function showNextDishesDetail(a)
{
	$(a).parent(".xiaoxi_list").next(".xx_details_wrap").toggle();
}

function msgHandle(id)
{
	var url = $("#popSaveForm").attr("action");
	if(id)
	{
		jQuery.ajax({
			url: url+"/"+id,
			data: $('#popSaveForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				popCloseBox();
				if(data.statusCode == '200')
				{
					toastr.success(data.message);
					window.location=window.ctx+'/bill/diancai?billId='+data.sign;
				}
				else
				{
					toastr.error(data.message);
				}
			}
		});
	}
}


function deleteMsg(id)
{
	if(id)
	{
		jQuery.ajax({
			url: window.ctx+"/self/message/pop/msgHandle/deleteMsg/"+id,
			data: $('#popSaveForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				if(data.statusCode == '200')
				{
					refreshPage();
				}
				else
				{
					toastr.error(data.message);
				}
			}
		});
	}
}

function reStartService(id)
{
	if(id)
	{
		jQuery.ajax({
			url: window.ctx+"/self/message/pop/msgHandle/reStartService/"+id,
			data: $('#popSaveForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				if(data.statusCode == '200')
				{
					refreshPage();
				}
				else
				{
					toastr.error(data.message);
				}
			}
		});
	}
}


function popCloseBox()
{
	closebox();
}

function messageCenterCancel(messageId) {
	jQuery.ajax({
		url: window.ctx + "/self/message/pop/msgHandle/displayCall",
		data:{
			messageId : messageId,
			handleType : '3'
		},
		type: "POST",
		dataType: "JSON",
		success: function(data){
			var status = data.statusCode;
			if(status == '200')
			{
				refreshDisplayPage();
			}
		},
		error:function(){
			
		}
	});
}

</script>
</head>

<body>
					
<form id="popSaveForm" action="${ctx}/self/message/pop/msgHandle/update" method="post" >

<div class="popup_wrap">
		<div class="popup_header">
			<div class="popup_header_l"></div>
			<div class="popup_header_c">
				<h3>消息处理</h3>
			</div>
			<div class="popup_header_r"></div>
		</div>
		<div class="popup_body">
			<div class="kamingxi_wrap">
			
			
			
				<div class="xiaoxi_cl_wrap" id="ajaxMsgContent">
					
				</div>
				
				<div class="line_w"></div>
				<div class="but-area-f" style="left:430px;">
					<a class="but-quxiao" href="javascript:closebox();"></a>
				</div>
			</div>
		</div>
	</div>

	
</form>
</body>
</html>