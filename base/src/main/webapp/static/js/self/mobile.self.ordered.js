$(function() {
	$(document).delegate('.but_minus', 'click', function() {
		var thisDiv=$(this).parent().parent().parent();//alert(thisDiv.attr('id'));
		$.post("delDish", {tabNO:$('#tabNO').val(),dishId:thisDiv.attr('id')}, function(data) {
			console.log("delDish:" + data.message);
			var sumMoney=thisDiv.find(".sumMoney").text();
			var sum=$(document).find("#sum").text(); 
			$(document).find("#sum").text(sum-sumMoney);
			thisDiv.remove();
		});
	});

	// 烹饪备注

	$(".but_pengren").click(function() {
		toastr.info('烹饪备注?');

	});
	// 全单删除
	$("#deleteAllDish").click(function() {

		$.post("delAllDish", {
			tabNO : $('#tabNO').val()
		}, function(data) {
			//alert(data.message);
			$(".dishItem").remove();
			$(document).find("#sum").text(0);
		});

	});
	// 继续加菜

	// 呼叫下单
	$(".but_call__").click(function() {
		$.ajax({
			url : "commitOrder",
			// data: $("#Form1, #Form2, #Form3").serialize(),
			data : {
				tabNO : $('#tabNO').val()
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				console.log("but_call:" + data.message);
				toastr.info(data.message);
			}
		});
	});

});