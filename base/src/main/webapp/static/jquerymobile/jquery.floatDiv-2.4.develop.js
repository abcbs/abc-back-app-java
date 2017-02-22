/*
=========== 插件可设置参数及默认值 ===========
onOff:true, //默认开启,此项一般不作设置
width:"", //宽度
height:"", //高度
cover:true, //是否遮罩，默认有
coverDiv:"floatDivCover", //遮罩层挂勾(填写元素id，此项一般不作设置)
coverColor:"#000000", //遮罩背景颜色
fade:50, //遮罩透明度
space:0, //沿边间距
level:"center", //水平排列方式(left,center,right)
vertical:"middle", //竖直排列方式(top,middle,bottom)
close:"", //关闭按键，默认无(支持CSS选择器语法)
drag:"", //拖拽按键(须为对象内元素)，默认无(支持CSS选择器语法)，若值为字符串"self"，则自身为按键
dragLimit:true, //拖拽区域限制，默认限制在窗口可视区内
ifScroll:true, //是否滚动模式（层是否随滚动条滚动），默认为滚动模式
scrollBar:false //窗口滚动条是否可用（非滚动模式下有效）
====================================
插件版本：V 2.4
@作者:路卡卡
QQ:1599538531
2012-04-27
====================================
PS:
	同时解决在万恶的IE6下两个问题：select不能遮住问题；改善滚动时层抖动问题(还有轻微，日后找到更好的办法再更新);
	支持ESC键关闭;
注意：
	1、如果浮动层里是ajax动态加载内容且层未定宽高，请在ajax回调函数里取得数据之后的位置调用本插件方法，否则层位置可能产生偏差；
	2、如果在自定义方法执行过程中需要关闭请使用$("selector").floatDiv({onOff:false})，如ajax的回调[立即触发]。其它关闭如单击关闭设置close即可[事件触发]；
	3、同时设置ifScroll:false和scrollBar:false将造成锁屏效果；
	4、IE6的情况下，当且仅当非滚动模式（ifScroll:false）支持拖拽！
====================================
2011-12-21更新：
	⊙增加：选择器判断；
	
2012-01-16更新：
	⊙改进：只在IE6下监听滚动事件，非IE6下插件灵敏度提升；
	⊙增加：自定义滚动模式，非滚动模式下可设置窗口滚动条是否可用；
	⊙增加：浮动层拖拽功能;
	
2012-01-17更新：
	⊙增加：浮动层拖拽区域控制；
	⊙改进：拖拽性能改进；
	
2012-02-16更新：
	⊙改进：窗口滚动(scroll)及窗口改变(resize)改为分流模式（延时响应），更省资源；
	⊙改进：清理变量；
	⊙改进：针对IE6 select bug的改进 — 改进iframe模式下select会闪的问题；
	
2012-02-20更新：
	⊙改进：解决初始小窗口加载，最大化后遮罩宽度不能铺满的Bug;
	
2012-03-02更新：
	⊙改进：解决非滚动模式下层定位不准确的问题;
	
2012-03-23更新：
	⊙改进：关闭句柄支持CSS选择器语法;
	
2012-04-27更新：
	⊙改进：梳理优化变量缓存;
	⊙改进：参数名称简化，增加后续链式支持;
	⊙改进：遮罩性能优化;
	
====================================
*/
;(function($){
	$.fn.extend({
		floatDiv : function(opt){
			if( !$(this).length ){return}; // 选择器判断
			// 参数初始化
			opt = $.extend({
				onOff : true, // 默认开启
				width : "", // 宽度
				height : "", // 高度
				cover : true, // 是否遮罩，默认有遮罩
				coverDiv : "floatDivCover", // 遮罩层挂勾（遮罩模式下有效）
				coverColor : "#000000", // 遮罩背景颜色（遮罩模式下有效）
				fade : 50, // 遮罩透明度，默认一半一半（遮罩模式下有效）
				space : 0, // 沿边间距（针对层靠近窗口边沿的情况），默认无
				level : "center", // 水平排列方式，默认水平居中
				vertical : "middle", // 竖直排列方式，默认垂直居中
				close : "", // 关闭按键，默认无(支持CSS选择器语法)
				drag : "", // 拖拽按键(须为对象内元素)，默认无(支持CSS选择器语法)
				dragLimit : true, // 拖拽区域限制，默认有
				ifScroll : true, // 是否滚动模式（层是否随滚动条滚动），默认为滚动模式
				scrollBar : false // 窗口滚动条是否可用（非滚动模式下有效，默认不可用）
			},opt);
			
			// 全局变量
			var obj = $(this),
				selfWidth, // 对象宽度
				selfHeight, // 对象高度
				maxZ = 0, // 文档Z轴最大值
				browserVersion = null, // 浏览器版本
				dragX = 0,dragY = 0, // 拖拽偏移
				dragFlag = false, // 拖拽标记
				PluginVersion = "2.4", // 版本号
				Window = $(window), // 缓存window对象
				Document = $(document), // 缓存document对象
				
				// 初始化操作
				init = function(){
					// IE6执行
					if( browserVersion == "ie6" ){					
						if( opt.ifScroll ){
							// 改善滚动时层抖动问题					
							$("head").append("<style>html{ background-image:url(about:blank); background-attachment:fixed;}</style>");
						};
						// 取得页面内显式的下拉菜单
						hideSelect = $("select:visible");
					};
					// 取得Z轴最大值
					maxZ = getMaxZindex();
					// 判断是否绑定关闭句柄
					if( typeof opt.close == 'string' && opt.close != '' ){
						$(opt.close).click(function(){
							closeEvent();
						});
					};
					selfWidth = opt.width == "" ? obj.width() : opt.width; // 宽度判断
					selfHeight = opt.height == "" ? obj.height() : opt.height; // 高度判断
					// 层初始化
					obj.width(selfWidth).height(selfHeight).show();
					// 判断是否随滚动条滚动
					if( opt.ifScroll ){
						// 层定位方式
						if( browserVersion == "ie6" ){
							obj.css("position","absolute");
						}
						else{
							obj.css({"position":"fixed"});
						};
					}else{
						obj.css({"position":"absolute"});
					};
					// 非滚动模式隐藏滚动条
					if( !opt.ifScroll && !opt.scrollBar ){
						$("html").css("overflow","hidden");
					};
					// 有遮罩层执行
					if( opt.cover ){
						// 生成遮罩层[一次]
						if( !$("#" + opt.coverDiv).length ){
							$("body").append("<div id = " + opt.coverDiv + "></div>");
						};
						// 把遮罩DIV的ID储存在元素上
						obj.data("cover",opt.coverDiv);
						coverFix();
					}
					// 没有遮罩层且为IE6执行[针对IE6 select Bug]
					else{
						if( browserVersion == "ie6" && hideSelect.length ){
							// 第一次调用添加遮挡iframe
							if( $("#framezd").length == 0 ){
								$("body").append('<iframe id="framezd" scrolling="no" frameborder="0"></iframe>');	
							}
							$("#framezd").width( selfWidth ).height( selfHeight ).css({"position" : "absolute","left" : "0px","top" : "0px","z-index" : maxZ + 1,"opacity" : 0,"filter" : "alpha(opacity=0)"}).show();
						};	
					};
					keepfix( opt.level,opt.vertical );
				},
				
				// 遮罩定位方法
				coverFix = function(){
					var doc = document.compatMode ? document.documentElement : document.body, // 判断浏览器渲染模式
						scrollWidth = doc.scrollWidth, // 文档滚动宽度
						scrollHeight = doc.scrollHeight; // 文档滚动高度
					// 遮罩层操作
					$( "#" + opt.coverDiv ).width( scrollWidth ).height( scrollHeight ).css({"position" : "absolute","left" : "0px","top" : "0px","display" : "none","background" : opt.coverColor,"opacity" : opt.fade / 100,"filter" : "alpha(opacity=" + opt.fade + ")","z-index" : maxZ + 2}).show();
					// 若IE6并且存在非隐藏的下拉菜单
					if( browserVersion == "ie6" && hideSelect.length ){
						hideSelect.hide();
					};
				},

				// 层定位方法
				keepfix = function( level,vertical ){
					var winWidth = Window.width(),
						winHeight = Window.height(),
						scrollLeft = Window.scrollLeft(),
						scrollTop = Window.scrollTop(),
						_left_l = opt.space, // 水平左
						_left_c = (winWidth - selfWidth)/2,
						_left_r = winWidth - selfWidth - opt.space, // 水平右
						_top_t = opt.space, // 垂直上
						_top_m = ( winHeight - selfHeight ) / 2,
						_top_b = winHeight - selfHeight - opt.space; // 垂直下
						_left = level == "left" ? _left_l : ( level == "center" ? _left_c : _left_r ); // 判断水平位置排列
						_top = vertical == "top" ? _top_t : ( vertical == "middle" ? _top_m : _top_b ); // 判断垂直位置排列
					// IE6坑爹模式
					if( browserVersion == "ie6" ){
						_left += scrollLeft;
						_top += scrollTop;
						// 无遮罩模式[针对IE6 select Bug]
						if( !opt.cover && hideSelect.length ){
							$("#framezd").css({
								"z-index" : maxZ + 1,
								"left" : _left + "px",
								"top" : _top + "px"
							});
						};
					}
					// 非IE6并且为非滚动模式
					else if( !opt.ifScroll ){
						_left += scrollLeft;
						_top += scrollTop;
					};
					// 正常模式
					obj.css({
						"z-index" : maxZ + 3,
						"left" : _left + "px",
						"top" : _top + "px"
					});
				},
			
				// 拖拽
				dragEvent = function(){
					var drag = opt.drag == "self" ? obj : obj.find(opt.drag),
						_move = false,
						posX = 0,posY = 0,x = 0,y = 0;
					// 监听鼠标按下
					drag.mousedown(function(e){
						posX = e.pageX - parseInt( obj.css("left") );
						posY = e.pageY - parseInt( obj.css("top") );
						_move = true;
					});
					if( opt.drag != "self" ){
						drag.css("cursor","move");
					};
					// 监听鼠标拖动
					Document.mousemove(function(ev){
						if( _move ){
							x = ev.pageX - posX;
							y = ev.pageY - posY;
							dragFlag = true;
							var scrollTop = Window.scrollTop(),
								x_Boundary = Window.width() - obj.width(), // X轴边界
								y_Boundary = Document.height() - obj.height(); // Y轴边界
							// 拖拽区域限制
							if( opt.dragLimit ){
								x = x < 0 ? 0 : x;
								x = x > x_Boundary ? x_Boundary : x;
								y = y < 0 ? 0 : y;
								y = y > y_Boundary ? y_Boundary : y;
								// 锁屏情况拖拽区域限制在当前视口
								if( !opt.ifScroll && !opt.scrollBar ){
									y = y > Window.height() + scrollTop - obj.height() ? Window.height() + scrollTop - obj.height() : y;
								};							
							};
							obj.css({
								"left" : x,
								"top" : y
							});
							// 阻止默认动作
							ev.preventDefault();
						};
					}).mouseup(function(){
						if( _move ){
							dragX = x; // 赋给全局变量
							dragY = y; // 赋给全局变量
							_move = false;
						};
					});
				},
			
				// 关闭事件
				closeEvent = function(){
					obj.css("z-index",0).hide();
					var TempDiv = $( "#" + obj.data("cover") ), framezd = $("#framezd");
					if( TempDiv.length ){
						TempDiv.css("z-index",0).hide();
					};
					if( framezd.length ){
						framezd.css("z-index",0).hide();
					};
					// 非滚动模式还原
					if( !opt.ifScroll && !opt.scrollBar ){
						$("html").css("overflow","");
					};
					// IE6还原下拉菜单状态
					if( browserVersion == "ie6" && hideSelect && hideSelect.length ){
						hideSelect.show();	
					};
				},
			
				// 返回文档Z轴上最大的值
				getMaxZindex = function(){
					var maxIndex = 0;
					$("*").each(function(){
						var thisIndex = $(this).css("z-index");
						maxIndex = maxIndex < parseInt(thisIndex) ? parseInt(thisIndex) : maxIndex;
					});
					return maxIndex;
				};

			// 开关
			if( opt.onOff ){
				// 检测IE6
				if( navigator.userAgent.indexOf("MSIE 6.0") != -1 ){
					browserVersion = "ie6";
					var hideSelect;
				};
				// IE6且为滚动模式 监听窗口滚动（函数分流）
				if( browserVersion == "ie6" && opt.ifScroll ){
					Window.bind("scroll",function(){
						var tid = null;
						if( tid ){clearTimeout(tid)};
						tid = setTimeout(function(){
							keepfix(opt.level,opt.vertical);
						},300);
					});
				};
				// 监听窗口改变
				Window.bind("resize",function(){
					// 窗口改变则对遮罩宽高重新赋值
					var doc = document.compatMode ? document.documentElement : document.body; // 判断浏览器渲染模式
					$( "#" + opt.coverDiv ).width( doc.scrollWidth ).height( doc.scrollHeight );
					keepfix( opt.level,opt.vertical );
				});
				// ESC键关闭
				Document.bind("keyup",function(e){
					// var key=e.keyCode ? e.keyCode : e.which;
					if( e.which == 27 && obj.is(":visible") ){
						closeEvent();
					};						  
				});
				// 判断是否绑定拖拽
				if( obj.find( opt.drag).length || opt.drag == "self" ){
					// IE6下不支持滚动模式下拖拽！
					if( browserVersion == "ie6" ){
						if( !opt.ifScroll ){
							dragEvent();	
						};
					}else{
						dragEvent();
					};
				};
				// 开始初始化
				init();
			}else{
				closeEvent();
			};
			// 返回自己（便于链式操作）
			return obj;
		}			
	});	  
})(jQuery);