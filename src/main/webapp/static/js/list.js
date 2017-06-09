$(function () {

    var tableEl = $('#loan-table');
    var table;

    function reloadTable() {

        $("input[type='checkbox'][name='chk_all']").prop("checked", false);

        table = tableEl.DataTable({
            language: myApp.datatablesLang,
            "destroy": true,
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "info": true,
            "autoWidth": false,
            "serverSide": true,
            "ajax": function (data, callback, settings) {
                data.loanType = $("#loanType").val();
                data.status = $('.category.active').attr('status');
                data.r = Math.random();
                $.ajax({
                    type: "post",
                    cache: false,
                    data: data,
                    url: "loan/list",
                    success: function (res) {
                        var result = $.parseJSON(res);
                        if (result.code == 0) {
                            var cntList = result.cntlist;
                            for (var i = 0; i < cntList.length; i++) {
                                var map = cntList[i];
                                $('.category[status="' + map.status + '"] span').text("(" + map.cnt + ")");
                            }

                            if (data.loanType == '商易贷') {
                                for (var i = 0; i < result.data.length; i++) {
                                    var map = result.data[i];
                                    if (map.workunit2) {
                                        map.workunit += '-' + map.workunit2;
                                    }
                                }
                            }
                            callback(result);
                        }
                    }
                });
            },
            "columns": [
                {
                    "class": 'td-checkbox',
                    "orderable": false,
                    "data": null,
                    "defaultContent": '<input type="checkbox" name="chk_item""/>'
                },
                {
                    "data": "fromUserCode",
                    "render": function (data, type, full, meta) {
                        return data ? '<a href="#" class="link-from-user">' + data + '</a>' : '';
                    }
                },
                {"data": "usernm"},
                {"data": "phonenum"},
                {"data": "workunit"},
                {"data": "bank"},
                {"data": "loanNum"},
                {"data": "createtime"},
                {
                    "class": 'view-details',
                    "orderable": false,
                    "data": null,
                    "defaultContent": '<a href="####">详情</a>'
                }
            ]
        });
    }

    reloadTable();
    $('.category').on('click', function () {
        $('.category').removeClass('active');
        $(this).addClass('active');
        reloadTable();
    });

    tableEl.on("change", "input[type='checkbox'][name='chk_all']", function () {
        $("input[type='checkbox'][name='chk_item']").prop("checked", $(this).is(':checked'));
    });
    tableEl.on("change", "input[type='checkbox'][name='chk_item']", function () {
        var allChecked = $("input[type='checkbox'][name='chk_item']:not(:checked)").length === 0;
        $("input[type='checkbox'][name='chk_all']").prop("checked", allChecked);
    });


    tableEl.on("click", ".view-details a", function () {
        var data = table.row($(this).parents('tr')).data();

        $.ajax({
            type: "post",
            cache: false,
            data: {
                loanid: data.loanid,
                r: Math.random()
            },
            url: "loan/get",
            success: function (res) {
                var result = $.parseJSON(res);
                var map = result.result;
                if (result.code == 0) {
                    var dl = $('#details-modal dl');
                    dl.html('');
                    dl.append('<dt>申请人：</dt><dd>' + map.usernm + '</dd>');
                    dl.append('<dt>申请额度：</dt><dd>' + map.loanNum + '</dd>');
                    dl.append('<dt>联系手机：</dt><dd>' + map.phonenum + '</dd>');
                    dl.append('<dt>经办银行：</dt><dd>' + map.bank + '</dd>');
                    dl.append('<dt>推荐人：</dt><dd>' + map.referrals + '</dd>');
                    dl.append('<dt>工作单位：</dt><dd>' + map.workunit + '</dd>');
                    if (map.loanType == '邮信贷') {

                        // dl.append('<dt>申请人数：</dt><dd>' + map.loanmen + '</dd>');
                        dl.append('<dt>个人职务：</dt><dd>' + map.job + '</dd>');
                        dl.append('<dt>月收入：</dt><dd>' + map.income + '</dd>');
                        dl.append('<dt>房贷月供：</dt><dd>' + map.loanHouse + '</dd>');
                        dl.append('<dt>车贷月供：</dt><dd>' + map.loanCar + '</dd>');
                        dl.append('<dt>其他贷款：</dt><dd>' + map.loanConsumer + '</dd>');
                        dl.append('<dt>审核情况：</dt><dd>' + map.status + '</dd>');

                        $('#details-modal').modal();
                    } else if (map.loanType == '商易贷') {

                        dl.append('<dt>是否为本地人：</dt><dd>' + map.localPerson + '</dd>');
                        dl.append('<dt>本地是否有房产：</dt><dd>' + map.house + '</dd>');
                        dl.append('<dt>年销售额：</dt><dd>' + map.income + '</dd>');
                        dl.append('<dt>担保方式：</dt><dd>' + map.guaranteeType + '</dd>');
                        dl.append('<dt>审核情况：</dt><dd>' + map.status + '</dd>');

                        $('#details-modal').modal();
                    }
                }
            }
        });
    });

    $('#export').on('click', function () {
        var loanType = $("#loanType").val();
        var status = $('.category.active').attr('status');
        // 获取三端的URL
        var params = {
            status: status,
            loanType: loanType,
            r: Math.random()
        };
        var url = "loan/export?" + $.param(params);
        location.href = url;
    });
    // 删除
    $('#showDelete').on('click', function () {
        var len = $("input[type='checkbox'][name='chk_item']:checked").length;
        if (len == 0) {
            alert("请选择用户");
            return;
        } else {
            $('#delete-modal .alert-success').hide();
            $('#delete-modal .alert-danger').hide();
            $('#delete-modal').modal();
        }
    });
    $('#delete-modal button.btn-danger').on('click', function () {
        var loanid = [];
        var flag = 1;
        $("input[type='checkbox'][name='chk_item']:checked").each(function () {
            var data = table.row($(this).parents('tr')).data();
            if (data.status != 0) {
                $('#delete-modal .alert-danger').text("只能删除待联系客户");
                $('#delete-modal .alert-danger').show();
                flag = 0;
            }
            loanid.push(data.loanid);
        });
        if (flag == 1 && loanid.length > 0) {
            $.ajax({
                type: "post",
                cache: false,
                traditional: true,
                data: {
                    loanid: loanid,
                    r: Math.random()
                },
                url: "loan/delete",
                success: function (res) {
                    var result = $.parseJSON(res);
                    if (result.code == 0) {
                        $('#delete-modal .alert-success').text("删除成功");
                        $('#delete-modal .alert-success').show();

                        reloadTable();
                    } else {
                        $('#delete-modal .alert-danger').text("删除失败");
                        $('#delete-modal .alert-danger').show();
                    }
                }
            });
        }
    });

    // 通过审核
    $('#showVerify').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        var len = checkboxs.length;
        if (len == 0) {
            alert("请选择用户");
            return;
        } else if (len > 1) {
            alert("请选择单个用户");
            return;
        } else {
            var data = table.row(checkboxs.parents('tr')).data();
            var dl = $('#verify-modal dl');
            dl.html('');
            dl.append('<dt>申请人：</dt><dd>' + data.usernm + '</dd>');
            dl.append('<dt>申请额度：</dt><dd>' + data.loanNum + '</dd>');
            dl.append('<dt>经办银行：</dt><dd>' + data.bank + '</dd>');
            $('#verify-remark').val('');

            $('#verify-modal .alert-success').hide();
            $('#verify-modal .alert-danger').hide();
            $('#verify-modal').modal();
        }
    });
    $('#verify-modal button.btn-primary').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        if (checkboxs.length == 1) {
            var data = table.row(checkboxs.parents('tr')).data();
            $.ajax({
                type: "post",
                cache: false,
                data: {
                    loanid: data.loanid,
                    status: 2,
                    remark: $('#verify-remark').val(),
                    r: Math.random()
                },
                url: "loan/update",
                success: function (res) {
                    var result = $.parseJSON(res);
                    if (result.code == 0) {
                        $('#verify-modal .alert-success').text("通过审核成功");
                        $('#verify-modal .alert-success').show();

                        reloadTable();
                    } else {
                        $('#verify-modal .alert-danger').text("通过审核失败");
                        $('#verify-modal .alert-danger').show();
                    }
                }
            });
        }
    });

    // 提交审核
    $('#showstatus2').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        var len = checkboxs.length;
        if (len == 0) {
            alert("请选择用户");
            return;
        } else if (len > 1) {
            alert("请选择单个用户");
            return;
        } else {
            $('#status2-modal .alert-success').hide();
            $('#status2-modal .alert-danger').hide();
            $('#status2-modal').modal();
        }
    });
    $('#status2-modal button.btn-primary').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        if (checkboxs.length == 1) {
            var data = table.row(checkboxs.parents('tr')).data();
            $.ajax({
                type: "post",
                cache: false,
                data: {
                    loanid: data.loanid,
                    status: 1,
                    r: Math.random()
                },
                url: "loan/updateLoan.htm",
                success: function (res) {
                    var result = $.parseJSON(res);
                    if (result.code == 0) {
                        $('#status2-modal .alert-success').text("提交审核成功");
                        $('#status2-modal .alert-success').show();

                        reloadTable();
                    } else {
                        $('#status2-modal .alert-danger').text("提交审核失败");
                        $('#status2-modal .alert-danger').show();
                    }
                }
            });
        }
    });

    // 提交驳回
    $('#showstatus3').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        var len = checkboxs.length;
        if (len == 0) {
            alert("请选择用户");
            return;
        } else if (len > 1) {
            alert("请选择单个用户");
            return;
        } else {
            $('#status3-modal .alert-success').hide();
            $('#status3-modal .alert-danger').hide();
            $('#status3-modal').modal();
        }
    });
    $('#status3-modal button.btn-primary').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        if (checkboxs.length == 1) {
            var data = table.row(checkboxs.parents('tr')).data();
            $.ajax({
                type: "post",
                cache: false,
                data: {
                    loanid: data.loanid,
                    status: 3,
                    r: Math.random()
                },
                url: "loan/updateLoan.htm",
                success: function (res) {
                    var result = $.parseJSON(res);
                    if (result.code == 0) {
                        $('#status3-modal .alert-success').text("提交驳回成功");
                        $('#status3-modal .alert-success').show();

                        reloadTable();
                    } else {
                        $('#status3-modal .alert-danger').text("提交驳回失败 ");
                        $('#status3-modal .alert-danger').show();
                    }
                }
            });
        }
    });

    // 调整
    $('#showVerify2').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        var len = checkboxs.length;
        if (len == 0) {
            alert("请选择用户");
            return;
        } else if (len > 1) {
            alert("请选择单个用户");
            return;
        } else {
            var data = table.row(checkboxs.parents('tr')).data();
            var dl = $('#verify2-modal dl');
            dl.html('');
            dl.append('<dt>申请人：</dt><dd>' + data.usernm + '</dd>');
            dl.append('<dt>经办银行：</dt><dd>' + data.bank + '</dd>');
            $('#verify2-remark').val('');
            $('#loanNum2').val('')

            $('#verify2-modal .alert-success').hide();
            $('#verify2-modal .alert-danger').hide();
            $('#verify2-modal').modal();
        }
    });
    $('#verify2-modal button.btn-primary').on('click', function () {
        var checkboxs = $("input[type='checkbox'][name='chk_item']:checked");
        if (checkboxs.length == 1) {
            var data = table.row(checkboxs.parents('tr')).data();
            $.ajax({
                type: "post",
                cache: false,
                data: {
                    loanid: data.loanid,
                    status: 4,
                    remark: $('#verify2-remark').val(),
                    loanNum: $('#loanNum2').val(),
                    r: Math.random()
                },
                url: "loan/updateLoan.htm",
                success: function (res) {
                    var result = $.parseJSON(res);
                    if (result.code == 0) {
                        $('#verify2-modal .alert-success').text("调整额度成功");
                        $('#verify2-modal .alert-success').show();

                        reloadTable();
                    } else {
                        $('#verify2-modal .alert-danger').text("调整额度失败");
                        $('#verify2-modal .alert-danger').show();
                    }
                }
            });
        }
    });
});