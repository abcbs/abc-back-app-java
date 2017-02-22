$(document).ready(function(){
	$("#popSave").bind("click",forcePay);
});

//强制结账
function forcePay(){
	$dialog.dialog( "close" );
	var isPrint = $("#isPrint").val();
	pay(isPrint,true);
}
