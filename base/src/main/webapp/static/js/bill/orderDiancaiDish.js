$(document).ready(function(){
	//异步请求
	initFrameSize();
	refreshDish();
	

});
var diancaiListUlMarginLeft=0;

/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
	if(subWidth > 0)
	{
		$(".body_sec").css("width",1000+subWidth+"px");
		$(".list_b").css("width",659+subWidth+"px");
		
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_sec").css("height",672+subHeight+"px");
		$(".list_a").css("height",670+subHeight+"px");
		$(".list_b").css("height",670+subHeight+"px");
	}
}
/**
 * 自适应浏览器宽度
 */
function initDishWidth()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
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
		$(".body_sec").css("height",672+subHeight+"px");
		$(".list_a").css("height",670+subHeight+"px");
		$(".list_b").css("height",670+subHeight+"px");
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

var diancanDish_categoryId ='';
var diancanDish_dsCategoryId ='';
var diancanDish_keywords ='';
var diancanDish_page =1;
function refreshDish()
{
	var orderId = $("#currentOrderId").val();
	dishCatagoryChange(orderId,diancanDish_categoryId,1,diancanDish_dsCategoryId,'');
}


function keyWordSearch()
{
	var keywords = $("#keywords").val();
	var orderId = $("#currentOrderId").val();
	dishCatagoryChange(orderId,diancanDish_categoryId,1,diancanDish_dsCategoryId,keywords);
}


function dishCatagoryChange(orderId,categoryId,page,dsCategoryId,keywords)
{
	ProgressbarUtil.show("ajaxContent",670);
	diancanDish_categoryId = categoryId;
	diancanDish_dsCategoryId = dsCategoryId;
	diancanDish_keywords = keywords;
	diancanDish_page = page;
	
	 $.ajax({
		    type:"get",
		    url:getScreenSizeUrl('/bill/ajax/orderDiancaiContent?orderId='+orderId+'&page='+page+'&categoryId='+categoryId+'&dsCategoryId='+dsCategoryId+'&keywords='+keywords,'dish'),
		    data:null,
		    cache:false,
		    async:true,
		    success:function(html){
		    	$("#ajaxContent").html(html);
		    	initDishWidth();
		    	$(".diancan_wrap").disableSelection();
		    	$("#userDefined").click(function(){
		    		var orderId = $("#currentOrderId").val();
		    		popForm('自定义菜肴',window.ctx+'/bill/pop/orderUserdefined/create/'+orderId,'529','644');
		    	});
		    	$("#keywords").keydown(function(evt) {
		    		if(evt.keyCode == 13)
		    		{
		    			keyWordSearch();
		    		}
		    	});
		    	
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
					if(dishCatagoryTabWidth + left < dishTabWidth)
					{
						return;
					}
					left-= 300;
					diancaiListUlMarginLeft=left;
					$("#diancaiListUl").animate({marginLeft:left+"px"},100);
				});
		    },
			error:function(){
				toastr.error("出错了");
			}
		  });
}



//添加套餐
function dishesSetForm(div,dsId){
	var orderId = $("#currentOrderId").val();
	popForm('套餐',window.ctx+'/bill/pop/addDishesSet?billId='+orderId+'&dsId='+dsId+"&type=addSet&isOrder=1",'880','644');
}

