<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="sidebar-scroll">
        <nav>
            <ul class="nav">     <!--左侧导航栏-->

                    <c:forEach items="${sessionScope.menus}" var="list">
                        <li><a href="${list.url}" target="rightFrame"><i class="lnr lnr-linearicons"></i> <span>${list.name}</span></a></li>
                    </c:forEach>

            </ul>
        </nav>
    </div>

