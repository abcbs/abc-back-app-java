(function(menu) {
    jQuery.fn.contextmenu = function(options) {
        var defaults = {
            offsetX : 2,        //鼠标在X轴偏移量
            offsetY : 2,        //鼠标在Y轴偏移量
            items   : [],       //菜单项
            action  : $.noop()  //自由菜单项回到事件
        };
        var opt = menu.extend(true, defaults, options);
       
        function create(e) {
        	var m = null;
        	if(!menu("#contextmenu").attr("id"))
        	{
        		 m = menu('<div class="tanchu" id="contextmenu" ></div>').appendTo(document.body);
                 
                 menu.each(opt.items, function(i, item) {
                     if (item) {
                         if(item.type == "split"){ 
                             menu("<div class='m-split'></div>").appendTo(m);
                             return;
                         }
                         var row   = menu('<a href="###" id='+item.style+' class='+item.style+'></a>').appendTo(m);
                       
                         if (item.action) {
                             row.click(function() {
                                 item.action(e,e.target);
                             });
                         }
                     }
                 });
        	}
        	else
        	{
        		m = menu("#contextmenu");
        		m.children("a").each(function(i){
        			var row   = menu(this);
        			var item = opt.items[i];
                    if (item.action) {
                        row.click(function() {
                            item.action(e,e.target);
                        });
                    }
        		})
        		
        	}
            
            return m;
        }
        this.bind('contextmenu', function(e) {
            var m = create(e).show("fast");
            
            var isOk = tableDivClick(e,e.target);
            if(!isOk)
            {
            	m.remove();
            	return;
            }
            var left = e.pageX + opt.offsetX, top = e.pageY + opt.offsetY, p = {
                wh : menu(window).height(),
                ww : menu(window).width(),
                mh : m.height(),
                mw : m.width()
            }
            
//            top = (top + p.mh) >= p.wh ? (top -= p.mh) : top;2014-12-16 12:02:05 调整误差为40 （p.wh - 40）
            top = (top + p.mh) >= p.wh - 40 ? (top -= p.mh) : top;
            //当菜单超出窗口边界时处理
            left = (left + p.mw) >= p.ww ? (left -= p.mw) : left;
            m.css({
                zIndex : 10000,
                left : left,
                top : top
            });
            $(document.body).bind('contextmenu click', function() {
                m.hide("fast",function(){
                    m.remove();
                });        
            });
            
            return false;
        });
        return this;
    }
})(jQuery);
