$(function() {
	$("#goOrderAAAAA").floatdiv({
		right : "30px",
		bottom : "50px"
	});
	$(document).delegate('#leftSlider_>a', 'click', function() {
		toggleSliderClass(this);
	});
	//呼叫服务员
	$(document).delegate('.call', 'click', function() {
		if(confirm("确定要呼叫 服务员现场服务 吗？"))
		{
			$.post("callService", {tabNO:$('#tabNO').val(),type:'5'}, function(data) {
				toastr.info(data.message);
			});
		}
		else
		{
		}
	});
	//添加餐具
	$(document).delegate('.canju', 'click', function() {
		if(confirm("确定要呼叫 添加餐具 服务 吗？"))
		{
			$.post("callService", {tabNO:$('#tabNO').val(),type:'1'}, function(data) {
				toastr.info(data.message);
			});
		}
	});
	//加菜
	$(document).delegate('.jc', 'click', function() {
		if(confirm("确定要呼叫 加菜 服务 吗？"))
		{
			$.post("callAddDish", {tabNO:$('#tabNO').val()}, function(data) {
				toastr.info(data.message);
			});
		}
	});
	//催菜
	$(document).delegate('.tjcj', 'click', function() {
		if(confirm("确定要呼叫 催菜 服务 吗？"))
		{
			$.post("urgeDish", {tabNO:$('#tabNO').val(),type:'4'}, function(data) {
				toastr.info(data.message);
			});
		}
	});
	//结账
	$(document).delegate('.jiezhang', 'click', function() {
		if(confirm("确定要呼叫 结账 服务 吗？"))
		{
			$.post("callService", {tabNO:$('#tabNO').val(),type:'2'}, function(data) {
				toastr.info(data.message);
			});
		}
	});
	//自定义呼叫
	$(document).delegate('#otherMsgSave', 'click', function() {
		$.post("callService", {tabNO:$('#tabNO').val(),type:'3',content:$('#otherMsgText').val()}, function(data) {
			toastr.info(data.message);
			$("#popupBeizhu").popup("close");
		});
	});
	$(document).delegate('#calledMessage', 'click', function() {
		toastr.info('calledMessage');
	});
});
$(document).bind("pageinit", function() {
	initCalledMessage();
});
function initCalledMessage(){
	$.post("showCalledMessage", {tabNO:$('#tabNO').val()}, function(data) {
		//$('#calledMessage').text(data.message);
		//$("#handMsg").click();
	});
};

function toggleSliderClass(selector) {
	$("#leftSlider_>a").each(function(i) {
		$(this).removeClass($(this).attr('title')).removeClass($(this).attr('title')+'_on');
		$(this).addClass($(this).attr('title'));
	});
	$(selector).addClass($(selector).attr('title')+'_on');
}