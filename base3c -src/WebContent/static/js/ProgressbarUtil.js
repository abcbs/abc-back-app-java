
/**
 * 进度条
 */
var ProgressbarUtil = {
	show : function (parentId,height) {
//		OverlayUtil.show(parentId,height);
		var temp_float = new String;
		temp_float += "<div id=\"cy-progressbar\" class=\"ui-progressbar\"></div>";
		$("#"+parentId).append(temp_float);
		$("#"+parentId).css({
			position: "relative"
		});
		var clientHeight = height;
		var clientWidth = $("#"+parentId).width();
		var halfClient = clientHeight / 2;
		
		var barWidth = 300;
		var progressbarObj = $("#"+parentId).find("#cy-progressbar");
		
		progressbarObj.show();
		progressbarObj.css({
			display : "block",
			float: "left",
			left : ((clientWidth - barWidth) / 2) + "px",
			top : halfClient + "px"
		});
		progressbarObj.progressbar({
			value : false
		});
		var progressbar = progressbarObj;
		var progressbarValue = progressbar.find(".ui-progressbar-value");
		progressbarValue.css({
			"background" : '#' + Math.floor(Math.random() * 16777215).toString(16)
		});
	},
	showG2 : function () {
		OverlayUtil.show("body",document.body.clientHeight);
		$("body").find("#cy-G2-progressbar").remove();
		var temp_float = new String;
		temp_float += "<div id=\"cy-G2-progressbar\" class=\"ui-mini8-progressbar-wrap\"><div class=\"ui-mini8-progressbar\"></div></div>";
		$("body").append(temp_float);
		$("body").css({
			position: "relative"
		});
		var clientHeight = document.body.clientHeight;
		var clientWidth = document.body.clientWidth;
		var halfClient = clientHeight / 2;
		var barWidth = 250;
		var progressbarObj = $("body").find("#cy-G2-progressbar");
		
		progressbarObj.show();
		progressbarObj.css({
			display : "block",
			float: "left",
			left : ((clientWidth - barWidth) / 2) + "px",
			top : (halfClient - 125) + "px"
		});
	},
	hide : function (parentId) {
		$("#"+parentId).find("#cy-progressbar").hide();
		OverlayUtil.hide();
	}
	,
	hideG2 : function () {
		$("body").find("#cy-G2-progressbar").hide();
		OverlayUtil.hide("body");
	}
};

/**
 * 遮罩层
 */
var OverlayUtil = {
	show : function(parentId,height){
		var temp_float = new String;
		temp_float += "<div id=\"cy-overlayDiv\" style=\"display:none\"></div>";
		$("body").append(temp_float);
		
		var overlayobj = $("body").find('#cy-overlayDiv');
		
		overlayobj.css({'position':'relative','display':'block','background-color' : 'black', 'opacity' : '0.6','filter': 'alpha(opacity = 60)'});
		var clientHeight = document.body.clientHeight;
		var clientWidth = document.body.clientWidth;
		
		overlayobj.css('width', '100%');
		overlayobj.css('height', height+'px');

		overlayobj.css('z-index', 150);
		overlayobj.show();
	},
	hide : function(parentId){
		var overlayobj = $("body").find('#cy-overlayDiv');
		overlayobj.hide();
	}
};

