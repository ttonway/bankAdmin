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

    <title>邮储自媒体</title>

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
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                获取海报
            </a>
        </div>
    </div>
</nav>

<ul class="nav nav-tabs nav-justified">
    <li role="presentation" class="active" loan_type="0" type="邮信贷"><a href="#">消费贷海报</a></li>
    <li role="presentation" loan_type="1" type="商易贷"><a href="#">生意贷海报</a></li>
</ul>

<div class="container">
    <div class="row poster-row">
    </div>

    <div class="fixed-bottom">
    <button type="button" class="btn btn-primary btn-block" onclick="generatePoster()">
        生成海报
    </button>
    </div>
</div>

<script id="posterTmpl" type="text/x-jsrender">
    <div class="col-xs-4 col-md-3 poster" poster-id="{{:posterId}}" loan-type="{{:loanType}}" filename="{{:fileName}}" >
        <div class="thumbnail">
            <img src="image/{{:posterId}}">
        </div>
        <div class="selected-mask"><span class="glyphicon glyphicon-ok"></span></div>
    </div>
</script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/jsrender.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/partner.js"></script>
</body>
</html>

