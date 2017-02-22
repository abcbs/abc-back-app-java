//line 11071-v1.3
// bind,live,delegate,on
$(document).bind("mobileinit", function() {
	$.extend($.mobile, {
		self : {},
		test : function() {

		}
	});
});
jQuery.extend({
	addDish2Bill : function(a, b) {
		return a < b ? a : b;
	}
});
// mobileinit pagebeforecreate pagecreate pageinit
$(document).bind("pageinit", function() {
	loadDishList();
});
function loadDishList() {
	$("#dishList").load("dishList", {}, function(data) {
		console.log("loadDishList:");
		$("div[data-role='content'] ul").listview();
	});
}
$(document).ready(
		function() {
			$("div[id='dish-navbar-filter']div[data-role='navbar'] ul li")
					.bind("click", function(event, data) {
						event.preventDefault();
						loadDishList();
					});
			// 右滑显示工具栏 &&左滑显示菜类
			$("body").bind("swiperight", function(event) {
				// alert("swiperight");
			});

			var orderDishes = new Array();
			jQuery.data(document.body, 'dishData', orderDishes);
			//点击加菜
			
			$(".dish-filter").bind("swipeleft", function(event) {
				// $(this).html("swipeleft");
			});
			$("#goOrder").bind("click", function(event) {
				var nodes = new Array();
				var checks = new Array();
				$.each(nodes, function(i, n) {
					checks.push(n.id);
				});

				$.post("goOrder", {
					"dishes" : jQuery.data(document.body, 'dishData'),//{"dishesId":123,"saleNum":2},//jQuery.data(document.body, 'dishData'),
					"billId" : $("#searchform").attr("billId")
				}, function(data) {
					alert(data.message);
					console.log("ids:" + data);
				});
			});
			$(document).bind("pageloadfailed__", function(event, data) {
				// 阻止默认行为，告知浏览器本次事件由事件的回调函数（即本函数）处理
				event.preventDefault();
				// 自定义处理，尝试加载另一个页面 ,在本函数或者其它异步方法中调用 resolve(),
				// 告知 $.mobile.changePage() 方法继续页面请求并执行 done() 回调函数队列
				data.deferred.resolve(data.absUrl, data.options, page);
				alert("fail");
			});
			// var $pages = $(":jqmData(role='page'),:jqmData(role='dialog')");
			// alert($pages);
		});