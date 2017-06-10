(function () {

    $('.input-single-select').find('li').on('click', function (e) {
        var $target = $(e.target);
        $target.siblings('li').removeClass('on');
        $target.addClass('on');

        var name = $(this).attr('name');
        $('input[name="' + name + '"]').val($(this).text());
    });

    $('#workUnitType').on('change', function () {
        var value = $(this).val();
        var input = $('#workUnitName');
        if ('金融、电信、烟草、电力' == value) {
            input.show();
        } else {
            input.hide();
        }
    });


    var listItems = $('.list-group').find('.list-group-item');
    listItems.on('click', function () {
        $(this).siblings('.list-group-item').removeClass('on');
        $(this).addClass('on');
    });

    $('.nav-tabs').find('li').on('click', function () {
        $(this).siblings('li').removeClass('active');
        $(this).addClass('active');
    });

    $('.poster').on('click', function () {
        $(this).siblings('.poster').removeClass('selected');
        $(this).addClass('selected');
    });
}());


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
        if (!$('#workUnitName').is(":hidden") && !$('#workUnitName').val()) {
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
    var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
    if (!reg.test(phoneNumber)) {
        alert("请输入正确手机号码");
        return;
    }

    $('form').submit();
}

function submitArea() {
    var area = $('.list-group-item.on').text();
    if (!area) {
        alert("请选择合作区域.");
        return;
    }

    location.href = "poster?area=" + area;
}

function generatePoster() {
}