<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="" type="image/x-icon"/>
    <meta name="wap-font-scale" content="no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/base.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/login.css" />
</head>

<body>
    <div class="login-head">
        <img src="<%=request.getContextPath()%>/src/images/logo1.png" alt="">
    </div>
    <div class="login-bg">
    </div>
    <div class="login-cont">
        <div class="cont-tit">
            HELLO，欢迎登录
        </div>
        <div class="cont-form">
        	<div class="ipt-item">
                <span id="span" style="display: none;"></span>
            </div>
            <div class="ipt-item">
                <input type="text" class="name" placeholder="工号" id="usercode">
            </div>
            <div class="ipt-item">
                <input type="password" class="password error" id="userpw" placeholder="密码">
            </div>           
            <div class="clear">
                <button class="sub" onclick="login()">
                    登录
                </button> 
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/src/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/src/js/login.js"></script>
</body>
</html>