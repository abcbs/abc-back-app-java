$(document).ready(function() {

	$("#costExpendsubmitForm").CanYinValidate([ 
	{
		id : "materialConsumption",
		name : "原料消耗",
		require : false,
		type : "numberThanZero",//numberThanZero money
		length : [ 0, 15 ]
	},{
		id : "transportCost",
		name : "运输费",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "materialOther",
		name : "原料成本支出-其他",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "salary",
		name : "工资",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "foodCost",
		name : "伙食费",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "employeeBenefit",
		name : "员工福利",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "laborOther",
		name : "人工成本支出-其他",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "chummage",
		name : "房租",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "taxesCost",
		name : "税费",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "waterCost",
		name : "水费",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "electricCost",
		name : "电费",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "natgas",
		name : "天然气",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "maintain",
		name : "维修",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "materialConsumption",
		name : "消耗品",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	},{
		id : "costOther",
		name : "其他成本支出-其他",
		require : false,
		type : "numberThanZero",
		length : [ 0, 15 ]
	}
	]);
	
	$("#submit").click( function () { 
	    //校验
		if(!$("#popSaveForm").CanYinValid())
		{
			return;
		}
		if($('#materialConsumption').val()==''&&$('#transportCost').val()==''&&$('#materialOther').val()==''
			&&$('#salary').val()==''&&$('#foodCost').val()==''&&$('#employeeBenefit').val()==''&&$('#laborOther').val()==''
				&&$('#chummage').val()==''&&$('#taxesCost').val()==''&&$('#waterCost').val()==''&&$('#electricCost').val()==''&&$('#natgas').val()==''&&$('#maintain').val()==''&&$('#materialConsumption').val()==''&&$('#costOther').val()=='')
		{
			toastr.error('成本录入,至少有一项不为空');
			return;
		}
		$('#costExpendsubmitForm').submit();
	});
	
	$("#cancel").click( function () { 
		closebox();
	});
	
	$("input.jjb_input").each(function(i){
		   var name =$(this).attr('name');
		   if(name!=toEdit&&eid!=''){
			   
			   $(this).attr('disabled','disabled');
		   }
	 });
});