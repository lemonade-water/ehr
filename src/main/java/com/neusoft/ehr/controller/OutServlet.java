package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.service.UserService;
import com.neusoft.ehr.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OutServlet" ,value="/outing")
public class OutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把登录时间和下线时间存下来
        UserService userService = new UserService();
        HttpSession session = request.getSession(false);
        HrUsers hrUsers = (HrUsers) session.getAttribute("user");

        userService.updateTime(hrUsers.getId(),(String)session.getAttribute("logintime"),DateUtils.getTime());
        request.getSession(false).invalidate();


        response.sendRedirect("login.jsp");
    }
}
