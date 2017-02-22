$(".dish-filter").bind("tap", function(event) {
	$("#searchform").append($(this).attr("lang") + "\<br/>");
	jQuery.data(document.body, 'foo', {
		first : 16,
		last : "pizza!"
	});
	// alert($.data(document.body, 'foo').a);
	var tempOrderData = jQuery.data(document.body, 'dishData');
	// alert($.isArray(tempOrderData));
	tempOrderData.push($(this).attr("lang"));
});