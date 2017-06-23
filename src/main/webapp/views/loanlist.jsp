<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.psbc.pojo.AdminUserDetails" %>
<%@ page import="com.psbc.pojo.LoanUser" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/daterangepicker/daterangepicker.css">
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
                ${loanType}
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

                            <div class="box-tools">
                                <div class="input-group input-group-sm" style="width: 150px;">
                                    <button type="button" class="btn btn-default pull-right" id="daterange-btn">
                                        <span><i class="fa fa-calendar"></i> 所有</span>
                                        <i class="fa fa-caret-down"></i>
                                    </button>
                                </div>
                            </div>
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
                                    <c:if test="${requestScope.loanType eq LoanUser.LOAN_TYPE_0}">
                                        <th>工作单位</th>
                                    </c:if>
                                    <c:if test="${requestScope.loanType eq LoanUser.LOAN_TYPE_1}">
                                        <th>行业</th>
                                    </c:if>
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
                            <% if ("支行营销岗".equals(userDetails.getUserRole())) { %>
                            <a href="#" class="btn btn-default pull-left" id="showstatus2">审核</a>
                            <% } %>
                            <% if ("市分行管理岗".equals(userDetails.getUserRole()) || "支行营销岗".equals(userDetails.getUserRole())) { %>
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
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success" role="alert"></div>
                <div class="alert alert-danger" role="alert"></div>
                <p>确认通过审核吗？</p>
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


<input type="hidden" id="loanType" value='${loanType}'>

<script src="<%=request.getContextPath()%>/static/lib/jQuery/jquery-2.2.3.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="<%=request.getContextPath()%>/static/lib/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/datatables/dataTables.bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/daterangepicker/moment.min.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/daterangepicker/daterangepicker.js"></script>
<script src="<%=request.getContextPath()%>/static/lib/adminLTE/js/app.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/app.js"></script>
<script src="<%=request.getContextPath()%>/static/js/loanlist.js"></script>
</body>
</html>