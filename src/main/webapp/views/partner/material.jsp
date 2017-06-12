<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta name="wap-font-scale" content="no">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="http://www.suning.com/favicon.ico" type="image/x-icon"/>

    <title>邮政自媒体合伙人计划</title>

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link href="<%=request.getContextPath()%>/static/css/partner.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">`
            <a class="navbar-brand" href="#">
                实体宣传材料
            </a>
        </div>
    </div>
</nav>

<div class="container">

    <form>

        <div class="form-group">
            <label for="material">请选择您需要的实体宣传材料</label>
            <ul class="list-group multi-select" id="material">
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>扫码牌</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>X展架</li>
            </ul>
        </div>

        <div class="form-group">
            <label for="receiver-block">请填写您的物流信息</label>
            <ul class="list-group" id="receiver-block">
                <li class="list-group-item">
                    <div class="row">
                        <label class="col-xs-4">收货人：</label>
                        <div class="col-xs-8" style="padding-left: 0;">
                            <input type="text" class="form-control" id="receiver">
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <label for="receiverPhoneNumber" class="col-xs-4 control-label">手机号码：</label>
                        <div class="col-xs-8" style="padding-left: 0;">
                            <input type="tel" class="form-control" id="receiverPhoneNumber">
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <label for="receiverPhoneNumber" class="col-xs-4 control-label">所在区或县：</label>
                        <div class="col-xs-8" style="padding-left: 0;">
                            <select class="form-control" name="workUnitType" id="receiverArea">
                                <option value="" selected disabled>请选择</option>
                                <option>射阳县</option>
                                <option>亭湖区</option>
                                <option>盐都区</option>
                                <option>东台市</option>
                                <option>大丰区</option>
                                <option>建湖县</option>
                                <option>响水县</option>
                                <option>阜宁县</option>
                                <option>滨海县</option>
                            </select>
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <label for="receiverPhoneNumber" class="col-xs-4 control-label">详细地址：</label>
                        <div class="col-xs-8" style="padding-left: 0;">
                            <input type="text" class="form-control" id="receiverAddress" placeholder="街道、楼牌号等">
                        </div>
                    </div>
                </li>
            </ul>
        </div>

        <button type="button" class="btn btn-primary btn-block" style="margin-top: 30px;" onclick="submitMaterial()">提交
        </button>
    </form>
</div>


<div class="modal fade" id="alert-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p>确认删除此员工吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/partner.js"></script>
</body>
</html>

