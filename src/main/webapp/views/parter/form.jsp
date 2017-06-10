<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link href="<%=request.getContextPath()%>/static/css/parter.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <c:if test="${type eq 'whitecollar'}">
                    我是白领
                </c:if>
                <c:if test="${type eq 'shop'}">
                    我是商户
                </c:if>
            </a>
        </div>
    </div>
</nav>

<div class="container">
    <form>
        <div class="form-group">
            <label for="oldCustomer">是否为我行老客户</label>
            <ul id="oldCustomer" class="form-control input-single-select">
                <li name="oldCustomer">是</li>
                <li name="oldCustomer">否</li>
            </ul>
        </div>

        <div class="form-divider"></div>

        <c:if test="${type eq 'whitecollar'}">
            <div class="form-group">
                <label for="workUnitType">单位性质</label>
                <select class="form-control" name="workUnitType" id="workUnitType">
                    <option value="" selected disabled>请选择</option>
                    <option>公务员、事业单位、国有企业</option>
                    <option>公立学校、公立医院</option>
                    <option>金融、电信、烟草、电力</option>
                </select>

                <input type="text" class="form-control" name="workUnitName" id="workUnitName" placeholder="请输入单位名称"
                       style="margin-top: 5px; display: none;">
            </div>

            <div class="form-divider"></div>
        </c:if>

        <div class="form-group">
            <label for="userName">姓名</label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入姓名">
        </div>

        <c:if test="${type eq 'shop'}">
            <div class="form-group">
                <label for="shopName">店面名称</label>
                <input type="text" class="form-control" id="shopName" name="shopName">
            </div>
            <div class="form-group">
                <label for="shopAddress">店面位置</label>
                <input type="text" class="form-control" id="shopAddress" name="shopAddress">
            </div>
        </c:if>

        <div class="form-group">
            <label for="phoneNumber">联系方式</label>
            <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="请输入手机号码">
        </div>

        <button type="button" class="btn btn-primary btn-block" style="margin-top: 30px;" onclick="submitForm()">继续</button>
    </form>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/parter.js"></script>
</body>
</html>

