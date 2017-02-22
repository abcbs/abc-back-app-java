var timeRefreshOrder = null;
$(document).ready(function(){
	kuaijiejian();
	initFrameSize();
	refreshPage();
	
});


/**
 * 审核外卖单
 */
function openReviewTakeout(element){
	if(!getPermission("cloud_review"))
	{
		return;
	}
	
	if(element){
		if($(element).hasClass("ddshenhe_no")){
			return;
		}
	}else{
		return;
	}
	if(currentBill){
		var billId = currentBill.attr("id");
		var billType = currentBill.attr("billType");
		popShenhe(billType,billId);
	}
}

/**
 * 弹出审核页面
 * @param billType 订单类型
 * @param billId 云订单ID
 */
function popShenhe(billType,billId){
	if(billType == '1'){
		popForm('审核外卖单',window.ctx+'/cloud/pop/billDetail?billId='+billId,'880','644');
	}
	else{
		popForm('审核预订单',window.ctx+'/cloud/pop/cloudOrderBillDetail?billId='+billId,'880','644');
	}
}

/**
 * 弹出审核追踪
 */
function openBillTrack(element){
	if(!getPermission("cloud_zhuizong"))
	{
		return;
	}
	if(element){
		if($(element).hasClass("ddzhuizong_no")){
			return;
		}
	}else{
		return;
	}
	var billId = currentBill.attr("id");
	var billType = currentBill.attr("billType");
	var billNo = currentBill.attr("billNo");
	popZhuizong(billType,billId,billNo);
}

/**
 * 弹出订单追踪页面
 * @param billType
 * @param billId
 * @param billNo
 */
function popZhuizong(billType,billId,billNo){
	if(billType == '1'){
		popForm('订单追踪',window.ctx+'/cloud/pop/takeoutTrack?billId='+billId+"&billNo="+billNo,'600','644');
	}else if(billType == '2'){
		popForm('订单追踪',window.ctx+'/cloud/pop/orderTrack?billId='+billId+"&billNo="+billNo,'600','644');
	}
}

/**
 * 刷新页面
 */
function refreshPage(){
	if(!getPermission("cloud_refresh"))
	{
		return;
	}
	var isConnect = $("#isConnect").val();
	if(isConnect == 'true'){
		cloudBillChange(cloudBill_type,1,cloudBill_keywords);
	}
}

function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".body").animate({width:998+subWidth},window.animate_speed);
		$(".list_l").animate({width:804+subWidth},window.animate_speed);
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body").css("height",668+subHeight+"px");
		$(".list_l").css("height",665+subHeight+"px");
		$(".list_r").css("height",665+subHeight+"px");
	}
}

var cloudBill_type = "";
var cloudBill_keywords = "";
var cloudBill_page = 1;

/**
 * 搜索云订单
 * @param type
 * @param page
 * @param keywords
 */
function cloudBillChange(type,page,keywords)
{
	ProgressbarUtil.show("ajaxContent",594);
	var cloudBillStatus = $("#cloudBillStatus").val();
	cloudBill_type = type;
	cloudBill_keywords = keywords;
	cloudBill_page = page;
	ajaxUtil.getUrlContent(getScreenSizeUrl('/cloud/ajax/bill/list?searchType='+cloudBill_type+'&page='+cloudBill_page+'&keywords='+keywords+'&cloudBillStatus='+cloudBillStatus,'cloudBill'),"ajaxContent",function(){
    	initWidth();
    	bindBillAreaEvent();
    	setFocus(document.getElementById("keywords"));
	});
	
}

/**
 * 设置云订单状态
 */
function setCloudBillStatus(status,element){
	$("#cloudBillStatus").val(status);
	setStatusClass(status,element);
	refreshPage();
}

/**
 * 设置状态样式
 */
function setStatusClass(status,element){
	//重置订单审核、订单跟踪按钮状态
	setButtonStatus();
	//重置所有状态Class
	$("#allStatus").removeClass().addClass("zt_quanbu");
	$("#daishenheStatus").removeClass().addClass("zt_daishenhe");
	$("#tongguoStatus").removeClass().addClass("zt_shenhetongguo");
	$("#weitongguoStatus").removeClass().addClass("zt_shenheweitongguo");
	var currentClass = "";
	//全部
	if(status == ''){
		currentClass = "zt_quanbu_on";
	}
	//待审核
	if(status == '0'){
		currentClass = "zt_daishenhe_on";
	}
	//审核通过
	if(status == '1'){
		currentClass = "zt_shenhetongguo_on";
	}
	//审核不通过
	if(status == '-1'){
		currentClass = "zt_shenheweitongguo_on";
	}
	$(element).removeClass().addClass(currentClass);
}

function bindBillAreaEvent()
{
	$(".index_box").bind('contextmenu', function() {
	      return false;
	    });
	
	$(".index_box").click(function(event){
		event.stopPropagation();
		removeAllOnClass();
		//订单审核和订单跟踪置灰不可用
		$("#ddshenhe").removeClass().addClass("ddshenhe_no");
		$("#ddzhuizong").removeClass().addClass("ddzhuizong_no");
		currentBill = null;
	});
	clearInterval(timeRefreshOrder);
//	timeRefreshOrder = setInterval("isChangedOrder()",30000);
}

/**
 * 云订单有变动，则更新当前云订单列表
 */
function isChangedOrder(){
	if(needRefreshCloud == '1'){
		searchBill();
	}
	return;
	jQuery.ajax({
		url: window.ctx + "/cloud/isChangedOrder",
		type: "GET",
		dataType: "JSON",
		success: function(data){
			var needRefreshCloud = data.messageMap.needRefreshCloud;
			if(needRefreshCloud == '1'){
				searchBill();
			}
		},
		error:function(){
		}
	});
}

function refreshCloudBill(){
	searchBill();
}

function searchBill()
{
	var keywords = $("#keywords").val();
	cloudBillChange(cloudBill_type,1,keywords);
}
	
/**
 * 自适应浏览器宽度
 */
function initWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".main_nav").css("width",806+subWidth+"px");
		$("#left").css("width",795+subWidth+"px");
		$(".main_nav").css("width",807+subWidth+"px");
		$(".search_wrap").css("width",795+subWidth+"px");
	}
	
	var subHeight = (windowAreaParams.screenHeight - 768) < 0 ? 0 : (windowAreaParams.screenHeight - 768);
	if(subHeight > 0)
	{
//		$(".left").css("height",517+subHeight+"px");
//		$("#left").css("height",540+"px");
		$(".index_box").css("height",600+subHeight+"px");
	}
}

var currentBill = null;
//云订单 的左右键点击事件处理
function billDivClick(event,divDom)
{
	event=event?event:window.event;
    event.cancelBubble=true;
	
	var div = $(divDom);
	if(!div.attr("id")){
		return false;
	}
    // click 事件的处理
	//订单类型
	var billType = div.attr("billType");
	//订单状态
	var billStatus = div.attr("billStatus");
	setButtonStatus(billType,billStatus);
	currentBill = div;
	//如果是左键
	if(event.which == 1 || event.which != 3){
		var divClass = currentBill.attr("class");
		if(currentBill.hasClass("ons")){
			currentBill.removeClass("ons");
		}else{
			removeAllOnClass();
			currentBill.addClass("ons");
		}
	}else if(event.which == 3){
		//右键
	}
	return true;
}

//云订单 的双击事件处理
function billDivDBClick(divDom){
	var div = $(divDom);
	if(!div.attr("id")){
		return false;
	}
	//订单类型
	var billType = div.attr("billType");
	//订单ID
	var billId = div.attr("id");
	//订单NO
	var billNo = div.attr("billNo");
	//订单状态
	var billStatus = div.attr("billStatus");
	//外卖
	if(billType == '1'){
		//订单审核
		if(billStatus == '1'){
			popShenhe(billType,billId);
		}
		//订单追踪
		if(billStatus == '2' || billStatus == '3' || billStatus == '4' || billStatus == '5' || billStatus == '6' || billStatus == '7' || billStatus == '8'){
			//外卖单详情
			showTakeoutDetail(billId);
		}
	}
	//预订
	if(billType == '2'){
		//订单审核
		if(billStatus == '1'){
			popShenhe(billType,billId);
		}
		//预订单详情
		if(billStatus == '2' || billStatus == '4' || billStatus == '5' || billStatus == '6' || billStatus == '7' || billStatus == '8'){
			showOrderDetail(billId);
		}
	}
}

function refreshCloud(){
	ProgressbarUtil.show("noConnectDiv",594);
	window.location = window.ctx + "/cloud/list";
}

/**
 * 外卖单详情
 * @param billId
 */
function showTakeoutDetail(billId){
	popForm('外卖单详情',window.ctx+'/cloud/pop/takeoutBillDetail/'+billId,'880','644');
}

/**
 * 预订单详情
 * @param billId
 */
function showOrderDetail(billId){
	popForm('预订单详情',window.ctx+'/cloud/pop/orderBillDetail/'+billId,'880','644');
}

/**
 * 设置订单审核、订单跟踪的状态
 */
function setButtonStatus(billType,billStatus){
	//外卖
	if(billType == '1'){
		//待审核
		if(billStatus == '1'){
			$("#ddshenhe").removeClass().addClass("ddshenhe");
			$("#ddzhuizong").removeClass().addClass("ddzhuizong_no");
		}
		//审核通过(2：已确认，4：已下单，5：派送中，6：已送达，7：已结账，8：已取消)
		if(billStatus == '2' ||  billStatus == '4' || billStatus == '5' || billStatus == '6' || billStatus == '7' || billStatus == '8'){
			$("#ddshenhe").removeClass().addClass("ddshenhe_no");
			$("#ddzhuizong").removeClass().addClass("ddzhuizong");
		}
		//审核未通过
		if(billStatus == '3'){
			$("#ddshenhe").removeClass().addClass("ddshenhe_no");
			$("#ddzhuizong").removeClass().addClass("ddzhuizong_no");
		}
	}else if(billType == '2'){
		//待审核
		if(billStatus == '1'){
			$("#ddshenhe").removeClass().addClass("ddshenhe");
			$("#ddzhuizong").removeClass().addClass("ddzhuizong_no");
		}
		//审核通过(2：已确认，4：完结，5：已取消，7：已过期，8：就餐中)
		if(billStatus == '2' || billStatus == '4' || billStatus == '5' || billStatus == '7' || billStatus == '8'){
			$("#ddshenhe").removeClass().addClass("ddshenhe_no");
			$("#ddzhuizong").removeClass().addClass("ddzhuizong");
		}
		//审核未通过
		if(billStatus == '6'){
			$("#ddshenhe").removeClass().addClass("ddshenhe_no");
			$("#ddzhuizong").removeClass().addClass("ddzhuizong_no");
		}
	}else{
		$("#ddshenhe").removeClass().addClass("ddshenhe_no");
		$("#ddzhuizong").removeClass().addClass("ddzhuizong_no");
	}
}

/**
 * 改变账单状态
 */
function changeCloudStatus(status){
	refreshPage();
}

function removeAllOnClass()
{
	$("div",$(".left",".index_box")).each(function(){
		if($(this).hasClass("ons"))
		{
			$(this).removeClass("ons");
		}
	});
}

/**
 * 快捷键
 */
var hotKey = null;
function kuaijiejian()
{
	hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"1",
		callBackFunction:function()
		{//订单审核 快捷键
			var isFocus=$("#keywords").is(":focus");
			var isClass = $("#ddshenhe").hasClass("ddshenhe_no");
			if(!isFocus && !isClass){//焦点不在搜索文本框且订单审核按钮允许点击，那么触发订单审核按钮的点击事件
				$("#ddshenhe").click();
			}
			
		}
	}
	,
	{//快捷键2 ：刷新
		keyCode:"2",
		callBackFunction:function()
		{//刷新 快捷键
			var isFocus=$("#keywords").is(":focus");
			if(!isFocus){//焦点不在搜索框上，那么触发刷新按钮的点击事件
				window.location.href=$("#but_shuaxin").attr("href");
			}
			
		}
	}
	,
	{
		keyCode:"3",
		callBackFunction:function()
		{//订单追踪 快捷键
			var isFocus=$("#keywords").is(":focus");
			var isClass = $("#ddzhuizong").hasClass("ddzhuizong_no");
			if(!isFocus && !isClass){//焦点不在搜索文本框且订单追踪按钮允许点击，那么触发订单追踪按钮的点击事件
				$("#ddzhuizong").click();
			}
		}
	}
	,
	{
		keyCode:"enter",
		callBackFunction:function()
		{
			var isFocus=$("#keywords").is(":focus");
			if(isFocus){//触发检索按钮的点击事件
				$("#but_keyWords").click();
			}
			
		}
	}
	,
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			var isFocus=$("#keywords").is(":focus");
			if(isFocus){//如果已获取焦点，那么让他失去焦点
				$("#keywords").blur();
			}
			else{//如果未获取焦点，那么让他获取焦点
				$("#keywords").focus();
			}
		}
	},
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/index';
		}
	}
	],0);
	
}

	