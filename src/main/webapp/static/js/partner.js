(function () {
    function initSelect() {
        var options1 = $('.input-single-select').find('li');
        options1.on('click', function (e) {
            var $target = $(e.target);
            $target.siblings('li').removeClass('on');
            $target.addClass('on');
        });

        var options2 = $('.input-multi-select').find('li');
        options2.on('click', function (e) {
            var $target = $(e.target);
            $target.toggleClass('on');
        });
    }

    initSelect();


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
    // var workunit1 = $('#workunit1').val();
    // var workunit2 = $('#workunit2').val();
    // var other = $('#workunit-other').val();
    //
    // if (!workunit1) {
    //     alert("情选择行业.");
    //     return;
    // }
    // if (!workunit2) {
    //     if (!$('#workunit2').is(":hidden")) {
    //         alert("情选择行业.");
    //         return;
    //     }
    //     workunit2 = '';
    // }
    // if (workunit1 == '其他') {
    //     if (!other) {
    //         alert("情选择行业.");
    //         return;
    //     }
    //     workunit1 = other;
    //     workunit2 = '';
    // }
    // if (workunit2 == '其他') {
    //     if (!other) {
    //         alert("情选择行业.");
    //         return;
    //     }
    //     workunit2 = other;
    // }
    //
    // var localPerson = $('.input-single-select li.on[name="localPerson"]').text();
    // if (!localPerson) {
    //     alert("请选是否为本地人");
    //     return;
    // }
    //
    // var house = $('.input-single-select li.on[name="house"]').text();
    // if (!localPerson) {
    //     alert("请选本地是否有房产");
    //     return;
    // }
    //
    // var income = $('#income').val();
    // income = income.replace(' ', '').replace('万元', '');
    // income = parseInt(income) * 10000;
    // if (isNaN(income)) {
    //     income = 0;
    // }
    // if (income === 0) {
    //     alert("请输入年销售额");
    //     return;
    // }
    //
    // var types = [];
    // $('.input-multi-select li.on[name="guaranteeType"]').each(function () {
    //     types.push($(this).text());
    // });
    // if (types.length === 0) {
    //     alert("请选择担保方式");
    //     return;
    // }
    // var guaranteeType = types.join("|");
    //
    // var loannum = $('#loannum').val();
    // loannum = loannum.replace(' ', '').replace('万元', '');
    // loannum = parseInt(loannum) * 10000;
    // if (isNaN(loannum)) {
    //     loannum = 0;
    // }
    // if (loannum === 0) {
    //     alert("请输入申请金额");
    //     return;
    // }

    location.href = "../area";
}

function submitArea() {
    location.href = "poster";
}

function generatePoster() {
}