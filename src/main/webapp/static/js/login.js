function login() {
	$('#span').css('display', 'none');
	var usercode = $('#usercode').val();
	var userpw = $('#userpw').val();
	var params = {
		usercode : usercode,
		userpw : userpw,
		r : Math.random()
	};
	$.get("getAdminUser.htm", params, function(res) {
		var result = $.parseJSON(res).code;
		if (result == '000') {
			$('#span').text("登录名无效");
			$('#span').css('display', 'block');
			return;
		} else if (result == '002') {
			$('#span').text("密码错误");
			$('#span').css('display', 'block');
			return;
		} else {
			location.href = "main.htm?usercode="+usercode+"&r="+Math.random();
		}
	});
}