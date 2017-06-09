<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.psbc.pojo.AdminUserDetails" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/datatables/dataTables.bootstrap.css">
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
                帐号密码管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">

                        <div class="box-body">
                            <table id="user-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="td-checkbox"><input type="checkbox" name="chk_all"/></th>
                                    <th>支行</th>
                                    <th>员工姓名</th>
                                    <th>员工工号</th>
                                    <th>登录密码</th>
                                    <th>登录时间</th>
                                    <th>岗位</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->

                        <div class="box-footer clearfix">
                            <a href="#" class="btn btn-default pull-left" id="addUser">新增员工</a>
                            <a href="#" class="btn btn-default pull-left" id="resetUser">重置密码</a>
                            <a href="#" class="btn btn-default pull-left" id="deleteUser">删除</a>
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


<div class="modal fade" id="add-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增员工</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p class="text-info">温馨提示：初始密码与工号相同</p>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="usernm" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="usernm" placeholder="请输入您的姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="usercode" class="col-sm-2 control-label">工号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="usercode" placeholder="请输入您的手机号码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bank" class="col-sm-2 control-label">银行</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="bank">
                                <option>市分行营业部（人民路毓龙路交界处）</option>
                                <option>市分行零售部（人民路毓龙路交界处）</option>
                                <option>亭湖区支行（青年路钱江财富广场）</option>
                                <option>盐都区支行（盐马路东进路交界处）</option>
                                <option>东台支行</option>
                                <option>大丰支行</option>
                                <option>建湖支行</option>
                                <option>射阳支行</option>
                                <option>阜宁支行</option>
                                <option>滨海支行</option>
                                <option>响水支行</option>
                                <option>小贷团队</option>
                                <option>招商场商圈专营团队</option>
                                <option>家装市场团队</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userrole" class="col-sm-2 control-label">岗位</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="userrole">
                                <option>市分行管理岗</option>
                                <option>支行营销岗</option>
                                <option>授信管理岗</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确认创建</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="reset-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">重置密码</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认重置此员工的密码吗？</p>
                <p class="text-info">初始密码与工号相同</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确认重置</button>
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
                <h4 class="modal-title">删除员工</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认删除此员工吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="<%=request.getContextPath()%>/static/lib/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/datatables/dataTables.bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/adminLTE/js/app.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/app.js"></script>
<script src="<%=request.getContextPath()%>/static/js/userlist.js"></script>
</body>
</html>
