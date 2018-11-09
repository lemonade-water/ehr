package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.service.RoleService;
import com.neusoft.ehr.utils.FactoryUntil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/29.
 */
public class RolesController extends HttpServlet {


    //反射实例化
    private RoleService rolesService = (RoleService) FactoryUntil.getObject("RoleService");
    private HrRoles condition = (HrRoles) FactoryUntil.getObject("HrRoles");
    private String tableName = FactoryUntil.getClassName("HrRoles");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取URL
        String servletPath = request.getServletPath();
        //得到方法名，除去.rdo
        String methodName = servletPath.substring(1,servletPath.length()-4);

        //利用反射获取方法
        try {
            Method method =getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);//执行该方法

            response.sendRedirect("roles");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    //新增
    public void add(HttpServletRequest request,HttpServletResponse response){
        /*Long role_id = Long.valueOf(request.getParameter("role_id")); // 接收role_id
        String role_name = request.getParameter("sex");//接收role_name
        System.out.println(role_id);
        System.out.println(role_name);

        //写返回的JSON
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = "{'success':'成功','false':'失败'}";
        pw.print(json);
        pw.flush();
        pw.close();*/
        Long id = Long.valueOf(request.getParameter("id"));
        String rolename = request.getParameter("rolename");
        condition.setId(id);
        condition.setRoleName(rolename);





        rolesService.add(condition);
    }

    //修改
    public void update(HttpServletRequest request,HttpServletResponse response){
        Long id = Long.valueOf(request.getParameter("id"));
        String rolename = request.getParameter("rolename");
        condition.setId(id);
        condition.setRoleName(rolename);
        rolesService.update(condition);
    }

    //查询所有
    public void queryAll(HttpServletRequest request,HttpServletResponse response){

    }

    //删除操作
    public Boolean delete(HttpServletRequest request, HttpServletResponse response) {
        int id =Integer.parseInt(request.getParameter("id"));

        return rolesService.deleteRole(tableName, "id", id);
    }
}
