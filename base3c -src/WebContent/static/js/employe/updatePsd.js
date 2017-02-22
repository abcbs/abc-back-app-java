$(document).ready(function(){
	$("#psdSubmit").bind("click",savePsd);
	
});

function savePsd()
{
	if($("#psdForm").CanYinValid())
	{
		var url = $("#psdForm").attr("action");
		jQuery.ajax({
			url: url,
			data: $('#psdForm').serialize(),
			type: "POST",
			dataType: "json",
			beforeSend: function(){
			},
			success: function(data){
				toastr.success(data.message);
				$("input[name!='id']").val('');
			}
		});
	}
	return false;
}
