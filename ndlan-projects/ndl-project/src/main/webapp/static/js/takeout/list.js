$(document).ready(function(){
	initFrameSize();
	hotKeyForTakeout();
	searchTakeout();
	$("#xinzeng").bind("click",xinzeng);
	$("#diancai").bind("click",diancai);
	$("#xiadan").bind("click",xiadan);
	$("#chedan").bind("click",chedan);
	$("#paisong").bind("click",paisong);
	$("#jiezhang").bind("click",jiezhang);
	
//	setInterval(takeoutTimer,"300000");（废止，无意义暂停使用）
	messageListen();
});

function messageListen() {
	var from = $('#from').val();
	if(from == "message") {
		var preMobile = $('#preMobile').val();
		popForm('新增外卖',window.ctx + '/takeout/pop/addTakeout?preMobile='+preMobile,'900','700');
	}
}

/**
 * 定时任务（废止，无意义暂停使用）
 */
function takeoutTimer(){
	funButtonStatus("default");
	searchTakeout();
}

/**
 * 跳转点菜
 */
function skipDiancai(){
	if(!getPermission("takeout_diancai"))
	{
		return;
	}
	var tId = $("#tId").val();
	var billId = $("#billId").val();
	if(tId == "" || typeof(tId) == 'undefined'){
		return;
	}
	window.location = window.ctx + "/bill/diancai?billType=2&tId="+tId+"&billId="+billId;
}

/**
 * 新增外卖
 */
function xinzeng(isModify){
	if($("#index_xiugai").hasClass("index_xiugai_no")){
		return;
	}
	if(getPermission("takeout_addTakeout"))
	{
		var tId = "";
		if(isModify == 1){
			tId = $("#tId").val();
		}
		
		popForm('新增外卖',window.ctx + '/takeout/pop/addTakeout?tId='+tId,'900','700');
	}
}

/**
 * 点菜
 */
function diancai(){
	if($("#diancai").hasClass("diancai_no")){
		return;
	}
	skipDiancai();
}

/**
 * 下单
 */
function xiadan()
{
	if($("#xiadan").hasClass("yuding_no")){
		return;
	}
	if(!getPermission("takeout_xiadan"))
	{
		return;
	}
	var billId = $("#billId").val();
	$.ajax({
	    type:"get",
	    url:window.ctx + "/bill/xiadan/"+billId,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	var sign = data.sign;
	    	var message = data.message;
	    	if(sign == 1){
	    		var sendAtOnce = data.messageMap.sendAtOnce;
	    		if(sendAtOnce == 1){
	    			skipPaisong();
	    		}else{
	    			toastr.success("下单成功！");
	    			searchTakeout();
	    		}
	    	}else{
	    		toastr.error(message);
	    	}
	    },
		error:function(){
		}
	  });
}

/**
 * 打印派送单
 */
function dayin()
{
	if($("#index_dayin").hasClass("index_dayin_no")){
		return;
	}
	var billId = $("#billId").val();
	$.ajax({
	    type:"get",
	    url:window.ctx + "/takeout/print/"+billId,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	var statusCode = data.statusCode;
	    	if(statusCode == 200){
	    		toastr.success("打印成功！");
	    	}else{
	    		toastr.error("打印失败！");
	    	}
	    },
		error:function(){
		}
	  });
}

/**
 * 撤单
 */
function chedan(){
	if($("#chedan").hasClass("jiezhang_no")){
		return;
	}
	if(!getPermission("takeout_chedan"))
	{
		return;
	}
	var billId = $("#billId").val();
	if(billId == ""){
		//没有账单的外卖单撤单
		chedanForNoBill();
		return;
	}
	popForm('撤单',window.ctx+'/index/pop/chedan/'+billId,'529','335');
}

/**
 * 没有账单的外卖单撤单
 */
function chedanForNoBill(){
	dialogBoxConfirm('撤单确认','确定撤单吗？',function(){
		var tId = $("#tId").val();
		$.ajax({
		    type:"post",
		    url:window.ctx+"/takeout/chedanForNoBill",
		    data:"tId="+tId,
		    cache:false,
		    success:function(data){
		    	if(data.statusCode == 200){
		    		searchTakeout();
		    		toastr.success("撤单成功！");
		    	}else{
					toastr.success("撤单失败！");
				}
		    },
			error:function(){
			}
		});
	});
}

/**
 * 跳转派送页面
 */
function skipPaisong(){
	if(!getPermission("takeout_paisong"))
	{
		return;
	}
	var tId = $("#tId").val();
	if(tId == "" || typeof(tId) == 'undefined'){
		return;
	}
	popForm('派送',window.ctx+'/takeout/pop/sendForm/'+tId,'600','600');
}

/**
 * 派送
 */
function paisong(){
	if($("#paisong").hasClass("cuicai_no")){
		return;
	}
	skipPaisong();
}

/**
 * 跳转结账页面
 * @returns
 */
function skipJiezhang(){
	if(!getPermission("takeout_jiezhang"))
	{
		return;
	}
	var billId = $("#billId").val();
	window.location = window.ctx + "/bill/payPage/" + billId;
}

/**
 * 结账
 */
function jiezhang(){
	if($("#jiezhang").hasClass("qingtai_no")){
		return;
	}
	skipJiezhang();
}

/**
 * 跳转外卖单页面 
 */
function skipTakeoutDetail(){
	var tId = $("#tId").val();
	if(tId == "" || typeof(tId) == 'undefined'){
		return;
	}
	popForm('外卖单',window.ctx+'/takeout/pop/takeoutDetail/'+tId,'900','700');
}

/**
 * 双击外卖单
 */
function ondblclickTakeout(element){
	var billStatus = $(element).attr("billStatus");
	if(billStatus == '' || billStatus == '1' || billStatus == '9'){//新增外卖单，未下单，部分下单
		skipDiancai();
	}else if(billStatus == '2'){//已下单
		skipPaisong();
	}else if(billStatus == '11'){//派送中
		skipJiezhang();
	}else if(billStatus == '3' || billStatus == '8'){//结账，撤单
		skipTakeoutDetail();
	}
}

/**
 * 撤单回调
 */
function chedanPopBack(){
	searchTakeout();
}

/**
 * 搜索外卖单
 */
function searchTakeout(page)
{
	var pageSize = PageSizeUtil.getSizeByType("takeout");
	if(typeof(page) == 'undefined'){
		page = 1;
	}
	ProgressbarUtil.show("takeoutListDiv",550);
	var data = $("#takeoutListForm").serialize()+"&pageSize="+pageSize+"&page="+page;
	var focusObj = document.activeElement;
	var searchValue = $("#inputKeywords").val();
	
	$.ajax({
	    type:"get",
	    url:$("#takeoutListForm").attr("action"),
	    data:data,
	    cache:false,
	    async:true,
	    success:function(html){
	    	$("#takeoutContent").html(html);
	    	setCount();
	    	bindContentmenu();
	    	initWidth();
	    	
//	    	$("#inputKeywords").focus();
	    	$("#inputKeywords").val(searchValue);
	    	$(focusObj).focus();
	    },
		error:function(){
		}
	  });
}

/**
 * 设置预订，15分钟，30分钟，更久个数
 */
function setCount(){
	$("#time0").html("预订（"+$("#yudingCount").val()+"）");
	$("#time15").html("15分钟（"+$("#shiwuCount").val()+"）");
	$("#time30").html("30分钟（"+$("#sanshiCount").val()+"）");
	$("#timeLong").html("更久（"+$("#longCount").val()+"）");
	$("#weiwanjieCountSpan").html("未完结<br/>" + $("#weiwanjieCount").val() + "单");
	$("#weixiadanCountSpan").html("未下单<br/>" + $("#weixiadanCount").val() + "单");
	$("#yixiadanCountSpan").html("已下单<br/>" + $("#yixiadanCount").val() + "单");
	$("#paisongzhongCountSpan").html("派送中<br/>" + $("#paisongzhongCount").val() + "单");
	$("#yiwanjieCountSpan").html("已完结<br/>" + $("#yiwanjieCount").val() + "单");
}

/**
 * 送餐时间条件筛选
 * @param type
 * @param element
 */
function setTimeType(type,element){
	defualtTimeTypeClass();
	//预订
	if(type == "0"){
		$(element).removeClass().addClass("yuding yudingons");
	}
	//15分钟
	if(type == "15"){
		$(element).removeClass().addClass("shiwufen shiwufenons");
	}
	//30分钟
	if(type == "30"){
		$(element).removeClass().addClass("sanshifen sanshifenons");
	}
	//更久
	if(type == "-1"){
		$(element).removeClass().addClass("gengjiu gengjiuons");
	}
	$("#sendTimeType").val(type);
	searchTakeout();
}

/**
 * 外卖状态筛选
 */
function setStatus(billStatus,element,statusDesc){
	defualtStatusClass();
	$(element).removeClass().addClass("zt_quanbu_on");
	$("#billStatus").val(billStatus);
	$("#billStatusDT").html("状态过滤－" + statusDesc);
	searchTakeout();
}

/**
 * 关键字筛选
 * @param keywords
 */
function setKeywords(){
	var keywords = $("#inputKeywords").val();
	$("#keywords").val(keywords);
	searchTakeout();
}

/**
 * 选中外卖单
 */
function tableDivClick(event,element){
	event=event?event:window.event;
    event.cancelBubble=true;
    //左击，不显示右击菜单
    if(event.which == 1){
    	event.stopPropagation();
    	if($("#contextmenu").attr("id")){
    		$("#contextmenu").remove();
    	}
    }
    
    var div = $(element);
    if(!div.attr("p")){
    	div = div.parents("div[id='takeoutDiv']");
	}
    
	var currentClass = div.attr("class");
	var billStatus = div.attr("billStatus");
	if(div.hasClass("on")){
		funButtonStatus(billStatus);
		return true;
	}
	
	var currentClass = div.attr("class");
	defualtTakeoutClass();
	if(currentClass == 'yudingzhong'){
		div.removeClass().addClass("yudingzhong on");
	}
	if(currentClass == 'paisong'){
		div.removeClass().addClass("paisong on");
	}
	if(currentClass == 'weixiadan'){
		div.removeClass().addClass("weixiadan on");
	}
	if(currentClass == 'yixiadan'){
		div.removeClass().addClass("yixiadan on");
	}
	if(currentClass == 'yijiezhang'){
		div.removeClass().addClass("yijiezhang on");
	}
	var tId = div.attr("tId");
	var billId = div.attr("billId");
	$("#tId").val(tId);
	$("#billId").val(billId);
	funButtonStatus(billStatus);
	return true;
}

/**
 * 右击菜单
 */
function bindContentmenu(){
	$('div',"#takeoutListDiv").contextmenu({
		items : [{
			text :'修改',
			style:'index_xiugai',
			action: function(event,target){
				xinzeng(1);
			}
		},{
			text :'点餐',
			style:'index_diancai',
			action: function(event,target){
				diancai();
			}
		},{
            text :'下单',
			style:'index_xiadan',
            action: function(event,target){
            	xiadan();
            }
        },{
            text :'撤单',
			style:'index_chedan',
            action: function(event,target){
            	chedan();
            }
        },{
            text :'结账',
			style:'index_jiezhang',
            action: function(event,target){
            	jiezhang();
            }
        },{
            text :'打印',
			style:'index_dayin',
            action: function(event,target){
            	dayin();
            }
        }
		]
	});	
}

/**
 * 设置功能区按钮状态
 */
function funButtonStatus(billStatus){
	//默认值
	if(billStatus == "default"){
		$("#diancai").removeClass().addClass("diancai_no");//点菜
		$("#xiadan").removeClass().addClass("yuding_no");//下单
		$("#chedan").removeClass().addClass("jiezhang_no");//撤单
		$("#paisong").removeClass().addClass("cuicai_no");//派送
		$("#jiezhang").removeClass().addClass("qingtai_no");//结账
	}
	//新增外卖，未产生账单
	if(billStatus == ""){
		$("#diancai").removeClass().addClass("diancai");//点菜
		$("#xiadan").removeClass().addClass("yuding_no");//下单
		$("#chedan").removeClass().addClass("jiezhang");//撤单
		$("#paisong").removeClass().addClass("cuicai_no");//派送
		$("#jiezhang").removeClass().addClass("qingtai_no");//结账
		//右击菜单样式控制
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai");//修改
			$("#index_diancai").removeClass().addClass("index_diancai");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan_no");//下单
			$("#index_chedan").removeClass().addClass("index_chedan");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang_no");//结账
			$("#index_dayin").removeClass().addClass("index_dayin_no");//打印
		}
	}
	//未下单
	if(billStatus == 1){
		$("#diancai").removeClass().addClass("diancai");//点菜
		$("#xiadan").removeClass().addClass("yuding");//下单
		$("#chedan").removeClass().addClass("jiezhang");//撤单
		$("#paisong").removeClass().addClass("cuicai");//派送
		$("#jiezhang").removeClass().addClass("qingtai");//结账
		//右击菜单样式控制
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai");//修改
			$("#index_diancai").removeClass().addClass("index_diancai");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan");//下单
			$("#index_chedan").removeClass().addClass("index_chedan");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang");//结账
			$("#index_dayin").removeClass().addClass("index_dayin_no");//打印
		}
	}
	//已下单
	if(billStatus == 2){
		$("#diancai").removeClass().addClass("diancai");//点菜
		$("#xiadan").removeClass().addClass("yuding_no");//下单
		$("#chedan").removeClass().addClass("jiezhang");//撤单
		$("#paisong").removeClass().addClass("cuicai");//派送
		$("#jiezhang").removeClass().addClass("qingtai");//结账
		//右击菜单样式控制
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai");//修改
			$("#index_diancai").removeClass().addClass("index_diancai");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan_no");//下单
			$("#index_chedan").removeClass().addClass("index_chedan");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang");//结账
			$("#index_dayin").removeClass().addClass("index_dayin");//打印
		}
	}
	//已结账
	if(billStatus == 3){
		$("#diancai").removeClass().addClass("diancai_no");//点菜
		$("#xiadan").removeClass().addClass("yuding_no");//下单
		$("#chedan").removeClass().addClass("jiezhang_no");//撤单
		$("#paisong").removeClass().addClass("cuicai_no");//派送
		$("#jiezhang").removeClass().addClass("qingtai_no");//结账
		//右击菜单样式控制
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai_no");//修改
			$("#index_diancai").removeClass().addClass("index_diancai_no");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan_no");//下单
			$("#index_chedan").removeClass().addClass("index_chedan_no");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang_no");//结账
			$("#index_dayin").removeClass().addClass("index_dayin");//打印
		}
	}
	//反结账
	if(billStatus == 4){
		$("#diancai").removeClass().addClass("diancai");//点菜
		$("#xiadan").removeClass().addClass("yuding");//下单
		$("#chedan").removeClass().addClass("jiezhang");//撤单
		$("#paisong").removeClass().addClass("cuicai");//派送
		$("#jiezhang").removeClass().addClass("qingtai");//结账
		//右击菜单样式控制
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai");//修改
			$("#index_diancai").removeClass().addClass("index_diancai");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan");//下单
			$("#index_chedan").removeClass().addClass("index_chedan");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang");//结账
			$("#index_dayin").removeClass().addClass("index_dayin");//打印
		}
	}
	//撤单
	if(billStatus == 8){
		$("#diancai").removeClass().addClass("diancai_no");//点菜
		$("#xiadan").removeClass().addClass("yuding_no");//下单
		$("#chedan").removeClass().addClass("jiezhang_no");//撤单
		$("#paisong").removeClass().addClass("cuicai_no");//派送
		$("#jiezhang").removeClass().addClass("qingtai_no");//结账
		//右击菜单样式控制
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai_no");//修改
			$("#index_diancai").removeClass().addClass("index_diancai_no");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan_no");//下单
			$("#index_chedan").removeClass().addClass("index_chedan_no");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang_no");//结账
			$("#index_dayin").removeClass().addClass("index_dayin_no");//打印
		}
	}
	//部分下单
	if(billStatus == 9){
		$("#diancai").removeClass().addClass("diancai");//点菜
		$("#xiadan").removeClass().addClass("yuding");//下单
		$("#chedan").removeClass().addClass("jiezhang");//撤单
		$("#paisong").removeClass().addClass("cuicai");//派送
		$("#jiezhang").removeClass().addClass("qingtai");//结账
		//右击菜单样式控制
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai");//修改
			$("#index_diancai").removeClass().addClass("index_diancai");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan");//下单
			$("#index_chedan").removeClass().addClass("index_chedan");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang");//结账
			$("#index_dayin").removeClass().addClass("index_dayin");//打印
		}
	}
	//派送中
	if(billStatus == 11){
		$("#diancai").removeClass().addClass("diancai_no");//点菜
		$("#xiadan").removeClass().addClass("yuding_no");//下单
		$("#chedan").removeClass().addClass("jiezhang");//撤单
		$("#paisong").removeClass().addClass("cuicai_no");//派送
		$("#jiezhang").removeClass().addClass("qingtai");//结账
		if($("#contextmenu").attr("id")){
			$("#index_xiugai").removeClass().addClass("index_xiugai");//修改
			$("#index_diancai").removeClass().addClass("index_diancai_no");//点餐
			$("#index_xiadan").removeClass().addClass("index_xiadan_no");//下单
			$("#index_chedan").removeClass().addClass("index_chedan");//撤单
			$("#index_jiezhang").removeClass().addClass("index_jiezhang");//结账
			$("#index_dayin").removeClass().addClass("index_dayin");//打印
		}
	}
}

/**
 * 恢复外卖单默认样式
 */
function defualtTakeoutClass(){
	$(".on").each(function(){
		$(this).removeClass("on");
	});
}

/**
 * 恢复时间选择默认样式
 */
function defualtTimeTypeClass(){
	$("#timeAll").removeClass().addClass("quanbu");
	$("#time0").removeClass().addClass("yuding");
	$("#time15").removeClass().addClass("shiwufen");
	$("#time30").removeClass().addClass("sanshifen");
	$("#timeLong").removeClass().addClass("gengjiu");
}

/**
 * 恢复外卖状态选择默认样式
 */
function defualtStatusClass(){
	$(".zt_quanbu_on:first").removeClass().addClass("zt_quanbu");
}

/**
 * 外卖模块快捷键
 */
var hotKey = null;
function hotKeyForTakeout(){
	hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			var isFocus=$("#inputKeywords").is(":focus");
			if(isFocus){//触发检索按钮的点击事件
				$("#inputKeywordsBut").click();
			}
		}
	},{
		keyCode:1,
		callBackFunction:function()
		{
			var isFocus=$("#inputKeywords").is(":focus");
			if(isFocus){
				return;
			}
			$("#xinzeng").click();
		}
	},{
		keyCode:2,
		callBackFunction:function()
		{
			var isFocus=$("#inputKeywords").is(":focus");
			if(isFocus){
				return;
			}
			$("#diancai").click();
		}
	},{
		keyCode:3,
		callBackFunction:function()
		{
			var isFocus=$("#inputKeywords").is(":focus");
			if(isFocus){
				return;
			}
			$("#xiadan").click();
		}
	},{
		keyCode:4,
		callBackFunction:function()
		{
			var isFocus=$("#inputKeywords").is(":focus");
			if(isFocus){
				return;
			}
			$("#chedan").click();
		}
	},{
		keyCode:5,
		callBackFunction:function()
		{
			var isFocus=$("#inputKeywords").is(":focus");
			if(isFocus){
				return;
			}
			$("#paisong").click();
		}
	},{
		keyCode:6,
		callBackFunction:function()
		{
			var isFocus=$("#inputKeywords").is(":focus");
			if(isFocus){
				return;
			}
			$("#jiezhang").click();
		}
	},
	{
		keyCode:"shift_tab",
		callBackFunction:function()
		{
			window.location=window.ctx+'/bill/list';
		}
	}
	],0);
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

/**
 * 自适应浏览器宽度
 */
function initWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - window.initFrameSize_subWidth;
	if(subWidth > 0)
	{
		$(".main_nav").animate({width:806+subWidth},window.animate_speed); 
		$(".lefts").animate({width:795+subWidth},window.animate_speed); 
		$(".main_nav").animate({width:807+subWidth},window.animate_speed);
		$(".search_wrap").animate({width:795+subWidth},window.animate_speed); 
	}
	
	var subHeight = (windowAreaParams.screenHeight - 768) < 0 ? 0 : (windowAreaParams.screenHeight - 768);
	if(subHeight > 0)
	{
		//		$(".lefts").css("height",517+subHeight+"px");
		$(".index_box").css("height",600+subHeight+"px");
	}
}
