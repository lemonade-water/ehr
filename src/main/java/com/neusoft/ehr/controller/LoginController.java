package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.entity.HrUsersRoles;
import com.neusoft.ehr.service.*;
import com.neusoft.ehr.utils.DateUtils;
import com.neusoft.ehr.utils.FactoryUntil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/login")
public class LoginController extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            //取username和password
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String usertype = request.getParameter("type");
            HttpSession httpSession = request.getSession();
            //去获得HrUsers对象
            HrUsers condition = (HrUsers) FactoryUntil.getObject("HrUsers");
            //封装到HrUsers对象中
            condition.setUsername(username);
            condition.setPassword(password);

            ImpServiceFac userService = FactoryUntil.getClass(UserService.class);
            //实例化UserService对象
            //UserService userService = (UserService) FactoryUntil.getObject("UserService");
            condition= userService.getUser(condition);

            //获得角色对象
            HrUsersRoles hrUsersRoles = (HrUsersRoles) FactoryUntil.getObject("HrUsersRoles");

            ImpServiceFac userRolesService = FactoryUntil.getClass(UserRolesService.class);
            //查询用户角色表，判定是否用户是否匹配角色
            //UserRolesService userRolesService = (UserRolesService) FactoryUntil.getObject("UserRolesService");



            if(condition!=null){
                boolean b = userRolesService.isHasRoles(condition.getId(),Long.parseLong(usertype));
                if(b) {
                    httpSession.setAttribute("usertype",usertype);
                    //登录成功
                    httpSession.setAttribute("user", condition);
                    //记录下登录时间，存放在session里面
                    httpSession.setAttribute("logintime", DateUtils.getTime());

                    ImpServiceFac rolesMenusService = FactoryUntil.getClass(RolesMenusService.class);
                    //获取菜单信息
                    //RolesMenusService rolesMenusService = (RolesMenusService) FactoryUntil.getObject("RolesMenusService");


                    Collection<HrMenus> list = rolesMenusService.queryMenuByRoles(Integer.parseInt(usertype));
                    httpSession.setAttribute("menus", list);
                    //获取用户信息
                    request.getRequestDispatcher("/WEB-INF/view1/index.jsp").forward(request, response);
                    //response.sendRedirect("http://localhost:8080/ehr2/login");
                }else {
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }
            }else {
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            this.doPost(request,response);
    }
}
