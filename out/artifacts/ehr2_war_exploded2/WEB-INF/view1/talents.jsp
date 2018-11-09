<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>人才库</title>
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
        $("#tid").val(id);

    }
    //判断模糊查询key值是否为空
    function tsubmit() {
        var a=document.getElementById("querykey").value;

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
            <form action="add.tdo"  id="myFrom" method="post">
                <div class="modal-body">
                    <div align="center">

                        Id：<input type="text" name="id" style="width: 170px"><br/>
                        姓名：<input type="text" name="username" style="width: 170px"><br/>
                        工号：<input type="text" name="t_code" style="width: 170px"><br/>
                        性别：<select name="sexual">
                                <option value="1">男</option>
                                <option value="2">女</option>
                                </select>
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

            <form action="update.tdo"  id="myFrom2" method="post">
                <div class="modal-body">
                    <div align="center">
                        <input type="hidden" name="id" value="" id="tid"/>
                        姓名：<input type="text" name="username" style="width: 170px"><br/>
                        性别：<select name="sexual">
                                <option value="1">男</option>
                                <option value="2">女</option>
                                </select><br/>
                        就职时间：<input type="text" name="employ_date" style="width: 170px"><br/>
                        离职时间：<input type="text" name="unemploy_date" style="width: 170px"><br/>
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
        <input type="text" class="form-control" placeholder="查询" id="query-value" value="">
    </div>
    <a class="btn btn-info" id="user-query" >查询</a>
    
    <%--<form action="talents" method="get">
    <div class="col-md-2">
        <input type="text" class="form-control" name="queryvalue" placeholder="查询" id="query-value" value="">
    </div>
        <input type="submit"><a class="btn btn-info" id="user-query" >查询</a></input>--%>
    </form>
    <span class="col-md-1"></span>
    <button class="btn btn-info" data-toggle="modal" data-target="#myModal">新增</button>
</div>

</div>
<br><br>
<hr>
<table  class="table table-striped">
    <thead>
    <tr align="center" >
        <td class="col-md-1"><b>序号</b></td>
        <td class="col-md-1"><b>编号</b></td>
        <td class="col-md-1"><b>姓名</b></td>
        <td class="col-md-1"><b>性别</b></td>
        <td class="col-md-2"><b>入职时间</b></td>
        <td class="col-md-2"><b>离职时间</b></td>
        <td class="col-md-4"><b>操作</b></td>
    </tr>
    </thead>
    <tbody id="tb" align="center">
    <c:forEach items="${requestScope.talents}" var="talents" varStatus="status">
        <tr align="center" id="table-body">
            <td>${(requestScope.pages-1)*5+status.count}</td>
            <td>${talents.id}</td>
            <td>${talents.name}</td>
            <td>${talents.sexual>0?'男':'女'}</td>
            <td>${talents.employDate}</td>
            <td>${talents.unemployDate}</td>
            <td>
                <button class="btn btn-info" data-toggle="modal" data-target="#myModa2" onclick="editAddress(${talents.id},this)">修改</button>
                <a href="delete.tdo?talentid=${talents.id}"><button class="btn btn-info" data-toggle="modal">删除</button></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <form name="f1" method="get" action="talents" >
        <table border="0" align="center" >
            <tr id="pageTo">
                <td>第${requestScope.pages}页/共${requestScope.totalpages}页<a href="talents?pages=1">首页</a></td>
                <td><a href="talents?pages=${requestScope.pages-1 }"> 上一页</a></td>
                <td><a href="talents?pages=${requestScope.pages+1}"> 下一页</a></td>
                <td><a href="talents?pages=${requestScope.totalpages}">最后一页</a></td>
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
    $(document).ready(function(){
        //queryvalue
        $("#user-query").click(function () {
            var queryvalue = $("#query-value").val();
            if(queryvalue==null||queryvalue==""){
                alert("请输入值！")
            }else{
                $.ajax({
                    url:"ajaxtalents?queryvalue="+queryvalue,
                    method:"get",
                    async:true,
                    datatype:'json',
                    success:function (data) {
                        var json=JSON.parse(data);
                        //console.log(json);
                        $("#tb").empty();
                        $("#pageTo").empty();
                        //alert("<td>第"+json.pages+"页/共"+(json.totalpages)+"页</td><td><a href='talents?pages="+(json.totalpages-1)+">上一页</a></td><td><a href='talents?pages="+(json.totalpages+1)+"'>下一页</a></td>");
                        $("#pageTo").append("<td>第"+(json.pages)+"页/共"+(json.totalpages)+"页<a href='talents?pages=1'>首页</a></td><td><a href='talents?pages="+(json.totalpages-1)+"'>上一页</a></td><td><a href='talents?pages="+(json.totalpages+1)+"'>下一页</a></td><td><a href='talents?pages="+json.totalpages+"'>最后一页</a></td>");
                        //var talents = new Array(json.talents);
                        console.log(json.talents[0].employdate);
                        for(i in json.talents){
                            //alert("152");
                            $("#tb").append("<tr><td>"+((json.pages-1)*5+i)+"</td><td>"+(json.talents[i].id)+"</td><td>"+(json.talents[i].name)+"</td><td>"+(json.talents[i].sexual>0?'男':'女')+"</td><td>"+(json.talents[i].employdate)+"</td><td>" + (json.talents[i].unemploydate) + "</td>"+
                                "<td><button class='btn btn-info' data-toggle='modal' data-target='#myModa2' onclick='editAddress("+json.talents[i].id+",this)'>"+"修改</button><a href='delete.tdo?talentid="+json.talents[i].id+"'><button class='btn btn-info' data-toggle='modal'>删除</button></a></td></tr>");
                        }
                        //location.reload(false);
                    }
                })
            }
        })

    })

</script>
</body>
</html>
