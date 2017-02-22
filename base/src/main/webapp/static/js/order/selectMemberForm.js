$(document).ready(function(){
	changeMemberContent();
	$("#selectMember").bind("click",selectMember);
//	$("#memberSearch").bind("change",changeMemberContent);
	$("#memberSearch").sudoku();
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			selectMember();
		}
	},
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			popBack();
		}
	}
	,
	{
		keyCode:"up",
		callBackFunction:function(event)
		{
			upKeySelect();
		}
	}
	,
	{
		keyCode:"down",
		callBackFunction:function(event)
		{
			downKeySelect();
		}
	}
	],2);
	
});

var popMemberSelectIndex = -1;

function downKeySelect()
{
	var total = $("#pop_memberContentList").children("li").length;
	popMemberSelectIndex++;
	if(popMemberSelectIndex == total-1)
	{
		popMemberSelectIndex = 0;
	}
	$("#pop_memberContentList").children("li").eq(popMemberSelectIndex).children("a").click();
	
}

function upKeySelect()
{
	var total = $("#pop_memberContentList").children("li").length;
	popMemberSelectIndex--;
	if(popMemberSelectIndex == -1)
	{
		popMemberSelectIndex = total-1;
	}
	$("#pop_memberContentList").children("li").eq(popMemberSelectIndex).children("a").click();
}

function setMember(mbId,name,mobile,html){
	selectRow(html);
	$("#selectMbId").val(mbId);
	$("#selectMemberName").text(name);
	$("#selectTelphone").val(mobile);
}

function selectMember(){
	var mbId = $("#selectMbId").val();
	var memberName = $("#selectMemberName").text();
	var telphone = $("#selectTelphone").val();
	var rj = {"mbId":mbId,"orderPeopleName":memberName,"telphone":telphone};
	popSelectCallBack(rj);
}

var popLastDl = null;
function selectRow(html){
	var a = $(html);
	if(popLastDl){
		popLastDl.removeClass("hykjz_infor_on");
	}
	a.addClass("hykjz_infor_on");
	
	popLastDl = a;
}

function changeMemberContent()
{
	var kewWords = $("#memberSearch").val();
	ProgressbarUtil.show("ajaxContent2",594);
	 $.ajax({
		    type:"get",
		    url:window.ctx+'/order/ajax/member/list?kewWords='+kewWords,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(html){
		    	$("#ajaxContent2").html(html);
		    },
			error:function(){
			}
	});
}

