var totalPrice = 0;

function minus(a,price)
{
	var jq = $(a);
	var orderNumObj= jq.parent(".caozuo_wrap").children("#orderNum");
	var orderNum = orderNumObj.val();
	if(orderNum > 0)
	{
		orderNum--;
		orderNumObj.val(orderNum);
		totalPrice = totalPrice - parseFloat(price);
		attrPrice();
	}
}

function add(a,price)
{
	var jq = $(a);
	var orderNumObj= jq.parent(".caozuo_wrap").children("#orderNum");
	var orderNum = orderNumObj.val();
	orderNum++;
	orderNumObj.val(orderNum);
	totalPrice = totalPrice + parseFloat(price);
	
	attrPrice();
}


function attrPrice()
{
	$("#realCostShow").text('').text(totalPrice);
	$("#realCost").val(totalPrice);
}

function combineYouhuiDishes()
{
	var dishesObj = [];
	$("div[class='can_list']").each(function(){
			var div = $(this);
	 		var dishes = {};
	 		var ziZhuYouhuiName = div.attr("ziZhuYouhuiName");
	 		var ziZhuYouhuiUnitPrice = div.attr("price");
	 		var unitNum = div.find("#orderNum").val();
	 		dishes.ziZhuYouhuiName = ziZhuYouhuiName;
	 		dishes.ziZhuYouhuiUnitPrice = ziZhuYouhuiUnitPrice;
	 		dishes.unitNum = unitNum;
	 		if(unitNum && unitNum > 0)
	 		{
	 			dishesObj.push(dishes);
	 		}
	});
	//将JSON对象转换为字符串
	var dishesString = JSON.stringify(dishesObj);
	if(dishesObj == "" || dishesObj.length == 0){
		toastr.error("至少要有一个选择");
		return false;
	}
	$("#dinerBillZiZhuDisheStr").val(dishesString);
	return true;
}

function zizhuMemberPay(ctx,billId)
{
	if(combineYouhuiDishes())
	{
		formSubmit("payForm");
	}
	
	
}

function zizhuBankCardPay(ctx,billId)
{
	if(combineYouhuiDishes())
	{
		$("#payForm").attr("action",ctx+"/self/zizhu/zizhuBankCardPay");
		formSubmit("payForm");
	}
}

	
function reOrder()
{
	$("div[class='can_list']").each(function(){
		var div = $(this);
		div.find("#orderNum").val('0');
	});
	totalPrice = 0;
	attrPrice();
}


