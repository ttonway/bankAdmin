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

<div class="container-fluid">
    <div class="result-block">
        <img class="img-responsive" src="<%=request.getContextPath()%>/static/images/partner-result.png"/>
        <div class="text-content">您的海报已生成</div>
        <div class="text-tip">请点击查看海报，并长按保存</div>
        <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#myModal">查看海报</button>
        <a href="material" class="btn btn-primary btn-border btn-block" role="button" style="margin-top: 15px;">我需要实体宣传材料</a>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div>
                    <image class='img-responsive img-rounded'
                           src="<%=request.getContextPath()%>/partner/generate/partner-${sessionScope.partner.partnerId}/${sessionScope.partner.posterType}/${sessionScope.partner.posterFileName}"/>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/partner.js"></script>
</body>
</html>

