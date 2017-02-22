$(document).ready(function(){
	$("#buttonSend").bind("click",saveSender);
});

/**
 * 保存派送员
 */
function saveSender(){
	var senderBy = $("#cpfl").val();
	if(senderBy == null || senderBy == ''){
		toastr.error("派送员不能为空");
		return;
	}
	$("#buttonSend").unbind("click",saveSender);
	$.ajax({
	    type:"post",
	    url:window.ctx+"/takeout/saveSender",
	    data:$("#senderForm").serialize(),
	    cache:false,
	    success:function(data){
	    	$("#buttonSend").bind("click",saveSender);
	    	if(data.statusCode == 200){
	    		searchTakeout();
	    		closebox();
	    	}
	    },
		error:function(){
			$("#buttonSend").bind("click",saveSender);
		}
	});
}