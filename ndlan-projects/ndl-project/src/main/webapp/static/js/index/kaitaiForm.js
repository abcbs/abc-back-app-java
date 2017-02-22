$(document).ready(function(){
	$("#popSave").bind("click",saveDish);
	$("#peopleNum,#sudokuSelect").sudoku();
	
	$("#popSaveForm").CanYinValidate([
  	    {
  		    id:"tabNo",
  		    name:"餐台",
  		    require:true,
  			length:[1,36]
  		},
  		{
  		    id:"peopleNum",
  		    name:"就餐人数",
  		    require:false,
  		    type:'intNumberThanZero'
  		}
  	  ]);
	var hotKey = new CanYinHotKeys();
	hotKey.init($("body"),[{
		keyCode:"enter",
		callBackFunction:function()
		{
			saveDish(0);
		}
	},
	{
		keyCode:"esc",
		callBackFunction:function()
		{
			closebox();
		}
	}
	],1);
	
	//是否进入点餐
	$("a","#isEnterDiancaiPageDiv").bind("click",function(){
		var a = $(this);
		if(!a.hasClass("but_a_on"))
		{
			$("a","#isEnterDiancaiPageDiv").removeClass("but_a_on").addClass("but_a");
			a.removeClass("but_a").addClass("but_a_on");
			$("#isEnterDiancaiPage").val(a.attr("value"));
		}
	});
});


//同步锁，每个函数一个
var syncLock = new SyncLock("popSave");


function saveDish(isJudgeRm)
{
	if(isJudgeRm != '1'){
		isJudgeRm = '0';
	}
	if($("#popSaveForm").CanYinValid())
	{
		 if(!syncLock.Start()) 
		   {
			   return;
		   }
		   
		var url = $("#popSaveForm").attr("action");
		jQuery.ajax({
			url: url + "?rid="+MathUtil.random() + "&tableNo=" + $("#tabNo").val(),
			data: $('#popSaveForm').serialize()+"&isJudgeRm="+isJudgeRm,
			type: "POST",
			dataType: "json",
			beforeSend: function()
			{  
			},
			success: function(data)
			{
				var isOK = data.message;
				if(isOK == '1')
				{
					//开台成功
					var isEnterDiancaiPage = data.rel;
					if(isEnterDiancaiPage == 1)
					{
						window.location = window.ctx + '/bill/diancai?billId='+data.forwardUrl;
					}
					else
					{
						//判断是否是预订
						var orderId =  data.navTabId;
						if(orderId)
						{
//							refreshPage();
							window.location = window.ctx + '/index';
						}
						else
						{
							if("undefined" == typeof currentTable)
							{
								if("undefined" == typeof refreshPayPage)
								{
									
								}
								else
								{
									if(refreshPayPage)
									{
										toastr.success("操作成功");
										refreshPayPage();
										BillChange(data.forwardUrl);
									}
								}
							}
							else if(currentTable == null)
							{
								//当前页面，没有选中桌子
								refreshTable();
								toastr.success("操作成功");
							}
							else
							{
								if(currentTable)
								{
									currentTable.attr("billId",data.forwardUrl);
									refreshTable();
									toastr.success("操作成功");
								}
							}
						}
						closebox();
					}
				}else if(isOK == '2'){
					toastr.error("开台失败，无此餐台");
				}else if(isOK == '6'){
					toastr.error("开台失败，此餐台已被预订锁定。");
				}else if(isOK == '7'){
					toastr.error("开台失败，此餐台状态非空闲。");
				}else if(isOK == '8'){
					if(data.messageMap.result == 0){//原料库存不足，是否继续
		    			dialogBoxConfirm('原料不足',data.messageMap.message+"    是否继续?",
		    					function(){
		    						saveDish("1");
		    					}
		    			,function (){});
			    	}else if(data.messageMap.result == 1){	//原料库存预警，请及时补充原料
				    		toastr.warning(data.messageMap.message);
				    		setTimeout(function(){
				    			saveDish("1");
				    		},2000);
			    	}else if(data.messageMap.result == 4){	//此菜品数量不足
			    		toastr.warning(data.messageMap.message);
			    	}
				}
				else{
					toastr.error("开台失败，此台可能已经被占用");
				}
				syncLock.End();
			},
			error:function(){
				syncLock.End();
			}
		});
	}
		
	return false;
}


