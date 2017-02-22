$(document).ready(function(){
        
});

window.ctx = '/canyin-frontdesk';

function test()
{
	var url = $("#url").val();
	$.ajax({
	    type:"get",
	    url:window.ctx+url,
	    success:function(data){
			var m = data.statusCode+"\n";
			m += data.message+"\n";
	        //jquery解析list数据
			for(var i=0;i<data.list.length;i++)
			{
				 m += "\n";
				var eachData = data.list[i];
				 $.each(eachData,function(key,value){
			            m += "{"+key+":"+value+"}";
		        });
		        m += "\n";
			}
	        $("#text").text(m);
	    },
		error:function(){
	    	alert(1);
		}
	});
}
