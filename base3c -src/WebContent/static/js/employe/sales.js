$(document).ready(function(){
	initFrameSize();
});

/**
 * 菜肴销售明细
 * @param categoryId
 * @param dishesName
 * @param startDate
 * @param endDate
 */
function dishesSalesDetail(categoryId,categoryName,startDate,endDate){
	var url = window.ctx+"/employe/pop/dishesSalesDetail?categoryId="+categoryId+"&startDate="+startDate+"&endDate="+endDate;
	popForm(categoryName+'销售明细',url,529,644);
}

/**
 * 套餐销售明细
 * @param dsCategoryId
 * @param dsName
 * @param startDate
 * @param endDate
 */
function dishesSetSalesDetail(dsCategoryId,categoryName,startDate,endDate){
	var url = window.ctx+"/employe/pop/dishesSetSalesDetail?dsCategoryId="+dsCategoryId+"&startDate="+startDate+"&endDate="+endDate;
	popForm(categoryName+'销售明细',url,529,644);
}

/**
 * 搜索销售信息
 * @param startDate
 * @param endDate
 */
function getSales(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var url = window.ctx+"/employe/sales?startDate="+startDate+"&endDate="+endDate;
	window.location = url;
}

/**
 * 自适应浏览器宽度
 */
function initFrameSize()
{
	var subWidth = windowAreaParams.screenWidth - 1000 - 25;
	if(subWidth > 0)
	{
		$(".body_system_jiaojieban").css("width",998+subWidth+"px");
		$(".jjb_one_jiaojieban").css("width",994+subWidth+"px");
		$(".jiaojieban_content").css("width",994+subWidth+"px");
		$(".jiaojieban_title").css("width",994+subWidth+"px");
		$(".jiaojieban_con").css("width",994+subWidth+"px");
		$(".jj_con_right").css("width",994+subWidth-235+"px");
	}
	var subHeight = windowAreaParams.screenHeight - 768;
	if(subHeight > 0)
	{
		$(".body_system_jiaojieban").css("height",712+subHeight+"px");
		$(".jiaojieban_content").css("height",705+subHeight+"px");
		$(".jj_con_left").css("height",650+subHeight+"px");
	}
}