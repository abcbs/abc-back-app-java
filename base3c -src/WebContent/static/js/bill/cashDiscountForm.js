$(document).ready(function(){
	$("#customDiscount").bind("click",customDiscount);
});

function customDiscount(){
	if(getPermissionNoToast("discount"))
	{
		var billId = $("#billId").val();
		closebox();
		popForm('自定义折扣',window.ctx + '/bill/pop/customDiscount/'+billId,'529','644');
	}
	else
	{
		popForm('获得权限',window.ctx+'/index/pop/permission/create?functionType=1','529','353');
	}
}


function permissionCallBack(isHasPermission,functionType,id)
{
	if(functionType == '1')
	{
		var billId = $("#billId").val();
		closebox();
		popForm('自定义折扣',window.ctx + '/bill/pop/customDiscount/'+billId,'529','644');
	}
}

function chooseDiscount(ccdId,billId){
	var url = window.ctx+"/bill/cashDiscount/"+ccdId+"?billId="+billId;
	jQuery.ajax({
		url: url,
		data: null,
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			closebox();
			var billId = $("#currentBillId").val();
			BillChange(billId);
		}
	});
}

function cancleDiscount(ccdId,billId){
	var url = window.ctx+"/bill/cancle/cashDiscount/"+ccdId+"?billId="+billId;
	jQuery.ajax({
		url: url,
		data: null,
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			closebox();
			var billId = $("#currentBillId").val();
			BillChange(billId);
		}
	});

}
