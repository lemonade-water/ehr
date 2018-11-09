<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="index.html" style="font-size: large">人事管理系统</a>
        </div>
        <div class="container-fluid">
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
                            <i class="lnr lnr-alarm"></i>
                            <span class="badge bg-danger">5</span>
                        </a>
                        <ul class="dropdown-menu notifications">
                            <li><a href="#" class="notification-item"><span class="dot bg-warning"></span>System space is almost full</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/user.png" class="img-circle" alt="Avatar"> <span>Samuel</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
                            <li><a class="btn btn-primary btn-lg" data-toggle="modal" data-target="#outing"></i> <span>Logout</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <%--模态框--%>
    <div class="modal fade" id="outing" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>

            </div>
            <div class="modal-body">
                确定退出吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a href="outing"><button type="button" class="btn btn-primary" id="button_outing">确定</button></a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
