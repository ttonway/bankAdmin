$(function () {

    function reloadPosters() {
        var grid = $('#poater-grid');
        grid.find('.poster').remove();
        var loanType = $('.category.active').attr('type');
        $.getJSON("partner/posterlist", {loanType: loanType}, function (data) {
            if (data.code == 0) {
                var cntList = data.cntlist;
                for (var i = 0; i < cntList.length; i++) {
                    var map = cntList[i];
                    $('.category[type="' + map.loanType + '"] span').text("(" + map.cnt + ")");
                }

                grid.find('.poster').remove();
                var html = $.templates("#posterTmpl").render(data.data);
                grid.append(html);
            }
        });
    }

    reloadPosters();

    $('.category').on('click', function () {
        $('.category').removeClass('active');
        $(this).addClass('active');
        reloadPosters();
    });

    $('#poater-grid').on('click', '.poster .thumbnail img', function () {
        $("#bigimage-modal").find("img").attr('src', $(this).attr('src'));
        $("#bigimage-modal").modal();
    });

    $('#file').fileinput({
        language: 'zh',
        browseClass: "btn btn-default btn-block",
        //showCaption: false,
        showRemove: false,
        showUpload: false,
        uploadUrl: "poster/upload",
        uploadExtraData: function () {
            return {
                posterName: $('#posterName').val(),
                loanType: $('#loanType').val()
            };
        },
        allowedFileTypes: ["image"]
    });

    $('#btn-add').on('click', function () {
        $('#add-modal .alert-success').hide();
        $('#add-modal .alert-danger').hide();
        $('#poserName').val('');
        $('#file').fileinput('clear');
        $('#add-modal').modal();
    });
    $('#add-modal button.btn-primary').on('click', function () {
        if ($('#file').fileinput('getFilesCount') > 0) {
            $('#file').fileinput('upload');
        }
    });
    $('#file').on('fileuploaded', function (event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;

        if (response.code == 0) {
            $('#add-modal .alert-success').text("上传成功");
            $('#add-modal .alert-success').show();

            reloadPosters();
        } else {
            $('#add-modal .alert-danger').text("上传失败");
            $('#add-modal .alert-danger').show();
        }
    });


    $('#poater-grid').on('click', '.poster .btn-danger', function () {
        var item = $(this).parents('.poster');
        var posterId = item.attr('poster-id');

        $.getJSON("poster/delete", {posterId: posterId}, function (data) {
            if (data.code == 0) {
                item.remove();

                reloadPosters();
            }
        });
    });
    $('#poater-grid').on('click', '.poster .btn-success', function () {
        var item = $(this).parents('.poster');
        var loanType = item.attr('loan-type');
        var fileName = item.attr('filename');

        var src = 'partner/generate/' + $('#userCode').val() + '/';
        if (loanType == '邮信贷') {
            src += '0/'
        } else if (loanType == '商易贷') {
            src += '1/'
        } else {
            return;
        }
        src += fileName;
        $("#bigimage-modal").find("img").attr('src', src);
        $("#bigimage-modal").modal();
    });


});