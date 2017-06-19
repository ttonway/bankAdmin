<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.psbc.pojo.AdminUserDetails" %>
<%@ page import="com.psbc.util.AuthorityUtils" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<% AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal(); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <link rel="shortcut icon" href="" type="image/x-icon"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/font-awesome-4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/ionicons-2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/bootstrap-fileinput/css/fileinput.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/adminLTE/css/AdminLTE.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/adminLTE/css/skins/skin-green.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/app.css">
</head>

<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">

    <jsp:include page="header.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                海报模版
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <a href="#" class="category active" type="邮信贷">邮信贷<span>(0)</span></a>
                            <a href="#" class="category" type="商易贷">生意贷<span>(0)</span></a>
                            <% if (AuthorityUtils.hasAuthority(userDetails, "ROLE_ADMIN")) { %>
                            <div class="pull-right box-tools">
                                <button id="btn-add" type="button" class="btn btn-primary btn-sm" title="Add">
                                    <i class="fa fa-plus"></i></button>
                            </div>
                            <% } %>
                        </div>
                        <!-- /.box-header -->

                        <div class="box-body" id="poater-grid">
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<div class="modal fade" id="bigimage-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div>
                    <image class='img-responsive img-rounded'/>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="add-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增海报模版</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="posterName" class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="posterName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="posterName" class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="loanType">
                                <option>邮信贷</option>
                                <option value="商易贷">生意贷</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="file" class="col-sm-2 control-label">文件</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="file" id="file">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">上传</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script id="posterTmpl" type="text/x-jsrender">
    <div class="col-sm-4 col-md-2 poster" poster-id="{{:posterId}}" loan-type="{{:loanType}}" filename="{{:fileName}}" >
        <div class="thumbnail">
            <img src="partner/image/{{:fileName}}.thm.jpg">
            <div class="caption">
                <h3>{{:posterName}}</h3>
                <% if (AuthorityUtils.hasAuthority(userDetails, "ROLE_ADMIN")) { %>
                <p><a href="#" class="btn btn-danger" role="button">删除</a></p>
                <% } else { %>
                <p><a href="#" class="btn btn-success" role="button">生成海报</a></p>
                <% } %>
            </div>
        </div>
    </div>
</script>

<input type="hidden" id="userCode" value='<%=userDetails.getUsername()%>'>

<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/bootstrap-fileinput/js/locales/zh.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/adminLTE/js/app.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/jsrender.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/app.js"></script>
<script src="<%=request.getContextPath()%>/static/js/poster.js"></script>
</body>
</html>
