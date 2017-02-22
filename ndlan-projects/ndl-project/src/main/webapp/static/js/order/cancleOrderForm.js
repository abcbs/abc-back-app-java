$(document).ready(function(){
	$("#popSave").bind("click",saveSpe);
	
	$("a","#popButtonArea").click(function(){
		if(!$(this).hasClass("tuicai_on"))
		{
			$("a","#popButtonArea").removeClass("tuicai_on");
			$(this).addClass("tuicai_on");
		}
		else
		{
			$(this).removeClass("tuicai_on");
		}
	});
	
});

function saveSpe()
{
	if($("#popSaveForm").valid() == true){
		var url = $("#popSaveForm").attr("action");
		var reaId = "";
		var cancleReason = "";
		$("a","#popButtonArea").each(function(){
			if($(this).hasClass("tuicai_on"))
			{
				reaId = $(this).attr("id");
				cancleReason = $(this).attr("mes");
			}
		});
		if(reaId){
			url += "?reaId="+reaId+"&cancleReason="+cancleReason;
			$("#popSaveForm").attr("action",url);
			jQuery.ajax({
				url: url,
				data: "",
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
		}else{
			toastr.error("选择取消预订原因");
		}
		
	}
}

