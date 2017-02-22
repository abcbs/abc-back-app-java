$(document).ready(function(){
	//异步请求
	initFrameSize();
	var orderId = $("#currentOrderId").val();
	BillChange(orderId);

	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"1",
		callBackFunction:function()
		{
			var billId = $("#currentBillId").val();
			var jump = window.ctx+"/bill/list?billId=" + billId;
			window.location = jump;
		}
	},
	{
		keyCode:"2",
		callBackFunction:function()
		{
			var billId = $("#currentBillId").val();
			if(billId)
			{
				var jump = window.ctx+"/bill/diancai?billId=" + billId;
				window.location = jump;
			}
			else
			{
				toastr.info("请选择账单");
			}
		}
	}
	,
	{
		keyCode:"3",
		callBackFunction:function()
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
	}
	,
	{
		keyCode:"4",
		callBackFunction:function()
		{
			$("#xiadan").click();
		}
	}
	],0);
	
});
/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
	if(subWidth > 0)
	{
		$(".body_sec").css("width",1000+subWidth+"px");
	}
}


/**
 * 自适应浏览器宽度
 */
function initWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
	if(subWidth > 0)
	{
		$(".body_sec").css("width",1000+subWidth+"px");
	}
}


var dishsArray = new Array();
function initDishes()
{
	itemPerPage = PageSizeUtil.getSizeByType('bill');
	dishsArray = new Array();
	var billList = $("#billList");
	billList.find("tr").each(function(i){
		var tr = $(this);
		if(tr.attr("id"))
		{
			var seq = tr.children("#seq").text();
			var dishesId = tr.children("#dishesId").text();
			var dishesName = tr.children("#dishesName").text();
			var orderNum = tr.children("#orderNum").text();
			var dishesCode = tr.children("#dishesCode").text();
			var price = tr.children("#price").text();
			var total = parseInt(orderNum) * parseFloat(price);
			var newDish = {"seq":seq,"dishesId":dishesId,"dishesName":dishesName,"orderNum":orderNum,"dishesCode":dishesCode,"price":price,"total":total};
			dishsArray.push(newDish);
		}
	});
	billList.find("tr").click(function(e){
		var tr = $(this);
		if(!tr.hasClass("on_tr"))
		{
			tr.parent("tbody").find("tr").removeClass("on_tr");
			tr.addClass("on_tr");
			
			$("#xiadanWindow").hide();
			var bdId = tr.attr("bdId");
			newDishWindow(tr,e,'newDishWindow',bdId);
		}
		else
		{
			$("#xiadanWindow").hide();
		}
	});
	
	//备注
	$("#beizhu").click(function(){
		var orderId = $("#currentOrderId").val();
		popForm('烹饪备注',window.ctx+'/bill/pop/orderCooking/update/'+orderId,'880','644');
	});
	//初始化分页
	initPage();
}

function permissionCallBack(isHasPermission)
{
	if(isHasPermission)
	{
		var billId = $("#currentOrderId").val();
		popForm('烹饪备注',window.ctx+'/bill/cooking/update/'+billId,'880','644');
	}
	
}

function xiadanWindow(tr,e,id,bdId)
{
	$("#dishCuicai").unbind("click");
	$("#dishHuacai").unbind("click");
	$("#dishTuicai").unbind("click");
	
	$("#dishCuicai").click(function(){
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/dishCuicai/"+bdId,
		    data:null,
		    cache:false,
		    success:function(data){
		    	$("#"+id).hide();
		    	toastr.success(data.message);
		    	tr.removeClass("on_tr");
		    },
			error:function(){}
		  });
	});
	$("#dishHuacai").click(function(){
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/dishHuacai/"+bdId,
		    data:null,
		    cache:false,
		    success:function(data){
		    	var ibillId = $("#currentOrderId").val();
		    	BillChange(ibillId);
		    },
			error:function(){}
		  });
	});
	$("#dishTuicai").click(function(){
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/dishTuicai/"+bdId,
		    data:null,
		    cache:false,
		    success:function(data){
		    	var ibillId = $("#currentOrderId").val();
		    	BillChange(ibillId);
		    },
			error:function(){}
		  });
	});
	
	showFloat(e,'xiadanWindow',tr);
}

function newDishWindow(tr,e,id,bdId)
{
	//初始化
	var orderNum = tr.attr("orderNum");
	$("#newDishNum").val(orderNum);
	$("#oldDishNum").val(orderNum);
	
	$("#newDishWindow").click(function(e){
		e.stopPropagation();
	});
	
	$("#newDishDelete").unbind("click");
	$("#newDishCancel").unbind("click");
	$("#newDishConfirm").unbind("click");
	$("#newDishAdd").unbind("click");
	$("#newDishSub").unbind("click");
	$("#newDishBeizhu").unbind("click");
	
	
	$("#newDishNum").sudokuShow();
	
	
	$("#newDishDelete").click(function(){
		var iorderId = $("#currentOrderId").val();
		var dishesId = tr.attr("id");
		var isSet = tr.attr("isSet");
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/orderDishDelete/"+iorderId+"/"+bdId+"?isSet="+isSet,
		    data:null,
		    cache:false,
		    success:function(data){
		    	$("#"+id).hide();
		    	var iorderId = $("#currentOrderId").val();
		    	BillChange(iorderId);
		    },
			error:function(){}
		  });
	});
	$("#newDishCancel").click(function(){
		$("#"+id).hide();
		tr.removeClass("on_tr");
	});
	$("#newDishConfirm").click(function(){
		var newDishNum = $("#newDishNum").val();
		var dishesId = tr.attr("id");
		var iorderId = $("#currentOrderId").val();
		var isSet = tr.attr("isSet");
		var num = newDishNum.replaceAll("\\.","-");
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/orderDishNumChange/"+iorderId+"/"+bdId+"/"+num+"?isSet="+isSet,
		    data:null,
		    cache:false,
		    success:function(data){
		    	$("#"+id).hide();
		    	var iorderId = $("#currentOrderId").val();
		    	BillChange(iorderId);
		    },
			error:function(){}
		  });
	});
	
	$("#newDishBeizhu").click(function(){
		$("#"+id).hide();
		var isSet = tr.attr("isSet");
		popForm('烹饪备注',window.ctx+'/bill/pop/orderDishCooking/update/'+bdId+"?isSet="+isSet,'880','644');
	});
	
	$("#newDishAdd").click(function(){
		var newDishNum = $("#newDishNum").val();
		newDishNum++;
		$("#newDishNum").val(newDishNum);
		
	});
	$("#newDishSub").click(function(){
		var newDishNum = $("#newDishNum").val();
		if(newDishNum > 1)
		{
			newDishNum--;
			$("#newDishNum").val(newDishNum);
		}
	});
	showFix(e,id,tr);
}


function BillChange(orderId,tr)
{
	ProgressbarUtil.show("ajaxBillContent",670);
	 $.ajax({
		    type:"get",
		    url:window.ctx+'/bill/ajax/orderDiancaiBillContent?orderId='+orderId,
		    data:null,
		    cache:false,
		    async:true,
		    success:function(html){
		    	$("#ajaxBillContent").html(html);
		    	initDishes();
		    	initWidth();
		    	$("#currentOrderId").val(orderId);
		    	if(tr)
		    	{
		    		$(tr).parent("tbody").find("tr").removeClass("on_tr");
		    		$(tr).addClass("on_tr");
		    	}
		    },
			error:function(){
			}
		  });
}

var itemPerPage = 10;
var currentPage = 0;
var toLastPage = false;
function initPage()
{
	if(toLastPage)
	{
		goLastPage();
		toLastPage = false;
	}
	var start = currentPage * itemPerPage;
	var end = start + itemPerPage;
	showTrs(start,end);
	
	$("#billPageBack").click(function(){
		var totalSize = dishsArray.length;
		var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
		if(currentPage > 0)
		{
			currentPage --;
			var start = currentPage * itemPerPage;
			var end = start + itemPerPage;
			showTrs(start,end);
		}
	});
	
	$("#billPageGo").click(function(){
		var totalSize = dishsArray.length;
		var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
		if(currentPage < totalPage-1)
		{
			currentPage ++;
			var start = currentPage * itemPerPage;
			var end = start + itemPerPage;
			showTrs(start,end);
		}
	});
}

function goLastPage()
{
	var totalSize = dishsArray.length;
	var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
	currentPage = totalPage -1;
}

function refreshPage(currentPage)
{
	var totalSize = dishsArray.length;
	var totalPage = parseInt(totalSize/itemPerPage) + (totalSize%itemPerPage > 0 ? 1 : 0);
	$("#billPageBack").removeClass();
	$("#billPageGo").removeClass();
	if(currentPage == 0)
	{
		$("#billPageBack").addClass('but_qx_left_on');
	}
	else
	{
		$("#billPageBack").addClass('but_qx_left');
	}
	if(currentPage == totalPage-1 || totalPage == 0)
	{
		$("#billPageGo").addClass('but_qx_right_on');
	}
	else
	{
		$("#billPageGo").addClass('but_qx_right');
	}
}

function showTrs(start,end)
{
	var showNum = 0;
	var billList = $("#billList");
	billList.find("tr").each(function(i){
		var tr = $(this);
		var seq = i;
		if(tr.attr("id"))
		{
			tr.hide();
			if(seq > start && seq <= end)
			{
				tr.show();
				showNum++;
			}
		}
	});
	
	if(showNum < itemPerPage)
	{
		for(var i=0;i<itemPerPage-showNum;i++)
		{
			var billStr = "<tr id=\"blank\"><td id=\"seq\">&nbsp;</td><td id=\"dishesName\"></td><td id=\"price\"></td><td id=\"orderNum\"></td><td id=\"total\"></td></tr>";
			billList.append(billStr);
		}
	}
	
	refreshPage(currentPage);
}


var synAddDishFlag = false;

function addDishes(div){
	
	if(!synAddDishFlag)
	{
		synAddDishFlag = true;
		var dish = $(div);
		var dishesId = dish.find("#dishesId").text();
		var dishesName = dish.find("#dishesName").text();
		var orderNum = dish.find("#orderNum").text();
		var dishesCode = dish.find("#dishesCode").text();
		var unitName = dish.find("#unitName").text();
		var price = dish.find("#price").text();
		
		var total = parseInt(orderNum) * parseFloat(price);
		
		var orderStatus = $("#orderStatus").val();
		if(orderStatus != '2')
		{
			toastr.warning("不能点菜了");
			synAddDishFlag = false;
	    	toLastPage = true;
			return;
		}
		
		//加菜
		var orderId = $("#orderId").val();
		$.ajax({
		    type:"get",
		    url:window.ctx+"/bill/orderJiacai/"+orderId+"/"+dishesId,
		    data:null,
		    cache:false,
		    success:function(data){
		    	synAddDishFlag = false;
		    	toLastPage = true;
		    	var iorderId = $("#currentOrderId").val();
		    	BillChange(iorderId);
		    },
			error:function(){
				synAddDishFlag = false;
			}
		  });
	}
	else
	{
		toastr.info("下单太快了，稍等片刻");
	}
}

function ajaxErrorFunction()
{
	toastr.error("出错了");
}

