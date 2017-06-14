(function () {

    $('.input-single-select').find('li').on('click', function (e) {
        var $target = $(e.target);
        $target.siblings('li').removeClass('on');
        $target.addClass('on');

        var name = $(this).attr('name');
        $('input[name="' + name + '"]').val($(this).text());
    });


    $('.list-group.sigle-select').find('.list-group-item').on('click', function () {
        $(this).siblings('.list-group-item').removeClass('on');
        $(this).addClass('on');
    });
    $('.list-group.multi-select').find('.list-group-item').on('click', function () {
        $(this).toggleClass('on');
    });



    var grid = $('.poster-row');

    function reloadPosters() {
        grid.find('.poster').remove();
        var loanType = $('.nav-tabs li.active').attr('type');
        $.getJSON("posterlist", {loanType: loanType}, function (data) {
            if (data.code == 0) {
                grid.find('.poster').remove();
                var html = $.templates("#posterTmpl").render(data.data);
                grid.append(html);
            }
        });
    }

    if (grid.length > 0) {
        reloadPosters();

        $('.nav-tabs').find('li').on('click', function () {
            $(this).siblings('li').removeClass('active');
            $(this).addClass('active');
            reloadPosters();
        });

        grid.on('click', '.poster', function () {
            $(this).siblings('.poster').removeClass('selected');
            $(this).addClass('selected');
        });
    }

}());

function isPhoneNumber(str) {
    var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
    return reg.test(str);
}

function submitForm() {
    if (!$('input[name="oldCustomer"]').val()) {
        alert("请选择是否为我行老客户.");
        return;
    }

    if ($('#workUnitType').length > 0) {
        if (!$('#workUnitType').val()) {
            alert("请选择单位性质.");
            return;
        }
        if (!$('#workUnitName').val()) {
            alert("请输入单位名称.");
            return;
        }
    }

    if (!$('#userName').val()) {
        alert("请输入姓名.");
        return;
    }

    if ($('#shopName').length > 0) {
        if (!$('#shopName').val()) {
            alert("请选择店面名称.");
            return;
        }
        if (!$('#shopAddress').val()) {
            alert("请输入店面位置.");
            return;
        }
    }

    var phoneNumber = $('#phoneNumber').val();
    if (!phoneNumber) {
        alert("请输入联系方式");
        return;
    }
    if (!isPhoneNumber(phoneNumber)) {
        alert("请输入正确手机号码");
        return;
    }

    $('form').submit();
}

function submitArea() {
    var area = $('#corporateArea .list-group-item.on').text();
    if (!area) {
        alert("请选择合作区域.");
        return;
    }

    location.href = "poster?area=" + area;
}

function generatePoster() {
    var poster = $('.poster.selected');
    if (poster.length == 0) {
        alert("请选择海报.");
        return;
    }
    var posterType = $('.nav-tabs li.active').attr('loan_type');

    location.href = "result?posterType=" + posterType + "&posterFileName=" + poster.attr('filename');
}


function submitMaterial() {
    var items = $('#material .list-group-item.on');
    if (items.length == 0) {
        alert("请选择宣传材料.");
        return;
    }
    var materials = [];
    items.each(function () {
        materials.push($(this).text());
    });

    var receiver = $('#receiver').val();
    var receiverPhoneNumber = $('#receiverPhoneNumber').val();
    var receiverArea = $('#receiverArea').val();
    var receiverAddress = $('#receiverAddress').val();
    if (!receiver) {
        alert("请输入收货人.");
        return;
    }
    if (!receiverPhoneNumber) {
        alert("请输入手机号码");
        return;
    }
    if (!isPhoneNumber(receiverPhoneNumber)) {
        alert("请输入正确手机号码");
        return;
    }
    if (!receiverArea) {
        alert("请选择所在区或县.");
        return;
    }
    if (!receiverAddress) {
        alert("请输入详细地址.");
        return;
    }

    $.getJSON("needMaterial", {
        materials: materials.join("|"),
        receiver: receiver,
        receiverPhoneNumber: receiverPhoneNumber,
        receiverArea: receiverArea,
        receiverAddress: receiverAddress
    }, function (data) {
        if (data.code == 0) {
            $('#alert-modal .modal-body p').text("操作成功");
        } else {
            $('#alert-modal .modal-body p').text("操作失败");
        }
        $('#alert-modal').modal();
    });
}