$(document).ready(function(){
	var billId = $("#currentBillId").val();
	dishCatagoryChange(billId,'',1,'','','');
	
	diancaiDishFirstEnter();
	
	
});
var diancaiListUlMarginLeft=0;

function diancaiDishFirstEnter()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".list_b").css("width",659+subWidth+"px").effect("slide",300);
	}
}

/**
 * 初始化选中的菜肴
 */
function initSelectedDish()
{
	setTimeout(function(){
		var billDishes = $("#diancaiBillContentDishesTr").children("tr[bdId]");
		var dishesParent = $("#diancaiDishDiv");
		
		for(var i=0;i<billDishes.length;i++)
		{
			var tr = $(billDishes[i]);
			var dishesId = billDishes[i].id;
			var dishesName = tr.attr('dishesName');
			
			var dish = dishesParent.find("div[id='"+dishesId+"']");
			var dishClass = dish.attr('class');
			if(dishClass.indexOf('_order') < 0)
			{
				var orderClass = dishClass + "_order";
				dish.removeClass().addClass(orderClass);
			}
		}
	},200);
}

function initDishWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".list_b").css("width",659+subWidth+"px");
		$(".list_b_in").css("width",658+subWidth+"px");
		$(".tab_nav_wrap").css("width",658+subWidth+"px");
		$(".tab_nav").css("width",658+subWidth+"px");
		
		$(".tab_nav_baby").css("width",644+subWidth+"px");
		$(".diancan_wrap").css("width",628+subWidth+"px");
		$(".diancan_but_area").css("width",658+subWidth+"px");
		$(".search_sec").css("width",200+subWidth+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".list_b_in").css("height",670+subHeight+"px");
		$(".diancan_wrap").css("height",494+subHeight+"px");
	}
	var dishTabWidth = $(".tab_nav_baby").width();
	var dishCatagoryTabWidth = $("#diancaiListUl").width();
	if(dishCatagoryTabWidth < dishTabWidth)
	{
		$("#dish_left").hide();
		$("#dish_right").hide();
	}
	$("#diancaiListUl").css({marginLeft:diancaiListUlMarginLeft+"px"});
}
var isSelectedFirst = false;
function keyWordSearch(keywords)
{
	if(!keywords){
		keywords = $("#keywords").val();
	}
	var billId = $("#currentBillId").val();
	dishCatagoryChange(billId,diancanDish_categoryId,1,diancanDish_dsCategoryId,keywords,diancanDish_firstCategoryId);
}
var diancanDish_firstCategoryId ='';
var diancanDish_categoryId ='';
var diancanDish_dsCategoryId ='';
var diancanDish_keywords ='';
var diancanDish_page =1;
function refreshDish()
{
	var billId = $("#currentBillId").val();
	dishCatagoryChange(billId,diancanDish_categoryId,diancanDish_page,diancanDish_dsCategoryId,diancanDish_keywords,diancanDish_firstCategoryId);
}

function dishCatagoryChange(billId,categoryId,page,dsCategoryId,keywords,firstCategoryId)
{
	if(firstCategoryId && firstCategoryId != 'all')
	{
		diancanDish_firstCategoryId = firstCategoryId;
	}
	else if(firstCategoryId == 'all')
	{
		diancanDish_firstCategoryId = '';
	}
	diancanDish_categoryId = categoryId;
	diancanDish_dsCategoryId = dsCategoryId;
	diancanDish_keywords = keywords;
	diancanDish_page = page;
	var billType = $("#currentBillType").val();
	
//	ProgressbarUtil.show("ajaxContent",670);
	ajaxUtil.getUrlContent(getScreenSizeUrl('/bill/ajax/diancaiContent?billType='+billType+'&billId='+billId+'&page='+page+'&categoryId='+categoryId+'&dsCategoryId='+dsCategoryId+'&keywords='+keywords+'&firstCategoryId='+diancanDish_firstCategoryId,'dish'),"ajaxContent",function(){
		initDishWidth();
    	billListInitEvent();
    	if(keywords && isSelectedFirst){
    		//选择第一个菜肴
        	$("#diancaiDishDiv").find("div").each(function(index){
        		if(index == 0){
        			var divClass = $(this).attr("class");
        			if(divClass == "diancan_green" || divClass == "diancan_green_order"){
        				$(this).attr("class","diancan_green_select");
        			}else if(divClass == "diancan_red" || divClass == "diancan_red_order"){
        				$(this).attr("class","diancan_red_select");
        			}else if(divClass == "diancan_yellow" || divClass == "diancan_yellow_order"){
        				$(this).attr("class","diancan_yellow_select");
        			}
        		}
        	});
    	}
	});
	
}

var tabKeyAreaIndex = 0;
function billListInitEvent()
{
	$(".diancan_wrap").disableSelection();
	$("#userDefined").click(function(){
		if(getPermission("userDefined"))
		{
			var billStatus =$("#billStatus").val();
			if(billStatus == 3 || billStatus == 8)
			{
				//结账，撤单，不能再加菜
				toastr.warning("不能再加菜");
				return;
			}
			var billId = $("#currentBillId").val();
			var billType = $("#currentBillType").val();
			popForm('自定义菜肴',window.ctx+'/bill/pop/userdefined/create?billId='+billId+'&billType='+billType,'529','644');
		}
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
				isSelectedFirst = true;
				keyWordSearch($("#keywords").val());
			},100);
		}else if(evt.ctrlKey && evt.keyCode==13){
			//获取第一个菜肴
			selectDishDiv(0);
		}else if(evt.keyCode != 17){
			//获取第一个菜肴
			selectDishDiv(0);
		}
	});
	
	
	$("body").unbind("keydown");
	$("body").find("input").focusin(function(){
		$("body").unbind("keydown");
	}).focusout(function(){
		CanYinHotKeysGetLastHotKeys(0);
	});
	
//	$(".body_sec").unbind("keydown");
//	$(".body_sec").keydown(function(evt) {
//		if(evt.keyCode == 9)
//		{
//			var lis = $("#diancaiListUl").children("li");
//			var ci = tabKeyAreaIndex;
//			if(ci >= lis.length-1)
//			{
//				ci = 0;
//			}
//			else
//			{
//				ci=tabKeyAreaIndex+1;
//			}
//			tabKeyAreaIndex=ci;
//			lis.eq(tabKeyAreaIndex).children("a").click();
//		}
//	});
	
	$("#dish_left").click(function(){
		var left = $("#diancaiListUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(left < 0)
		{
			left+= 300;
			diancaiListUlMarginLeft=left;
			$("#diancaiListUl").animate({marginLeft:left+"px"},100);
		}
		
	});
	
	$("#dish_right").click(function(){
		var dishTabWidth = $(".tab_nav_baby").width();
		var dishCatagoryTabWidth = $("#diancaiListUl").width();
		var left = $("#diancaiListUl").css("marginLeft");
		left = parseInt(left.replaceAll("px",""));
		if(dishCatagoryTabWidth + left < dishTabWidth - 100)
		{
			return;
		}
		left-= 300;
		diancaiListUlMarginLeft=left;
		$("#diancaiListUl").animate({marginLeft:left+"px"},100);
	});
}


function selectDishDiv(index)
{
	var selectDiv = $("#diancaiDishDiv").find("div").eq(index);
	var selectDivClass =selectDiv.attr("class");
	if(selectDivClass == "diancan_green_select" || selectDivClass == "diancan_red_select" || selectDivClass == "diancan_yellow_select"){
		var dishAndSetDiv = selectDiv.attr("dishAndSetDiv");
		//菜肴
		if(dishAndSetDiv == 1){
			addDishes(selectDiv);
			$("#keywords").val("");
		}else if(dishAndSetDiv == 2){//套餐
			dishesSetForm(selectDiv[0],selectDiv.attr("dishesId"))
		}
		return false;
	}
}


function pageInitDishCategory(start,end)
{
	$("#diancaiListUl").find("li").each(function(i){
		if(i<3)
		{
			$(this).show();
		}
	});
}

function showFirstLeve(e,a){
	e.stopPropagation();
	var m=$('#dc_box_wrap');
	
	m.css({
		position: "absolute"
	});
	m.show();
	
	var l = e.pageX + 2,
	t = e.pageY+25,
	p={
		wh:$(window).height(),
		ww:$(window).width(),
		mh:m.height(),
		mw:m.width()
	}
	t=(t+p.mh)>=p.wh?(t-=p.mh):t;//当菜单超出窗口边界时处理
	l=0;
	m.css({zIndex:999998, left:l, top:98});
	
	$("body").click(function(event){
		m.hide();
	});
	
	m.children(".dc_box").unbind("mouseleave");
	m.children(".dc_box").bind("mouseleave",function(){
		m.hide();
	});
}
