//初始化首页面
//mobileinit pagebeforecreate pagecreate pageinit
$(document).bind("pageinit", function() {
	loadDish(0,0);
	getShoppingCarInfo();
});
$("#keyWordSearchButton").click( function () {
	$('.DishType').removeClass("current_a");
	$('.DishType:first').addClass("current_a");
	$('.dishesCategory').removeClass("current_b");
	$('.dishesCategory:first').addClass("current_b");
	loadDish(0,0,$("#keyWord").val());
});
function loadDishByType(sortType){
	var sortTypeVal=$('#typeVal').val();
	var SaleNumVal=$('#SaleNumVal').val();
	 if(sortType=='1'){
		if(SaleNumVal=='saleNum_ASC'){
			SaleNumVal='saleNum_DESC';
		}else{
			SaleNumVal='saleNum_ASC';
		}		
		$('#SaleNumVal').val(SaleNumVal);
		var dishCategory=$('#dishCategoryVal').val();
		loadDish(SaleNumVal,dishCategory);
	}else if(sortType=='2'){
		if(sortTypeVal=='price_ASC'){
			sortTypeVal='price_DESC';
		}else{
			sortTypeVal='price_ASC';
		}	
		$('#typeVal').val(sortTypeVal);
		var dishCategory=$('#dishCategoryVal').val();
		loadDish(sortTypeVal,dishCategory);
	}else if(sortType=='3'){
		if(sortTypeVal=='price_ASC'){
			sortTypeVal='price_DESC';
		}else{
			sortTypeVal='price_ASC';
		}		
	}
	
};
function loadDishByCategory(dishCategory){
	$('#dishCategoryVal').val(dishCategory);
	var sortType=$('#typeVal').val();
	loadDish(sortType,dishCategory);
};
function loadDish(sortType,dishCategory,keyWord){
	$("#dishList").load("dishList", {sortType:sortType,dishCategory:dishCategory,tabNO:$('#tabNO').val(),
		search_LIKE_dishesName:keyWord,search_LIKE_dishesCode:keyWord}, function(data) {
			if(window.screen.width <= 768)
			{
				var comIntval = 113/2;
				$(".cai_wrap").css("width",(980-96)+"px");
				$(".cai_list").css("width",(980-40-96)+"px");
				$("dl",".cai_list").css("width",(980-430-48)+"px");
//				$("dt",".cai_list").css("width",(980-430-48)/2+"px");
//				$(".cai_name").css("width",(502/2)+"px");
//				$(".cai_count").css("width",(502/2)+"px");
			}
	});
};

function initDishInfo(){
	$.getJSON("getAddedDish", { tabNO:$('#tabNO').val() }, function(json){
	  alert("JSON Data: " + json);
	});
};
function getShoppingCarInfo(){
	$.getJSON("getShoppingCarInfo", { tabNO:$('#tabNO').val() }, function(data){
	  console.log("getShoppingCarInfo:" + data);
	  $("#goOrderAAAAA span a").text(data+"份");
	});
};
//处理点击样式改变
$(document).delegate('.dishesCategory', 'click', function() {
	$('.dishesCategory').removeClass("current_b");
	$(this).addClass("current_b");
});
$(document).delegate('.DishType', 'click', function() {
	$('.DishType').removeClass("current_a");
	$(this).addClass("current_a");
});
/////
//加菜
function addDish(){
	
};

$(document).delegate('.but_minus', 'click', function() {
	var amount=$(this).parent().parent().parent().find("#amount");
	if(amount.text()==0){
		return false;
	}
	amount.text(amount.text()-1);
	var dishId=$(this).parent().parent().parent().find(".cai_list").attr("id");
	$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:amount.text()}, function(data) {
		console.log("but_add:" + data.message);
		getShoppingCarInfo();
	});
});


$(document).delegate('.but_add', 'click', function() {
	var amount=$(this).parent().parent().parent().find("#amount");
	amount.text(amount.text()-1+2);
	var dishId=$(this).parent().parent().parent().find(".cai_list").attr("id");
	//alert(dishId);
	$.post("addDish", {tabNO:$('#tabNO').val(),dishId:dishId,count:amount.text()}, function(data) {
		console.log("but_add:" + data.message);
		getShoppingCarInfo();
	});
});
$(document).delegate('.dish_back', 'click', function() {
	//$("div").load("dishDetail").modal();
	//$.modal("<div><h1>SimpleModal</h1></div>");
	//$.mobile.changePage("dishDetail", "slideup"); 
	$.mobile.changePage("dishDetail","pop", false, false); 
	//$.mobile.loadPage("dishDetail");
});
$(document).delegate('#goOrderAAAAA', 'click', function() {
	  
	  
});
