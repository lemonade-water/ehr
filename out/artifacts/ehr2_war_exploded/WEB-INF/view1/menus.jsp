<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

</script>
<%--新增模态框--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增菜单</h4>
            </div>
                <div class="modal-body">
                    <div align="center">
                        url：<input type="text" id="url" name="username" style="width: 170px" placeholder="下划线“/”开始"><br/>
                        菜单名字：<input type="text" id="menusname" name="text" style="width: 170px"><br/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary"  id="sub_menu">提交</button>
                </div>
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
                <h4 class="modal-title" id="myModalLabe2">授予角色菜单</h4>
            </div>
                <input type="hidden" value="" id="hidden_menuId">
                授予人：${sessionScope.user.username}<br>
                授予此菜单的角色：<select id="modal2_select_add" name="modal2_select_add">
                                </select>
            <div class="modal-body">
                    <div align="center" id="select_role">

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary"  id="select_add">ok</button>
                </div>

        </div>
    </div>
</div>


<%--删除--%>
<div class="modal fade" id="myModa3" tabindex="-1" role="dialog" aria-labelledby="myModalLabe3">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabe3"></h4>
            </div>
            <input type="hidden" value="" id="del_menu_id">
            <input type="hidden" value="" id="del_role_id">

        </select>
            <div class="modal-body">确定删除吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary"  id="del_menu_role">确定</button>
            </div>

        </div>
    </div>
</div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion"
                   href="#collapseOne">
                    菜单管理
                </a>
                </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
                <button class="btn btn-info" data-toggle="modal" data-target="#myModal">新增菜单</button>
                <br>
                <br>
                <table  class="table table-responsive">
                    <tr align="center" >
                        <td class="col-md-1"><b>序号</b></td>
                        <td class="col-md-1"><b>菜单名字</b></td>
                        <td class="col-md-1"><b>菜单地址</b></td>
                        <td class="col-md-3"><b>操作</b></td>
                    </tr>
                    <c:forEach var="menu" items="${requestScope.menus}" varStatus="status">
                    <tr align="center" id="${menu.id}">
                        <td class="col-md-1"><b>${status.count}</b></td>
                        <td class="col-md-1"><b>${menu.name}</b></td>
                        <td class="col-md-1"><b>${menu.url}</b></td>
                        <td>
                            <button class="btn btn-info" onclick="queryroles(${menu.id})">授予角色此菜单</button>
                            <button class="btn btn-info" id="delete-menu" onclick="del(${menu.id})"><a>删除菜单</a></button>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>


<div class="panel panel-default">
    <div class="panel-heading">
        <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion"
               href="#collapseTwo">
                用户拥有权限管理
            </a>
        </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse">
        <hr>
        <table  class="table table-striped">
            <tr align="center"  >
                <td class="col-md-1"><b>序号</b></td>
                <td class="col-md-1"><b>角色名字</b></td>
                <td class="col-md-1"><b>已拥有菜单</b></td>
                <td class="col-md-4"><b>操作</b></td>
            </tr>

            <c:forEach items="${requestScope.rolesmenus}" var="rolesmenu" varStatus="status">
                <tr align="center" id="${rolesmenu.roleHrRoles.id}">
                    <td>${status.count}</td>
                    <td>${rolesmenu.roleHrRoles.roleName}</td>
                    <td>${rolesmenu.menuHrMenus.name}</td>
                    <td>
                        <button class="btn btn-info" onclick="del_role_menu(${rolesmenu.roleHrRoles.id},${rolesmenu.menuHrMenus.id})">删除角色菜单</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </div>
</div>

<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="assets/vendor/chartist/js/chartist.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
<script>
    //判断模糊查询key值是否为空
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
    $(document).ready(function () {
        $("#sub_menu").click(function () {
            var url = $("#url").val();
            var menusname = $("#menusname").val();
            if(url==""||url==null||menusname==""||menusname==null){
                alert("url和menusname不能为空!");
            }else{
                $.ajax({
                   url:"ajaxAddMenu",
                   method:"post",
                   data:{
                       url:url,
                       menusname:menusname,
                    },
                    datatype:'json',
                   async:true,
                   success:function (data) {
                        var json = JSON.parse(data);

                        if(json.mesId==200){
                            alert(json.mesName);
                            location.reload(false);
                        }else {
                            alert(json.mesName);
                        }
                   }
                });
            }
        });
    });

    function del(del_id){
        //console.log(del_id);
        $.ajax({
                url:"ajaxAddMenu?delete_id="+del_id,
                method:"get",
                dataType:'JSON',
                async:true,
                success:function (data1) {
                    if(data1.delmesId==200){
                        alert(data1.delmesName);
                        location.reload(false);
                    }else{
                        alert(data1.delmesName);
                    }
                }
            }
        );
    }
    function queryroles(queryroles) {
        //console.log(queryroles);
        $.ajax({
            url:"querole?query_id="+queryroles,
            method:"get",
            dataType:'JSON',
            async:true,
            success:function (data) {
                //console.log(data[0]);
                $("#myModa2").modal("show");
                $("#hidden_menuId").val(queryroles);
                //prepend
                $("#modal2_select_add").empty();
                for(var i in data) {
                    $("#modal2_select_add").append("<option id='"+data[i].roles_id+"'>"+data[i].roles_name+"</option>");
                }
            }
        });
    }


    $("#select_add").click(function () {
        var menu_id = $("#modal2_select_add option:selected").attr("id");
        var role_id = $("#hidden_menuId").val();
        //console.log(role_id);
        $.ajax({
            url:"querole",
            method:"post",
            datatype:"JSON",
            data:{
                role_id:role_id,
                menu_id:menu_id,
            },
            async:true,
            success:function (data) {
                var json = JSON.parse(data);
                if(json.mes_id==200){
                    alert(json.mes_name);
                    $("#myModa2").modal("hide");
                    window.location.reload(true);
                }else {
                    alert(json.mes_name);
                }
            }
        })
    })
    
    function del_role_menu(role_id,menu_id) {
            $("#del_menu_id").val(menu_id);
            $("#del_role_id").val(role_id);
            $("#myModa3").modal("show");
    }

    $("#del_menu_role").click(function () {
        var menu_id = $("#del_menu_id").val();
        var role_id = $("#del_role_id").val();
        console.log(menu_id);
        console.log(role_id);
        if(menu_id!=""||role_id!=""){
            $.ajax({
                url:"delrolemenu",
                method:"post",
                datatype:"JSON",
                data:{
                    menu_id:menu_id,
                    role_id:role_id,
                },
                async:true,
                success:function (data) {
                    var json = JSON.parse(data);
                    if(json.mes_id==200){
                        alert(json.mes_name);
                        $("#myModa3").modal("hide");
                        $("#"+role_id).empty();
                    }else {
                        alert(json.mes_name);
                    }
                }
            })
        }
    });
</script>
</body>
</html>
