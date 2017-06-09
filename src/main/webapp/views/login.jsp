<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="" type="image/x-icon"/>
    <meta name="wap-font-scale" content="no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/base.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/login.css"/>
</head>

<body>
<div class="login-head">
    <img src="<%=request.getContextPath()%>/static/images/logo1.png" alt="">
</div>
<div class="login-bg">
</div>
<div class="login-cont">
    <div class="cont-tit">
        HELLO，欢迎登录
    </div>
    <form class="cont-form" action="j_spring_security_check" method='POST'>
        <div class="ipt-item">
            <c:if test="${not empty error}">
                <span class="error">${error}</span>
            </c:if>
            <c:if test="${not empty msg}">
                <span class="msg">${msg}</span>
            </c:if>
        </div>

        <div class="ipt-item">
            <input type="text" class="name" name="username" placeholder="工号">
        </div>
        <div class="ipt-item">
            <input type="password" class="password error" name="password" placeholder="密码">
        </div>
        <div class="clear">
            <button class="sub" type="submit">登录</button>
        </div>
    </form>
</div>
</body>
</html>