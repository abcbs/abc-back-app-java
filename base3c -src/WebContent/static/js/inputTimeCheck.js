function validate(obj, str) {
	if (str.length == 1) {
		obj.value = str;

		if (str > 2) {
			obj.value = "0";
		}
	} else if (str.length == 2) {
		if (str > 23) {
			obj.value = "23" + ":";
		} else {
			obj.value = str + ":";
		}

	} else if (str.length == 4) {
		str = str.substr(3, 1);
		if (str > 6) {
			obj.value = obj.value + "6";
		}

	} else if (str.length == 5) {
		str = str.substr(3, 2);
		if (str > 60) {
			obj.value = obj.value.substr(0, 3) + "00" + ":";
		} else {
			obj.value = obj.value + ":";
		}

	} else if (str.length == 7) {
		str = str.substr(6, 1);
		if (str > 6) {
			obj.value = obj.value.substr(0, 6) + "0";
		}

	} else if (str.length == 8) {
		str = str.substr(6, 2);
		if (str > 60) {
			obj.value = obj.value.substr(0, 6) + "00";
		} else {
			obj.value = obj.value;
		}
		var t1 = obj.value.substr(0, 2);
		var t2 = obj.value.substr(3, 2);
		var t3 = obj.value.substr(6, 2);
		if (t1 == 23 && t2 == 60 && t3 == 60) {
			obj.value = "00:00:00";
		}

	} else if (str.length > 8) {
		obj.value = obj.value.substr(0, 8);
	}

}
function isTime(obj, str) {
	str = str + "00:00:00".substr(str.length);
	obj.value = str;
	var a = str.match(/^(\d{0,2}):(\d{0,2}):(\d{0,2})$/);
	if (a == null) {
		obj.value = "00:00:00";
		return false;
	}
	if (a[1] >= 24 || a[2] >= 60 || a[3] >= 60) {
		obj.value = "00:00:00";
		return false;
	}
	return true;
}
function onlynum(event) {
	event = jQuery.event.fix(event); // jQuery event normalization.
	if (!(event.keyCode == 46) && !(event.keyCode == 8)
			&& !(event.keyCode == 37) && !(event.keyCode == 39))
		if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)))
			event.returnValue = false;
}