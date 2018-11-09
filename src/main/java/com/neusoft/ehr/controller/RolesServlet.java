package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrUsersRoles;
import com.neusoft.ehr.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/31.
 */
@WebServlet(name = "RolesServlet",value = "/roles")
public class RolesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HrUsersRoles> list = new ArrayList<HrUsersRoles>();
        RoleService roleService = new RoleService();
        int flag = 0;
        try {
            flag = (Integer)request.getAttribute("flag");
            list= (List<HrUsersRoles>) request.getAttribute("list");

        }catch (Exception e){
            list=roleService.queryRoles();
        }
        request.setAttribute("usersRoles",list);
        request.getRequestDispatcher("/WEB-INF/view1/roles.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
