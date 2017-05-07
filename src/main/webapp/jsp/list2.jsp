<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="" type="image/x-icon"/>
    <meta name="wap-font-scale" content="no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/base.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/index.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/pagination.css"/>
</head>

<body>
<div class="index-cont clearfix">
    <div class="index-left ml">
        <div class="cont-head">
            <img src="<%=request.getContextPath()%>/src/images/logo1.png"/>
        </div>
        <div class="cont-list">
            <a href="list1.htm">邮信贷</a>
        </div>
        <div class="cont-list active">
            <a href="list2.htm">生意贷</a>
        </div>
        <div class="left-bg"></div>
    </div>
    <div class="index-right">
        <div class="cont-head">
            <a class="head-span modi" href="javascript:resetUser2()">
                修改密码
            </a>
            <span class="head-name">
                    <%=session.getAttribute("usernm")%>
                </span>
            <a class="head-span logout" href="javascript:logout()">
                注销
            </a>
        </div>
        <div class="right-wrap">
            <div class="rw-tit">
                帐号密码管理
                <div class="rw-tit-list">
                    <a href="javascript:ckeckStatus(0)" class="on" name1="statuslist" status="0">待联系 <span
                            id="statuslist0">（0）</span></a>
                    <a href="javascript:ckeckStatus(1)" name1="statuslist" status="1">待审核 <span
                            id="statuslist1">（0）</span></a>
                    <a href="javascript:ckeckStatus(2)" name1="statuslist" status="2">已审核 <span
                            id="statuslist2">（0）</span></a>
                    <a href="javascript:ckeckStatus(3)" name1="statuslist" status="3">已驳回 <span
                            id="statuslist3">（0）</span></a>
                    <a href="javascript:ckeckStatus(4)" name1="statuslist" status="4">待调整 <span
                            id="statuslist4">（0）</span></a>
                    <a href="javascript:ckeckStatus(5)" name1="statuslist" status="5">已删除 <span
                            id="statuslist5">（0）</span></a>
                </div>
            </div>
            <table class="rw-table" style="table-layout: fixed;">
                <thead>
                <tr>
                    <th style="width:30px"><input type="checkbox"/></th>
                    <th style="width:200px">来源</th>
                    <th>申请人</th>
                    <th>联系手机</th>
                    <th>行业</th>
                    <th>申请支行</th>
                    <th>申请金额</th>
                    <th>申请时间</th>
                    <th>查看详情</th>
                </tr>
                </thead>
                <tbody id="userTable">
                </tbody>
            </table>
            <div id="News-Pagination"></div>
            <div class="btn-div">
                <button class="index-btn" onclick="exportList()">
                    下&nbsp;&nbsp;载
                </button>
                <button class="index-btn" onclick="showVerify()" id="showVerify">
                    审&nbsp;&nbsp;核
                </button>
                <button class="index-btn" onclick="showstatus2()" id="showstatus2">
                    提交至审核
                </button>
                <button class="index-btn" onclick="showstatus3()" id="showstatus3">
                    驳&nbsp;&nbsp;回
                </button>
                <button class="index-btn" onclick="showVerify2()" id="showVerify2">
                    调&nbsp;&nbsp;整
                </button>
                <button class="index-btn" onclick="showDelete()" id="showDelete">
                    删&nbsp;&nbsp;除
                </button>
            </div>
        </div>
    </div>
</div>

<div class="popup" data-pop="verify">
    <div class="pop-head">
        审核
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-list">
            <div class="list-tit">
                确认审核通过吗？
            </div>
            <div class="pop-cont">
                <div class="app-item success" id="sucess"></div>
                <div class="app-item fail" id="fail"></div>
                <div class="list-item">
                    <span class="list-item-span1">申 请 人：</span>
                    <span class="list-item-span2" id="usernm1">万德武</span>
                </div>
                <div class="list-item">
                    <span class="list-item-span1">申请额度：</span>
                    <span class="list-item-span2" id="loanNum1">30万</span>
                </div>
                <div class="list-item">
                    <span class="list-item-span1">经办银行：</span>
                    <span class="list-item-span2">盐城邮政储蓄银行双元路支盐城邮政储蓄银行双元路支</span>
                </div>
                <div class="pop-txta clearfix">
                    <span>填写备注：</span>
                    <textarea class="pop-ipt-txta" placeholder="请输入备注信息（非必填）" id="remark1">
                        
                    </textarea>
                </div>
            </div>
            <div class="pop-btn">
                <button onclick="submitVerify()">通过审核</button>
            </div>
        </div>
    </div>
</div>


<div class="popup" data-pop="status1">
    <div class="pop-head">
        提交联系
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-div">
            <div class="app-item success" id="sucess2"></div>
            <div class="app-item fail" id="fail2"></div>
            <div class="pop-f1">确认提交联系吗？</div>
        </div>
        <div class="pop-btn">
            <button onclick="submitStatus1()">确认联系</button>
        </div>
    </div>
</div>


<div class="popup" data-pop="status2">
    <div class="pop-head">
        提交审核
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-div">
            <div class="app-item success" id="sucess3"></div>
            <div class="app-item fail" id="fail3"></div>
            <div class="pop-f1">确认提交审核吗？</div>
        </div>
        <div class="pop-btn">
            <button onclick="submitStatus2()">确认审核</button>
        </div>
    </div>
</div>

<div class="popup" data-pop="status3">
    <div class="pop-head">
        提交驳回
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-list">
            <div class="list-tit">
                确认提交驳回吗？
            </div>
            <div class="pop-cont">
                <div class="app-item success" id="sucess4"></div>
                <div class="app-item fail" id="fail4"></div>
                <div class="pop-txta clearfix">
                    <span>填写备注：</span>
                    <textarea class="pop-ipt-txta" placeholder="请输入备注信息（非必填）" id="remarksubmitStatus3">
                        
                    </textarea>
                </div>
            </div>
            <div class="pop-btn">
                <button onclick="submitStatus3()">驳回</button>
            </div>
        </div>
    </div>
</div>

<div class="popup" data-pop="verify2">
    <div class="pop-head">
        调整额度
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-list">
            <div class="app-item success" id="sucess5"></div>
            <div class="app-item fail" id="fail5"></div>
            <div class="list-tit">
                确认调整额度吗？
            </div>
            <div class="list-item">
                <span class="list-item-span1">申 请 人：</span>
                <span class="list-item-span2" id="usernm2">万德武</span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">申请额度：</span>
                <input type="text" id="loanNum2"/>
            </div>
            <div class="list-item">
                <span class="list-item-span1">经办银行：</span>
                <span class="list-item-span2">盐城邮政储蓄银行双元路支盐城邮政储蓄银行双元路支</span>
            </div>
            <div class="pop-txta clearfix">
                <span>填写备注：</span>
                <textarea class="pop-ipt-txta" placeholder="请输入备注信息（非必填）" id="remark2()">
                        
                    </textarea>
            </div>
        </div>
        <div class="pop-btn">
            <button onclick="submitVerify2()">调整额度</button>
        </div>
    </div>
</div>


<div class="popup" data-pop="delete">
    <div class="pop-head">
        删除
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-div">
            <div class="app-item success" id="sucess6"></div>
            <div class="app-item fail" id="fail6"></div>
            <div class="pop-f1">确认删除吗？</div>
        </div>
        <div class="pop-btn">
            <button onclick="submitDelete()">确认删除</button>
        </div>
    </div>
</div>


<div class="popup" data-pop="reset2">
    <div class="pop-head">
        修改密码
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-div">
            <div class="app-item success" id="sucess7"></div>
            <div class="app-item fail" id="fail7"></div>
            <div class="pop-f1">确认修改密码吗？</div>
        </div>
        <div class="app-form">
            <div>
                <span>密码</span>
                <input type="text" id="userpw4"/>
            </div>
        </div>
        <div class="pop-btn">
            <button onclick="submitReset2()">确认修改</button>
        </div>
    </div>
</div>

<div class="popup" data-pop="detail">
    <div class="pop-head">
        详情
        <span class="close">X</span>
    </div>
    <div class="pop-cont">
        <div class="pop-list">
            <div class="list-item">
                <span class="list-item-span1">申 请 人：</span>
                <span class="list-item-span2" id="usernm3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">申请额度：</span>
                <span class="list-item-span2" id="loanNum3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">联系手机：</span>
                <span class="list-item-span2" id="phonenum3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">经办银行：</span>
                <span class="list-item-span2" id="bank3">盐城邮政储蓄银行双元路支盐城邮政储蓄银行双元路支</span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">推荐人：</span>
                <span class="list-item-span2" id="referrals3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">行业：</span>
                <span class="list-item-span2" id="workunit3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">是否为本地人：</span>
                <span class="list-item-span2" id="localPerson3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">本地是否有房产：</span>
                <span class="list-item-span2" id="house3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">年销售额（万元）：</span>
                <span class="list-item-span2" id="income3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">担保方式：</span>
                <span class="list-item-span2" id="guaranteeType3"></span>
            </div>
            <div class="list-item">
                <span class="list-item-span1">审核情况：</span>
                <span class="list-item-span2" id="status99"></span>
            </div>


        </div>
    </div>
</div>

<input type="hidden" id="userid" value='<%=session.getAttribute("userid")%>'>
<input type="hidden" id="usercode" value='<%=session.getAttribute("usercode")%>'>
<input type="hidden" id="bank" value='<%=session.getAttribute("bank")%>'>
<input type="hidden" id="userrole" value='<%=session.getAttribute("userrole")%>'>
<input type="hidden" id="loanType" value='生意贷'>
<script type="text/javascript" src="<%=request.getContextPath()%>/src/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/src/js/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/src/js/jsRender.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/src/js/list.js"></script>

<script id="userTableTmpl" type="text/x-jsrender">  
<tr>
    <td style="width:30px"><input type="checkbox" loanid="{{:loanid}}" usernm="{{:usernm}}" loanNum="{{:loanNum}}" status="{{:status}}"/></td>
    <td style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden; ">{{:utmsrc}}</td>
    <td>{{:usernm}}</td>
    <td>{{:phonenum}}</td>
    <td>{{:workunit}}{{if workunit2}}-{{:workunit2}}{{/if}}</td>
    <td>{{:area}}{{if bank}}-{{:bank}}{{/if}}</td>
    <td>{{:loanNum1}}</td>
    <td>{{:createtime}}</td>
    <td><a href="javascript:showDetail({{:loanid}})">详情</a></td>
</tr>
</script>
</body>
</html>