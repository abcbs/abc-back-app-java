$(document).ready(function(){
});

function billPrint(billId,printerId){
	$.ajax({
	    type:"get",
	    url:window.ctx+"/bill/billPrint/"+billId+"?printerId="+printerId,
	    data:null,
	    cache:false,
	    success:function(data){
	    	toastr.info(data.message);
	    },
		error:function(){}
	});
}

