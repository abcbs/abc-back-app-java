$(document).ready(function(){
	$("#popReviewForm").CanYinValidate(
			[{
       		    id:"remarks",
       		    name:"审核备注",
       		    require:false,
       		    length:[0,200]
       		}
   	  ]);
});

//同步锁，每个函数一个
var syncLock = new SyncLock("popSave");

function submitReviewPassForm(isJudgeRm){
	//校验
	if(!$("#popSaveForm").CanYinValid())
	{
		return;
	}
	if(!syncLock.Start()){
		return;
	}
	var url = $("#popReviewForm").attr("action");
	var isXiadan = $("#isXiadan").val();
	$("#isJudgeRm").val(isJudgeRm);
	jQuery.ajax({
		url: url,
		data: $('#popReviewForm').serialize(),
		type: "POST",
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data){
			if(data.statusCode == '200'){
				changeCloudStatus(data.sign);
				var takeoutOrderDiv = data.messageMap.takeoutOrderDiv;
				if(takeoutOrderDiv == '1'){
					if(isXiadan == '1'){
						var billId = data.messageMap.billId;
						xiadan(billId);
					}else{
						toastr.success("操作成功!");
						initMessage();
						closebox();
					}
				}else{
					toastr.success("操作成功!");
					initMessage();
					closebox();
				}
				
			}else if(data.statusCode == '300'){
				//判断是否连网、会员卡余额等
				if(data.type == '1'){
					toastr.warning(data.message);
					syncLock.End();
					return;
				}
				if(isJudgeRm == 0){
		    		if(data.messageMap.result == 0){//原料库存不足，是否继续
		    			dialogBoxConfirm('原料不足',data.messageMap.message+"    是否继续?",
		    					function(){
		    						submitReviewPassForm(1);
		    					}
		    			,function (){});
			    	}else if(data.messageMap.result == 1){	//原料库存预警，请及时补充原料
				    		toastr.warning(data.messageMap.message);
				    		setTimeout(function(){
				    			submitReviewPassForm(1);
				    		},2000);
			    	}else if(data.messageMap.result == 4){	//此菜品数量不足
			    		toastr.warning(data.messageMap.message);
						changeCloudStatus(data.sign);
						closebox();
			    	}
		    	}
			}
			syncLock.End();
		},
		error:function(){
			syncLock.End();
		}
	});
}

/**
 * 下单
 */
function xiadan(billId){
	$.ajax({
	    type:"get",
	    url:window.ctx + "/bill/xiadan/"+billId,
	    data:null,
	    cache:false,
	    async:true,
	    success:function(data){
	    	var sign = data.sign;//打印是否成功;1：成功，0：失败
	    	if(sign == 1){
	    		toastr.success("操作成功!");
	    		initMessage();
	    		closebox();
	    	}
	    },
		error:function(){
		}
	  });
}
