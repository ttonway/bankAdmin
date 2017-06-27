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
                data.minTime = $('#daterange-btn').attr('min-time');
                data.maxTime = $('#daterange-btn').attr('max-time');
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
                            var cntMap = {};
                            for (var i = 0; i < cntList.length; i++) {
                                var map = cntList[i];
                                cntMap[map.status] = map.cnt;
                            }
                            $('.category').each(function () {
                                var cnt = cntMap[$(this).attr('status')] || 0;
                                $(this).find('span').text("(" + cnt + ")");
                            });


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
                    "class": 'from-user',
                    "data": "fromUserCode",
                    "render": function (data, type, full, meta) {
                        return data ? '<a href="#">' + data + '</a>' : '';
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

    $('#daterange-btn').daterangepicker(
        {
            locale: {
                applyLabel: '确认',
                cancelLabel: '取消',
                fromLabel: '起始时间',
                toLabel: '结束时间',
                customRangeLabel: '自定义',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
                firstDay: 1
            },
            ranges: {
                '所有': [moment(), moment()],
                '过去7天': [moment().subtract(6, 'days'), moment()],
                '过去30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')]
            },
            startDate: moment(),
            endDate: moment()
        },
        function (start, end) {
            if (start.isSame(end, 'day')) {
                $('#daterange-btn').attr('min-time', '');
                $('#daterange-btn').attr('max-time', '');
                $('#daterange-btn span').html('<i class="fa fa-calendar"></i> 所有');
            } else {
                $('#daterange-btn').attr('min-time', start.format("YYYYMMDD HHmmss"));
                $('#daterange-btn').attr('max-time', end.format("YYYYMMDD HHmmss"));
                $('#daterange-btn span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
            }

            reloadTable();
        }
    );

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
                    var dl = $('<dl class="dl-horizontal"></dl>');
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
                    } else if (map.loanType == '商易贷') {

                        dl.append('<dt>是否为本地人：</dt><dd>' + map.localPerson + '</dd>');
                        dl.append('<dt>本地是否有房产：</dt><dd>' + map.house + '</dd>');
                        dl.append('<dt>年销售额：</dt><dd>' + map.income + '</dd>');
                        dl.append('<dt>担保方式：</dt><dd>' + map.guaranteeType + '</dd>');
                        dl.append('<dt>审核情况：</dt><dd>' + map.status + '</dd>');
                    }

                    $('#details-modal .modal-body').html('');
                    $('#details-modal .modal-body').append(dl);
                    $('#details-modal').modal();
                }
            }
        });
    });

    tableEl.on("click", ".from-user a", function () {
        var data = table.row($(this).parents('tr')).data();

        $.ajax({
            type: "post",
            cache: false,
            data: {
                userCode: data.fromUserCode,
                r: Math.random()
            },
            url: "loan/getFromUser",
            success: function (res) {
                var result = $.parseJSON(res);
                if (result.code == 0) {
                    var dl = $('<dl class="dl-horizontal"></dl>');
                    if (result.admin) {
                        var map = result.admin;
                        dl.append('<dt>姓名：</dt><dd>' + map.userName + '</dd>');
                        dl.append('<dt>工号：</dt><dd>' + map.userCode + '</dd>');
                        dl.append('<dt>银行：</dt><dd>' + map.bank + '</dd>');
                        dl.append('<dt>岗位：</dt><dd>' + map.role + '</dd>');

                        $('#details-modal .modal-body').html('');
                        $('#details-modal .modal-body').append(dl);
                        $('#details-modal').modal();
                    } else if (result.partner) {
                        var map = result.partner;

                        var type;
                        if (map.partnerType == "whitecollar") {
                            type = "白领";
                        } else if (map.partnerType == "shop") {
                            type = "商家";
                        }
                        dl.append('<dt>类型：</dt><dd>' + type + '</dd>');
                        dl.append('<dt>是否为我行老客户：</dt><dd>' + map.oldCustomer + '</dd>');
                        dl.append('<dt>姓名：</dt><dd>' + map.userName + '</dd>');
                        dl.append('<dt>联系方式：</dt><dd>' + map.phoneNumber + '</dd>');
                        if (map.partnerType == "whitecollar") {
                            dl.append('<dt>单位性质：</dt><dd>' + map.workUnitType + '</dd>');
                            dl.append('<dt>单位名称：</dt><dd>' + map.workUnitName + '</dd>');
                        } else if (map.partnerType == "shop") {
                            dl.append('<dt>店面名称：</dt><dd>' + map.shopName + '</dd>');
                            dl.append('<dt>店面位置：</dt><dd>' + map.shopAddress + '</dd>');
                        }
                        dl.append('<dt>合作区域：</dt><dd>' + map.bank + '</dd>');
                        dl.append('<dt>我需要实体宣传材料：</dt><dd>' + map.needMaterial + '</dd>');
                        if (map.needMaterial == "是") {
                            dl.append('<dt>实体宣传材料：</dt><dd>' + map.materials + '</dd>');
                            dl.append('<dt>收货人：</dt><dd>' + map.receiver + '</dd>');
                            dl.append('<dt>手机号码：</dt><dd>' + map.receiverPhoneNumber + '</dd>');
                            dl.append('<dt>所在区或县：</dt><dd>' + map.receiverArea + '</dd>');
                            dl.append('<dt>详细地址：</dt><dd>' + map.receiverAddress + '</dd>');
                        }

                        $('#details-modal .modal-body').html('');
                        $('#details-modal .modal-body').append(dl);
                        $('#details-modal').modal();
                    } else {
                        $('#details-modal .modal-body').html('<p>用户不存在</p>');
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
            minTime: $('#daterange-btn').attr('min-time'),
            maxTime: $('#daterange-btn').attr('max-time'),
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
                    status: 2,
                    r: Math.random()
                },
                url: "loan/update",
                success: function (res) {
                    var result = $.parseJSON(res);
                    if (result.code == 0) {
                        $('#status2-modal .alert-success').text("通过审核成功");
                        $('#status2-modal .alert-success').show();

                        reloadTable();
                    } else {
                        $('#status2-modal .alert-danger').text("通过审核失败");
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
                url: "loan/update",
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
                url: "loan/update",
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