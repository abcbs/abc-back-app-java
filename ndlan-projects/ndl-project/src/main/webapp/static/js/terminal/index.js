window.ctx = "/";
var url = window.location.href;
if (url.indexOf('canyin-frontdesk') > 0) {
	window.ctx = "/canyin-frontdesk";
}

$(document).ready(function() {
	refreshDish();
//	display();
});

function refreshDish() {
	dishCatagoryChange('', diancanDish_categoryId, diancanDish_page,
			diancanDish_dsCategoryId);
}

var diancanDish_categoryId = '';
var diancanDish_dsCategoryId = '';
var diancanDish_page = 1;
function dishCatagoryChange(billId, categoryId, page, dsCategoryId) {
	second = 60;
	diancanDish_categoryId = categoryId;
	diancanDish_dsCategoryId = dsCategoryId;
	diancanDish_page = page;
	$
			.ajax({
				type : "get",
				url : window.ctx + '/self/terminal/ajax/dishesContent?page='
						+ page + '&categoryId=' + categoryId + '&dsCategoryId='
						+ dsCategoryId,
				data : null,
				cache : false,
				async : true,
				success : function(html) {
					$("#dishesAjaxContent").html(html);
				},
				error : function() {
				}
			});
}

function goPayVip() {
	$("#vipAjaxContent").show();
	$("#dishesAjaxContent").hide();
}
function goDiancan() {
	$("#dishesAjaxContent").show();
	$("#vipAjaxContent").hide();
}

function popForm(width, height) {
	$("#oriCost").text($("#billNeedMoney").val());
	new dialogHtmlBox("payInfoBoxHtml", width, height);
}

function changeTextFocus(v) {
	currentTextId = $(v).attr("id");
}

var currentTextId = "cardNo";
function numEnter(v) {
	if (v == 'clear') {
		$("#" + currentTextId).val('');
	} else if (v == 'back') {
	} else if (v == 'char') {
		$("#boardChar").show();
		$("#boardNum").hide();
	}else if (v == 'num') {
		$("#boardNum").show();
		$("#boardChar").hide();
	} else {
		var cv = $("#" + currentTextId).val();
		$("#" + currentTextId).val(cv + v);
	}
}

function getMemberCardInfo() {
	var cardNo = $("#cardNo").val();
	var cardPassword = $("#cardPassword").val();
	$.ajax({
		type : "get",
		url : window.ctx + '/self/terminal/ajax/getMemberCardInfo?cardNo='
				+ cardNo + '&cardPassword=' + cardPassword,
		data : null,
		cache : false,
		async : true,
		success : function(data) {
			if (data.statusCode == '200') {
				$("#balance").text("余额：" + data.message);
				$("#memberIntegral").text("积分：" + data.rel);
				$("#consuIntegral").text("消费积分：" + $("#billCost").text());
			} else {
				toastr.error("卡号活密码错误!");
			}
		},
		error : function() {
		}
	});
}

/**
 * 会员结账
 */
function vipPay() {
	var cardNo = $("#cardNo").val();
	var confirmCardPassword = $("#confirmCardPassword").val();
	if (!confirmCardPassword) {
		toastr.error("请输入密码!");
		return;
	}
	var billId = $("#currentBillId").val();
	var needMoney = $("#billNeedMoney").val();
	jQuery.ajax({
		url : window.ctx + '/bill/saveMoneyPayment/' + billId + '?money='
				+ needMoney,
		data : null,
		type : "POST",
		dataType : "json",
		beforeSend : function() {
		},
		success : function(data) {
			if (data.rel == '1') {
				finalPay();
			} else if (data.rel == '2')
				toastr.error(data.message);
			else {
				toastr.error("现金支付错误");
			}
		}
	});

}
function finalPay() {
	var billId = $("#currentBillId").val();
	jQuery.ajax({
		url : window.ctx + '/bill/pay/' + billId
				+ '?isPrint=true&isForce=false',
		data : null,
		type : "POST",
		dataType : "json",
		beforeSend : function() {
		},
		success : function(data) {
			// data.rel = 1:付款金额不足，不能结账,2:不满足最低消费，不能结账,3:打印失败
			if (data.rel == 1) {
				toastr.error("付款金额不足，不能结账");
			} else if (data.rel == 0) {
				toastr.info("结账完成");
				closeboxHtml();
				refreshBill();
			} else {
				toastr.info(data.message);
			}
		}
	});
}

function display() {
	var Digital = new Date();

	var hours = Digital.getHours();
	var minutes = Digital.getMinutes();
	var seconds = Digital.getSeconds();
	var dn = "AM";

	if (hours > 12) {
		dn = "PM";
		hours = hours - 12;
	}
	if (hours == 0) {
		hours = 12;
	}
	// 以上两个if让小时显示限制在1~12之间
	if (minutes <= 9) {
		minutes = "0" + minutes;
	}
	if (seconds <= 9) {
		seconds = "0" + seconds;
	}
	// 改变字体
	myclock = "" + hours + ":" + minutes + ":" + seconds + " " + dn + "";
	$(".time").text(myclock);
	setTimeout("display()", 1000);
}
