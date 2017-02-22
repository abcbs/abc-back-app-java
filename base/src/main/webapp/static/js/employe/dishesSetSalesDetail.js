$(document).ready(function(){
	
});

/**
 * 打印菜肴销量详情
 * @param categoryId
 * @param startDate
 * @param endDate
 */
function print(dsCategoryId,startDate,endDate){
	jQuery.ajax({
		url: window.ctx + '/employe/printDishesSetSalesDetail/'+dsCategoryId+'?startDate='+startDate+'&endDate='+endDate,
		data: null,
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			if(data.statusCode == '1'){
				toastr.info(data.message);
			}else{
				toastr.error(data.message);
			}
			closebox();
		}
	});
}