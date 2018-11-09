package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrRolesMenus;
import com.neusoft.ehr.service.ImpServiceFac;
import com.neusoft.ehr.service.MenusService;
import com.neusoft.ehr.service.RolesMenusService;
import com.neusoft.ehr.utils.FactoryUntil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MenusServlet",value="/menus")
public class MenusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在这里写请求；
        //查询数据库显示所有的菜单
        MenusService menusService = new MenusService();
        List<Object> list = menusService.queryAll();
        request.setAttribute("menus",list);

        ImpServiceFac rolesMenusService = FactoryUntil.getClass(RolesMenusService.class);
        //查询数据库显示i所有的用户的菜单
        //RolesMenusService rolesMenusService = new RolesMenusService();
        List<Object> rolesmenus = rolesMenusService.queryAll();
        request.setAttribute("rolesmenus",rolesmenus);

        request.getRequestDispatcher("/WEB-INF/view1/menus.jsp").forward(request, response);
    }
}
