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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/index.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/pagination.css" />
</head>

<body>
    <div class="index-cont clearfix">
        <div class="index-left ml">
            <div class="cont-head">
                <img src="<%=request.getContextPath()%>/src/images/logo1.png"/>
            </div>
            <div class="cont-list active">
                帐号密码信息
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
                </div>
                <table class="rw-table" >
                    <thead>
                        <tr>
                            <th><input type="checkbox"/></th>
                            <th>支行</th>
                            <th>员工姓名</th>
                            <th>员工工号</th>
                            <th>登录密码</th>
                            <th>登录时间</th>
                            <th>岗位</th>
                            <th style="display: none;">登录时长</th>
                            
                        </tr>
                    </thead>
                    <tbody id="userTable">
                        
                    </tbody>
                </table>
<div id="News-Pagination"></div>
<div class="btn-div">
                    <button class="index-btn" onclick="addUser()">
                        新增员工
                    </button>
                    <button class="index-btn" onclick="resetUser()">
                        重置密码
                    </button>
                    <button class="index-btn" onclick="deleteUser()">
                        删&nbsp;&nbsp;除
                    </button>
                </div>                
            </div>
        </div>
    </div>
    
    <div class="mark"></div>
    <div class="popup" data-pop="new">
        <div class="pop-head">
            新增员工
            <span class="close">X</span>
        </div>
        <div class="pop-cont">
            <div class="pop-tip">
                温馨提示：初始密码与工号相同
            </div>

            <div class="app-form">
                <div class="app-item success" id="sucess">
                </div>
                <div class="app-item fail" id="fail">
                </div>
                <div class="app-item">
                    <span>姓名</span>
                    <input type="text" placeholder="请输入您的姓名" id="usernm">
                </div>
                <div class="app-item">
                    <span>工号</span>
                    <input type="text" placeholder="请输入您的手机号码" id="usercode">
                </div>
                <div class="app-item">
                    <span>银行</span>
                    <select class="app-sel" id="bank">
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
                <div class="app-item">
                    <span>岗位</span>
                    <select class="app-sel" id="userrole">
                        <option>市分行管理岗</option>
                        <option>支行营销岗</option>
                        <option>授信管理岗</option>
                    </select>
                </div>
            </div>
            <div class="pop-btn">
                <button onclick="submitUser()">确认创建</button>
            </div>
        </div>
    </div>
    <div class="popup" data-pop="reset">
        <div class="pop-head">
            重置密码
            <span class="close">X</span>
        </div>
        <div class="pop-cont">
            <div class="pop-div">
            	<div class="app-item success" id="sucess2"></div>
             	<div class="app-item fail" id="fail2"></div>
                <div class="pop-f1">确认重置此员工的密码吗？</div>
                <div class="pop-f2">初始密码密码与工号相同</div>
            </div>
            <div class="pop-btn">
                <button onclick="submitReset()">确认重置</button>
            </div>
        </div>
    </div>
    
    <div class="popup" data-pop="delete">
        <div class="pop-head">
            删除员工
            <span class="close">X</span>
        </div>
        <div class="pop-cont">
            <div class="pop-div">
            	<div class="app-item success" id="sucess3"></div>
             	<div class="app-item fail" id="fail3"></div>
                <div class="pop-f1">确认删除此员工吗？</div>
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
            	<div class="app-item success" id="sucess4"></div>
             	<div class="app-item fail" id="fail4"></div>
                <div class="pop-f1">确认修改密码吗？</div>
            </div>
            <div class="app-form">
	            <div > 
	            	<span>密码</span>
	            	<input type="text" id="userpw4"/>
	            </div>
            </div>
            <div class="pop-btn">
                <button onclick="submitReset2()">确认修改</button>
            </div>
        </div>
    </div>
    
        
	<input type="hidden" id="userid" value='<%=session.getAttribute("userid")%>'>
    <input type="hidden" id="usercode" value='<%=session.getAttribute("usercode")%>'>
    <script type="text/javascript" src="<%=request.getContextPath()%>/src/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/src/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/src/js/index.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/src/js/jsRender.js"></script>
    
<script id="userTableTmpl" type="text/x-jsrender">  
<tr>
                            <td><input type="checkbox" value="{{:userid}}" usercode="{{:usercode}}"/></td>
                            <td>{{:bank}}</td>
                            <td>{{:usernm}}</td>
                            <td>{{:usercode}}</td>
                            <td>{{:userpw}}</td>
                            <td>{{:loginTime}}</td>
                            <td>{{:userrole}}</td>
                            <td  style="display: none;">0</td>
                        </tr>
</script>      
</body>
</html>