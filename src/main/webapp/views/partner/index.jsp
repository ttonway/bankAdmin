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

    <link href="<%=request.getContextPath()%>/static/css/carousel.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img class="background" src="<%=request.getContextPath()%>/static/images/partner-bg1.png">

                <div class="carousel-caption">
                    <h4>成为合伙人条件</h4>
                    <div class="divider"></div>
                    <p>1.为我行个人贷款业务存量客户。<br/>
                        2.为国家公务员、企事业单位、国有企业、公立学校、公立医院、金融、电信、烟草、电力的正式职工。<br/>
                        3.有合法经营手续，在盐城地区有固定的经营场所。<br/>
                        （以上满足1项即可）</p>
                </div>
            </div>
            <div class="item">
                <img class="background" src="<%=request.getContextPath()%>/static/images/partner-bg2.png">
                <div class="carousel-caption">
                    <h4>合伙人的优势</h4>
                    <div class="divider"></div>
                    <p>通过微信、QQ、微博等社交平台宣传我行业务，根据浏览、申请的情况获得合伙人积分，积分可以兑换相应礼品。</p>
                </div>
            </div>
            <div class="item">
                <img class="background" src="<%=request.getContextPath()%>/static/images/partner-bg3.png">
                <div class="carousel-caption">
                    <h4>合伙人工作内容</h4>
                    <div class="divider"></div>
                    <p>1.注册申请获取广告素材<br/>
                        2.通过社交平台转发相关广告<br/>
                        3.在自有经营场所布放广告</p>
                </div>
            </div>
        </div>
        <img class="top-logo" src="<%=request.getContextPath()%>/static/images/partner-logo.png">
        <a type="button" class="btn btn-primary btn-lg" href="select">立即申请</a>
        <img class="bottom-logo" src="<%=request.getContextPath()%>/static/images/bank-logo.png">
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/carousel-swipe.js"></script>
<script>
    $("#myCarousel").carousel();
</script>

</body>
</html>
