function showFloat(e,id,tr)
{
	e.stopPropagation();
	var m=$('#'+id);
	
	m.css({
		position: "absolute"
	});
	m.show();
	var l = e.pageX + 2,
	t = e.pageY+25,
	p={
		wh:$(window).height(),
		ww:$(window).width(),
		mh:m.height(),
		mw:m.width()
	}
	t=(t+p.mh)>=p.wh?(t-=p.mh):t;//当菜单超出窗口边界时处理
//	l=(l+p.mw)>=p.ww?(l-=p.mw):l;
	l=0;
	m.css({zIndex:999998, left:l, top:t});
	
	$("body").click(function(event){
		m.hide();
		tr.removeClass("on_tr");
	});
}

function showFix(e,id,tr)
{
	e.stopPropagation();
	var m=$('#'+id);
	
	m.css({
		position: "absolute"
	});
	m.show();
	var l = e.pageX + 2,
	t = e.pageY+25,
	p={
		wh:$(window).height(),
		ww:$(window).width(),
		mh:m.height(),
		mw:m.width()
	}
	t=(t+p.mh)>=p.wh?(t-=p.mh):t;//当菜单超出窗口边界时处理
//	l=(l+p.mw)>=p.ww?(l-=p.mw):l;
	l=0;
	m.css({zIndex:999998, left:"340px", top:"80px"});
	
	$("body").click(function(event){
		m.hide();
		tr.removeClass("on_tr");
	});
}