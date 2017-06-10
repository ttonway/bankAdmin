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
                合作区域
            </a>
        </div>
    </div>
</nav>

<div class="container">
    <form>

        <div class="form-group">
            <label for="corporateArea">请选择合作区域</label>
            <ul class="list-group" id="corporateArea">
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>市区</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>东台</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>大丰</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>建湖</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>射阳</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>滨海</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>阜宁</li>
                <li class="list-group-item"><span class="glyphicon glyphicon-ok"></span>响水</li>
            </ul>
        </div>

        <button type="button" class="btn btn-primary btn-block" style="margin-top: 30px;" onclick="submitArea()">继续</button>
    </form>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/partner.js"></script>
</body>
</html>

