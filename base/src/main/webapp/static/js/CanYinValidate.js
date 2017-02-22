jQuery.fn.CanYinValidate = function( options )
{
	var form = jQuery(this);
	
	
	
	jQuery.fn.CanYinValid=function() {
		for(var i=0;i<options.length;i++)
		{
			var option = options[i];
			if(option.require == true)
			{
				if(!validRequire(option.id))
				{
					toastr.error(option.name + "不能为空!");
					return false;
				}
			}
			if(option.require == false)
			{
				if(isNull(option.id))
				{
					continue;
				}
			}
			
			if(option.type)
			{
				
				if(option.type == 'email')
				{
					if(!email(option.id))
					{
						toastr.error(option.name + "邮箱格式不正确!");
						return false;
					}
				}
				if(option.type == 'date')
				{
					if(!date(option.id))
					{
						toastr.error(option.name + "日期格式不正确!");
						return false;
					}
				}
				if(option.type == 'dateISO')
				{
					if(!dateISO(option.id))
					{
						toastr.error(option.name + "日期格式不正确!");
						return false;
					}
				}
				if(option.type == 'mobile')
				{
					if(!mobile(option.id))
					{
						toastr.error(option.name + "格式不正确!");
						return false;
					}
				}
				if(option.type == 'number')
				{
					if(!number(option.id))
					{
						toastr.error(option.name + "不是数字!");
						return false;
					}
				}
				if(option.type == 'intNumberThanZero')
				{
					if(!number(option.id))
					{
						toastr.error(option.name + "不是数字!");
						return false;
					}
					else
					{
						var obj = $("#"+option.id);
						var value = parseFloat(obj.val());
						if(parseInt(value)!=value)
						{
							toastr.error(option.name + "必须是整数!");
							return false;
						}
						else if(value < 0)
						{
							toastr.error(option.name + "不能是负数!");
							return false;
						}
					}
				}
				
				if(option.type == 'numberThanZero')
				{
					if(!number(option.id))
					{
						toastr.error(option.name + "不是数字!");
						return false;
					}
					else
					{
						var obj = $("#"+option.id);
						var value = parseFloat(obj.val());
						if(value < 0)
						{
							toastr.error(option.name + "不能是负数!");
							return false;
						}
					}
				}
				
				if(option.type == 'numberLargeZero')
				{
					if(!number(option.id))
					{
						toastr.error(option.name + "不是数字!");
						return false;
					}
					else
					{
						var obj = $("#"+option.id);
						var value = parseFloat(obj.val());
						if(value <= 0)
						{
							toastr.error(option.name + "必须大于0!");
							return false;
						}
					}
					
					
				}
				
				if(option.type == 'money') {
					var obj = $("#"+option.id);
					var value = obj.val();
					var reg = /^\d+(\.\d+)?$/;
					if(!reg.test(value)) {
						toastr.error("费用格式错误!");
						return false;
					}
				}
				
				if(option.type == 'telephone') {
					var obj = $("#"+option.id);
					var value = obj.val();
					var reg = /^\d{3,4}-\d{7,8}$|^\d{7,20}$/;
					if(!reg.test(value)) {
						toastr.error("电话格式错误!");
						return false;
					}
				}
				
			}
			if(option.length)
			{
				if(!validLength(option.id,option.length))
				{
					if(option.length[0] == option.length[1])
					{
						toastr.error(option.name + "长度为"+option.length[0]+ " 位 ");
					}
					else
					{
						toastr.error(option.name + "长度为"+option.length[0] + " 到 " + option.length[1] +" 之间" );
					}
					
					return false;
				}
			}
			
			if(option.equalTo)
			{
				if(!cyValidEqule(option.id,option.equalTo))
				{
					toastr.error(option.name + "和"+ option.equalToName + " 值不一样 ");
					return false;
				}
			}
			
		}
		
		return true;
	}
	
	var isNull = function (inputId){
		var obj = $("#"+inputId);
		var v = obj.val();
		if(!v || v == null || v == '')
		{
			return true;
		}
		return false;
	}
	
	var validRequire = function (inputId){
		var obj = $("#"+inputId);
		var v = obj.val();
		if(!v)
		{
			return false;
		}
		return true;
	}
	
	var validLength = function (inputId,lengthArray){
		
		var obj = $("#"+inputId);
		var v = obj.val();
		if(!v)
		{
			return false;
		}
		if(v.length < lengthArray[0] || v.length > lengthArray[1])
		{
			return false;
		}
		return true;
	}
	
	var cyValidEqule = function(inputId,sInputId){
		var obj1 = $("#"+inputId);
		var obj2 = $("#"+sInputId);
		var v1 = obj1.val();
		var v2 = obj2.val();
		if(v1==v2)
		{
			return true;
		}
		return false;
	}
	
	
	var email = function(inputId) {
		var obj = $("#"+inputId);
		var value = obj.val();
		return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
	}
	
	var date = function( inputId) {
		var obj = $("#"+inputId);
		var value = obj.val();
		return  !/Invalid|NaN/.test(new Date(value.replaceAll("-", "/")).toString());
	}

	var dateISO = function( inputId) {
		var obj = $("#"+inputId);
		var value = obj.val();
		return  /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/.test(value);
	}

	var number = function( inputId) {
		var obj = $("#"+inputId);
		var value = obj.val();
		return  /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
	}
	
	var mobile = function( inputId) {
		var obj = $("#"+inputId);
		var value = obj.val();
		if(isNaN(value)){
			return false;
		}
		if(value < 0){
			return false;
		}
		return true;
	}
}

/* 非空中文或字母或数字
 * maxLength 最大长度
 */
jQuery.fn.canyinValidateCh = function(maxLength) {
	var value = $(this).val();
	if(value.length > maxLength) {
		return false;
	}
	value = jQuery.trim(value);
	var reg = /^[a-zA-Z0-9\u4E00-\u9FA5]+$/;
	if(!reg.test(value)) {
		return false;
	}
	return true;
}

/* 非空数字
 * minLength 最小长度 如果最大长度为空 此参数为必须位数
 * maxLength 最大长度
 */
jQuery.fn.canyinValidateNumber = function(minLength,maxLength) {
	var value = $(this).val();
	var reg;
	if(typeof(maxLength) == 'undefined') {
		reg = new RegExp("^\\d{" + minLength + "}$");
	} else {
		reg = new RegExp("^\\d{" + minLength + "," + maxLength + "}$");
	}
	if(!reg.test(value)) {
		return false;
	}
	return true;
}

/* 手机验证
 * 
 */
jQuery.fn.canyinVlidateMobile = function() {
	var value = $(this).val();
	var length = value.length;
	return (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));  
}

/* 非空验证
 *
 */
jQuery.fn.canyinValidateRequire = function (){
	var v = $.trim($(this).val());
	if(v.length == 0) {
		return false;
	}
	return true;
}

/* 长度验证
 * minLength 最小长度
 * maxLength 最大长度
 */
jQuery.fn.canyinValidateLength = function (minLength,maxLength){
	var value = $(this).val();
	if(value.length < minLength) {
		return false;
	}
	if(value.length > maxLength) {
		return false;
	}
	return true;
}

/* 钱验证 可为空
 *
 */
jQuery.fn.canyinValidateMoney = function (){
	var value = $(this).val();
	if(value.length == 0) {
		return true;
	}
	var reg = /^\d+(\.\d+)?$/;
	if(!reg.test(value)) {
		return false;
	}
	return true;
}

/* 电话验证 可为空
 * 
 */
jQuery.fn.canyinValidateTelephone = function (){
	var value = $(this).val();
	if(value.length == 0) {
		return true;
	}
	var reg = /^\d{3,4}-\d{7,8}$|^\d{7,20}$/;
	if(!reg.test(value)) {
		return false;
	}
	return true;
}

