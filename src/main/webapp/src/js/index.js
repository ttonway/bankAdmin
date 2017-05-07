var pagenum = 1;

var bank = bank || (function(Win,Doc,$,undefined){
		return {
			init:function(){
				$(document).on("click",".js-pop",function(){
					var dataName = $(this).attr('data-name');
					$('[data-pop="'+dataName+'"]').show();
					$(".mark").show();
				})
				$(".close").on('click',function(){
					$(this).parents('.popup').hide();
					$(".mark").hide();
					getUser();
				})
			}
		}
	})(window,document,$);

(function(){
	
    bank.init();
    getUser();
})();


function getUser() {
	var startRow = (pagenum-1)*10;
	var endRow = 10+(pagenum-1)*10;	
	$.ajax({
		type : "post",
		cache : false,
		data : {
			startrow : startRow,
		  	endrow : endRow,
			r : Math.random()
		},
		url : "/bankAdmin/adminUser/getUserList.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			initPagination(result.cnt);
			if(result.code == 0){
				var userList = result.result;
				var html = $.templates("#userTableTmpl").render(userList);
				$("#userTable").html(html);  
			}
		}
	});
}

function addUser(){
	$('#sucess').hide();
	$('#fail').hide();
	$('[data-pop="new"]').show();
	$(".mark").show();
}


function submitUser(){
	var usernm = $('#usernm').val();
	if(usernm == ''){
		$('#fail').show();
		$('#fail').html("请填写姓名");
		return;
	}
	var usercode = $('#usercode').val();
	if(usercode == ''){
		$('#fail').show();
		$('#fail').html("请填写工号");
		return;
	}
	var bank = $('#bank').val();
	if(bank == ''){
		$('#fail').show();
		$('#fail').html("请填写银行");
		return;
	}	
	var userrole = $('#userrole').val();
	if(userrole == ''){
		$('#fail').show();
		$('#fail').html("请填写岗位");
		return;
	}	
	
	$.ajax({
		type : "post",
		cache : false,
		data : {
			usernm : usernm,
			usercode : usercode,
			bank : bank,
			userrole : userrole,
			r : Math.random()
		},
		url : "/bankAdmin/adminUser/submitUser.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if(result.code == 0){
				$('#sucess').show();
				$('#fail').hide();
				$('#sucess').html("新建成功");  
			}else if(result.code == 2){
				$('#fail').show();
				$('#sucess').hide();
				$('#fail').html("工号已存在");
			}else{
				$('#fail').show();
				$('#sucess').hide();
				$('#fail').html("新建失败");
			}
		}
	});
}

function resetUser(){
	$('#sucess2').hide();
	$('#fail2').hide();
	var len = $("input[type='checkbox']:checked").length;
	if(len == 0){
		alert("请选择用户");
		return;
	}
	if(len > 1){
		alert("请选择单个用户");
		return;
	}else{
		$('[data-pop="reset"]').show();
		$(".mark").show();
	}
}

function submitReset(){
	var userid = $("input[type='checkbox']:checked").attr('value');
	var usercode = $("input[type='checkbox']:checked").attr('usercode');
	$.ajax({
		type : "post",
		cache : false,
		data : {
			userid : userid,
			usercode : usercode,
			r : Math.random()
		},
		url : "/bankAdmin/adminUser/submitReset.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if(result.code == 0){
				$('#fail2').hide();
				$('#sucess2').show();
				$('#sucess2').html("重置成功");
			}else{
				$('#fail2').show();
				$('#sucess2').hide();
				$('#fail2').html("重置失败 ");
			}
		}
	});
}

function deleteUser(){
	$('#sucess3').hide();
	$('#fail3').hide();
	var len = $("input[type='checkbox']:checked").length;
	if(len == 0){
		alert("请选择用户");
		return;
	}else{
		$('[data-pop="delete"]').show();
		$(".mark").show();
	}
}

function submitDelete(){
	var userid = new Array();
	$("input[type='checkbox']:checked").each(
			function(){
				userid.push($(this).attr('value'))
			}
	);
	$.ajax({
		type : "post",
		cache : false,
		traditional :true,
		data : {
			userid : userid,
			r : Math.random()
		},
		url : "/bankAdmin/adminUser/deleteUser.htm",
		success : function(res) {
			var result = $.parseJSON(res);
			if(result.code == 0){
				$('#fail3').hide();
				$('#sucess3').show();
				$('#sucess3').html("删除成功");
			}else{
				$('#fail3').show();
				$('#sucess3').hide();
				$('#fail3').html("删除失败 ");
			}
		}
	});
}

//分页初始化
initPagination = function(cnt) {
  $("#News-Pagination").pagination(cnt, {
      items_per_page:10, // 每页显示多少条记录
      current_page:pagenum-1, // 当前显示第几页数据
      num_display_entries:3, // 分页显示的条目数
      next_text:"下一页",
      prev_text:"上一页",
      num_edge_entries:2, // 两侧显示的首尾分页的条目数
      callback:handlePaginationClick,
      ellipse_text:'...'  //省略的页数用什么文字表示
  });

  var $pg = $(".jumpSpan input");
  $pg .focus(function(e){
      $pg.attr("class","jumpInputHidden");
      $(this).attr("class","jumpInput");
  });
};

handlePaginationClick = function(new_page_index) {
	  pagenum = new_page_index + 1;
	  getUser();
	  return false;
	};

function logout(){
	location.href = "/bankAdmin/login.htm";
}


function resetUser2(){
	$('#sucess4').hide();
	$('#fail4').hide();
	$('[data-pop="reset2"]').show();
	$(".mark").show();
}

function submitReset2(){
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
			if(result.code == 0){
				$('#fail4').hide();
				$('#sucess4').show();
				$('#sucess4').html("修改成功");
			}else{
				$('#fail4').show();
				$('#sucess4').hide();
				$('#fail4').html("修改失败 ");
			}
		}
	});
}