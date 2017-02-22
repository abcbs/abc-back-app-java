$(document).ready(function(){
	confirmYes();
	confirmNo();
});

function confirmYes(){
	$("#confirmYes").click(function(){
		dialogBoxConfirm("审核","确定审核通过吗？",function(){
			confirm(1);
		});
	});
}

function confirmNo(){
	$("#confirmNo").click(function(){
		dialogBoxConfirm("审核","确定审核失败吗？",function(){
			confirm(0);
		});
	});
}

function confirm(flag){
	jQuery.ajax({
		url: window.ctx+'/order/pop/ajax/confirm/' + flag,
		data: $('#popSaveForm').serialize(),
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			closebox();
			toastr.info(data.message);
			refreshPage();
		}
	});
}