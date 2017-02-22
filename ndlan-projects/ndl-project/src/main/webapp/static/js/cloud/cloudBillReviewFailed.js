$(document).ready(function(){
	$(".tuicai").click(function(){
		var tuicaiSelect = $(this).attr("id");
		$(".tuicai_wrap").find("a").each(function(){
			var tuicai = $(this).attr("id");
			if(tuicaiSelect == tuicai) {
				$(this).css("background","url("+window.ctx+"/static/images/popup/popup_bg_5.png) -113px 0");
				var speOpReasonName = $(this).find("em").html();
				$("#failReasonDesc").val(speOpReasonName);
				$("#notes").val("");
			} else {
				$(this).css("background","url("+window.ctx+"/static/images/popup/popup_bg_5.png) 0 0");
			}
		});
	});
});

function focusNotes(){
	$(".tuicai_wrap").find("a").each(function(){
		$(this).css("background","url("+window.ctx+"/static/images/popup/popup_bg_5.png) 0 0");
	});
	$("#failReasonDesc").val(speOpReasonName);
}
//同步锁，每个函数一个
var syncLock = new SyncLock("popSave");

function submitReviewFailedForm(){
	var speOpReasonName = $.trim($("#failReasonDesc").val());
	var notes = $.trim($("#notes").val());
	if(speOpReasonName.length == 0 && notes.length == 0) {
		toastr.error("请选择或填写不通过原因!");
		return;
	}
	if(speOpReasonName.length == 0) {
		if(notes.length > 10) {
			toastr.error("填写不通过原因最多支持10个汉字!");
			return;
		}
	}
	if(!syncLock.Start()){
		return;
	}
	var url = $("#popReviewForm").attr("action");
	var billId = $("#billId").val();
	var takeoutOrderDiv = $("#takeoutOrderDiv").val();
	var failReasonDesc = $("#failReasonDesc").val();
	var notes = encodeURIComponent($("#notes").val());
	jQuery.ajax({
		url: url,
		data: {
			billId : billId,
			takeoutOrderDiv : takeoutOrderDiv,
			failReasonDesc : failReasonDesc,
			notes : notes
		},
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			if(data.statusCode == '200'){
				initMessage();
				toastr.success("操作成功!");
				changeCloudStatus(data.sign);
				closebox();
			}else if(data.statusCode == '300'){
				initMessage();
				toastr.warning("此账单状态已经变更："+data.sign);
				changeCloudStatus(data.sign);
				closebox();
			}
			syncLock.End();
		},error:function(){
			syncLock.End();
		}
	});
}
