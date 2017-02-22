
$(function(){
	$("#goOrderAAAAA").floatdiv("rightbottom");
	$("#gotop").floatdiv("middlebottom");
	$("#service").floatdiv({
		left : "21px",
		top : "170px"
	});
	
});


$( document ).on( "click", ".show-page-loading-msg", function() {
    var $this = $( this ),
        theme = $this.jqmData( "theme" ) || $.mobile.loader.prototype.options.theme,
        msgText = $this.jqmData( "msgtext" ) || $.mobile.loader.prototype.options.text,
        textVisible = $this.jqmData( "textvisible" ) || $.mobile.loader.prototype.options.textVisible,
        textonly = !!$this.jqmData( "textonly" );
        html = $this.jqmData( "html" ) || "";
    $.mobile.loading( "show", {
            text: msgText,
            textVisible: textVisible,
            theme: theme,
            textonly: textonly,
            html: html
    });
})
.on( "click", ".hide-page-loading-msg", function() {
    $.mobile.loading( "hide" );
});


function viewDish(img,event,ctx,dishesId,tabNO)
{
	event.stopPropagation();
	var tabNO= $('#tabNO').val();
	window.location = ctx+'/self/viewPic?dishesId='+dishesId+"&tabNO="+tabNO;
}
