<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>角色管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, role-scalable=0">
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
    //模态框传值
    function editUandR(id,id1,obj){
        $("#role_id").val(id);
        $("#user_id").val(id1);

    }
    //判断模糊查询key值是否为空
    function asubmit() {
        var a=document.getElementById("querykey").value;
        console.log(a);
        if(a==""){
            return false;
        }
        $("#role_query").attr("href","queryKey.urdo?queryKey="+a)
        return true;
    }

   /* $("#bt_add").onclick(function () {

        $.ajax({
                url:  "add.rdo",
                type: "post",
                async:true,
                contentType: "application/json; charset=utf-8",
                dataType:"json",
                data: {
                    add_role_id: $("#add_role_id").val(),
                    add_role_name: $("#add_role_name").val()
                },
                success:function (data) {
                    var obj = eval('(' + data + ')');
                    alert(obj.success)
            },
            error : function() {
            },
            complete : function() {
            }


        })
    })*/

</script>


<%--新增角色模态框--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
            </div>


            <form action="add.rdo"  id="myFrom" method="post">
                <div class="modal-body">
                    <div align="center">
                        角色Id：<input type="text" name="id" id="add_role_id" style="width: 170px"><br/>
                        角色名：<input type="text" name="rolename" id="add_role_name" style="width: 170px"><br/>

                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary"   id="bt_add">提交</button>


                </div>
            </form>


        </div>
    </div>
</div>

<%--新增用户权限模态框--%>
<div class="modal fade" id="myModa3" tabindex="-1" role="dialog" aria-labelledby="myModalLabe3">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabe3">新增用户角色</h4>
            </div>


            <form action="add.urdo"  id="myFrom3" method="post">
                <div class="modal-body">
                    <div align="center">
                        用户ID：<input type="text" name="user_id" style="width: 170px"><br/>
                        角色ID：<input type="text" name="role_id" style="width: 170px"><br/>

                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary"  id="bt3">提交</button>
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
                <h4 class="modal-title" id="myModalLabe2">修改角色</h4>
            </div>


            <form action="update.rdo"  id="myFrom2" method="post">
                <div class="modal-body">
                    <div align="center">

                        角色ID：<input type="text" readOnly="true" name="id" value="" id="rid"/>
                        角色名称：<input type="text" name="rolename" style="width: 170px"><br/>


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


<%--更新用户角色模态框--%>
<div class="modal fade" id="myModa4" tabindex="-1" role="dialog" aria-labelledby="myModalLabe4">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabe4">修改用户角色</h4>
            </div>


            <form action="update.urdo"  id="myFrom4" method="post">
                <div class="modal-body">
                    <div align="center">
                        用户ID：<input type="text" name="user_id" id="user_id" readOnly="true" style="width: 170px"><br/>
                        角色ID<input type=""  name="role_id"  id="role_id"style="width: 170px"/>



                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary"  id="bt4">提交</button>
                </div>
            </form>


        </div>
    </div>
</div>


<div class="col-md-12">
    <div class="col-md-2">
        <input type="text" class="form-control" placeholder="查询角色权限" id="querykey">

    </div>
    <a class="btn btn-info" href="queryKey.udo" id="role_query" onclick="return asubmit()">查询</a>
    <span class="col-md-1"></span>

    <button class="btn btn-info" data-toggle="modal" data-target="#myModal">新增角色</button>
    <button class="btn btn-info" data-toggle="modal" data-target="#myModa3">新增角色权限</button>
</div>

</div>
<br><br>
<hr>
<table  class="table table-striped">
    <tr align="center"  >
        <td class="col-md-1"><b>角色ID</b></td>
        <td class="col-md-1"><b>角色名</b></td>
        <td class="col-md-1"><b>用户ID</b></td>
        <td class="col-md-4"><b>操作</b></td>
        <td class="col-md-4"><b>用户角色操作</b></td>
    </tr>

    <c:forEach items="${usersRoles}" var="usersRoles">
        <tr align="center">
            <td> ${  usersRoles.roleHrRoles.id}  </td>
            <td>${ usersRoles.roleHrRoles.roleName}</td>
            <td>
                <c:choose>
                    <c:when test="${ usersRoles.userHrUsers.id!=0}">
                        ${  usersRoles.userHrUsers.id}
                    </c:when>
                    <c:otherwise>
                        ----
                    </c:otherwise>
                </c:choose>
               <%-- <c:if test="${ usersRoles.userHrUsers.id!=0} ">${  usersRoles.userHrUsers.id}</c:if>--%>
            </td>
            <td>
                <button class="btn btn-info" data-toggle="modal" data-target="#myModa2" onclick="editAddress(${usersRoles.roleHrRoles.id},this)">修改角色</button>
                <a class="btn btn-info" href="delete.rdo?id=${usersRoles.roleHrRoles.id}">删除角色</a>
            </td>
            <td>

                   <c:choose>
                       <c:when test="${ usersRoles.userHrUsers.id!=0}">
                           <button class="btn btn-info" data-toggle="modal" data-target="#myModa4" onclick="editUandR(${usersRoles.roleHrRoles.id},${ usersRoles.userHrUsers.id},this)">修改权限</button>
                           <a class="btn btn-info" href="delete.urdo?role_id=${usersRoles.roleHrRoles.id}&user_id=${usersRoles.userHrUsers.id}">取消权限</a>
                       </c:when>

                   </c:choose>

            </td>
        </tr>
    </c:forEach>

</table>
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="assets/vendor/chartist/js/chartist.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
</body>
</html>