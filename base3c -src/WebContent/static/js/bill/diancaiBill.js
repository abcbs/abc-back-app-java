
//定义setTimeout执行方法
var TimeFn = null;

$(document).ready(function(){
	//异步请求
	var billId = $("#currentBillId").val();
	BillChange(billId);
});

var isShiftAndEnter = false;


function tabChange(tabli,url)
{
	var billId = $("#currentBillId").val();
	var currentBillType = $("#currentBillType").val();
	
	if(billId)
	{
		var tab = $(tabli); 
		var jump = url + "?billId=" + billId;
		if(url.indexOf("payPage") >= 0)
		{
			jump = url + "/" + billId;
		}
		window.location = jump;
	}
	else if(currentBillType == 2)
	{
		var tab = $(tabli); 
		var jump = url;
		if(url.indexOf("payPage") >= 0)
		{
			toastr.info("请选择账单");
			return;
		}
		window.location = jump;
	}
	else
	{
		toastr.info("请选择账单");
	}
}


function initDishes()
{
	var billList = $("#billList");
	billList.find("tr").click(function(e){
		var tr = $(this);
		var isSet = tr.attr("isSet");
		$("#isSet").val(isSet);
		if(!tr.hasClass("on_tr"))
		{
			tr.parent("tbody").find("tr").removeClass("on_tr");
			tr.addClass("on_tr");
			
			var billStatus =$("#billStatus").val();
			if(billStatus == 3 || billStatus == 8)
			{
				//结账，反结账，撤单，不能操作
				return;
			}
			
			
			var dishesStatus = tr.attr("dishesStatus");
			
			if(dishesStatus == '1' || dishesStatus == '2')
			{
				//未上菜，催菜，退菜等
				$("#newDishWindow").hide();
				var bdId = tr.attr("bdId");
				xiadanWindow(tr,e,'xiadanWindow',bdId,isSet);
			}
			else if(dishesStatus == '5')
			{
				//未下单
				$("#xiadanWindow").hide();
				var bdId = tr.attr("bdId");
				newDishWindow(tr,e,'newDishWindow',bdId);
			}
			else
			{
				$("#xiadanWindow").hide();
				$("#newDishWindow").hide();
			}
		}
		else
		{
			$("#xiadanWindow").hide();
		}
	});
	
	//备注
	$("#beizhu").click(function(){
		var billStatus =$("#billStatus").val();
		if(billStatus == 3 || billStatus == 8 || billStatus == 2)
		{
			//已下单，结账，反结账，撤单，不能操作
			toastr.warning("不能添加烹饪备注!");
			return;
		}
		var billId = $("#currentBillId").val();
		if(billId)
		{
			popForm('烹饪备注',window.ctx+'/bill/pop/cooking/update/'+billId,'880','644');
		}
		else
		{
			toastr.warning("请先点菜!");
		}
		
	});
	
	//初始化分页
	domPage($("#diancaiBillContentDishesTr"),"tr",PageSizeUtil.getSizeByType('bill'));
	if("undefined" != typeof initSelectedDish)
	{
		if(initSelectedDish)
		{
			initSelectedDish();
		}
	}
}

/**
 * 已下单弹出框
 * @param tr
 * @param e
 * @param id
 * @param bdId
 * @param isSet
 */
function xiadanWindow(tr,e,id,bdId,isSet)
{
	var unitType = tr.attr("unitType");
	//称重类
	if(unitType == '1'){
		$("#dishXiugai").removeClass().addClass("but_czjl");
	}else{
		$("#dishXiugai").removeClass().addClass("but_czjl_no");
	}
	$("#dishCuicai").unbind("click");
	$("#dishHuacai").unbind("click");
	$("#dishZengcai").unbind("click");
	$("#dishTuicai").unbind("click");
	$("#dishXiugai").unbind("click");
	$("#dishCuicai").click(function(){
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/dishCuicai/"+bdId+"?isSet="+isSet,
		    data:null,
		    cache:false,
		    success:function(data){
		    	$("#"+id).hide();
		    	toastr.success(data.message);
		    	tr.removeClass("on_tr");
		    	refreshDiancaiDishFunction();
		    },
			error:function(){}
		  });
	});
	$("#dishHuacai").click(function(){
		var dname = tr.attr("dishesName");
		dialogBoxConfirm("划   菜","确定‘    "+dname+"  ’已经上菜了吗？",function(){
			$.ajax({
			    type:"get",
			    url:window.ctx+"/bill/dishHuacai/"+bdId+"?isSet="+isSet,
			    data:null,
			    cache:false,
			    success:function(data){
			    	if(data.statusCode != 200)
			    	{
			    		toastr.error(data.message);
			    	}
			    	var ibillId = $("#currentBillId").val();
			    	BillChange(ibillId);
			    },
				error:function(){}
			  });
		});
		
	});
	
	
	var discountType = tr.attr("discountType");
	if(discountType == 3)
	{
		//赠菜
		$("#dishZengcai").removeClass("but_zengcai").addClass("but_zengcai_no");
	}
	else
	{
		$("#dishZengcai").removeClass("but_zengcai_no").addClass("but_zengcai");
	}
	
	$("#dishZengcai").click(function(){
		if(getPermissionNoToast("zengcai"))
		{
			diancaiBill_zengcai(bdId,isSet);
		}
		else
		{
			$("#"+id).hide();
			var ids = bdId+"," + isSet;
			popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=5&functionId='+ids,'529','353');
		}
		
	});
	
	$("#dishTuicai").click(function(){
		if(getPermissionNoToast("tuicai")){
			popForm('退菜原因',window.ctx+'/bill/pop/tuicaiReason/'+bdId+"?isSet="+isSet,'529','644');
		}
		else{
			//套餐
			if(isSet == '1'){
				popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=4&functionId='+bdId,'529','353');
			}else{
				popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=3&functionId='+bdId,'529','353');
			}
		}
		
	});
	
	var unitName = tr.attr("unitName");//菜肴单位名称
	$("#oldDishEditNum").next("span").html(unitName);
	
	$("#dishXiugai").click(function(e){
		$("#xiadanWindow").hide();
		if($("#dishXiugai").hasClass("but_czjl_no")){
			return;
		}
		newDishEditWindow(tr,e,'newDishEditWindow',bdId)
	});
	
	showFloat(e,'xiadanWindow',tr);
}

/**
 * 未下单菜肴 双击菜肴
 */
function dbcAddDishes(div,e)
{
	 clearTimeout(TimeFn);
	var id = "newDishWindow";
	var dish = $(div);//触发双击事件的菜肴对象
	
	//使允许操作的功能层显示出来
	$("#fourButtons").hide();
	$("#fourButtons").find("a").each(function(index){
		if(index == 0){
			$(this).attr("id","");
		}else if(index == 1){
			$(this).attr("id","");
		}else if(index == 2){
			$(this).attr("id","");
		}else if(index == 3){
			$(this).attr("id","");
		}
	});
	$("#threeButtons").show();
	$("#threeButtons").height(10);
	$("#threeButtons").find("a").each(function(index){
		if(index == 0){
			$(this).attr("id","newDishBeizhu");
			$(this).hide();
		}else if(index == 1){
			$(this).attr("id","newDishZengcai");
			$(this).hide();
		}else if(index == 2){
			$(this).attr("id","newDishDelete");
			$(this).hide();
		}
	});
	//初始化
	$("#newDishNum").val(1);
	$("#oldDishNum").val(1);
	
	var unitName = dish.find("#unitName").text();//菜肴单位名称
	$("#oldDishNum").next("span").html(unitName);
	
	$("#newDishWindow").click(function(e){
		e.stopPropagation();
	});
	
	$("#newDishDelete").unbind("click");
	$("#newDishCancel").unbind("click");
	$("#newDishConfirm").unbind("click");
	$("#newDishAdd").unbind("click");
	$("#newDishSub").unbind("click");
	$("#newDishBeizhu").unbind("click");
	$("#newDishZengcai").unbind("click");
	$("#editSet").unbind("click");
	
	$("#newDishNum").sudokuShow();
	
	
	//删除按钮不允许使用	
	
	//取消按钮的点击事件
	$("#newDishCancel").click(function(){
		$("#"+id).hide();
	});
	$("#newDishConfirm").click(function(){
		var newDishNum = $("#newDishNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		if(newDishNum <= 0)
		{
			toastr.warning("菜品数量应该大于0!");
			return;
		}
		var dishesId = dish.find("#dishesId").text();
		var ibillId = $("#billId").val();
		$("#"+id).hide();
		addDishes(div,newDishNum);//订单添加菜肴
	});
	
	$("#newDishAdd").click(function(){
		var newDishNum = $("#newDishNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		newDishNum = Number(newDishNum)+Number(1);
		newDishNum = newDishNum.toFixed(1);
		var intNewdishNum = parseInt(newDishNum);
		if(Number(newDishNum) == intNewdishNum){
			newDishNum = intNewdishNum;
		}
		$("#newDishNum").val(newDishNum);
	});
	$("#newDishSub").click(function(){
		var newDishNum = $("#newDishNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		if(newDishNum > 1)
		{
			newDishNum = Number(newDishNum)-Number(1);
			newDishNum = newDishNum.toFixed(1);
			var intNewdishNum = parseInt(newDishNum);
			if(Number(newDishNum) == intNewdishNum){
				newDishNum = intNewdishNum;
			}
			$("#newDishNum").val(newDishNum);
		}
	});
	$('#newDishNum').keydown(function(d){
		if(d.keyCode == 13){
			$("#newDishConfirm").click();
			$("#keywords").focus();
		}
	});
	
	showFix(e,id,null);
}

/**
 * 套餐/菜肴添加数量
 */
function addDishSet(ibillId,bdId,newNum,isSet,isJudgeRm,oldNum,id){
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/dishNumChange/"+ibillId+"/"+bdId + "?isSet=" + isSet+"&isJudgeRm="+isJudgeRm+"&oldDishNum="+oldNum+"&newDishNum="+newNum,
	    data:null,
	    cache:false,
	    success:function(data){
	    	//隐藏newDishWindow层，貌似没用，先保留
	    	if(id != ""){
	    		$("#"+id).hide();
	    	}
	    	if(isJudgeRm == 0){
	    		if(data.messageMap.result == 0){
	    			dialogBoxConfirm('点餐',data.messageMap.message,
	    					function(){
	    				      addDishSet(ibillId,bdId,newNum,isSet,"1",oldNum);
	    					}
	    			,function(){});
		    	}else if(data.messageMap.result == 4){
		    		toastr.warning(data.messageMap.message);
		    		
		    	}else{
		    		addDishSet(ibillId,bdId,newNum,isSet,"1",oldNum);
		    	}
	    	}else{
	    		if(data.messageMap.result == 1){
		    		toastr.warning(data.messageMap.message);
		    	}
		    	BillChange(ibillId);
		    	refreshPayPageFunction();
		    	refreshDiancaiDishFunction();
	    	}	
	    }
	})
}
/**
 * 未下单菜肴
 */
function newDishWindow(tr,e,id,bdId)
{
	$("#threeButtons").height("84");//恢复弹窗的三个功能按钮层的高度
	
	var isSet = tr.attr("isSet");
	if(isSet == "1"){
		$("#fourButtons").show();
		$("#fourButtons").find("a").each(function(index){
			if(index == 0){
				$(this).attr("id","newDishBeizhu");
				$(this).show();
			}else if(index == 1){
				$(this).attr("id","newDishZengcai");
				$(this).show();
			}else if(index == 2){
				$(this).attr("id","editSet");
				$(this).show();
			}else if(index == 3){
				$(this).attr("id","newDishDelete");
				$(this).show();
			}
		});
		$("#threeButtons").hide();
		$("#threeButtons").find("a").each(function(index){
			if(index == 0){
				$(this).attr("id","");
				$(this).show();
			}else if(index == 1){
				$(this).attr("id","");
				$(this).show();
			}else if(index == 2){
				$(this).attr("id","");
				$(this).show();
			}
		});
	}else{
		$("#fourButtons").hide();
		$("#fourButtons").find("a").each(function(index){
			if(index == 0){
				$(this).attr("id","");
				$(this).show();
			}else if(index == 1){
				$(this).attr("id","");
				$(this).show();
			}else if(index == 2){
				$(this).attr("id","");
				$(this).show();
			}else if(index == 3){
				$(this).attr("id","");
				$(this).show();
			}
		});
		$("#threeButtons").show();
		$("#threeButtons").find("a").each(function(index){
			if(index == 0){
				$(this).attr("id","newDishBeizhu");
				$(this).show();
			}else if(index == 1){
				$(this).attr("id","newDishZengcai");
				$(this).show();
			}else if(index == 2){
				$(this).attr("id","newDishDelete");
				$(this).show();
			}
		});
	}
	//初始化
	var orderNum = tr.attr("orderNum");
	$("#newDishNum").val(orderNum);
	$("#oldDishNum").val(orderNum);
	
	var unitName = tr.attr("unitName");
	$("#oldDishNum").next("span").html(unitName);
	
	$("#newDishWindow").click(function(e){
		e.stopPropagation();
	});
	
	$("#newDishDelete").unbind("click");
	$("#newDishCancel").unbind("click");
	$("#newDishConfirm").unbind("click");
	$("#newDishAdd").unbind("click");
	$("#newDishSub").unbind("click");
	$("#newDishBeizhu").unbind("click");
	$("#newDishZengcai").unbind("click");
	$("#editSet").unbind("click");
	
	$("#newDishNum").sudokuShow();
	
	$("#newDishDelete").click(function(){
		var ibillId = $("#currentBillId").val();
		var dishesId = tr.attr("id");
		var dname = tr.attr("dishesName");
		
		if(dishesId)
		{
			$("#"+id).hide();
			$.ajax({
			    type:"get",
			    url:window.ctx+"/bill/dishDelete/"+ibillId+"/"+bdId + "?isSet=" + isSet,
			    data:null,
			    cache:false,
			    success:function(data){
			    	var ibillId = $("#currentBillId").val();
			    	BillChange(ibillId);
			    	refreshDiancaiDishFunction();
			    	refreshPayPageFunction();
			    },
				error:function(){}
			  });	
		}
	});
	$("#newDishCancel").click(function(){
		$("#"+id).hide();
		tr.removeClass("on_tr");
	});
	$("#newDishConfirm").click(function(){
		var newDishNum = $("#newDishNum").val();
		var isSet = tr.attr("isSet");//套餐标志
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		//isSet = 0;
		if(newDishNum <= 0)
		{
			toastr.warning("菜品数量应该大于0!");
			return;
		}
		var dishesId = tr.attr("id");
		var ibillId = $("#currentBillId").val();
		var oldDishNum = $("#oldDishNum").val();
		
		//套餐或者菜肴数量修改函数
		addDishSet(ibillId,bdId,newDishNum,isSet,"0",oldDishNum,id);
		
	});
	
	$("#newDishBeizhu").click(function(){	
		$("#"+id).hide();
		var isSet = tr.attr("isSet");
		popForm('烹饪备注',window.ctx+'/bill/pop/dishCooking/update/' + bdId + "?isSet=" + isSet,'880','644');
	});
	
	$("#editSet").click(function(){
		$("#"+id).hide();
		var billId = $("#billId").val();
		var dsId = tr.attr("dsId");
		popForm('套餐',window.ctx+'/bill/pop/addDishesSet?billId='+billId+'&dsId='+dsId+"&type=editSet"+"&bdId="+bdId,'880','644');
	});
	
	var discountType = tr.attr("discountType");
	if(discountType == 3)
	{
		//赠菜
		$("#newDishZengcai").text("取消赠菜");
	}
	else
	{
		$("#newDishZengcai").text("赠菜");
	}
	
	
	$("#newDishZengcai").click(function(){
		var isSet = tr.attr("isSet");
		if(getPermissionNoToast("zengcai"))
		{
			diancaiBill_zengcai(bdId,isSet);
		}
		else
		{
			$("#"+id).hide();
			var ids = bdId+"," + isSet;
			popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=5&functionId='+ids,'529','353');
		}
	});
	
	$("#newDishAdd").click(function(){
		var newDishNum = $("#newDishNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		newDishNum = Number(newDishNum)+Number(1);
		newDishNum = newDishNum.toFixed(1);
		var intNewdishNum = parseInt(newDishNum);
		if(Number(newDishNum) == intNewdishNum){
			newDishNum = intNewdishNum;
		}
		$("#newDishNum").val(newDishNum);
	});
	$("#newDishSub").click(function(){
		var newDishNum = $("#newDishNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		if(newDishNum > 1)
		{
			newDishNum = Number(newDishNum)-Number(1);
			newDishNum = newDishNum.toFixed(1);
			var intNewdishNum = parseInt(newDishNum);
			if(Number(newDishNum) == intNewdishNum){
				newDishNum = intNewdishNum;
			}
			$("#newDishNum").val(newDishNum);
		}
	});
	$('#newDishNum').keydown(function(d){
		if(d.keyCode == 13){
			$("#newDishConfirm").click();
			$("#keywords").focus();
		}
	});
	
	showFix(e,id,tr);
	$("#newDishNum").focus();
}

/**
 * 刷新支付页面的方法
 */
function refreshPayPageFunction()
{
	if("undefined" == typeof refreshPayPage)
	{
		
	}
	else
	{
		if(refreshPayPage)
		{
			refreshPayPage();
		}
	}
}
function refreshDiancaiDishFunction()
{
	if("undefined" == typeof refreshDish)
	{
		
	}
	else
	{
		if(refreshDish)
		{
			refreshDish();
		}
	}
}

/**
 * 已下单菜肴
 */
function newDishEditWindow(tr,e,id,bdId)
{
	//初始化
	var orderEditNum = tr.attr("orderNum");
	$("#newDishEditNum").val(orderEditNum);
	$("#oldDishEditNum").val(orderEditNum);
	
	$("#newDishEditWindow").click(function(e){
		e.stopPropagation();
	});
	
	$("#newDishEditCancel").unbind("click");
	$("#newDishEditConfirm").unbind("click");
	$("#newDishEditAdd").unbind("click");
	$("#newDishEditSub").unbind("click");
	$("#newDishEditNum").sudokuShow();
	
	/**
	 * 解决快捷键冲突问题
	 */
	$('#newDishEditNum').keydown(function(d){
		d.stopPropagation();
		if(d.keyCode == 13){
			$("#newDishEditConfirm").click();
			$("#keywords").focus();
		}
	});
	
	$("#newDishEditCancel").click(function(){
		$("#"+id).hide();
		tr.removeClass("on_tr");
	});
	$("#newDishEditConfirm").click(function(){
		var isSet = $("#isSet").val();
		var newDishNum = $("#newDishEditNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		if(newDishNum <= 0)
		{
			toastr.warning("菜品数量不能为0!");
			return;
		}
		var dishesId = tr.attr("id");
		var ibillId = $("#currentBillId").val();
		var oldDishNum = $("#oldDishEditNum").val();
		
		//套餐或者菜肴数量修改函数
		addDishSet(ibillId,bdId,newDishNum,isSet,"0",oldDishNum,id);
//		$.ajax({
//		    type:"get",
//		    url:window.ctx+"/bill/dishNumChange/"+ibillId+"/"+bdId+"/"+num+"?isSet="+isSet,
//		    data:null,
//		    cache:false,
//		    success:function(data){
//		    	$("#"+id).hide();
//		    	var ibillId = $("#currentBillId").val();
//		    	BillChange(ibillId);
//		    	refreshPayPageFunction();
//		    	refreshDiancaiDishFunction();
//		    },
//			error:function(){}
//		  });
	});
	
	
	$("#newDishEditAdd").click(function(){
		var newDishNum = $("#newDishEditNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		newDishNum = Number(newDishNum)+Number(1);
		newDishNum = newDishNum.toFixed(1);
		var intNewdishNum = parseInt(newDishNum);
		if(Number(newDishNum) == intNewdishNum){
			newDishNum = intNewdishNum;
		}
		$("#newDishEditNum").val(newDishNum);
		
	});
	$("#newDishEditSub").click(function(){
		var newDishNum = $("#newDishEditNum").val();
		if(isNaN(newDishNum)){
			toastr.error("请输入正确数值");
			return;
		}
		if(newDishNum > 1)
		{
			newDishNum = Number(newDishNum)-Number(1);
			newDishNum = newDishNum.toFixed(1);
			var intNewdishNum = parseInt(newDishNum);
			if(Number(newDishNum) == intNewdishNum){
				newDishNum = intNewdishNum;
			}
			$("#newDishEditNum").val(newDishNum);
		}
	});
	
	showFix(e,id,tr);
	$("#newDishEditNum").focus();
}


function BillChange(billId,tr)
{
//	ProgressbarUtil.show("ajaxBillContent",670);
	var billType = $("#currentBillType").val();
	ajaxUtil.getUrlContent(window.ctx+'/bill/ajax/diancaiBillContent?billId='+billId+'&billType='+billType,"ajaxBillContent",function(){
		initDishes();
    	xiadan();
    	quxiao();
    	$("#currentBillId").val(billId);
    	
    	if(isShiftAndEnter)
    	{
    		$("#billList").find("tbody").find("tr[bdId]").last().click();
    		isShiftAndEnter = false;
    	}
    	
    	
    	if(tr)
    	{
    		$(tr).parent("tbody").find("tr").removeClass("on_tr");
    		$(tr).addClass("on_tr");
    	}
    	refreshPayPageFunction();
    	
    	domPage($("#diancaiBillContentDishesTr"),"tr",PageSizeUtil.getSizeByType('bill'),0,null);
    	kexian($("#payableCost_hidden").val(),'1');
	});
}


var synAddDishFlag = false;

function addDishes(div,dishNum){
	
	var time = 200;//延迟时间
	if(dishNum != null){
		time = 0;
	}
	// 取消上次延时未执行的方法
    clearTimeout(TimeFn);
    //执行延时
    TimeFn = setTimeout(function(){
        //do function在此处写单击事件要执行的代码
    	if(!synAddDishFlag)
    	{
    		synAddDishFlag = true;
    		var dish = $(div);
    		//判断是否是时价菜
    		var isRulingPrice = dish.attr("isRulingPrice");
    		
    		//判断是否可以外卖
    		var billType = $("#billType").val();
    		var isTakeout = dish.attr("isTakeout");
    		
    		if(billType == 2)
    		{
    			//外卖单，只能点外卖菜品
    			if(!isTakeout || isTakeout == 0)
    			{
    				toastr.warning("外卖单不能点非外卖菜品!");
    				synAddDishFlag = false;
    				return;
    			}
    		}
    		
    		
    		
    		//判断沽清数量
    		var estimate = dish.attr("estimate");
    		if(dishNum == null){//如果不是带数量点餐
    			dishNum = 1;
    		}
    		//有沽清数量的
    		if(estimate && parseInt(estimate) < parseInt(dishNum))
    		{
    			toastr.warning("此菜品已经沽清!");
    			synAddDishFlag = false;
    			return;
    		}
    		
    		var dishesId = dish.find("#dishesId").text();
    		var dishesName = dish.find("#dishesName").text();
    		var orderNum = dish.find("#orderNum").text();
    		var dishesCode = dish.find("#dishesCode").text();
    		var unitName = dish.find("#unitName").text();
    		var price = dish.find("#price").text();
    		
    		
    		var billStatus =$("#billStatus").val();
    		if(billStatus == 3 || billStatus == 8)
    		{
    			//结账，反结账，撤单，不能再加菜
    			toastr.warning("此账单已完结，不能再加菜!");
    			synAddDishFlag = false;
    			return;
    		}
    		
    		if(isRulingPrice && isRulingPrice == '1')
    		{
    			var currentBillId = $("#currentBillId").val();
    			popForm('时价菜',window.ctx+'/bill/pop/rulingPrice/'+dishesId+'?billId='+currentBillId+'&billType='+billType +"&dishNum="+dishNum,'600','644');
    			synAddDishFlag = false;
    		}
    		else
    		{
    			var total = parseInt(orderNum) * parseFloat(price);
    			
    			//加菜
    			var currentBillType = $("#currentBillType").val();
    			var currentBillId = $("#currentBillId").val();
    			
    			var ibillId = $("#currentBillId").val();
    			//先判断原料库存，符合修改库存沽清
    		
    			asyncJiacai(div,currentBillType,currentBillId,dishesId,ibillId,dish,dishNum,"0");
    		}
    		
    	}
    	else
    	{
    		toastr.info("点菜太快了，稍等片刻");
    	}
    },time);
}

/**
 * 异步加菜
 */
function asyncJiacai(div,currentBillType,currentBillId,dishesId,ibillId,dish,dishNum,isJudgeRm)
{
	//加一效果
		$.tipsBox({
			obj: $(div),
			str: "+"+dishNum,
            callback: function() {
            }
		});

	
	var tId = $("#tId").val();
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/jiacai/"+currentBillType+"/"+dishesId+"?billId="+currentBillId+"&dishNum="+dishNum+"&tId="+tId+"&isJudgeRm="+isJudgeRm,
	    data:null,
	    cache:false,
	    success:function(data){
	    	synAddDishFlag = false;
	    	toLastPage = true;
	    	//第一次外卖需要处理
	    	if(!$("#currentBillId").val())
	    	{
	    		$("#currentBillId").val(data.value);
	    		$("#billId").val(data.value);
	    		$("#billType").val(data.type);
	    	}
	    	$("#isCanPlace").val(1);
    		if(data.callbackType)
    		{
    			$("#diancaiBillContentBillNo").text(data.callbackType);
    		}
	    	
	    	if(isJudgeRm == 0 && data.messageMap && data.messageMap.result){
	    		if(data.messageMap.result == 0){//原料库存不足，是否继续
	    			dialogBoxConfirm('点餐',data.messageMap.message+"    是否继续?",
	    					function(){
	    						asyncJiacai(div,currentBillType,currentBillId,dishesId,ibillId,dish,dishNum,"1")
	    					}
	    			,function (){});
	    			return;
		    	}else if(data.messageMap.result == 1){	//原料库存预警，请及时补充原料
//		    		toastr.warning("原料库存预警，请及时补充原料!");
		    	}else if(data.messageMap.result == 4){	//此菜品数量不足
		    		if(estimate == '0'){
		    			dish.removeClass().addClass("diancan_red_order");
		    		}
		    		toastr.warning("此菜品数量不足!");
		    		return;
		    	}
	    	}
	    	addBillContentDish(data.forwardUrl,data.messageMap.bdid);
    		var estimate = data.sign;
	    	var dsId = data.rel;
	    	
	    	dish.attr("estimate",estimate);
	    	dish.find("#estimate").text(estimate);
	    	if(estimate >0 && estimate < 10)
	    	{
	    		dish.removeClass().addClass("diancan_yellow_order");
	    	}
	    	else if(estimate == '0')
	    	{
	    		dish.removeClass().addClass("diancan_red_order");
	    	}
	    	else{
	    		dish.removeClass().addClass("diancan_green_order");
	    	}
	    	if(data.messageMap.result == 1){
	    		toastr.warning(data.messageMap.message);
	    	}
	    	$("#keywords").val("");
	    },
		error:function(){
			synAddDishFlag = false;
		}
	})
}
	
	
function addBillContentDish(insertHtml,bdId)
{
	domPage($("#diancaiBillContentDishesTr"),"tr",PageSizeUtil.getSizeByType('bill'),null,insertHtml);
	//更新价格
	var billId = $("#currentBillId").val();
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/calculatorBill/"+billId,
	    data:null,
	    cache:false,
	    success:function(data){
	    	var oriCost = data.sign;
			$("#billOriCost").text("菜品消费："+oriCost);
			$("#oriCost").val(oriCost);
			var consumeLow = data.dataMap.consumeLow;//add by zhaomingming
			$("#consumeLow").val(consumeLow);//add by zhaomingming
			var tableConsumeLow = $("#tableConsumeLow").val();
//			if(Number(oriCost) >= Number(tableConsumeLow)){
			if(Number(consumeLow) >= Number(tableConsumeLow)){//add by zhaomingming
				var createEmployeeName = $("#createEmployeeName").val();
				$("#consumeLow").remove();
				$("#consumeLowDesc").html("<span class='hideTooLongChar'>开单人:"+ createEmployeeName +"</span>");
			}
			//是否可以下单
			if(data.rel == '1')
			{
				$("#billStatus").val(data.forwardUrl);
				$("#billStatusDesc").text(data.navTabId);
				xiadan();
				quxiao();
			}
			
			kexian(data.dataMap.payableCost+"","1");//客显 显示 应付金额
	    },
		error:function(){
		}
	});
	
	$("#diancaiBillContentDishesTr").find("tr[bdId='"+bdId+"']").click(function(e){
		var tr = $(this);
		var isSet = tr.attr("isSet");
		$("#isSet").val(isSet);
		if(!tr.hasClass("on_tr"))
		{
			tr.parent("tbody").find("tr").removeClass("on_tr");
			tr.addClass("on_tr");
			
			var billStatus =$("#billStatus").val();
			if(billStatus == 3 || billStatus == 8)
			{
				//结账，反结账，撤单，不能操作
				return;
			}
			
			
			var dishesStatus = tr.attr("dishesStatus");
			
			if(dishesStatus == '1' || dishesStatus == '2')
			{
				//未上菜，催菜，退菜等
				$("#newDishWindow").hide();
				var bdId = tr.attr("bdId");
				xiadanWindow(tr,e,'xiadanWindow',bdId,isSet);
			}
			else if(dishesStatus == '5')
			{
				//未下单
				$("#xiadanWindow").hide();
				var bdId = tr.attr("bdId");
				newDishWindow(tr,e,'newDishWindow',bdId);
			}
			else
			{
				$("#xiadanWindow").hide();
				$("#newDishWindow").hide();
			}
		}
		else
		{
			$("#xiadanWindow").hide();
		}
	});
	
}

/**
 * 添加菜肴判断原料以及添加，同步方法
 */
function dishAddJudge(currentBillType,currentBillId,dishesId,ibillId,dish,dishNum,isJudgeRm){
	var tId = $("#tId").val();
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/jiacai/"+currentBillType+"/"+dishesId+"?billId="+currentBillId+"&dishNum="+dishNum+"&tId="+tId+"&isJudgeRm="+isJudgeRm,
	    data:null,
	    cache:false,
	    success:function(data){
	    	synAddDishFlag = false;
	    	toLastPage = true;
	    	//第一次外卖需要处理
	    	$("#currentBillId").val(data.value);
    		$("#billId").val(data.value);
    		$("#billType").val(data.type);
    		BillChange(data.value);
    		
    		var estimate = data.sign;
	    	var dsId = data.rel;
	    	
	    	dish.attr("estimate",estimate);
	    	dish.find("#estimate").text(estimate);
	    	if(estimate >0 && estimate < 10)
	    	{
	    		dish.removeClass().addClass("diancan_yellow_order");
	    	}
	    	else if(estimate == '0')
	    	{
	    		dish.removeClass().addClass("diancan_red_order");
	    	}
	    	else{
	    		dish.removeClass().addClass("diancan_green_order");
	    	}
	    	if(data.messageMap.result == 1){
	    		toastr.warning(data.messageMap.message);
	    	}
	    	$("#keywords").val("");
	    	
	    	if(isJudgeRm == 0 && data.messageMap && data.messageMap.result){
	    		if(data.messageMap.result == 0){//原料库存不足，是否继续
	    			dialogBoxConfirm('点餐',data.messageMap.message+"    是否继续?",
	    					function(){
	    						dishAddJudge(currentBillType,currentBillId,dishesId,ibillId,dish,dishNum,"1");
	    					}
	    			,function (){});
		    	}else if(data.messageMap.result == 1){	//原料库存预警，请及时补充原料
//		    		toastr.warning("原料库存预警，请及时补充原料!");
		    	}else if(data.messageMap.result == 4){	//此菜品数量不足
		    		toastr.warning("此菜品数量不足!");
		    	}
	    	}
	    },
		error:function(){
			synAddDishFlag = false;
		}
	})
}

//添加套餐
function dishesSetForm(div,dsId){
	//判断是否可以外卖
	var billType = $("#billType").val();
	var isTakeout = $(div).attr("isTakeout");
	
	if(billType == 2)
	{
		//外卖单，只能点外卖菜品
		if(!isTakeout || isTakeout == 0)
		{
			toastr.warning("外卖单不能点非外卖套餐!");
			return;
		}
	}
	
	//判断沽清数量
	var estimate = $(div).attr("estimate");
	//有沽清数量的
	if(estimate && estimate ==0)
	{
		toastr.warning("此套餐已经沽清!");
		return;
	}
	
	var billId = $("#billId").val();
	popForm('套餐',window.ctx+'/bill/pop/addDishesSet?billId='+billId+'&dsId='+dsId+"&type=addSet",'880','644');
}

function xiadan()
{
	var billStatus = $("#billStatus").val();
	xiadanStatus();
	$("#xiadan").unbind("click");
	$("#xiadan").click(function(){
		var c = $(this).attr("class");
		if(c == 'but_xiadan_no')
		{
			return;
		}
		if(getPermission("xiadan"))
		{
			var billId = $("#billId").val();
			var billStatus = $("#billStatus").val();
			if(billStatus == 1 || billStatus == 4 || billStatus == 9)
			{
				//未下单，判断是否低于最低消费
				var tableConsumeLow = $("#tableConsumeLow").val();
				var oriCost = $("#oriCost").val();
				var consumeLow = $("#consumeLow").val();//add by zhaomingming
//				if(Number(oriCost) < Number(tableConsumeLow))
				if(Number(consumeLow) < Number(tableConsumeLow))//add by zhaomingming
				{
					dialogBoxConfirm("下单","当前账单消费金额不满足最低消费金额 ，确认下单吗 ？",function(){
						xiadanConfirm();
					});
				}
				else
				{
					
					xiadanConfirm();
				}
				
			}
		}
	});
}

function xiadanConfirm()
{
	var isShowPlaceBillConfirmWindow = $("#isShowPlaceBillConfirmWindow").val();
	var billId = $("#billId").val();
	var billType = $("#billType").val();
	//是否弹出下单确认窗口
	if(0 == isShowPlaceBillConfirmWindow)
	{
		//不显示下单确认窗口
		
		//下单后跳转到餐台还是结账
		
		var billPlaceEnterDeskOrPay = $("#billPlaceEnterDeskOrPay").val();
		if(billPlaceEnterDeskOrPay != 1 && billPlaceEnterDeskOrPay !=2 && billPlaceEnterDeskOrPay !=0)
		{
			billPlaceEnterDeskOrPay = 0;
		}
		$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/xiadan/"+billId+"?enterType="+billPlaceEnterDeskOrPay,
	    data:null,
	    cache:false,
	    success:function(data){
	    	//外卖不受操作设置影响
	    	if(billType == '2'){
	    		//跳转外卖模块
	    		window.location = window.ctx+'/takeout/list';
	    		return;
	    	}
	    	if(data.statusCode && data.statusCode=='0')
	    	{
	    		toastr.info(data.message);
	    		//原地不动
	    		BillChange(billId);
	    	}
	    	else if(data.statusCode && data.statusCode=='1')
	    	{
	    		//下单后进入餐台
	    		window.location = window.ctx+'/index';
	    		toastr.info(data.message);
	    	}
	    	else if(data.statusCode && data.statusCode=='2')
	    	{
	    		//下单后进入结账
	    		window.location = window.ctx+'/bill/payPage/'+data.rel;
	    		toastr.info(data.message);
	    	}
	    	else
	    	{
		    	BillChange(ibillId);
		    	toastr.info(data.message);
		    	if("undefined" == typeof refreshDishCatagoryChange)
				{
					
				}
				else
				{
					if(refreshDishCatagoryChange)
					{
						refreshDishCatagoryChange();
					}
				}
	    	}
	    },
		error:function(){
			
		}
	  });
	}
	else
	{
		popForm('下单确认',window.ctx+'/bill/pop/xiadanConfirm/'+billId,'529','680');
	}
	
	
}

//快餐结账
function jiezhang()
{
	var billId = $("#currentBillId").val();
	if(billId)
	{
		var jump = window.ctx+"/bill/payPage/" + billId;
		window.location = jump;
	}
	else
	{
		toastr.info("请选择账单");
	}
}

function xiadanStatus()
{
	var billStatus = $("#billStatus").val();
	var isCanPlace = $("#isCanPlace").val();
	if( (billStatus == 1 || billStatus == 9 || billStatus == 4) && 
			isCanPlace == '1'){
		$("#xiadan").removeClass().addClass("but_xiadan");
		$("#quxiao").removeClass().addClass("but_qx_xiugai");
	}else{
		$("#xiadan").removeClass().addClass("but_xiadan_no");
		$("#quxiao").removeClass().addClass("but_qx_xiugai_no");
	}
}
function quxiao()
{
	var billId = $("#billId").val();
	if(!billId){
		$("#quxiao").removeClass().addClass("but_qx_xiugai_no");
		return;
	}
	if($("#quxiao").hasClass("but_qx_xiugai_no")){
		return;
	}
	$("#quxiao").unbind("click");
	$("#quxiao").click(function(){
		var billId = $("#billId").val();
		if(!billId)
		{
			return;
		}
		var billStatus = $("#billStatus").val();
		if(billStatus == 1 || billStatus == 9 || billStatus == 4)
		{
			//未下单和部分下单时候，可以取消修改
			$.ajax({
			    type:"get",
			    url:window.ctx+"/bill/quxiao/"+billId,
			    data:null,
			    cache:false,
			    success:function(data){
			    	toastr.success("已将订单还原到上次下单的状态！");
			    	var ibillId = $("#currentBillId").val();
			    	BillChange(ibillId);
			    	refreshDiancaiDishFunction();
			    	refreshPayPageFunction();
			    },
				error:ajaxErrorFunction
			  });
		}
	});
}

function ajaxErrorFunction()
{
	toastr.error("出错了");
}

/**
 * 账单列表页面调用的
 * @param billId
 * @param tr
 */
function billListChange(billId,tr)
{
	if(!$(tr).hasClass("on_tr"))
	{
		currentPage = 0;
		BillChange(billId,tr);
	}
	
	var billStatus = $(tr).attr("billStatus");
	//是否允许撤单
	var isChedan = $(tr).attr("isChedan");
	var chedanbt = $("#listChendan");
	var fanjzbt = $("#fanjzbt");
	var listPrintBt = $("#listPrint");
	
	//结账状态的账单才能反结账
	if(billStatus == '3'){
		fanjzbt.removeClass("but_fanjiezhang_on").addClass("but_fanjiezhang");
	}else{
		if(!fanjzbt.hasClass("but_fanjiezhang_on"))
		{
			fanjzbt.removeClass("but_fanjiezhang_on").addClass("but_fanjiezhang_on");
		}
	}
	
	if(billStatus != '8' && isChedan == '1')
	{
		//未下单，撤单
		chedanbt.removeClass("but_chedan_on").addClass("but_chedan");
	}
	else
	{
		if(!chedanbt.hasClass("but_chedan_on"))
		{
			chedanbt.removeClass("but_chedan").addClass("but_chedan_on");
		}
	}
	
	listPrintBt.removeClass("but_dayin_on").addClass("but_dayin");
}

function listChendan(bt)
{
	
	var chedanbt = $("#listChendan");
	if(!chedanbt.hasClass("but_chedan_on"))
	{
		if(getPermission("chedan"))
		{
			var billId = $("#currentBillId").val();
			if(billId)
			{
				popForm('撤单',window.ctx+'/index/pop/chedan/'+billId,'529','335');
			}
			else
			{
				toastr.info("请选择一个账单");
			}
		}
//		popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=2','529','353');
	}
}
function chedanPopBack()
{
	var billId = $("#currentBillId").val();
	BillChange(billId);
}
function listPrint()
{
	var listPrintBt = $("#listPrint");
	var billId = $("#currentBillId").val();
	if(!listPrintBt.hasClass("but_dayin_on"))
	{
		if(getPermission("listPrint"))
		{
			if(billId)
			{
				popForm('打印',window.ctx+'/bill/pop/billPrint/'+billId,'529','680');
			}
			else
			{
				toastr.info("请选择一个账单");
			}
		}
	
	}
	
}



//反结账
function resettle(){
	var fanjzbt = $("#fanjzbt");
	if(!fanjzbt.hasClass("but_fanjiezhang_on"))
	{
		if(getPermission("fanjzbt"))
		{
			var billId = $("#currentBillId").val();
			if(billId)
			{
				popForm('反结账',window.ctx+'/bill/pop/resettle/'+billId,'529','644');
			}
			else
			{
				toastr.info("请选择一个账单");
			}
//			popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=1','529','353');
		}
	}
}

function permissionCallBack(isHasPermission,functionType,id)
{
	if(functionType == '1')
	{
		var billId = $("#currentBillId").val();
		if(billId)
		{
			popForm('反结账',window.ctx+'/bill/pop/resettle/'+billId,'529','644');
		}
		else
		{
			toastr.info("请选择一个账单");
		}
	}
	else if(functionType == '2')
	{
		var billId = $("#currentBillId").val();
		if(billId)
		{
			popForm('撤单',window.ctx+'/index/pop/chedan/'+billId,'529','335');
		}
		else
		{
			toastr.info("请选择一个账单");
		}
	}
	else if(functionType == '3')
	{
		popForm('退菜原因',window.ctx+'/bill/pop/tuicaiReason/'+id,'529','644');
	}else if(functionType == '4'){
		//套餐退菜
		var isSet = $("#isSet").val();
		popForm('退菜原因',window.ctx+'/bill/pop/tuicaiReason/'+id+"?isSet="+isSet,'529','644');
	}
	else if(functionType == '5'){
		//赠菜
		var ids = id.split(",");
		diancaiBill_zengcai(ids[0],ids[1]);
	}
}


function diancaiBill_zengcai(bdId,isSet)
{
	var ibillId = $("#currentBillId").val();
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/zengcai/"+bdId+"/"+ibillId+"?isSet="+isSet,
	    data:null,
	    cache:false,
	    success:function(data){
	    	if(data.statusCode != 200)
	    	{
	    		toastr.error(data.message);
	    	}
	    	else
	    	{
	    		toastr.success(data.message);
	    	}
	    	BillChange(ibillId);
	    	refreshPayPageFunction();
	    },
		error:function(){}
	  });
}

/**
 * 客显
 * 
 */
function kexian(money,type){
	if(isExitsFunction("window.CallCSharpMethod")){
		window.CallCSharpMethod("OpenCustomerDisplay",money,type);
	}
}

//是否存在指定函数 
function isExitsFunction(funcName) {
    try {
        if (typeof(eval(funcName)) == "function") {
            return true;
        }
    } catch(e) {}
    return false;
}
