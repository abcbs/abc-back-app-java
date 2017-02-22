var currentDishe = null;

$(document).ready(function(){
	initFrameSize();
	
	refreshPage();
	
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
			estimate();
		}
	}
	,
	{
		keyCode:"2",
		callBackFunction:function()
		{
			qxestimate();
		}
	}
	,
	{
		keyCode:"3",
		callBackFunction:function()
		{
			allQxestimate();
		}
	}
	,
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/cloud/list';
		}
	}
	,
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			//搜索框得到焦点
			$("#keywords").focus();
			setFocus(document.getElementById("keywords"));
		}
	}
	],0);
	
}

function refreshPage()
{
	dishCatagoryChange('',diancanDish_categoryId,diancanDish_page,diancanDish_dsCategoryId,diancanDish_keywords);
}

function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body_huiyuan").animate({width:998+subWidth},window.animate_speed);
		$(".list_h").animate({width:807+subWidth},window.animate_speed);
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_huiyuan").css("height",670+subHeight+"px");
		$(".list_h").css("height",664+subHeight+"px");
		$(".list_y").css("height",664+subHeight+"px");
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
		$(".guqing_nav").css("width",806+subWidth+"px");
		$(".guqing_wrap").css("width",795+subWidth+"px");
		$(".guqing_nav").css("width",807+subWidth+"px");
		$(".hy_sousuo").css("width",795+subWidth+"px");
	}
	
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".guqing_wrap").css("height",517+subHeight+"px");
	}
	
	//分区个数
	var mwidth = $(".guqing_nav").width();
	var areaNum = parseInt(mwidth/81);
	if($("#indexDishesAraaUl").find("li").length <= areaNum)
	{
		$("#dish_left").hide();
		$("#dish_right").hide();
	}
	$(".guqing_nav").find("ul").css("width",($("#indexDishesAraaUl").find("li").length+1)*81+"px");
	$("#indexDishesAraaUl").css({marginLeft:memberListUlMarginLeft+"px"});
}
var memberListUlMarginLeft = 0;
/**
 * 点击菜肴分类
 */
var diancanDish_categoryId ='';
var diancanDish_dsCategoryId ='';
var diancanDish_keywords ='';
var currentSearchEstimateStatus = '';
var diancanDish_page = 1;

function dishCatagoryChange(billId,categoryId,page,dsCategoryId,keywords,estimateStatus)
{
	ProgressbarUtil.show("ajaxContent",594);
	diancanDish_categoryId = categoryId;
	diancanDish_dsCategoryId = dsCategoryId;
	diancanDish_keywords = keywords;
	diancanDish_page = page;
	if(estimateStatus || estimateStatus=='')
	{
		currentSearchEstimateStatus=estimateStatus;
	}
	ajaxUtil.getUrlContent(getScreenSizeUrl('/estimate/ajax/dishes/list?estimateStatus='+currentSearchEstimateStatus+'&page='+diancanDish_page+'&categoryId='+categoryId+'&dsCategoryId='+dsCategoryId+'&keywords='+keywords,'estimateDish'),"ajaxContent",function(){
    	initWidth();
    	initTableEvent();
    	checkEstimateNumber();
	});
}

/**
 * 定时刷新沽清类别的数量
 */
function checkEstimateNumber()
{
	$.ajax({
	    type:"get",
	    url:window.ctx+'/estimate/getEstimateNumber',
	    cache:false,
	    async:true,
	    dataType: "json",
	    success:function(data){
	    	var json = eval(data.message);
	    	var ts = json[0];
	    	$("#es_quanbu").html(ts.all+"份");
	    	$("#es_yiguqing").html(ts.yiguqing+"份");
	    	$("#es_yishouxin").html(ts.yishouxin+"份");
	    	$("#es_jiangshouwan").html(ts.jiangshouwan+"份");
	    	$("#es_weiguqing").html(ts.weiguqing+"份");
	    },
		error:function(){
			
		}
	});
}


function changeDishesStatus(estimateStatus,a)
{
	var ja = $(a);
	var oriClass = ja.attr("oriClass");
	dishCatagoryChange('','',1,'','',estimateStatus);
	
	$("a",".guqing_zhuangtai").each(function(){
		var t = $(this);
		var toriClass = t.attr("oriClass");
		t.removeClass().addClass(toriClass);
	});
	ja.addClass(oriClass+"_on");
}


function initTableEvent()
{
	currentDishe=null;
	setButtonStatus('');
	$("#dish_left").click(function(){
		var left = $("#indexDishesAraaUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(left < 0)
		{
			left+= 500;
			memberListUlMarginLeft = left;
			$("#indexDishesAraaUl").animate({marginLeft:left+"px"},100);
		}
	});
	
	$("#dish_right").click(function(){
		var mainTabWidth = $(".guqing_nav").width();
		var mainCatagoryTabWidth = $("#indexDishesAraaUl").width();
		var left = $("#indexDishesAraaUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(mainCatagoryTabWidth + left < mainTabWidth)
		{
			return;
		}
		left-= 500;
		memberListUlMarginLeft = left;
		$("#indexDishesAraaUl").animate({marginLeft:left+"px"},100);
	});
	$("#keywords").focus();
	setFocus(document.getElementById("keywords"));
	$("#keywords").keydown(function(evt) {
		evt=evt?evt:window.event;
		evt.cancelBubble=true;
		if(!(evt.keyCode >= 48 || evt.keyCode <= 90 || evt.keyCode == 13 || evt.keyCode == 17 || evt.keyCode == 8)){
			return;
		}
		//上右下左箭头
		if(evt.keyCode == 38 || evt.keyCode == 39 || evt.keyCode == 40 || evt.keyCode == 37)
		{
			return;
		}
		//17=Ctrl
		if(evt.keyCode != 13 && !(evt.ctrlKey && evt.keyCode == 17)){
			setTimeout(function(){
				keyWordSearch();
			},100);
		} else if(evt.ctrlKey && evt.keyCode==13){
			
		} else if(evt.keyCode != 17){
			
		}
	});
}

function keyWordSearch()
{
	var keywords = $("#keywords").val();
	dishCatagoryChange('',diancanDish_categoryId,1,diancanDish_dsCategoryId,keywords);
}


function ajaxErrorFunction()
{
	toastr.error("出错了");
}


//add by wangnan

//弹出沽清对话框
function estimate(){
	if(currentDishe)
	{
		var dishesId = currentDishe.attr("dishesId");
		var dishesType = currentDishe.attr("dishesType");
		popForm('沽清',window.ctx+'/estimate/pop/estimate?dishesId='+dishesId+'&dishesType='+dishesType,'550','644');
	}
}

function qxestimate()
{
	if(currentDishe)
	{
		
		if($("#qxguqing").hasClass("qx_guqing_no")){
			return;
		}
		
		var dishesId = currentDishe.attr("dishesId");
		var dishesType = currentDishe.attr("dishesType");
		var dishesName = currentDishe.attr("dishesName");
		dialogBoxConfirm("取消沽清","确定取消‘"+dishesName+"’的沽清数量吗？",function(){
			$.ajax({
			    type:"get",
			    url:window.ctx+"/estimate/qxguqing/"+dishesId+"?dishesType="+dishesType,
			    data:null,
			    cache:false,
			    async:true,
			    dataType: "json",
			    success:function(data){
		    		toastr.success(data.message);
		    		refreshPage();
			    },
				error:function(){
				}
			  });
		});
	}
}

//取消全部沽清
function allQxestimate()
{
	dialogBoxConfirm("批量取消沽清","确定取消全部菜肴沽清吗？",function(){
		$.ajax({
		    type:"get",
		    url:window.ctx+"/estimate/allQxguqing",
		    data:null,
		    cache:false,
		    async:true,
		    dataType: "json",
		    success:function(data){
	    		toastr.success(data.message);
	    		refreshPage();
		    },
			error:function(){
			}
		  });
	});
}


//点击菜肴
function disheDivClick(event,divDom)
{
	event=event?event:window.event;
    event.cancelBubble=true;
	
	var div = $(divDom);
	
	//如果是左键
	if(event.which == 1){
		var thisClass = div.attr("class");
		if(thisClass.indexOf("_on") < 0){
			if(currentDishe)
			{
				var currentDisheClass = currentDishe.attr("class");
				if(currentDisheClass.indexOf("_on") >= 0){
					var currentDisheOldClass = currentDishe.attr("oldClass");
					currentDishe.removeClass(currentDisheOldClass+"_on");
				}
			}
			div.addClass(thisClass+"_on");
			currentDishe = div;
		}
		else
		{
			var oldClass = div.attr("oldClass");
			div.removeClass(oldClass+"_on");
			currentDishe=null;
		}
		setButtonStatus();
	}
	return true;
}

function setButtonStatus(estimate)
{
	if(currentDishe)
	{
		var estimate = currentDishe.attr("estimate");
		//有沽清数量的
		if(estimate && estimate >=0)
		{
			$("#qxguqing").removeClass().addClass("qx_guqing");
		}
		else
		{
			$("#qxguqing").removeClass().addClass("qx_guqing_no");
		}
		
		
		$("#guqing").removeClass().addClass("guqing mr_8");
		
	}
	else
	{
		$("#guqing").removeClass().addClass("guqing_no mr_8");
		$("#qxguqing").removeClass().addClass("qx_guqing_no");
	}
	
	
	
}


