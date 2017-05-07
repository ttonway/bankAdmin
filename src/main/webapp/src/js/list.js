var pagenum = 1;

var bank = bank || (function(Win, Doc, $, undefined) {
	return {
		init : function() {
			$(document).on("click", ".js-pop", function() {
				var dataName = $(this).attr('data-name');
				$('[data-pop="' + dataName + '"]').show();
				$(".mark").show();
			})
			$(".close").on('click', function() {
				$(this).parents('.popup').hide();
				$(".mark").hide();
				getUser();
			})
		}
	}
})(window, document, $);

(function() {
	bank.init();
	getUser();
})();

// 通过审核
function showVerify() {
	$('#sucess').hide();
	$('#fail').hide();
	var len = $("input[type='checkbox']:checked").length;
	if (len == 0) {
		alert("请选择用户");
		return;
	}
	if (len > 1) {
		alert("请选择单个用户");
		return;
	} else {
		var usernm = $("input[type='checkbox']:checked").attr('usernm');
		var loanNum = $("input[type='checkbox']:checked").attr('loanNum');
		$('#usernm1').text(usernm);
		$('#loanNum1').text(loanNum);
		$('[data-pop="verify"]').show();
		$(".mark").show();
	}
}

function submitVerify() {
	var loanid = $("input[type='checkbox']:checked").attr('loanid');
	var remark = $("#remark1").val();
	$.ajax({
		type : "post",
		cache : false,
		data : {
			loanid : loanid,
			status : 2,
			remark : remark,
			r : Math.random()
		},
		url : "/bankAdmin/loan/updateLoan.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if (result.code == 0) {
				$('#fail').hide();
				$('#sucess').show();
				$('#sucess').html("通过审核成功");
			} else {
				$('#fail').show();
				$('#sucess').hide();
				$('#fail').html("通过审核失败 ");
			}
		}
	});
}

// 提交审核
function showstatus2() {
	$('#sucess3').hide();
	$('#fail3').hide();
	var len = $("input[type='checkbox']:checked").length;
	if (len == 0) {
		alert("请选择用户");
		return;
	}
	if (len > 1) {
		alert("请选择单个用户");
		return;
	} else {
		$('[data-pop="status2"]').show();
		$(".mark").show();
	}
}

function submitStatus2() {
	var loanid = $("input[type='checkbox']:checked").attr('loanid');
	$.ajax({
		type : "post",
		cache : false,
		data : {
			loanid : loanid,
			status : 1,
			r : Math.random()
		},
		url : "/bankAdmin/loan/updateLoan.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if (result.code == 0) {
				$('#fail3').hide();
				$('#sucess3').show();
				$('#sucess3').html("提交审核成功");
			} else {
				$('#fail3').show();
				$('#sucess3').hide();
				$('#fail3').html("提交审核失败 ");
			}
		}
	});
}

// 提交驳回
function showstatus3() {
	$('#sucess4').hide();
	$('#fail4').hide();
	var len = $("input[type='checkbox']:checked").length;
	if (len == 0) {
		alert("请选择用户");
		return;
	}
	if (len > 1) {
		alert("请选择单个用户");
		return;
	} else {
		$('[data-pop="status3"]').show();
		$(".mark").show();
	}
}

function submitStatus3() {
	var loanid = $("input[type='checkbox']:checked").attr('loanid');
	var remark = $("#remarksubmitStatus3").val();
	$.ajax({
		type : "post",
		cache : false,
		data : {
			loanid : loanid,
			status : 3,
			r : Math.random()
		},
		url : "/bankAdmin/loan/updateLoan.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if (result.code == 0) {
				$('#fail4').hide();
				$('#sucess4').show();
				$('#sucess4').html("提交驳回成功");
			} else {
				$('#fail4').show();
				$('#sucess4').hide();
				$('#fail4').html("提交驳回失败 ");
			}
		}
	});
}

// 调整
function showVerify2() {
	$('#sucess5').hide();
	$('#fail5').hide();
	var len = $("input[type='checkbox']:checked").length;
	if (len == 0) {
		alert("请选择用户");
		return;
	}
	if (len > 1) {
		alert("请选择单个用户");
		return;
	} else {
		var usernm = $("input[type='checkbox']:checked").attr('usernm');
		var loanNum = $("input[type='checkbox']:checked").attr('loanNum');
		$('#usernm2').text(usernm);
		$('#loanNum2').val(loanNum);
		$('[data-pop="verify2"]').show();
		$(".mark").show();
	}
}

function submitVerify2() {
	var loanid = $("input[type='checkbox']:checked").attr('loanid');
	var remark = $("#remark2").val();
	var loanNum = $('#loanNum2').val();
	$.ajax({
		type : "post",
		cache : false,
		data : {
			loanid : loanid,
			status : 4,
			remark : remark,
			loanNum : loanNum,
			r : Math.random()
		},
		url : "/bankAdmin/loan/updateLoan.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if (result.code == 0) {
				$('#fail5').hide();
				$('#sucess5').show();
				$('#sucess5').html("调整额度成功");
			} else {
				$('#fail5').show();
				$('#sucess5').hide();
				$('#fail5').html("调整额度失败 ");
			}
		}
	});
}

// 删除
function showDelete() {
	$('#sucess6').hide();
	$('#fail6').hide();
	var len = $("input[type='checkbox']:checked").length;
	if (len == 0) {
		alert("请选择用户");
		return;
	} else {
		$('[data-pop="delete"]').show();
		$(".mark").show();
	}
}

function submitDelete() {
	var loanid = new Array();
	var flag = 1;
	$("input[type='checkbox']:checked").each(function() {
		if ($(this).attr('status') != 0) {
			$('#fail6').show();
			$('#sucess6').hide();
			$('#fail6').html("只能删除待联系客户");
			flag = 0;
		}
		loanid.push($(this).attr('loanid'))
	});
	if(flag == 1){
		$.ajax({
			type : "post",
			cache : false,
			traditional : true,
			data : {
				loanid : loanid,
				r : Math.random()
			},
			url : "/bankAdmin/loan/deleteLoan.htm",
			success : function(res) {
				var result = $.parseJSON(res);
				if (result.code == 0) {
					$('#fail6').hide();
					$('#sucess6').show();
					$('#sucess6').html("删除成功");
				} else {
					$('#fail6').show();
					$('#sucess6').hide();
					$('#fail6').html("删除失败 ");
				}
			}
		});
	}

}

// 明细
function showDetail(loanid) {
	$.ajax({
		type : "post",
		cache : false,
		data : {
			loanid : loanid,
			r : Math.random()
		},
		url : "/bankAdmin/loan/selecDetial.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			var map = result.result;
			if (result.code == 0) {
				if (map.loanType == '邮信贷') {
					$('#usernm3').text(map.usernm);
					$('#loanNum3').text(map.loanNum);
					$('#phonenum3').text(map.phonenum);
					$('#bank3').text(map.bank);
					$('#referrals3').text(map.referrals);
					$('#workunit3').text(map.workunit);
					$('#loanmen3').text(map.loanmen);
					$('#job3').text(map.job);
					$('#income3').text(map.income);
					$('#loanHouse3').text(map.loanHouse);
					$('#loanCar3').text(map.loanCar);
					$('#loanConsumer3').text(map.loanConsumer);
					$('#status99').text(map.status);
					$('[data-pop="detail"]').show();
					$(".mark").show();
				} else if (map.loanType == '生意贷') {
					$('#usernm3').text(map.usernm);
					$('#loanNum3').text(map.loanNum1);
					$('#phonenum3').text(map.phonenum);
					$('#bank3').text(map.area + (map.bank ? '-' + map.bank : ''));
					$('#referrals3').text(map.referrals);
					$('#workunit3').text(map.workunit + (map.workunit2 ? '-' + map.workunit2 : ''));
					$('#localPerson3').text(map.localPerson);
					$('#house3').text(map.house);
					$('#income3').text(map.income + '万');
					$('#guaranteeType3').text(map.guaranteeType);
					$('#status99').text(map.status);
					$('[data-pop="detail"]').show();
					$(".mark").show();
				}
			}
		}
	});
}

function ckeckStatus(i) {
	var status = $("[name1='statuslist'].on").removeClass("on");
	$($("[name1='statuslist']")[i]).addClass("on");
	getUser();
}

function getUser() {
	var startRow = (pagenum - 1) * 10;
	var endRow = 10 + (pagenum - 1) * 10;
	var status = $("[name1='statuslist'].on").attr('status');
	var loanType = $("#loanType").val();
	$.ajax({
		type : "post",
		cache : false,
		data : {
			loanType: loanType,
			startrow : startRow,
			endrow : endRow,
			bank : $('#bank').val(),
			userrole : $('#userrole').val(),
			status : status,
			r : Math.random()
		},
		url : "/bankAdmin/loan/getLoanList.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			initPagination(result.cnt);
			if (result.code == 0) {
				var cntList = result.cntlist;
				for (i = 0; i < cntList.length; i++) {
					var map = cntList[i];
					if (map.status == 0) {
						$('#statuslist0').text("(" + map.cnt + ")");
					}
					if (map.status == 1) {
						$('#statuslist1').text("(" + map.cnt + ")");
					}
					if (map.status == 2) {
						$('#statuslist2').text("(" + map.cnt + ")");
					}
					if (map.status == 3) {
						$('#statuslist3').text("(" + map.cnt + ")");
					}
					if (map.status == 4) {
						$('#statuslist4').text("(" + map.cnt + ")");
					}
					if (map.status == 5) {
						$('#statuslist5').text("(" + map.cnt + ")");
					}					
				}
				var userList = result.result;
				var html = $.templates("#userTableTmpl").render(userList);
				$("#userTable").html(html);
				$('#showVerify').hide();
				$('#showstatus2').hide();
				$('#showstatus3').hide();
				$('#showVerify2').hide();
				$('#showDelete').hide();
				if ($('#userrole').val() == '支行营销岗') {
					$('#showstatus2').show();
					$('#showDelete').show();
				}
				if ($('#userrole').val() == '授信管理岗') {
					$('#showVerify').show();
					$('#showstatus3').show();
					$('#showVerify2').show();
				}

			}
		}
	});
}

// 分页初始化
initPagination = function(cnt) {
	$("#News-Pagination").pagination(cnt, {
		items_per_page : 10, // 每页显示多少条记录
		current_page : pagenum - 1, // 当前显示第几页数据
		num_display_entries : 3, // 分页显示的条目数
		next_text : "下一页",
		prev_text : "上一页",
		num_edge_entries : 2, // 两侧显示的首尾分页的条目数
		callback : handlePaginationClick,
		ellipse_text : '...' // 省略的页数用什么文字表示
	});

	var $pg = $(".jumpSpan input");
	$pg.focus(function(e) {
		$pg.attr("class", "jumpInputHidden");
		$(this).attr("class", "jumpInput");
	});
};

handlePaginationClick = function(new_page_index) {
	pagenum = new_page_index + 1;
	getUser();
	return false;
};

function logout() {
	location.href = "/bankAdmin/login.htm";
}



function resetUser2() {
	$('#sucess7').hide();
	$('#fail7').hide();
	$('[data-pop="reset2"]').show();
	$(".mark").show();
}

function submitReset2() {
	var userid = $("#userid").val();
	var usercode = $("#usercode").val();
	var userpw = $("#userpw4").val();
	$.ajax({
		type : "post",
		cache : false,
		data : {
			userid : userid,
			usercode : usercode,
			userPw : userpw,
			r : Math.random()
		},
		url : "/bankAdmin/adminUser/submitReset.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if (result.code == 0) {
				$('#fail7').hide();
				$('#sucess7').show();
				$('#sucess7').html("修改成功");
			} else {
				$('#fail7').show();
				$('#sucess7').hide();
				$('#fail7').html("修改失败 ");
			}
		}
	});
}


//导出商品数据
function exportList(){
	var status = $("[name1='statuslist'].on").attr('status');
    // 获取三端的URL
    var params = {
    		bank : $('#bank').val(),
			userrole : $('#userrole').val(),
			status : status,
			r : Math.random()
    };
	var url = "/bankAdmin/loan/export.htm?" + $.param(params);	
	location.href =url;
};