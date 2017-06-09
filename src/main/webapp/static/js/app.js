
var myApp = {};
$(function () {
    myApp.datatablesLang = {
        "sProcessing": "处理中...",
        "sLengthMenu": "每页 _MENU_ 项",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
        "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页",
            "sJump": "跳转"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    };

    $('#reset-pwd-btn').on('click', function () {
        $('#reset-pwd-modal .alert-success').hide();
        $('#reset-pwd-modal .alert-danger').hide();
        $('#reset-pwd-modal').modal();
    });
    $('#reset-pwd-modal button.btn-primary').on('click', function () {
        var userpw = $("#new-password").val();
        $.ajax({
            type: "post",
            cache: false,
            data: {
                userPw: userpw,
                r: Math.random()
            },
            url: "admin/resetPwd",
            success: function (res) {
                var result = $.parseJSON(res);
                if (result.code == 0) {
                    $('#reset-pwd-modal .alert-success').text("修改成功");
                    $('#reset-pwd-modal .alert-success').show();
                } else {
                    $('#reset-pwd-modal .alert-danger').text("修改失败");
                    $('#reset-pwd-modal .alert-danger').show();
                }
            }
        });
    });

});