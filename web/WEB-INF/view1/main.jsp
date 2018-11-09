<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="assets/vendor/chartist/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="assets/css/demo.css">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>
<body>
<script>
    //模态框传值
    function editAddress(id,obj){
        $("#rid").val(id);

    }
    //判断模糊查询key值是否为空
    function asubmit() {
        var a=document.getElementById("querykey").value;
        console.log(a);
        if(a==""){
            return false;
        }
        $("#user_query").attr("href","queryKey.udo?queryKey="+a)
        return true;
    }

</script>
<%--新增模态框--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增User</h4>
            </div>
            <form action="add.udo"  id="myFrom" method="post">
                <div class="modal-body">
                    <div align="center">
                        Id：<input type="text" name="id" style="width: 170px"><br/>
                        姓名：<input type="text" name="username" style="width: 170px"><br/>
                        密码：<input type="password" name="password" style="width: 170px"><br/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary"  id="bt1">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--更新模态框--%>
<div class="modal fade" id="myModa2" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabe2">修改User</h4>
            </div>

            <form action="update.udo"  id="myFrom2" method="post">
                <div class="modal-body">
                    <div align="center">
                        <input type="hidden" name="id" value="" id="rid"/>
                        姓名：<input type="text" name="username" style="width: 170px"><br/>
                        密码：<input type="password" name="password" style="width: 170px"><br/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary"  id="bt2">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>


<div class="col-md-12">
    <div class="col-md-2">
        <input type="text" class="form-control" placeholder="查询" id="querykey">
    </div>
    <a class="btn btn-info" id="user-query" onclick="asubmit()">查询</a>
    <span class="col-md-1"></span>
    <button class="btn btn-info" data-toggle="modal" data-target="#myModal">新增</button>
</div>

</div>
<br><br>
<hr>
<table  class="table table-striped">
    <tr align="center"  >
        <td class="col-md-1"><b>序号</b></td>
        <td class="col-md-1"><b>工号</b></td>
        <td class="col-md-1"><b>姓名</b></td>
        <td class="col-md-1"><b>上一次登录时间</b></td>
        <td class="col-md-1"><b>上一次离线时间</b></td>
        <td class="col-md-4"><b>操作</b></td>
    </tr>

    <c:forEach items="${users}" var="user" varStatus="status">
        <tr align="center">
            <td>${(requestScope.pages-1)*5+status.count}</td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${user.lastLeaveTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
            <button class="btn btn-info" data-toggle="modal" data-target="#myModa2" onclick="editAddress(${user.id},this)">修改</button>
            <a href="delete.udo?userid=${user.id}"><button class="btn btn-info" data-toggle="modal">删除</button></a>
            </td>
        </tr>
    </c:forEach>
    <form name="f1" method="get" action="user" >
        <table border="0" align="center" >
            <tr>
                <td>第${requestScope.pages}页/共${requestScope.totalpages}页<a href="user?pages=1">首页</a></td>
                <td><a href="user?pages=${requestScope.pages-1 }"> 上一页</a></td>
                <td><a href="user?pages=${requestScope.pages+1}"> 下一页</a></td>
                <td><a href="user?pages=${requestScope.totalpages}">最后一页</a></td>
                <td>转到第:<input type="text" name="pages" size="8">页<input type="submit" value="GO" name="cndok"></td>
            </tr>
        </table>
    </form>
</table>
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="assets/vendor/chartist/js/chartist.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
<script>
    //判断模糊查询key值是否为空

</script>
</body>
</html>