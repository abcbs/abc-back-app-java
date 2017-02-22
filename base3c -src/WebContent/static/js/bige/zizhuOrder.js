var totalPrice = 0;
var numArray = [0,0,0,0,0];
function changeDinnerType(a,type,imgSrc,unitPrice)
{
	var jq = $(a);
	var img = $("#changeImg");
	img.attr("src",imgSrc);
	
	$("#unitPrice").val(unitPrice);
	$("#type").val(type);
	$("#buyNum").text(numArray[type])
	attrPrice();
	
	$("a",".list_first").each(function(){
		var o = $(this);
		var c = o.attr("class");
		if(c.indexOf("_on") > 0)
		{
			c = c.substr(0,c.indexOf("_on"));
			o.attr("class",c);
		}
	});
	
	var jqc = jq.attr("class");
	jq.attr("class",jqc+"_on");
	
}


function minus(a,unitPrice,index)
{
	var jq = $(a);
	var orderNum= numArray[index];
	if(orderNum > 0)
	{
		orderNum--;
		numArray[index] = orderNum;
		
		if(orderNum < 10)
		{
			$(a).next().text("0"+orderNum);
		}
		else
		{
			$(a).next().text(orderNum);
		}
		
		
		totalPrice = totalPrice - unitPrice;
		attrPrice();
	}
}

function add(a,unitPrice,index,type)
{
	var jq = $(a);
	var orderNum= numArray[index];
	orderNum++;
	if(orderNum < 10)
	{
		$(a).prev().text("0"+orderNum);
	}
	else
	{
		$(a).prev().text(orderNum);
	}
	
	numArray[index] = orderNum;
	totalPrice = totalPrice + unitPrice;
	attrPrice();
}


function attrPrice()
{
	$("#realCostShow").text('').text(totalPrice);
	$("#realCost").val(totalPrice);
	
	var totalPeople = 0;
	for(var i=0;i<numArray.length;i++)
	{
		totalPeople += numArray[i];
	}
	
	$("#buyNum").html(totalPeople);
	
}

function combineYouhuiDishes()
{
	var dishesObj = [];
	for(var i=0;i<numArray.length;i++)
	{
		var each = numArray[i];
		var dishes = {};
		if(i == 0)
		{
			dishes.ziZhuYouhuiName = "成人自助";
			dishes.ziZhuYouhuiUnitPrice = 60;
		}
		else if(i == 1)
		{
			dishes.ziZhuYouhuiName = "老人/儿童自助";
			dishes.ziZhuYouhuiUnitPrice = 39;
		}
		else if(i == 2)
		{
			dishes.ziZhuYouhuiName = "周二男士日";
			dishes.ziZhuYouhuiUnitPrice = 49;
		}
		else if(i == 3)
		{
			dishes.ziZhuYouhuiName = "周三女士日";
			dishes.ziZhuYouhuiUnitPrice = 49;
		}
		else if(i == 4)
		{
			dishes.ziZhuYouhuiName = "周一老人日";
			dishes.ziZhuYouhuiUnitPrice = 35;
		}
		dishes.unitNum = each;
		if(dishes.unitNum > 0)
		{
			dishesObj.push(dishes);
		}
	}
	//将JSON对象转换为字符串
	var dishesString = JSON.stringify(dishesObj);
	if(dishesObj == "" || dishesObj.length == 0){
		toastr.warning("请购买自助餐！");
		return null;
	}
	return dishesString;
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
	$("div",".list_third").each(function(){
		var a = $(this);
		a.children("div[class='fenshu']").text('00');
	});
	
	totalPrice = 0;
	numArray = [0,0,0,0,0];
	$("#buyNum").text('00')
	attrPrice();
}

var isCanGo = true;
function goAdmin()
{
	if(isCanGo)
	{
		isCanGo = false;
		setTimeout("afterTen()", 10000);
		$.ajax({
		    type:"get",
		    url:window.ctx+"/self/bige/ajax/goAdmin",
		    data:null,
		    cache:false,
		    async:true,
		    success:function(data){
		    	if(data.statusCode == '200')
		    	{
//		    		window.location = window.ctx+'/login/urlLogin?u=admin&p=admin';
		    	}
		    },
			error:function(){
			}
		  });
	}
}
function afterTen()
{
	isCanGo = true;
}