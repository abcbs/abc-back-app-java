$(document).ready(function(){
	$("#addDishesSet").bind("click",oprate);
	
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			oprate();
		}
	},
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			closebox();
		}
	}
	],1);
	
});

//获得对应的替换菜肴列表
function replaceList(div,dsId,dsDishesId){
	if($(div).hasClass("mr_cai_on")){
		return;
	}
	$(".mr_cai_on").each(function(){
		$(this).attr("class","mr_cai mrcy");
	});
	$(div).attr("class","mr_cai_on mrcy");
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/pop/replaceDishesContent/"+dsId+"/"+dsDishesId,
	    data:null,
	    cache:false,
	    success:function(data){
	    	$("#replaceDishes").html(data);
	    	//判断点击的菜肴和默认菜肴不一致需要将默认菜肴替换掉替换菜肴列表里的刚刚选中的菜肴
	    	var dishesId = $(div).attr("dishesId");
	    	var mr_dishesId = $(div).attr("mr_dishesId");
	    	//如果当前选中菜肴不是默认菜肴
	    	if(dishesId != mr_dishesId){
		    	//默认菜肴
		    	var mr_unitNum = $(div).attr("mr_unitNum");
				var mr_unitName = $(div).attr("mr_unitName");
				var mr_dishesName = $(div).attr("mr_dishesName");
				var mr_dishesCode = $(div).attr("mr_dishesCode");
				$("#replaceDishes").find("div").each(function(){
					var replaceDisheId = $(this).attr("replaceDisheId");
					if(replaceDisheId == dishesId){
						$(this).attr("replaceDisheId",mr_dishesId);
						$(this).attr("unitNum",mr_unitNum);
						$(this).attr("unitName",mr_unitName);
						$(this).attr("dishesName",mr_dishesName);
						$(this).attr("dishesCode",mr_dishesCode);
						$(this).find("span").each(function(index){
//							if(index == 0){
//								$(this).html(mr_dishesName);
//							}else if(index == 1){
//								$(this).html(mr_unitNum+mr_unitName);
//							}else if(index == 2){
//								$(this).html(mr_dishesCode);
//							}
							if(index == 0){
								$(this).html(mr_dishesName + "</br>" + mr_unitNum + mr_unitName + "</br>" + mr_dishesCode);
							}
						});
					}
				});
	    	}
	    	
	    },
		error:function(){
		}
	  });
}

function oprate(){
	var isOrder = $("#isOrder").val();
	//预订
	if(isOrder == '1'){
		var orderId = $("#orderId").val();
		var dsId = $("#dsId").val();
		var dsDishesDesc = getDishesDesc();
		var orderStatus = $("#orderStatus").val();
		if(orderStatus != '2')
		{
			toastr.warning("不能点菜了");
			synAddDishFlag = false;
	    	toLastPage = true;
			return;
		}
		$.ajax({
		    type:"post",
		    url:window.ctx+"/bill/orderJiacaiSet/"+orderId+"/"+dsId,
		    data:"dsDishesDesc="+dsDishesDesc,
		    cache:false,
		    success:function(data){
		    	toLastPage = true;
		    	var iorderId = $("#currentOrderId").val();
		    	BillChange(iorderId);
		    	closebox();
		    },
			error:function(){
				synAddDishFlag = false;
			}
		  });
	}else{
		var type = $("#type").val();
		if(type == 'addSet'){
			addDishesSet();
		}else if(type == 'editSet'){
			editSet();
		}
	}
	
}

//组织套餐相关数据
function getDishesDesc(){
	var dishesObj = [];
	var dsDishesDesc = "";
	$(".mrcy").each(function(){
		var dishes = {};
		var dsDishesId = $(this).attr("dsDishesId");
		var dishesId = $(this).attr("dishesId");
		var dishesName = $(this).attr("dishesName");
		var unitNum = $(this).attr("unitNum");
		var unitName = $(this).attr("unitName");
		var dishesCode = $(this).attr("dishesCode");
		var mr_dishesId = $(this).attr("mr_dishesId");
		var mr_dishesName = $(this).attr("mr_dishesName");
		var mr_unitNum = $(this).attr("mr_unitNum");
		var mr_unitName = $(this).attr("mr_unitName");
		var mr_dishesCode = $(this).attr("mr_dishesCode");
		dishes.dsDishesId = dsDishesId;
		dishes.dishesId = dishesId;
		dishes.dishesName = dishesName;
		dishes.unitNum = unitNum;
		dishes.unitName = unitName;
		dishes.dishesCode = dishesCode;
		dishes.mr_dishesId = mr_dishesId;
		dishes.mr_dishesName = mr_dishesName;
		dishes.mr_unitNum = mr_unitNum;
		dishes.mr_unitName = mr_unitName;
		dishes.mr_dishesCode = mr_dishesCode;
		dishesObj.push(dishes);
	});
	//将JSON对象转换为字符串
	var dishesString = JSON.stringify(dishesObj);
	dsDishesDesc = dishesString;
	return dsDishesDesc;
}

function editSet(){
	var dsDishesDesc = getDishesDesc();
	var bdsId = $("#bdsId").val();
	
	tcReplace(bdsId,dsDishesDesc,0);

}
/**
 * 更新替换套餐
 * @param bdsId
 * @param dsDishesDesc
 */
function tcReplace(bdsId,dsDishesDesc,isJudgeRm){
	$.ajax({
	    type:"post",
	    url:window.ctx+"/bill/editDishesSet/"+bdsId,
	    data:"dsDishesDesc="+dsDishesDesc+"&isJudgeRm="+isJudgeRm,
	    cache:false,
	    success:function(data){
	    	closebox();
	    	if(isJudgeRm == 0){
		    	if(data.messageMap.result == 0){
		    		dialogBoxConfirm('套餐替换',data.messageMap.message,
	    					function(){
		    					tcReplace(bdsId,dsDishesDesc,1);
	    					}
	    			);
		    	}else{
		    		tcReplace(bdsId,dsDishesDesc,1);
		    	}
	    	}else{
		    	if(data.messageMap.result == 1){
		    		toastr.warning(data.messageMap.message);
		    	}
	    	}
	    	
	    },
		error:function(){
			synAddDishFlag = false;
		}
	  });
}
var synAddDishFlag = false;
//添加套餐信息
function addDishesSet(){
	var dsDishesDesc = getDishesDesc();
	if(!synAddDishFlag){
		synAddDishFlag = true;
		//判断是否可以外卖
		var billType = $("#billType").val();
		var isTakeout = $("#isTakeout").val();
		if(billType == 2){
			//外卖单，只能点外卖菜品
			if(!isTakeout || isTakeout == 0){
				toastr.warning("外卖单不能点非外卖菜品!");
				synAddDishFlag = false;
				return;
			}
		}
		var billStatus =$("#billStatus").val();
		if(billStatus == 3 || billStatus == 8){
			//结账，反结账，撤单，不能再加菜
			toastr.warning("此账单已完结，不能再加菜!");
			synAddDishFlag = false;
			return;
		}
		
		//加菜
		var dsId = $("#dsId").val();
		var currentBillType = $("#currentBillType").val();
		var currentBillId = $("#currentBillId").val();
		
		var ibillId = $("#currentBillId").val();
		//判断套餐添加时原料以及添加操作
		dishTcAdd(dsId,currentBillType,currentBillId,dsDishesDesc,"",ibillId,"0");
	}else{
		toastr.info("下单太快了，稍等片刻");
	}
}
/**
 * 添加套餐
 * @param dsId
 * @param currentBillType
 * @param currentBillId
 * @param dsDishesDesc
 * @param bdId
 * @param ibillId
 * @param isJudgeRm
 */
function dishTcAdd(dsId,currentBillType,currentBillId,dsDishesDesc,bdId,ibillId,isJudgeRm){
	var tId = $("#tId").val();
	$.ajax({
	    type:"post",
	    url:window.ctx+"/bill/addDishesSet/"+currentBillType+"/"+dsId+"?billId="+currentBillId+"&isJudgeRm="+isJudgeRm+"&tId="+tId,
	    data:"dsDishesDesc="+dsDishesDesc,
	    cache:false,
	    success:function(data){
	    	synAddDishFlag = false;
	    	toLastPage = true;
	    	if(isJudgeRm == 0){
	    		if(data.messageMap.result == 0){
	    			dialogBoxConfirm('点餐',data.messageMap.message+"    是否继续?",
	    					function(){
	    				      dishTcAdd(dsId,currentBillType,currentBillId,dsDishesDesc,"",ibillId,"1");
	    					}
	    			,function (){});
		    	}else if(data.messageMap.result == 1){
		    		dishTcAdd(dsId,currentBillType,currentBillId,dsDishesDesc,"",ibillId,"1");
		    	}else if(data.messageMap.result == 2){
		    		dishTcAdd(dsId,currentBillType,currentBillId,dsDishesDesc,"",ibillId,"1");
		    	}
	    	}else{
	    		$("#currentBillId").val(data.value);
	    		$("#billId").val(data.value);
	    		$("#billType").val(data.type);
	    		closebox();
		    	BillChange(data.value);
		    	if(data.messageMap.result == 1){
		    		toastr.warning(data.messageMap.message);
		    	}
		    	
		    	var estimate = data.sign;
				
		    	$("#diancaiDishDiv").find("div").each(function(){
		    		var dishAndSetDiv = $(this).attr("dishAndSetDiv");
		    		if(dishAndSetDiv == '2'){
		    			var dishesId = $(this).attr("dishesId");
		    			if(dishesId == data.rel){
		    				$(this).attr("estimate",estimate);
		    				$(this).find("#estimate").text(estimate);
		    				if(estimate >0 && estimate < 10){
		    					$(this).removeClass().addClass("diancan_yellow_order");
	    			    	}else if(estimate == '0'){
	    			    		$(this).removeClass().addClass("diancan_red_order");
	    			    	}else{
	    			    		$(this).removeClass().addClass("diancan_green_order");
	    			    	}
		    			}
		    		}
		    	});
	    	}
	    	$("#keywords").val("");
	    	
	    },
		error:function(){
			synAddDishFlag = false;
		}
	})
}
//替换菜肴
function replaces(div){
	var dsDishesId = $(div).attr("dsDishesId");
	$(".mrcy").each(function(){
		var morendsDishesId = $(this).attr("dsDishesId");
		var morenDishesId = "";
		var morenUnitNum = "";
		var morenUnitName = "";
		var morenDishesName = "";
		var morenDishesCode = "";
		var replaceDisheId = "";
		var replaceUnitNum = "";
		var replaceUnitName = "";
		var replaceDishesName = "";
		var replaceDishesCode = "";
		if(dsDishesId == morendsDishesId){
			replaceDisheId = $(div).attr("replaceDisheId");
			replaceUnitNum = $(div).attr("unitNum");
			replaceUnitName = $(div).attr("unitName");
			replaceDishesName = $(div).attr("dishesName");
			replaceDishesCode = $(div).attr("dishesCode");
			morenDishesId = $(this).attr("dishesId");
			morenUnitNum = $(this).attr("unitNum");
			morenUnitName = $(this).attr("unitName");
			morenDishesName = $(this).attr("dishesName");
			morenDishesCode = $(this).attr("dishesCode");
			
			$(this).attr("dishesId",replaceDisheId);
			$(this).attr("unitNum",replaceUnitNum);
			$(this).attr("unitName",replaceUnitName);
			$(this).attr("dishesName",replaceDishesName);
			$(this).attr("dishesCode",replaceDishesCode);
			$(this).find("span").each(function(rIndex){
//				if(rIndex == 0){
//					$(this).html(replaceDishesName);
//				}
//				if(rIndex == 1){
//					$(this).html(replaceUnitNum+replaceUnitName);
//				}
//				if(rIndex == 2){
//					$(this).html(replaceDishesCode);
//				}
				if(rIndex == 0){
					$(this).html(replaceDishesName + "</br>" + replaceUnitNum + replaceUnitName + "</br>" + replaceDishesCode);
				}
			});
			
			$(div).attr("replaceDisheId",morenDishesId);
			$(div).attr("unitNum",morenUnitNum);
			$(div).attr("unitName",morenUnitName);
			$(div).attr("dishesName",morenDishesName);
			$(div).attr("dishesCode",morenDishesCode);
			$(div).find("span").each(function(morenIndex){
//				if(morenIndex == 0){
//					$(this).html(morenDishesName);
//				}
//				if(morenIndex == 1){
//					$(this).html(morenUnitNum+morenUnitName);
//				}
//				if(morenIndex == 2){
//					$(this).html(morenDishesCode);
//				}
				if(morenIndex == 0){
					$(this).html(morenDishesName + "</br>" + morenUnitNum + morenUnitName + "</br>" + morenDishesCode);
				}
			});
		}
	});
}

function mrUp(){
	var st = $("div.mr_cont").scrollTop();
	$("div.mr_cont").scrollTop(st-80);
}

function mrDown(){
	var st = $("div.mr_cont").scrollTop();
	$("div.mr_cont").scrollTop(st+80);
}

function reUp(){
	var st = $("div.th_cont").scrollTop();
	$("div.th_cont").scrollTop(st-80);
}

function reDown(){
	var st = $("div.th_cont").scrollTop();
	$("div.th_cont").scrollTop(st+80);
}



