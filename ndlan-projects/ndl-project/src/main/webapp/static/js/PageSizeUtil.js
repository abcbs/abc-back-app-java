
/**
 * 进度条
 */
var PageSizeUtil = {
	getSizeByType : function (type) {
		if(type == 'index')
		{
			return PageSizeUtil.getIndexTableSize();
		}
		else if(type == 'dish')
		{
			return PageSizeUtil.getDishsSize();
		}
		else if(type == 'member')
		{
			return PageSizeUtil.getMemberSize();
		}
		else if(type == 'bill')
		{
			return PageSizeUtil.getBillListSize();
		}
		else if(type == 'estimateDish')
		{
			return PageSizeUtil.getEstimateDishsSize();
		}
		else if(type == 'cloudBill')
		{
			return PageSizeUtil.getCloudBillSize();
		}
		else if(type == 'takeout')
		{
			return PageSizeUtil.getTakeoutSize();
		}
		return 20;
	}
	,
	getIndexTableSize : function () {
		var subWidth = windowAreaParams.screenWidth - 1000 - 25;//1366   1440
		
		var subHeight = windowAreaParams.screenHeight - 768;//643   775
		
		if(subHeight < 0)
		{
			subHeight = 0;
		}
		var row = 4;//768
		var col = 7;//1024
		var cHeight = 103+12;
		var cWidth = 103;
		var a = parseInt(subHeight/cHeight);
		row += a;
		var b = parseInt(subWidth/cWidth);
		col += b;
		return row * col;
	}
	,
	getIndexTableCol : function () {
		var subWidth = windowAreaParams.screenWidth - 1000 - 25;
		var col = 7;//1024
		var cWidth = 103;
		var b = parseInt(subWidth/cWidth);
		col += b;
		return col;
	}
	,
	getBillListSize : function () {
		var subHeight = windowAreaParams.screenHeight - 768;
		if(subHeight < 0)
		{
			subHeight = 0;
		}
		var row = 10;//768
		var cHeight = 40;
		var a = parseInt(subHeight/cHeight);
		row += a;
		return row;
	}
	,
	getDishsSize : function () {
		var subWidth = windowAreaParams.screenWidth - 1000 - 25;
		var subHeight = windowAreaParams.screenHeight - 768;
		if(subHeight < 0)
		{
			subHeight = 0;
		}
		var row = 5;//768
		var col = 6;//1024
		var cHeight = 87+10;
		var cWidth = 87+15;
		var a = parseInt(subHeight/cHeight);
		row += a;
		var b = parseInt(subWidth/cWidth);
		col += b;
		return row * col;
	},
	getEstimateDishsSize : function () {
		var subWidth = windowAreaParams.screenWidth - 1000 - 25;
		var subHeight = windowAreaParams.screenHeight - 768;
		if(subHeight < 0)
		{
			subHeight = 0;
		}
		var row = 5;//768
		var col = 8;//1024
		var cHeight = 87+10;
		var cWidth = 87+15;
		var a = parseInt(subHeight/cHeight);
		row += a;
		var b = parseInt(subWidth/cWidth);
		col += b;
		return row * col;
	},
	getMemberSize : function () {
		var subHeight = windowAreaParams.screenHeight - 768;
		if(subHeight < 0)
		{
			subHeight = 0;
		}
		var row = 12;//768
		var cHeight = 38;
		var a = parseInt(subHeight/cHeight);
		row += a;
		return row;
	},
	getCloudBillSize:function(){
		var subWidth = windowAreaParams.screenWidth - 1000 - 25;
		var subHeight = windowAreaParams.screenHeight - 768;
		if(subHeight < 0)
		{
			subHeight = 0;
		}
		var row = 4;//768
		var col = 4;//1024
		var cHeight = 120+10;
		var cWidth = 170+15;
		var a = parseInt(subHeight/cHeight);
		row += a;
		var b = parseInt(subWidth/cWidth);
		col += b;
		return row * col;
	},
	getTakeoutSize:function(){
		var subWidth = windowAreaParams.screenWidth - 1000 - 25;
		var subHeight = windowAreaParams.screenHeight - 768;
		if(subHeight < 0)
		{
			subHeight = 0;
		}
		var row = 4;//768
		var col = 4;//1024
		var cHeight = 120+10;
		var cWidth = 170+15;
		var a = parseInt(subHeight/cHeight);
		row += a;
		var b = parseInt(subWidth/cWidth);
		col += b;
		return row * col;
	}
};
