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
		$("a","#popButtonArea").each(function(){
			if($(this).hasClass("tuicai_on"))
			{
				reaId = $(this).attr("id");
			}
		});
		if(reaId)
		{
			url += "?reaId="+reaId
			$("#popSaveForm").attr("action",url);
			$("#popSaveForm").submit();
		}
		else
		{
			toastr.error("选择反结账原因");
		}
		
	}
}

