$(document).ready(function(){
	initFrameSize();
	dishCatagoryChange('',currentSearchParams,1,1);
	
	initButton();
	
	guashi();
	tingyong();
	tuika();
	chongzhi();
	kuaijiejian();
});
var hotKey = null;
function kuaijiejian()
{
	hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"1",
		callBackFunction:function()
		{
			$("#jianka").click();
		}
	}
	,
	{
		keyCode:"2",
		callBackFunction:function()
		{
			$("#guashi").click();
		}
	}
	,
	{
		keyCode:"3",
		callBackFunction:function()
		{
			$("#tingyong").click();
		}
	}
	,
	{
		keyCode:"4",
		callBackFunction:function()
		{
			$("#tuika").click();
		}
	}
	,
	{
		keyCode:"5",
		callBackFunction:function()
		{
			$("#chongzhi").click();
		}
	}
	,
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/order';
		}
	}
	,
	{
		keyCode:"right",
		callBackFunction:function()
		{
			$(".but_qx_right").click();
		}
	}
	,
	{
		keyCode:"left",
		callBackFunction:function()
		{
			$(".but_qx_left").click();
		}
	}
	,
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			//搜索框得到焦点
			$("#kewWords").focus();
			setFocus(document.getElementById("kewWords"));
		}
	}
	],0);
	
}

function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body_huiyuan").hide().css("width",998+subWidth+"px").show();
		$(".list_h").css("width",807+subWidth+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_huiyuan").css("height",670+subHeight+"px");
		$(".list_h").css("height",664+subHeight+"px");
		$(".list_y").css("height",664+subHeight+"px");
		$(".hy_table_wrap").css("height",547+subHeight+"px");
	}
}

/**
 * 自适应浏览器宽度
 */
function initWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".hy_nav").css("width",806+subWidth+"px");
		$(".hy_table_wrap").css("width",807+subWidth+"px");
		$(".huiyuan_table").css("width",807+subWidth+"px");
		$(".hy_sousuo").css("width",795+subWidth+"px");
		$("tr",".huiyuan_table").css("width",807+subWidth+"px");
	}
	
	//分区个数
	var mwidth = $(".hy_nav").width();
	var areaNum = parseInt(mwidth/115);
	if($("#memberListUl").find("li").length <= areaNum)
	{
		$("#dish_left").hide();
		$("#dish_right").hide();
	}
	$(".hy_nav").find("ul").css("width",($("#memberListUl").find("li").length+1)*115+"px");
	$("#memberListUl").css({marginLeft:memberListUlMarginLeft+"px"});
}
var memberListUlMarginLeft = 0;



function refreshPage()
{
	dishCatagoryChange(currentKewWords,currentSearchParams,currentPage,currentCardStatus);
}
/**
 * 点击餐台区域
 */
var currentSearchParams = '';
var currentPage = 1;
var currentCardStatus = '';
var currentKewWords = '';
var currentRow = null;
function dishCatagoryChange(kewWords,searchParams,page,cardStatus)
{
	ProgressbarUtil.show("ajaxContent",594);
	
	currentSearchParams = searchParams;
	currentPage = page;
	currentKewWords = kewWords;
	currentRow = null;
	var ic = '';
	if("undefined" == typeof cardStatus)
	{
	}
	else
	{
		ic = cardStatus;
	}
	currentCardStatus= ic;
	ajaxUtil.getUrlContent(getScreenSizeUrl('/member/ajax/member/list?page='+currentPage+'&cardStatus='+ic+'&kewWords='+kewWords+'&'+searchParams,'member'),"ajaxContent",function(){
		initTableEvent();
    	initWidth();
	});
}


function showSub(event,tdDom)
{
	event=event?event:window.event;
    event.cancelBubble=true;
	
	var a = $(tdDom);
	
	var tr = a.parent("td").parent("tr");
	var id = tr.attr("id");
	var text = a.text();
	var mcId = tr.attr("mcId");
	
//	alert(id + " ： " + text+ " ： " + mcId);
	if(text == '+')
	{
		tr.parent().find("tr[id='"+id+"'][mcId!='"+mcId+"']").show();
		a.text("-");
	}
	else
	{
		tr.parent().find("tr[id='"+id+"'][mcId!='"+mcId+"']").hide();
		a.text("+");
	}
}
function initTableEvent()
{
//	$("#showSub").unbind("click");
//	$("#showSub").click(function(event){
//		event.stopPropagation();
//		var tr = $(this).parent("td").parent("tr");
//		var id = tr.attr("id");
//		var text = $(this).text();
//		var mcId = tr.attr("mcId");
//		
//		alert(id + " ： " + text+ " ： " + mcId);
//		if(text == '+')
//		{
//			tr.parent().find("tr[id='"+id+"'][mcId!='"+mcId+"']").show();
//			$(this).text("-");
//		}
//		else
//		{
//			tr.parent().find("tr[id='"+id+"'][mcId!='"+mcId+"']").hide();
//			$(this).text("+");
//		}
//	});
	
//	var tbody = $("tbody","#memberTable");
//	tbody.children("tr").click(function(){
//		var tr = $(this);
//		if(tr.hasClass("on_hy_tr"))
//		{
//			tr.removeClass("on_hy_tr");
//			setButtonStatus(false,tr);
//			currentRow = null;
//		}
//		else
//		{
//			tbody.children("tr").removeClass("on_hy_tr");
//			tr.addClass("on_hy_tr");
//			setButtonStatus(true,tr);
//			currentRow = tr;
//		}
//	});
	
	$(".body_huiyuan").unbind("keydown");
	$(".body_huiyuan").keydown(function(evt) {
		if(evt.keyCode == 9)
		{
			var lis = $("#memberListUl").children("li");
			var ci = tabKeyAreaIndex;
			if(ci >= lis.length-1)
			{
				ci = 0;
			}
			else
			{
				ci=tabKeyAreaIndex+1;
			}
			tabKeyAreaIndex=ci;
			lis.eq(tabKeyAreaIndex).children("a").click();
		}
	});
	hotKey.exInputFocus();
	


	CanYinHotKeysGetLastHotKeys(0);
	$("body").find("input").focusin(function(){
		$("body").unbind("keydown");
	}).focusout(function(){
		CanYinHotKeysGetLastHotKeys(0);
	});
	
	$(".body_huiyuan").click(function(event){
		event.stopPropagation();
		removeAllOnClass();
		buttonInit();
		currentRow = null;
	});
	
	//状态选中
	$(".hy_gongneng_2").children("a").removeClass("hy_on");
	if(!currentCardStatus)
	{
		$(".hy_gongneng_2").children("a").eq(0).addClass("hy_on");
		$("#memberListShow").text("会员卡状态－全部");
	}
	else if(currentCardStatus == 1)
	{
		$(".hy_gongneng_2").children("a").eq(1).addClass("hy_on");
		$("#memberListShow").text("会员卡状态－正常");
	}
	else if(currentCardStatus == 2)
	{
		$(".hy_gongneng_2").children("a").eq(2).addClass("hy_on");
		$("#memberListShow").text("会员卡状态－停用");
	}
	else if(currentCardStatus == 3)
	{
		$(".hy_gongneng_2").children("a").eq(3).addClass("hy_on");
		$("#memberListShow").text("会员卡状态－挂失");
	}
	else if(currentCardStatus == 5)
	{
		$(".hy_gongneng_2").children("a").eq(4).addClass("hy_on");
		$("#memberListShow").text("会员卡状态－退卡");
	}
	
	$("#dish_left").click(function(){
		var left = $("#memberListUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(left < 0)
		{
			left+= 500;
			memberListUlMarginLeft = left;
			$("#memberListUl").animate({marginLeft:left+"px"},100);
		}
	});
	
	$("#dish_right").click(function(){
		var mainTabWidth = $(".hy_nav").width();
		var mainCatagoryTabWidth = $("#memberListUl").width();
		var left = $("#memberListUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(mainCatagoryTabWidth + left < mainTabWidth)
		{
			return;
		}
		
		left-= 500;
		memberListUlMarginLeft = left;
		$("#memberListUl").animate({marginLeft:left+"px"},100);
	});
	
}


function rowClick(event,divDom)
{
	event=event?event:window.event;
    event.cancelBubble=true;
	var tr = $(divDom);
    // click 事件的处理
	if(tr.hasClass("on_hy_tr"))
	{
		tr.removeClass("on_hy_tr");
		setButtonStatus(false,tr);
		currentRow = null;
	}
	else
	{
		var tbody = $("tbody","#memberTable");
		tbody.children("tr").removeClass("on_hy_tr");
		tr.addClass("on_hy_tr");
		setButtonStatus(true,tr);
		currentRow = tr;
	}
	
}


function buttonInit()
{
	setButtonStatus(false,null);
}

function removeAllOnClass()
{
	var tbody = $("tbody","#memberTable");
	tbody.children("tr").each(function(){
		if($(this).hasClass("on_hy_tr"))
		{
			$(this).removeClass("on_hy_tr");
		}
	});
	
}

var tabKeyAreaIndex = 0;

function setButtonStatus(isSelect,tr)
{
	if(isSelect)
	{
		var cardStatus = tr.attr("cardStatus");
		if(cardStatus)
		{
			if(cardStatus == '5')
			{
				//退卡
				$("#guashi").removeClass("hy_guashi").addClass("hy_guashi_no");
				$("#tingyong").removeClass("hy_tingyong").addClass("hy_tingyong_no");
				$("#tuika").removeClass("hy_tuika").addClass("hy_tuika_no");
				$("#chongzhi").removeClass("hy_chongzhi").addClass("hy_chongzhi_no");
			}
			else if(cardStatus == '3')
			{
				//挂失
				$("#guashi").removeClass("hy_guashi").addClass("hy_guashi_no");
				$("#tingyong").removeClass("hy_tingyong").addClass("hy_tingyong_no");
				$("#tuika").removeClass("hy_tuika").addClass("hy_tuika_no");
				$("#chongzhi").removeClass("hy_chongzhi").addClass("hy_chongzhi_no");
			}
			else if(cardStatus == '2')
			{
				//停用
				$("#guashi").removeClass("hy_guashi").addClass("hy_guashi_no");
				$("#tingyong").removeClass("hy_tingyong").addClass("hy_tingyong_no");
				$("#tuika").removeClass("hy_tuika").addClass("hy_tuika_no");
				$("#chongzhi").removeClass("hy_chongzhi").addClass("hy_chongzhi_no");
			}
			else if(cardStatus == '4')
			{
				//待发卡
				$("#guashi").removeClass("hy_guashi_no").addClass("hy_guashi");
				$("#tingyong").removeClass("hy_tingyong_no").addClass("hy_tingyong");
				$("#tuika").removeClass("hy_tuika_no").addClass("hy_tuika");
				$("#chongzhi").removeClass("hy_chongzhi_no").addClass("hy_chongzhi");
			}
			else if(cardStatus == '1')
			{
				//正常
				$("#guashi").removeClass("hy_guashi_no").addClass("hy_guashi");
				$("#tingyong").removeClass("hy_tingyong_no").addClass("hy_tingyong");
				$("#tuika").removeClass("hy_tuika_no").addClass("hy_tuika");
				$("#chongzhi").removeClass("hy_chongzhi_no").addClass("hy_chongzhi");
			}
		}
		else
		{
			$("#guashi").removeClass("hy_guashi").addClass("hy_guashi_no");
			$("#tingyong").removeClass("hy_tingyong").addClass("hy_tingyong_no");
			$("#tuika").removeClass("hy_tuika").addClass("hy_tuika_no");
			$("#chongzhi").removeClass("hy_chongzhi").addClass("hy_chongzhi_no");
		}
	}
	else
	{
		$("#guashi").removeClass("hy_guashi").addClass("hy_guashi_no");
		$("#tingyong").removeClass("hy_tingyong").addClass("hy_tingyong_no");
		$("#tuika").removeClass("hy_tuika").addClass("hy_tuika_no");
		$("#chongzhi").removeClass("hy_chongzhi").addClass("hy_chongzhi_no");
	}
}
function initButton()
{
	$("#jianka").click(function(){
		if(getPermission("jianka"))
		{
			if(currentRow)
			{
				var mbId = currentRow.attr("id");
				if(mbId)
				{
					popForm('快速建卡',window.ctx+'/member/pop/jianka/createForm?mbId='+mbId,'880','644');
				}
			}
			else
			{
				popForm('快速建卡',window.ctx+'/member/pop/jianka/createForm','880','644');
			}
			
		}
		
	});
}


function guashi()
{
	$("#guashi").click(function(){
		if(!$("#guashi").hasClass("hy_guashi_no"))
		{
			if(getPermission("guashi"))
			{
				if(currentRow)
				{
					var mcId = currentRow.attr("mcId");
					var cardStatus = currentRow.attr("cardStatus");
					if(mcId)
					{
						if(cardStatus != 3)
						{
							dialogBoxConfirm("挂失","确定挂失此会员卡吗？",function(){
								 $.ajax({
									    type:"get",
									    url:window.ctx+'/member/changeCardStatus/'+mcId+'/3',
									    data:null,
									    cache:false,
									    async:true,
									    success:function(data){
									    	toastr.success(data.message);
									    	dishCatagoryChange(currentKewWords,currentSearchParams,currentPage,currentCardStatus);
									    },
										error:function(){
											toastr.warning("操作失败");
										}
								 });
							});
						}
						else
						{
							toastr.info("此会员卡已经挂失");
						}
						
					}
					else
					{
						toastr.warning("此会员无会员卡");
					}
				}
			}
			
			
		}
	});
}

function tingyong()
{
	$("#tingyong").click(function(){
		if(!$("#tingyong").hasClass("hy_tingyong_no"))
		{
			if(getPermission("tingyong"))
			{
				if(currentRow)
				{
					var mcId = currentRow.attr("mcId");
					var cardStatus = currentRow.attr("cardStatus");
					if(mcId)
					{
						if(cardStatus != 2)
						{
							dialogBoxConfirm("停用","确定停用此会员卡吗？<br/>如需启用此会员卡，请点击会员卡号",function(){
								 $.ajax({
									    type:"get",
									    url:window.ctx+'/member/changeCardStatus/'+mcId+'/2',
									    data:null,
									    cache:false,
									    async:true,
									    success:function(data){
									    	toastr.success(data.message);
									    	dishCatagoryChange(currentKewWords,currentSearchParams,currentPage,currentCardStatus);
									    },
										error:function(){
											toastr.error("操作失败");
										}
								 });
							});
						}
						else
						{
							toastr.info("此会员卡已经停用");
						}
					}
					else
					{
						toastr.warning("此会员无会员卡");
					}
				}
			}
			
		}
	});
}

function tuika()
{
	$("#tuika").click(function(){
		if(!$("#tuika").hasClass("hy_tuika_no"))
		{
			if(getPermission("tuika"))
			{
				if(currentRow)
				{
					var mcId = currentRow.attr("mcId");
					var cardStatus = currentRow.attr("cardStatus");
					if(mcId)
					{
						if(cardStatus != 5)
						{
							popForm('退卡',window.ctx+'/member/pop/tuika/createForm/'+mcId,'529','335');
						}
						else
						{
							toastr.info("此会员卡已经退卡");
						}
					}
					else
					{
						toastr.warning("此会员无会员卡");
					}
				}
			}
			
		}
	});
}

function chongzhi()
{
	$("#chongzhi").click(function(){
		if(!$("#chongzhi").hasClass("hy_chongzhi_no"))
		{
			if(getPermission("chongzhi"))
			{
				if(currentRow)
				{
					var mcId = currentRow.attr("mcId");
					if(mcId)
					{
						var mcId = currentRow.attr("mcId");
						popForm('会员卡充值',window.ctx+'/member/pop/chongzhi/createForm/'+mcId,'529','644');
					}
					else
					{
						toastr.warning("此会员无会员卡");
					}
				}
				else
				{
					toastr.warning("请选择一张会员卡!");
				}
			}
		}
	});
}

function popCloseBox()
{
	if("undefined" == typeof kuaijiejian)
	{
		closebox();
	}
	else
	{
		closebox(kuaijiejian);
	}
}

//会员卡搜索的enter事件
function kewWordsEnter(event){
	var keyCode = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
	 if (keyCode ==13){
		 dishCatagoryChange(document.getElementById('kewWords').value,currentSearchParams,1,'');
	 }
}


/**
 * 打印会员卡信息
 * @param element
 */
function printMemberCardInfo(cardId,cardNo){
		
	if(getPermission("memberPrint"))
	{
		dialogBoxConfirm("会员卡信息打印确认","确定打印卡号为‘    "+cardNo+"  ’的会员卡信息吗？",function(){
			$.ajax({
				type:"get",
				url:window.ctx+"/member/ajax/printMemberCardInfo",
				data:{'mcId':cardId},
				cache:false,
				success:function(data){
					toastr.info(data.message);
				},
				error:function(){}
			});
		});
	}
		
	
}
