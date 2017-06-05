<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.psbc.pojo.AdminUser" %>
<% AdminUser adminUser = (AdminUser) session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <link rel="shortcut icon" href="" type="image/x-icon"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/font-awesome-4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/ionicons-2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/adminLTE/css/AdminLTE.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/adminLTE/css/skins/skin-green.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/app.css">
</head>

<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="#" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>邮储</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>邮储</b>管理系统</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">

                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs"><%= adminUser.getUsernm() %></span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="<%=request.getContextPath()%>/lib/adminLTE/img/avatar5.png" class="img-circle"
                                     alt="User Image">
                                <p>
                                    <%= adminUser.getUsernm() %> - <%= adminUser.getUserrole() %>
                                    <small><%= adminUser.getBank() %>
                                    </small>
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default" id="reset-pwd-btn">修改密码</a>
                                </div>
                                <div class="pull-right">
                                    <a href="../login.htm" class="btn btn-default">注销</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header"><img src="<%=request.getContextPath()%>/src/images/logo1.png"/></li>
                <!-- Optionally, you can add icons to the links -->
                <li class="active"><a href="list1.htm"><i class="fa fa-link"></i> <span>邮信贷</span></a></li>
                <li><a href="list2.htm"><i class="fa fa-link"></i> <span>生意贷</span></a></li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                邮信贷
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <a href="#" class="category active" status="0">待联系<span>(0)</span></a>
                            <a href="#" class="category" status="1">待审核<span>(0)</span></a>
                            <a href="#" class="category" status="2">已审核<span>(0)</span></a>
                            <a href="#" class="category" status="3">已驳回<span>(0)</span></a>
                            <a href="#" class="category" status="4">待调整<span>(0)</span></a>
                            <a href="#" class="category" status="5">已删除<span>(0)</span></a>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="loan-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="td-checkbox"><input type="checkbox" name="chk_all"/></th>
                                    <th>来源</th>
                                    <th>申请人</th>
                                    <th>联系手机</th>
                                    <th>工作单位</th>
                                    <th>申请支行</th>
                                    <th>申请金额</th>
                                    <th>申请时间</th>
                                    <th class="view-details">查看详情</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->

                        <div class="box-footer clearfix">
                            <a href="#" class="btn btn-default pull-left" id="export">下载</a>
                            <% if ("授信管理岗".equals(adminUser.getUserrole())) { %>
                            <a href="#" class="btn btn-default pull-left" id="showVerify">审核</a>
                            <a href="#" class="btn btn-default pull-left" id="showstatus3">驳回</a>
                            <a href="#" class="btn btn-default pull-left" id="showVerify2">调整</a>
                            <% } %>
                            <% if ("支行营销岗".equals(adminUser.getUserrole())) { %>
                            <a href="#" class="btn btn-default pull-left" id="showstatus2">提交至审核</a>
                            <% } %>
                            <% if ("市分行管理岗".equals(adminUser.getUserrole()) || "支行营销岗".equals(adminUser.getUserrole())) { %>
                            <a href="#" class="btn btn-default pull-left" id="showDelete">删除</a>
                            <% } %>
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


<div class="modal fade" id="details-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">详情</h4>
            </div>
            <div class="modal-body">
                <dl class="dl-horizontal">
                </dl>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认删除吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="verify-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认审核通过吗？</p>
                <dl class="dl-horizontal">
                    <dt>申请人：</dt>
                    <dd></dd>
                    <dt>申请额度：</dt>
                    <dd></dd>
                    <dt>经办银行：</dt>
                    <dd></dd>
                </dl>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="verify-remark" class="col-sm-2 control-label">填写备注：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="verify-remark" placeholder="请输入备注信息（非必填）"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">通过审核</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="status2-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提交审核</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认提交审核吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确认审核</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="status3-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提交驳回</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认提交驳回吗？</p>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="status3-remark" class="col-sm-2 control-label">填写备注：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="status3-remark" placeholder="请输入备注信息（非必填）"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">驳回</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="verify2-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">调整额度</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认调整额度吗？</p>
                <dl class="dl-horizontal">
                    <dt>申请人：</dt>
                    <dd></dd>
                    <dt>经办银行：</dt>
                    <dd></dd>
                </dl>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="loanNum2" class="col-sm-2 control-label">申请额度：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="loanNum2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="verify2-remark" class="col-sm-2 control-label">填写备注：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="verify2-remark" placeholder="请输入备注信息（非必填）"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">调整额度</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="reset-pwd-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认修改密码吗？</p>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="new-password" class="col-sm-2 control-label">密码：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new-password">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确认修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<input type="hidden" id="loanType" value='邮信贷'>

<script src="<%=request.getContextPath()%>/lib/jQuery/jquery-2.2.3.min.js"></script>
<script src="<%=request.getContextPath()%>/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="<%=request.getContextPath()%>/lib/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/lib/datatables/dataTables.bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/lib/adminLTE/js/app.min.js"></script>
<script src="<%=request.getContextPath()%>/src/js/app.js"></script>
<script src="<%=request.getContextPath()%>/src/js/list.js"></script>
</body>
</html>
