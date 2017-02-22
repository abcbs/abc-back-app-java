$(document).bind("pageinit", function() {
	getShoppingCarInfo();
	
});
function getShoppingCarInfo(){
	$.getJSON("getShoppingCarInfo", { tabNO:$('#tabNO').val() }, function(data){
//	  console.log("getShoppingCarInfo:" + data);
	  $("#goOrderAAAAA span a").text(data+"份");
	});
};